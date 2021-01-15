package com.mall.user.controller;

import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.dto.SysUser;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public MallResult<SysUser> findById(@PathVariable("id") Integer id) {
        return MallResult.success(userService.findById(id));
    }

    @GetMapping("/findOrderList")
    public MallResult<PageResult<Order>> findOrderList(UserFindOrderVo userVo) {
        PageResult<Order> list = userService.findOrderList(userVo);
        return MallResult.success(list);
    }

}
