package com.buu.oa.service.impl;

import com.buu.oa.entity.LeaveApplication;
import com.buu.oa.mapper.LeaveApplicationMapper;
import com.buu.oa.service.LeaveApplicationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 请假申请服务实现类
 * 处理请假申请提交、单号生成、状态初始化等业务逻辑
 */
@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    @Resource
    private LeaveApplicationMapper leaveApplicationMapper;

    @Override
    public Long submitLeaveApplication(LeaveApplication leaveApplication) {
        // 生成唯一请假单号：前缀QJ + 年月日 + 8位随机标识
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        String leaveNo = "QJ" + dateStr + uuid;
        leaveApplication.setLeaveNo(leaveNo);

        // 初始化审批状态为待审批，启动请假流程
        leaveApplication.setStatus("PENDING");

        // 后端二次计算天数，避免前端篡改
        long dayDiff = leaveApplication.getEndDate().toEpochDay() - leaveApplication.getStartDate().toEpochDay() + 1;
        leaveApplication.setDays(java.math.BigDecimal.valueOf(dayDiff));

        leaveApplicationMapper.insertLeaveApplication(leaveApplication);
        return leaveApplication.getId();
    }
}