package com.mall.model.vo;

import com.mall.model.dto.Brand;
import com.mall.model.dto.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsVo extends Goods {

    private List<String> descImgList;

    private List<String> simpleImgList;

    private Brand brand;

    private CatVo cat;
}
