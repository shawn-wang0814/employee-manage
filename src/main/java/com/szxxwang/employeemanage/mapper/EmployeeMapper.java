package com.szxxwang.employeemanage.mapper;

import com.szxxwang.employeemanage.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.mapper
 * Date:2023/6/21 16:03
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Mapper
@Repository
public interface EmployeeMapper {
    @Select("select * from employees;")
    public List<Employee> getAll();

    @Select("select serialNumber from employees;")
    public List<String> getAllSerial();

    @Select("select * from employees where serialNumber=#{serialNumber};")
    public Employee getBySerialNumber(String serialNumber);

    @Insert("insert into employees(serialNumber,employeeName,gender,dp,joinDate,retireDate,thisYearRemainingDay) values(#{serialNumber},#{employeeName},#{gender},#{dp},#{joinDate},#{retireDate},#{thisYearRemainingDay});")
    public int addEmployee(Employee employee);

    @Insert("insert into employees(serialNumber,employeeName,gender,dp,joinDate,retireDate) values(#{serialNumber},#{employeeName},#{gender},#{dp},#{joinDate},#{retireDate});")
    public int addEmployeeWithoutLeave(Employee employee);

    @Delete("delete from employees where serialNumber=#{serialNumber}")
    public void deleteEmployee(String serialNumber);

    @Update("update employees set employeeName=#{employee.employeeName},gender=#{employee.gender},dp=#{employee.dp},joinDate=#{employee.joinDate},retireDate=#{employee.retireDate}" +
            ",lastYearRemainingDay=#{employee.lastYearRemainingDay},thisYearRemainingDay=#{employee.thisYearRemainingDay} " +
            "where serialNumber=#{employee.serialNumber};")
    public void updateEmployee(@Param("employee")Employee employee);
}
