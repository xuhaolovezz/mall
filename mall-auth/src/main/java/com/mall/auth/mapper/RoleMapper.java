package com.mall.auth.mapper;

import com.mall.model.dto.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByUser(@Param("userId") Integer userId,
                            @Param("tenantCode") String tenantCode);
}