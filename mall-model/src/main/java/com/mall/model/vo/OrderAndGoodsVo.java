package com.mall.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderAndGoodsVo {

    // 此次订单要购买的商品
    private List<Integer> goodsList;

    // 用户ID
    private Integer userId;

    // 用户备注
    private String userNote;

    // 选择的收货地址ID
    private Integer addressId;

}
