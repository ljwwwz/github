$.jgrid.defaults.width = 780;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';
var tabTypeId_map = {
	contractInfoID : "contractInfoId",
	enclosureInfoID : "enclosureInfoId",
	checkConInfoID : "checkConInfoID"
};
var url_map = {
	contractInfo_url : rootPath+"/cmag/conmanger/mag/form?",
	enclosureInfo_url : rootPath+"/cmag/conmanger/mag/getEnclosure?",
	whoCheck_url : rootPath+"/cmag/conmanger/mag/getWhoCheck?"
};
var grid = null;
$(function() {
    //导出
	$("#hr_export").click(
			function() {
				top.$.jBox.confirm("确认要导出合同数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action",rootPath+"/cmag/conmanger/mag/exportExcel");
						$("#searchForm").submit();
					}
				}, {buttonsFocus : 1});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
	});
	
	grid = $("#list").jqGrid(
					{
						url : rootPath+"/cmag/conmanger/mag/list1",
						contentType : 'application/json',
						mtype : "post",
						datatype : 'json',
						jsonReader : {
							repeatitems : false,
							userdata : "userdata"
						},
						height : "220",
						shrinkToFit : false,
						postData : {
							flag : flag,
							myCon : myCon,
							tabType:tabType,
							contract_no:contractNo
						},
						colNames : ["menus","contract_no","enclosure_id", "fileType","contract_type_dm", "合同名称","谁看过此合同", "附件","扫描件", "合同编号","归属地", "甲方", "乙方", "酷秀签约人", "开始日期", "截止日期","合同创建人","合同状态","查看流程图", "操作" ],
						colModel : [
								{	name : "menus",
									index : "menus",
									hidden : true,
									frozen:true
								},
								{	name : "contract_no",
									index : "contract_no",
									hidden : true,
									frozen:true
								},
								{
									name : "enclosure_id",
									index : "enclosure_id",
									hidden : true,
									frozen:true
								},
								{
									name : "fileType",
									index : "fileType",
									hidden : true,
									frozen:true
								},
								{
									name : "contract_type_dm",
									index : "contract_type_dm",
									hidden : true,
									frozen:true
								},
								{
									name : "contract_name",
									index : "contract_name",
									align : 'center',
								 	frozen: true,
									formatter : function(cellvalue, options,rawObject) {
										var contractName = "<span style='color:#3EAAE9;cursor:pointer;'><u>"+ cellvalue + "</u></span>";
										return contractName;
									}
								},
								{
									name : "whoCheck",  //谁看过此合同
									title : false,
									width : '100',
									align : 'center',
									formatter : function(cellvalue, options,rawObject) {
										var whoCheck = "<a href='javascript:void(0)' onclick='whoCheck(\""+ rawObject.contract_no+ "\")'><img src="+ ctxStatic+ "/images/question_mark_16.png width='16px' height='16px'></a>";
										return whoCheck;
									}
								},
								{
									name : "act1",  //附件
									title : false,
									width : '50',
									align : 'center',
									formatter : function(cellvalue, options,rawObject) {
										var enclosure = "<a href='javascript:void(0)' onclick='queryEnclosure(\""+ rawObject.enclosure_id+ "\")'><img src="+ ctxStatic+ "/images/attach.png width='16px' height='16px'></a>";
										return enclosure;
									}
								},
								{
									name : "act2", //扫描件
									title : false,
									align : 'center',
									width : '100',
									formatter : function(cellvalue, options,rawObject) {
										var str = getScan(rawObject.state_id,rawObject.fileType,rawObject.enclosure_id,rawObject.menus);
										return str;
									}
								},
								{
									name : "contract_no_name", //合同编号
									index : "contract_no_name",
									width : '350',
									align : 'center'
								},
								{
									name : "ss_area",   //归属地
									index : "ss_area",
									align : 'center',
									width : '100',
									formatter : function(cellvalue, options,rawObject) {
										var areaName = "";
										if (rawObject.ss_area == "01") {
											areaName = "华南地区"
										}
										if (rawObject.ss_area == "02") {
											areaName = "华东地区"
										}
										if (rawObject.ss_area == "03") {
											areaName = "华中地区"
										}
										if (rawObject.ss_area == "04") {
											areaName = "华北地区"
										}
										if (rawObject.ss_area == "05") {
											areaName = "西南地区"
										}
										if (rawObject.ss_area == "06") {
											areaName = "西北地区"
										} 
										if (rawObject.ss_area == "07") {
											areaName = "东北地区"
										}
										if (rawObject.ss_area == "08") {
											areaName = "台港澳地区"
										}
										return areaName;
									}
								},
								{  
									name : "firstPartyName",   //甲方
									index : "firstPartyName",
									width : '250',
									align : 'center'
								},
								{
									name : "secondPartyName",   //乙方
									index : "secondPartyName",
									width : '250',
									align : 'center'
								},
								{
									name : "qdrName",   //签订人姓名
									index : "qdrName",
									align : 'center'
								},
								{
									name : "begin_date",  //开始时间
									index : "begin_date",
									align : 'center'
								},
								{
									name : "end_date",   //结束时间
									index : "end_date",
									align : 'center'
								},
								{
									name : "createUser_name",  //创建者姓名
									index : "createUser_name",
									align : 'center'
								},
								{
									name : "state_id",      //状态
									index : "state_id",
									align : 'center',
									formatter : function(cellvalue, options,rawObject) {
										var stateId = "";
										if (rawObject.state_id == "01") {
											stateId = "待发送"
										}
										if (rawObject.state_id == "02") {
											stateId = "待初审"
										}
										if (rawObject.state_id == "03") {
											stateId = "初审通过"
										}
										if (rawObject.state_id == "04") {
											stateId = "初审不通过"
										}
										if (rawObject.state_id == "05") {
											stateId = "OA审批通过"
										}
										if (rawObject.state_id == "06") {
											stateId = "扫描件待确认"
										}
										if (rawObject.state_id == "07") {
											stateId = "扫描件确认不通过"
										}
										if (rawObject.state_id == "08") {
											stateId = "合同生效"
										}
										if (rawObject.state_id == "09"){
											stateId = "复制合同"
										}
										return stateId;
									}
								},
								{
									name : "flowchart",  //查看流程图
									title : false,
									align : 'center',
									width : '100',
									formatter : function(cellvalue, options,rawObject) {
										var flowchart = "<a style='cursor: pointer;' id='showFlowChart' data-contract-no="+rawObject.contract_no+"><img src="+ ctxStatic+ "/images/flowChart.png width='16px' height='16px'></a>";
										return flowchart;
									}
								},
								{
									name : "act",  //操作
									title : false,
									align : 'center',
									width : '300',
									formatter : function(cellvalue, options,rawObject) {
										return getOperString(rawObject.state_id,rawObject.fileType,rawObject.contract_no,rawObject.enclosure_id,rawObject.contract_type_dm,rawObject.menus,rawObject.contract_no_name);
									}
								} ],
						pager : "#pager",
						autoWidth : true,
						rowNum : 10,
						rowList : [ 10, 20, 30 ],
						rownumbers : true,
						viewrecords : true,
						loadonce : true,
						loadComplete : function(xhr) {
							var rowNum = parseInt($(this).getGridParam("records"), 10);
							if (rowNum <= 0) {
								top.$.jBox.tip("无数据！", "info");
							}
						},
						loadError : function(xhr, status, error) {
							top.$.jBox.prompt(error, "系统提示", "error");
						},
						onCellSelect : onCellSelect
	});
	
	//查看流程图
	$("#showFlowChart").live("click",function(){
		url = rootPath+"/cmag/conmanger/mag/showFlowChartPage?contract_no="+$(this).attr("data-contract-no");
		window.open(url, "_blank", "location=no,scrollbars=no,resizable=0,modal=false,alwaysRaised=yes,height=500,width=700");
	});
	
	//冻结列
	jQuery("#list").jqGrid("setFrozenColumns");
	//显示垂直滚动条
	$("#list").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll'});
	$(window).bind('resize', function() {
		$("#list").setGridWidth($("#_grid").width() - 30);
	}).trigger('resize');
	jQuery("#list").jqGrid('navGrid', '#pager', {edit : false,add : false,del : false,search : false,refresh : true,view : false,position : "left",cloneToTop : false,afterRefresh:afterRefresh});

});
//工具栏上的刷新操作		
function afterRefresh() {
	grid.jqGrid('setGridParam', {datatype : 'json',page : 1}).trigger("reloadGrid");
}

// 查看合同详细信息
function onCellSelect(rowid, index, contents, event) {
	var contractNo = grid.getRowData(rowid)['contract_no'];
	var contractTypeDm = grid.getRowData(rowid)['contract_type_dm'];
	if (index == '6') {
		var url_check = rootPath+"/cmag/conmanger/mag/checkContract";
		$.post(url_check, {
			"contract_no" : contractNo,
			"contract_type_dm" : contractTypeDm
		}, function(data) {
			if (data.status == "SUCCESS") {
				var url = url_map.contractInfo_url + "contract_no=" + contractNo + "&contract_type_dm=" + contractTypeDm + "&tabType=1";
				window.parent.callBySubPage({
					tabTypeId : tabTypeId_map.contractInfoID,
					operType : "viewContractRow",
					url : url
				});
			} else {
				top.$.jBox.tip(data.msg, "error");
			}
		}, "json");
	}
}
// 查看附件
function queryEnclosure(enclosureId) {
	var url = url_map.enclosureInfo_url + "enclosure_id=" + enclosureId+ "&tabType=2&flag=01";
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.enclosureInfoID,
		operType : "viewEnclosureRow",
		url : url
	});
}
//谁看过此合同
function whoCheck(contract_no){
	var url = url_map.whoCheck_url + "contract_no=" + contract_no + "&tabType=5"//&flag=02";
	window.parent.callBySubPage({
		tabTypeId:tabTypeId_map.checkConInfoID,
		operType:"whoCheck",
		url:url
	});
}
// 查看扫描件
function queryScanInfo(enclosureId){
	var url = url_map.enclosureInfo_url + "enclosure_id=" + enclosureId + "&tabType=5&flag=02";
	window.parent.callBySubPage({
		tabTypeId:tabTypeId_map.enclosureInfoID,
		operType:"viewScanRow",
		url:url
	});
}
// 编辑合同信息
function editContract(conNO, conTypeDm) {
	var tabType = "0";
	var url = url_map.contractInfo_url + "contract_no=" + conNO+ "&contract_type_dm=" + conTypeDm + "&tabType=" + tabType;
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractInfoID,
		operType : "editContractRow",
		url : url
	});
}
// 发送合同初审
function sendToVerify(conNO) {
	var url = rootPath+"/cmag/conmanger/mag/sendToVerify";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			contract_no : conNO
		},
		dataType : "json",
		beforeSend : function() {
			loading('正在发送初审，请稍等...');
		},
		success : function(data) {
			loading();
			if (data.status == "SUCCESS") {
				top.$.jBox.tip(data.msg, "info");
				window.parent.callBySubPage({
					tabTypeId : tabTypeId_map.contractInfoID,
					operType : "sendToVerify"
				});
			} else {
				top.$.jBox.tip(data.msg, "error");
			}
		},
		error : function(res) {
			top.$.jBox.prompt("发送初审发送错误！请重新发送！", "error");
		}
	});
}
// 合同初审 查看初审意见信息
function praeiudicium(conNO) {
	var url = rootPath+"/cmag/conmanger/mag/praeiudicium?contract_no=" + conNO;
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractInfoID,
		operType : "praeiudiciumRow",
		url : url
	});
}
// 上传扫描件
function uploadScan(conNo, conTypeDm) {
	var url = url_map.contractInfo_url + "contract_no=" + conNo+ "&contract_type_dm=" + conTypeDm + "&tabType=4";
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractInfoID,
		operType : "uploadScanRow",
		url : url
	});
}
// 扫描件确认
function scanConfrim(conNo, conTypeDm, enclId) {
	var url = url_map.contractInfo_url + "contract_no=" + conNo+ "&contract_type_dm=" + conTypeDm + "&enclosure_id=" + enclId+ "&tabType=3";
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractInfoID,
		operType : "scanConfrimRow",
		url : url
	});
}
// 复制
function copyCon(conNo, conTypeDm, enclId,conNoName) {
	var url = rootPath+"/cmag/conmanger/mag/copyContract";
	$.post(url, {
		"contract_no" : conNo,
		"contract_type_dm" : conTypeDm,
		"enclosure_id" : enclId,
		"contract_no_name" : conNoName
	}, function(data) {
		if (data.status == "SUCCESS") {
			grid.jqGrid('setGridParam', {datatype : 'json',page : 1}).trigger("reloadGrid");
			top.$.jBox.tip(data.msg, "info");
		} else {
			top.$.jBox.tip(data.msg, "error");
		}
	}, "json");
}
//扫描件信息
function getScan(conState,fjType,enclId,menus){
	var arr = new Array();
	arr = menus.split(",");
	var len = arr.length;
	var str = '';
	if (conState == "06" || conState == "07"){
		if($.inArray("查看扫描件", arr) >= 0){
			if (fjType == "01") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/Package-uncheck.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "02") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/picture-uncheck.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "03") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/pdf-uncheck.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "04") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/question_mark_16.png width="30px" height="30px"></a>&nbsp;';
			}
		}
	}
	if (conState == "08"){
		if($.inArray("查看扫描件", arr) >= 0){
			if (fjType == "01") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/Package-checked.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "02") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/picture-checked.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "03") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/pdf-checked.png width="30px" height="30px"></a>&nbsp;';
			}
			if (fjType == "04") {
				str += '<a href="javascript:void(0)" onclick="queryScanInfo(\''+ enclId+ '\')"><img src='+ ctxStatic+'/images/question_mark_16.png width="30px" height="30px"></a>&nbsp;';
			}
		}
	}
	if(conState != "06" && conState != "07" && conState != "08"){
		str += '未上传';
	}
	return str;
}
// 操作字符串拼接
function getOperString(conState, fjType, conNO, enclId, conTypeDm,menus,conNoName) {
	var arr = new Array();
	arr = menus.split(",");
	//alert($.inArray("编辑", arr));
	var len = arr.length;
	var str = '';
	if (conState == "01") {
		if($.inArray("编辑", arr) >= 0 || $.inArray("创建角色编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("发送初审", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="sendToVerify(\''+ conNO + '\')">发送初审</a>&nbsp';
		}
	}
	if (conState == "02") {
		if($.inArray("合同初审", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="praeiudicium(\'' + conNO + '\')">合同初审</a>&nbsp;';
		}
	}
	if (conState == "03") {
		if($.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("上传扫描件", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="uploadScan(\'' + conNO+ '\',\'' + conTypeDm + '\')">上传扫描件</a>&nbsp;';
		}
	}
	if (conState == "04") {
		if($.inArray("创建角色编辑", arr) >= 0 || $.inArray("初审角色编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO+ '\',\''+ conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("查看合同初审信息", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="praeiudicium(\''+ conNO+ '\')">查看初审意见信息</a>&nbsp;';
		}
		if($.inArray("发送初审", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="sendToVerify(\''+ conNO + '\')">发送初审</a>&nbsp;';
		}
	}
	if (conState == "05") {
		if($.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("上传扫描件", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="uploadScan(\'' + conNO+ '\',\'' + conTypeDm + '\')">上传扫描件</a>&nbsp;';
		}
	}
	if (conState == "06") {
		if($.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("扫描件确认", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="scanConfrim(\''+ conNO + '\',\'' + conTypeDm + '\',\'' + enclId + '\')">扫描件确认</a>&nbsp;';
		}
	}
	if (conState == "07") {
		if($.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
		if($.inArray("重新上传扫描件", arr) >= 0){
			str += '<a href="javascript:void(0)" onclick="uploadScan(\'' + conNO + '\',\'' + conTypeDm + '\')">重新上传扫描件</a>&nbsp;';
		}
	}
	if (conState == "08") {
		if($.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
	}
	if(conState == "09")  {
		if($.inArray("创建角色编辑", arr) >= 0 || $.inArray("初审角色编辑", arr) >= 0 || $.inArray("编辑", arr) >= 0){
			str +=  '<a href="javascript:void(0)" onclick="editContract(\''+ conNO + '\',\'' + conTypeDm + '\')">编辑</a>&nbsp;';
		}
	}
	if($.inArray("复制", arr) >= 0){
		str +=  '<a href="javascript:void(0)" onclick="copyCon(\'' + conNO+ '\',\'' + conTypeDm + '\',\'' + enclId + '\',\'' + conNoName + '\')">复制</a>';
	}
	return str;
}