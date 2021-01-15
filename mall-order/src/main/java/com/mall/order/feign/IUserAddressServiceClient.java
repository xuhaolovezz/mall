package com.mall.order.feign;

import com.mall.api.service.IUserAddressService;
import org.springframework.cloud.openfeign.FeignClient;

// 用户收货地址的feign调用
@FeignClient(name = "mall-zuul-service",path = "/api/user")
public interface IUserAddressServiceClient extends IUserAddressService {
}
