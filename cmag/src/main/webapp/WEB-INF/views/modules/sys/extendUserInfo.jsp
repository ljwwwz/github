<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/newCon/extendUserInfo.js"></script>
</head>
<body>
	<div class="control-group" style="margin-top:120px;">
		<div class="row">
			<div class="col-xs-6" style="margin-left:125px;">
				<a id="noUploadScan" style="cursor:pointer;"><strong>未上传扫描件 <span class="badge">${upScanInfo}</span></strong></a>
				<a id="noSendVerify" style="margin-left: 10px;cursor:pointer;"><strong>待发送初审 <span class="badge">${preSend}</span></strong></a>
				<a id="praeiudicium" style="margin-left: 10px;cursor:pointer;"><strong>初审中 <span class="badge">${verifying}</span></strong></a>
				<a id="noConfirmScan" style="margin-left: 10px;cursor:pointer;"><strong>扫描件待确认<span class="badge">${scanConfirm}</span></strong></a>
				<a id="scanConfrim" style="margin-left: 10px;cursor:pointer;"><strong>合同生效中 <span class="badge" style="background-color: red;">${fulInfo}</span></strong></a>
			</div>
		</div>
		<br> <br> <br> <br> <br>
		<!-- <div class="row">
				<div class="col-xs-6" style="margin-left:125px;">
					 <strong>两个月到期 <span class="badge">42</span></strong>&nbsp;&nbsp;&nbsp;&nbsp;
					 <strong>一个月到期 <span class="badge">42</span></strong>&nbsp;&nbsp;&nbsp;&nbsp;
					 <strong style="color: red;">已到期 <span class="badge" style="background-color: red;">42</span></strong>
				 </div>
			</div> -->
	</div>
</body>
</html>