package com.turing.entity;

public class Role {
    private int roleId;
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
    }

}
// role_id int primary key auto_increment /*角色编号*/,
// role_name varchar(30) /*角色名称*/
// roleId int primary key autoIncrement /*角色编号*/,
// roleName varchar(30) /*角色名称*/