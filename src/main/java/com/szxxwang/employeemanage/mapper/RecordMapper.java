package com.szxxwang.employeemanage.mapper;

import com.szxxwang.employeemanage.domain.GiveRecord;
import com.szxxwang.employeemanage.domain.TakeRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Project Name:employee-manage
 * File Name:null.java
 * Package Name:com.szxxwang.employeemanage.mapper
 * Date:2023/6/22 15:23
 * Copyright (c) 2023, szxxwang@outlook.com All Rights Reserved.
 */
@Mapper
@Repository
public interface RecordMapper {
    @Select("select * from giveRecord where serialNumber=#{sn}")
    List<GiveRecord> getAll(String sn);

    @Select("select * from giveRecord where serialNumber=#{sn}")
    List<GiveRecord> getGiveAll(String sn);

    @Select("select * from takeRecord where serialNumber=#{sn}")
    List<TakeRecord> getTakeAll(String sn);

    @Insert("insert into giveRecord(year,serialNumber,employeeName,joinDate,days,operation,adminName,approveDate,giveDate,daysInTheory,daysActually) " +
            "values(#{year},#{serialNumber},#{employeeName},#{joinDate},#{days},#{operation},#{adminName},#{approveDate},#{giveDate},#{daysInTheory},#{daysActually})")
    int createGiveRecord(GiveRecord giveRecord);

    @Select("select * from takeRecord where year = #{year} and serialNumber=#{serialNumber}")
    List<TakeRecord> getTakeRecordList(String year,String serialNumber);

    @Select("select * from giveRecord where year = #{year} and serialNumber=#{serialNumber}")
    List<GiveRecord> getGiveRecordList(String year,String serialNumber);

    @Insert("insert into takeRecord(year,serialNumber,employeeName,operation,days,adminName,approveDate,daysActually,daysInTheory) " +
            "values(#{year},#{serialNumber},#{employeeName},#{operation},#{days},#{adminName},#{approveDate},#{daysActually},#{daysInTheory})")
    int createTakeRecord(TakeRecord takeRecord);

    @Delete("delete from giverecord where serialNumber=#{serialNumber}")
    void deleteGiveRecord(String serialNumber);

    @Delete("delete from takerecord where serialNumber=#{serialNumber}")
    void deleteTakeRecord(String serialNumber);
}
