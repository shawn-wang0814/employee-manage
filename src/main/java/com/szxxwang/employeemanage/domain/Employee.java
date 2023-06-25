package com.szxxwang.employeemanage.domain;

import java.sql.Date;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.domain
 * Date:2023/6/20 21:41
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public class Employee {

    int id;
    String serialNumber;
    String employeeName;
    int gender;
    String dp;
    Date joinDate;
    int lastYearRemainingDay;
    int thisYearRemainingDay;
    Date retireDate;
    int yearsOfService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employName) {
        this.employeeName = employName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getLastYearRemainingDay() {
        return lastYearRemainingDay;
    }

    public void setLastYearRemainingDay(int lastYearRemainingDay) {
        this.lastYearRemainingDay = lastYearRemainingDay;
    }

    public int getThisYearRemainingDay() {
        return thisYearRemainingDay;
    }

    public void setThisYearRemainingDay(int thisYearRemainingDay) {
        this.thisYearRemainingDay = thisYearRemainingDay;
    }

    public Date getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", gender=" + gender +
                ", dp='" + dp + '\'' +
                ", joinDate=" + joinDate +
                ", lastYearRemainingDay=" + lastYearRemainingDay +
                ", thisYearRemainingDay=" + thisYearRemainingDay +
                ", retireDate=" + retireDate +
                ", yearsOfService=" + yearsOfService +
                '}';
    }
}
