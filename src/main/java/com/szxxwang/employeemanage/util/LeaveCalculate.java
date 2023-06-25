package com.szxxwang.employeemanage.util;

import com.szxxwang.employeemanage.domain.Employee;
import org.apache.ibatis.jdbc.Null;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.util
 * Date:2023/6/23 8:45
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public class LeaveCalculate {

    public int daysInTheory;

    public int getDaysInTheory() {
        return daysInTheory;
    }

    public void setDaysInTheory(double division) {

        if(division < 0.5){
            this.daysInTheory = 0;
        }else if( 0.5 <= division & division < 1.5){
            this.daysInTheory = 10;
        }else if( 1.5 <= division & division < 2.5){
            this.daysInTheory= 11;
        }else if( 2.5 <= division & division < 3.5){
            this.daysInTheory=12;
        }else if( 3.5 <= division & division < 4.5){
            this.daysInTheory=14;
        } else if( 4.5 <= division & division < 5.5){
            this.daysInTheory=16;
        }else if( 5.5 <= division & division < 6.5){
            this.daysInTheory=18;
        }else if(division >= 6.5){
            this.daysInTheory=20;
        }
        
    }

    public Integer calculateLeaveOfThisYear(Date joinDate,Date retireDate){

//        Date joinDate = employee.getJoinDate();
//        Date retireDate = employee.getRetireDate();

        Calendar calendarNow = Calendar.getInstance();
        java.util.Date time = calendarNow.getTime(); //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strJoinDate = sdf.format(joinDate); //格式化数据库时间类型为时间字符串
        System.out.println("strJoinDate:" + strJoinDate);
        String strNowDate = sdf.format(time);
        System.out.println("strNowDate:" + strNowDate);
        LocalDate temporalJoin = LocalDate.parse(strJoinDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate temporalNow = LocalDate.parse(strNowDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int between = (int)ChronoUnit.MONTHS.between(temporalJoin, temporalNow); //计算入职日期和当前日期相差月份
        System.out.println("countMoths:" + between);
        double division = between / 12.00;
        this.setDaysInTheory(division);
        System.out.println("division:"+division);
        System.out.println("getDays:"+this.getDaysInTheory());
        if(retireDate == null){
            return this.getDaysInTheory();
        }else {

            String strRetireDate = sdf.format(retireDate);
            LocalDate temporalRetire = LocalDate.parse(strRetireDate);
            int divisionOfThisYear = (int)ChronoUnit.MONTHS.between(temporalJoin, temporalRetire);
            System.out.println("divisionOfThisYear:" + divisionOfThisYear);
            int number = (divisionOfThisYear % 12) - 1;
            System.out.println("number" + number);
            int days = (int) Math.ceil(this.getDaysInTheory() / 12.0) * number;
            System.out.println("days:" + days);
            return days;
        }


    }

}
