package com.turing.service;


import com.turing.entity.Bankcard;

public interface BankService {

    /**添加用户的银行卡信息*/
    int addUserBank(Bankcard bankcard);

    /** 根据用户Id查询对应的银行卡信息 */
    Bankcard seletcBankByUserId(Bankcard bankcard);

    /**删除对应用户id的银行卡信息*/
    int deleteBankByUserId(Bankcard bankcard);
}
