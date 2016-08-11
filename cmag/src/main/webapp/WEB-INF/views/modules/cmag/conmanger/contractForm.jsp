<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同详细信息页面</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/cmag_tab.jsp"%>
	<script type="text/javascript">
		var rootPath = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		var contractName = "${contract.contract_name}";
		var contractTypeDm = "${contract.contract_type_dm}";
		var tabType = "${tabType}";
		/* 保存合同归档信息 */
		function saveArc(){
			$("#inputForm2").attr("action","${ctx}/cmag/conmanger/mag/saveArcInfo");
			$("#inputForm2").submit();
		}
	</script>
	<script type="text/javascript" src="${ctxStatic}/common/cmag/mang/contractForm.js"></script>
	<script type="text/javascript" src="${ctxStatic}/city/area.js"></script>
	<style type="text/css">
		#disCss{
			margin-left:80px
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="contract" action="${ctx}/cmag/conmanger/mag/update" enctype="multipart/form-data" method="post" class="breadcrumb form-search" onsubmit="return check();">
		<input id="old_contract_no" name="old_contract_no" type="hidden">
		<input type="hidden" name="operatorType" id="operatorType">
		<input type="hidden" name="isUpload" id="isUpload"/>
		<input type="hidden" name="isFile" id="isFile"/>
		<input type="hidden" name="copyInfo" id="copyInfo"/>
		<form:hidden path="enclosure_id"/>
		<form:hidden path="create_time"/>
		<form:hidden path="create_user"/>
		<form:hidden path="modify_user"/>
		<form:hidden path="modify_time"/>
		<form:hidden path="state_id"/>
		<form:hidden path="user_id"/>
		<form:hidden path="contract_type_dm"/>
		<form:hidden path="contract_no"/>
		<form:hidden path="contract_no_name"/>
		<ul class="ul-form" style="height: 400px;" >
			<h4>合同基本信息</h4>
			<h5 style="float:right;margin-top:-25px;"><span class="help-inline"><font color="red" id="info"></font></span></h5>
			<br>
			<li>	
				<label>合同名称：</label> 
				<form:input path="contract_name"  maxlength="1400"/> 
				<span class="help-inline"><font color="red">*</font></span>
			</li>
			<li style="margin-left:80px">
				<label>归档合同编号：</label> 
				<form:input path="history_conNo" maxlength="40"/> 
			</li>
			<li class="clearfix"></li>
			<li>
				<label>甲方：</label>
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<sys:treeselect disabled="disabled" id="jfOffice" name="jfOffice.id" value="${contract.jfOffice.id}" labelName="jfOffice.name" labelValue="${contract.jfOffice.name}" 
									title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
				<c:if test="${tabType == 0 }">
					<sys:treeselect id="jfOffice" name="jfOffice.id" value="${contract.jfOffice.id}" labelName="jfOffice.name" labelValue="${contract.jfOffice.name}" 
									title="合同甲方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
			</li>
			<li style="margin-left:93px">
				<label>乙方：</label>
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<sys:treeselect disabled="disabled" id="yfOffice" name="yfOffice.id" value="${contract.yfOffice.id}" labelName="yfOffice.name" labelValue="${contract.yfOffice.name}" 
									title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
				<c:if test="${tabType == 0 }">
					<sys:treeselect id="yfOffice" name="yfOffice.id" value="${contract.yfOffice.id}" labelName="yfOffice.name" labelValue="${contract.yfOffice.name}" 
									title="合同乙方" url="/cmag/newcontract/contractData/companyTreeData" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>开始日期：</label> 
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<input value="${contract.begin_date}" class="input-large" type="text"  readonly="readonly"/>
				</c:if>
				<c:if test="${tabType == 0}">
					<form:input path="begin_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" />
				</c:if>
			</li>
			<li style="margin-left:90px">
				<label>结束日期：</label>
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<input value="${contract.end_date}"  class="input-large" type="text" readonly="readonly" />
				</c:if>
				<c:if test="${tabType == 0}">
					<form:input path="end_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" readonly="readonly" />
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>签订人：</label> 
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<form:input path="qdrName" class="input-large" type="text" readonly="readonly" />&nbsp;&nbsp;&nbsp;
				</c:if>
				<c:if test="${tabType == 0}">
					<sys:treeselect id="contract" name="qdr" value="${contract.qdr}" labelName="qdrName" labelValue="${contract.qdrName}" 
									title="签订人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true"  cssStyle="width:160px;"/>
					<span class="help-inline"> <font color="red">*</font></span>
				</c:if>
			</li>
			<li style="margin-left:80px">
				<label>签订日期：</label>
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<form:input path="qd_date"  class="input-large" type="text" readonly="readonly" />
				</c:if>
				<c:if test="${tabType == 0}">
					<form:input path="qd_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"   />
				</c:if>
			</li>
			<li class="clearfix"></li>
			<li>
				<label>所属省份：</label> 
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<sys:treeselect id="area" name="area.id" value="${contract.area.id}" labelName="area.name" labelValue="${contract.area.name}"  disabled="disabled"
							title="省份" url="/cmag/newcontract/contractData/areaTreeData" extId="${contract.area.id}" cssStyle="width:160px;" allowClear="true"/>
				</c:if>	
				<c:if test="${tabType == 0}">
					<li style="display:none;" id="area1">
						<sys:treeselect id="area" name="area.id" value="${contract.area.id}" labelName="area.name" labelValue="${contract.area.name}"
							title="省份" url="/cmag/newcontract/contractData/areaTreeData" extId="${contract.area.id}" cssStyle="width:160px;margin-left:5px" allowClear="true"/>
					</li>
					<li style="display:block;" id="area2">
						<input type="text" readonly="readonly" value="${contract.area.name}" class="input-large" style="margin-left:5px"/>
					</li>
				</c:if>	
			</li>
			<li>
				<label style="margin-left:105px">所属区域：</label>
				<form:select class="input-medium" path="ss_area" id="ss_area" style="width:220px;">
						<option value="00">请选择区域</option>
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
				<label>关联合同：</label>&nbsp;&nbsp;&nbsp;
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<sys:treeselect id="relationcontract" name="relationcontract.contract_no" value="${contract.relationcontract.contract_no}" labelName="relationcontract.name" labelValue="${contract.relationcontract.name}" 
								disabled="disabled" title="关联合同" url="/cmag/newcontract/contractData/contractTreeData" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
				<c:if test="${tabType == 0}">
					<sys:treeselect id="relationcontract" name="relationcontract.contract_no" value="${contract.relationcontract.contract_no}" labelName="relationcontract.name" labelValue="${contract.relationcontract.name}" 
									title="关联合同" url="/cmag/newcontract/contractData/contractTreeData" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
				</c:if>	
			</li>
			<li  style="margin-left:125px">
				<label style="width:90px;text-align:left;margin-left:0px;">履行跟踪人：</label> 
				<c:if test="${tabType == 1 or tabType == 4 or tabType == 3}">
					<sys:treeselect id="lxgUser" name="lxgUser.id" value="${contract.lxgUser.id}" labelName="lxgUser.name" labelValue="${contract.lxgUser.name}"  disabled="disabled" 
									title="合同跟踪人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
					<span class="help-inline"> <font color="red">*</font></span>
				</c:if>	
				<c:if test="${tabType == 0}">
					<sys:treeselect id="lxgUser" name="lxgUser.id" value="${contract.lxgUser.id}" labelName="lxgUser.name" labelValue="${contract.lxgUser.name}" 
								title="合同跟踪人" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked="true" cssStyle="width:160px;margin-left:-10px"/>
					<span class="help-inline"> <font color="red">*</font></span>
				</c:if>	
			</li>
			<li class="clearfix"></li>
			<br>
			<li>
				<label>邮寄地址：</label>
				<select class="required" id="s_province" name="s_province" style="width:90px;"></select>  
				<select class="required" id="s_city" name="s_city" style="width:150px;"></select>  
				<select class="required" id="s_county" name="s_county" style="width:150px;"></select>
				<form:input path="street_info" id="street_info"/>
			</li>
			<li class="clearfix"></li>
			<br>
			<li>
				<label>备注：</label> 
				<form:textarea rows="3" class="input-xxlarge" path="remarks" id="remark"/>
			</li>
			<c:if test="${tabType == 1}">
				<li>
					<label>附件：</label>
					<%-- <a href="${contract.enclosure.path}">${contract.enclosure.name}</a> --%>
					<a href="${ctx}/cmag/conmanger/mag/downloadFile?enclosure_id=${contract.enclosure_id}&flag=${contract.enclosure.flag}">${contract.enclosure.name}</a>
				</li>
			</c:if>
		</ul>
		<c:if test="${tabType == 0}">
			<div class="control-group">
				<label class="control-label" style="margin-left: 48px;">附件：</label>
				<input type="file" id="uploadFile" name="file" title="重新选择上传附件" onclick="javascript:$('#tmpFile').hide();$('#tmpFile').text('');"/>
				<span id="tmpFile" style="margin-left:-53px;">${contract.enclosure.name}</span>
				<span class="help-inline">只能上传 “doc”/“docx”和“pdf”格式的文档</span>
			</div>
		</c:if>
		<c:if test="${contract.contract_type_dm == 02 and tabType != 4}">
		<ul class="ul-form" style="height: 150px;">
			<br>
			<h4>酷秀收款账户信息</h4>
			<br>
			<li>
				<label>开户银行：</label>
				<form:input path="distributor.deposit_bank" maxlength="1500" />
			</li>
			<li id="disCss">
				<label>开户账号：</label>
				<form:input path="distributor.account_number" maxlength="1500" />
			</li>
			<li class="clearfix"></li>
			<li>
				<label>开户名称：</label>
				<form:input path="distributor.account_name"  maxlength="1500" />
			</li>		
			<li class="clearfix"></li>	
		</ul>
	</c:if>
		<c:if test="${contract.contract_type_dm == 03 and tabType != 4}">
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
		<c:if test="${contract.contract_type_dm == 02 and tabType != 4}">
			<ul class="ul-form" style="height: 280px;">
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
				<li style="margin-left:93px">
					<label>分销商账号：</label>
					<form:input path="distributor.distributor_account" class="required" maxlength="1500" />
					<span class="help-inline"> <font color="red">*</font></span>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>保证金：</label>
					<form:input path="distributor.bail" id="bail" class="required" maxlength="1500" onblur="check(this)"/>
					<span class="help-inline"> <font color="red">*</font></span>
				</li>
				<li id="disCss">
					<label>年度任务量：</label>
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
				<li style="margin-left:100px;">
					<label>联系电话：</label>
					<form:input path="distributor.phone"  maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<li>
					<label>协议类型：</label>
					<form:select id="typeDm" path="distributor.protocol_type" class="input-large">
						<option value="00">请选择协议类型</option>
						<form:options items="${fns:getDictList('distributor_contract_protocol_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
				<li style="margin-left:111px;">
					<label>预付款：</label>
					<form:input path="distributor.advance" maxlength="1500" onbulr="check(this)"/>
				</li>
				<li class="clearfix"></li>
			</ul>
		</c:if>
		<c:if test="${contract.contract_type_dm == 01 and tabType != 4}">
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
				</li>
				<li style="margin-left:80px">
					<label style="width:100px">供应景区名称：</label>
					<form:input path="supplier.scenicspot_name" class="required" maxlength="1500" style="width:205px"/>
					<span class="help-inline"><font color="red">*</font></span>
				</li>
				<li class="clearfix"></li>
				<li>
					<label>供应商账号：</label>
					<form:input path="supplier.supplier_account" maxlength="1500" />
				</li>
				<li id="disCss">
					<label>所属站点：</label>
					<form:input path="supplier.ss_site" maxlength="1500" />
				</li>
				<li class="clearfix"></li>
				<li>
					<label>供应商签约人：</label>
					<form:input path="supplier.supplier_contractor" maxlength="1500" style="width:205px"/>
				</li>
				<li>
					<label style="margin-left:92px">联系电话：</label>
					<form:input path="supplier.contractor_phone" maxlength="1500" style="width:205px"/>
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
				<li style="margin-left:90px">
					<label>开户银行：</label>
					<form:input path="supplier.deposit_bank" maxlength="1500" /><!-- new -->
				</li>
				<li class="clearfix"></li>
				<li id="">
					<label>开户账号：</label>
					<form:input path="supplier.account_number" maxlength="1500" />
				</li>
				<li>
					<label style="margin-left:98px">开户名称：</label>
					<form:input path="supplier.account_name" maxlength="1500" />
				</li>
				<li class="clearfix"></li>	
				<li>
					<label>供应商地址：</label>
					<form:input path="supplier.supplier_add" maxlength="1500" style="width:205px"/>
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
				<li id="disCss">
					<label  style="margin-left:32px">对接系统：</label>
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
					<!-- <span class="help-inline"><font color="red">*</font></span> -->
				</li>
				<li>
					<label style="margin-left:112px">押金状态：</label>
					<form:input path="supplier.bail_state"  maxlength="1500" onbulr="check(this)"/><!-- new -->
					<!-- <span class="help-inline"><font color="red">*</font></span> -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>提供发票类型：</label>
					<form:input path="supplier.billing_info"  maxlength="1500" />
					<!-- <span class="help-inline"><font color="red">*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></span> -->
				</li>
				<li id="dLiCss">
					<label  style="margin-left:112px">对账联系方式：</label>
					<form:input path="supplier.phone"  maxlength="1500" />
					<!-- <span class="help-inline"><font color="red">*</font></span> -->
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
						<form:input path="supplier.verify_date" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width:205px" id="verify_date" /><!-- new -->
						<span class="help-inline">若存在返佣信息，请选择核算时间</span>
				</li>
				<li class="clearfix"></li>
			</ul> 
		</c:if>
		<c:if test="${tabType == 4}">
			<div class="control-group">
				<label class="control-label" style="margin-left: 50px;">上传扫描件资料：</label>
				<input type="text" size="420" name="upfile" id="upfile" style="border:1px dotted #ccc;width:420px;" readonly="readonly">
				<input type="button" value="选择扫描件" onclick="uploadFile.click()" class="btn btn-primary" style="border:1px solid #ccc;">  
				<input type="file" id="uploadFile" name="file" style="display:none" onchange="upfile.value=this.value" accept="imag/jpg,image/jpeg,image/png,application/pdf,aplication/zip,application/msword">
				<span class="help-inline">只能上传1.jpg、jpeg、png 2.pdf 3.zip rar tar 格式的文件</span>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasAnyPermissions name="cmag:conmanger:mag:edit,cmag:conmanger:mag:conEdit,cmag:conmanger:mag:verifyEdit">
				<c:if test="${tabType == 0}">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;
				</c:if>
				<c:if test="${tabType == 3}">
					<input type="hidden" id="czFlag" name="czFlag">
					<input id="okSubmit" class="btn btn-primary" type="submit" value="扫描件确认通过" onclick="javascript:$('#czFlag').val('Y')"/>&nbsp;&nbsp;
					<input id="noSubmit" class="btn btn-primary" type="submit" value="扫描件确认不通过" onclick="javascript:$('#czFlag').val('N')"/>
				</c:if>
				<c:if test="${tabType == 4}">
					<input id="btnSubmit" class="btn btn-primary" type="submit"  onclick="saveScaning();" value="保存扫描件"/>&nbsp;
				</c:if>
			</shiro:hasAnyPermissions>
			<c:if test="${count le 1}">
				<a id="btnSerach" class="btn btn-primary" href="#"><i class="icon-search icon-white"></i>查询关联合同信息</a>
			</c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="btnCancel" class="btn" href="#">返回</a>
		</div>
	</form:form>
	<c:if test="${warnFlag != 0}">
	<form:form id="inputForm2" modelAttribute="contract" method="post" class="breadcrumb form-search">
		<form:hidden path="contract_no"/>
		<shiro:hasPermission name="cmag:conmanger:mag:archivedInfo">
			<ul class="ul-form" style="height: 200px;">
				<br>
				<h4>合同归档信息</h4>
				<br>
				<li>
					<label>合同保管人：</label>
					<form:input path="custodian" class="arcInfo" maxlength="1500" /><!-- new -->
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>
				<li id="disCss">
					<label>签收时间：</label>
					<form:input path="receipt_time" class="input-large Wdate" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width:205px" id="arcInfo"/><!-- new -->
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>原件：</label>
					<form:select id="arcInfoYj" path="original_file" style="width:220px;" >
						<option value="0">请选择</option>
						<form:option value="1">是</form:option>
						<form:option value="2">否</form:option>
					</form:select>
				</li>		
				<li id="disCss">
					<label>份数：</label>
					<form:input path="copies" class="arcInfo" maxlength="1500" /><!-- new -->
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>
				<li class="clearfix"></li>
				<li>
					<label>原编码：</label>
					<form:input path="original_code" class="arcInfo" maxlength="1500" /><!-- new -->
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>
				<li id="disCss">
					<label>扫描版：</label>
					<form:input path="scan_version" class="arcInfo" maxlength="1500" /><!-- new -->
					<!-- <span class="help-inline"> <font color="red">*</font></span> -->
				</li>		
			</ul>
			<c:if test="${tabType == 1}">
				<span><input id="btnSubmit" class="btn btn-primary" type="button" value="保存合同归档信息" style="margin-left:50px" onclick="saveArc();"/></span>		
			</c:if>
		</shiro:hasPermission>
	</form:form>
	</c:if>
</body>
</html>