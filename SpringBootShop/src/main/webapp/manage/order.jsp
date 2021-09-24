<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>订单管理</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript" src="<%=basePath%>scripts/function-manage.js"></script>
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
        <h2>订单管理</h2>
        <div class="manage">
            <!-- <div class="search">
                <form method="post" action="order/findOrder.do" id="find" onsubmit="return checkBeforeSumit()">
                    订单号：<input type="text" class="text" name="orderId" id="orderId"/>
                    订货人：<input type="text" class="text" name="userName" id="userName"/>
                    <label class="ui-blue"><input type="submit" name="submit" value="查询" /></label>
                </form>
            </div> -->
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
                <c:if test="${orderList != null}">
                    <c:forEach items="${orderList}" var="order">
                        <tr align="center">
                            <td class="first w4 c" align="center">${order.eoId}/${order.orderDetails.products.name}</td>
                            <td class="w1 c" align="center">${order.eoUserId}</td>
                            <td class="w1 c" align="center">${order.eoUserName}</td>
                            <td align="center">${order.eoUserAddress}</td>
                            <td class="w1 c" align="center">${order.ostatuss.statusName}</td>
                            <td class="w1 c" align="center"><a href='javascript:Edit("${order.eoId}");'>修改</a> <a
                                    href='javascript:Delete("${order.eoId}");'>删除</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <div class="pager">
                <ul class="clearfix">


                    <li><a href="order/getAllOrders.do?currentPage=1">首页</a></li>
                    <li>
                        <c:if test="${currentPage==1}" var="pre">
                            <a href="order/getAllOrders.do?currentPage=1">上一页</a>
                        </c:if>
                        <c:if test="${ ! pre }">
                            <a href="order/getAllOrders.do?currentPage=${currentPage-1 }">上一页</a>
                        </c:if>
                    </li>
                    <li>
                        <c:if test="${currentPage==totalCount}" var="next">
                            <a href="order/getAllOrders.do?currentPage=${totalCount }">下一页</a>
                        </c:if>
                        <c:if test="${ ! next }">
                            <a href="order/getAllOrders.do?currentPage=${currentPage+1 }">下一页</a>
                        </c:if>
                    </li>
                    <li><a href="order/getAllOrders.do?currentPage=${totalCount }">末 页</a></li>
                    <br/>
                    <li> 当前第${currentPage }页,一共${total }条,一共${totalCount }页</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function Delete(eoId) {
        if (confirm("确定要删除ID为" + eoId + "订单吗？")) {
            location.href = "order/delOrder.do?eoId=" + eoId;
        }
    }

    function Edit(eoId) {
        if (confirm("确定要修改ID为" + eoId + "的订单吗？")) {
            location.href = "order/orderFindById.do?eoId=" + eoId;
        }
    }

    function checkBeforeSumit() {
        var orderId = document.getElementById("orderId").value;
        var regex = /^[0-9]*$/;
        if (!regex.test(orderId)) {
            alert("不能是非数字字符串");
            return false;
        }


    }
</script>
</html>
