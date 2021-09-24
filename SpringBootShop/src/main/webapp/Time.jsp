<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath1%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
				<script type="text/javascript">
		function $$(id){
				return document.getElementById(id);
			}
			function showTime(){
			var myspan=$$("myTime");
			var mydate=new Date();
			var myYear=mydate.getFullYear();
			var myMonth=mydate.getMonth()+1;
			var myRiqi=mydate.getDate();
			var myDay=mydate.getDay();
			var myHour=mydate.getHours();
			var myMinitue=mydate.getMinutes();
			var mySecond=mydate.getSeconds();
			var str =myYear+"年";
			if(myMonth<10){
				str+="0";
			}
			str+=myMonth+"月";
			if(myRiqi<10){
				str+="0";
			}
			str+=myRiqi+"日";
			if(myHour<10){
				str+="0";
			}
			str+=myHour+":";
			if(myMinitue<10){
				str+="0";
			}
			str+=myMinitue+":";
			if(mySecond<10){
				str+="0";
		}
		str+=mySecond;
		myspan.innerHTML=str;
	//	setTimeout("showTime()",1000);
	}
	function myLoad(){
		setInterval("showTime()",1000);
	}
	</script>

  </head>
  
  <body onload="myLoad()">
    管理员${checkUser.euName }您好，今天是<span id="myTime"></span>，欢迎回到管理后台。
  </body>
</html>
