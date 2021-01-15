package com.mall.auth.service.impl;

import com.mall.auth.mapper.RoleMapper;
import com.mall.common.vo.MallResult;
import com.mall.model.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> selectRolesByUser(Integer userId, String tenantCode) {
        return roleMapper.selectByUser(userId,tenantCode);
    }

    @Transactional
    public MallResult<Void> createRole(Role role) {
        roleMapper.insert(role);
        return MallResult.success();
    }

}
