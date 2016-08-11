<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>合同查看记录</title>
  	<meta name="decorator" content="default"/>
  	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		table th, table td { 
			text-align: center; 
			height:38px;
		}
	</style>
  </head>
  <body>
	<form:form id="searchForm" modelAttribute="enclosure" action="${ctx}/cmag/conmanger/mag/" method="post" class="breadcrumb form-horizontal">
		<sys:message content="${message}"/>	
		<h4>
			<c:if test="${tabType == 2}">合同查看记录</c:if><c:if test="${tabType == 5}">合同查看记录</c:if>
		</h4>
		<br>
		<br>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>合同名称</th>
					<th>具体操作</th>
					<th>操作人登录名</th>
					<th>操作人姓名</th>
					<!-- 	<th>附件名称</th> -->
					<th>查看时间</th>
					<th>操作结果</th>
					<!-- <th>提交参数</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logs}" var="log" varStatus="status">
					<tr>
						<%-- <td><img src="${enclosure.path}"></td> --%>
						<td>${log.contract_name}</td>
						<td>
							<c:if test="${log.url == 'checkContract'}">
								查看了合同
							</c:if>
							<c:if test="${log.url == 'downloadFile'}">
								下载了附件
							</c:if>
							<c:if test="${log.url == 'getEnclosure'}">
								查看了附件
							</c:if>
						</td>
						<td>${log.loginName}</td>
						<td>${log.name}</td>
						<td><fmt:formatDate value="${log.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${log.result}</td>
						<%-- <td>${log.args}</td> --%>
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
