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

    <title>留言管理</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link type="text/css" rel="stylesheet" href="<%=basePath %>css/style.css"/>
    <script type="text/javascript" src="<%=basePath %>scripts/function-manage.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        function del(id) {
            var result = window.confirm("确认要删除吗?");
            if (true == result) {
                window.location.href = "comment/commentDel.do?id=" + id;
            }
        }


        var currentPage;

        function searchPage(pageNum) {
            $.ajax({
                type: "POST",
                url: "comment/getCommentByPage.do",
                data: {
                    "pageNum": pageNum,
                },
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
                    var datas = pager.data;
                    var resultTable = $("#resultTable");
                    resultTable.html($("table tr")[0]);
                    for (var i = 0; i < datas.length; i++) {
                        var tr = $("<tr></tr>");
                        var noTd = $("<td>" + datas[i].id + "</td>")
                        noTd.attr('class', 'first w4 c');
                        var nameTd = $("<td>" + datas[i].nickName + "</td>");
                        nameTd.attr('class', 'w1 c');
                        var contentTd = $("<td>" + datas[i].content + "</td>");
                        if (datas[i].reply == null || datas[i].reply == "") {
                            var replyOrNotTd = $("<td>" + "未回复" + "</td>");
                        } else {
                            var replyOrNotTd = $("<td>" + "已回复" + "</td>");
                        }
                        var operation = $("<td></td>");
                        operation.attr('class', 'w1 c');
                        var reply = $("<a>" + "回复" + "</a>");
                        reply.attr('href', "<%=basePath%>manage/guestbook-modify.jsp?id=" + datas[i].id);
                        reply.attr('id', 'replya');
                        var del = $("<a>" + "删除" + "</a>");
                        del.attr('href', "javascript:;");
                        del.attr('onclick', 'del(' + datas[i].id + ')');
                        del.attr('id', 'dela');
                        operation.append(reply).append(del);

                        tr.append(noTd).append(nameTd).append(contentTd).append(replyOrNotTd).append(operation);
                        resultTable.append(tr);
                    }
                    for (; i < 4; i++) {
                        var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td></tr>");
                        resultTable.append(tr);
                    }
                }
            });
        }

        $(document).ready(function () {
            searchPage(1);
        });

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
    <div class="main" id="main">
        <h2>留言管理</h2>
        <div class="manage">
            <table class="list" id="resultTable">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>留言内容</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </table>
            <div class="pager">
                <input type="hidden" id="pageNo">
                <input type="hidden" id="totalPage">
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
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
</body>
</html>
