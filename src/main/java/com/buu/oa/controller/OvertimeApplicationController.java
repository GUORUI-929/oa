package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.OvertimeApplication;
import com.buu.oa.service.OvertimeApplicationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加班申请控制器
 * 处理工作台模块的加班申请提交请求，启动审批流程
 */
@RestController
@RequestMapping("/api/overtime")
public class OvertimeApplicationController {

    @Resource
    private OvertimeApplicationService overtimeApplicationService;

    /**
     * 提交加班申请
     * @param overtimeApplication 加班表单数据
     * @return 提交结果，包含生成的加班单ID
     */
    @PostMapping("/apply")
    public Result<Long> applyOvertime(@RequestBody OvertimeApplication overtimeApplication) {
        // 默认当前登录员工ID，与考勤模块保持一致
        if (overtimeApplication.getEmpId() == null) {
            overtimeApplication.setEmpId(1L);
        }
        // 参数校验
        if (overtimeApplication.getStartTime() == null || overtimeApplication.getEndTime() == null) {
            return Result.error("请选择加班起止时间");
        }
        if (overtimeApplication.getStartTime().isAfter(overtimeApplication.getEndTime())) {
            return Result.error("开始时间不能晚于结束时间");
        }
        Long id = overtimeApplicationService.submitOvertimeApplication(overtimeApplication);
        return Result.success(id);
    }
}