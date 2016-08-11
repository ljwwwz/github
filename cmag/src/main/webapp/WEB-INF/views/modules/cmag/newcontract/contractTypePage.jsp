<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同拟定</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		  var extTabObj=null;
          $(function(){
            extTabObj=$('#tabs').bindExtTab({});
			$('#btn_create').click(function(){
				var titleVal = "";
				var typeDm = $("#typeDm option:selected").val();
				if(typeDm == "00"){
					top.$.jBox.prompt("请您先选择要新建的合同类型，然后再进行操作！","系统提示","warning");
					return;
				}
				if(typeDm == "01"){
					titleVal = "供应商合同信息";
				}
				if(typeDm == "02"){
					titleVal = "分销商合同信息";
				}
				if(typeDm == "03"){
					titleVal = "其他合同信息";
				}
				var subPage=extTabObj.findTabPage('tab_'+$(this).attr('id'));
				if(subPage == undefined){
					extTabObj.addTab({
						id : 'tab_' + $(this).attr('id'),
						title : titleVal,
						close : true,
						url : '${ctx}/cmag/newcontract/contractData/contractByType?contractTypeDm='+ typeDm
					})
				}else{
					 var loadParams={
						 id:	'tab_' + $(this).attr('id'),
						 close : true,      //是否可以关闭
						 url:	'${ctx}/cmag/newcontract/contractData/contractByType?contractTypeDm='+ typeDm //指定新url,可不传
					}
					loadParams.title = titleVal;
                    extTabObj.reloadTab(loadParams);
                    extTabObj.selectTab(loadParams.id);
				}
				
			});
		});
	</script>
</head>
<body>
 <div id="tabs">
	<ul class="nav nav-tabs" role="tablist">
		<li class="active" role="presentation" ><a href="#home" aria-controls="home" role="tab" data-toggle="tab">选择新建合同类型</a></li>
	</ul>
	<br>
	 <div class="tab-content">
         <div role="tabpanel" class="tab-pane active" id="home">
			<form:form id="saveForm"  modelAttribute="contract"  method="post" class="form-horizontal">
				<sys:message content="${message}"/>
				<div class="control-group">
					<label class="control-label" style="margin-left:100px;">请选择要新建的合同类型：</label>
					<div class="controls">
						<form:select id="typeDm" path="contractType.typeDm" class="input-large">
							<option value="00">请选择要新建的合同类型</option>
							<form:options items="${fns:getDictList('contract_type_dm')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<shiro:hasPermission name="cmag:newcontract:contractData:edit">
					<div class="form-actions">
						<a href="#" id="btn_create" class="btn btn-primary">创  建</a>
					</div>
				</shiro:hasPermission>
			</form:form>
		</div>
	</div>
 </div>
</body>
</html>