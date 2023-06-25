package com.szxxwang.employeemanage.controller;

import com.szxxwang.employeemanage.domain.Admin;
import com.szxxwang.employeemanage.domain.Employee;
import com.szxxwang.employeemanage.service.AdminServiceImpl;
import com.szxxwang.employeemanage.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.controller
 * Date:2023/6/20 21:37
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private DataSource dataSource;

    //实现登录
    @RequestMapping("/index")
    public String show() throws SQLException {
        System.out.println("这个方法被执行！");
        Connection connection = dataSource.getConnection();
        System.out.println("connection:" + connection);
        return "login";
    }

    @RequestMapping(value = "/loginIn")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("name:" + name);
        System.out.println("password:" + password);
        Admin admin = adminService.getAdmin(name, password);
        System.out.println("此方法被执行！");
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo",admin);
        if(admin!=null){
            System.out.println(admin.toString());
//            response.sendRedirect("/employeeList");
            return "redirect:/employeeList";
        }else {
            return "error";
        }
    }

    @RequestMapping("/employeeList")
    public String employeeList(Model md,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
        List<Employee> employeeList = employeeService.getAll();
        md.addAttribute("employeeList",employeeList);
        System.out.println("员工一览方法执行!");
        String path = request.getContextPath();
//        response.sendRedirect(path + "templates/employeeList.html");
        return "employeeList";

    }



}
