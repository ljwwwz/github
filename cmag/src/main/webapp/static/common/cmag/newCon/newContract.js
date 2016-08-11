/**
 * 新建合同信息
 */
$(function() {
	/*//初始化时候甲方节点个数
	var firstPartyNum = 1;
	//初始化时候乙方节点个数
	var secondPartyNum = 1;
	//展开或者关闭所用时间(ms)
	var time = 300;
	//酷秀收款账户信息节点克隆
	var coolshowReceive = $(".coolshowReceive").clone();
	//甲方节点克隆
	var firstParty = $(".firstParty").clone();
	//分销商信息节点克隆
	var distributorInfo = $(".distributorInfo").clone();
	//乙方节点克隆
	var secondParty = $(".secondParty").clone();
	//
	var deleteFirstPartyImg = "<img class='deleteFirstPartyImg' width='23' style='cursor: pointer;' src='"+ctxStatic+"/images/delete.png' title='减少一项'>"
	var deleteSecondPartyImg = "<img class='deleteSecondPartyImg' width='23' style='cursor: pointer;' src='"+ctxStatic+"/images/delete.png' title='减少一项'>"
	//添加甲方按钮的监听
	$(".addFirstPartyImg").live("click",function(){
		//再次可克隆节点，防止重复
		var clone = firstParty.clone(); 
		//将加号图片换成减号
		clone.children("img").replaceWith(deleteFirstPartyImg);
		//添加节点
		$(".partys").eq(0).append(clone);
	})
	//移除甲方按钮的监听
	
	$(".deleteFirstPartyImg").live("click",function(){
		$(this).parent(".firstParty").remove();
		
	})
	
	//酷秀收款账户信息节点默认折叠
	$(".coolshowReceive").animate({
		"height":"60px"
	},time);
	
	//点击展开或者关闭
	$(".addSign").live("click",function(){
		if(this.state == "" || this.state == null){
			this.state = 0;//1展开   0折叠  
		}
		if(this.state == 0){ //折叠状态
			$(this).parents("ul").animate({
				"height":"150px"
			},time);
			this.state = 1;
			changeAdd(this);
		}else{
			$(this).parents("ul").animate({
				"height":"60px"
			},time);
			this.state = 0
			changeAdd(this);
		}
	});*/
	
	
	
	_init_area(); //初始化全国城市三级联动插件
	
	/*$.getJSON(ctxStatic+'/city/bootstrap-chinese-region/sql_areas.json',function(data){
		for (var i = 0; i < data.length; i++) {
			var area = {id:data[i].id,name:data[i].cname,level:data[i].level,parentId:data[i].upid};
			data[i] = area;
		};
		$('.bs-chinese-region').chineseRegion('source',data);
	});*/
	var contractNo = (contract_no == "")?"":contract_no;
	if(contractNo != ""){
		top.$.jBox.prompt("保存["+contractTypeName+"]合同数据成功！","系统提示","warning");	
		$(":input").each(function(index,element){
			$(element).attr("readonly","readonly");
		});
		$(":select").each(function(index,element){
			$(element).attr("disabled","disabled");
		});
		$("#remark").attr("readonly","readonly");
	}
	
	function forSubmit(){
		$("#contractForm").validate({
			submitHandler: function(form){
				var id =  $("#btnSubmit").attr("id");
				if(checkDate()){
					top.$.jBox.prompt("您输入的开始日期大于结束日期，请重新选择！","系统提示","warning");
					return;
				}
				var errMsg = formCheck();
				if(errMsg != ""){
					top.$.jBox.prompt(errMsg,"系统提示","warning");
					return;
				}
				loading('正在提交，请稍等...');
				$("#contractForm").attr("action",rootPath+"/cmag/newcontract/contractData/save");
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
			}
		});
	}
	
	$("#btnSubmit").click(function(){
		$("#btnFlag").val("00");
		forSubmit();
	});
	
	$("#saveSubmit").click(function(){
		$("#btnFlag").val("01");
		forSubmit();
	});
});
//控制邮件地址 显示方式
function selectAdd(val) {
	if (val == "1") { //省份
		$("#tabFirst").addClass("active");
		$("#tabSecond").removeClass("active");
		$("#tabThree").removeClass("active");
		
		$("#province").addClass("active");
		$("#city").removeClass("active");
		$("#district").removeClass("active");
	}
	if (val == "2") { //市县
		$("#tabSecond").addClass("active");
		$("#tabFirst").removeClass("active");
		$("#tabThree").removeClass("active");
		
		$("#city").addClass("active");
		$("#province").removeClass("active");
		$("#district").removeClass("active");
	}
	if (val == "3") { //区
		$("#tabThree").addClass("active");
		$("#tabFirst").removeClass("active");
		$("#tabSecond").removeClass("active");
		
		$("#district").addClass("active");
		$("#province").removeClass("active");
		$("#city").removeClass("active");
	}
}

//依参数改变加减号
function changeAdd(ele){
	if(ele.state == 1){  //如果是展开状态
		ele.innerHTML = "-";
	}else{
		ele.innerHTML = "+";
	}
}

//树形回调函数：当改变值时
function areaTreeselectCallBack(v, h, f){
	var hd_area = new Array("山东","江苏","安徽","浙江","福建","上海");
	var hn_area = new Array("广东","广西壮族自治区","海南");
	var hz_area = new Array("湖北","湖南","河南","江西");
	var hb_area = new Array("北京","天津","河北","山西","内蒙古自治区");
	var xb_area = new Array("宁夏回族自治区","新疆维吾尔自治区","青海","陕西","甘肃");
	var xn_area = new Array("四川","云南","贵州","西藏自治区","重庆");
	var db_area = new Array("辽宁","吉林","黑龙江");
	var tb_area = new Array("台湾","香港特别行政区","澳门特别行政区"); //台港澳地区（包括台湾、香港、澳门）
	var area = $("#areaName").val().split("|");
	if($.inArray(area[0], hd_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("华东地区");
		$("#ss_area").val("02");
	}
	if($.inArray(area[0], hn_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("华南地区");
		$("#ss_area").val("01");
	}
	if($.inArray(area[0], hz_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("华中地区");
		$("#ss_area").val("03");
	}
	if($.inArray(area[0], hb_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("华北地区");
		$("#ss_area").val("04");
	}
	if($.inArray(area[0], xb_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("西北地区");
		$("#ss_area").val("06");
	}
	if($.inArray(area[0], xn_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("西南地区");
		$("#ss_area").val("05");
	}
	if($.inArray(area[0], db_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("东北地区");
		$("#ss_area").val("07");
	}
	if($.inArray(area[0], tb_area) >= 0){
		$("#s2id_ss_area .select2-chosen").text("台港澳地区");
		$("#ss_area").val("08");
	}
} 
//表单参数检查
function formCheck(){
	var contractType = contractTypeDm;
	var msg = "";
	var fjName = $("#uploadFile").val();
	if (fjName == "") {
		msg = msg + "请选择要上传的合同附件！";
		msg = msg +  "\n";
	}
	var fileType = fjName.substring(fjName.lastIndexOf(".")+1,fjName.length).toLowerCase();
	if (fileType != "doc" && fileType != "docx" && fileType != "pdf") {
		msg = msg + "您上传的附件格式不符合格式要求，必须是doc/docx或者pdf格式的文档！";
		msg = msg +  "\n";	
	}
	var finstPartys = $("#jfOfficeId").val().split("|",2);
	var secondPartys = $("#yfOfficeId").val().split("|",2);
	var firstParty = finstPartys[0];
	var jfFlag = finstPartys[1];
	var secondParty = secondPartys[0];
	var yfFlag = secondPartys[1];
	if(firstParty == ""){
		msg = msg +  "请选择合同的甲方！";
		msg = msg + "\n";
	}

	if(secondParty == ""){
		msg = msg + "请选择合同的乙方！";
		msg = msg + "\n";
	}
/*	if(firstParty == secondParty){
		msg = msg + "合同的甲方和乙方不能一样，请您重新选择！";
		msg = msg + "\n";
	}
	if(contractType == "01"){
		if(jfFlag == "1" || jfFlag == "2"){
			msg = msg + "您当前填写的是供应商类型的合同，甲方必须是酷秀集团列表下面的子公司，请您重新选择甲方！";
			msg = msg + "\n";
		}
		if(yfFlag == "0" || yfFlag == "1"){
			msg = msg + "您当前填写的是供应商类型的合同，乙方必须是供应商列表下面的企业，请您重新选择乙方！";
			msg = msg + "\n";
		}
	}
	if(contractType == "02"){
		if(jfFlag == "0" || jfFlag == "2"){
			msg = msg + "您当前填写的是分销商类型的合同，甲方必须是分销商列表下面的企业，请您重新选择甲方！";
			msg = msg + "\n";
		}
		if(yfFlag == "1" || yfFlag == "2"){
			msg = msg + "您当前填写的是供应商类型的合同，乙方必须是酷秀集团列表下面的子公司，请您重新选择乙方！";
			msg = msg + "\n";
		}
	}*/
	var ss_sf = $("#areaId").val();
	if(ss_sf == ""){
		msg = msg + "请选择所属省份！";
		msg = msg + "\n";
	}
	var ss_area = $("#ss_area").val();
	if(ss_area == "00"){
		msg = msg + "请选择所属区域！";
		msg = msg + "\n";
	}
	if(contractType == "02"){
		var level = $("#level").val();
		if(level == "00"){
			msg = msg + "请选择分销商级别！";
			msg = msg + "\n";
		}
		var settlement_way = $("#settlement_way").val();
		if(settlement_way == "00"){
			msg = msg + "请选择分销商结算方式！";
			msg = msg + "\n";
		}
		var checking_period = $("#checking_period").val();
		if(checking_period == "00"){
			msg = msg + "请选择分销商对账周期！";
			msg = msg + "\n";
		}
		var rebate_way = $("#rebate_way").val();
		if(rebate_way == "00"){
			msg = msg + "请选择分销商返佣结算方式！";
			msg = msg + "\n";
		}
	}
	if(contractType == "01"){
		var supplier_type = $("#supplier_type").val();
		if(supplier_type == "00"){
			msg = msg + "请选择供应商类型！";
			msg = msg + "\n";
		}
		/* var suppChecking_period = $("#suppCheckingPeriod").val();
		if(suppChecking_period == "00"){
			msg = msg + "请选择供应商对账周期！";
			msg = msg + "\n";
		}
		var suppRebate_way = $("#suppRebateWay").val();
		if(suppRebate_way == "00"){
			msg = msg + "请选择供应商返佣结算方式！";
			msg = msg + "\n";
		} */
	}
	return msg;
}
//日期检查
function checkDate() {
	var tmp = $("#begin_date").val().split("-");
	var begin_date = new Date(tmp[0], tmp[1], tmp[2]);
	var begin_dates = begin_date.getTime();
	var tmp1 = $("#end_date").val().split("-");
	var end_date = new Date(tmp1[0], tmp1[1], tmp1[2]);
	var end_dates = end_date.getTime();
	if (begin_dates > end_dates) {
		return true;
	}
}
//数字框检查
function check(ob) {
	var val = ob.value;
	if (isNaN(val)) {
		top.$.jBox.prompt("输入的数据必须是数字，请重新输入！","系统提示","warning");
		ob.value = "";
	}
}
//检查合同编号在数据库中是否重复
function checkContractNo(obj){
	$.post(rootPath+"/cmag/newcontract/contractData/checkContractNo",
		{"contract_no":obj.value},function(data){
			if(data.status == "SUCCESS"){
				$("#msg").text(data.msg);
			}else{
				$("#msg").text("*");
			}
		},"json");
}

//供应商返佣信息检查
function checkRebate(obj){
	if(obj.value == "01"){
		top.$.jBox.prompt("请选择后续的核算时间！","系统提示","warning");
		$(verify_date).removeAttr("disabled");
	}
	if(obj.value == "02"){
		$(verify_date).attr("disabled","disabled");
	}
}

function jfOfficeTreeselectCallBack(v,h,f){
	var name = new Array();
	name = $("#jfOfficeName").val().split(",");
	$(".firstPartysName").html("已选")
	console.log(typeof name)
	for(a = 0;a<name.length;a++){
		$(".firstPartysName").html($(".firstPartysName").html()+"<br>"+name[a])
	}
	
}