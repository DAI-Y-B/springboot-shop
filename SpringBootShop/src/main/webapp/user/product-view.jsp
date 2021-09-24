<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商品展示</title>

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
        $(document).ready(function () {
            var str = window.location.href;
            var index = str.indexOf('?') + 1;
            str = str.substring(index);
            var obj = str.split('=');
            $.ajax({
                type: "POST",
                url: "product/productFind.do",
                async: true,
                data: {
                    "id": obj[1]
                },
                success: function (msg) {
                    //window.opener.location.reload();
                    var product = JSON.parse(msg);//将字符串转为json对象
                    var mainDiv = $("#main");
                    var productDiv = $("#product");
                    //$("#productDiv").trigger("create");
                    var name = $("<h1>" + product.name + "</h1>");
                    var hiddeninput = $("<input type='hidden' id='productName' value='" + product.name + "' />");
                    productDiv.append(name).append(hiddeninput);

                    var sty = $("<div></div>");
                    sty.attr('id', 'sty');
                    sty.attr('class', 'infos');
                    var imgDiv = $("<div></div>");
                    imgDiv.attr('id', 'imgDiv');
                    imgDiv.attr('class', 'thumb');
                    var img = $("<img/>");
                    img.attr('src', '/pic/' + product.fileName);
                    img.attr('id', 'productImg');
                    imgDiv.append(img);
                    sty.append(imgDiv);
                    $("#imgDiv").trigger("create");

                    var buyDiv = $("<div></div>");
                    buyDiv.attr('class', 'buy');
                    var p1 = $("<p>" + "商场价:" + "<span class='price' " + ">" + product.price + "</span>" + "</p>");
                    var hiddeninput1 = $("<input type='hidden' id='price' value='" + product.price + "' />");
                    buyDiv.append(p1).append(hiddeninput1);
                    var p4 = $("<p>" + "商品ID:" + product.id + "</p>");
                    p4.attr('id', 'productId');
                    buyDiv.append(p4);
                    var p2 = $("<p>" + "品牌:" + product.brand + "</p>");
                    buyDiv.append(p2);
                    var p3 = $("<p>" + "库存:" + product.stock + "</p>");
                    buyDiv.append(p3);
                    var p5 = $("<p>" + "商品码:" + product.barcode + "</p>");
                    buyDiv.append(p5);


                    var buyButton = $("<div class='button' id='buyButton' >" +
                        "<input type='button' onclick='goBuy2(" + product.id + "," + product.price + ")' id='button' name='button' " + "/>" +
                        "<div>");

                    var a = $("<a>" + "放入购物车" + "</a>");
                    a.attr('href', 'javascript:;');
                    a.attr('onclick', 'goBuy1(' + product.id + "," + product.price + ')');
                    a.attr('id', 'carA');
                    buyButton.append(a);
                    buyDiv.append(buyButton);


                    sty.append(buyDiv);
                    $("#buyButton").trigger("create");
                    var clearDiv = $("<div id='clearDiv' class='clear'>" + "</div>");
                    sty.append(clearDiv);
                    productDiv.append(sty);
                    $("#sty").trigger("create");
                    var introduce = $("<div></div>");
                    var h2 = $("<h2>" + "<strong>" + "商品详情:" + "</strong>" + "</h2>");
                    introduce.append(h2);
                    var detail = $("<div class='text' id='detail' " + ">" + product.description + "</div>");
                    introduce.append(detail);
                    productDiv.append(introduce);
                    mainDiv.append(productDiv);
                    $("#productDiv").trigger("create");
                    var clearDiv2 = $("<div id='clearDiv2' class='clear' " + "></div>");
                    mainDiv.append(clearDiv2);
                    $("#main").trigger("create");
                }
            });


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
    <script type="text/javascript">
        function goBuy2(id, price) {
            window.location.href = "order/showOneProduct.do?productId=" + id;
        }

        function goBuy1(id, price) {
            window.location.href = "order/userAddCar.do?productId=" + id;
            var rs = "<%=session.getAttribute("rs")%>";
            alert(rs);

        }
    </script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo">
        <img src="images/logo1.png"/>
    </div>
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
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 商品展示
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <div class="box" id="spfl">
        </div>
    </div>
    <div id="product" class="main">

    </div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
