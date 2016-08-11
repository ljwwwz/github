/**
 * 合同信息
 */
var tabType = tabType;
var contract_name = contractName;
var conNameLength = contract_name.indexOf( "copy" );
var tabTitle = "合同查询列表";
$( function () {
	_init_area(); //初始化全国城市三级联动插件
	//查询关联合同
	$("#btnSerach").click(function() {
		var contract_no = $("#contract_no").val();
		var  url = rootPath+"/cmag/conmanger/mag/list?contract_no="+contract_no+"&tabType=00";
		window.parent.callBySubPage({
			tabTypeId : "contractInfoId",
			operType : "relationConRow",
			url : url
		});
	});
	//返回
	$("#btnCancel").click(function() {
		window.parent.callBySubPage("cancle");
	});
    $( "#old_contract_no" ).val( $( "#contract_no" ).val() );
    if ( conNameLength >= 0 ) {
        $( "#copyInfo" ).val( "Y" );
        top.$.jBox.tip("该合同为复制合同,请您对合同名称进行去掉【copy】字样的操作，以便进入合同的正常的流程控制！", "info");
        $( "#info" ).text( "该合同为复制合同，请使用编辑功能修改相关信息" );
        $("#area1").css('display','block'); 
		$("#area2").css('display','none');
    }
    //查看
    if ( tabType == "1" || tabType == "4" || tabType == "3") { 
    	address = mail_address.split(" ");
    	$("#s2id_s_province a span").eq(0).html(address[0]);
    	$("#s2id_s_city a span").eq(0).html(address[1]);
    	$("#s2id_s_county a span").eq(0).html(address[2]);
    	$( ":input" ).each( function ( index, element ) {
        	if ($( element ).attr( "class") != "arcInfo" ){
        		$( element ).attr( "readonly", "readonly" );
        	}
        } );
        $("#arcInfo").removeAttr("readonly");
        $("#arcInfoYj").removeAttr("readonly");
        $( "#remark" ).attr( "readonly", "readonly" );
    }
    //更新
    if(tabType == "0"){
    	address = mail_address.split(" ");
    	$("#s2id_s_province a span").eq(0).html(address[0]);
    	$("#s_province").val(address[0]);
    	change(1);
    	$("#s2id_s_city a span").eq(0).html(address[1]);
    	$("#s_city").val(address[1]);
    	change(2);
    	$("#s2id_s_county a span").eq(0).html(address[2]);
    	$("#s_county").val(address[2]);
    	
    }
    if($("#rebate").val() == 02){
    	$(verify_date).attr("disabled","disabled");
    }
    $( "#inputForm" ).validate( {
        submitHandler: function ( form ) {
            if ( checkDate() ) {
                top.$.jBox.prompt( "您输入的开始日期大于结束日期，请重新选择！", "系统提示", "warning" );
                return;
            }
            var errMsg = formCheck();
            if ( errMsg != "" ) {
                top.$.jBox.prompt( "温馨提醒\n" + errMsg + "\n" + "请重新操作！", "系统提示", "warning" );
                return;
            }
            if ($("#contract_name").indexOf("copy")) {
            	 top.$.jBox.prompt( "该合同为复制合同,请您对合同名称进行去掉【copy】字样的操作，以便进入合同的正常的流程控制", "系统提示", "warning" );
                 return;
			}
            $( "#firstPartyName" ).val( $( "#firstParty" ).find( "option:selected" ).text() );
            $( "#secondPartyName" ).val( $( "#secondParty" ).find( "option:selected" ).text() );
            loading( '正在提交，请稍等...' );
            form.submit();
        },
        errorContainer: "#messageBox",
        errorPlacement: function ( error, element ) {
            $( "#messageBox" ).text( "输入有误，请先更正。" );
        }
    } );
} );
//表单参数检查
function formCheck() {
    var contractType = contractTypeDm;
    var msg = "";
    if ( $( "#tmpFile" ).text() != "" ) {
        $( "#isUpload" ).val( "Y" )
        $( "#isFile" ).val( $( "#tmpFile" ).text() );
    } else {
        $( "#isUpload" ).val( "N" )
        var fjName = $( "#uploadFile" ).val();
        if ( fjName == "" ) {
            msg = msg + "请选择要上传的合同附件！";
            msg = msg + "\n";
        }
        var fileType = fjName.substring( fjName.lastIndexOf( "." ) + 1,fjName.length ).toLowerCase();
        if (fileType != "doc" && fileType != "docx" && fileType != "pdf") {
    		msg = msg + "您上传的附件格式不符合格式要求，必须是doc/docx或者pdf格式的文档！";
    		msg = msg +  "\n";	
    	}
    }
    var firstParty = $( "#jfOfficeId" ).val();
    if ( firstParty == "" ) {
        msg = msg + "请选择合同的甲方！";
        msg = msg + "\n";
    }
    var secondParty = $( "#yfOfficeId" ).val();
    if ( secondParty == "" ) {
        msg = msg + "请选择合同的乙方！";
        msg = msg + "\n";
    }
    if ( firstParty == secondParty ) {
        msg = msg + "合同的甲方和乙方不能一样，请您重新选择！";
        msg = msg + "\n";
    }
    var ss_area = $( "#ss_area" ).val();
    if ( ss_area == "00" ) {
        msg = msg + "请选择所属区域！";
        msg = msg + "\n";
    }
    if ( contractType == "02" ) {
        var level = $( "#level" ).val();
        if ( level == "00" ) {
            msg = msg + "请选择分销商级别！";
            msg = msg + "\n";
        }
        var settlement_way = $( "#settlement_way" ).val();
        if ( settlement_way == "00" ) {
            msg = msg + "请选择分销商结算方式！";
            msg = msg + "\n";
        }
        var checking_period = $( "#checking_period" ).val();
        if ( checking_period == "00" ) {
            msg = msg + "请选择分销商对账周期！";
            msg = msg + "\n";
        }
        var rebate_way = $( "#rebate_way" ).val();
        if ( rebate_way == "00" ) {
            msg = msg + "请选择分销商返佣结算方式！";
            msg = msg + "\n";
        }
    }
    if ( contractType == "01" ) {
        var supplier_type = $( "#supplier_type" ).val();
        if ( supplier_type == "00" ) {
            msg = msg + "请选择供应商类型！";
            msg = msg + "\n";
        }
        /* 				var suppChecking_period = $("#suppCheckingPeriod").val();
         if (suppChecking_period == "00") {
         msg = msg + "请选择供应商对账周期！";
         msg = msg + "\n";
         }
         var suppRebate_way = $("#suppRebateWay").val();
         if (suppRebate_way == "00") {
         msg = msg + "请选择供应商返佣结算方式！";
         msg = msg + "\n";
         } */
    }
    return msg;
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
//日期检查
function checkDate() {
    var tmp = $( "#begin_date" ).val().split( "-" );
    var begin_date = new Date( tmp[0], tmp[1], tmp[2] );
    var begin_dates = begin_date.getTime();
    var tmp1 = $( "#end_date" ).val().split( "-" );
    var end_date = new Date( tmp1[0], tmp1[1], tmp1[2] );
    var end_dates = end_date.getTime();
    if ( begin_dates > end_dates ) {
        return true;
    }
}
function saveScaning() {
    if ( tabType == 4 ) {
        $( "#operatorType" ).val( "03" );
    }
}
//文件扩展名判断
function checkExtension() {
    var fjName = $( "#uploadFile" ).val();
    if ( fjName == "" ) {
        return false;
    }
    var fileType = fjName.substring( fjName.lastIndexOf( "." ) + 1, fjName.length ).toLowerCase();
    if ( "jpg,jpeg,png".indexOf( fileType ) >= 0 ) {
        return true;
    }
    if ( "zip,rar,tar".indexOf( fileType ) >= 0 ) {
        return true;
    }
    if ( "pdf".indexOf( fileType ) >= 0 ) {
        return true;
    }
    return false;
}
function check() {
    if ( tabType == 0 ) {
        $( "#operatorType" ).val( "01" );
    }
    if ( tabType == 3 ) {
        $( "#operatorType" ).val( "02" );
    }
    if ( tabType == 4 ) {
        if ( !checkExtension() ) {
            top.$.jBox.prompt( "您上传的附件格式不符合系统要求的格式，格式要求必须是：\n 1.图片格式：jpg、jpeg、png \n 2.文档格式 pdf \n 3.压缩包格式：zip、rar", "系统提示", "warning" );
            return false;
        }
    }
    return true;
}
//检查合同编号在数据库中是否重复
function checkContractNo( obj ) {
    $.post( rootPath + "/cmag/newcontract/contractData/checkContractNo", {"contract_no": obj.value},
        function ( data ) {
            if ( data.status == "SUCCESS" ) {
                $( "#msg" ).text( data.msg );
            } else {
                $( "#msg" ).text( "*" );
            }
        }, "json" );
}
