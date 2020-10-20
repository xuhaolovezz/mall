package com.mall.user.controller;

import com.mall.common.CommonConst;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.dto.SysUser;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.user.service.impl.UserServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping("/findOrderList")
    @HystrixCommand(fallbackMethod = "failCall")
    public MallResult<PageResult<Order>> findOrderList(UserFindOrderVo userVo) {
        RestTemplate restTemplate = new RestTemplate();

//        return restTemplate.postForObject("http://localhost:8881/api/order/findByUserId",)
        return MallResult.success(userService.findOrderList(userVo));
    }

    public MallResult<PageResult<Order>> failCall(UserFindOrderVo userVo) {
        return MallResult.fail("500","服务器请求超时");
    }
}
