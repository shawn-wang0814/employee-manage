package com.szxxwang.employeemanage.mysqlConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.mysqlConfig
 * Date:2023/6/20 20:17
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@ConfigurationProperties(prefix = "jdbc")
public class MysqlProperties {
    String driverClass;
    String url;
    String username;
    String password;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MysqlProperties{" +
                "driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
