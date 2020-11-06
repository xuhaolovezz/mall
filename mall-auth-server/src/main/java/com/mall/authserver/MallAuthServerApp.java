package com.mall.authserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
@MapperScan("com.mall.authserver.mapper")
public class MallAuthServerApp {

    public static void main(String[] args) {
        SpringApplication.run(MallAuthServerApp.class, args);
    }

}
