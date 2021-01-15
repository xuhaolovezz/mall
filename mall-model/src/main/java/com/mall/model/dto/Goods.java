package com.mall.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
public class Goods {
    private Integer goodsId;

    private Integer catId;

    private String num;

    private String name;

    private Integer brandId;

    private String desc;

    private Integer isOnSale;

    private Date onSaleTime;

    private BigDecimal marketPrice;

    private BigDecimal salePrice;

    private Integer commentCount;

    private Integer saleCount;

    private Integer stock;

    private String coverImg;

    private String descImg;

    private String simpleImg;
}