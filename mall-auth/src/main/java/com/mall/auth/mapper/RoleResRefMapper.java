package com.mall.auth.mapper;

import com.mall.model.dto.RoleResRef;

public interface RoleResRefMapper {
    int insert(RoleResRef record);

    int insertSelective(RoleResRef record);
}