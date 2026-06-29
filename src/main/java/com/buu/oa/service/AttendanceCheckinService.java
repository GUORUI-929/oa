package com.buu.oa.service;

import com.buu.oa.entity.AttendanceCheckin;

import java.util.List;
import java.util.Map;

/**
 * 考勤打卡服务接口
 * 定义考勤日历、统计相关的业务方法
 */
public interface AttendanceCheckinService {

    /**
     * 获取指定员工指定年月的考勤记录列表
     * @param empId 员工ID
     * @param year 年份
     * @param month 月份
     * @return 考勤记录列表
     */
    List<AttendanceCheckin> getMonthlyAttendance(Long empId, Integer year, Integer month);

    /**
     * 获取指定员工指定年月的考勤统计数据
     * @param empId 员工ID
     * @param year 年份
     * @param month 月份
     * @return 统计数据（出勤天数、迟到次数、缺卡次数、请假天数）
     */
    Map<String, Integer> getMonthlyStatistics(Long empId, Integer year, Integer month);
}