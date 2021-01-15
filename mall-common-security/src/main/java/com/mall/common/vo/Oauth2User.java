package com.mall.common.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@Builder
//@NoArgsConstructor
public class Oauth2User implements UserDetails {

    private Integer userId;

    private String username;

    private String password;

    private String name;

    private String phone;

    private Integer status;


    Set<SimpleGrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
