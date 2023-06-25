package com.szxxwang.employeemanage.domain;

import lombok.Data;

import java.sql.Date;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.domain
 * Date:2023/6/25 12:08
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Data
public class TakeRecord {
    int id;
    String year;
    String serialNumber;
    String employeeName;
    int days;
    int operation;
    String adminName;
    Date approveDate;
    int daysInTheory;
    int daysActually;
}
