package com.szxxwang.employeemanage.interceptor;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.interceptor
 * Date:2023/6/23 13:55
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *新建拦截器
 *
 * 自定义拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截处理代码
        //静态资源不被拦截器拦截
        String uri = request.getRequestURI();
        if (uri.endsWith("js")||uri.endsWith("css")||uri.endsWith("jpg")||uri.endsWith("svg")||uri.endsWith("jpg")||uri.endsWith("png")){
            return true ;
        }
        HttpSession session = request.getSession();
        // 获取用户信息，如果没有用户信息直接返回提示信息
        Object loginInfo = session.getAttribute("loginInfo");
        if (loginInfo == null) {
            request.setAttribute("msg","请先登录！");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        } else {
            return true;
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}