package com.mall.user.service.impl;

import com.mall.common.exception.BusinessException;
import com.mall.common.vo.MallResult;
import com.mall.common.vo.PageResult;
import com.mall.model.dto.Order;
import com.mall.model.dto.SysUser;
import com.mall.model.vo.UserFindOrderVo;
import com.mall.user.feign.IOrderServiceClient;
import com.mall.user.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl{

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private IOrderServiceClient orderServiceClient;

    public SysUser findById(Integer id) {
        return userMapper.findById(id);
    }

    @Transactional
    public int create(SysUser user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int res = userMapper.insert(user);
        log.debug("插入一条用户数据===》{}",user);
        return res;
    }

    public PageResult<Order> findOrderList(UserFindOrderVo userVo) {
        MallResult<PageResult<Order>> result = orderServiceClient.findByUserId(userVo.getUserId(),
                userVo.getPage(),userVo.getPageSize());
        if (!result.isSuccess()) {
            log.error("查询用户订单失败，用户ID为：{}，错误消息为：{}",userVo.getUserId(),result.getMsg());
            throw new BusinessException(result.getMsg());
        }
        return result.getData();
    }
}
