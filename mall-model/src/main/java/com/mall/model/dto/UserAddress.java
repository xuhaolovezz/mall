package com.mall.model.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserAddress {
    private Integer addressId;

    private Integer userId;

    private String consignee;

    private String email;

    private String area;

    private String address;

    private String zipcode;

    private String mobile;

    private Boolean isDefault;

    private Boolean isPickup;

    private String label;

}