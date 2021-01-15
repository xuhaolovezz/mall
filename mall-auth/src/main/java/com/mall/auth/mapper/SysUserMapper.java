package com.mall.auth.mapper;

import com.mall.model.dto.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser findById(@Param("id") Integer id);
}