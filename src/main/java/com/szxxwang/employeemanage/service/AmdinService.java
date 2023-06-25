package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.Admin;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/20 21:38
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public interface AmdinService {
    Admin getAdmin(String name,String password);
}
