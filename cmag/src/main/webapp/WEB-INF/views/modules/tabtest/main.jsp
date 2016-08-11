<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Tab页测试</title>
	<meta name="decorator" content="default"/>
    <script src="${ctxStatic}/bootstrap/addTabs/theme/js/bootstrap-exttabs.js" type="text/javascript"></script>
    <link href="${ctxStatic}/bootstrap/addTabs/theme/css/bootstrap-exttabs.css" type="text/css" rel="stylesheet" />
	 <script type="text/javascript">

            var extTabObj=null;
            $(function(){
                extTabObj=$('#tabs').bindExtTab({});
                $('#addTab1').click(function(){
                    extTabObj.addTab({
                       id: 'tab_'+$(this).attr('id'),
                       title: $(this).html(),
					   url:'${ctx}/tab/test/tabTest1' 
                    })
                });
               
                $('#closeTab1').click(function(){
                    extTabObj.closeTab('tab_'+$('#addTab1').attr('id'));
                });

                  $('#selectTab1').click(function(){
                     extTabObj.selectTab('tab_'+$('#addTab1').attr('id'));
                });

                 $('#callTab1').click(function(){
                    
                    var subPage=extTabObj.findTabPage('tab_'+$('#addTab1').attr('id'));
                    subPage.contentWindow.callByParentPage(); 
                });


                $('#addTab1Close').click(function(){
                    extTabObj.addTab({
                       id: 'tab_'+$(this).attr('id'),
                       title: $(this).html(),
                       close: true,
                       url:'${ctx}/sys/menu/' 
                    })
                });
                 $('#closeTab2').click(function(){
                    extTabObj.closeTab('tab_'+$('#addTab1Close').attr('id'));
                });
                $('#reloadTab2').click(function(){
                    var loadParams={
						 id:'tab_'+$('#addTab1Close').attr('id'),
						 title:'重新加载Tab2', //指定新标题,可不传
						 url:'${ctx}/sys/menu/' //指定新url,可不传
						 }
                    extTabObj.reloadTab(loadParams);
                });
                
                $('#reloadTab1').click(function(){
                	var loadParams={
						 id:'tab_'+$('#addTab1').attr('id'),
						 title:'重新加载Tab1', //指定新标题,可不传
						 url:'${ctx}/tab/test/tabTest1' //指定新url,可不传
						 }
                    extTabObj.reloadTab(loadParams);
                    
                });
                
            })
             
            function callBySubPage(params){
                alert('this is subCall');


            }


        </script>
</head>
<body>
        <div id="tabs">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>      
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <div>
                          <button type="button" id="addTab1" class="btn btn-default" >Tab1-不可关闭</button>
                          <button type="button" id="closeTab1" class="btn btn-default" >关闭Tab1</button>
                          <button type="button" id="selectTab1" class="btn btn-default" >选中[Tab1-不可关闭]</button>
                          <button type="button" id="callTab1" class="btn btn-default" >调用[Tab1-不可关闭]子页面方法</button>
                           <button type="button" id="reloadTab1" class="btn btn-default" >重新加载Tab1</button>
                        </div>
                        <div>
                           <button type="button" id="addTab1Close" class="btn btn-default" >Tab2-可关闭</button>
                           <button type="button" id="closeTab2" class="btn btn-default" >关闭Tab2</button>
                            <button type="button" id="reloadTab2" class="btn btn-default" >重新加载Tab2</button>
                        </div>
                    </div>                    
                </div>

            </div>    

</body>
</html>