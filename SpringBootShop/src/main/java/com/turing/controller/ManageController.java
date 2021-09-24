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
@RequestMapping(value = "/manage")
public class ManageController {
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
    private OrderService orderService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/getAllUsers")
    public String getAllUsers(@RequestParam("currentPage") String currentPage, HttpSession session) {
        logger.info("调用getAllUsers方法,实现了分页查询");
        List<User> users = null;
        users = userService.selectAllUsers();
        for (User user : users) {
            System.out.println("getAllUsers---------------------------user：" + user);
        }
        List<User> fenyeUsers = null;
        int size = 3;
        int totalCount = users.size() % size == 0 ? users.size() / size : users.size() / size + 1;
        if (null != currentPage) {
            fenyeUsers = userService.selectAllUsersPerPage((Integer.parseInt(currentPage) - 1) * size, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        } else {
            fenyeUsers = userService.selectAllUsersPerPage(1, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        }
        session.setAttribute("userList", fenyeUsers);
        session.setAttribute("totalCount", totalCount);
        session.setAttribute("total", users.size());

        return "manage/user";
    }

    // 管理员修改查询
    @RequestMapping(value = "/fingByUserName")
    public String fingByUserName(@RequestParam("euUserName") String euUserName, HttpSession session) {
        logger.info("调用fingByUserName方法,先查询出相关的消息，最后返回到修改页面");
        User userFind = new User();
        userFind.setEuUserName(euUserName);
        userFind = userService.selectByUserCount(userFind);
        session.setAttribute("someOneInfo", userFind);
        // 查询所有权限信息
        List<Role> roleFind = roleService.selectAllRole();
        for (Role role : roleFind) {
            System.out.println("fingByUserName--------------------roleFind" + role);
        }
        session.setAttribute("roleInfo", roleFind);
        return "manage/user-modify";
    }

    // 管理员修改
    @RequestMapping(value = "/editUser")
    public String editUser(@RequestParam("euName") String euName, @RequestParam("euPassword") String euPassword,
                           @RequestParam("euEmail") String euEmail, @RequestParam("euSex") String euSex,
                           @RequestParam("euMobile") String euMobile, @RequestParam("euAddress") String euAddress,
                           @RequestParam("euStatus") int euStatus, HttpSession session) {
        logger.info("调用editUser方法,修改用户信息");
        User userOldInfoUser = (User) session.getAttribute("someOneInfo");
        userOldInfoUser.setEuName(euName);
        userOldInfoUser.setEuPassword(euPassword);
        userOldInfoUser.setEuSex(euSex);
        userOldInfoUser.setEuMobile(euMobile);
        userOldInfoUser.setEuEmail(euEmail);
        userOldInfoUser.setEuAddress(euAddress);
        userOldInfoUser.setEuUserId(userOldInfoUser.getEuUserId());
        userOldInfoUser.setEuStatus(euStatus);
        userService.updateUser(userOldInfoUser);
        return "redirect:/manage/getAllUsers.do?currentPage=1";
    }

    // 用户修改查询
    @RequestMapping(value = "/fingByUserName1")
    public String fingByUserName1(HttpSession session) {
        logger.info("调用fingByUserName1方法,先查询出相关的消息，最后返回到修改页面");
        //修改的是用户自身数据，所以只要获取一个当前的用户session变量即可
        User userOne = (User) session.getAttribute("checkUser");
        User userFind = new User();
        userFind.setEuUserName(userOne.getEuUserName());
        userFind = userService.selectByUserCount(userFind);
        session.setAttribute("someOneInfo1", userFind);
        // 查询所有权限信息
        List<Role> roleFind = roleService.selectAllRole();
        for (Role role : roleFind) {
            System.out.println("fingByUserName1--------------------roleFind" + role);
        }
        session.setAttribute("roleInfo1", roleFind);
        return "user/user-modify";
    }

    // 用户修改
    @RequestMapping(value = "/editUser1")
    public String editUser1(@RequestParam("euName") String euName, @RequestParam("euPassword") String euPassword,
                            @RequestParam("euEmail") String euEmail, @RequestParam("euSex") String euSex,
                            @RequestParam("euMobile") String euMobile, @RequestParam("euAddress") String euAddress,
                            HttpSession session) {
        logger.info("调用editUser1方法,修改用户信息");
        User userOldInfoUser = (User) session.getAttribute("someOneInfo1");
        userOldInfoUser.setEuName(euName);
        userOldInfoUser.setEuPassword(euPassword);
        userOldInfoUser.setEuSex(euSex);
        userOldInfoUser.setEuMobile(euMobile);
        userOldInfoUser.setEuEmail(euEmail);
        userOldInfoUser.setEuAddress(euAddress);
        userOldInfoUser.setEuUserId(userOldInfoUser.getEuUserId());
        userService.updateUser(userOldInfoUser);
        return "index";
    }

    @RequestMapping(value = "/delUser")
    public String delUser(@RequestParam("euUserName") String euUserName, HttpSession session) {
        logger.info("调用delUser方法,删除用户信息");
        // 根据用户名查找对应的信息
        User userDelInfoUser = new User();
        userDelInfoUser.setEuUserName(euUserName);
        userDelInfoUser = userService.selectByUserCount(userDelInfoUser);
        User userOldInfoUser = (User) session.getAttribute("checkUser");
        System.out.println("userDelInfoUseruserDelInfoUseruserDelInfoUseruserDelInfoUseruserDelInfoUser---" + userDelInfoUser.getEuUserName());
        System.out.println("userOldInfoUser.getEuUserName()userOldInfoUser.getEuUserName()userOldInfoUser.getEuUserName()---" + userOldInfoUser.getEuUserName());

        //自己不能删除自己
        if (userDelInfoUser.getEuUserName().equals(userOldInfoUser.getEuUserName())) {
            //删除失败，自己不能删除自己
            return "redirect:/manage/getAllUsers.do?currentPage=1";
        }


        System.out.println("delUser---------------------userDelInfoUser" + userDelInfoUser);
        // 删除对应用户的银行卡信息
        Bankcard bankDelInfo = new Bankcard();
        bankDelInfo.setBankcardUserId(userDelInfoUser.getEuUserId());
        bankService.deleteBankByUserId(bankDelInfo);
        // 删除对应用户的权限信息
        Right rightDelInfo = new Right();
        rightDelInfo.setRightUserId(userDelInfoUser.getEuUserId());
        rightService.deleteByUserId(rightDelInfo);
        // 删除对应的用户VIP信息
        Vip vipDelInfo = new Vip();
        vipDelInfo.setVipUserId(userDelInfoUser.getEuUserId());
        vipService.deleteVipByUserId(vipDelInfo);
        // 删除对应订单表
        Order orderDelInfo = new Order();
        orderDelInfo.setEoUserId(userDelInfoUser.getEuUserId());
        List<Order> orderDetail = orderService.selectOrdersById(orderDelInfo);
        //删除对应的订单详情表
        OrderDetail orderDetailDelInfo = new OrderDetail();
        for (Order order : orderDetail) {
            System.out.println("orderorderorderorderorderorderorderorderorderorderorderorderorder---" + order);
            orderDetailDelInfo.setEoId(order.getEoId());
            orderDetailService.deleteOrderInfo(orderDetailDelInfo);
        }
        //删除完成订单详细表之后再删除订单表
        orderService.deleteOneSelf(orderDelInfo);
        // 删除对应购物车表
        Car caoDelInfo = new Car();
        caoDelInfo.setUserId(userDelInfoUser.getEuUserId());
        carService.deleteOneSelf(caoDelInfo);

        userService.deleteUser(userDelInfoUser);
        return "redirect:/manage/getAllUsers.do?currentPage=1";
    }

    @RequestMapping(value = "/addUserByAdmin")
    public String addUserByAdmin(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
                                 @RequestParam("euName") String euName, @RequestParam("euSex") String euSex,
                                 @RequestParam("euIdentityCode") String euIdentityCode, @RequestParam("euMobile") String euMobile,
                                 @RequestParam("euEmail") String euEmail, @RequestParam("euAddress") String euAddress, HttpSession session)
            throws Exception {
        logger.info("调用addUserByAdmin方法,实现了添加用户的功能");
        // 提示用户名是否存在
        String resUserString = null;
        User isUser = new User();
        isUser.setEuUserName(userName);
        isUser = userService.selectByUserCount(isUser);
        if (isUser != null) {
            resUserString = "此账号存在请重新输入";
            session.setAttribute("resUserString", resUserString);
            return "manage/user-add";
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

        return "redirect:/manage/getAllUsers.do?currentPage=1";
    }
}
