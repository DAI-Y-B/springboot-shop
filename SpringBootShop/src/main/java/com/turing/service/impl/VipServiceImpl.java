package com.turing.service.impl;

import com.turing.entity.Vip;
import com.turing.mapper.VipMapper;
import com.turing.service.VipService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipServiceImpl implements VipService {
    // 日志
    Logger logger = Logger.getLogger(VipServiceImpl.class);
    @Autowired
    private VipMapper vipMapper;

    public Vip selectVipByuser(Vip vip) {
        logger.info("调用UserServiceImpl类的selectVipByuser方法,实现了根据用户id查询对应用户的vip信息 ");
        Vip vipByUserId = new Vip();
        vipByUserId = vipMapper.selectVipByuser(vip);
        return vipByUserId;
    }

    public int deleteVipByUserId(Vip vip) {
        logger.info("调用UserServiceImpl类的deleteVipByUserId方法,实现了根据用户id删除对应用户的vip信息 ");
        return vipMapper.deleteVipByUserId(vip);
    }

}
