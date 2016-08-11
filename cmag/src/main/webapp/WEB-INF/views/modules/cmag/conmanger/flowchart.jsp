<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>流程图</title>
  	<meta name="decorator" content="default"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		$(document).ready(function(){
			$("#btnMenu").remove();
			
			
			$.ajaxSetup({
				async: false,//使用同步的方式,true为异步方式
			})
			
			//获取当签合同状态代码
			url = "${ctx}/cmag/conmanger/mag/getConState?contract_no="+"${contract.contract_no}";
			args = {};
			var currentState = null;
			$.post(url,args,function(data){
				currentState = data;
			})
			
			//请求所有状态对象
			url = "${ctx}/cmag/conmanger/mag/getConStatesJson";
			args = {};
			var stateJson = null;
			$.post(url,args,function(data){
				stateJson = JSON.parse(data);
			});
			
			var prevs = new Array();//用来保存当前状态前面所有状态的的state_dm的集合
			var start = 0;
			
			//用于收集当前状态之前所有的状态
			for(a = 0;a<stateJson.length;a++){
				if(stateJson[a].state_dm == currentState ){//当前状态对应的json
					getPrevs(stateJson[a]);
				}
			};
			//遍历stateJson。给包含在prevs变量中的state增加属性，不包含的增加另一种属性
			for(b = 0;b<stateJson.length;b++){
				if(inArray(stateJson[b].state_dm,prevs) == -1){
					//如果不包含
					stateJson[b].htmlContent = "<div style='background-color: #ED675A'>"+stateJson[b].state_name+"<div>";
				}else{
					//如果包含
					stateJson[b].htmlContent = "<div style='background-color: #9AE08E'>"+stateJson[b].state_name+"<div>";
				}
			}
			
			write();
			
			//将流程写入格子中
			function write(){
				$(".row1 .boxs .flow").eq(0).html(stateJson[0].htmlContent);
				$(".row2 .boxs .flow").eq(0).html(stateJson[1].htmlContent);
				$(".row2 .boxs .flow").eq(2).html(stateJson[3].htmlContent);
				$(".row3 .boxs .flow").eq(0).html(stateJson[2].htmlContent);
				$(".row4 .boxs .flow").eq(0).html(stateJson[4].htmlContent);
				$(".row5 .boxs .flow").eq(0).html(stateJson[5].htmlContent);
				$(".row5 .boxs .flow").eq(2).html(stateJson[6].htmlContent);
				$(".row6 .boxs .flow").eq(0).html(stateJson[7].htmlContent);
				$(".row7 .boxs .flow").eq(0).html(stateJson[8].htmlContent);
			}
			
			//此递归函数用来获取当前状态前面所有状态的的state_dm的集合
			function getPrevs(state){
				if(state.state_dm != null && state.state_dm != ""){
					prevs[start] = state.state_dm;
					start++;
				}
				
				if(state.prev == null || state.prev==""){
					return;
				}else{
					for(a = 0;a<stateJson.length;a++){
						if(stateJson[a].state_dm == state.prev ){
							getPrevs(stateJson[a]);
						}
					}
				}
			}
			
			//此函数用于判断元素是否在数组中
			function inArray(value,array){
				for(a = 0;a<array.length;a++){
					if(value == array[a]){
						return a;
					}
				}
				return -1;
			}
			
		});
	</script>
	<style type="text/css">
		.row{
			height:54px;
			width:653px;
		}
		.row .boxs{
			width:640px;
			height:30px;
		}
		.row .boxs .flow{
			width:128px;
			height:30px;
			float:left;
			text-align: center;
			font-family: 微软雅黑;
			line-height: 30px;
			font-weight: bold;
		}
		.mainBody{
			width:640px;
			margin:0px auto;
		}
		.row .arrows{
			width:653px;
			height:20px;
		}
		.row .arrows .arrow{
			width:128px;
			height:20px;
			float:left;
			text-align: center;
			line-height: 27px;
		    color: #666;
		    text-align: center;
		    font-size: 20px;
		    display: inline-block;
		}
		.mainBody{
			margin-top:20px;
		}
		
	</style>
  </head>
  <body >
  <div  class="mainBody">
	<div class="row row1">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row2">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow">------------------></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row3">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row4">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row5">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow">------------------></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row6">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow">↓</div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row7">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<!-- <div class="row row8">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div>
	<div class="row row9">
		<div class="boxs">
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
			<div class="flow"></div>
		</div>
		<div class="arrows">
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
			<div class="arrow"></div>
		</div>
	</div> -->
	</div>
  </body>
</html>
