<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同客商信息管理--主页</title>
	<%@ include file="/WEB-INF/views/include/cmag_head.jsp"%>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var extTabObj = null;
		var tab_map = {
			main:	  "ksMainList",
			ksShow:   "ksShow",
			ksDelete:	"ksDelete",	
		};
		var url = "${ctx}/cmag/contract/ks/";
		$(document).ready(function() {
			extTabObj=$('#tabs').bindExtTab({});
			//初始化  合同客商信息查询列表
			extTabObj.addTab({
				id : 	 tab_map.main,
				title :  '合同客商信息查询列表',
				url : 	 url
			})
		});
		//供子页面回调使用
		function callBySubPage(params){
			var tabType = params.tabTypeId;
			var operType = params.operType;
			var url = params.url;
			if(tabType == "ksShowId"){
				if(operType == "ksShow"){
					var loadParams = {
						id : 	tab_map.ksShow,
						title : '修改合同主体',
						close : true,
						url : 	url
					}
				}
				checkTab(loadParams);
			}
			if(tabType == "ksDeleteId"){
				if(operType == "ksDelete"){
					var loadParams = {
						id : 	tab_map.ksDelete,
						title : '删除合同主体',
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