package com.mall.order.mapper;

import com.mall.model.dto.OrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper {
    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> findByOrderId(@Param("orderId") Integer orderId);
}