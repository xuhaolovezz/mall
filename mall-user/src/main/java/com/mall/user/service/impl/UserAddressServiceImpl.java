package com.mall.user.service.impl;

import com.mall.model.dto.UserAddress;
import com.mall.user.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAddressServiceImpl {

    @Autowired
    private UserAddressMapper userAddressMapper;

    public List<UserAddress> findListByUserId(String userId) {
        return userAddressMapper.findListByUserId(userId);
    }

    @Transactional
    public Integer create(UserAddress userAddress) {
        return userAddressMapper.insert(userAddress);
    }

    @Transactional
    public void updateDefaultAddress(Integer addressId, Integer userId, Integer isDefault) {
        if (isDefault == 1) {
            userAddressMapper.clearDefaultAddress(userId);
        }

        userAddressMapper.setDefaultAddress(addressId,isDefault);
    }


}
