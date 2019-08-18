package com.mmall.controller.common.filters;

import com.alibaba.fastjson.JSON;
import com.mmall.common.Const;
import com.mmall.pojo.User;
import com.mmall.service.RedisService;
import com.mmall.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "sessionExpireFilter",urlPatterns = "*.do" )
public class SessionExpireFilter implements Filter {

    @Autowired
    private RedisService redisService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String loginToken = CookieUtil.readLoginToken(request);
        if(StringUtils.isNotEmpty(loginToken)){
            //呐user
            String userJsonStr = redisService.get(loginToken);
            User user = (User) JSON.parseObject(userJsonStr,User.class);
            if(user!=null){
                //重置过期时间
                redisService.expire(loginToken, Const.RedisCacheExtime.REDIS_SESSION_TIME);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
