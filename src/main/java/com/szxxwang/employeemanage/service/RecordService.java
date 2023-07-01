package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.GiveRecord;
import com.szxxwang.employeemanage.domain.TakeRecord;

import java.util.List;

/**
 * Project Name:springboot
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/20 21:38
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
public interface RecordService {
    List<String> findGiveAllYearList(String sn);
    List<GiveRecord> findGiveAll(String sn);
    List<TakeRecord> findTakeAll(String sn);
    int createGiveRecord(GiveRecord leaveRecord);
    List<GiveRecord> getGiveRecordByYear(String year,String serialNumber);
    List<TakeRecord> getTakeRecordByYear(String year,String serialNumber);
    int createTakeRecord(TakeRecord takeRecord);
    void deleteGiveRecord(String serialNumber);
    void deleteTakeRecord(String serialNumber);
}
