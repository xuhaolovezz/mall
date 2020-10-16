package com.mall.user.controller;

import com.mall.common.CommonConst;
import com.mall.common.vo.MallResult;
import com.mall.model.dto.UserAddress;
import com.mall.user.service.impl.UserAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConst.URL_PREFIX + "/user_address")
public class UserAddressController {

    @Autowired
    private UserAddressServiceImpl userAddressService;

    @GetMapping("/findByUserId")
    public MallResult<List<UserAddress>> findListByUserId(String userId) {
        return MallResult.success(userAddressService.findListByUserId(userId));
    }

    @PostMapping("/create")
    public MallResult<Void> create(@RequestBody UserAddress userAddress) {
        userAddressService.create(userAddress);
        return MallResult.success();
    }

    @PostMapping("/updateDefaultAddress")
    public MallResult<Void> updateDefaultAddress(Integer addressId, Integer userId, Integer isDefault) {
        userAddressService.updateDefaultAddress(addressId, userId, isDefault);
        return MallResult.success();
    }

}
