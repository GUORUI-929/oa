package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.MeetingReservation;
import com.buu.oa.entity.MeetingRoom;
import com.buu.oa.service.MeetingService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会议室预约控制器
 * 处理会议室预约相关前端请求，包含会议室查询、预约查询、预约提交接口
 */
@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

    @Resource
    private MeetingService meetingService;

    /**
     * 获取可用会议室列表
     * @return 可用会议室列表
     */
    @GetMapping("/room-list")
    public Result<List<MeetingRoom>> getRoomList() {
        List<MeetingRoom> list = meetingService.getAvailableRoomList();
        return Result.success(list);
    }

    /**
     * 获取月度预约列表
     * @param roomId 会议室ID，可选
     * @param year 年份
     * @param month 月份
     * @return 月度预约列表
     */
    @GetMapping("/monthly")
    public Result<List<MeetingReservation>> getMonthlyReservation(
            @RequestParam(required = false) Long roomId,
            @RequestParam Integer year,
            @RequestParam Integer month) {
        List<MeetingReservation> list = meetingService.getMonthlyReservation(roomId, year, month);
        return Result.success(list);
    }

    /**
     * 提交会议预约申请
     * @param reservation 预约表单数据
     * @return 预约单ID
     */
    @PostMapping("/submit")
    public Result<Long> submitReservation(@RequestBody MeetingReservation reservation) {
        try {
            Long id = meetingService.submitReservation(reservation);
            return Result.success(id);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
}