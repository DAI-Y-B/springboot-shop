<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>注册中心</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript" src="<%=basePath%>scripts/function.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            ///window.location.href="/EB/newsQuery.do";
            $.ajax({
                type: "POST",
                url: "/EB/rootCategoryQuery.do",
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
            <h1>注册</h1>
            <ul class="steps clearfix">
                <li class="current"><em></em>填写注册信息</li>
                <li class="last"><em></em>注册成功</li>
            </ul>
            <form id="regForm" method="post" action="${pageContext.request.contextPath }/user/regist.do">
                <table>
                    <tr>
                        <td class="field">用户名：</td>
                        <td>
                            <input class="text" type="text" name="userName" id="userName" onfocus="FocusItem(this)"
                                   onblur="CheckItem1();"/>
                            ${resUserString}
                            <span id="span"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">登录密码：</td>
                        <td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">确认密码：</td>
                        <td><input class="text" type="password" name="rePassWord" id="rePassWord"
                                   onfocus="FocusItem(this)" onblur="CheckItem2();"/><span id="tip"></span></td>
                    </tr>
                    <tr>
                        <td class="field">姓名：</td>
                        <td><input type="text" class="text" name="euName" id="euName"/></td>
                    </tr>
                    <tr>
                        <td class="field">性别：</td>
                        <td>
                            <input type="radio" name="euSex" id="euSex" value="男" checked="checked"/>男
                            <input type="radio" name="euSex" id="euSex" value="女"/>女
                        </td>
                    </tr>
                    <tr>
                        <td class="field">身份证：</td>
                        <td><input type="text" class="text" name="euIdentityCode" id="euIdentityCode"
                                   onfocus="FocusItem(this)" onblur="CheckItem3(this);"/></td>
                    </tr>
                    <tr>
                        <td class="field">手机号码：</td>
                        <td><input type="text" class="text" name="euMobile" id="euMobile" onfocus="FocusItem(this)"
                                   onblur="CheckItem4(this);"/></td>
                    </tr>
                    <tr>
                        <td class="field">邮箱：</td>
                        <td><input type="text" class="text" name="euEmail" id="euEmail" onfocus="FocusItem(this)"
                                   onblur="CheckItem5(this);"/></td>
                    </tr>
                    <tr>
                        <td class="field">送货地址：</td>
                        <td><input type="text" class="text" name="euAddress" id="euAddress" onfocus="FocusItem(this)"
                                   onblur="CheckItem6(this);"/></td>
                    </tr>
                    <tr>
                        <td class="field">验证：</td>
                        <td>
                            <input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)"
                                   onclick="changeImg();"/>
                            ${m}*${n}=?&nbsp;&nbsp;${remind}
                            <span></span>
                        </td>
                    </tr>
                    <!-- <tr>
                        <td class="field">类型：</td>
                        <td><input class="text" type="text" name="status" onfocus="FocusItem(this)" onblur="CheckItem(this);" value="1为普通用户，2为管理员"/><span></span></td>
                    </tr> -->
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" name="submit" value="提交注册"/></label></td>
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
    //   		$(function(){
    // 			$("#userName").blur(function(){//失去焦点
    function CheckItem1() {
        var userName = $("#userName").val();//获得输入框中的值
        if (userName == null || userName.trim().length == 0) {
            alert("用户名不能为空");
            return;
        }
        <%-- 	$.ajax({
                type:"get",
                url:"<%=basePath%>findUByUserName.do?en_user_name="+encodeURI(encodeURI(userName)),
                dataType:"json",
                success:function(userName){
                    if(typeof(en_user_name) == null ){
                        return;
                    }
                        alert("用户名重复，请重新输入");
                        return;
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
        var rePassWord = $("#rePassWord").val();
        if (rePassWord == null || rePassWord.trim().length == 0) {
            alert("确认密码不能为空");
            return;
        }
        if (rePassWord != "" && rePassWord != null) {
            if (rePassWord != passWord) {
                alert("两次输入的密码不相同，请重新输入");
                return false;
            }
        }
    }

    function CheckItem3() {
        var euIdentityCode = $("#euIdentityCode").val();//获得输入框中的值
        if (euIdentityCode == null || euIdentityCode.trim().length == 0) {
            alert("身份证不能为空");
            return;
        }
    }

    function CheckItem4() {
        var euMobile = $("#euMobile").val();//获得输入框中的值
        if (euMobile == null || euMobile.trim().length == 0) {
            alert("手机号不能为空");
            return;
        }
    }

    function CheckItem5() {
        var euEmail = $("#euEmail").val();//获得输入框中的值
        if (euEmail == null || euEmail.trim().length == 0) {
            alert("邮箱不能为空");
            return;
        }
    }

    function CheckItem6() {
        var euAddress = $("#euAddress").val();//获得输入框中的值
        if (euAddress == null || euAddress.trim().length == 0) {
            alert("地址不能为空");
            return;
        }
    }

</script>
</html>
