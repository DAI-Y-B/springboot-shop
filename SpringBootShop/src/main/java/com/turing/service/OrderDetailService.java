package com.turing.service;

import com.turing.entity.OrderDetail;

public interface OrderDetailService {

    /** 创建单明细表 */
    int addOrderDatail(OrderDetail orderDetail);

    /**删除对应id的订单信息*/
    int deleteOrderInfo(OrderDetail orderDetail);
}
