package com.turing.service.impl;

import com.turing.entity.Bankcard;
import com.turing.mapper.BankMapper;
import com.turing.service.BankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    // 日志
    Logger logger = Logger.getLogger(BankServiceImpl.class);
    @Autowired
    private BankMapper bankMapper;

    public int addUserBank(Bankcard bankcard) {
        logger.info("调用BankServiceImpl类的addUserBank方法,实现了给用户创建银行卡信息");
        return bankMapper.addUserBank(bankcard);
    }

    public Bankcard seletcBankByUserId(Bankcard bankcard) {
        logger.info("调用BankServiceImpl类的seletcBankByUserId方法,实现了根据用户的Id查询对应的银行卡信息");
        Bankcard bankUserInfo = new Bankcard();
        bankUserInfo = bankMapper.seletcBankByUserId(bankcard);
        return bankUserInfo;
    }

    public int deleteBankByUserId(Bankcard bankcard) {
        logger.info("调用BankServiceImpl类的deleteBankByUserId方法,实现了根据用户的Id删除对应的银行卡信息");
        return bankMapper.deleteBankByUserId(bankcard);
    }


}
