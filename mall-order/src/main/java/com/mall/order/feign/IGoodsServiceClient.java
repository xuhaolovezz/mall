package com.mall.order.feign;

import com.mall.api.service.IGoodsService;
import org.springframework.cloud.openfeign.FeignClient;

// 商品服务的feign调用
@FeignClient(name = "mall-zuul-service",path = "/api/goods")
public interface IGoodsServiceClient extends IGoodsService {
}
