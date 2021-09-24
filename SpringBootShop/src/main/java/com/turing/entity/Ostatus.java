package com.turing.entity;

public class Ostatus {
    private int statusId;
    private String statusName;

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Ostatus [statusId=" + statusId + ", statusName=" + statusName + "]";
    }

}
// status_id int primary key auto_increment /*角色编号*/,
// status_name varchar(30) /*角色名称*/
//
// statusId int primary key autoIncrement /*角色编号*/,
// statusName varchar(30) /*角色名称*/