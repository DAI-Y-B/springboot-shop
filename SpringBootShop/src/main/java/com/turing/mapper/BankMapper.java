package com.turing.mapper;

import com.turing.entity.Bankcard;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BankMapper {
    /**添加用户信息*/
    @Insert("insert into easybuy_bankcard values (null,#{bankcardUserId},#{bankcardNumber},#{bankcardPwd},#{bankcardMoney})")
    @Options(useGeneratedKeys = true)
//自动增长列
    int addUserBank(Bankcard bankcard);

    /** 根据用户Id查询对应的银行卡信息 */
    @Select("select * from easybuy_bankcard where bankcard_user_id = #{bankcardUserId}")
    Bankcard seletcBankByUserId(Bankcard bankcard);

    /**删除对应用户id的银行卡信息*/
    @Delete("delete from easybuy_bankcard where bankcard_user_id = #{bankcardUserId}")
    int deleteBankByUserId(Bankcard bankcard);
}
//bankcard_id int primary key auto_increment /*银行卡编号*/,
//bankcard_user_id int(10) /*用户编号*/,
//bankcard_number int(10) /*银行卡号*/,
//bankcard_pwd varchar(20) /*银行卡密码*/,
//bankcard_money double(10) /*金额*/
//
//bankcardId int primary key autoIncrement /*银行卡编号*/,
//bankcardUserId int(10) /*用户编号*/,
//bankcardNumber int(10) /*银行卡号*/,
//bankcardPwd varchar(20) /*银行卡密码*/,
//bankcardMoney double(10) /*金额*/