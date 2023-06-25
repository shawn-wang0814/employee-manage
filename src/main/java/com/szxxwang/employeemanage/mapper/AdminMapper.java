package com.szxxwang.employeemanage.mapper;

import com.szxxwang.employeemanage.domain.Admin;
import com.szxxwang.employeemanage.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.mapper
 * Date:2023/6/20 21:53
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Mapper
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE adminName = #{name} AND pwd = #{password}")
    List<Admin> getAdmin(@Param("name") String name, @Param("password") String password);
//    @Select("select * from admin;")
//    List<Admin> getAdmin();



}
