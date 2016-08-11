<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<!-- 搜索表单 -->
	<title>合同维护管理</title>
	<%@ include file="/WEB-INF/views/include/cmag_head.jsp"%>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var flag = "${flag}";
		var myCon = "${myCon}";
		var tabType = "${tabType}";
		var contractNo = "${contractNo}";
		var ctxStatic = "${ctxStatic}";	
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/mang/contractQueryPage.js"></script>
	<script type="text/javascript">
		$(function(){
			//查询
		    $("#btn_query").on("click",function(){
		    	var ss_area = $("#ss_area option:selected").val();
		    	$("#list").jqGrid('setGridParam',{datatype:'json',page:1,postData:
		    	{'contractName':$("#contract_name").val(),'contractNoName':$("#contract_no_name").val(),'qdName':$("#qdrName").val(),'ssArea':$("#ss_area").val(),
		    	 'firstParty':$("#jfOfficeId").val(),'secondParty':$("#yfOfficeId").val(),'qdDate':$("#qd_date").val(),'beginDate':$("#begin_date").val(),'endDate':$("#end_date").val()}}).trigger("reloadGrid");
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
		<c:if test="${tabType != 00}">
			<form:form id="searchForm"  modelAttribute="contract"  method="post" class="breadcrumb form-search">
				<sys:message content="${message}"/>
				<ul class="ul-form">
					<li style="margin-left:-30px">
						<label>合同名称：</label>
						<form:input path="contract_name" maxlength="1500" htmlEscape="false" id="contract_name" style="height:26px"/>
					</li>
					<li>
						<label>合同编号：</label>
						 <form:input path="contract_no_name"  maxlength="1500" htmlEscape="false" id="contract_no_name"  style="height:26px"/>
					</li>
					<li>
						<label>签订人：</label>
						<form:input path="qdrName"  maxlength="1500" htmlEscape="false" id="qdrName"  style="height:26px"/>
					</li>
					<li style="margin-left:-30px">
						<label>所属区域：</label>
						<form:select path="ss_area" maxlength="1500" htmlEscape="false" style="width:206px" id="ss_area">
								<option value="">请选择</option>
								<form:option value="01">华南地区</form:option>
								<form:option value="02">华东地区</form:option>
								<form:option value="03">华中地区</form:option>
								<form:option value="04">华北地区</form:option>
								<form:option value="05">西南地区</form:option>
								<form:option value="06">西北地区</form:option>
								<form:option value="07">东北地区</form:option>
						</form:select>
					</li>
					<li class="clearfix"></li>
					<li>
						<label>合同甲方：</label>
						<sys:treeselect  id="jfOffice" name="jfOffice.id" value="${jfOffice.id}" labelName="jfOffice.name" labelValue="${jfOffice.name}" 
											title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;height:30px"/>
					</li>
					<li style="margin-left:-15px">
						<label style="margin-left: 25px;">合同乙方：</label>
						<sys:treeselect  id="yfOffice" name="yfOffice.id" value="${yfOffice.id}" labelName="yfOffice.name" labelValue="${yfOffice.name}" 
											title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;height:30px"/>
					</li>
					<li style="margin-left:-30px">
						<label>签订时间：</label>
						<form:input id="qd_date" path="qd_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" style="width:207px;height:26px"/>
					</li>
					<li>
						<label>开始时间：</label>
						 <form:input id="begin_date" path="begin_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" style="width:207px;height:26px"/>
					</li>
					<li class="clearfix"></li>
					<li style="margin-left:-2px">
						<label>结束时间：</label>
						<form:input id="end_date" path="end_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" style="width:207px;height:26px"/>
					</li>
					<li style="margin-left:-10px">
						<label>归档合同编号：</label> 
						<form:input path="history_conNo" maxlength="40" style="height:26px"/> 
					</li>
					<li class="btns">
						<input id="btn_query" class="btn btn-primary" type="button" value="查询" style="margin-left:15px"/>
						<a id="hr_export" class="btn btn-primary" href="#"><i class="icon-download-alt icon-white"></i>导  出</a>
					</li>
					<li class="clearfix"></li>
				</ul>
			</form:form>
		</c:if>
		<div style="margin-left:20px;width:98%;zoom:1;margin-top:-16px;" id="_grid">
		    <table id="list" width="100%"></table>
			<div id="pager"></div>
		</div>
	</div>
</body>
</html>