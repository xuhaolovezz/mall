package com.mall.goods;

import com.mall.goods.service.impl.GoodsServiceImpl;
import com.mall.model.dto.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class MallGoodsApplicationTests {

    @Autowired
    private GoodsServiceImpl goodsService;

    @Test
    public void contextLoads() {
        Goods goods = goodsService.findById(1);
        System.out.println(goods);
    }

}
