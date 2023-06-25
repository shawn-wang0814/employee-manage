package com.szxxwang.employeemanage.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.util
 * Date:2023/6/23 15:00
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public class FirstDayOfThisYear {

    public static String getFirstDay(){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        calendar.set(Calendar.MONTH, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天

        return format.format(calendar.getTime());
    }

}
