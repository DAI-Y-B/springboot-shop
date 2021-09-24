<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>留言中心</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Refresh" content="30" ;/>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <link type="text/css" rel="stylesheet" href="<%=basePath %>css/style.css"/>
    <script type="text/javascript" src="<%=basePath %>scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>

    <script type="text/javascript">
        function checkBeforeCommit() {
            var guestName = $("#guestName").val();
            if (guestName == "") {
                alert("昵称不能为空");
                return;
            }
            var guestTitle = $("#guestTitle").val();
            if (guestTitle == "") {
                alert("标题不能为空");
                return;
            }
            var guestContent = $("#guestContent").val();
            if (guestContent == "") {
                alert("内容不能为空");
                return;
            }
            if (guestContent.length >= 100) {
                alert("回复内容不得超过100字");
                return;
            }
            $("form").submit();
        }
    </script>
    <script type="text/javascript">
        setInterval(searchPage(1), 10000);

        var currentPage;

        function searchPage(pageNum) {
            $.ajax({
                type: "POST",
                url: "comment/getCommentByPage.do",
                data: {
                    pageNum: pageNum
                },
                async: true,
                success: function (msg) {
                    var pager = $.parseJSON(msg);
                    currentPage = pager;
                    document.getElementById("pageNo").value = pager.pageNum;
                    document.getElementById("totalPage").value = pager.totalPage;
                    if (pager.totalPage < pageNum) {
                        alert("共有" + pager.totalPage + "页，您所要查看的页不存在，已为您跳转到最后一页，第" + pager.pageNum + "页");
                        pageNum = pager.pageNum;
                    }
                    if (pageNum < 1) {
                        alert("已经是第1页了");
                        pageNum = 1;
                    }
                    var lastPage = $("#lastPage");
                    lastPage.attr('onclick', 'searchPage(' + (pager.pageNum - 1) + ')');
                    var endPage = $("#endPage");
                    endPage.attr('onclick', 'searchPage(' + (pager.pageNum + 1) + ')');
                    var array = pager.data;
                    var commentMes = $("#commentMes");
                    commentMes.html("");
                    //document.getElementById("commentMes").innerHTML="";
                    for (var i = 0; i < array.length; i++) {
                        var li = $("<li></li>");
                        var dl = $("<dl></dl>");
                        var dt = $("<dt>" + array[i].content + "</dt>");
                        var dd = $("<dd class='author'" + ">" + "网友:" + array[i].nickName + "</dd>")
                        var dd1 = $("<dd>" + array[i].reply + "</dd>");
                        dl.append(dt).append(dd).append(dd1);
                        li.append(dl);
                        commentMes.append(li);
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
                    var topUL = $("#topUL");
                    topUL.html("");
                    //<li class="first"><a href="#">音乐</a></li>

                    //<li><a href="#">影视</a></li>
                    var h2 = $("<h2>" + "商品分类" + "</h2>");
                    spfl.append(h2);
                    for (var i = 0; i < array.length; i++) {
                        var dl = $("<dl></dl>");
                        var dt = $("<dt>" + array[i].cName + "</dt>");
                        dl.append(dt);
                        var li = $("<li></li>");
                        if (i == 0) {
                            li.attr('class', 'first');
                        }
                        var a = $("<a>" + array[i].cName + "</a>");
                        a.attr('href', '<%=basePath%>product-list.jsp?cId=' + array[i].cId);
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
                                    a.attr('href', '<%=basePath%>product-list.jsp?cId=' + array1[j].cId);
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
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="images/logo.gif"/></div>
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
    您现在的位置：<a href="middle/middleMainJsp.do">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
    <div class="lefter">
        <div class="box" id="spfl">
        </div>
    </div>
    <div class="main">
        <div class="guestbook">
            <h2>全部留言</h2>
            <ul id="commentMes">
            </ul>
            <div class="clear"></div>
            <input type="hidden" id="pageNo">
            <input type="hidden" id="totalPage">
            <div class="pager">
                <ul class="clearfix">
                    <li><a id="lastPage" href="javascript:;">上一页</a></li>
                    <li class="current"><a href="javascript:;" onclick="searchPage(2)">1</a></li>
                    <li><a href="javascript:;" onclick="searchPage(2)">2</a></li>
                    <li><a href="javascript:;" onclick="searchPage(3)">3</a></li>
                    <li><a href="javascript:;" onclick="searchPage(4)">4</a></li>
                    <li><a href="javascript:;" onclick="searchPage(5)">5</a></li>
                    <li><a id="endPage" href="javascript:;">下一页</a></li>
                </ul>
            </div>
            <div id="reply-box">
                <form action="comment/commentAdd.do" method="post">
                    <table>
                        <tr>
                            <td class="field">昵称：</td>
                            <td><input class="text" type="text" name="guestName" id="guestName"/></td>
                        </tr>
                        <tr>
                            <td class="field">留言标题：</td>
                            <td><input class="text" type="text" name="guestTitle" id="guestTitle"/></td>
                        </tr>
                        <tr>
                            <td class="field">留言内容：</td>
                            <td><textarea name="guestContent" id="guestContent"></textarea></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><label class="ui-blue"><input type="button" name="button" value="提交留言"
                                                              onclick="checkBeforeCommit()"/></label></td>
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
