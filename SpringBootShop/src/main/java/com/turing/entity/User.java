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
// eu_user_id int not null primary key auto_increment,/* 用户编号 */
// eu_user_name varchar(20) not null ,/* 用户姓名 */
// eu_name varchar(20) not null,/* 用户昵称 */
// eu_password varchar(20) not null,/* 用户密码 */
// eu_sex varchar(5) not null,/* 性别 */
// eu_birthday varchar(10),/* 生日 */
// eu_identity_code varchar(60),/* 身份证 */
// eu_email varchar(80),/* 邮箱 */
// eu_mobile varchar(11),/* 手机号 */
// eu_address varchar(200),/* 地址 */
// eu_status int not null/* 账户状态 1 管理员 2 VIP 3 普通用户 */
//
// euUserId int not null primary key autoIncrement,/* 用户编号 */
// euUserName varchar(20) not null ,/* 用户姓名 */
// euName varchar(20) not null,/* 用户昵称 */
// euPassword varchar(20) not null,/* 用户密码 */
// euSex varchar(5) not null,/* 性别 */
// euBirthday varchar(10),/* 生日 */
// euIdentityCode varchar(60),/* 身份证 */
// euEmail varchar(80),/* 邮箱 */
// euMobile varchar(11),/* 手机号 */
// euAddress varchar(200),/* 地址 */
// euStatus int not null/* 账户状态 1 管理员 2 vip 3 普通用户 */