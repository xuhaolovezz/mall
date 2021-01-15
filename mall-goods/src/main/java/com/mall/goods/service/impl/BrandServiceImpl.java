package com.mall.goods.service.impl;

import com.mall.goods.mapper.BrandMapper;
import com.mall.model.dto.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl {

    @Autowired
    private BrandMapper brandMapper;

    public Brand findById(Integer brandId){
        return brandMapper.findById(brandId);
    }
}
