package com.mall.goods.mapper;

import com.mall.model.dto.Brand;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper {
    int insert(Brand brand);

    Brand findById(@Param("id") Integer id);
}
