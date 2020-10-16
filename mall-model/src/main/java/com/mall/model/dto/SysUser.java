package com.mall.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class SysUser {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private Boolean status;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
}