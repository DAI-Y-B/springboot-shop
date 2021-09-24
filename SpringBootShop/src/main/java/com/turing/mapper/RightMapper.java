package com.turing.mapper;

import com.turing.entity.Right;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RightMapper {

    /**添加用户权限信息*/
    @Insert("insert into  easybuy_right values(null,#{rightRoleId},#{rightUserId})")
    @Options(useGeneratedKeys = true)
//自动增长列
    int addRight(Right right);

    /**根据用户Id查询用户的权限信息*/
    @Select("select * from easybuy_right where right_userId = #{rightUserId}")
    Right selectByUserId(Right right);

    /**根据用户Id删除对应用户的权限信息*/
    @Delete("delete from easybuy_right where right_userId = #{rightUserId}")
    int deleteByUserId(Right right);
}
//easybuy_right
//right_id int /*权限关联编号*/,
//right_roleId int(20) /*角色编号*/,
//right_userId int(20) /*用户编号*/
//
//rightId int /*权限关联编号*/,
//rightRoleId int(20) /*角色编号*/,
//rightUserId int(20) /*用户编号*/