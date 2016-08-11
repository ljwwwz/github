<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同客商新增页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var updateFlag = "${updateFlag}";
		function onSubmit(){
			var typeDm = $("#typeDm option:selected").val();
			var ksName = $("#ksName").val();
			if(typeDm == "00"){
				top.$.jBox.prompt("请您先选择要新增的客商类型，然后再进行操作！","系统提示","warning");
				return;
			}
			if(ksName == ""){
				top.$.jBox.prompt("请您输入要新增的客商名称，然后再进行操作！","系统提示","warning");
				return;
			}
			if(updateFlag != "Y"){
				$("#saveForm").attr("action","${ctx}/cmag/contract/ks/saveKsInfo");
				$("#saveForm").submit();
			}else{
				$("#saveForm").attr("action","${ctx}/cmag/contract/ks/update");
				$("#saveForm").submit();
			}
		}
	</script>
</head>
<body>
	<br>
	<form:form id="saveForm"  modelAttribute="contract"  method="post" class="form-horizontal">
		<input type="hidden" id="idValue" name="idValue" value="${id}">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" style="margin-left:100px;">请选择客商类型：</label>
			<div class="controls">
				<form:select id="typeDm" path="contractType.typeDm" class="input-large">
					<option value="00">请选择客商类型</option>
					<form:options items="${fns:getDictList('contract_type_dm')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			<br />
			<label class="control-label" style="margin-left:100px;">请输入客商名称：</label>
			<input style="width:200px" type="text" name="ksName" id="ksName" value="${name}">
			<span class="help-inline"><font color="red" >*</font></span>
		</div>
		<shiro:hasPermission name="cmag:newcontract:contractData:edit">
			<div class="form-actions">
			<c:if test="${updateFlag != 'Y'}">
				<a href="#" onclick="onSubmit();" class="btn btn-primary">新 增</a>
			</c:if>
			<c:if test="${updateFlag == 'Y'}">
				<a href="#" onclick="onSubmit();" class="btn btn-primary">修改</a>
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
			</c:if>
			</div>
		</shiro:hasPermission>
	</form:form>
</body>
</html>