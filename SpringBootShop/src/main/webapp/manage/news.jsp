<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新闻管理</title>
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
        function $$(id) {
            return document.getElementById(id);
        }

        function showTime() {
            var myspan = $$("myTime");
            var mydate = new Date();
            var myYear = mydate.getFullYear();
            var myMonth = mydate.getMonth() + 1;
            var myRiqi = mydate.getDate();
            var myDay = mydate.getDay();
            var myHour = mydate.getHours();
            var myMinitue = mydate.getMinutes();
            var mySecond = mydate.getSeconds();
            var str = myYear + "年";
            if (myMonth < 10) {
                str += "0";
            }
            str += myMonth + "月";
            if (myRiqi < 10) {
                str += "0";
            }
            str += myRiqi + "日";
            if (myHour < 10) {
                str += "0";
            }
            str += myHour + ":";
            if (myMinitue < 10) {
                str += "0";
            }
            str += myMinitue + ":";
            if (mySecond < 10) {
                str += "0";
            }
            str += mySecond;
            myspan.innerHTML = str;
            //	setTimeout("showTime()",1000);
        }

        function myLoad() {
            setInterval("showTime()", 1000);
        }
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
        <h2>新闻管理</h2>
        <div class="manage">
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>新闻标题</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <c:forEach items="${newsList}" var="news">
                <tr>
                    <td class="first w4 c">${news.nId}</td>
                    <td>${news.nTitle }</td>
                    <td class="w1 c"><a href="<%=basePath%>news/newsFind.do?id=${news.nId}">修改</a> <a
                            href="javascript:;" onclick="del('${news.nId}')">删除</a></td>
                </tr>
                </c:forEach>
                </tr>
                <tr>
                    <td colspan="3">

                        <a href="news/newsQueryByPage.do?currentPage=1">首页</a>
                        <c:if test="${currentPage==1}" var="pre">
                            <a href="news/newsQueryByPage.do?currentPage=1">上一页</a>
                        </c:if>
                        <c:if test="${ ! pre }">
                            <a href="news/newsQueryByPage.do?currentPage=${currentPage-1 }">上一页</a>
                        </c:if>

                        <c:if test="${currentPage==totalCount}" var="next">
                            <a href="news/newsQueryByPage.do?currentPage=${totalCount }">下一页</a>
                        </c:if>
                        <c:if test="${ ! next }">
                            <a href="news/newsQueryByPage.do?currentPage=${currentPage+1 }">下一页</a>
                        </c:if>
                        <a href="news/newsQueryByPage.do?currentPage=${totalCount }">末 页</a>
                        <br/>
                        当前第${currentPage }页,一共${total }条,一共${totalCount }页
                    </td>
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
<script type="text/javascript">
    function del(id) {
        var result = window.confirm("确认删除新闻" + id + "吗？");
        if (true == result) {
            //将要删除的部门编号收为url传递到del.jsp中
            window.location.href = "news/newsDel.do?id=" + id;
        }
    }

    /* $(function(){
            //键盘按下事件
            $("#page").keydown(function(e){//e是被按下的按键
                if(e.which==13){//点击回车
                    //alert(this.value);
                    window.location.href ="newsQueryByPage.do?pageNo="+this.value;
                }
            });
        }); */
</script>
</html>
