package com.buu.oa.service;

import com.buu.oa.entity.LeaveApplication;

/**
 * 请假申请服务接口
 * 定义请假申请提交、状态流转等业务方法
 */
public interface LeaveApplicationService {

    /**
     * 提交请假申请，启动审批流程
     * @param leaveApplication 请假申请信息
     * @return 生成的请假单ID
     */
    Long submitLeaveApplication(LeaveApplication leaveApplication);
}