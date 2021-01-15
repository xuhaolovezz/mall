package com.mall.auth.mapper;

import com.mall.model.dto.UserRoleRef;

public interface UserRoleRefMapper {
    int insert(UserRoleRef record);

    int insertSelective(UserRoleRef record);
}