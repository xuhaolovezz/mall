package com.mall.auth.mapper;

import com.mall.model.dto.Resource;

public interface ResourceMapper {
    int insert(Resource record);

    int insertSelective(Resource record);
}