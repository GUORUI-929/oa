package com.buu.oa.service;

import com.buu.oa.entity.MeetingReservation;
import com.buu.oa.entity.MeetingRoom;

import java.util.List;

/**
 * 会议室预约服务接口
 * 定义会议室查询、预约查询、预约提交等业务方法
 */
public interface MeetingService {

    /**
     * 获取可用会议室列表
     * @return 可用会议室列表
     */
    List<MeetingRoom> getAvailableRoomList();

    /**
     * 获取指定会议室指定月份的预约列表
     * @param roomId 会议室ID，可选
     * @param year 年份
     * @param month 月份
     * @return 预约列表
     */
    List<MeetingReservation> getMonthlyReservation(Long roomId, Integer year, Integer month);

    /**
     * 提交会议预约申请
     * @param reservation 预约信息
     * @return 生成的预约单ID
     * @throws RuntimeException 时间冲突时抛出异常
     */
    Long submitReservation(MeetingReservation reservation);
}