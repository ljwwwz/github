<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同提醒管理--主页</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var extTabObj = null;
		var tab_map = {
			main:			"conWarnMainList",
			contractView:   "viewContractRowID",
			editContractWarn: "editContractWarnRowID",
			viewContractWarnInfo: "viewContractWarnInfoRowID"
		};
		var url = "${ctx}/cmag/conwarn/mag";
		$(document).ready(function() {
			extTabObj=$('#tabs').bindExtTab({});
			var conWarn_title = "合同提醒查询列表";
			//初始化  合同提醒查询列表
			var loadParams = {
				id : 	 tab_map.main,
				title :  conWarn_title,
				url : 	 url
			};
			checkTab(loadParams);
		});
		//供子页面回调使用
		function callBySubPage(params) {
			if(params == "cancle"){
				extTabObj.selectTab(tab_map.main);
				return;
			}
			var tabType = params.tabTypeId;
			var operType = params.operType;
			var url = params.url;
			if(tabType == "contractInfoId"){ //查看合同信息
				if (operType == "viewContractRow") {
					var viewID = tab_map.contractView;
					var loadParams = {
						id : viewID,
						title : '查看合同信息',
						close : true,
						url : url
					}
				}
			}else{
				if (operType == "editContractWarnRow") {
					var loadParams = {
						id : tab_map.editContractWarn,
						title : '修合同提醒信息',
						close : true,
						url : url
					}
				}
				if (operType == "viewContractWarnInfoRow") {
					var loadParams = {
						id : tab_map.viewContractWarnInfo,
						title : '查看合同提醒详细信息',
						close : true,
						url : url
					}
				}
			}
			checkTab(loadParams);
		}
		//判断tab标签页
		function checkTab(tabParams) {
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