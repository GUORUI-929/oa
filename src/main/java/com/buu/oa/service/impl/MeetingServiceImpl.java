package com.buu.oa.service.impl;

import com.buu.oa.entity.MeetingReservation;
import com.buu.oa.entity.MeetingRoom;
import com.buu.oa.mapper.MeetingReservationMapper;
import com.buu.oa.mapper.MeetingRoomMapper;
import com.buu.oa.service.MeetingService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 会议室预约服务实现类
 * 处理会议室查询、预约冲突检测、预约提交等业务逻辑
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    @Resource
    private MeetingRoomMapper meetingRoomMapper;

    @Resource
    private MeetingReservationMapper meetingReservationMapper;

    @Override
    public List<MeetingRoom> getAvailableRoomList() {
        return meetingRoomMapper.selectAvailableRoomList();
    }

    @Override
    public List<MeetingReservation> getMonthlyReservation(Long roomId, Integer year, Integer month) {
        return meetingReservationMapper.selectMonthlyReservation(roomId, year, month);
    }

    @Override
    public Long submitReservation(MeetingReservation reservation) {
        // 1. 参数基础校验
        if (reservation.getStartTime() == null || reservation.getEndTime() == null) {
            throw new RuntimeException("预约开始时间和结束时间不能为空");
        }
        if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
            throw new RuntimeException("开始时间不能晚于结束时间");
        }
        if (reservation.getRoomId() == null) {
            throw new RuntimeException("请选择会议室");
        }

        // 2. 冲突检测算法：查询同一会议室时间重叠的有效预约
        List<MeetingReservation> conflictList = meetingReservationMapper.selectConflictReservation(
                reservation.getRoomId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                null
        );
        if (!conflictList.isEmpty()) {
            throw new RuntimeException("该会议室在所选时间段已被预约，请选择其他时间或会议室");
        }

        // 3. 生成唯一预约单号：前缀HY + 年月日时分 + 6位随机标识
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        String reservationNo = "HY" + dateStr + uuid;
        reservation.setReservationNo(reservationNo);

        // 4. 初始化默认值
        reservation.setStatus(1);
        reservation.setRemindStatus(0);
        if (reservation.getEmpId() == null) {
            reservation.setEmpId(1L);
        }

        // 5. 插入数据库
        meetingReservationMapper.insertReservation(reservation);
        return reservation.getId();
    }
}