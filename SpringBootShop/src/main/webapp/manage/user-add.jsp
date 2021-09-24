<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>添加用户</title>

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
        <h2>新增用户</h2>
        <div class="manage">
            <form action="<%=basePath %>manage/addUserByAdmin.do" method="post">
                <table class="form">
                    <tr>
                        <td class="field">用户名：</td>
                        <td>
                            <input type="text" class="text" name="userName" id="userName" onfocus="FocusItem(this)"
                                   onblur="CheckItem1();"/>
                            ${resUserString}
                        </td>
                    </tr>
                    <tr>
                        <td class="field">姓名：</td>
                        <td><input type="text" class="text" name="euName" id="euName" onfocus="FocusItem(this)"
                                   onblur="CheckItem2();"/></td>
                    </tr>
                    <tr>
                        <td class="field">密码：</td>
                        <td><input type="text" class="text" name="passWord" id="passWord" onfocus="FocusItem(this)"
                                   onblur="CheckItem3();"/></td>
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
                                   onfocus="FocusItem(this)" onblur="CheckItem4();"/></td>
                    </tr>
                    <tr>
                        <td class="field">手机号码：</td>
                        <td><input type="text" class="text" name="euMobile" id="euMobile" onfocus="FocusItem(this)"
                                   onblur="CheckItem5();"/></td>
                    </tr>
                    <tr>
                        <td class="field">邮箱：</td>
                        <td><input type="text" class="text" name="euEmail" id="euEmail" onfocus="FocusItem(this)"
                                   onblur="CheckItem6();"/></td>
                    </tr>
                    <tr>
                        <td class="field">送货地址：</td>
                        <td><input type="text" class="text" name="euAddress" id="euAddress" onfocus="FocusItem(this)"
                                   onblur="CheckItem7();"/></td>
                    </tr>
                    <tr>
                        <td class="field">用户类型：</td>
                        <td>
                            <select id="euStatus" name="euStatus">
                                <option value="5">普通会员</option>

                                <c:forEach var="roleInfo" items="${roleInfo}">
                                    <option value="${roleInfo.roleId}">${roleInfo.roleName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <!-- 	<tr>
                            <td class="field">头像：</td>
                            <td><input type="file" class="text" name="photo" /></td>
                        </tr> -->
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="submit" name="submit" value="添加"/></label></td>
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
<script type="text/javascript" src="/EB/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/EB/scripts/function.js"></script>
<script type="text/javascript">
    //   		$(function(){
    // 			$("#userName").blur(function(){//失去焦点
    function CheckItem1() {
        var userName = $("#userName").val();//获得输入框中的值
        if (userName == null || userName.trim().length == 0) {
            alert("用户名不能为空");
            return;
        }
    }

    function CheckItem2() {
        var euName = $("#euName").val();//获得输入框中的值
        if (euName == null || euName.trim().length == 0) {
            alert("地址不能为空");
            return;
        }
    }

    function CheckItem3() {
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

    function CheckItem4() {
        var euIdentityCode = $("#euIdentityCode").val();//获得输入框中的值
        if (euIdentityCode == null || euIdentityCode.trim().length == 0) {
            alert("身份证不能为空");
            return;
        }
    }

    function CheckItem5() {
        var euMobile = $("#euMobile").val();//获得输入框中的值
        if (euMobile == null || euMobile.trim().length == 0) {
            alert("手机号不能为空");
            return;
        }
    }

    function CheckItem6() {
        var euEmail = $("#euEmail").val();//获得输入框中的值
        if (euEmail == null || euEmail.trim().length == 0) {
            alert("邮箱不能为空");
            return;
        }
    }

    function CheckItem7() {
        var euAddress = $("#euAddress").val();//获得输入框中的值
        if (euAddress == null || euAddress.trim().length == 0) {
            alert("地址不能为空");
            return;
        }
    }

</script>
</html>