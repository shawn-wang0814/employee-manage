package com.szxxwang.employeemanage.controller;

import com.alibaba.fastjson.JSON;
import com.szxxwang.employeemanage.domain.Employee;
import com.szxxwang.employeemanage.domain.GiveRecord;
import com.szxxwang.employeemanage.domain.TakeRecord;
import com.szxxwang.employeemanage.service.EmployeeServiceImpl;
import com.szxxwang.employeemanage.service.RecordServiceImpl;
import com.szxxwang.employeemanage.util.LeaveCalculate;
import com.szxxwang.employeemanage.util.SqlDateFromat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.controller
 * Date:2023/6/20 21:37
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private RecordServiceImpl recordService;

    @RequestMapping("/addEmployee")
    public String addEmployeePage(Model md){
        List<String> allSerial = employeeService.getAllSerial();
        String string = JSON.toJSONString(allSerial);
        System.out.println("String:"+string);
        md.addAttribute("serialList",string);
        return "addEmployee";
    }

//    @ResponseBody
    @RequestMapping(value = "/addEmp")
    public void addEmployee(@RequestParam("serialNumber")String serialNumber, @RequestParam("name")String name, @RequestParam("gender")Integer gender,
                              @RequestParam("dp")String dp, @RequestParam("joinDate")String joinDate,
                              @RequestParam("retireDate")String retireDate, HttpServletResponse response) throws ParseException, IOException {

        Date sqlJoinDate = SqlDateFromat.sqlDateformate(joinDate);
        Date sqlRetireDate = SqlDateFromat.sqlDateformate(retireDate);
        Employee employee = new Employee();
        employee.setSerialNumber(serialNumber);
        employee.setEmployeeName(name);
        employee.setGender(gender);
        employee.setDp(dp);
        employee.setJoinDate(sqlJoinDate);
        employee.setRetireDate(sqlRetireDate);
//        List<GiveRecord> recordList = recordService.findAll(serialNumber);
//        if(recordList != null && !recordList.isEmpty()){
//            LeaveCalculate leaveCalculate = new LeaveCalculate();
//            Integer days = leaveCalculate.calculateLeaveOfThisYear(sqlJoinDate,sqlRetireDate);
//            employee.setThisYearRemainingDay(days);
//            employeeService.addEmployee(employee);
//        }else {
//            employeeService.addEmployeeWithoutLeave(employee);
//        }
        int i = employeeService.addEmployeeWithoutLeave(employee);
        System.out.println("i" + i);
        response.sendRedirect("/employeeList");
    }

    @RequestMapping("/delete/{serialNumber}")
    public void delEmployee(@PathVariable("serialNumber")String serialNumber,HttpServletResponse response) throws IOException {
        System.out.println(serialNumber);
        employeeService.deleteEmployee(serialNumber);
        recordService.deleteGiveRecord(serialNumber);
        recordService.deleteTakeRecord(serialNumber);
        response.sendRedirect("/employeeList");
    }

    @RequestMapping("/empInfo/{serialNumber}")
    public String empInfo(@PathVariable("serialNumber") String serialNumber, Model md) {
        Employee employee = employeeService.getBySerialNumber(serialNumber);
        return getString(serialNumber, md, employee);

    }

    @RequestMapping("/employeeInfo")
    public String employeeInfo(@RequestParam("search") String serialNumber, Model md) {
        if(serialNumber.isEmpty()){
            return "redirect:employeeList";
        }else{
            System.out.println("serialNumber:"+serialNumber);
            Employee employee = employeeService.getBySerialNumber(serialNumber);
            if(employee == null){
                return "noEmployee";
            }
            System.out.println("employee:"+employee.toString());
            return getString(serialNumber, md, employee);
        }
    }

    private String getString(@RequestParam("search") String serialNumber, Model md, Employee employee) {
        md.addAttribute("employee",employee);

        List<GiveRecord> giveAllList = recordService.findGiveAll(serialNumber);
        md.addAttribute("giveRecordList",giveAllList);
        List<TakeRecord> takeAllList = recordService.findTakeAll(serialNumber);
        md.addAttribute("takeRecordList",takeAllList);
        return "employeeInfo";
    }

    @RequestMapping("/edit/{serialNumber}")
    public String employeeEdit(@PathVariable("serialNumber") String serialNumber, Model md, HttpServletRequest request) {
        Employee employee = employeeService.getBySerialNumber(serialNumber);
        md.addAttribute("employee",employee);
        request.setAttribute("employee",employee);
//        request.getRequestDispatcher("resources/templates/employeeEdit.html").forward(request,response);
        return "employeeEdit";
    }

    @RequestMapping("/updateEmp")
    public void employeeUpdate(@RequestParam("serialNumber")String serialNumber, @RequestParam("employeeName")String name, @RequestParam("gender")Integer gender,
                                 @RequestParam("dp")String dp, @RequestParam("joinDate")String joinDate,
                                 @RequestParam("newRetireDate")String newRetireDate,@RequestParam("oldRetireDate")String oldRetireDate,@RequestParam("thisYearRemainingDay")Integer thisYearRemainingDay,
                               @RequestParam("lastYearRemainingDay")Integer lastYearRemainingDay,
                               HttpServletResponse response) throws ParseException, IOException {
        Employee employee = new Employee();
        employee.setSerialNumber(serialNumber);
        employee.setEmployeeName(name);
        employee.setGender(gender);
        employee.setDp(dp);
        Date sqlJoinDate = SqlDateFromat.sqlDateformate(joinDate);
        Date sqlRetireDate = SqlDateFromat.sqlDateformate(newRetireDate);
        //修改员工信息页面，如果添加员工的退职日期，则当年假期重新计算。
        if(!newRetireDate.equals(oldRetireDate)){
            LeaveCalculate leaveCalculate = new LeaveCalculate();
            thisYearRemainingDay = leaveCalculate.calculateLeaveOfThisYear(sqlJoinDate, sqlRetireDate);
        }
        employee.setThisYearRemainingDay(thisYearRemainingDay);
        employee.setLastYearRemainingDay(lastYearRemainingDay);
        employee.setJoinDate(sqlJoinDate);
        employee.setRetireDate(sqlRetireDate);
        System.out.println("employee:" + employee);
        employeeService.updateEmployee(employee);
        response.sendRedirect("/employeeList");

    }
}
