package com.mall.goods.mapper;

import com.mall.model.dto.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);

    Goods findById(@Param("id") Integer id);
}