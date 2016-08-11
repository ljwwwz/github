<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同提醒详细信息页面</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		var tabType = "${tabType}";
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/conWarn/contractWarnForm.js"></script>
	<style type="text/css">
		#disCss{
			margin-left:80px
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="contract"  method="post" class="breadcrumb form-search" onsubmit="return check(this);">
		<form:hidden path="contract_no_name"/>
		<form:hidden path="conWarn.warn_id"/>
		<br>
		<br>
		<ul class="ul-form" style="height: 400px;" >
			<li>	
				<label>提醒合同：</label> 
				<span class="help-inline"><font color="red">${contract.contract_no_name}</font></span>
			</li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li>
				<label>提醒人：</label> 
				<c:if test="${tabType == 1}">
					<form:input path="conWarn.remind_name" class="input-large" type="text" readonly="readonly" />
				</c:if>
				<c:if test="${tabType == 0}">
					<sys:treeselect id="conWarn" name="conWarn.remind_person" value="${contract.conWarn.remind_person}" labelName="conWarn.remind_name" labelValue="${contract.conWarn.remind_name}" 
									title="提醒人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</c:if>
			</li>
			<li style="margin-left:80px">
				<label>提醒日期：</label>
				<c:if test="${tabType == 1}">
					<form:input path="conWarn.remind_time"  class="input-large" type="text" readonly="readonly" />
				</c:if>
				<c:if test="${tabType == 0}">
					<form:input path="conWarn.remind_time" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"   />
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li>
				<label>备注：</label> 
				<form:textarea path="conWarn.remind_content"  rows="3" class="input-xxlarge" id="remind_content"/>
			</li>
		</ul>
		<div class="form-actions">
			<shiro:hasAnyPermissions name="cmag:conwarn:mag:edit">
				<c:if test="${tabType == 0}">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
				</c:if>
			</shiro:hasAnyPermissions>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="btnCancel" class="btn" href="#">返回</a>
		</div>
	</form:form>
</body>
</html>