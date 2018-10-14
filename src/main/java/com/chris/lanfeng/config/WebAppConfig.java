package com.chris.lanfeng.config;

import com.chris.lanfeng.web.token.DefaultTokenUserService;
import com.langu.authorization.interceptor.AuthIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ben on 16/9/6.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DefaultTokenUserService tokenUserService() {
        return new DefaultTokenUserService();
    }

    @Bean
    public AuthIntercepter authIntercepter() {
        AuthIntercepter intercepter = new AuthIntercepter();
        intercepter.setTokenParamKey("token");
        intercepter.setUserIdKey("userId");
        intercepter.setTokenUserService(tokenUserService());
        return intercepter;
    }


}
