package com.mall.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableZuulProxy
public class MallZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(MallZuulApp.class, args);
    }

}
