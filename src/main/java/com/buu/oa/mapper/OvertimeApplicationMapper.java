package com.buu.oa.mapper;

import com.buu.oa.entity.OvertimeApplication;
import org.apache.ibatis.annotations.Param;

/**
 * 加班申请Mapper接口
 * 定义加班申请相关数据库操作方法
 */
public interface OvertimeApplicationMapper {

    /**
     * 新增加班申请单
     * @param entity 加班申请实体
     * @return 受影响行数
     */
    int insertOvertimeApplication(@Param("entity") OvertimeApplication entity);
}