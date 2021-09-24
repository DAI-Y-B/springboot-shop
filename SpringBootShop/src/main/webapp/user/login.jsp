<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户登陆中心</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript" src="<%=basePath%>scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
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
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>登陆</h1>
            <form id="loginForm" method="post" action="${pageContext.request.contextPath }/user/login.do">
                <table>
                    <tr>
                        <td class="field">用户名：</td>
                        <td><input class="text" type="text" name="userName" id="userName" onfocus="FocusItem(this)"
                                   onblur="CheckItem1();"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">登录密码：</td>
                        <td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)"
                                   onblur="CheckItem2();"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">验证码：</td>
                        <td>
                            <input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/>
                            ${m1}+${n1}=?&nbsp;&nbsp;${remind1}
                            <span></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" name="submit" value="立即登录"/></label></td>
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
</body>

<script type="text/javascript">
    function changeImg() {
        //图片预加载，前端技术
        $("#veryCode").attr("src", "<%=basePath%>getCodeImg.do?t=" + Math.random());//还是异步
    }

    function CheckItem1() {
        //var tip = $("#tip");
        var userName = $("#userName").val();
        if (userName == null || userName.trim().length == 0) {
            alert("用户名不能为空");
            return;
        }
        <%-- $.ajax({
            type:"get",
            url:"<%=basePath%>findUByUserName.do?en_user_name="+encodeURI(encodeURI(userName)),
            dataType:"json",
            success:function(userName){
                if(typeof(en_user_name) =="undefined"){
                        //alert("用户名存在");
                        return;
                    }
                      alert("用户名存在");
                },
            error:function(XMLHttpRequest, textStatus, errorThrown) {
                //这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
                alert(XMLHttpRequest.responseText);
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus); // parser error;
                }
        }); --%>
    }

    function CheckItem2() {
        var passWord = $("#passWord").val();
        if (passWord == null || passWord.trim().length == 0) {
            alert("登录密码不能为空");
            return;
        }
    }
</script>
</html>
