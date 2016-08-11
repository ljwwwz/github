$.jgrid.defaults.width = 780;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';
var tabTypeId_map = {
	contractInfoID : "contractInfoId",
	contractWarnInfoID : "contractWarnInfoId"
};
var url_map = {
	contractInfo_url : rootPath+"/cmag/conmanger/mag/form?",
	contracWarnInfo_url: rootPath+"/cmag/conwarn/mag/form?"
};
var grid = null;
$(function() {
	grid = $("#list").jqGrid(
					{
						url : rootPath+"/cmag/conwarn/mag/list1",
						contentType : 'application/json',
						mtype : "post",
						datatype : 'json',
						jsonReader : {
							repeatitems : false,
							userdata : "userdata"
						},
						height : "200",
						shrinkToFit : false,
						postData : {
						},
						colNames : [ "warn_id","menus","contract_no","contract_type_dm","enclosure_id","合同名称","warnPersonFlag","合同编号","甲方", "乙方", "合同签订时间", "状态","提醒时间","提醒人","remind_contentAll","提醒内容","操作" ],
						colModel : [
								{name : "warn_id",index : "warn_id",hidden : true,frozen:true},
								{name : "menus",index : "menus",hidden : true,frozen:true},
								{name : "contract_no",index : "contract_no",hidden : true,frozen:true},
								{name : "contract_type_dm",index : "contract_type_dm",hidden : true,frozen:true},
								{name : "enclosure_id",index : "enclosure_id",hidden : true,frozen:true},
								{name : "contract_name",index : "contract_name",align : 'center',frozen:true,
									formatter : function(cellvalue, options,rawObject) {
										var contractName = "<span style='color:#3EAAE9;cursor:pointer;'>"+ cellvalue + "</span>";
										return contractName;
									}
								},
								{name : "warnPersonFlag",index : "warnPersonFlag",hidden : true},
								{name : "contract_no_name",index : "contract_no_name",width : '250',align:'center'},
								{name : "firstPartyName",index : "firstPartyName",width : '250',align:'center'},
								{name : "secondPartyName",index : "secondPartyName",width : '250',align:'center'},
								{name : "qd_date",index : "qd_date",align : 'center'},
								{name : "remind_state",index : "remind_state",align : 'center',
									formatter : function(cellvalue, options,rawObject) {
										var stateId = "";
										if (rawObject.remind_state == "01") {
											stateId = "未提醒";
										}
										if (rawObject.remind_state == "02") {
											stateId = "提醒中";
										}	
										if (rawObject.remind_state == "03") {
											stateId = "已忽略";
										}
										return stateId;
									}
								},
								{name : "remind_time",index : "remind_time",align : 'center'},
								{name : "remind_name",index : "remind_name",width : '70',align : 'center'},
								{name : "remind_contentAll",index : "remind_contentAll",hidden : true},
								{name : "remind_content",index : "remind_content",width : '250',align : 'center',
									formatter:function(cellvalue, options,rawObject){
										var str = "";
										if(cellvalue.length>3){
											  str = cellvalue.substring(0,3)+"...";
										}else {
											str = cellvalue;
										}
										return "<span style='color:#3EAAE9;cursor:pointer;'><u>"+ str + "</u></span>";
									}
								},
								{name : "act",title : false,align : 'center',
									formatter : function(cellvalue, options,rawObject) {
										return getOperString(rawObject.warn_id,rawObject.remind_state,rawObject.contract_no,rawObject.contract_type_dm,rawObject.contract_no_name,rawObject.menus,rawObject.warnPersonFlag);
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
						gridComplete:function(){
				             var ids = $("#list").getDataIDs();
				             for(var i=0;i<ids.length;i++){
				                 var rowData = $("#list").getRowData(ids[i]);
				                 if(rowData.remind_state == "提醒中"){
				                     $('#'+ids[i]).find("td").css("background-color","#808080");
				                 }
				             }
				             return true;
				         },
						onCellSelect : onCellSelect
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

//查看合同详细信息
function onCellSelect(rowid, index, contents, event) {
	var contractNo = grid.getRowData(rowid)['contract_no'];
	var contractTypeDm = grid.getRowData(rowid)['contract_type_dm'];
	var warn_content = grid.getRowData(rowid)['remind_contentAll'];
	if (index == '6') {
		var url_check = rootPath+"/cmag/conmanger/mag/checkContract";
		$.post(url_check, {
			"contract_no" : contractNo,
			"contract_type_dm" : contractTypeDm
		}, function(data) {
			if (data.status == "SUCCESS") {
				var url = url_map.contractInfo_url + "contract_no=" + contractNo + "&contract_type_dm=" + contractTypeDm + "&tabType=1&warnFlag=0";
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
	if(index == "16"){
		top.$.jBox.prompt(warn_content, "合同提醒详细内容");
	}
}
//编辑
function editContract(id,contract_no) {
	var tabType = "0";
	var url = url_map.contracWarnInfo_url + "id=" + id + "&contract_no=" + contract_no + "&tabType=" + tabType;
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractWarnInfoID,
		operType : "editContractWarnRow",
		url : url
	});
}
//详情
function viewConWarnInfo(id,contract_no) {
	var tabType = "1";
	var url = url_map.contracWarnInfo_url + "id=" + id + "&contract_no=" + contract_no + "&tabType=" + tabType;
	window.parent.callBySubPage({
		tabTypeId : tabTypeId_map.contractWarnInfoID,
		operType : "viewContractWarnInfoRow",
		url : url
	});
}
//删除
function delWarn(id) {
	var url = rootPath+"/cmag/conwarn/mag/delWarn";
	$.post(url, {
		"id" : id
	}, function(data) {
		if (data.status == "SUCCESS") {
			top.$.jBox.tip(data.msg, "info");
			$("#list").jqGrid('setGridParam', {
				datatype : 'json',
				page : 1
			}).trigger("reloadGrid");
		} else {
			top.$.jBox.tip(data.msg, "error");
		}
	}, "json");
}
// 操作字符串拼接
function getOperString(id,remind_state,contract_no,contract_type_dm,contract_no_name,menus,warnPersonFlag) {
	var arr = new Array();
	arr = menus.split(",");
	var str = '<a href="javascript:void(0)" onclick="viewConWarnInfo(\''+ id + '\',\'' + contract_no + '\')">详情</a>&nbsp;';
	if (warnPersonFlag != "Y") {    //当前登录的是被提醒人，不显示编辑和删除
		if (remind_state == "01") {
			if($.inArray("编辑", arr) >= 0){
				str += '<a href="javascript:void(0)" onclick="editContract(\''+ id + '\',\'' + contract_no + '\')">编辑</a>&nbsp';
			}
			if($.inArray("删除", arr) >= 0){
				str += '<a href="javascript:void(0)" onclick="delWarn(\''+ id + '\')">删除</a>&nbsp';
			}
		}
	}
	return str;
}