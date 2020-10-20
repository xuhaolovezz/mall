package com.mall.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFindOrderVo extends PageVo{

    private Integer userId;

}
