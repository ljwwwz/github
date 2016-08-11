<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同提醒维护管理</title>
	<%@ include file="/WEB-INF/views/include/cmag_head.jsp"%>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/conWarn/conWarnQueryPage.js"></script>
	<script type="text/javascript">
		$(function(){
			//查询
		   $("#btn_query").on("click",function(){
		    	var remind_state = $("#remind_state option:selected").val();
		    	$("#list").jqGrid('setGridParam',{datatype:'json',page:1,postData:
		    	{'contractName':$("#contract_name").val(),
		    	 'contractNo':$("#contract_no").val(),
		    	 'firstParty':$("#jfOfficeId").val(),
		    	 'secondParty':$("#yfOfficeId").val(),
		    	 'qdDate':$("#qd_date").val(),
		    	 'remind_state':remind_state,
		    	 'remind_person':$("#conWarnId").val()}}).trigger("reloadGrid");
		    });
		});
	</script>
	<style type="text/css">
		.ui-th-div{
			text-align: center;
		}
	</style>
</head>
<body>
	<div>
		<form:form id="searchForm"  modelAttribute="contract"  method="post" class="breadcrumb form-search">
			<sys:message content="${message}"/>
			<ul class="ul-form">
				<li style="margin-left:-30px">
					<label>合同名称：</label>
					<form:input path="contract_name" maxlength="1500" htmlEscape="false" id="contract_name" style="height:26px"/>
				</li>
				<li>
					<label>合同编号：</label>
					 <form:input path="contract_no_name"  maxlength="1500" htmlEscape="false" id="contract_no"  style="height:26px"/>
				</li>
				<li>
					<label>合同甲方：</label>
					<sys:treeselect  id="jfOffice" name="jfOffice.id" value="${jfOffice.id}" labelName="jfOffice.name" labelValue="${jfOffice.name}" 
										title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;height:30px"/>
				</li>
				<li class="clearfix"></li>
				<li style="margin-left:-30px">
					<label>合同乙方：</label>
					<sys:treeselect  id="yfOffice" name="yfOffice.id" value="${yfOffice.id}" labelName="yfOffice.name" labelValue="${yfOffice.name}" 
										title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;height:30px"/>
				</li>
				<li>
					<label>签订时间：</label>
					<form:input id="qd_date" path="qd_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" style="width:207px;height:26px"/>
				</li>
				<li>
					<label>提醒人：</label>
					<sys:treeselect id="conWarn" name="conWarn.remind_person" value="${contract.conWarn.remind_person}" labelName="conWarn.remind_name" labelValue="${contract.conWarn.remind_name}" 
									title="提醒人" url="/sys/office/treeData?type=3&isAll=true" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;height:30px"/>
				</li>
				<li style="margin-left:-30px">
					<label>提醒状态：</label>
					<form:select path="remind_state" maxlength="1500" htmlEscape="false" style="width:206px" id="remind_state">
						<option value="00">请选择</option>
						<form:option value="01">正常</form:option>
						<form:option value="02">提醒中</form:option>
						<form:option value="03">已忽略</form:option>
					</form:select>
				</li>
				<li class="clearfix"></li>
				<li class="btns">
					<input id="btn_query" class="btn btn-primary" type="button" value="查询" style="margin-left:15px"/>
				</li>
			</ul>
		</form:form>
		<div style="margin-left:20px;width:98%;zoom:1;" id="_grid">
			<table id="list" width="100%"></table>
			<div id="pager"></div>
		</div>
	</div>
</body>
</html>