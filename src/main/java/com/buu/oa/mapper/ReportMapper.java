package com.buu.oa.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 数据统计Mapper接口
 */
public interface ReportMapper {

    /**
     * 查询各部门在职人数分布
     * @return 部门名称与对应人数列表
     */
    List<Map<String, Object>> selectDeptPersonCount();

    /**
     * 查询近N个月报销金额趋势
     * @param months 统计月份数
     * @return 月份与对应总金额列表
     */
    List<Map<String, Object>> selectExpenseTrend(@Param("months") Integer months);

    /**
     * 查询指定年月报销金额Top5员工
     * @param year 年份
     * @param month 月份
     * @return 员工报销排名列表
     */
    List<Map<String, Object>> selectMonthlyExpenseTop5(@Param("year") Integer year, @Param("month") Integer month);
}