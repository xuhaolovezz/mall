package com.mall.goods;

import com.mall.goods.service.impl.CatServiceImpl;
import com.mall.model.vo.CatVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallGoodsApplication.class)
public class CatServiceTest {

    @Autowired
    private CatServiceImpl catService;

    @Test
    public void test() {
        CatVo catVo = catService.findByIdDeep(7);
        System.out.println(catVo);
    }

}
