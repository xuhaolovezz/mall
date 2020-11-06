package com.mall.user.config;

import com.mall.common.annotation.EnableMallSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableMallSecurity
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

}
