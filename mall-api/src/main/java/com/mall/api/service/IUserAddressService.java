package com.mall.api.service;

import com.mall.common.vo.MallResult;
import com.mall.model.dto.UserAddress;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUserAddressService {

    @GetMapping("/findByUserId")
    MallResult<List<UserAddress>> findByUserId(String userId);

    @GetMapping("/{id}")
    MallResult<UserAddress> findById(@PathVariable Integer id);

}
