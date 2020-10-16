package com.mall.order.mapper;

import com.mall.model.dto.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);

    List<Order> findListByUserId(@Param("userId") Integer userId);
}