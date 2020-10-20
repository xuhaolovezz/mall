package com.mall.goods;

import com.mall.goods.service.impl.GoodsServiceImpl;
import com.mall.model.dto.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallGoodsApplicationTests {

    @Autowired
    private GoodsServiceImpl goodsService;

    @Test
    void contextLoads() {
        Goods goods = goodsService.findById(1);
        System.out.println(goods);
    }

}
