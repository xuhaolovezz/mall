package com.mall.zuul.oauth2.config;

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
        http.authorizeRequests()
                .antMatchers("/oauth_login").permitAll()
                .antMatchers("/api-goods/**").permitAll();
    }
}
