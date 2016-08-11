<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同维护管理--主页</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var flag = "${flag}";
		var myCon = "${myCon}";
		var pre = "${pre}";
		var extTabObj = null;
		var tab_map = {
			main:			"contractMainList",
			contractView:   "viewContractRow",
			enclosureView:	"viewEnclosureRow",	
			scanView:		"viewScanRow",
			editContractView:"editContractRow",
			praeiudiciumEdit: "praeiudiciumRow",
			uploadScanInfo:	  "uploadScanRow",
			scanConfrim:	  "scanConfrimRow",
			relationCon:	  "relationConRow",
			contractPra:      "conViewPraeiudicium"
		};
		var url = "${ctx}/cmag/conmanger/mag/list?myCon="+myCon+"&flag="+flag;
		$(document).ready(function() {
			extTabObj=$('#tabs').bindExtTab({});
			var conView_title = "合同查询列表";
			if(myCon == "Y"){
				if(flag == "00"){ //未上传扫描件
					conView_title = "未上传扫描件-合同查询列表";
				}
				if(flag == "01"){//待发送初审
					conView_title = "待发送初审-合同查询列表";
				}
				if(flag == "02"){//初审中
					conView_title = "初审中-合同查询列表";
				}
				if(flag == "08"){//合同生效中
					conView_title = "合同生效中-合同查询列表";
				}
				if(flag == "06"){//扫描件待确认
					conView_title = "扫描件待确认-合同查询列表";
				}
			}
			//初始化  合同查询列表
			var loadParams = {
				id : 	 tab_map.main,
				title :  conView_title,
				url : 	 url
			}
			checkTab(loadParams);
		});
		//供子页面回调使用
		function callBySubPage(params){
			var operType = params.operType;
			if(params.falg == "sub"){
				if(operType == "praCancle"){
					extTabObj.selectTab(tab_map.praeiudiciumEdit);
					return;
				}			
			}else{
				if(params == "cancle"){
					extTabObj.selectTab(tab_map.main);
					return;
				}
			}
			var tabType = params.tabTypeId;
			var url = params.url;
			if(tabType == "contractInfoId"){
				if(operType == "viewContractRow"){
				    var viewID = tab_map.contractView;
					var loadParams = {
						id : 	viewID,
						title : '查看合同信息',
						close : true,
						url : 	url
					}
				}
				if(operType == "editContractRow"){
					var loadParams = {
						id : 	tab_map.editContractView,
						title : '修改合同信息',
						close : true,
						url : 	url
					}
				}
				if(operType == "sendToVerify"){
					var loadParams = {
						id : 	tab_map.main,
						url : 	url
					}
				}
				if(operType == "praeiudiciumRow"){
					var loadParams = {
						id : 	tab_map.praeiudiciumEdit,
						title : '合同初审信息',
						close : true,
						url : 	url
					}
				}
				if(operType == "uploadScanRow"){
					var loadParams = {
						id : 	tab_map.uploadScanInfo,
						title : '上传扫描件资料',
						close : true,
						url : 	url
					}
				}
				if(operType == "scanConfrimRow"){
					var loadParams = {
						id : 	tab_map.scanConfrim,
						title : '扫描件信息确认',
						close : true,
						url : 	url
					}
				}
				if(operType == "copyConRow"){
					var loadParams = {
						id : 	tab_map.scanConfrim,
						title : '扫描件信息确认',
						close : true,
						url : 	url
					}
				}
				if(operType == "relationConRow"){
					var loadParams = {
						id : 	tab_map.relationCon,
						title : '查看关联合同信息',
						close : true,
						url : 	url
					}
				}
				checkTab(loadParams);
			}else{
				if(operType == "viewEnclosureRow"){
					var loadParams = {
						id : 	tab_map.enclosureView,
						title : '查看附件信息',
						close : true,
						url : 	url
					}
				}
				if(operType == "viewScanRow"){
					var loadParams = {
						id : 	tab_map.scanView,
						title : '查看扫描件信息',
						close : true,
						url : 	url
					}
				}
				if(operType == "whoCheck"){
					var loadParams = {
						id : 	tab_map.scanView,
						title : '合同查看记录',
						close : true,
						url : 	url
					}
				}
				if(operType == "conView_cs"){
					var loadParams = {
						id : 	tab_map.contractPra,
						title : '合同初审-查看合同信息',
						close : true,
						url : 	url
					}
				}
				checkTab(loadParams);
			}
        }
        //判断tab标签页
        function checkTab(tabParams){
        	if (extTabObj.containTab(tabParams.id)) {
				extTabObj.reloadTab(tabParams);
				extTabObj.selectTab(tabParams.id);
			} else {	
				extTabObj.addTab(tabParams);
			}
		}
		
	</script>
</head>
<body>
	<div id="tabs">
		<ul class="nav nav-tabs" role="tablist"></ul>
		<div class="tab-content"></div>
	</div>
</body>
</html>