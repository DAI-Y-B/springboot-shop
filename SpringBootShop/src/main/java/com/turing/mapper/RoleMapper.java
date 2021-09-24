package com.turing.mapper;

import com.turing.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**根据用户的账号状态查询用户的权限信息*/
    @Select("select * from easybuy_role where role_id = #{roleId}")
    Role selectByUserStatus(Role role);

    /**查询所有的权限信息*/
    @Select("select * from easybuy_role")
    List<Role> selectAllRole();
}
//easybuy_role
//role_id int primary key auto_increment /*角色编号*/,
//role_name varchar(30) /*角色名称*/
//
//roleId int primary key autoIncrement /*角色编号*/,
//roleName varchar(30) /*角色名称*/