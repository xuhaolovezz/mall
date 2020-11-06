package com.mall.order.config;

import com.mall.common.annotation.EnableMallSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableMallSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

}
