package com.mall.user.feign;

import com.mall.api.service.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "MALL-ZUUL",path = "/api-order")
public interface IOrderServiceClient extends IOrderService {

}
