package com.szxxwang.employeemanage.controller;

import com.szxxwang.employeemanage.domain.Admin;
import com.szxxwang.employeemanage.domain.Employee;
import com.szxxwang.employeemanage.domain.GiveRecord;
import com.szxxwang.employeemanage.domain.TakeRecord;
import com.szxxwang.employeemanage.service.EmployeeServiceImpl;
import com.szxxwang.employeemanage.service.RecordServiceImpl;
import com.szxxwang.employeemanage.util.FirstDayOfThisYear;
import com.szxxwang.employeemanage.util.LeaveCalculate;
import com.szxxwang.employeemanage.util.SqlDateFromat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.controller
 * Date:2023/6/22 16:11
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Controller
public class RecordController {

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping("/giveAndTake/{serialNumber}")
    public String giveAndTake(@PathVariable("serialNumber")String serialNumber, Model md, HttpServletRequest request){
        Employee employee = employeeService.getBySerialNumber(serialNumber);
        md.addAttribute("employee",employee);
        String firstDay = FirstDayOfThisYear.getFirstDay();
        md.addAttribute("firstDay",firstDay);
        Admin admin = (Admin)request.getSession().getAttribute("loginInfo");
        md.addAttribute("admin",admin.getAdminName());
        return "giveAndTake";

    }

    @RequestMapping("/createRecord")
    public String createRecord(@RequestParam("serialNumber")String serialNumber, @RequestParam("employeeName") String employeeName,
                               @RequestParam("joinDate")Date joinDate, @RequestParam("operation")Integer operation,
                               @RequestParam("days")Integer days, @RequestParam("adminName")String adminName,
                               @RequestParam("approveDate")String approveDate, @RequestParam("giveDate") String giveDate,
                               Model md) throws ParseException {

        String year = giveDate.substring(0,4);
        System.out.println("year:" + year);
//        Date sqlApproveDate = new Date(approveDate.getTime());
        Date sqlApproveDate = SqlDateFromat.sqlDateformate(approveDate);
        Date sqlGiveDate = SqlDateFromat.sqlDateformate(giveDate);
        int intYear = Integer.parseInt(year);
        String lastYear = (intYear-1) + "";
        List<GiveRecord> giveRecordsLastYear = recordService.getGiveRecordByYear(lastYear, serialNumber);

        int totalOfLast = 0;
        int totalTakeOfLast = 0;
        List<TakeRecord> takeRecordsLastYear = recordService.getTakeRecordByYear(lastYear, serialNumber);
        if(!giveRecordsLastYear.isEmpty()){
            GiveRecord giveRecordOfLast = giveRecordsLastYear.get(0);
            totalOfLast = giveRecordOfLast.getDaysInTheory();
        }

        if(!takeRecordsLastYear.isEmpty()){
            for(TakeRecord tr:takeRecordsLastYear){
                totalTakeOfLast += tr.getDays();
            }
        }
        int remainingOfLast = totalOfLast - totalTakeOfLast; // 算出去年剩余假期的总数
        Employee employee = employeeService.getBySerialNumber(serialNumber);
        if(operation == 1){
            GiveRecord giveRecord = new GiveRecord();
            giveRecord.setYear(year);
            giveRecord.setSerialNumber(serialNumber);
            giveRecord.setEmployeeName(employeeName);
            giveRecord.setJoinDate(joinDate);
            giveRecord.setGiveDate(sqlGiveDate);
            giveRecord.setAdminName(adminName);
            giveRecord.setApproveDate(sqlApproveDate);
            giveRecord.setOperation(operation);
            giveRecord.setDays(days);
            giveRecord.setDaysInTheory(days); //如果选择付与假期，则理论假期天数=付与的天数（前端利用ajax自动计算）。
            giveRecord.setDaysActually(remainingOfLast);//在付与日当时的实际可用年假天数为去年剩余的年假天数，前年的已过期
            employee.setLastYearRemainingDay(remainingOfLast);
            employee.setThisYearRemainingDay(days);
            System.out.println("giveRecord:" + giveRecord);
            employeeService.updateEmployee(employee);
            recordService.createGiveRecord(giveRecord);//每年年初付与一次假期，按年份新增一条数据。
        }
        if(operation == 0){ //以付与日开始，超出两年的假期就过期，所以如果是前年假期还有剩余就已过期，只用去年剩余的假期参与计算。

            int remainingOfLastYear = employee.getLastYearRemainingDay();
            int remainingOfThisYear = employee.getThisYearRemainingDay();
            TakeRecord takeRecord = new TakeRecord();
            takeRecord.setYear(year);
            takeRecord.setSerialNumber(serialNumber);
            takeRecord.setDays(days);
            takeRecord.setApproveDate(sqlApproveDate);
            takeRecord.setDaysActually((remainingOfLastYear+remainingOfThisYear)-days);
            takeRecord.setOperation(operation);
            takeRecord.setAdminName(adminName);
            takeRecord.setEmployeeName(employeeName);
            List<GiveRecord> giveRecordByYear = recordService.getGiveRecordByYear(year, serialNumber);
            takeRecord.setDaysInTheory(giveRecordByYear.get(0).getDaysInTheory());
            if(remainingOfLastYear >= days){   //如果是消化年假则先扣除去年剩余的部分，如果去年剩余的年假不够消化，则再用今年的年假抵扣。
                remainingOfLastYear -= days;
                employee.setLastYearRemainingDay(remainingOfLastYear);
            }else{
                employee.setLastYearRemainingDay(0);
                remainingOfThisYear -= (days-remainingOfLastYear);
                employee.setThisYearRemainingDay(remainingOfThisYear);
            }
            employeeService.updateEmployee(employee);
            recordService.createTakeRecord(takeRecord);
        }

        List<GiveRecord> giveRecordList = recordService.findGiveAll(serialNumber);
        List<TakeRecord> takeRecordList = recordService.findTakeAll(serialNumber);
        md.addAttribute("giveRecordList",giveRecordList);
        md.addAttribute("takeRecordList",takeRecordList);
        md.addAttribute("employee",employee);
        return "employeeInfo";
    }

}
