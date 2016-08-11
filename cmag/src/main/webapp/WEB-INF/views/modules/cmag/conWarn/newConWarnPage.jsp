<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同提醒新建页面</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		var flag = "${saveFlag}";
		$(function(){
			if(flag == "Y"){
				$("#remind_content").attr("readonly","readonly");
			}
			var contract_noName = "${contract_noName}";
			$("#contract_no_name").text(contract_noName);
		});
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/conWarn/newConWarnPage.js"></script>
	<style type="text/css">
		#disCss{
			margin-left:80px
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="conWarnForm" modelAttribute="contract"  method="post" class="breadcrumb form-search" action="${ctx}/cmag/conwarn/mag/saveConWarn">
		<form:hidden path="conWarn.id"/>
		<input type="hidden" id="contract_no" name="contract_no">
		<input type="hidden" id="contract_noName" name="contract_noName" value="${contract_noName}">
		<input type="hidden" id="saveFlag" name="saveFlag" value="${saveFlag}">
		<br>
		<h4>合同提醒新建</h4>
		<br>
		<ul class="ul-form" style="height: 300px;" >
			<li>	
				<label>提醒合同：</label> 
				<c:if test="${saveFlag != 'Y'}">
					<sys:treeselect id="remindcontract" name="conWarn.warn_contractNo" value="${conWarn.warn_contractNo}" labelName="conWarn.name" labelValue="${conWarn.name}" 
									title="提醒合同" url="/cmag/conwarn/mag/contractTreeData" allowClear="true" notAllowSelectParent="true" cssStyle="width:200px"/>
				</c:if>
				<c:if test="${saveFlag == 'Y'}">
					<sys:treeselect disabled="disabled" id="remindcontract" name="conWarn.warn_contractNo" value="${conWarn.warn_contractNo}" labelName="conWarn.name" labelValue="${conWarn.name}" 
									title="提醒合同" url="/cmag/conwarn/mag/contractTreeData" allowClear="true" notAllowSelectParent="true" cssStyle="width:200px"/>
				</c:if>
			</li>
			<li style="margin-left:80px">
				<label>合同编号：</label>
				<span class="help-inline" id="contract_no_name"></span>
			</li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li>
				<label>提醒人：</label>
					<c:if test="${saveFlag != 'Y' }"> 
						<sys:treeselect id="contractWarn" name="conWarn.remind_person" value="${contract.conWarn.remind_person}" labelName="conWarn.remind_name" labelValue="${contract.conWarn.remind_name}" 
									title="提醒人" url="/sys/office/treeData?type=3&isAll=true" allowClear="true" notAllowSelectParent="true"  cssStyle="width:200px;" checked="true" />
					</c:if>
					<c:if test="${saveFlag == 'Y' }"> 
						<sys:treeselect disabled="disabled" id="contractWarn" name="conWarn.remind_person" value="${contract.conWarn.remind_person}" labelName="conWarn.remind_name" labelValue="${contract.conWarn.remind_name}" 
									title="提醒人" url="/sys/office/treeData?type=3&isAll=true" allowClear="true" notAllowSelectParent="true"  cssStyle="width:200px;" checked="true" />
					</c:if>
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
			</li>
			<li style="margin-left:80px">
				<label>提醒日期：</label>
					<c:if test="${saveFlag != 'Y' }"> 
						<form:input path="conWarn.remind_time" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"   />
					</c:if>
					<c:if test="${saveFlag == 'Y' }">
						<input value="${conWarn.remind_time}" class="input-large" type="text"  readonly="readonly" style="width:205px"/>
					</c:if> 
			</li>
			<li class="clearfix"></li>
			<li class="clearfix"></li>
			<li>
				<label>提醒内容：</label> 
				<form:textarea path="conWarn.remind_content"  rows="3" class="input-xxlarge" id="remind_content"/>
			</li>
		</ul>
		<div class="form-actions">
			<c:if test="${saveFlag != 'Y'}">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
			</c:if>
		</div>
	</form:form>
</body>
</html>