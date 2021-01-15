package com.mall.api.service;

import com.mall.common.vo.MallResult;
import com.mall.model.vo.GoodsVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IGoodsService {

    @GetMapping("/goods/{id}")
    MallResult<GoodsVo> findById(@PathVariable Integer id);

    @PostMapping("/goods/reduceGoodsStock")
    MallResult<Void> reduceGoodsStock(@RequestParam("goodsId")Integer goodsId,
                                      @RequestParam("count")Integer count);
}
