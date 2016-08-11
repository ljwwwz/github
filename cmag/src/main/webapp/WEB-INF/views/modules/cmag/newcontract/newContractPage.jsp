<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<!-- <img width="30" style="float: right" src="${ctxStatic}/images/add.jpg" title="增加一项"> --><%-- 
<img class="deleteImg" width="23" style="cursor: pointer;" src="${ctxStatic}/images/delete.png" title="减少一项"> --%>
	<title>合同拟定页面</title>
	<meta name="decorator" content="default" />
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		var contract_no = "${contract.contract_no}";
		var contractTypeName = "${contract.contractType.typeName}";
		var contractTypeDm = "${contract.contractType.typeDm}";
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/newCon/newContract.js"></script>
	<script type="text/javascript" src="${ctxStatic}/city/area.js"></script>
	<style type="text/css">
		#liCss{
			margin-left: 80px;
		}
		#dLiCss{
			margin-left:100px;
		}
		.addSign{
			line-height: 40px;
			font-weight: bold;
			width:200px;
			text-align:center;
			display:inline-block;
			font-size: 30px;
			cursor: pointer;
		}
		.clean{
			clear: both;
		}
		.ul-form2{
			padding-bottom: 30px;
		}
		.firstParty,.secondParty{
			float:left;
			width:370px;
			margin-left: 56px;
		}
		.partys{
			float:left;
			width:429px;
		}
		.firstPartysName ,.secondPartysName{
			margin-left:56px;
			font-family: 微软雅黑;
			font-size: 18px;
			font-weight: bold;
			line-height: 30px;
		}
	</style>
</head>
<body>
	<sys:message content="${message}" />
	<form:form id="contractForm" modelAttribute="contract" method="post" enctype="multipart/form-data" class="breadcrumb form-search">
		<input id="typeDm" name="typeDm" type="hidden" value="${contract.contractType.typeDm}"/>
		<input id="btnFlag" name="btnFlag" type="hidden">
		<ul class="ul-form ul-form2" >
			<h4>合同基本信息</h4>
			<br>
			<li>
				<label>合同名称：</label> 
				<form:input path="contract_name" class="required" maxlength="1400" /> 
				<span class="help-inline"><font color="red">*</font></span>
			</li>
			<li id="liCss">
				<label>归档合同编号：</label> 
				<form:input path="history_conNo" maxlength="40"/> 
			</li>
			<li class="clearfix"></li>
			<li>
				<label>开始日期：</label> 
				<c:if test="${not empty contract.contract_no}">
					<input value="${contract.begin_date}" class="input-large" type="text"  readonly="readonly" style="width:205px"/>
				</c:if>
				<c:if test="${empty contract.contract_no}">
					<form:input path="begin_date" id="begin_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" cssStyle="width:205px" />
				</c:if>
			</li>
			<li>
				<label style="margin-left:106px">结束日期：</label>
				<c:if test="${not empty contract.contract_no}">
					<input value="${contract.end_date}"  class="input-large" type="text" readonly="readonly" style="width:205px"/>
				</c:if>
				<c:if test="${empty contract.contract_no}">
					<form:input path="end_date" id="end_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" cssStyle="width:205px"/>
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>签订人：</label> 
				<c:if test="${not empty contract.contract_no}">
					<sys:treeselect disabled="disabled" id="qdrUser" name="qdrUser.id" value="${qdrUser.id}" labelName="qdrUser.name" labelValue="${qdrUser.name}" 
									title="签订人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
				<c:if test="${empty contract.contract_no}">
					<sys:treeselect id="qdrUser" name="qdrUser.id" value="${qdrUser.id}" labelName="qdrUser.name" labelValue="${qdrUser.name}" 
									title="签订人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
			</li>
			<li>
				<label style="margin-left:106px">签订日期：</label>
				<c:if test="${not empty contract.contract_no}">
					<input value="${contract.qd_date}" class="input-large" type="text" readonly="readonly" style="width:205px"/>
				</c:if>
				<c:if test="${empty contract.contract_no}">
					<form:input path="qd_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" cssStyle="width:205px" />
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>所属省份：</label> 
				<c:if test="${not empty contract.contract_no}">
					<sys:treeselect id="area" name="area.id" value="${area.id}" labelName="area.name" labelValue="${area.name}"  disabled="disabled"
							title="省份" url="/cmag/newcontract/contractData/areaTreeData" extId="${area.id}" cssStyle="width:160px;" allowClear="true"/>
				</c:if>	
				<c:if test="${empty contract.contract_no}">
					<sys:treeselect id="area" name="area.id" value="${area.id}" labelName="area.name" labelValue="${area.name}"
							title="省份" url="/cmag/newcontract/contractData/areaTreeData" extId="${area.id}" cssStyle="width:160px;" allowClear="true"/>
				</c:if>	
			</li>
			<li style="margin-left:95px">
				<label>所属区域：</label>
				<form:select class="input-medium" path="ss_area" id="ss_area" style="width:220px;">
						<option value="00">请选择区域</option>
						<form:option value="01">华南地区</form:option>
						<form:option value="02">华东地区</form:option>
						<form:option value="03">华中地区</form:option>
						<form:option value="04">华北地区</form:option>
						<form:option value="05">西南地区</form:option>
						<form:option value="06">西北地区</form:option>
						<form:option value="07">东北地区</form:option>
						<form:option value="08">台港澳地区</form:option>
				</form:select>
			</li>
			<li class="clearfix"></li>
			<li style="margin-left:30px">
				<label style="width:90px;text-align:left;margin-left:0px;">履行跟踪人：</label> 
				<c:if test="${not empty contract.contract_no}">
					<sys:treeselect id="lxgUser" name="lxgUser.id" value="${lxgUser.id}" labelName="lxgUser.name" labelValue="${lxgUser.name}"  disabled="disabled" 
									title="合同跟踪人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
				<c:if test="${empty contract.contract_no}">
					<sys:treeselect id="lxgUser" name="lxgUser.id" value="${lxgUser.id}" labelName="lxgUser.name" labelValue="${lxgUser.name}" 
								title="合同跟踪人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
			</li>	
			<li style="margin-left:95px">
				<label>关联合同：</label>&nbsp;&nbsp;&nbsp;
				<c:if test="${not empty contract.contract_no}">
					<sys:treeselect id="relationcontract" name="relationcontract.contract_no" value="${relationcontract.contract_no}" labelName="relationcontract.name" labelValue="${relationcontract.name}" 
								disabled="disabled" title="关联合同" url="/cmag/newcontract/contractData/contractTreeData" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
				<c:if test="${empty contract.contract_no}">
					<sys:treeselect id="relationcontract" name="relationcontract.contract_no" value="${relationcontract.contract_no}" labelName="relationcontract.name" labelValue="${relationcontract.name}" 
									title="关联合同" url="/cmag/newcontract/contractData/contractTreeData" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
			</li>
			<li class="clearfix"></li>
			<br>
			<li>
				<label>邮寄地址：</label> <!-- street_info -->
				<select class="required" id="s_province" name="s_province" style="width:90px;"></select>  
				<select class="required" id="s_city" name="s_city" style="width:150px;"></select>  
				<select class="required" id="s_county" name="s_county" style="width:150px;"></select>
				<form:input path="street_info" id="street_info"/>
			</li>
			<li class="clearfix"></li>
			<br/>
			<li>
				<label>备注：</label> 
				<form:textarea rows="3" class="input-xxlarge" path="remarks" id="remark"/>
			</li>
			<li class="clearfix"></li>
			<li class="clean"></li>
		</ul>
		<div class="control-group">
			<label class="control-label" style="margin-left: 48px;">附件：</label>
			<c:if test="${not empty contract.contract_no}">
					<input id="fj" type="text" htmlEscape="false" maxlength="420" class="input-xlarge"  value="${fileName}"/>
				</c:if>	
				<c:if test="${empty contract.contract_no}">
					<!-- <input type="file" id="uploadFile" name="file"/> -->
					<input type="text" size="420" name="upfile" id="upfile" style="border:1px dotted #ccc;width:420px;" readonly="readonly">  
					<input type="button" value="选择附件" onclick="uploadFile.click()" class="btn btn-primary" style="border:1px solid #ccc;">  
					<input type="file" id="uploadFile" name="file" style="display:none" onchange="upfile.value=this.value" accept="application/msword,application/pdf">
					<span class="help-inline">只能上传 “doc”/“docx”和“pdf”格式的文档</span>
				</c:if>	
		</div>
		<div>
			<div class="partys">
				<div class="firstParty">
					<label>甲方：</label>
					<c:if test="${not empty contract.contract_no}">
						<sys:treeselect checked="true" disabled="disabled" id="jfOffice" name="jfOffice.id" value="${jfOffice.id}" labelName="jfOffice.name" labelValue="${jfOffice.name}" 
										title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="false" notAllowSelectParent="true"  cssStyle="width:160px;"/>
						<span class="help-inline"> <font color="red">*</font></span>
					</c:if>
					<c:if test="${empty contract.contract_no}">
						<sys:treeselect  checked="true" id="jfOffice" name="jfOffice.id" value="${jfOffice.id}" labelName="jfOffice.name" labelValue="${jfOffice.name}" 
										title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
						<span class="help-inline"> <font color="red">*</font></span>
					</c:if>
					<%-- <img class="addFirstPartyImg" width="30" style="cursor: pointer;" src="${ctxStatic}/images/add.jpg" title="增加一项"> --%>
				</div>
				<div class="firstPartysName">
					已选:
				</div>
			</div>
			<div class="partys">
				<div class="secondParty">
					<label style="margin-left:30px">乙方：</label>
					<c:if test="${not empty contract.contract_no}">
						<sys:treeselect disabled="disabled" id="yfOffice" name="yfOffice.id" value="${yfOffice.id}" labelName="yfOffice.name" labelValue="${yfOffice.name}" 
										title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="false" notAllowSelectParent="true"  cssStyle="width:160px;"/>
						<span class="help-inline"> <font color="red">*</font></span>
					</c:if>
					<c:if test="${empty contract.contract_no}">
						<sys:treeselect id="yfOffice" name="yfOffice.id" value="${yfOffice.id}" labelName="yfOffice.name" labelValue="${yfOffice.name}" 
										title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
						<span class="help-inline"> <font color="red">*</font></span>
					</c:if>
					<%-- <img class="addSecondPartyImg" width="30" style="cursor: pointer;" src="${ctxStatic}/images/add.jpg" title="增加一项"> --%>
				</div>
				<div class="secondPartysName">
					已选:
				</div>
			</div>
			<div class="clean"></div>
		</div>
		<div class="clean"></div>
	<c:if test="${contract.contractType.typeDm == 02}">
		<ul class="ul-form coolshowReceive">
			<br/>
			<h4>
				酷秀收款账户信息
				<!-- <span title="点击展开" class="addSign">
				+
				</span> -->
			</h4>
			<br/>
			<li>
				<label>开户银行：</label>
				<form:input path="distributor.deposit_bank"  maxlength="1500" />
			</li>
			<li style="margin-left:115px;">
				<label>开户账号：</label>
				<form:input path="distributor.account_number" maxlength="1500" />
			</li>
			<li class="clearfix"></li>
			<li>
				<label>开户名称：</label>
				<form:input path="distributor.account_name" maxlength="1500" />
			</li>		
			<li class="clearfix"></li>	
		</ul>
	</c:if>
	<c:if test="${contract.contractType.typeDm == 03}">
		<ul class="ul-form" style="height: 200px;">
			<br>
			<h4>其他信息</h4>
			<br>
			<li>
				<label>Name：</label>
				<form:input path="other.name" class="required" maxlength="1500" />
			</li>
			<li>
				<label>Code：</label>
				<form:input path="other.code" class="required" maxlength="1500" />
			</li>					
		</ul>
	</c:if>
	<c:if test="${contract.contractType.typeDm == 02}">
			<ul class="ul-form" class="distributorInfo" style="height: 280px;">
				<br>
				<h4>分销商基本信息</h4>
				<br>
				<li>
					<label>分销商级别：</label> 			
					<form:select class="input-medium" path="distributor.level" style="width:220px;" id="level">
						<option value="00">请选择级别</option>
						<form:option value="01">一级</form:option>
						<form:option value="02">二级</form:option>
					</form:select>
				</li>
				<li>
					<label style="margin-left:121px">分销商账号：</label>
					<form:input path="distributor.distributor_account"  maxlength="1500" />
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>保证金：</label>
					<form:input path="distributor.bail" id="bail" class="required" maxlength="1500" onblur="check(this)"/>
					<span class="help-inline"> <font color="red">*</font></span>
				</li>	
				<li>
					<label style="margin-left:108px">年度任务量：</label>
					<form:input path="distributor.year_quantity" class="required" maxlength="1500" onblur="check(this)"/>
					<span class="help-inline"><font color="red">*</font></span>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>结算方式：</label>
					<form:select class="input-medium" path="distributor.settlement_way" style="width:220px;" id="settlement_way">
						<option value="00">请选择结算方式</option>
						<form:option value="01">无</form:option>
						<form:option value="02">按单结算</form:option>
					</form:select>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>分销商签约人：</label>
					<form:input path="distributor.distributor_contractor" maxlength="1500" style="width:205px"/>
				</li>
				<li style="margin-left:115px;">
					<label>联系电话：</label>
					<form:input path="distributor.phone" maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<li>
					<label>协议类型：</label>
					<form:select id="typeDm" path="distributor.protocol_type" style="width:220px;" >
						<option value="00">请选择协议类型</option>
						<form:options items="${fns:getDictList('distributor_contract_protocol_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li style="margin-left:116px;">
					<label>预付款：</label>
					<form:input path="distributor.advance" maxlength="1500" onbulr="check(this)"/>
				</li>
				<li class="clearfix"></li>
			</ul>
	</c:if>
	<c:if test="${contract.contractType.typeDm == 01}">
			<ul class="ul-form" style="height: 680px;">
				<br>
				<h4>供应商基本信息</h4>
				<br>
				<li>
					<label>供应商类型：</label> 			
					<form:select class="input-medium" path="supplier.supplier_type" style="width:220px;" id="supplier_type">
						<option value="00">请选择类型</option>
						<form:option value="1">OTA平台</form:option>
						<form:option value="2">景区</form:option>
						<form:option value="3">旅行社</form:option>
						<form:option value="4">代理商</form:option>
						<form:option value="5">城市代理商</form:option>
						<form:option value="6">其他</form:option>
					</form:select>
					<span class="help-inline"><font color="red">*</font></span>
				</li>
				<li id="dLiCss">
					<label style="width:100px">供应景区名称：</label>
					<form:input path="supplier.scenicspot_name" class="required" maxlength="1500" style="width:205px"/>
					<span class="help-inline"><font color="red">*</font></span>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>供应商账号：</label>
					<form:input path="supplier.supplier_account" maxlength="1500" />
				</li>
				<li style="margin-left:114px">
					<label>所属站点：</label>
					<form:input path="supplier.ss_site"  maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<li>
					<label>供应商签约人：</label>
					<form:input path="supplier.supplier_contractor" class="required" maxlength="1500" style="width:205px"/>
					<span class="help-inline"><font color="red">*</font></span>
				</li>
				<li>
					<label style="margin-left:112px">联系电话：</label>
					<form:input path="supplier.contractor_phone" maxlength="1500" style="width:205px"/>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>供应商地址：</label>
					<form:input path="supplier.supplier_add" maxlength="1500" style="width:205px"/>
				</li>
				<li class="clearfix"></li>
				<br>
				<h5 style="margin-left: 30px">供应商收款账户信息</h5>
				<br>
				<li>
					<label>账户类型：</label>
					<form:select class="input-medium" path="supplier.account_type" style="width:220px;" id=""><!-- new -->
						<option value="00">请选择</option>
						<form:option value="01">对公</form:option>
						<form:option value="02">对私</form:option>
					</form:select>
				</li>
				<li>
					<label style="margin-left:112px">开户银行：</label>
					<form:input path="supplier.deposit_bank" maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<li>
					<label>开户账号：</label>
					<form:input path="supplier.account_number" maxlength="1500" />
				</li>
				<li>
					<label style="margin-left:112px">开户名称：</label>
					<form:input path="supplier.account_name" maxlength="1500" />
				</li>
				<li class="clearfix"></li>	
				<br>
				<h5 style="margin-left: 20px">付款核对结算信息</h5>
				<br>
				<li>
					<label>付款方式：</label>
					<form:input path="supplier.payment_info"  maxlength="1500" />
					<!-- <span class="help-inline"><font color="red">*</font></span> -->
				</li>
				<li id="dLiCss">
					<label>对接系统：</label>
					<form:input path="supplier.use_system"  maxlength="1500" />
					<!-- <span class="help-inline"><font color="red">*</font></span> -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>对账紧急程度：</label>
					<form:select class="input-medium" path="supplier.emergency_level" style="width:220px;" id=""><!-- new -->
						<option value="00">请选择</option>
						<form:option value="01">非常紧急</form:option>
						<form:option value="02">紧急</form:option>
						<form:option value="03">不紧急</form:option>
						</form:select>
				</li>
				<li>
					<label style="margin-left:112px">确认对账单：</label>
					<form:select class="input-medium" path="supplier.confirm_statement" style="width:220px;" id=""><!-- new -->
						<option value="00">请选择</option>
						<form:option value="01">是</form:option>
						<form:option value="02">否</form:option>
						</form:select>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>纳税人规模：</label>
					<form:select class="input-medium" path="supplier.taxpayer_scale" style="width:220px;" id=""><!-- new -->
						<option value="00">请选择</option>
						<form:option value="01">小规模纳税人</form:option>
						<form:option value="02">一般纳税人</form:option>
						</form:select>
				</li>
				<li>
					<label style="margin-left:112px">税率：</label>
					<form:input path="supplier.tariff" class="required" maxlength="1500" style="width:205px"/><!-- new -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>押金信息：</label>
					<form:input path="supplier.bail"  maxlength="1500" onbulr="check(this)"/>
				</li>
				<li>
					<label style="margin-left:112px">押金状态：</label>
					<form:input path="supplier.bail_state"  maxlength="1500" onbulr="check(this)"/>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>提供发票类型：</label>
					<form:input path="supplier.billing_info"  maxlength="1500" />
				</li>
				<li id="dLiCss">
					<label>对账联系方式：</label>
					<form:input path="supplier.phone"  maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<br>
				<h5 style="margin-left: 30px">返佣信息</h5>
				<br>
				<li>
				<label>返佣/奖励政策：</label>
					<form:select class="input-medium" path="supplier.rebate" style="width:220px;" onchange="checkRebate(this)" id="rebate">
						<option value="00">请选择</option>
						<form:option value="01">有</form:option>
						<form:option value="02">无</form:option>
					</form:select>
				<li>
					<label style="margin-left: 110px">核算时间：</label>
						<form:input path="supplier.verify_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width:205px" id="verify_date"/><!-- new -->
						<span class="help-inline">若存在返佣信息，请选择核算时间</span>
				</li>
				<li class="clearfix"></li>
			</ul>
	</c:if>
	<div class="form-actions">
		<shiro:hasPermission name="cmag:newcontract:contractData:edit">
			<c:if test="${empty contract.contract_no}">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;&nbsp;
				<shiro:hasPermission name="cmag:newcontract:contractData:saveToVerify">
				<input id="saveSubmit" class="btn btn-primary" type="submit" value="保 存并发送初审" />&nbsp;&nbsp;
				</shiro:hasPermission>
			</c:if>
		</shiro:hasPermission>
		<!-- <input id="btnCancel" class="btn" type="button" value="返 回"/> -->
	</div>
	</form:form>
</body>
</html>