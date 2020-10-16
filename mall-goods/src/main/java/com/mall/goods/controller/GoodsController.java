package com.mall.goods.controller;

import com.mall.common.CommonConst;
import com.mall.common.vo.MallResult;
import com.mall.goods.service.impl.GoodsServiceImpl;
import com.mall.model.dto.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonConst.URL_PREFIX + "/goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping("/{id}")
    public MallResult<Goods> findById(@PathVariable Integer id) {
        return MallResult.success(goodsService.findById(id));
    }

}
