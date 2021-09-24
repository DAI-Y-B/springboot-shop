<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商品管理</title>

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
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>


    <script type="text/javascript">

        function del(id) {
            var result = window.confirm("确认要删除吗?");
            if (true == result) {
                window.location.href = "product/productDelete.do?id=" + id;
            }
        }


        var currentPage;

        function searchPage(pageNum) {
            $.ajax({
                type: "POST",
                url: "product/queryProductByPage.do",
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
                    var array = pager.data;
                    var resultTable = $("#resultTable");
                    resultTable.html($("table tr")[0]);
                    for (var i = 0; i < array.length; i++) {
                        var tr = $("<tr></tr>");

                        var noTd = $("<td>" + array[i].id + "</td>");
                        noTd.attr('class', 'first w4 c');

                        var nameTd = $("<td></td>")
                        nameTd.attr('class', 'thumb');

                        var productImg = $("<img>");
                        productImg.attr('src', '/pic/' + array[i].fileName);
                        productImg.attr('style', "width:56;height=56;");
                        var productDetail = $("<a>" + array[i].name + "</a>");
                        productDetail.attr('href', '<%=basePath%>user/product-view.jsp?id=' + array[i].id);
                        productDetail.attr('target', '_blank');
                        productDetail.attr('id', 'detail');
                        nameTd.append(productImg).append(productDetail);

                        var stock = $("<td>" + array[i].stock + "</td>");
                        stock.attr('class', 'w1 c');


                        var operation = $("<td></td>");
                        operation.attr('class', 'w1 c');
                        var alterop = $("<a>" + '修改' + "</a>");
                        alterop.attr('href', "<%=basePath%>manage/product-modify.jsp?id=" + array[i].id);
                        alterop.attr('id', 'alter');

                        var delop = $("<a>" + '删除' + "</a>");
                        delop.attr('href', "javascript:;");
                        delop.attr('onclick', 'del(' + array[i].id + ')');
                        delop.attr('id', 'del');

                        operation.append(alterop).append(delop);
                        tr.append(noTd).append(nameTd).append(stock).append(operation);
                        resultTable.append(tr);
                    }
                    for (; i < 4; i++) {
                        var tr = $("<tr><td>&nbsp;</td><td></td><td></td></tr>");
                        resultTable.append(tr);
                    }

                    var trPage = $("<tr><td>&nbsp;</td><td align='center'><span>" + '第' + pageNum + '页' + "</span>&nbsp;&nbsp;&nbsp;<span>" + '共' + pager.totalPage + '页' + "</span></td><td>&nbsp;</td><td>&nbsp;</td></tr>");
                    resultTable.append(trPage);

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
    <div class="main">
        <h2>商品管理</h2>
        <div class="manage">
            <table class="list" id="resultTable">
                <tr>
                    <th>ID</th>
                    <th>商品名称</th>
                    <th>库存</th>
                    <th>操作</th>
                </tr>

                <%--
                <tr>
                    <td class="first w4 c">1</td>
                    <td class="thumb"><img src="<%=basePath%>images/product/0_tiny.gif" /><a href="<%=basePath%>manage/product-view.jsp" target="_blank">铁三角 Audio-Technica ATH-EQ300M-SV 银色 挂耳式耳机</a></td>
                    <td class="w1 c"><a href="<%=basePath %>manage/product-modify.jsp">修改</a> <a href="javascript:Delete(1);">删除</a></td>
                </tr>
                <tr>
                    <td class="first w4 c">1</td>
                    <td class="thumb"><img src="<%=basePath%>images/product/0_tiny.gif" /><a href="<%=basePath%>manage/product-view.jsp" target="_blank">铁三角 Audio-Technica ATH-EQ300M-SV 银色 挂耳式耳机</a></td>
                    <td class="w1 c"><a href="<%=basePath %>manage/product-modify.jsp">修改</a> <a href="javascript:Delete(1);">删除</a></td>
                </tr>
                 --%>


            </table>

            <div class="pager">
                <input type="hidden" id="pageNo">
                <input type="hidden" id="totalPage">
                <ul class="clearfix">
                    <li><a id="lastPage" href="javascript:;">上一页</a></li>
                    <li class="current"><a href="javascript:;" onclick="searchPage(1)">1</a></li>
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