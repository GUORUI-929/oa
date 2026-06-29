package com.buu.oa.mapper;

import com.buu.oa.entity.LeaveApplication;
import org.apache.ibatis.annotations.Param;

/**
 * 请假申请Mapper接口
 * 定义请假申请相关数据库操作方法
 */
public interface LeaveApplicationMapper {

    /**
     * 新增请假申请单
     * @param leaveApplication 请假申请实体
     * @return 受影响行数
     */
    int insertLeaveApplication(@Param("entity") LeaveApplication leaveApplication);
}