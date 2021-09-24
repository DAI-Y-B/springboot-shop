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

    <title>您的购物车</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>

    <script type="text/javascript">
        //setTimeout(function(){location.reload()},10000); //指定1秒刷新一次
        $(document).ready(function () {
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
        });
    </script>
    <script type="text/javascript">
        function productInfo() {

            /*  obj = document.getElementsByName("carinfo");
             check_val = [];
             for(k in obj){
                 if(obj[k].checked)
                     check_val.push(obj[k].value);
             }
                alert(check_val); */
            var bb = "";
            var temp = "";
            var a = document.getElementsByName("carinfo");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked) {
                    temp = a[i].value;
                    bb = bb + "," + temp;
                }
            }
            window.location.href = "order/buyProduct.do?bb=" + bb;
        }

        function addPrice(num, productId) {
            window.location.href = "order/updateCount.do?num=" + num + "&productId=" + productId;
        }

        function reducePrice(num, productId) {
            window.location.href = "order/updateCount.do?num=" + num + "&productId=" + productId;
        }

        function delShopping(productId) {
            var result = window.confirm("确认要删除吗?");
            if (true == result) {
                window.location.href = "order/deleteProduct.do?productId=" + productId;
            }
        }
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
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 购物车
</div>
<div class="wrap">
    <div id="shopping">
        <form action="">
            <table id="resultTable">
                <tr>
                    <th>商品名称</th>
                    <th>商品价格</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${userCars }" var="userCar">

                    <tr id="product_id_1">

                        <td class="thumb">
                            <input type="checkbox" name="carinfo" id="carinfo" value="${userCar.productId}"/>
                            <img src="/pic/${userCar.products.fileName} "/>
                            <a href="user/product-view.jsp?id=${userCar.productId}">${userCar.products.name }</a>
                        </td>
                        <td class="price" id="price_id_1">
                            <span>${userCar.products.price }</span>
                            <input type="hidden" value="99"/>
                        </td>
                        <td class="number">
                            <dl>
                                <dd onclick="reducePrice('2','${userCar.productId}');"
                                    style="width: 15px ; height: 18px;">-
                                </dd>
                                <dt><input id="number_id_1" type="text" name="number" value="${userCar.productCount }"/>
                                </dt>
                                <dd onclick="addPrice('1','${userCar.productId}');" style="width: 15px ; height: 18px;">
                                    +
                                </dd>
                            </dl>
                        </td>
                        <td class="delete"><a href="javascript:delShopping('${userCar.productId}');">删除</a></td>
                    </tr>

                </c:forEach>

            </table>
            <div class="button"><input type="button" value="" onclick="productInfo()"/></div>
        </form>
    </div>

</div>

<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
