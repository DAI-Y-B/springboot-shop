package com.turing.controller;

import com.turing.entity.News;
import com.turing.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/middle")
// 专门用来中转.do文件（方便之后用于拦截器）
public class MiddleController {
    @Autowired
    private NewsService newsService;

    // 购物车中转
    @RequestMapping(value = "/middleYouCar")
    public String middleYouCar(HttpSession session) {
        return "redirect:/order/lookYourCar.do";
    }

    // 登陆中转
    @RequestMapping(value = "/middleLogin")
    public String middleLogin(HttpSession session) {
        return "redirect:/user/showLogin.do";
    }

    // 注册中转
    @RequestMapping(value = "/middleRegist")
    public String middleRegist(HttpSession session) {
        return "redirect:/user/showRegist.do";
    }

    // 个人中心
    @RequestMapping(value = "/middleUserCenter")
    public String middleUserCenter(HttpSession session) {
        return "user/index";
    }

    // 留言版
    @RequestMapping(value = "/middleComment")
    public String middleComment(HttpSession session) {
        return "user/guestbook";
    }

    // 回到首页
    @RequestMapping(value = "/middleMainJsp")
    public String middleMainJsp(HttpSession session) {
        // 新闻查看
        List<News> news = null;
        news = newsService.selectAllNews();
        session.setAttribute("newsList", news);
        return "index";
    }

    // 个人管理
    @RequestMapping(value = "/middleManageOne")
    public String middleManageOne(HttpSession session) {
        return "redirect:/manage/fingByUserName1.do";
    }

    // 个人订单管理
    @RequestMapping(value = "/middleOneOrder")
    public String middleOneOrder(HttpSession session) {
        return "redirect:/order/getOenOrders.do?currentPage=1";
    }

    // 管理员管理中心首页
    @RequestMapping(value = "/middleManageCenter")
    public String middleManageCenter(HttpSession session) {
        return "manage/index";
    }

    // 用户管理
    @RequestMapping(value = "/middleManageUser")
    public String middleManageUser(HttpSession session) {
        return "redirect:/manage/getAllUsers.do?currentPage=1";
    }

    // 新增用户
    @RequestMapping(value = "/middleManageAddUser")
    public String middleManageAddUser(HttpSession session) {
        return "manage/user-add";
    }

    // 商品管理
    @RequestMapping(value = "/middleManageProduct")
    public String middleManageProduct(HttpSession session) {
        return "redirect:/product/checkStock.do";
    }

    // 添加商品
    @RequestMapping(value = "/middleManageProductAdd")
    public String middleManageProductAdd(HttpSession session) {
        return "manage/product-add";
    }

    // 商品分类管理
    @RequestMapping(value = "/middleManageProductClass")
    public String middleManageProductClass(HttpSession session) {
        return "manage/productClass";
    }

    // 添加商品分类
    @RequestMapping(value = "/middleManageProductClassAdd")
    public String middleManageProductClassAdd(HttpSession session) {
        return "manage/productClass-add";
    }

    // 订单管理
    @RequestMapping(value = "/middleManageOrder")
    public String middleManageOrder(HttpSession session) {
        return "redirect:/order/getAllOrders.do?currentPage=1";
    }

    // 留言回复与管理
    @RequestMapping(value = "/middleManageReply")
    public String middleManageReply(HttpSession session) {
        return "manage/guestbook";
    }

    // 新闻管理
    @RequestMapping(value = "/middleManageNews")
    public String middleManageNews(HttpSession session) {
        return "redirect:/news/newsQueryByPage.do?currentPage=1";
    }

    // 添加新闻
    @RequestMapping(value = "/middleManageAddNews")
    public String middleManageAddNews(HttpSession session) {
        return "manage/news-add";
    }
}
