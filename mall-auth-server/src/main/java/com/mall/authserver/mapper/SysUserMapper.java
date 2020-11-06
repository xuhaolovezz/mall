package com.mall.authserver.mapper;

import com.mall.common.vo.Oauth2User;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

    Oauth2User selectByUsername(@Param("username")String username);

}
