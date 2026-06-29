package com.buu.oa.mapper;

import com.buu.oa.entity.ExpenseReport;
import org.apache.ibatis.annotations.Param;

/**
 * 报销单主表Mapper接口
 * 定义报销单主表相关数据库操作方法
 */
public interface ExpenseReportMapper {

    /**
     * 新增报销单主记录
     * @param entity 报销单实体
     * @return 受影响行数
     */
    int insertExpenseReport(@Param("entity") ExpenseReport entity);
}