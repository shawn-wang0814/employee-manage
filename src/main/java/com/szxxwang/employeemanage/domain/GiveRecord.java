package com.szxxwang.employeemanage.domain;

import lombok.Data;

import java.sql.Date;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.domain
 * Date:2023/6/20 21:46
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */

@Data
public class GiveRecord {
    int id;
    String year;
    String serialNumber;
    String employeeName;
    Date joinDate;
    Date giveDate;
    int days;
    int operation;
    String adminName;
    Date approveDate;
    int daysInTheory;
    int daysActually;
}
