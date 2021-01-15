package com.mall.model.vo;

import com.mall.model.dto.Order;
import com.mall.model.dto.OrderGoods;
import lombok.Data;

import java.util.List;

@Data
public class OrderAndGoodsVo {
    private Order order;
    private List<OrderGoods> goodsList;
}
