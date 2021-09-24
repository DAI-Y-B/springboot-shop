package com.turing.entity;

public class Bankcard {
    private int bankcardId;
    private int bankcardUserId;
    private String bankcardNumber;
    private String bankcardPwd;
    private double bankcardMoney;

    public int getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(int bankcardId) {
        this.bankcardId = bankcardId;
    }

    public int getBankcardUserId() {
        return bankcardUserId;
    }

    public void setBankcardUserId(int bankcardUserId) {
        this.bankcardUserId = bankcardUserId;
    }

    public String getBankcardNumber() {
        return bankcardNumber;
    }

    public void setBankcardNumber(String bankcardNumber) {
        this.bankcardNumber = bankcardNumber;
    }

    public String getBankcardPwd() {
        return bankcardPwd;
    }

    public void setBankcardPwd(String bankcardPwd) {
        this.bankcardPwd = bankcardPwd;
    }

    public double getBankcardMoney() {
        return bankcardMoney;
    }

    public void setBankcardMoney(double bankcardMoney) {
        this.bankcardMoney = bankcardMoney;
    }

    @Override
    public String toString() {
        return "Bankcard [bankcardId=" + bankcardId + ", bankcardUserId=" + bankcardUserId + ", bankcardNumber="
                + bankcardNumber + ", bankcardPwd=" + bankcardPwd + ", bankcardMoney=" + bankcardMoney + "]";
    }

}
// bankcard_id int primary key auto_increment /*银行卡编号*/,
// bankcard_user_id int(10) /*用户编号*/,
// bankcard_number int(10) /*银行卡号*/,
// bankcard_pwd varchar(20) /*银行卡密码*/,
// bankcard_money double(10) /*金额*/
//
// bankcardId int primary key autoIncrement /*银行卡编号*/,
// bankcardUserId int(10) /*用户编号*/,
// bankcardNumber int(10) /*银行卡号*/,
// bankcardPwd varchar(20) /*银行卡密码*/,
// bankcardMoney double(10) /*金额*/