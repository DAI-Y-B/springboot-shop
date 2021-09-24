<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加新闻</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript" src="<%=basePath%>scripts/function-manage.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="<%=basePath %>images/logo.gif"/></div>
    <div class="help">
        <a href="middle/middleMainJsp.do">返回前台页面</a>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="middle/middleManageCenter.do">首页</a></li>
            <li><a href="middle/middleManageUser.do">用户</a></li>
            <li><a href="middle/middleManageProduct.do">商品</a></li>
            <li><a href="middle/middleManageOrder.do">订单</a></li>
            <li><a href="middle/middleManageReply.do">留言</a></li>
            <li><a href="middle/middleManageNews.do">新闻</a></li>
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
    您现在的位置：<a href="middle/middleManageCenter.do">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
    <div id="menu-mng" class="lefter">
        <div class="box">
            <dl>
                <dt>用户管理</dt>
                <dd><em><a href="middle/middleManageAddUser.do">新增</a></em><a href="middle/middleManageUser.do">用户管理</a>
                </dd>
                <dt>商品信息</dt>
                <dd><em><a href="middle/middleManageProductClassAdd.do">新增</a></em><a
                        href="middle/middleManageProductClass.do">分类管理</a></dd>
                <dd><em><a href="middle/middleManageProductAdd.do">新增</a></em><a href="middle/middleManageProduct.do">商品管理</a>
                </dd>
                <dt>订单管理</dt>
                <dd><a href="middle/middleManageOrder.do">订单管理</a></dd>
                <dt>留言管理</dt>
                <dd><a href="middle/middleManageReply.do">留言管理</a></dd>
                <dt>新闻管理</dt>
                <dd><em><a href="middle/middleManageAddNews.do">新增</a></em><a href="middle/middleManageNews.do">新闻管理</a>
                </dd>
            </dl>
        </div>
    </div>
    <div class="main">
        <h2>添加新闻</h2>
        <div class="manage">
            <form action="news/newsAdd.do" method="post">
                <table class="form">
                    <tr>
                        <td class="field">新闻标题：</td>
                        <td><input type="text" class="text" name="title" id="title" value=""/></td>
                    </tr>
                    <tr>
                        <td class="field">新闻内容：</td>
                        <td><textarea name="content" id="content"></textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="submit" name="submit" value="添加"/></label></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
