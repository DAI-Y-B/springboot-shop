package com.turing.service;

import com.turing.entity.Right;

public interface RightService {

    /**添加用户权限信息*/
    int addRight(Right right);

    /**根据用户Id查询用户是不是Vip*/
    Right selectByUserId(Right right);

    /**根据用户Id删除对应用户的权限信息*/
    int deleteByUserId(Right right);
}
