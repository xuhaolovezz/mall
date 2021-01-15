package com.mall.model.vo;

import com.mall.model.dto.Cat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CatVo extends Cat {

    private List<Cat> parentCatList;

}
