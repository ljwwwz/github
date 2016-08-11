<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置常用联系人</title>
    <meta name="decorator" content="default"/>
	<script type="text/javascript">
		//验证输入数据
		function toVaild(){
			if ("${fns:getUser().id}" != '1'){
				$("#loginUserId").val("${fns:getUser().id}");
				var user_id = "${fns:getUser().id}"; 
			}
			if ("${fns:getUser().id}" == '1'){
				$("#loginUserId").val($("#draftStaffId").val());
				var user_id = $("#draftStaffId").val(); 
			}
			
			var second_id = $("#followStaffId").val();
			if(user_id == "" && second_id == ""){				
				top.$.jBox.prompt("合同拟订人和常用联系人为空，请重新选择！","系统提示","warning");
				return;
			}			
			if (user_id == ""){
				top.$.jBox.prompt("合同拟订人为空，请重新选择！","系统提示","warning");
				return;
			}
			if(second_id == ""){
				top.$.jBox.prompt("常用跟踪人为空，请重新选择！","系统提示","warning");
				return;
			} 
			if (toEqual(user_id,second_id)){
				top.$.jBox.prompt("常用跟踪人中包含合同拟订人，请重新选择！","系统提示","warning");
				return;
			}
			$("#topContraForm").submit();
		}
		//验证合同拟订人与常用联系人是否一致
		function toEqual(user_id,second_id){
			var sid = second_id.split(",");
			for (var i = 0;i < sid.length; i++){
				if(user_id == sid[i]){
					return true;
				}
			}
		}
		//查询常用联系人
		function find(){
			if ("${fns:getUser().id}" != '1'){
				$("#loginUserId").val("${fns:getUser().id}");
				var user_id = "${fns:getUser().id}"; 
			}
			if ("${fns:getUser().id}" == '1'){
				$("#loginUserId").val($("#draftStaffId").val());
				var user_id = $("#draftStaffId").val(); 
			}
			if (user_id == ""){
				top.$.jBox.prompt("合同拟订人为空，请重新选择！","系统提示","warning");
				return;
			} 
			$("#topContraForm").attr("action","${ctx}/cmag/newcontract/contractData/topContByFind");
			$("#topContraForm").submit();
	}
	</script>
  </head>
  <body>
		<br/>
		<div class="tab-content">
			<form:form id="topContraForm" modelAttribute="contract" action="${ctx}/cmag/newcontract/contractData/insTopCon" method="post" class="form-horizontal" >
				<sys:message content="${message}"/>
				<input name="loginUserId"  id="loginUserId" type="hidden" />	
				<div class="control-group">
					<label class="control-label"><strong>合同拟订人：</strong></label>
					<c:if test="${fns:getUser().id == '1'}">
						<sys:treeselect id="draftStaff" name="draftStaff.id" value="${draftStaff.id}" labelName="draftStaff.name" labelValue="${draftStaff.name}" 
										title="合同拟订人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"  cssStyle="width:170px;" />
					</c:if>								
					<c:if test="${fns:getUser().id != '1'}">		
						<input name="loginUserName" type="text" value="${fns:getUser().name}" readonly="readonly"/>
					</c:if>	
					<span class="help-inline"><font color="red">*</font> </span>
					<c:if test="${ flag != 'Y'}">
					<span><input id="btnButton" class="btn btn-primary" type="button" value="查询跟踪人" style="margin-left:50px" onclick="find()"/></span>
					</c:if>
					<br>
					<br>
				</div>
				<c:if test="${flag == 'Y'}">
				<div class="control-group">
					<br>
					<br>
					<label class="control-label"><strong>常用跟踪人：</strong></label>
						<sys:treeselect id="followStaff" name="followStaff.id" value="${followStaff.id}" labelName="followStaff.name" labelValue="${followStaff.name}" 
										title="常用跟踪人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:170px;"/>
					<span class="help-inline"><font color="red">*</font> </span>
					<br>
					<br>
				</div>
				</c:if>
				<c:if test="${flag == 'Y'}">
					<shiro:hasAnyPermissions name="cmag:newcontract:contractData:edit">
						<input id="btnSubmit" class="btn btn-primary" value="提交设置" style="margin-left:220px" type="button" onclick="toVaild()" />&nbsp;
					</shiro:hasAnyPermissions>
				</c:if>
			</form:form>
		  </div>
  </body>
</html>
