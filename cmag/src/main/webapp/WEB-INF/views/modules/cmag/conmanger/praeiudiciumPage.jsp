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
    <title>合同初审页面</title>
  	<meta name="decorator" content="default"/>
  	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		$(document).ready(function() {
			function formSubmit(){
				$("#searchForm").attr("action","${ctx}/cmag/conmanger/mag/setConState");
				$("#searchForm").submit();
			}
			$("#btnOK").click(function(){
				$("#btnFlag").val("Y");
				formSubmit();
			});
			$("#btnNO").click(function(){
				$("#btnFlag").val("N");
				formSubmit();
			});
			//查看合同信息
			$("#conView_cs").click(function(){
				var contract_no = "${contract.contract_no}";
				var contract_type_dm = "${contract.contract_type_dm}";
				var url = "${ctx}/cmag/conmanger/mag/form?contract_no="+contract_no+"&contract_type_dm="+contract_type_dm+"&tabType=1";
				window.parent.callBySubPage({
					tabTypeId : "conViewPraeiudicium",
					operType : "conView_cs",
					url : url
				});
			});
			//返回
			$("#ContPraCancel").click(function() {
				window.parent.callBySubPage({
					falg: "sub",
					operType:"praCancle"
				});
			});
		});
	</script>
  </head>
  <body>
	<form:form id="searchForm" modelAttribute="contract" method="post" class="breadcrumb form-horizontal">
		<sys:message content="${message}"/>	
		<form:hidden path="contract_name"/>
		<form:hidden path="contract_no"/>
		<input type="hidden" id="btnFlag" name="btnFlag">
		<h4>
			合同初审信息
		</h4>
		<br>
		<h5>
			<a id="conView_cs" href="#">查看合同信息</a>
		</h5>
		<br>
		<br>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<!-- <th>合同编号</th> -->
					<th>合同名称</th>
					<th>初审意见</th>
					<th>初审人</th>
					<th>初审时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="verifyInfo" varStatus="status">
					<tr>
						<td>${status.index  + 1}</td>
						<%-- <td>${verifyInfo.contract_no}</td> --%>
						<td>${verifyInfo.contract_name}</td>
						<td>${verifyInfo.info}</td>
						<td>${verifyInfo.verify_person}</td>
						<td><fmt:formatDate value="${verifyInfo.verify_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
		<br>
		<br>
		<shiro:hasPermission name="cmag:conmanger:mag:verifyPass">
			<div class="form-actions">
				<label>初审意见：</label> 
				<form:textarea path="verifyInfo.info" rows="3" class="input-xxlarge" id="info"/>
			</div>
		</shiro:hasPermission>
		<div class="form-actions">
			<shiro:hasPermission name="cmag:conmanger:mag:verifyPass">
				<input id="btnOK" class="btn btn-primary" type="button" value="初审通过" />&nbsp;&nbsp;
				<input id="btnNO" class="btn btn-primary" type="button" value="初审不通过" />&nbsp;&nbsp;
			</shiro:hasPermission>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <a id="ContPraCancel" class="btn" href="#">返回</a> -->
		</div>
	</form:form>
  </body>
</html>
