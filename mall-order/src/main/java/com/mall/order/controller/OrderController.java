package com.mall.order.controller;

import com.mall.common.CommonConst;
import com.mall.order.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CommonConst.URL_PREFIX + "/order")
public class OrderController {

    @Autowired
    public OrderServiceImpl orderService;
}
