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
    <title>新闻窗口</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            ///window.location.href="/EB/newsQuery.do";
            $.ajax({
                type: "POST",
                url: "product/rootCategoryQuery.do",
                async: false,
                success: function (msg) {
                    var array = eval(msg);

                    var topUL = $("#topUL");
                    topUL.html("");


                    for (var i = 0; i < array.length; i++) {
                        var li = $("<li></li>");
                        if (i == 0) {
                            li.attr('class', 'first');
                        }
                        var a = $("<a>" + array[i].cName + "</a>");
                        a.attr('href', '<%=basePath%>product-list.jsp?cId=' + array[i].cId);
                        li.append(a);
                        topUL.append(li);
                    }
                    var lastLi = $("<li></li>");
                    var lastA = $("<a>" + 'Investor Relations' + "</a>");
                    lastA.attr('href', '#');
                    lastLi.append(lastA);
                    topUL.append(lastLi);

                }
            });
        });

    </script>

</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="images/logo1.png"/></div>
    <div class="help">
        <a href="middle/middleYouCar.do" class="shopping">购物车</a>
        <a href="middle/middleLogin.do">登录</a>
        <a href="middle/middleRegist.do">注册</a>
        <a href="middle/middleComment.do">留言</a>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="middle/middleMainJsp.do">首页</a></li>
            <li><a href="middle/middleMainJsp.do">图书</a></li>
            <li><a href="middle/middleMainJsp.do">百货</a></li>
            <li><a href="middle/middleMainJsp.do">品牌</a></li>
            <li><a href="middle/middleMainJsp.do">促销</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="wrap">
        <ul class="clearfix" id="topUL">

        </ul>
    </div>
</div>
<div id="position" class="wrap">
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 阅读新闻
</div>
<div id="main" class="wrap">
    <div class="left-side">
        <div class="news-list">
            <h4>最新公告</h4>
            <ul>
                <c:forEach items="${newsList }" var="news">
                    <li><a href="news/findNewsByTitle.do?title=${news.nTitle }" target="_blank">${news.nTitle  }</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="spacer"></div>
        <div class="news-list">

            <h4>新闻动态</h4>
            <ul>
                <c:forEach items="${newsList }" var="news">
                    <li><a href="news/findNewsByTitle.do?title=${news.nTitle }" target="_blank">${news.nTitle  }</a>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>
    <div id="news" class="right-main">

        <h1>${news.nTitle }</h1>
        <div class="content">
            ${news.nContent }
        </div>

    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
