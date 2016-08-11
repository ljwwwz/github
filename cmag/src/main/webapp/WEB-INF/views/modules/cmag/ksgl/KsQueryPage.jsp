<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同客商信息管理</title>
	<%@ include file="/WEB-INF/views/include/cmag_head.jsp"%>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script>
		$.jgrid.defaults.width = 780;
		$.jgrid.defaults.responsive = true;
		$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
	<style type="text/css">
		.ui-th-div{
			text-align: center;
		}
	</style>
	<script type="text/javascript">
		var	tabTypeId_map = {
			ksShowID: "ksShowId" ,
			ksDeleteID: "ksDeleteId" ,
		};
		var url_map = {
			ksShow_url: "${ctx}/cmag/contract/ks/show?" ,
			ksDelete_url: "${ctx}/cmag/contract/ks/delete?" ,
		};
		$(function(){  
		    $("#list").jqGrid({
		        url : "${ctx}/cmag/contract/ks/list1",  
		        contentType : 'application/json',  
		        mtype : "post",  
		        datatype : 'json',  
		        jsonReader : {id : "0", repeatitems : false, userdata : "userdata"},  
		        height : "230",  
		        colNames : ["ID", "客商名称", "创建时间", "修改时间", "操作"],  
		        colModel : [{name:"id", index:"id",hidden:true,frozen: true},  
		                    {name:"name", index:"name",width:60,frozen: true},  
		                    {name:"create_time", index:"create_time",width:20},  
		                    {name:"modify_time", index:"modify_time",width:20},  
		                    {name : "act",title : false,width:20,align:'center',
		                    	formatter :function(cellvalue, options, rawObject) {
									var editOp = "<a href='javascript:void(0)' onclick='editKs(\""+rawObject.id+"\")'>编辑</a>";
									var deleteBtn = "<a href='javascript:void(0)' onclick='delKs(\""+rawObject.id+"\")'>删除</a>";
									return "&nbsp;&nbsp;" + editOp + "&nbsp;&nbsp;"+ deleteBtn;
								}}],  
		        pager : "#pager",  
		        autoWidth : true,  
		        rowNum : 10,  
		        rowList : [ 10, 20, 30 ],
		        rownumbers : true,  
		        viewrecords: true,
		        loadonce: true,   
		        loadComplete:function(xhr){
		        	var rowNum = parseInt($(this).getGridParam("records"), 10);
		        	if (rowNum <= 0) {  
		        		top.$.jBox.tip("无数据！","info");
		        	}
		        },
		        loadError:function(xhr,status,error){
		        	top.$.jBox.prompt(error,"系统提示","error");
		        } 
		     }).jqGrid("setFrozenColumns");

			//显示垂直滚动条
			$("#list").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
		    $(window).bind('resize', function() {  
		        $("#list").setGridWidth($("#_grid").width() - 30);  
		    }).trigger('resize');  
		    jQuery("#list").jqGrid('navGrid', '#pager',{edit: false, add: false, del: false, search: false, refresh: true, view: false,position: "left",cloneToTop: false});
		    //查询 
		    $("#btnSubmit").on("click",function(){
		    	var typeDm = $("#typeDm option:selected").val();
				if(typeDm == "00"){
					top.$.jBox.prompt("请选择要查询的客商类型，然后再进行操作！","系统提示","warning");
					return;
				}
				$("#list").jqGrid('setGridParam',{datatype:'json',page:1,postData:{'contractTypeDm':typeDm,'ksName':$("#ksName").val()}}).trigger("reloadGrid"); 
		    });
		    
		});  
		//编辑合同客商信息
		function editKs(id){
			var contractTypeDm = $("#typeDm option:selected").val();
			var flag = "00";
			var url = url_map.ksShow_url + "id="+id+"&contractTypeDm="+contractTypeDm+"&flag="+flag;
			window.parent.callBySubPage({tabTypeId:tabTypeId_map.ksShowID,operType:"ksShow",url:url});
		}
		//删除合同客商信息
		function delKs(id){
			var id = id;
			var contractTypeDm = $("#typeDm option:selected").val();
			top.$.jBox.confirm("确认要导出合同数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$.ajax({
						type:	"post",
						url:	 url_map.ksDelete_url,
						data:{
							id:id,
							contractTypeDm:contractTypeDm
						},
						dataType:"json",
						success:function(data){
							if(data.status == "SUCCESS"){
								top.$.jBox.tip(data.msg,"info");
								$("#list").jqGrid('setGridParam',{datatype:'json',page:1}).trigger("reloadGrid"); 
							}else{
								top.$.jBox.tip(data.msg,"error");
							}
						},
						error:function(textStatus,errorThrown){
							top.$.jBox.prompt("发送初审发送错误！请重新发送！"+textStatus,"error");
						}
					});
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px')
		}
	</script>
</head>
<body>
	<form:form id="updateForm"  modelAttribute="contract"  method="post" class="breadcrumb form-search">
		<sys:message content="${message}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input type="hidden" id="contractTypeDm" name="contractTypeDm">
		<ul class="ul-form" style="text-align:center;">
			<li>
				<label>客商类型：</label>
				<form:select id="typeDm" path="contractType.typeDm" class="input-large">
					<option value="00">请选择要查询的客商类型</option>
					<form:options items="${fns:getDictList('contract_type_dm')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>客商名称：</label>
				<input type="text" id="ksName" name="ksName" maxlength="1500" htmlEscape="false" style="height:26px"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" style="margin-left:15px"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div style="margin-left:20px;width:98%;zoom: 1;" id="_grid">
	    <table id="list" width="100%"></table>   
		<div id="pager"></div> 
	</div>
</body>
</html>