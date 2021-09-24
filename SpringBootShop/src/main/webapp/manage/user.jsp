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

    <title>用户信息显示</title>

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
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
    </script>
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
        <h2>用户管理</h2>
        <div class="manage">
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>Email</th>
                    <th>手机</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${userList}" var="user">
                    <tr align="center">
                        <td align="center" class="first w4 c">${user.euUserId}</td>
                        <td align="center" class="w1 c">${user.euName}</td>
                        <td align="center" class="w2 c">${user.euSex}</td>
                        <td align="center">${user.euEmail}</td>
                        <td align="center" class="w4 c">${user.euMobile}</td>
                        <td class="w1 c"><a href="javascript:Edit('${user.euUserName}');">修改</a> <a
                                href="javascript:Delete('${user.euUserName}');">删除</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="6" align="center">

                        <a href="manage/getAllUsers.do?currentPage=1">首页</a> &nbsp;
                        <c:if test="${currentPage==1}" var="pre">
                            <a href="manage/getAllUsers.do?currentPage=1">上一页</a> &nbsp;
                        </c:if>
                        <c:if test="${ ! pre }">
                            <a href="manage/getAllUsers.do?currentPage=${currentPage-1 }">上一页</a> &nbsp;
                        </c:if>
                        <c:if test="${currentPage==totalCount}" var="next">
                            <a href="manage/getAllUsers.do?currentPage=${totalCount }">下一页</a>&nbsp;
                        </c:if>
                        <c:if test="${ ! next }">
                            <a href="manage/getAllUsers.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;
                        </c:if>
                        <a href="manage/getAllUsers.do?currentPage=${totalCount }">末 页</a>
                        <br/>
                        当前第${currentPage }页,一共${total }条,一共${totalCount }页
                    </td>

                </tr>
            </table>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.js"></script>
<script type="text/javascript">
    function Delete(euUserName) {
        var result = window.confirm("确认删除" + euUserName + "嘛？");
        if (true == result) {
            window.location.href = "manage/delUser.do?euUserName=" + euUserName;
        }
    }

    function Edit(euUserName) {
        var result = window.confirm("确认修改" + euUserName + "嘛？");
        if (true == result) {
            window.location.href = "manage/fingByUserName.do?euUserName=" + euUserName;
        }
    }
</script>
</html>
