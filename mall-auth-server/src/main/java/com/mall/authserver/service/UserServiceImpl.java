package com.mall.authserver.service;

import com.mall.authserver.mapper.SysUserMapper;
import com.mall.common.vo.Oauth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private SysUserMapper userMapper;

    @Transactional
    public int create(Oauth2User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int res = userMapper.insert(user);
        log.debug("插入一条用户数据===》{}",user);
        return res;
    }
}
