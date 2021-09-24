package com.turing.service;

import com.turing.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    /** 创建订单 */
    int addOrder(Order order);

    /** 查询最新创建的订单 */
    Order selectLastOrder();

    /** 查询最新创建的订单 */
    List<Order> selectAllOrders();

    /**分页查询*/
    List<Order> selectAllOrdersPerPage(@Param("currentPage") Integer currentPage, @Param("size") Integer size);

    /**根据订单Id查询对应的订单*/
    Order selectById(Order order);

    /**根据订单Id修改订单信息*/
    int updateOrderInfo(Order order);

    /**删除对应id的订单信息*/
    int deleteOrderInfo(Order order);

    /**根据用户名查询订单*/
    List<Order> selectByUserName(Order order);

    /**根据订单Id和用户姓名一查询*/
    Order selectByNameAndId(Order order);

    /**根据用户id删除用户的购物车*/
    int deleteOneSelf(Order order);

    /** 根据订单Id查询对应的订单 */
    List<Order> selectOrdersById(Order order);

    /** 用户个人分页查询 */
    List<Order> selectAllOrdersPerPageByUserId(@Param("eoUserId") Integer eoUserId, @Param("currentPage") Integer currentPage, @Param("size") Integer size);
}
