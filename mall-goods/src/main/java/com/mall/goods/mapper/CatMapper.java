package com.mall.goods.mapper;

import com.mall.model.dto.Cat;
import org.apache.ibatis.annotations.Param;

public interface CatMapper {
    int insert(Cat cat);

    Cat findById(@Param("id") Integer id);
}
