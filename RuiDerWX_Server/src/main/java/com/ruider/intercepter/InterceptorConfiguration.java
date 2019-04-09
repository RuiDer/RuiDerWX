package com.ruider.intercepter;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by mahede on 2018/12/19.
 */
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new GlobalInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/**.html");
        ir.excludePathPatterns("/**/fonts/*");
        ir.excludePathPatterns("/**/*.css");
        ir.excludePathPatterns("/**/*.js");
        ir.excludePathPatterns("/**/*.png");
        ir.excludePathPatterns("/**/*.jpg");
        ir.excludePathPatterns("/**/*.gif");

        // 注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
}
