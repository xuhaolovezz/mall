package com.mall.user.service.impl;

import com.mall.model.dto.SysUser;
import com.mall.user.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl{

    @Autowired
    private SysUserMapper userMapper;

    public SysUser findById(Integer id) {
        return userMapper.findById(id);
    }

    @Transactional
    public int create(SysUser user) {
        int res = userMapper.insert(user);
        log.debug("插入一条用户数据===》{}",user);
        return res;
    }
}
