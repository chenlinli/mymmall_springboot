package com.mmall.controller.common.filters;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.swing.text.TabExpander;
import java.util.ArrayList;

//@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<SessionExpireFilter> sessionExpireFilter(){
//        FilterRegistrationBean<SessionExpireFilter> sessionExpireFilterFilterRegistrationBean = new FilterRegistrationBean<>(new SessionExpireFilter());
//        sessionExpireFilterFilterRegistrationBean.setName("sessionExpireFilter");
//        ArrayList<String> urls = new ArrayList<>();
//        urls.add("*.do");
//        sessionExpireFilterFilterRegistrationBean.setUrlPatterns(urls);
//        sessionExpireFilterFilterRegistrationBean.setOrder(1);
//        return sessionExpireFilterFilterRegistrationBean;
//    }

    @Bean
    public FilterRegistrationBean<TestFilterOrder> testFilterOrder(){
        FilterRegistrationBean<TestFilterOrder> sessionExpireFilterFilterRegistrationBean = new FilterRegistrationBean<>(new TestFilterOrder());
        sessionExpireFilterFilterRegistrationBean.setName("testFilterOrder");
        ArrayList<String> urls = new ArrayList<>();
        urls.add("*.do");
        sessionExpireFilterFilterRegistrationBean.setUrlPatterns(urls);
        sessionExpireFilterFilterRegistrationBean.setOrder(3);
        return sessionExpireFilterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<TestFilterOrder2> testFilterOrder2(){
        FilterRegistrationBean<TestFilterOrder2> sessionExpireFilterFilterRegistrationBean = new FilterRegistrationBean<>(new TestFilterOrder2());
        sessionExpireFilterFilterRegistrationBean.setName("testFilterOrder2");
        ArrayList<String> urls = new ArrayList<>();
        urls.add("*.do");
        sessionExpireFilterFilterRegistrationBean.setUrlPatterns(urls);
        sessionExpireFilterFilterRegistrationBean.setOrder(2);
        return sessionExpireFilterFilterRegistrationBean;
    }
}
