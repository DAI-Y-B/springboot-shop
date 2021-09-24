<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>留言回复</title>

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
        function checkBeforeCommit() {
            var orderId = $("#orderId").val();
            if (orderId == "") {
                alert("留言ID不能为空");
                return;
            }
            var name = $("#name").val();
            if (name == "") {
                alert("昵称不能为空");
                return;
            }
            var replyContent = $("#replyContent").val();
            if (replyContent == "") {
                alert("回复内容不能为空");
                return;
            }
            if (replyContent.length >= 100) {
                alert("回复内容不得超过100字");
                return;
            }
            $("form").submit();
        }

    </script>


    <script type="text/javascript">
        $(document).ready(function () {
            var str = window.location.href;
            var index = str.indexOf('?') + 1;
            str = str.substring(index);
            //var arr = str.split('&');
            var obj = str.split('=');
            //for(var i =0;i<arr.length;i++){
            //var str1 = arr[i].split('=')[0];
            //var str2 = arr[i].split('=')[1];
            //obj[str1] = str2;
            //}
            //alert(obj[1]);
            $.ajax({
                type: "POST",
                url: "comment/commentFind.do",
                async: false,
                data: {
                    id: obj[1]
                },
                success: function (msg) {
                    var comment = JSON.parse(msg);//将字符串转为json对象
                    $("#orderId").val(comment.id);
                    $("#name").val(comment.nickName);
                    //$("#oldContent").val(comment.content);
                    document.getElementById("oldContent").innerHTML = comment.content;
                    $("#replyContent").val(comment.reply);
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
        <h2>回复留言</h2>
        <div class="manage">
            <form action="comment/commentEdit.do" method="post">
                <table class="form">
                    <tr>
                        <td class="field">留言ID：</td>
                        <td><input type="text" class="text" name="orderId" id="orderId" value="100000"
                                   readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="field">留言姓名：</td>
                        <td><input type="text" class="text" name="name" id="name"/></td>
                    </tr>
                    <tr>
                        <td class="field">留言内容：</td>
                        <td><span id="oldContent"></span></td>
                    </tr>
                    <tr>
                        <td class="field">回复内容：</td>
                        <td><textarea name="replyContent" id="replyContent"></textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="button" name="button" value="更新"
                                                          onclick="checkBeforeCommit()"/></label></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    <%@include file="../foot.jsp" %>
</div>
<script type="text/javascript">
    function time() {
        var vWeek, vWeek_s, vDay;
        vWeek = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var date = new Date();
        year = date.getFullYear();
        month = date.getMonth() + 1;
        day = date.getDate();
        hours = date.getHours();
        minutes = date.getMinutes();
        seconds = date.getSeconds();
        vWeek_s = date.getDay();
        document.getElementById("time").innerHTML = year + "年" + month + "月" + day + "日" + "\t" + hours + ":" + minutes + ":" + seconds + "\t" + vWeek[vWeek_s];
        //document.getElementById("time").innerHTML = hours + ":" + minutes +":" + seconds;

    };
    setInterval("time()", 1000);

</script>
</body>
</html>
