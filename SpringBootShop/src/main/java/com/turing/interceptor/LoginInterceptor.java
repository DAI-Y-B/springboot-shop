package com.turing.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {
    private List<String> passList = null;
    Logger logger = Logger.getLogger(LoginInterceptor.class);

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion方法已经被执行");
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("postHandle方法已经被执行");
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("调用LoginInterceptor类的preHandle方法，验证用户是否登陆");
        boolean result = false;// 阻止请求继续处理，不会执行到对应的控制器的代码处
        // 获得请求的url
        String url = request.getServletPath();
        System.out.println("LoginInterceptor------>url:" + url);
        // 获得可以通过的url，通过解析xml文件的方式获得
        passList = new ArrayList<String>();
        // passList.add("/index.jsp");
        passList.add("/");

        // 游客模式下放行登陆、注册界面、新闻消息
        passList.add("/middle/middleLogin");
        passList.add("/middle/middleRegist");
        passList.add("/user/showLogin");
        passList.add("/user/showRegist");
        passList.add("/user/regist");
        passList.add("/user/login");
        // 游客模式下放行主页和新闻消息
        passList.add("/middle/middleMainJsp");
        // 游客状态可以看见商品信息和新闻消息
        passList.add("/product/product");
        passList.add("/product/proCateQueryByParenId");
        passList.add("/product/rootCategoryQuery");
        passList.add("/product/productFind");
        passList.add("/product/product1");
        passList.add("/news/findNewsByTitle");
        passList.add("/product/getProductByPage");
        passList.add("user/product-list.jsp");
        passList.add("user/product-view.jsp");
//        // 游客模式下放行登陆、注册界面、新闻消息
//        passList.add("/middle/middleLogin.do");
//        passList.add("/middle/middleRegist.do");
//        passList.add("/user/showLogin.do");
//        passList.add("/user/showRegist.do");
//        passList.add("/user/regist.do");
//        passList.add("/user/login.do");
//        // 游客模式下放行主页和新闻消息
//        passList.add("/middle/middleMainJsp.do");
//        // 游客状态可以看见商品信息和新闻消息
//        passList.add("/product/product.do");
//        passList.add("/product/proCateQueryByParenId.do");
//        passList.add("/product/rootCategoryQuery.do");
//        passList.add("/product/productFind.do");
//        passList.add("/product/product1.do");
//        passList.add("/news/findNewsByTitle.do");
//        passList.add("/product/getProductByPage.do");
//        passList.add("user/product-list.jsp");
//        passList.add("user/product-view.jsp");

        if (passList.contains(url)) {
            result = true;
            return result;
        } else {
            // 判断
            HttpSession session = request.getSession();
            if (null == session.getAttribute("checkUser")) {
                result = false;
                // 返回登录页面（因为目前没有登录页面，返回首页）
                // /Quest/user/login.jsp
                // request.getRequestDispatcher("login.jsp").forward(request,
                // response);
                response.sendRedirect("middleLogin");
            } else {
                result = true;
            }
        }
        return result;
    }

}
