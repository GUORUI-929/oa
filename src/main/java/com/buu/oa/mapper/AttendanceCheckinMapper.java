package com.buu.oa.mapper;

import com.buu.oa.entity.AttendanceCheckin;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface AttendanceCheckinMapper {
    List<AttendanceCheckin> selectMonthlyAttendance(@Param("empId") Long empId,
                                                    @Param("year") Integer year,
                                                    @Param("month") Integer month);

    // 这里改成List<Map>
    List<Map<String, Object>> selectMonthlyStatistics(@Param("empId") Long empId,
                                                      @Param("year") Integer year,
                                                      @Param("month") Integer month);
}