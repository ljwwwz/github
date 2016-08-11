/**
 *	合同提醒信息 
 */

//合同列表树形回调函数：当改变值时
function remindcontractTreeselectCallBack(v, h, f){
	var contract_no = $("#remindcontractId").val().split("|");
	$("#contract_no_name").text(contract_no[0]);
	$("#contract_no").val(contract_no[1]);
	$("#contract_noName").val(contract_no[0]);
}
//表单参数检查
function formCheck() {
    var msg = "";
    
    return msg;
}
function check(obj) {
	obj.attr("action",rootPath+"/cmag/conwarn/mag/update");
    return true;
}
	