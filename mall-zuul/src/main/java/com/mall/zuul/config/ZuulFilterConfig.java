package com.mall.zuul.config;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.mall.zuul.fallback.ZuulFallBackProvider;
import com.mall.zuul.filter.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {


    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public ZuulFallBackProvider zuulFallBackProvider() {
        ZuulFallBackProvider provider = new ZuulFallBackProvider();
        ZuulBlockFallbackManager.registerProvider(provider);
        return provider;
    }

//    @Bean
//    public ZuulFilter sentinelZuulPreFilter() {
//        // We can also provider the filter order in the constructor.
//        return new SentinelZuulPreFilter();
//    }
//
//    @Bean
//    public ZuulFilter sentinelZuulPostFilter() {
//        return new SentinelZuulPostFilter();
//    }
//
//    @Bean
//    public ZuulFilter sentinelZuulErrorFilter() {
//        return new SentinelZuulErrorFilter();
//    }

}
