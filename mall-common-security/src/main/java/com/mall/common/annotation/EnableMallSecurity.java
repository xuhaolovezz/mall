package com.mall.common.annotation;

import com.mall.common.config.ResourceServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ResourceServerConfig.class)
public @interface EnableMallSecurity {
}
