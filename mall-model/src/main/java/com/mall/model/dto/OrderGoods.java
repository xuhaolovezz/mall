package com.mall.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
public class OrderGoods {
    private Integer recId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private String goodsSn;

    private Short goodsNum;

    private BigDecimal marketPrice;

    private BigDecimal goodsPrice;

    private String barCode;

    private Integer isComment;

    private Integer isSend;

    private Integer deliveryId;

    private String sku;

    private String originalImg;

}