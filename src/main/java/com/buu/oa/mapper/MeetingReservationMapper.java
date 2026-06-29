package com.buu.oa.mapper;

import com.buu.oa.entity.MeetingReservation;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会议预约Mapper接口
 * 定义会议预约相关数据库操作方法
 */
public interface MeetingReservationMapper {

    /**
     * 查询指定会议室指定月份的有效预约列表
     * 业务逻辑：用于日历视图展示，查询状态有效的预约记录
     * @param roomId 会议室ID，为空查询所有会议室
     * @param year 年份
     * @param month 月份
     * @return 预约列表
     */
    List<MeetingReservation> selectMonthlyReservation(@Param("roomId") Long roomId,
                                                      @Param("year") Integer year,
                                                      @Param("month") Integer month);

    /**
     * 查询时间冲突的预约记录
     * 业务逻辑：冲突检测核心查询，同一会议室时间重叠的有效预约即为冲突
     * @param roomId 会议室ID
     * @param startTime 预约开始时间
     * @param endTime 预约结束时间
     * @param excludeId 排除的预约ID（用于修改场景，新增时传null）
     * @return 冲突的预约列表
     */
    List<MeetingReservation> selectConflictReservation(@Param("roomId") Long roomId,
                                                       @Param("startTime") LocalDateTime startTime,
                                                       @Param("endTime") LocalDateTime endTime,
                                                       @Param("excludeId") Long excludeId);

    /**
     * 新增会议预约
     * @param reservation 预约实体
     * @return 受影响行数
     */
    int insertReservation(@Param("entity") MeetingReservation reservation);
}