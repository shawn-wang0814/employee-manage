package com.szxxwang.employeemanage.mysqlConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.mysqlConfig
 * Date:2023/6/20 20:20
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Configuration
@EnableConfigurationProperties(MysqlProperties.class)
public class MysqlConfig {

    @Autowired
    private MysqlProperties mysqlProperties;

    @Bean
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(mysqlProperties.getDriverClass());
        dataSource.setUrl(mysqlProperties.getUrl());
        dataSource.setUsername(mysqlProperties.getUsername());
        dataSource.setPassword(mysqlProperties.getPassword());
        return dataSource;
    }

}
