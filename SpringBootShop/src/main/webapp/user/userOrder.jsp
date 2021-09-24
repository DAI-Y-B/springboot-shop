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

    <title>个人订单展示页</title>

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
                <dd>
                    <a href="middle/middleManageOne.do">用户管理</a>
                </dd>
                <dt>订单管理</dt>
                <dd>
                    <a href="middle/middleOneOrder.do">订单管理</a>
                </dd>
            </dl>
        </div>
    </div>
    <div class="main">
        <h2>订单管理</h2>
        <div class="manage">
            <div class="spacer"></div>
            <table class="list">
                <tr>
                    <th>订单ID/商品名称</th>
                    <th>用户ID</th>
                    <th>姓名</th>
                    <th>收货地址</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:if test="${orderList1 != null}">
                    <c:forEach items="${orderList1}" var="order">
                        <tr align="center">
                            <td class="first w4 c" align="center">${order.eoId}/${order.orderDetails.products.name}</td>
                            <td class="w1 c" align="center">${order.eoUserId}</td>
                            <td class="w1 c" align="center">${order.eoUserName}</td>
                            <td align="center">${order.eoUserAddress}</td>
                            <td class="w1 c" align="center">${order.ostatuss.statusName}</td>
                            <td class="w1 c" align="center"><a
                                    href='javascript:Edit("${order.eoId}");'>订单详情与修改</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <div class="pager">
                <ul class="clearfix">


                    <li><a href="order/getOenOrders.do?currentPage=1">首页</a></li>
                    <li><c:if test="${currentPage1==1}" var="pre">
                        <a href="order/getOenOrders.do?currentPage=1">上一页</a>
                    </c:if> <c:if test="${ ! pre }">
                        <a href="order/getOenOrders.do?currentPage=${currentPage1-1 }">上一页</a>
                    </c:if></li>
                    <li><c:if test="${currentPage1==totalCount1}" var="next">
                        <a href="order/getOenOrders.do?currentPage=${totalCount1 }">下一页</a>
                    </c:if> <c:if test="${ ! next }">
                        <a href="order/getOenOrders.do?currentPage=${currentPage1+1 }">下一页</a>
                    </c:if></li>
                    <li><a
                            href="order/getOenOrders.do?currentPage=${totalCount1 }">末 页</a></li>
                    <br/>
                    <li>当前第${currentPage1 }页,一共${total1 }条,一共${totalCount1 }页</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
<script type="text/javascript">
    function Edit(eoId) {
        if (confirm("确定要修改ID为" + eoId + "的订单吗？")) {
            location.href = "order/orderFindById1.do?eoId=" + eoId;
        }
    }
</script>
</body>
</html>
