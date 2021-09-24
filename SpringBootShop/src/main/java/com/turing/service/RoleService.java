package com.turing.service;


import com.turing.entity.Role;

import java.util.List;

public interface RoleService {

    /**根据用户的账号状态查询用户的权限信息*/
    Role selectByUserStatus(Role role);

    /**查询所有的权限信息*/
    List<Role> selectAllRole();
}
