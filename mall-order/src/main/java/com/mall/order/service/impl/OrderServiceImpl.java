package com.mall.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.vo.OrderAndGoodsVo;
import com.mall.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;

    public PageResult<Order> findListByUserId(Integer userId, int pageNum ,int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);

        List<Order> list = orderMapper.findListByUserId(userId);
        return new PageResult<>(pageNum,pageSize,page.getTotal(),list);
    }

    @Transactional
    public void create(OrderAndGoodsVo orderAndGoodsVo) {
        //orderMapper.insert(order);
        log.debug("插入订单数据======》{}");
    }
}
