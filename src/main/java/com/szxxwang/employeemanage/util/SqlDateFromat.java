package com.szxxwang.employeemanage.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.util
 * Date:2023/6/22 23:18
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public class SqlDateFromat {

    public static Date sqlDateformate(String string) throws ParseException {
        if(!string.equals("")){
            java.util.Date strDate=new SimpleDateFormat("yyyy-MM-dd").parse(string);
            Date sqlDate=new Date(strDate.getTime());
            return sqlDate;
        }else {
            return null;
        }
    }

}
