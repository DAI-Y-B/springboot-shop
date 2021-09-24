package com.turing.controller;

import com.turing.entity.*;
import com.turing.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RightService rightService;
    @Autowired
    private BankService bankService;
    @Autowired
    private VipService vipService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NewsService newsService;
    // // 查询用户是否存在
    // @RequestMapping(value = "/findUByUserName")
    // public void findUByUserName(@RequestParam("userName") String userName,
    // HttpServletResponse response,
    // HttpSession session) {
    // logger.info("调用findUByUserName方法,实现了查询是否存在相同账号");
    // System.out.println("查找userName：" + userName);
    // User u = new User();
    // u.setEuUserName(userName);
    // String result = "";
    // try {
    // u = userService.selectByUserCount(u);
    // if (u == null) {
    // result = null;// 查无此人
    // } else {
    // result = JSONObject.fromObject(u).toString();
    // }
    // response.setCharacterEncoding("UTF-8");
    // System.out.println(result);
    // response.getWriter().write(result);// 将json字符串返回
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    // 注册验证
    @RequestMapping(value = "/showRegist")
    public String showRegist(HttpSession session) {
        logger.info("调用showRegist方法,实现了验证功能");
        int m = (int) (Math.random() * 10);
        int n = (int) (Math.random() * 10);
        // 提示验证码是否正确
        String remind = null;
        // 提示用户名是否存在
        String resUserString = null;
        System.out.println("m" + m + "*" + "n" + n);
        session.setAttribute("m", m);
        session.setAttribute("n", n);
        session.setAttribute("remind", remind);
        session.setAttribute("resUserString", resUserString);
        return "user/register";
    }

    @RequestMapping(value = "/regist")
    public String regist(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
                         @RequestParam("euName") String euName, @RequestParam("euSex") String euSex,
                         @RequestParam("euIdentityCode") String euIdentityCode, @RequestParam("euMobile") String euMobile,
                         @RequestParam("euEmail") String euEmail, @RequestParam("euAddress") String euAddress,
                         @RequestParam("veryCode") Integer veryCode, HttpSession session) throws Exception {
        logger.info("调用showRegist方法,实现了注册功能");
        // 提示验证码是否正确
        String remind = null;
        // 提示用户名是否存在
        String resUserString = null;
        Integer m = (Integer) session.getAttribute("m");
        Integer n = (Integer) session.getAttribute("n");
        Integer r = m * n;
        // 如果验证码错误
        if (r != veryCode) {
            int m1 = (int) (Math.random() * 10);
            int n1 = (int) (Math.random() * 10);
            session.setAttribute("m", m1);
            session.setAttribute("n", n1);
            remind = "计算结果错误，请重新输入";
            session.setAttribute("remind", remind);

        }
        // 验证码正确
        remind = "验证码正确";
        session.setAttribute("remind", remind);

        User isUser = new User();
        isUser.setEuUserName(userName);
        isUser = userService.selectByUserCount(isUser);
        if (isUser != null) {
            resUserString = "此账号存在请重新输入";
            session.setAttribute("resUserString", resUserString);
            return "user/register";
        }
        // 添加用户信息
        User registUser = new User();
        registUser.setEuUserName(userName);
        registUser.setEuPassword(passWord);
        registUser.setEuName(euName);
        registUser.setEuSex(euSex);
        registUser.setEuIdentityCode(euIdentityCode);
        registUser.setEuMobile(euMobile);
        registUser.setEuEmail(euEmail);
        registUser.setEuAddress(euAddress);
        registUser.setEuStatus(5);
        userService.regist(registUser);

        // 查询用户相关信息
        User userInfo = new User();
        userInfo.setEuUserName(userName);
        userInfo = userService.selectByUserCount(userInfo);
        System.out.println("regist----------------------userInfo" + userInfo);

        // 随机生成银行卡号
        Random ran = new Random();
        String random = ran.nextInt(100000000) + ran.nextInt(100) + "";
        for (int i = 0; i < 10 - random.length(); i++) {
            random = "0" + random;
        }
        // 添加银行卡信息
        Bankcard card = new Bankcard();
        card.setBankcardUserId(userInfo.getEuUserId());
        card.setBankcardNumber(random);
        card.setBankcardPwd("123");
        bankService.addUserBank(card);
        // 添加用户角色信息
        Right right = new Right();
        right.setRightRoleId(userInfo.getEuStatus());
        right.setRightUserId(userInfo.getEuUserId());
        rightService.addRight(right);

        return "user/reg-result";
    }

    // 登录 人类验证
    @RequestMapping(value = "/showLogin")
    public String showLogin(HttpSession session) {
        logger.info("调用showLogin方法,实现了登陆验证功能");
        int m = (int) (Math.random() * 10);
        int n = (int) (Math.random() * 10);
        // 提示验证码是否正确
        String remind = null;
        // 提示用户是否存在
        String resUser = null;
        System.out.println("m" + m + "+" + "n" + n);
        session.setAttribute("m1", m);
        session.setAttribute("n1", n);
        session.setAttribute("remind", remind);
        session.setAttribute("resUserString", resUser);
        return "user/login";
    }

    // 登陆
    @RequestMapping(value = "/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
                        @RequestParam("veryCode") Integer veryCode, HttpSession session) {
        String remind = null;
        String resUser = null;
        Integer m = (Integer) session.getAttribute("m1");
        Integer n = (Integer) session.getAttribute("n1");
        Integer r = m + n;
        // 如果验证码错误
        if (r != veryCode) {
            int m1 = (int) (Math.random() * 10);
            int n1 = (int) (Math.random() * 10);
            session.setAttribute("m1", m1);
            session.setAttribute("n1", n1);
            remind = "验证码错误";
            session.setAttribute("remind1", remind);
            return "user/login";
        }
        remind = "验证码正确";
        session.setAttribute("remind1", remind);
        User checkUser = new User();
        checkUser.setEuUserName(userName);
        checkUser.setEuPassword(passWord);
        checkUser = userService.login(checkUser);
        System.out.println("login----------------------checkUser" + checkUser);
        if (checkUser == null) {
            resUser = "用户不存在，请重新输入";
            session.setAttribute("resUser", resUser);
            return "user/login";
        }
        session.setAttribute("checkUser", checkUser);
        // 查询对应银行卡信息
        Bankcard checkBankcard = new Bankcard();
        checkBankcard.setBankcardUserId(checkUser.getEuUserId());
        checkBankcard = bankService.seletcBankByUserId(checkBankcard);
        System.out.println("login----------------------checkBankcard" + checkBankcard);
        session.setAttribute("checkBankcard", checkBankcard);

        // 根据UserId查看用户的角色表
        Right checkRight = new Right();
        checkRight.setRightUserId(checkUser.getEuUserId());
        checkRight = rightService.selectByUserId(checkRight);
        System.out.println("login----------------------checkRight" + checkRight);
        session.setAttribute("checkRight", checkRight);

        // 根据User_Id查询对应的角色Vip信息
        Vip checkVip = new Vip();
        checkVip.setVipUserId(checkUser.getEuUserId());
        checkVip = vipService.selectVipByuser(checkVip);
        System.out.println("login----------------------checkVip" + checkVip);
        session.setAttribute("checkVip", checkVip);

        // 根据User_Id查询对应的角色Role信息
        Role checkRole = new Role();
        checkRole.setRoleId(checkUser.getEuStatus());
        checkRole = roleService.selectByUserStatus(checkRole);
        System.out.println("login----------------------checkRole" + checkRole);
        session.setAttribute("checkRole", checkRole);

        //新闻查看
        List<News> news = null;
        news = newsService.selectAllNews();
        session.setAttribute("newsList", news);

        if (checkUser.getEuStatus() != 5 && checkUser.getEuStatus() != 6) {
            return "manage/index";
        } else {
            return "index";
        }
    }


}
