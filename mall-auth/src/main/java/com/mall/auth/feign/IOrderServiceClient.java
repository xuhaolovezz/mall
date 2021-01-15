package com.mall.auth.feign;

import com.mall.api.service.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "mall-zuul-service",path = "/api/order")
public interface IOrderServiceClient extends IOrderService {

}
