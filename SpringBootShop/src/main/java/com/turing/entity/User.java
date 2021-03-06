package com.turing.entity;

public class User {
    private int euUserId;
    private String euUserName;
    private String euName;
    private String euPassword;
    private String euSex;
    private String euIdentityCode;
    private String euEmail;
    private String euMobile;
    private String euAddress;
    private double euMoney;
    private int euStatus;
    private Role roles;

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public double getEuMoney() {
        return euMoney;
    }

    public void setEuMoney(double euMoney) {
        this.euMoney = euMoney;
    }

    public int getEuUserId() {
        return euUserId;
    }

    public void setEuUserId(int euUserId) {
        this.euUserId = euUserId;
    }

    public String getEuUserName() {
        return euUserName;
    }

    public void setEuUserName(String euUserName) {
        this.euUserName = euUserName;
    }

    public String getEuName() {
        return euName;
    }

    public void setEuName(String euName) {
        this.euName = euName;
    }

    public String getEuPassword() {
        return euPassword;
    }

    public void setEuPassword(String euPassword) {
        this.euPassword = euPassword;
    }

    public String getEuSex() {
        return euSex;
    }

    public void setEuSex(String euSex) {
        this.euSex = euSex;
    }

    public String getEuIdentityCode() {
        return euIdentityCode;
    }

    public void setEuIdentityCode(String euIdentityCode) {
        this.euIdentityCode = euIdentityCode;
    }

    public String getEuEmail() {
        return euEmail;
    }

    public void setEuEmail(String euEmail) {
        this.euEmail = euEmail;
    }

    public String getEuMobile() {
        return euMobile;
    }

    public void setEuMobile(String euMobile) {
        this.euMobile = euMobile;
    }

    public String getEuAddress() {
        return euAddress;
    }

    public void setEuAddress(String euAddress) {
        this.euAddress = euAddress;
    }

    public int getEuStatus() {
        return euStatus;
    }

    public void setEuStatus(int euStatus) {
        this.euStatus = euStatus;
    }

    @Override
    public String toString() {
        return "User [euUserId=" + euUserId + ", euUserName=" + euUserName + ", euName=" + euName + ", euPassword="
                + euPassword + ", euSex=" + euSex + ", euIdentityCode=" + euIdentityCode + ", euEmail=" + euEmail
                + ", euMobile=" + euMobile + ", euAddress=" + euAddress + ", euMoney=" + euMoney + ", euStatus="
                + euStatus + ", roles=" + roles + "]";
    }

}
// eu_user_id int not null primary key auto_increment,/* ???????????? */
// eu_user_name varchar(20) not null ,/* ???????????? */
// eu_name varchar(20) not null,/* ???????????? */
// eu_password varchar(20) not null,/* ???????????? */
// eu_sex varchar(5) not null,/* ?????? */
// eu_birthday varchar(10),/* ?????? */
// eu_identity_code varchar(60),/* ????????? */
// eu_email varchar(80),/* ?????? */
// eu_mobile varchar(11),/* ????????? */
// eu_address varchar(200),/* ?????? */
// eu_status int not null/* ???????????? 1 ????????? 2 VIP 3 ???????????? */
//
// euUserId int not null primary key autoIncrement,/* ???????????? */
// euUserName varchar(20) not null ,/* ???????????? */
// euName varchar(20) not null,/* ???????????? */
// euPassword varchar(20) not null,/* ???????????? */
// euSex varchar(5) not null,/* ?????? */
// euBirthday varchar(10),/* ?????? */
// euIdentityCode varchar(60),/* ????????? */
// euEmail varchar(80),/* ?????? */
// euMobile varchar(11),/* ????????? */
// euAddress varchar(200),/* ?????? */
// euStatus int not null/* ???????????? 1 ????????? 2 vip 3 ???????????? */