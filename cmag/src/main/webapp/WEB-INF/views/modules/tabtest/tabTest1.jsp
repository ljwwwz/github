<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Tab页测试</title>
	<meta name="decorator" content="default"/>
    <script src="${ctxStatic}/bootstrap/addTabs/theme/js/bootstrap-exttabs.js" type="text/javascript"></script>
    <link href="${ctxStatic}/bootstrap/addTabs/theme/css/bootstrap-exttabs.css" type="text/css" rel="stylesheet" />
	 <script type="text/javascript">
             $(function(){
               
               
                $('#callParent').click(function(){
                    window.parent.callBySubPage();
                });
                
            })
            
            function callByParentPage(params){
                alert('this is parentCall');

            }


        </script>
</head>
<body>
       <button type="button" id="callParent" >调用父页面方法</button>

</body>
</html>