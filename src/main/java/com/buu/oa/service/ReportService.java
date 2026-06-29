package com.buu.oa.service;

import java.util.List;
import java.util.Map;

/**
 * 数据统计服务接口
 */
public interface ReportService {

    /**
     * 查询各部门人数分布
     * @return 部门人数数据
     */
    List<Map<String, Object>> getDeptPersonCount();

    /**
     * 查询近N个月报销金额趋势
     * @param months 月份数
     * @return 趋势数据
     */
    List<Map<String, Object>> getExpenseTrend(Integer months);

    /**
     * 查询指定年月报销金额Top5
     * @param year 年份，为空则取上月
     * @param month 月份，为空则取上月
     * @return Top5数据
     */
    List<Map<String, Object>> getMonthlyExpenseTop5(Integer year, Integer month);
}