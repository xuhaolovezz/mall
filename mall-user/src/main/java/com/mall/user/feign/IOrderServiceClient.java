package com.mall.user.feign;

import com.mall.api.service.IOrderService;
import com.mall.common.CommonConst;
import com.mall.user.config.RibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "MALL-ORDER-SERVICE",path = CommonConst.URL_PREFIX + "/order")
// 负载均衡订单服务
@RibbonClient(name = "MALL-ORDER-SERVICE",configuration = RibbonConfig.class)
public interface IOrderServiceClient extends IOrderService {

}
