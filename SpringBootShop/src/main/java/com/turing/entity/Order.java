package com.turing.entity;

import java.util.Date;

public class Order {
    private int eoId;
    private int eoUserId;
    private String eoUserName;
    private String eoUserAddress;
    private Date eoCreateTime;
    private double eoCost;
    private int eoStatus;
    private int eoType;
    private OrderDetail orderDetails;
    private Ostatus ostatuss;

    public Ostatus getOstatuss() {
        return ostatuss;
    }

    public void setOstatuss(Ostatus ostatuss) {
        this.ostatuss = ostatuss;
    }

    public OrderDetail getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetail orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getEoId() {
        return eoId;
    }

    public void setEoId(int eoId) {
        this.eoId = eoId;
    }

    public int getEoUserId() {
        return eoUserId;
    }

    public void setEoUserId(int eoUserId) {
        this.eoUserId = eoUserId;
    }

    public String getEoUserName() {
        return eoUserName;
    }

    public void setEoUserName(String eoUserName) {
        this.eoUserName = eoUserName;
    }

    public String getEoUserAddress() {
        return eoUserAddress;
    }

    public void setEoUserAddress(String eoUserAddress) {
        this.eoUserAddress = eoUserAddress;
    }

    public Date getEoCreateTime() {
        return eoCreateTime;
    }

    public void setEoCreateTime(Date eoCreateTime) {
        this.eoCreateTime = eoCreateTime;
    }

    public double getEoCost() {
        return eoCost;
    }

    public void setEoCost(double eoCost) {
        this.eoCost = eoCost;
    }

    public int getEoStatus() {
        return eoStatus;
    }

    public void setEoStatus(int eoStatus) {
        this.eoStatus = eoStatus;
    }

    public int getEoType() {
        return eoType;
    }

    public void setEoType(int eoType) {
        this.eoType = eoType;
    }

    @Override
    public String toString() {
        return "Order [eoId=" + eoId + ", eoUserId=" + eoUserId + ", eoUserName=" + eoUserName + ", eoUserAddress="
                + eoUserAddress + ", eoCreateTime=" + eoCreateTime + ", eoCost=" + eoCost + ", eoStatus=" + eoStatus
                + ", eoType=" + eoType + ", orderDetails=" + orderDetails + ", ostatuss=" + ostatuss + "]";
    }

}
// eo_id int not null primary key auto_increment,/* 订单Id */
// eo_user_id varchar(10) not null,/* 订单用户ID */
// eo_user_name varchar(20),/* 订单用户姓名（接收人） */
// eo_user_address varchar(200),/* 订单地址（接受地址） */
// eo_create_time date not null,/* 订单创建时间 */
// eo_cost decimal(12,2) not null,/* 订单金额 */
// eo_status int not null,/* 1 下单 2 审核通过 3 配货 4 送货中 5收获并确认 */
// eo_type int not null/* （付款方式）1 货到付款 2 网上支付 */
//
// eoId int not null primary key autoIncrement,/* 订单id */
// eoUserId varchar(10) not null,/* 订单用户id */
// eoUserName varchar(20),/* 订单用户姓名（接收人） */
// eoUserAddress varchar(200),/* 订单地址（接受地址） */
// eoCreateTime date not null,/* 订单创建时间 */
// eoCost decimal(12,2) not null,/* 订单金额 */
// eoStatus int not null,/* 1 下单 2 审核通过 3 配货 4 送货中 5收获并确认 */
// eoType int not null/* （付款方式）1 货到付款 2 网上支付 */