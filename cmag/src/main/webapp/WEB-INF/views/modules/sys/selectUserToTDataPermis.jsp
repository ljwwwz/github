<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分配数据权限</title>
<meta name="decorator" content="blank" />
<style type="text/css">
.ztree-wrap {
	position: relative;
	height: 300px;
	overflow-y: auto;
}
</style>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
	
		var officeTree;
		var userTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
			officeTree = $.fn.zTree.init($("#officeTree"), setting, officeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
						
			// 默认展开一级节点
			var nodes = officeTree.getNodesByParam("level", 0);
			for(var i=0; i<nodes.length; i++) {
				officeTree.expandNode(nodes[i], true, false, false);
			}
					  
			    //部门绑定事件  			    			   
			    officeTreekey = $("#officeTreekey");
			    officeTreekey.bind("focus", focusKey).bind("blur", blurKey).bind("change cut input propertychange", searchNode);
			    officeTreekey.bind('keydown', function (e){if(e.which == 13){searchNode();}});
			    			   			   			
			    //待选人员绑定事件  			    			   
			    userTreekey = $("#userTreekey");
			    userTreekey.bind("focus", focusKey).bind("blur", blurKey).bind("change cut input propertychange", searchNode);
			    userTreekey.bind('keydown', function (e){if(e.which == 13){searchNode();}});
			    	
				setTimeout("search();", "300"); 
		
			
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},				 		        
				callback: {onClick: treeOnClick}};
		
		var officeNodes=[
	            <c:forEach items="${officeList}" var="office">
	            {id:"${office.id}",
	             pId:"${not empty office.parent?office.parent.id:0}", 
	             name:"${office.name}"},
	            </c:forEach>];
	
		var pre_selectedNodes =[
   		        <c:forEach items="${userList}" var="user">
   		        {id:"${user.id}",
   		         pId:"0",
   		         name:"<font color='red' style='font-weight:bold;'>${user.name}</font>"},
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${users}" var="user">
		        {id:"${user.id}",
		         pId:"0",
		         name:"${user.name}"},
		        </c:forEach>];
		var pre_ids = "${selectIds}".split(",");
		var ids = "${selectIds}".split(",");
		var names = "${selectNames}".split(",");
		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){			
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			
			if("officeTree"==treeId){
				$.get("${ctx}/sys/role/users?officeId=" + treeNode.id, function(userNodes){
					userTree = $.fn.zTree.init($("#userTree"), setting, userNodes);
				});
			}
			if("userTree"==treeId){
				//alert(treeNode.id + " | " + ids);
				//alert(typeof ids[0] + " | " +  typeof treeNode.id);
				if($.inArray(String(treeNode.id), ids)<0){
					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
					names.push(String(treeNode.name));
				}
			};
			if("selectedTree"==treeId){					
				if($.inArray(String(treeNode.id), pre_ids)<0){
					selectedTree.removeNode(treeNode);
					ids.splice($.inArray(String(treeNode.id), ids), 1);
					names.splice($.inArray(String(treeNode.name), names), 1);
				}else{						
					selectedTree.removeNode(treeNode);
					ids.splice($.inArray(String(treeNode.id), ids), 1);
					names.splice($.inArray(String(treeNode.name), names), 1);
				}				
			}
		};
		
		
		var key,lastValue = "", nodeList = [], fontCss = {};  
		
		function focusKey(e) { 
			var currentTreeId = $(this).siblings(".ztree").attr("id");
			if(currentTreeId == 'officeTree'){				
				  if (officeTreekey.hasClass("empty")) {  
					  officeTreekey.removeClass("empty");  
				    }  
			}else if(currentTreeId == 'userTree'){				
				  if (userTreekey.hasClass("empty")) {  
					  userTreekey.removeClass("empty");  
				    }  
			}		   
		} 
		
		function blurKey(e) { 			
			var currentTreeId = $(this).siblings(".ztree").attr("id");
			if(currentTreeId == 'officeTree'){				
				 if (officeTreekey.get(0).value === "") {  
					 officeTreekey.addClass("empty");  
				    }  
			}else if(currentTreeId == 'userTree'){					
				 if (userTreekey.get(0).value === "") {  
					 userTreekey.addClass("empty");  
				    }  
			}
			
			searchNode(e);
		}  
		//搜索节点  
		function searchNode(e) {			
			var currentTreeId = $(this).parent().siblings(".ztree").attr("id");
		    var zTree = $.fn.zTree.getZTreeObj(currentTreeId);
		    var value;
		    if(currentTreeId == 'officeTree'){	
		    	// 取得输入的关键字的值
				value = $.trim(officeTreekey.get(0).value); 				      
			}else if(currentTreeId == 'userTree'){	
				// 取得输入的关键字的值
				value = $.trim(userTreekey.get(0).value); 
			}		  		   		    
		    // 按名字查询		    		    
		    var keyType = "name";  		    		  
		    // 如果和上次一次，就退出不查了。		    
		    if (lastValue === value) return;
		    // 保存最后一次
		    lastValue = value; 		  		    
		    var nodes = zTree.getNodes();
		    
		 // 如果要查空字串，就退出不查了。
		    if (value == ""){  		      		        
		        showAllNode(currentTreeId,nodes);
				return;
		    };  
		  
		    hideAllNode(currentTreeId,nodes);
			nodeList = zTree.getNodesByParamFuzzy(keyType, value);
			updateNodes(currentTreeId,nodeList);
		}  
		
		//隐藏所有节点
		function hideAllNode(currentTreeId,nodes){
			var zTree = $.fn.zTree.getZTreeObj(currentTreeId);
			nodes = zTree.transformToArray(nodes);
			for(var i=nodes.length-1; i>=0; i--) {
				zTree.hideNode(nodes[i]);
			}
		}				
		
		//显示所有节点
		function showAllNode(currentTreeId,nodes){
			debugger;
			var zTree = $.fn.zTree.getZTreeObj(currentTreeId);
			nodes = zTree.transformToArray(nodes);
			for(var i=nodes.length-1; i>=0; i--) {
				/* if(!nodes[i].isParent){
					tree.showNode(nodes[i]);
				}else{ */
					if(nodes[i].getParentNode()!=null){
						zTree.expandNode(nodes[i],false,false,false,false);
					}else{
						zTree.expandNode(nodes[i],true,true,false,false);
					}
				    zTree.showNode(nodes[i]);
					showAllNode(currentTreeId,nodes[i].children);
				/* } */
			}
		}
		
		
		//更新节点状态
		function updateNodes(currentTreeId,nodeList) {			
			 if(currentTreeId == 'officeTree'){	
				 officeTree.showNodes(nodeList);
					for(var i=0, l=nodeList.length; i<l; i++) {						
						//展开当前节点的父节点
						officeTree.showNode(nodeList[i].getParentNode()); 
						//tree.expandNode(nodeList[i].getParentNode(), true, false, false);
						//显示展开符合条件节点的父节点
						while(nodeList[i].getParentNode()!=null){
							officeTree.expandNode(nodeList[i].getParentNode(), true, false, false);
							nodeList[i] = nodeList[i].getParentNode();
							officeTree.showNode(nodeList[i].getParentNode());
						}
						//显示根节点
						officeTree.showNode(nodeList[i].getParentNode());
						//展开根节点
						officeTree.expandNode(nodeList[i].getParentNode(), true, false, false);
					} 				      
				}else if(currentTreeId == 'userTree'){	
					userTree.showNodes(nodeList);
					for(var i=0, l=nodeList.length; i<l; i++) {						
						//展开当前节点的父节点
						userTree.showNode(nodeList[i].getParentNode()); 
						//tree.expandNode(nodeList[i].getParentNode(), true, false, false);
						//显示展开符合条件节点的父节点
						while(nodeList[i].getParentNode()!=null){
							userTree.expandNode(nodeList[i].getParentNode(), true, false, false);
							nodeList[i] = nodeList[i].getParentNode();
							userTree.showNode(nodeList[i].getParentNode());
						}
						//显示根节点
						userTree.showNode(nodeList[i].getParentNode());
						//展开根节点
						userTree.expandNode(nodeList[i].getParentNode(), true, false, false);
					}
			    }					
		}
		
		// 开始搜索
		function search() {
			$("#officeSearch").slideToggle(200);
			$("#officeTxt").toggle();
			$("#officeTreekey").focus();			
		}
		
				
	</script>
</head>
<body>
	<div id="assignRole" class="row-fluid span12">

		<div class="span5 ztree-wrap" style="border-right: 1px solid #A8A8A8;">
			
			<div style="position: absolute; right: 8px; top: 5px; cursor: pointer;" onclick="search();">
				<i class="icon-search"></i><label id="officeTxt"></label>
			</div>
			<div id="officeSearch" class="form-search hide"
				style="padding: -10px 0 0 13px;">
				<label for="officeTreekey" class="control-label"
					style="padding: 5px 5px 3px 0;">关键字：</label> <input type="text"
					class="empty" id="officeTreekey" name="officeTreekey"
					maxlength="50" style="width: 110px;">
				<button class="btn" id="btn" onclick="searchNode()">搜索</button>
			</div>

			<p>所在部门：</p>
			<div id="officeTree" class="ztree"></div>
		</div>

		<div class="span4 ztree-wrap">
			
			<div id="userSearch" class="form-search"
				style="padding: -10px 0 0 13px;">
				<label for="userTreekey" class="control-label"
					style="padding: 5px 5px 3px 0;">关键字：</label> <input type="text"
					class="empty" id="userTreekey" name="userTreekey" maxlength="50"
					style="width: 110px;">
				<button class="btn" id="btn" onclick="searchNode()">搜索</button>
			</div>
			
			<p>待选人员：</p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="span2"
			style="padding-left: 16px; border-left: 1px solid #A8A8A8;">
			<p>已选人员：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
