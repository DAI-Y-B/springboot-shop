package com.turing.entity;

public class Right {
    private int rightId;
    private int rightRoleId;
    private int rightUserId;

    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    public int getRightRoleId() {
        return rightRoleId;
    }

    public void setRightRoleId(int rightRoleId) {
        this.rightRoleId = rightRoleId;
    }

    public int getRightUserId() {
        return rightUserId;
    }

    public void setRightUserId(int rightUserId) {
        this.rightUserId = rightUserId;
    }

    @Override
    public String toString() {
        return "Right [rightId=" + rightId + ", rightRoleId=" + rightRoleId + ", rightUserId=" + rightUserId + "]";
    }

}
// right_id int /*权限关联编号*/,
// right_roleId int(20) /*角色编号*/,
// right_userId int(20) /*用户编号*/
//
// rightId int /*权限关联编号*/,
// rightRoleId int(20) /*角色编号*/,
// rightUserId int(20) /*用户编号*/