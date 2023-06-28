package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.Employee;
import com.szxxwang.employeemanage.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.x509.SerialNumber;

import java.util.List;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/21 16:02
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }

    @Override
    public List<String> getAllSerial() {
        return employeeMapper.getAllSerial();
    }

    @Override
    public Employee getBySerialNumber(String serialNumber) {

        return employeeMapper.getBySerialNumber(serialNumber);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public int addEmployeeWithoutLeave(Employee employee) {
        return employeeMapper.addEmployeeWithoutLeave(employee);
    }

    @Override
    public void deleteEmployee(String serialNumber) {
        employeeMapper.deleteEmployee(serialNumber);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }
}
