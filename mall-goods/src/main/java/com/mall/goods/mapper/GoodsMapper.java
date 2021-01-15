package com.mall.goods.mapper;

import com.mall.model.dto.Goods;
import com.mall.model.vo.GoodsFindVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);

    Goods findById(@Param("id") Integer id);

    List<Goods> findList(GoodsFindVo vo);

    int updateStock(@Param("goodsId") Integer goodsId, @Param("count") Integer count);
}