package com.mall.order.feign;

import com.mall.api.service.IGoodsService;
import com.mall.common.CommonConst;
import org.springframework.cloud.openfeign.FeignClient;

// 商品服务的feign调用
@FeignClient(name = "MALL-GOODS-SERVICE",path = CommonConst.URL_PREFIX + "/goods")
public interface IGoodsServiceClient extends IGoodsService {
}
