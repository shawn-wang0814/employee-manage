package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.Admin;
import com.szxxwang.employeemanage.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/20 21:49
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Service
public class AdminServiceImpl implements AmdinService {


    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdmin(String name, String password) {
//        System.out.println("service name:" + name);
//        System.out.println("service password:" + password);
        List<Admin> admins = adminMapper.getAdmin(name,password);
//        for(Admin admin:admins){
//            System.out.println(admin.toString());
//        }
        Admin admin = admins.get(0);
        System.out.println(admin.toString());
        return admin;
    }
}
