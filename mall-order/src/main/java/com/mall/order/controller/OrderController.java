package com.mall.order.controller;

import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.dto.OrderGoods;
import com.mall.model.vo.AddOrderVo;
import com.mall.model.vo.OrderAndGoodsVo;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public OrderServiceImpl orderService;

    @PostMapping("/createOrder")
    public MallResult<Void> createOrder(@RequestBody AddOrderVo vo) {
        orderService.create(vo);
        return MallResult.success();
    }

    @GetMapping("/findByUserId")
    public MallResult<PageResult<Order>> findByUserId(UserFindOrderVo vo) {
        return MallResult.success(orderService.findListByUserId(vo));
    }


    @GetMapping("/findByCurrentUser")
    public MallResult<PageResult<OrderAndGoodsVo>> findByCurrentUser() {
        return MallResult.success(orderService.findByCurrentUser());
    }

    @GetMapping("/findGoodsByOrderId")
    public MallResult<List<OrderGoods>> findGoodsByOrderId(Integer orderId) {
        return MallResult.success(orderService.findGoodsByOrderId(orderId));
    }
}
