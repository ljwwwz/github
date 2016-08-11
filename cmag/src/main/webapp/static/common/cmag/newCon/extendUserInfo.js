/**
 * 个人信息
 */
var url = rootPath + "/cmag/conmanger/mag/showMainPage?myCon=Y";
$(function() {
	// 未上传扫描件
	$("#noUploadScan").on("click", function() {
		// 在顶层添加一个标签页
		var tmp_url = url + "&flag=00";
		addTabPage("未上传扫描件列表", tmp_url, undefined, $(this), true);
	});
	// 待发送初审
	$("#noSendVerify").on("click", function() {
		// 在顶层添加一个标签页
		var tmp_url = url + "&flag=01";
		addTabPage("待发送初审列表", tmp_url, undefined, $(this), true);
	});
	// 初审中
	$("#praeiudicium").on("click", function() {
		// 在顶层添加一个标签页
		var tmp_url = url + "&flag=02";
		addTabPage("初审中列表", tmp_url, undefined, $(this), true);
	});
	// 合同生效中
	$("#scanConfrim").on("click", function() {
		// 在顶层添加一个标签页
		var tmp_url = url + "&flag=08";
		addTabPage("生效合同列表", tmp_url, undefined, $(this), true);
	});
	// 扫描件待确认
	$("#noConfirmScan").on("click", function() {
		// 在顶层添加一个标签页
		var tmp_url = url + "&flag=06";
		addTabPage("待确认扫描件列表", tmp_url, undefined, $(this), true);
	});
});