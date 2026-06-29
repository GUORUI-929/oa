package com.buu.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 加班申请实体类
 * 对应 overtime_application 表，存储员工加班申请单据与审批状态
 */
@Data
@TableName("overtime_application")
public class OvertimeApplication {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 加班单号
     */
    private String overtimeNo;

    /**
     * 申请人员工ID
     */
    private Long empId;

    /**
     * 加班类型：1工作日 2周末 3节假日
     */
    private Integer overtimeType;

    /**
     * 加班开始时间
     */
    private LocalDateTime startTime;

    /**
     * 加班结束时间
     */
    private LocalDateTime endTime;

    /**
     * 加班时长（小时）
     */
    private BigDecimal hours;

    /**
     * 加班事由
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