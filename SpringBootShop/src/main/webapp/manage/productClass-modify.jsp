<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>商品类型修改</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/style.css"/>
    <script type="text/javascript"
            src="<%=basePath%>scripts/function-manage.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "product/rootCategoryQuery.do",
                async: false,
                success: function (msg) {
                    var array = eval(msg);
                    console.log(array);
                    var parentId = $("#parentId");
                    //var opj = "";
                    for (var i = 0; i < array.length; i++) {
                        var option = $("<option>" + array[i].cName + "</option>");
                        var a = array[i].cId;
                        option.attr('value', a);
                        if (array[i].cParentId == array[i].cId) {
                            option.attr('selected', 'selected');
                            //opj+="<option value ='"+array[i].cId+"' selected='selected'>"+array[i].cName+"</option>";
                        }
                        //opj+="<option value ='"+array[i].cId+"'>"+array[i].cName+"</option>";
                        parentId.append(option);
                    }
                    //parentId.append(opj);
                }
            });

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
                url: "product/productCategoryFind.do",
                async: false,
                data: {
                    cId: obj[1]
                },
                success: function (msg) {
                    var productCategory = JSON.parse(msg); //将字符串转为json对象
                    alert(productCategory.cParentId);
                    $("#parentId").val('productCategory.cParentId');
                    $("#className").val(productCategory.cName);
                    $("#cId").val(productCategory.cId);

                    var count = $("#parentId option").length;
                    var tf = productCategory.cParentId; //后端传值
                    for (var i = 0; i < count; i++) {
                        if ($("#parentId ").get(0).options[i].value == tf) { //获取select 的value值和入参比较
                            $("#parentId ").get(0).options[i].selected = true;
                            break;
                        }
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
        <h2>修改分类</h2>
        <div class="manage">
            <form action="product/productCategoryEdit.do" method="post">
                <input type="hidden" id="cId" name="cId">
                <table class="form">
                    <tr>
                        <td class="field">父分类：</td>
                        <td><select name="parentId" id="parentId">
                            <option value="0">根栏目</option>
                            <%--
                                        <option value="1">电器</option>
                                        <option value="2">衣服</option>
                                        <option value="4">显示屏</option>
                         --%>
                        </select></td>
                    </tr>
                    <tr>
                        <td class="field">分类名称：</td>
                        <td><input type="text" class="text" name="className"
                                   id="className"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="submit"
                                                          name="submit" value="更新"/></label></td>
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
</html>