package com.szxxwang.employeemanage;

import com.szxxwang.employeemanage.util.FirstDayOfThisYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManageApplicationTests {

    @Test
   public void contextLoads() {

        String text1 = "2023-02-01";
        Temporal temporal1 = LocalDate.parse(text1);
        String text2 = "2023-07-01";
        Temporal temporal2 = LocalDate.parse(text2);
        // 方法返回为相差月份
        int l = (int)ChronoUnit.MONTHS.between(temporal1, temporal2);
        int days = ((int)Math.ceil(10 / 12.0)) * (l -1);
        System.out.println(days);
    }
    @Test
   public void testGetFirstDayOfYear(){
        String firstDay = FirstDayOfThisYear.getFirstDay();
        System.out.println("firstDay"+firstDay);
    }

}
