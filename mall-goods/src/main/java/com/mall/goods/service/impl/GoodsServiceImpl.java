package com.mall.goods.service.impl;

import com.mall.goods.mapper.GoodsMapper;
import com.mall.model.dto.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoodsServiceImpl {

    @Autowired
    private GoodsMapper goodsMapper;

    public Goods findById(Integer goodsId) {
        return goodsMapper.findById(goodsId);
    }

}
