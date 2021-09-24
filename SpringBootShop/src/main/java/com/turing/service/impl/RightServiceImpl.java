package com.turing.service.impl;

import com.turing.entity.Right;
import com.turing.mapper.RightMapper;
import com.turing.service.RightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightServiceImpl implements RightService {
    // 日志
    Logger logger = Logger.getLogger(RightServiceImpl.class);

    @Autowired
    private RightMapper rightMapper;

    public int addRight(Right right) {
        logger.info("调用RightServiceImpl类的addRight方法,实现了用户添加权限信息功能");
        return rightMapper.addRight(right);
    }

    public Right selectByUserId(Right right) {
        logger.info("调用RightServiceImpl类的selectByUserId方法,实现了查询用户是不是Vip的信息");
        Right rightByUid = rightMapper.selectByUserId(right);
        return rightByUid;
    }

    public int deleteByUserId(Right right) {
        logger.info("调用RightServiceImpl类的deleteByUserId方法,删除用户权限信息");
        return rightMapper.deleteByUserId(right);
    }

}
