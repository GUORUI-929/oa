package com.buu.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 请假申请实体类
 * 对应 leave_application 表，存储员工请假申请单据与审批状态
 */
@Data
@TableName("leave_application")
public class LeaveApplication {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请假单号
     */
    private String leaveNo;

    /**
     * 申请人员工ID
     */
    private Long empId;

    /**
     * 请假类型：1病假 2事假 3年假 4调休
     */
    private Integer leaveType;

    /**
     * 请假开始日期
     */
    private LocalDate startDate;

    /**
     * 请假结束日期
     */
    private LocalDate endDate;

    /**
     * 请假天数
     */
    private BigDecimal days;

    /**
     * 请假事由
     */
    private String reason;

    /**
     * 审批状态：PENDING待审批、MANAGER_APPROVED经理通过、COMPLETED完成、REJECTED驳回
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}