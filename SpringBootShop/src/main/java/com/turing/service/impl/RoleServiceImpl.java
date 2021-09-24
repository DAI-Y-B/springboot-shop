package com.turing.service.impl;

import com.turing.entity.Role;
import com.turing.mapper.RoleMapper;
import com.turing.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    // 日志
    Logger logger = Logger.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;

    public Role selectByUserStatus(Role role) {
        logger.info("调用RoleServiceImpl类的selectByUserStatus方法,实现了给用户创建银行卡信息");
        Role roleInfo = new Role();
        roleInfo = roleMapper.selectByUserStatus(role);
        return roleInfo;
    }

    public List<Role> selectAllRole() {
        List<Role> allRoles = roleMapper.selectAllRole();
        return allRoles;
    }

}
