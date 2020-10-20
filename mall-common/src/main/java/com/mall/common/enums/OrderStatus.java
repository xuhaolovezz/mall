package com.mall.common.enums;

/**
 * 订单状态(0-待确认，1-已确认，2-已收货，3-已取消，4-已完成、5-已作废)
 */
public enum OrderStatus {
    NOT_CONFIRM(0,"待确认"),
    CONFIRMED(1,"已确认"),
    RECEIVED(2,"已收货"),
    CANCELED(3,"已取消"),
    COMPLETED(4,"已完成"),
    INVALID(5,"已作废")
    ;

    private int code;

    private String desc;

    OrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
