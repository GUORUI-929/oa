package com.buu.oa.service.impl;

import com.buu.oa.entity.AttendanceCheckin;
import com.buu.oa.mapper.AttendanceCheckinMapper;
import com.buu.oa.service.AttendanceCheckinService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceCheckinServiceImpl implements AttendanceCheckinService {

    @Resource
    private AttendanceCheckinMapper attendanceCheckinMapper;

    @Override
    public List<AttendanceCheckin> getMonthlyAttendance(Long empId, Integer year, Integer month) {
        return attendanceCheckinMapper.selectMonthlyAttendance(empId, year, month);
    }

    @Override
    public Map<String, Integer> getMonthlyStatistics(Long empId, Integer year, Integer month) {
        // 接收List<Map>，取第一条数据
        List<Map<String, Object>> dataList = attendanceCheckinMapper.selectMonthlyStatistics(empId, year, month);
        Map<String, Object> item = null;
        if (dataList != null && !dataList.isEmpty()) {
            item = dataList.get(0);
        }

        Map<String, Integer> resultMap = new HashMap<>();
        // 空数据全部默认0
        if (item == null) {
            resultMap.put("normalCount", 0);
            resultMap.put("lateCount", 0);
            resultMap.put("missCount", 0);
            resultMap.put("leaveCount", 0);
        } else {
            // 简化转换，消除冗余装箱警告
            resultMap.put("normalCount", item.get("normalCount") == null ? 0 : ((Number) item.get("normalCount")).intValue());
            resultMap.put("lateCount", item.get("lateCount") == null ? 0 : ((Number) item.get("lateCount")).intValue());
            resultMap.put("missCount", item.get("missCount") == null ? 0 : ((Number) item.get("missCount")).intValue());
            resultMap.put("leaveCount", item.get("leaveCount") == null ? 0 : ((Number) item.get("leaveCount")).intValue());
        }
        return resultMap;
    }
}