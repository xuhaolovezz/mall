package com.mall.common.config;

import com.mall.common.hander.MallAccessDeniedHandler;
import com.mall.common.hander.MallAuthenticationFailHandler;
import com.mall.common.serialize.JacksonSerializationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    @SuppressWarnings("")
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore (){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("auth-token:");
        // 自定义token序列化的类
        redisTokenStore.setSerializationStrategy(new JacksonSerializationStrategy());
        return redisTokenStore;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        // 从Redis中解析token的用户信息
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(redisTokenStore());
        return tokenService;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 解析token的类
        resources.tokenServices(tokenService());

        // 拒绝访问处理器
        resources.accessDeniedHandler(new MallAccessDeniedHandler());

        // 认证失败处理器
        resources.authenticationEntryPoint(new MallAuthenticationFailHandler());
    }
}
