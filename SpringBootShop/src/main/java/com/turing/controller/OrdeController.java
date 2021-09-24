package com.turing.controller;

import com.turing.entity.*;
import com.turing.service.CarService;
import com.turing.service.OrderDetailService;
import com.turing.service.OrderService;
import com.turing.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrdeController {

    Logger logger = Logger.getLogger(OrdeController.class);
    @Autowired
    private CarService carService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = "/userAddCar")
    public String userAddCar(@RequestParam("productId") Integer productId, HttpServletResponse response,
                             HttpSession session) throws Exception {
        User userInfo = (User) session.getAttribute("checkUser");
        Car carInfo = new Car();
        Car checkCar = new Car();
        carInfo.setUserId(userInfo.getEuUserId());
        carInfo.setProductId(productId);
        carInfo.setProductCount(1);
        System.out.println("carInfo" + carInfo);
        int rs = 0;
        // 查询购物车中是否存在相同的商品，如果存在，则直接更新数量
        checkCar = carService.selectCarById(carInfo);
        System.out.println("carInfo" + carInfo);
        System.out.println("checkCar" + checkCar);
        if (checkCar != null) {
            Car addCount = new Car();
            addCount.setProductCount(checkCar.getProductCount() + 1);
            addCount.setProductId(productId);
            addCount.setUserId(userInfo.getEuUserId());
            addCount.setCarId(checkCar.getCarId());
            System.out.println("addCount" + addCount);
            rs = carService.updateCarInfo(addCount);
        }
        // 如果购物车不存在，则添加新的商品
        else if (checkCar == null) {
            rs = carService.addCar(carInfo);
        }
        if (rs == 1) {
            session.setAttribute("rs", "亲~，添加购物车成功");
            return "user/product-view";
        } else {
            session.setAttribute("rs", "亲~，添加购物车失败");
            return "user/product-view";
        }
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestParam("productId") Integer productId, HttpServletResponse response,
                                HttpSession session) throws Exception {
        Car carInfo = new Car();
        carInfo.setProductId(productId);
        int result = carService.deleteCar(carInfo);
        System.out.println("deleteProduct------------------result" + result);
        return "redirect:/order/lookYourCar.do";
    }

    @RequestMapping(value = "/showOneProduct")
    public String showOneProduct(@RequestParam("productId") Integer productId, HttpSession session) {
        User userInfo = (User) session.getAttribute("checkUser");
        Car carInfo = new Car();
        Car checkCar = new Car();
        carInfo.setUserId(userInfo.getEuUserId());
        carInfo.setProductId(productId);
        carInfo.setProductCount(1);
        System.out.println("carInfo" + carInfo);
        int rs = 0;
        // 查询购物车中是否存在相同的商品，如果存在，则直接更新数量
        checkCar = carService.selectCarById(carInfo);
        System.out.println("carInfo" + carInfo);
        System.out.println("checkCar" + checkCar);
        if (checkCar != null) {
            Car addCount = new Car();
            addCount.setProductCount(checkCar.getProductCount() + 1);
            addCount.setProductId(productId);
            addCount.setUserId(userInfo.getEuUserId());
            addCount.setCarId(checkCar.getCarId());
            System.out.println("addCount" + addCount);
            rs = carService.updateCarInfo(addCount);
        }
        // 如果购物车不存在，则添加新的商品
        else if (checkCar == null) {
            rs = carService.addCar(carInfo);
        }
        if (rs == 1) {
            return "redirect:/order/lookYourCar.do";
        } else {
            return "redirect:/order/lookYourCar.do";
        }

    }

    @RequestMapping(value = "/lookYourCar")
    public String lookYourCar(HttpSession session) {
        User userInfo = (User) session.getAttribute("checkUser");
        Car lookCar = new Car();
        lookCar.setUserId(userInfo.getEuUserId());
        List<Car> userCars = carService.selectAllUser(lookCar);
        for (Car car : userCars) {
            System.out.println("lookYourCar-------------------userCars" + car);
        }
        session.setAttribute("userCars", userCars);
        // return "redirect:/order/shoppingProduct.do";
        return "user/carInfo";
    }

    @RequestMapping(value = "/updateCount")
    public String updateCount(@RequestParam("num") Integer num, @RequestParam("productId") Integer productId,
                              HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
            throws Exception {
        System.out.println("updateCount---------------------" + num);
        Car carInfoNew = new Car();
        carInfoNew.setProductId(productId);
        carInfoNew = carService.selectCarById(carInfoNew);
        if (num == 1) {
            carInfoNew.setProductCount(carInfoNew.getProductCount() + 1);
            carService.updateCarInfo(carInfoNew);
        } else if (num == 2) {
            carInfoNew.setProductCount(carInfoNew.getProductCount() - 1);
            carService.updateCarInfo(carInfoNew);
        }
        return "redirect:/order/lookYourCar.do";
    }

    @RequestMapping(value = "/buyProduct")
    public String buyProduct(@RequestParam("bb") String bb, HttpServletRequest request, HttpServletResponse response,
                             HttpSession session, Model model) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        System.out.println("进来了吗buyProduct");
        // 查询当前购买商品的用户Id
        User userInfo = (User) session.getAttribute("checkUser");
        // 将购物车中的
        String temp1[] = bb.split(",");
        Car countCar = new Car();
        // 查询对应物品的价格
        Product costProduct = new Product();
        for (String string : temp1) {
            System.out.println("buyProduct----------------------temp1" + string);
            if (string == null || string == "" || string.equals("")) {
                System.out.println("buyProduct----------------------continue");
                continue;
            } else {

                // 查询对应物品的数量，价格，以及在购买完之后删除
                costProduct.setId(Integer.parseInt(string));
                costProduct = productService.selectProductInfoById(costProduct);
                if (costProduct != null) {
                    // 获取购物车中的数量（防止一个人买多个物品）
                    countCar.setProductId(Integer.parseInt(string));
                    countCar = carService.selectCarById(countCar);

                    if (costProduct.getStock() < countCar.getProductCount()) {

                        out = response.getWriter();
                        out.print("<script>alert('库存数量不足!');" +
                                // "location.href='user/carInfo'"+
                                "</script>");
                        out.flush();
                        return "user/carInfo";
                    }

                    for (int i = 0; i < countCar.getProductCount(); i++) {
                        costProduct.setId(Integer.parseInt(string));
                        costProduct = productService.selectProductInfoById(costProduct);
                        // 开始添加新的订单
                        Order addInfOrder = new Order();
                        addInfOrder.setEoUserId(userInfo.getEuUserId());
                        addInfOrder.setEoUserName(userInfo.getEuName());
                        addInfOrder.setEoUserAddress(userInfo.getEuAddress());
                        addInfOrder.setEoCreateTime(new Date());
                        addInfOrder.setEoCost(costProduct.getPrice());
                        addInfOrder.setEoStatus(1);
                        addInfOrder.setEoType(2);
                        orderService.addOrder(addInfOrder);

                        // 查询最新的订单Id将Id数据获取之后写道订单详细表中
                        Order newOrderInfo = new Order();
                        newOrderInfo = orderService.selectLastOrder();

                        // 插入订单详情表中
                        OrderDetail newOrderDetail = new OrderDetail();
                        newOrderDetail.setEoId(newOrderInfo.getEoId());
                        newOrderDetail.setEpId(Integer.parseInt(string));
                        newOrderDetail.setEodCost(costProduct.getPrice());
                        newOrderDetail.setEodQuantity(1);
                        orderDetailService.addOrderDatail(newOrderDetail);

                        // 每购买一次库存减一
                        costProduct.setStock(costProduct.getStock() - 1);
                        productService.updateProduct(costProduct);

                    }
                    // 购买完成之后在购物车中删除
                    carService.deleteCar(countCar);
                }

            }

        }
        return "user/shopping-result";// 后期修改
    }

    @RequestMapping(value = "/getAllOrders")
    public String getAllOrders(@RequestParam("currentPage") String currentPage, HttpSession session) {
        logger.info("调用getAllOrders方法,实现了分页查询");
        List<Order> orders = null;
        orders = orderService.selectAllOrders();
        for (Order order : orders) {
            System.out.println("getAllOrders---------------------------orders：" + order);
        }
        List<Order> fenyeOrders = null;
        int size = 3;
        int totalCount = orders.size() % size == 0 ? orders.size() / size : orders.size() / size + 1;
        if (null != currentPage) {
            fenyeOrders = orderService.selectAllOrdersPerPage((Integer.parseInt(currentPage) - 1) * size, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        } else {
            fenyeOrders = orderService.selectAllOrdersPerPage(1, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        }
        session.setAttribute("orderList", fenyeOrders);
        session.setAttribute("totalCount", totalCount);
        session.setAttribute("total", orders.size());
        return "manage/order";
    }

    @RequestMapping(value = "/getOenOrders")
    public String getOenOrders(@RequestParam("currentPage") String currentPage, HttpSession session) {
        logger.info("调用getOenOrders方法,实现了分页查询");
        // 获取当前用户的用户Id数据
        User userInfo = (User) session.getAttribute("checkUser");
        Order orderByUserId = new Order();
        orderByUserId.setEoUserId(userInfo.getEuUserId());
        List<Order> oneOrders = null;
        oneOrders = orderService.selectOrdersById(orderByUserId);
        for (Order order : oneOrders) {
            System.out.println("getOenOrders------------------order" + order);
        }
        List<Order> fenyeUserIdOrders = null;
        int size = 3;
        int totalCount = oneOrders.size() % size == 0 ? oneOrders.size() / size : oneOrders.size() / size + 1;
        if (null != currentPage) {
            fenyeUserIdOrders = orderService.selectAllOrdersPerPageByUserId(userInfo.getEuUserId(),
                    (Integer.parseInt(currentPage) - 1) * size, size);
            session.setAttribute("currentPage1", Integer.parseInt(currentPage));
        } else {
            fenyeUserIdOrders = orderService.selectAllOrdersPerPageByUserId(userInfo.getEuUserId(), 1, size);
            session.setAttribute("currentPage1", Integer.parseInt(currentPage));
        }
        session.setAttribute("orderList1", fenyeUserIdOrders);
        session.setAttribute("totalCount1", totalCount);
        session.setAttribute("total1", oneOrders.size());
        return "user/userOrder";
    }

    // 管理员查看订单详情
    @RequestMapping(value = "/orderFindById")
    public String orderFindById(@RequestParam("eoId") Integer eoId, HttpSession session) {
        Order orderInfo = new Order();
        orderInfo.setEoId(eoId);
        orderInfo = orderService.selectById(orderInfo);
        session.setAttribute("orderOneSelf", orderInfo);
        return "manage/order-modify";
    }

    // 个人查看订单详情
    @RequestMapping(value = "/orderFindById1")
    public String orderFindById1(@RequestParam("eoId") Integer eoId, HttpSession session) {
        Order orderInfo = new Order();
        orderInfo.setEoId(eoId);
        orderInfo = orderService.selectById(orderInfo);
        session.setAttribute("orderOneSelf", orderInfo);
        return "user/userOrderModify";
    }

    // 管理员修改订单
    @RequestMapping(value = "/editOrder")
    public String editOrder(@RequestParam("orderId") Integer orderId, @RequestParam("name") String name,
                            @RequestParam("useraddress") String useraddress, @RequestParam("cost") Double cost,
                            @RequestParam("status") Integer status, HttpSession session) {
        Order orderEditInfo = new Order();
        // 查询出对应的Id的订单号
        orderEditInfo.setEoId(orderId);
        orderEditInfo = orderService.selectById(orderEditInfo);
        orderEditInfo.setEoUserName(name);
        orderEditInfo.setEoUserAddress(useraddress);
        orderEditInfo.setEoCost(cost);
        orderEditInfo.setEoStatus(status);
        orderService.updateOrderInfo(orderEditInfo);
        return "redirect:/order/getAllOrders.do?currentPage=1";
    }

    // 用户个人修改订单（只能修改订单的物流状态）
    @RequestMapping(value = "/editOrder1")
    public String editOrder1(@RequestParam("orderId") Integer orderId, @RequestParam("status") Integer status,
                             HttpSession session) {
        Order orderEditInfo = new Order();
        // 查询出对应的Id的订单号
        orderEditInfo.setEoId(orderId);
        orderEditInfo = orderService.selectById(orderEditInfo);
        orderEditInfo.setEoStatus(status);
        orderService.updateOrderInfo(orderEditInfo);
        return "redirect:/order/getOenOrders.do?currentPage=1";
    }

    @RequestMapping(value = "/delOrder")
    public String delOrder(@RequestParam("eoId") Integer eoId, HttpSession session) {
        Order orderEditInfo = new Order();
        OrderDetail orderDetailInfo = new OrderDetail();
        orderEditInfo.setEoId(eoId);
        orderDetailInfo.setEoId(eoId);
        int i = orderService.deleteOrderInfo(orderEditInfo);
        if (i == 1) {
            orderDetailService.deleteOrderInfo(orderDetailInfo);
            return "redirect:/order/getAllOrders.do?currentPage=1";
        }

        return "redirect:/order/getAllOrders.do?currentPage=1";
    }

}
