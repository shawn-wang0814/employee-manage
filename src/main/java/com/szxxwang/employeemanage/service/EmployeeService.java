package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.Employee;

import java.util.List;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/20 20:26
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public interface EmployeeService {
    List<Employee> getAll();
    Employee getBySerialNumber(String serialNumber);
    int addEmployee(Employee employee);
    int addEmployeeWithoutLeave(Employee employee);
    void deleteEmployee(String serialNumber);
    void updateEmployee(Employee employee);
}
