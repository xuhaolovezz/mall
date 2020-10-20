package com.mall.user.mapper;

import com.mall.model.dto.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressMapper {
    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress findById(@Param("addressId") Integer addressId);

    List<UserAddress> findListByUserId(@Param("userId") Integer userId);

    void clearDefaultAddress(@Param("userId") Integer userId);

    void setDefaultAddress(@Param("addressId") Integer addressId,
                           @Param("isDefault") Integer isDefault);
}