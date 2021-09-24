package com.turing.service.impl;

import com.turing.entity.Order;
import com.turing.mapper.OrderMapper;
import com.turing.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = Logger.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;

    public int addOrder(Order order) {
        logger.info("调用OrderServiceImpl中addOrder方法,添加新的订单");
        return orderMapper.addOrder(order);
    }

    public Order selectLastOrder() {
        logger.info("调用OrderServiceImpl中selectLastOrder方法,查询最新加入的一条订单");
        return orderMapper.selectLastOrder();
    }

    public List<Order> selectAllOrders() {
        logger.info("调用OrderServiceImpl类的selectAllOrders方法,查询所有订单");
        List<Order> orders = orderMapper.selectAllOrders();
        return orders;
    }

    public List<Order> selectAllOrdersPerPage(Integer currentPage, Integer size) {
        logger.info("调用OrderServiceImpl类的selectAllOrdersPerPage方法,查询所有订单,实现分页效果");
        List<Order> allOrderPage = orderMapper.selectAllOrdersPerPage(currentPage, size);
        return allOrderPage;
    }

    public Order selectById(Order order) {
        logger.info("调用OrderServiceImpl类的selectById方法，根据订单Id查询相关订单");
        return orderMapper.selectById(order);
    }

    public int updateOrderInfo(Order order) {
        logger.info("调用OrderServiceImpl类的selectById方法，根据订单Id修改相关的订单内容");
        return orderMapper.updateOrderInfo(order);
    }

    public int deleteOrderInfo(Order order) {
        logger.info("调用OrderServiceImpl类的deleteOrderInfo方法，根据订单Id删除相关的订单内容");
        return orderMapper.deleteOrderInfo(order);
    }

    public List<Order> selectByUserName(Order order) {
        logger.info("调用OrderServiceImpl类的deleteOrderInfo方法，根据用户名查询订单");
        return orderMapper.selectByUserName(order);
    }

    public Order selectByNameAndId(Order order) {
        logger.info("调用OrderServiceImpl类的deleteOrderInfo方法，根据订单Id和用户姓名一查询");
        return orderMapper.selectByNameAndId(order);
    }

    public int deleteOneSelf(Order order) {
        logger.info("调用OrderServiceImpl类的deleteOneSelf方法，根据订单Id删除订单详细表");
        return orderMapper.deleteOneSelf(order);
    }

    public List<Order> selectOrdersById(Order order) {
        logger.info("调用OrderServiceImpl类的selectOrdersById方法，根据订单Id查询对应的订单");
        return orderMapper.selectOrdersById(order);
    }

    public List<Order> selectAllOrdersPerPageByUserId(Integer eoUserId, Integer currentPage, Integer size) {
        logger.info("调用OrderServiceImpl类的selectAllOrdersPerPageByUserId方法，用户个人分页查询");
        return orderMapper.selectAllOrdersPerPageByUserId(eoUserId, currentPage, size);
    }


}
