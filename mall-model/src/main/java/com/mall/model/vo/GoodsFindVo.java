package com.mall.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsFindVo extends PageVo {

    private String goodsName;

}
