<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商品分类显示</title>

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
                window.location.href = "product/productCategoryDelete.do?id=" + id;
            }
        }


        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "product/rootCategoryQuery.do",
                async: false,
                success: function (msg) {
                    var array = eval(msg);
                    var resultTable = $("#resultTable");
                    resultTable.html($("table tr")[0]);
                    for (var i = 0; i < array.length; i++) {
                        var tr = $("<tr></tr>");
                        var noTd = $("<td>" + array[i].cId + "</td>");
                        console.log(array[i].cId);
                        noTd.attr('class', 'first w4 c');
                        var nameTd = $("<td>" + array[i].cName + "</td>")
                        console.log(array[i].cName);
                        var operation = $("<td></td>");
                        operation.attr('class', 'w1 c');
                        var alterop = $("<a>" + '修改' + "</a>");
                        alterop.attr('href', "<%=basePath%>manage/productClass-modify.jsp?cId=" + array[i].cId);
                        alterop.attr('id', 'alter');

                        var delop = $("<a>" + '删除' + "</a>");
                        delop.attr('href', "javascript:;");
                        delop.attr('onclick', 'del(' + array[i].cId + ')');
                        delop.attr('id', 'del');

                        operation.append(alterop).append(delop);
                        tr.append(noTd).append(nameTd).append(operation);
                        resultTable.append(tr);

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
                                    var tr = $("<tr></tr>");
                                    var noTd = $("<td>" + array1[j].cId + "</td>");
                                    noTd.attr('class', 'first w4 c');
                                    console.log(array1[j].cId);
                                    var nameTd = $("<td>" + array1[j].cName + "</td>");
                                    nameTd.attr('class', 'childClass');
                                    console.log(array1[j].cName);
                                    var operation = $("<td></td>");
                                    operation.attr('class', 'w1 c');
                                    var alterop = $("<a>" + '修改' + "</a>");
                                    alterop.attr('href', "<%=basePath%>manage/productClass-modify.jsp?cId=" + array1[j].cId);
                                    alterop.attr('id', 'alter');

                                    var delop = $("<a>" + '删除' + "</a>");
                                    delop.attr('href', "javascript:;");
                                    delop.attr('onclick', 'del(' + array1[j].cId + ')');
                                    delop.attr('id', 'del');

                                    operation.append(alterop).append(delop);
                                    tr.append(noTd).append(nameTd).append(operation);
                                    resultTable.append(tr);
                                }
                            }
                        });
                    }
                }
            });
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
        <h2>分类管理</h2>
        <div class="manage" style="overflow:scroll; width:700px; height:400px; ">
            <table class="list" id="resultTable">
                <tr>
                    <th>ID</th>
                    <th>分类名称</th>
                    <th>操作</th>
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
</html>
