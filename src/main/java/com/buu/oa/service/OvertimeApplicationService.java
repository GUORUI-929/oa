package com.buu.oa.service;

import com.buu.oa.entity.OvertimeApplication;

/**
 * 加班申请服务接口
 * 定义加班申请提交、状态流转等业务方法
 */
public interface OvertimeApplicationService {

    /**
     * 提交加班申请，启动审批流程
     * @param overtimeApplication 加班申请信息
     * @return 生成的加班单ID
     */
    Long submitOvertimeApplication(OvertimeApplication overtimeApplication);
}