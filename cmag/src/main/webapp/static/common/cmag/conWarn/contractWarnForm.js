/**
 * 合同信息
 */
var tabType = tabType;
$( function () {
	//返回
	$("#btnCancel").click(function() {
		window.parent.callBySubPage("cancle");
	});
    if ( tabType == "1") {
        $( ":input" ).each( function ( index, element ) {
        	$( element ).attr( "readonly", "readonly" );
        } );
        $( "#remind_content" ).attr( "readonly", "readonly" );
    }
} );
//表单参数检查
function formCheck() {
    var msg = "";
    
    return msg;
}
function check(obj) {
	$("#inputForm").attr("action",rootPath+"/cmag/conwarn/mag/update");
    return true;
}
