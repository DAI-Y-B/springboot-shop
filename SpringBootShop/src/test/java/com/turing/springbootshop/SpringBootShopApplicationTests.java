package com.turing.springbootshop;

import com.turing.entity.User;
import com.turing.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootShopApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void loginTest() {
        User user1 = new User();
        user1.setEuUserName("admin");
        user1.setEuPassword("123");
        User user = userMapper.login(user1);
        System.out.println(user);
    }

}
