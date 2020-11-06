package com.mall.model.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
public class Order {
    private Integer orderId;

    private Integer userId;

    private String num;

    private Integer status;

    private Integer shippingStatus;

    private Integer payStatus;

    private String consignee;

    private String zipcode;

    private String address;

    private String mobile;

    private String shippingCode;

    private String shippingName;

    private BigDecimal goodsPrice;

    private BigDecimal shippingPrice;

    private BigDecimal orderAmount;

    private BigDecimal totalAmount;

    private Date addTime;

    private Date payTime;

    private String userNote;

}