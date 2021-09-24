package com.turing.service;

import com.turing.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    /** 账号登陆 */
    User login(User user);

    /**根据用户名查询是否存在相同账号 */
    User selectByUserCount(User user);

    /**注册账号 */
    int regist(User user);

    /** 查询所有用户,实现分页效果 */
    List<User> selectAllUsers();

    /**实现分页查询，计算相关的页数位置*/
    List<User> selectAllUsersPerPage(@RequestParam("currentPage") Integer currentPage, @RequestParam("size") Integer size);

    /**修改用户信息*/
    int updateUser(User user);

    /**删除用户信息*/
    int deleteUser(User user);
}
