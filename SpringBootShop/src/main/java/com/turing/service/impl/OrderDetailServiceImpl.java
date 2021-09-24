package com.turing.service.impl;

import com.turing.entity.OrderDetail;
import com.turing.mapper.OrderDetailMapper;
import com.turing.service.OrderDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    Logger logger = Logger.getLogger(OrderDetailServiceImpl.class);
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public int addOrderDatail(OrderDetail orderDetail) {
        logger.info("调用OrderDetailServiceImpl中addOrderDatail方法,添加新的订单明细");
        return orderDetailMapper.addOrderDatail(orderDetail);
    }

    public int deleteOrderInfo(OrderDetail orderDetail) {
        logger.info("调用OrderDetailServiceImpl中deleteOrderInfo方法,删除对应Id的详细信息");
        return orderDetailMapper.deleteOrderInfo(orderDetail);
    }

}
