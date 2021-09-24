package com.turing.service;

import com.turing.entity.Vip;

public interface VipService {

    /** 根据用户id查询对应用户的vip信息 */
    Vip selectVipByuser(Vip vip);

    /** 根据用户id删除对应用户的vip信息 */
    int deleteVipByUserId(Vip vip);
}
