package com.turing.entity;

import java.util.Date;

public class Vip {
    private int vipId;
    private int vipUserId;
    private Date vipStartTime;
    private Date vipEndTime;

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public int getVipUserId() {
        return vipUserId;
    }

    public void setVipUserId(int vipUserId) {
        this.vipUserId = vipUserId;
    }

    public Date getVipStartTime() {
        return vipStartTime;
    }

    public void setVipStartTime(Date vipStartTime) {
        this.vipStartTime = vipStartTime;
    }

    public Date getVipEndTime() {
        return vipEndTime;
    }

    public void setVipEndTime(Date vipEndTime) {
        this.vipEndTime = vipEndTime;
    }

    @Override
    public String toString() {
        return "Vip [vipId=" + vipId + ", vipUserId=" + vipUserId + ", vipStartTime=" + vipStartTime + ", vipEndTime="
                + vipEndTime + "]";
    }
	
}
//vip_id             int primary key auto_increment 	/*会员编号*/,
//vip_user_id        int(20) 	/*用户编号*/,
//vip_start_time     datetime 	/*vip开始时间*/,
//vip_end_time       datetime 	/*vip结束时间*/
//
//vipId             int primary key autoIncrement 	/*会员编号*/,
//vipUserId        int(20) 	/*用户编号*/,
//vipStartTime     datetime 	/*vip开始时间*/,
//vipEndTime       datetime 	/*vip结束时间*/