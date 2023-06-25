package com.szxxwang.employeemanage.domain;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.domain
 * Date:2023/6/20 21:40
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public class Admin {
    int id;
    String adminName;
    String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
