package com.mall.model.vo;

import com.mall.model.dto.Goods;
import lombok.Data;

import java.util.List;

@Data
public class OrderAndGoodsVo {

    // 此次订单要购买的商品
    private List<Goods> goodsList;

    // 用户ID
    private Integer userId;

}
