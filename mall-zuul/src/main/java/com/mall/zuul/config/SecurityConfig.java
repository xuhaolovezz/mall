package com.mall.zuul.config;

import com.mall.common.annotation.EnableMallSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@EnableOAuth2Sso
@Configuration
//@EnableWebSecurity
@EnableMallSecurity
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // TODO 后期路径全部从数据库取
        http.authorizeRequests()
                .antMatchers("/api-auth/open/**").permitAll()
                .antMatchers("/api/order/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/goods/**").permitAll();
    }
}
