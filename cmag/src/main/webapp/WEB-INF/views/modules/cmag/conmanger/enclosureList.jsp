<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>附件信息页面</title>
  	<meta name="decorator" content="default"/>
  	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
	<form:form id="searchForm" modelAttribute="enclosure" action="${ctx}/cmag/conmanger/mag/" method="post" class="breadcrumb form-horizontal">
		<sys:message content="${message}"/>	
		<h4>
			<c:if test="${tabType == 2}">附件详细信息</c:if><c:if test="${tabType == 5}">扫描件详细信息</c:if>
		</h4>
		<br>
		<br>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>附件</th>
					<!-- 	<th>附件名称</th> -->
					<th>附件上传者</th>
					<th>附件上传时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="enclosure" varStatus="status">
					<tr>
						<td>${status.index  + 1}</td>
						<%-- <td><img src="${enclosure.path}"></td> --%>
						<td>${enclosure.name}</td>
						<td>${enclosure.create_user}</td>
						<td><fmt:formatDate value="${enclosure.upload_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a href="${ctx}/cmag/conmanger/mag/downloadFile?enclosure_id=${enclosure.enclosure_id}&flag=${enclosure.flag}" target="_blank">附件下载</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
<!-- 		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>  -->
	</form:form>
  </body>
</html>
