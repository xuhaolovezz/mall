package com.mall.order.mapper;

import com.mall.model.dto.OrderGoods;

public interface OrderGoodsMapper {
    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);
}