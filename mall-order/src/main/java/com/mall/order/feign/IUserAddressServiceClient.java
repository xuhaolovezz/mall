package com.mall.order.feign;

import com.mall.api.service.IUserAddressService;
import com.mall.common.CommonConst;
import org.springframework.cloud.openfeign.FeignClient;

// 用户收货地址的feign调用
@FeignClient(name = "MALL-USER-SERVICE",path = CommonConst.URL_PREFIX + "/user_address")
public interface IUserAddressServiceClient extends IUserAddressService {
}
