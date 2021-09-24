<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'user-modify.jsp' starting page</title>

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
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="<%=basePath %>images/logo.gif"/></div>
    <div class="help">
        <a href="middle/middleMainJsp.do">返回首页</a>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="middle/middleUserCenter.do">首页</a></li>
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
    您现在的位置：<a href="middle/middleManageOne.do">易买网</a> &gt; 修改用户
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
    <div id="main" class="wrap">
        <div class="main">
            <h2>修改用户</h2>
            <div class="manage">
                <form action="manage/editUser1.do" method="post">
                    <table class="form">
                        <tr>
                            <td class="field">用户名：</td>
                            <td><input type="text" class="text" name="euUserName" id="euUserName" readonly="readonly"
                                       value="${someOneInfo1.euUserName}"/></td>
                        </tr>
                        <tr>
                            <td class="field">姓名：</td>
                            <td><input type="text" class="text" name="euName" id="euName"
                                       value="${someOneInfo1.euName}"/></td>
                        </tr>
                        <tr>
                            <td class="field">密码：</td>
                            <td><input type="text" class="text" name="euPassword" id="euPassword"
                                       value="${someOneInfo1.euPassword}"/></td>
                        </tr>
                        <tr>
                            <td class="field">性别：</td>
                            <td>
                                <input type="radio" name="euSex" id="euSex" value="男" checked="checked"/>男
                                <input type="radio" name="euSex" id="euSex" value="女"/>女
                            </td>
                        </tr>
                        <tr>
                            <td class="field">身份证：</td>
                            <td><input type="text" class="text" name="euIdentityCode" id="euIdentityCode"
                                       value="${someOneInfo1.euIdentityCode}" readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <td class="field">手机号码：</td>
                            <td><input type="text" class="text" name="euMobile" id="euMobile"
                                       value="${someOneInfo1.euMobile}"/></td>
                        </tr>
                        <tr>
                            <td class="field">邮箱：</td>
                            <td><input type="text" class="text" name="euEmail" id="euEmail"
                                       value="${someOneInfo1.euEmail}"/></td>
                        </tr>
                        <tr>
                            <td class="field">送货地址：</td>
                            <td><input type="text" class="text" name="euAddress" id="euAddress"
                                       value="${someOneInfo1.euAddress}"/></td>
                        </tr>
                        <tr>
                            <td class="field">用户类型：</td>
                            <td>
                                <input type="text" class="text" name="euStatus" id="euStatus"
                                       value="${someOneInfo1.roles.roleName}" readonly="readonly"/>

                                <%-- <input type="text" class="text" name="euStatus" id="euStatus" value="${someOneInfo.roles.roleName}" readonly="readonly" /> --%>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><label class="ui-blue"><input type="submit" name="submit" value="更新"/></label></td>
                        </tr>
                    </table>
                </form>
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