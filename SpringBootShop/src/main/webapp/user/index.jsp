<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户中心首页</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link type="text/css" rel="stylesheet" href="<%=basePath %>css/style.css"/>
    <script type="text/javascript" src="<%=basePath %>scripts/function-manage.js"></script>

</head>
<body>
<div id="header" class="wrap">
    <div id="logo">
        <img src="<%=basePath%>images/logo.gif"/>
    </div>
    <div class="help">
        <a href="middle/middleMainJsp.do">返回首页</a>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current">
                <a href="middle/middleUserCenter.do">首页</a></li>
            <li><a href="middle/middleManageOne.do">个人管理</a></li>
            <li><a href="middle/middleOneOrder.do">订单管理</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="welcome wrap">
        <!-- 时间显示 -->
        <%@include file="../Time.jsp" %>
    </div>
</div>
<div id="position" class="wrap">
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 个人管理
</div>
<div id="main" class="wrap">
    <div id="menu-mng" class="lefter">
        <div class="box">
            <dl>
                <dt>个人管理</dt>
                <dd><a href="middle/middleManageOne.do">用户管理</a></dd>
                <dt>订单管理</dt>
                <dd><a href="middle/middleOneOrder.do">订单管理</a></dd>
            </dl>
        </div>
    </div>
    <div class="main">
        <h2>个人信息首页</h2>
        <div id="welcome" class="manage">
            <div class="shadow">
                <em class="corner lb"></em>
                <em class="corner rt"></em>
                <div class="box">
                    <div class="msg">
                        <p>尊敬的用户--${checkUser.euName}--欢迎回来</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
