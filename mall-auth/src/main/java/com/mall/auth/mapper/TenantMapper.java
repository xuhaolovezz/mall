package com.mall.auth.mapper;

import com.mall.model.dto.Tenant;

public interface TenantMapper {
    int insert(Tenant record);

    int insertSelective(Tenant record);
}