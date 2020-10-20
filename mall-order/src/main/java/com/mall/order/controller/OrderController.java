package com.mall.order.controller;

import com.mall.common.CommonConst;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.vo.OrderAndGoodsVo;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CommonConst.URL_PREFIX + "/order")
public class OrderController {

    @Autowired
    public OrderServiceImpl orderService;

    @PostMapping("/createOrder")
    public MallResult<Void> createOrder(@RequestBody OrderAndGoodsVo vo) {
        orderService.create(vo);
        return MallResult.success();
    }

    @GetMapping("/findByUserId")
    public MallResult<PageResult<Order>> findByUserId(UserFindOrderVo vo) {
        return MallResult.success(orderService.findListByUserId(vo));
    }

}
