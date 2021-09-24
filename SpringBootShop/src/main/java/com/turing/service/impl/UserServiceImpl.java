package com.turing.service.impl;

import com.turing.entity.User;
import com.turing.mapper.UserMapper;
import com.turing.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // 日志
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        logger.info("调用UserServiceImpl类的login方法,实现了用户登陆功能");
        User loginUser = new User();
        loginUser = userMapper.login(user);
        System.out.println("loginUser" + loginUser);
        return loginUser;
    }

    public int regist(User user) {
        logger.info("调用UserServiceImpl类的regist方法,实现了注册功能");
        return userMapper.regist(user);
    }

    public User selectByUserCount(User user) {
        logger.info("调用UserServiceImpl类的regist方法,实现了根据用户账号查询用户是否存在");
        User isUser = userMapper.selectByUserCount(user);
        return isUser;
    }

    public List<User> selectAllUsers() {
        logger.info("调用UserServiceImpl类的selectAllUsers方法,查询所有用户,实现分页效果");
        List<User> allUsers = userMapper.selectAllUsers();
        return allUsers;
    }

    public List<User> selectAllUsersPerPage(Integer currentPage, Integer size) {
        logger.info("调用UserServiceImpl类的selectAllUsers方法,查询所有用户,实现分页效果");
        List<User> allUsersPage = userMapper.selectAllUsersPerPage(currentPage, size);
        return allUsersPage;
    }

    public int updateUser(User user) {
        logger.info("调用UserServiceImpl类的updateUser方法,实现修改用户信息的效果");
        return userMapper.updateUser(user);
    }

    public int deleteUser(User user) {
        logger.info("调用UserServiceImpl类的deleteUser方法,实现删除用户信息的效果");
        return userMapper.deleteUser(user);
    }


}
