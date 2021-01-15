package com.mall.goods.controller;

import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.goods.service.impl.GoodsServiceImpl;
import com.mall.model.dto.Goods;
import com.mall.model.vo.GoodsFindVo;
import com.mall.model.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsServiceImpl goodsService;

    @GetMapping("/{id}")
    public MallResult<GoodsVo> findById(@PathVariable Integer id) {
        return MallResult.success(goodsService.findById(id));
    }

    @GetMapping("/findList")
    public MallResult<PageResult<Goods>> findList(GoodsFindVo vo) {
        return MallResult.success(goodsService.findList(vo));
    }

    @PostMapping("/reduceGoodsStock")
    public MallResult<Void> reduceStock(Integer goodsId,Integer count) {
        return goodsService.reduceGoodsStock(goodsId,count);
    }
}
