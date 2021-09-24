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
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "product/rootCategoryQuery.do",
                async: false,
                success: function (msg) {
                    var array = eval(msg);
                    var parentId = $("#parentId");
                    for (var i = 0; i < array.length; i++) {
                        var option = $("<option>" + array[i].cName + "</option>");
                        var a = array[i].cId;
                        option.attr('value', a);
                        option.attr('disabled', 'disabled');
                        parentId.append(option);
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
                                    if (j == array1.length - 1) {
                                        var option = $("<option>" + "└" + array1[j].cName + "</option>");
                                    } else {
                                        var option = $("<option>" + "├" + array1[j].cName + "</option>");
                                    }
                                    var a = array1[j].cId;
                                    option.attr('value', a);
                                    parentId.append(option);
                                }
                            }
                        });
                    }
                }
            });
        });

        function checkBeforeCommit() {
            var productName = $("#productName").val();
            if (productName == "") {
                alert("商品名不能为空");
                return;
            }
            var parentId = $("#parentId").val();
            if (parentId == "") {
                alert("类别不能为空");
                return;
            }
            var photo = $("#photo").val();
            if (photo == "") {
                alert("商品图片不能为空");
                return;
            }
            var productPrice = $("#productPrice").val();
            if (productPrice == "") {
                alert("商品价格不能为空");
                return;
            }
            //0-2为小数位的数
            var priceRegx = /^[0-9]+(.[0-9]{0,2})?$/;
            if (!priceRegx.test(productPrice)) {
                alert("价格格式错误，应为小数位两位以内的数");
                return false;
            }
            var productStock = $("#productStock").val();
            if (productStock == "") {
                alert("库存不能为空");
                return;
            }
            //非零正整数
            var stockRegx = /^\+?[1-9][0-9]*$/;
            if (!stockRegx.test(productStock)) {
                alert("库存应为非零正整数");
                return false;
            }
            var productBrand = $("#productBrand").val();
            if (productBrand == "") {
                alert("商品品牌不能为空");
                return;
            }
            var productBarcode = $("#productBarcode").val();
            if (productBarcode == "") {
                alert("商品码不能为空");
                return;
            }
            var barcodeRegx = /^\d{13}$/;
            if (!barcodeRegx.test(productBarcode)) {
                alert("条形码应是13位的数字");
                return false;
            }
            var productDes = $("#productDes").val();
            if (productDes == "") {
                alert("商品描述不能为空");
                return;
            }
            if (productDes.length >= 100) {
                alert("商品描述不得超过100字");
                return;
            }
            $("form").submit();
        }

        window.onload = function () {
            document.getElementById("productName").onblur = function () {//$("#deptNo")
                var productName = document.getElementById("productName").value;//var deptNo = $(this).val();
                $.ajax({
                    type: "POST",
                    url: "product/queryProductByName.do",
                    async: true,
                    data: {
                        "productName": productName
                    },
                    success: function (msg) {
                        if (msg.length != 0) {
                            var product = JSON.parse(msg);
                            if (product.name != null) {
                                alert("该商品名已存在,请重新输入");
                            }
                        }
                    }

                });

            }
        };


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
        <h2>添加商品</h2>
        <div class="manage">
            <form action="product/productAdd.do" method="post" enctype="multipart/form-data">
                <table class="form">
                    <tr>
                        <td class="field">商品名称：</td>
                        <td><input type="text" class="text" name="productName" id="productName"/></td>
                    </tr>
                    <tr>
                        <td class="field">所属分类：</td>
                        <td>
                            <select name="parentId" id="parentId">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">商品图片：</td>
                        <td><input type="file" class="text" name="photo" id="photo"/></td>
                    </tr>
                    <tr>
                        <td class="field">商品价格：</td>
                        <td><input type="text" class="text tiny" name="productPrice" id="productPrice"/> 元</td>
                    </tr>
                    <tr>
                        <td class="field">品牌：</td>
                        <td><input type="text" class="text" name="productBrand" id="productBrand"/></td>
                    </tr>
                    <tr>
                        <td class="field">库存：</td>
                        <td><input type="text" class="text tiny" name="productStock" id="productStock"/></td>
                    </tr>
                    <tr>
                        <td class="field">条码号：</td>
                        <td><input type="text" class="text" name="productBarcode" id="productBarcode"/></td>
                    </tr>
                    <tr>
                        <td class="field">商品描述：</td>
                        <td><textarea rows="5" name="productDes" class="text" id="productDes"></textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="button" onclick="checkBeforeCommit()" name="button"
                                                          value="添加"/></label></td>
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
