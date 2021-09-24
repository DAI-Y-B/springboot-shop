<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商城主页</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        //setTimeout(function(){location.reload()},10000); //指定1秒刷新一次
        $(document).ready(function () {
            ///window.location.href="/EB/newsQuery.do";
            $.ajax({
                type: "POST",
                url: "product/rootCategoryQuery.do",
                async: false,
                success: function (msg) {
                    var array = eval(msg);
                    var spfl = $("#spfl");
                    var h2 = $("<h2>" + "商品分类" + "</h2>");
                    spfl.append(h2);

                    var topUL = $("#topUL");
                    topUL.html("");


                    for (var i = 0; i < array.length; i++) {
                        var dl = $("<dl></dl>");
                        var dt = $("<dt>" + array[i].cName + "</dt>");
                        dl.append(dt);
                        var li = $("<li></li>");
                        if (i == 0) {
                            li.attr('class', 'first');
                        }
                        var a = $("<a>" + array[i].cName + "</a>");
                        a.attr('href', '<%=basePath%>user/product-list.jsp?cId=' + array[i].cId);

                        li.append(a);
                        topUL.append(li);
                        $.ajax({
                            type: "POST",
                            url: "product/proCateQueryByParenId.do",
                            async: false,
                            data: {
                                parentId: array[i].cId,
                            },
                            success: function (msg) {
                                var array1 = eval(msg);
                                for (var j = 0; j < array1.length; j++) {
                                    var dd = $("<dd></dd>");
                                    var a = $("<a>" + array1[j].cName + "</a>");
                                    a.attr('href', '<%=basePath%>user/product-list.jsp?cId=' + array1[j].cId);
                                    dd.append(a);
                                    dl.append(dd);
                                }
                            }
                        });
                        spfl.append(dl);
                        //<li class="last"><a href="#">Investor Relations</a></li>

                    }
                    var lastLi = $("<li></li>");
                    var lastA = $("<a>" + 'Investor Relations' + "</a>");
                    lastA.attr('href', '#');
                    lastLi.append(lastA);
                    topUL.append(lastLi);
                }
            });

            $.ajax({
                type: "POST",
                url: "product/product1.do",
                async: true,
                success: function (msg) {
                    var array = JSON.parse(msg);//将字符串转为json对象
                    var jrtj = $("#jrtj");
                    jrtj.html("");
                    var flag = array.length;
                    var flag1 = array.length;
                    //document.getElementById("commentMes").innerHTML="";
                    if (array.length > 8) {
                        flag = 8;
                    }
                    if (array.length > 12) {
                        flag1 = 12;
                    }
                    for (var i = 0; i < flag; i++) {
                        var li = $("<li></li>");
                        var dl = $("<dl></dl>");
                        var dt = $("<dt></dt>");
                        var productDetail = $("<a></a>");
                        productDetail.attr('href', '<%=basePath%>user/product-view.jsp?id=' + array[i].id);
                        //productDetail.attr('onclick','location.reload()');
                        productDetail.attr('target', '_blank');
                        productDetail.attr('id', 'detail');

                        var productImg = $("<img>");
                        productImg.attr('src', '/pic/' + array[i].fileName);
                        productDetail.append(productImg);
                        dt.append(productDetail);

                        var dd = $("<dd class='title'" + "></dd>");
                        var productDetail1 = $("<a>" + array[i].name + "</a>");
                        productDetail1.attr('href', '<%=basePath%>user/product-view.jsp?id=' + array[i].id);
                        productDetail1.attr('target', '_blank');
                        //productDetail1.attr('onclick','location.reload()');
                        productDetail1.attr('id', 'detail1');
                        dd.append(productDetail1);

                        var dd1 = $("<dd class='price' >" + "￥" + array[i].price + "</dd>");
                        dl.append(dt).append(dd).append(dd1);
                        li.append(dl);
                        jrtj.append(li);
                    }
                }
            });
            $.ajax({
                type: "POST",
                url: "product/product.do",
                async: true,
                success: function (msg) {
                    var array = JSON.parse(msg);//将字符串转为json对象
                    var rmtj = $("#rmtj");
                    rmtj.html("");
                    var flag = array.length;
                    var flag1 = array.length;
                    //document.getElementById("commentMes").innerHTML="";
                    if (array.length > 8) {
                        flag = 8;
                    }
                    if (array.length > 12) {
                        flag1 = 12;
                    }
                    for (var j = 0; j < flag1; j++) {
                        var li = $("<li></li>");
                        var dl = $("<dl></dl>");
                        var dt = $("<dt></dt>");
                        var productDetail = $("<a></a>");
                        productDetail.attr('href', '<%=basePath%>user/product-view.jsp?id=' + array[j].id);
                        productDetail.attr('target', '_blank');
                        //	productDetail.attr('onclick','location.reload()');
                        productDetail.attr('id', 'detail');

                        var productImg = $("<img>");
                        productImg.attr('src', '/pic/' + array[j].fileName);
                        productDetail.append(productImg);
                        dt.append(productDetail);

                        var dd = $("<dd class='title'" + "></dd>");
                        var productDetail1 = $("<a>" + array[j].name + "</a>");
                        productDetail1.attr('href', '<%=basePath%>user/product-view.jsp?id=' + array[j].id);
                        productDetail1.attr('target', '_blank');
                        //productDetail1.attr('onclick','location.reload()');
                        productDetail1.attr('id', 'detail1');
                        dd.append(productDetail1);

                        var dd1 = $("<dd class='price' >" + "￥" + array[j].price + "</dd>");
                        dl.append(dt).append(dd).append(dd1);
                        li.append(dl);
                        rmtj.append(li);
                    }
                }

            });
        });
    </script>
</head>
<body>
<!-- <script type="text/javascript">
	function Edit(euUserName){
		var result = window.confirm("确认修改"+euUserName+"嘛？");
		if(true == result){
			window.location.href = "manage/fingByUserName1.do?euUserName="+euUserName;
		}
	}
</script> -->
<div id="header" class="wrap">
    <div id="logo"><img src="images/logo1.png"/></div>
    <div class="help">
        <a href="middle/middleYouCar.do" class="shopping">购物车</a>
        <a href="middle/middleLogin.do">登录</a>
        <a href="middle/middleRegist.do">注册</a>
        <c:if test="${not empty checkUser}">
            <a href="middle/middleUserCenter.do">个人中心</a>
        </c:if>
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
<div id="main" class="wrap">
    <div class="lefter">
        <div class="box" id="spfl">
        </div>
        <div class="spacer"></div>
        <div id="lastFive">

        </div>
    </div>
    <div class="main">
        <div class="price-off">
            <h2>今日特价</h2>
            <ul class="product clearfix" id="jrtj">


            </ul>
        </div>
        <div class="side">
            <div class="news-list">
                <h4>最新公告</h4>
                <ul>
                    <c:forEach items="${newsList }" var="news">
                        <li><a href="news/findNewsByTitle.do?title=${news.nTitle }">${news.nTitle }</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="spacer"></div>
            <div class="news-list">
                <h4>新闻动态</h4>
                <ul>
                    <c:forEach items="${newsList }" var="news">
                        <li><a href="news/findNewsByTitle.do?title=${news.nTitle }">${news.nTitle }</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="spacer clear"></div>
        <div class="hot">
            <h2>热卖推荐</h2>
            <ul class="product clearfix" id="rmtj">
            </ul>
        </div>
    </div>
    <!-- 	<div class="clear"></div> -->
</div>
<div id="footer">
    <%@include file="foot.jsp" %>
</div>
</body>
</html>
