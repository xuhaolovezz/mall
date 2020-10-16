package com.mall.user;


import com.mall.model.dto.SysUser;
import com.mall.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MallUserApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test() {
        SysUser user = userService.findById(1);
        System.out.println(user.getName());
    }
}
