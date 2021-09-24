<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>个人订单修改页</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript"
            src="<%=basePath%>scripts/function-manage.js"></script>
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
    <div id="position" class="wrap">
        您现在的位置：<a href="middle/middleManageOne.do">易买网</a> &gt; 管理后台
    </div>
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
        <h2>显示订单详情，修改订单状态</h2>
        <div class="manage">
            <form action="order/editOrder1.do" method="post">
                <table class="form">
                    <tr>
                        <td class="field">订单ID：</td>
                        <td><input type="text" class="text" name="orderId"
                                   value="${orderOneSelf.eoId}" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="field">订购人姓名：</td>
                        <td><input type="text" class="text" name="name"
                                   value="${orderOneSelf.eoUserName}" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="field">用户地址：</td>
                        <td><input type="text" class="text" name="useraddress"
                                   value="${orderOneSelf.eoUserAddress }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="field">金额：</td>
                        <td><input type="text" class="text" name="cost"
                                   value="${orderOneSelf.eoCost }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="field">状态：</td>
                        <td><select class="text" name="status">
                            <option value=" ${orderOneSelf.eoStatus }">${orderOneSelf.ostatuss.statusName}</option>
                            <c:if test="${orderOneSelf.eoStatus < 5 }">
                                <option value="5">确认收货</option>
                                <!-- <option value="6">申请退款</option> -->
                            </c:if>
                        </select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="submit"
                                                          name="submit" value="更新"/></label></td>
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
