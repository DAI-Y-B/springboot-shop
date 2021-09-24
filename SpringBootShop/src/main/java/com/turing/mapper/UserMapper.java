package com.turing.mapper;

import com.turing.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {
    /** 根据用户名查询是否存在相同账号 */
    @Select("select * from easybuy_user where eu_user_name = #{euUserName}")
    @Results(id = "replyAndUsers", value = {@Result(column = "reply_id", property = "replyId"),
            @Result(column = "eu_user_id", property = "euUserId"),
            @Result(column = "eu_user_name", property = "euUserName"),
            @Result(column = "eu_name", property = "euName"),
            @Result(column = "eu_password", property = "euPassword"),
            @Result(column = "eu_sex", property = "euSex"),
            @Result(column = "eu_identity_code", property = "euIdentityCode"),
            @Result(column = "eu_mobile", property = "euMobile"),
            @Result(column = "eu_address", property = "euAddress"),
            @Result(column = "eu_money", property = "euMoney"),
            @Result(column = "eu_status", property = "euStatus"),
            @Result(column = "eu_status", property = "roles", many = @Many(fetchType = FetchType.LAZY, select = "com.turing.mapper.RoleMapper.selectByUserStatus")),})
    User selectByUserCount(User user);

    /** 账号登陆 */
    @Select("select * from easybuy_user where eu_user_name = #{euUserName} and eu_password = #{euPassword}")
    User login(User user);

    /** 注册账号 */
    @Insert("insert into easybuy_user values(null,#{euUserName},#{euName},#{euPassword},#{euSex},"
            + "#{euIdentityCode},#{euEmail},#{euMobile},#{euAddress},#{euMoney},#{euStatus})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int regist(User user);

    /** 查询所有用户,实现分页效果 */
    @Select("select * from easybuy_user")
    List<User> selectAllUsers();

    /** 实现分页查询，计算相关的页数位置 */
    @Select("select * from easybuy_user limit #{currentPage},#{size}")
    List<User> selectAllUsersPerPage(@Param("currentPage") Integer currentPage, @Param("size") Integer size);

    /**修改用户信息*/
    @Update("update easybuy_user set eu_user_name=#{euUserName}, eu_name=#{euName}, eu_password=#{euPassword}, eu_sex=#{euSex}, eu_identity_code=#{euIdentityCode}, "
            + "eu_email=#{euEmail}, eu_mobile=#{euMobile}, eu_address=#{euAddress}, eu_money=#{euMoney}, eu_status=#{euStatus} where eu_user_id=#{euUserId}")
    int updateUser(User user);

    /**删除用户信息*/
    @Delete("delete from easybuy_user where eu_user_name=#{euUserName}")
    int deleteUser(User user);
}
// easybuy_user
// eu_user_id /* 用户编号 */
// eu_user_name/* 用户姓名 */
// eu_name/* 用户昵称 */
// eu_password /* 用户密码 */
// eu_sex /* 性别 */
// eu_identity_code/* 身份证 */
// eu_mobile/* 手机号 */
//eu_email/*邮箱*/
// eu_address/* 地址 */
// eu_money /*账户金额*/
// eu_status /* 账户状态 1 管理员 2 VIP 3 普通用户 */

// euUserId /* 用户编号 */
// euUserName/* 用户姓名 */
// euName/* 用户昵称 */
// euPassword /* 用户密码 */
// euSex /* 性别 */
// euIdentityCode/* 身份证 */
// euMobile/* 手机号 */
//euEmail/*邮箱*/
// euAddress/* 地址 */
// euMoney /*账户金额*/
// euStatus /* 账户状态 1 管理员 2 vip 3 普通用户 */