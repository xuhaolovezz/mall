package com.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableZuulProxy
public class MallGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(MallGatewayApp.class, args);
    }

}
