package com.turing.entity;

public class Category {
    private int cId;
    private String cName;
    private int cParentId;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcParentId() {
        return cParentId;
    }

    public void setcParentId(int cParentId) {
        this.cParentId = cParentId;
    }

    @Override
    public String toString() {
        return "Category [cId=" + cId + ", cName=" + cName + ", cParentId=" + cParentId + "]";
    }

    public Category(int cId, String cName, int cParentId) {
        super();
        this.cId = cId;
        this.cName = cName;
        this.cParentId = cParentId;
    }

    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

}
// c_id int not null primary key auto_increment,/* 编号 */
// c_name varchar(20) not null, /* 名字 */
// c_parent_id int /* 父分类 */
//
// cId int not null primary key autoIncrement,/* 编号 */
// cName varchar(20) not null, /* 名字 */
// cParentId int /* 父分类 */