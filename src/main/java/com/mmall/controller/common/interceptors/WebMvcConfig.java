package com.mmall.controller.common.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //因为要interceptor要注入redis,将拦截器手动注入
    @Bean
    public AuthorityInterceptor authorityInterceptor(){
        return new AuthorityInterceptor();

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor()).addPathPatterns("/manage/**");
    }
}
