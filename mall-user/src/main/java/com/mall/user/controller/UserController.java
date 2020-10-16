package com.mall.user.controller;

import com.mall.common.CommonConst;
import com.mall.common.vo.MallResult;
import com.mall.model.dto.SysUser;
import com.mall.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConst.URL_PREFIX + "/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public MallResult<SysUser> findById(@PathVariable("id") Integer id) {
        return MallResult.success(userService.findById(id));
    }

    @PostMapping("/register")
    public MallResult<SysUser> register(@RequestBody SysUser sysUser) {
        userService.create(sysUser);
        return MallResult.success();
    }

}
