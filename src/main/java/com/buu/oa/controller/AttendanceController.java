package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.AttendanceCheckin;
import com.buu.oa.service.AttendanceCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceCheckinService attendanceCheckinService;

    private static final Long DEFAULT_EMP_ID = 1L;

    @GetMapping("/monthly")
    public Result<Map<String, Object>> getMonthlyCalendar(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {

        year = year == null ? 2026 : year;
        month = month == null ? 5 : month;

        List<AttendanceCheckin> attendanceList = attendanceCheckinService.getMonthlyAttendance(DEFAULT_EMP_ID, year, month);
        Map<String, Integer> statistics = attendanceCheckinService.getMonthlyStatistics(DEFAULT_EMP_ID, year, month);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("year", year);
        resultData.put("month", month);
        resultData.put("list", attendanceList);
        resultData.put("statistics", statistics);

        return Result.success(resultData);
    }
}