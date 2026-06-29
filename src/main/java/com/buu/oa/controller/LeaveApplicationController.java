package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.LeaveApplication;
import com.buu.oa.service.LeaveApplicationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请假申请控制器
 * 处理考勤日历模块的请假申请提交请求，启动审批流程
 */
@RestController
@RequestMapping("/api/leave")
public class LeaveApplicationController {

    @Resource
    private LeaveApplicationService leaveApplicationService;

    /**
     * 提交请假申请
     * @param leaveApplication 请假表单数据
     * @return 提交结果，包含生成的请假单ID
     */
    @PostMapping("/apply")
    public Result<Long> applyLeave(@RequestBody LeaveApplication leaveApplication) {
        // 默认当前登录员工ID，与考勤模块保持一致
        if (leaveApplication.getEmpId() == null) {
            leaveApplication.setEmpId(1L);
        }
        Long id = leaveApplicationService.submitLeaveApplication(leaveApplication);
        return Result.success(id);
    }
}