package com.szxxwang.employeemanage.interceptor;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.interceptor
 * Date:2023/6/23 13:57
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */


/**
 * 注册拦截器
 * 新建配置类继承WebMvcConfigurerAdapter类，重写addInterceptors方法
 * 既然要增加自己的拦截器，那当然要得到springboot加入拦截器的入口，然后把我们自己写的拦截器也注册到springboot中让其起作用
 * 需要加入@Configuration注解，在springboot启动的时候就会该配置类就会被扫描并加载，从而将我们的拦截器注册进去。这时候的拦截器已经可以正常工作了
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己的拦截器并设置拦截的请求路径
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**") //拦截的路径
                .excludePathPatterns("/index","/loginIn"); //排除的路径
    }


//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**");
//        super.addResourceHandlers(registry);
//    }
//
//    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index").setViewName("login");
//        super.addViewControllers(registry);
//    }
}