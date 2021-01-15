package com.mall.authserver.controller;

import com.mall.authserver.service.UserServiceImpl;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.Oauth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("/register")
    public MallResult<Oauth2User> register(@RequestBody Oauth2User sysUser) {
        userService.create(sysUser);
        return MallResult.success();
    }
}
