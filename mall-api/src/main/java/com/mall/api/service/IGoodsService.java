package com.mall.api.service;

import com.mall.common.vo.MallResult;
import com.mall.model.dto.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IGoodsService {

    @GetMapping("/{id}")
    MallResult<Goods> findById(@PathVariable Integer id);

}
