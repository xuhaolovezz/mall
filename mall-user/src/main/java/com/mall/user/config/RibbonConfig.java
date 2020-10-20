package com.mall.user.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Bean
    public IRule roundRobinRule() {
        return new RoundRobinRule();
    }

}
