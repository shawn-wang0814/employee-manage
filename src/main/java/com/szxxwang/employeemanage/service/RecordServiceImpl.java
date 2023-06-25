package com.szxxwang.employeemanage.service;

import com.szxxwang.employeemanage.domain.GiveRecord;
import com.szxxwang.employeemanage.domain.TakeRecord;
import com.szxxwang.employeemanage.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.service
 * Date:2023/6/22 15:22
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;


    @Override
    public List<GiveRecord> findGiveAll(String sn) {
        return recordMapper.getGiveAll(sn);
    }

    @Override
    public List<TakeRecord> findTakeAll(String sn) {
        return recordMapper.getTakeAll(sn);
    }

    @Override
    public int createGiveRecord(GiveRecord giveRecord) {
        return recordMapper.createGiveRecord(giveRecord);
    }

    @Override
    public List<GiveRecord> getGiveRecordByYear(String year, String serialNumber) {
        return recordMapper.getGiveRecordList(year,serialNumber);
    }

    @Override
    public List<TakeRecord> getTakeRecordByYear(String year, String serialNumber) {
        return recordMapper.getTakeRecordList(year,serialNumber);
    }

    @Override
    public int createTakeRecord(TakeRecord takeRecord) {
        return recordMapper.createTakeRecord(takeRecord);
    }
}
