package com.mall.api.service;

import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IOrderService {

    // 加了RequestParam即为GET请求，加RequestBody为post请求
    @RequestMapping("/findByUserId")
    MallResult<PageResult<Order>> findByUserId(@RequestParam("userId") Integer userId,
                                               @RequestParam("page") int page,
                                               @RequestParam("pageSize") int pageSize);

}
