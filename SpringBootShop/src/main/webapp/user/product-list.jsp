<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商品分类</title>

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
        function searchPage(pageNum) {
            var str = window.location.href;
            var index = str.indexOf('?') + 1;
            str = str.substring(index);
            var obj = str.split('=');
            $.ajax({
                type: "POST",
                url: "product/getProductByPage.do",
                async: false,
                data: {
                    id: obj[1],
                    pageNum: pageNum
                },
                success: function (msg) {
                    var pager = JSON.parse(msg);//将字符串转为json对象
                    var array = pager.data;
                    if (array.length != 0) {
                        if (pager.totalPage < pageNum) {
                            alert("共有" + pager.totalPage + "页，您所要查看的页不存在，已为您跳转到最后一页，第" + pager.pageNum + "页");
                        }
                        if (pageNum < 1) {
                            alert("已经是第1页了");
                        }
                    }
                    var lastPage = $("#lastPage");
                    lastPage.attr('onclick', 'searchPage(' + (pager.pageNum - 1) + ')');
                    var endPage = $("#endPage");
                    endPage.attr('onclick', 'searchPage(' + (pager.pageNum + 1) + ')');
                    var lastPage1 = $("#lastPage1");
                    lastPage1.attr('onclick', 'searchPage(' + (pager.pageNum - 1) + ')');
                    var endPage1 = $("#endPage1");
                    endPage1.attr('onclick', 'searchPage(' + (pager.pageNum + 1) + ')');

                    if (array.length == 0) {
                        $("#buttonUL1").html("");
                        $("#buttonUL2").html("");
                        $("#main").html("");
                        confirm("该分类下无商品，返回上一页");
                        window.history.go(-1);
                    }
                    var productMes = $("#productMes");
                    productMes.html("");
                    //document.getElementById("commentMes").innerHTML="";
                    for (var i = 0; i < array.length; i++) {
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
                        //productDetail1.attr('onclick','location.reload()');
                        productDetail1.attr('target', '_blank');
                        productDetail1.attr('id', 'detail1');
                        dd.append(productDetail1);

                        var dd1 = $("<dd class='price' >" + "￥" + array[i].price + "</dd>");
                        dl.append(dt).append(dd).append(dd1);
                        li.append(dl);
                        productMes.append(li);
                    }
                }

            });

        }


        $(document).ready(function () {
            searchPage(1);
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

            <%-- 	$.ajax({
                      type:"POST",
                      url:"/EB/cookieRecord.do",
                      async:true,
                      success:function(msg){
                      if(msg.length!=0){
                      var array = JSON.parse(msg);//将字符串转为json对象
                      var div = $("#lastFive");
                      div.attr('class','last-view');
                      var h2 = $("<h2>"+"最近浏览"+"</h2>");
                      div.append(h2);
                      var dl = $("<dl><dl>");
                      dl.attr('class','clearfix');
                      for(var  i=0;i<array.length;i++){
                          var dt = $("<dt></dt>");
                          var img = $("<img/>");
                          img.attr('src','/pic/'+array[i].fileName);
                          dt.append(img);
                          dl.append(dt);
                          var dd = $("<dd></dd>");
                          var a = $("<a>"+array[i].name+"</a>");
                          a.attr('href','<%=basePath%>user/product-view.jsp?id='+array[i].id);
                          a.attr('onclick','location.reload()');
                          dd.append(a);
                          dl.append(dd);
                      }
                      div.append(dl);
                      }
                  }
           });--%>


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
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 商品分类
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
        <div class="product-list">
            <h2>全部商品</h2>
            <input type="hidden" id="pageNo">
            <input type="hidden" id="totalPage">
            <div class="pager">
                <ul class="clearfix" id="buttonUL1">
                    <li><a id="lastPage" href="javascript:;">上一页</a></li>
                    <li class="current"><a href="javascript:;" onclick="searchPage(2)">1</a></li>
                    <li><a href="javascript:;" onclick="searchPage(2)">2</a></li>
                    <li><a href="javascript:;" onclick="searchPage(3)">3</a></li>
                    <li><a href="javascript:;" onclick="searchPage(4)">4</a></li>
                    <li><a href="javascript:;" onclick="searchPage(5)">5</a></li>
                    <li><a id="endPage" href="javascript:;">下一页</a></li>
                </ul>
            </div>
            <div class="clear"></div>
            <ul class="product clearfix" id="productMes">
            </ul>
            <div class="clear"></div>
            <div class="pager">
                <ul class="clearfix" id="buttonUL2">
                    <li><a id="lastPage1" href="javascript:;">上一页</a></li>
                    <li class="current"><a href="javascript:;" onclick="searchPage(2)">1</a></li>
                    <li><a href="javascript:;" onclick="searchPage(2)">2</a></li>
                    <li><a href="javascript:;" onclick="searchPage(3)">3</a></li>
                    <li><a href="javascript:;" onclick="searchPage(4)">4</a></li>
                    <li><a href="javascript:;" onclick="searchPage(5)">5</a></li>
                    <li><a id="endPage1" href="javascript:;">下一页</a></li>
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
</html>
