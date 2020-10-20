package com.mall.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {

    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }

}
