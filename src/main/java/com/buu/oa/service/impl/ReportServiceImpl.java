package com.buu.oa.service.impl;

import com.buu.oa.mapper.ReportMapper;
import com.buu.oa.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 数据统计服务实现
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Map<String, Object>> getDeptPersonCount() {
        return reportMapper.selectDeptPersonCount();
    }

    @Override
    public List<Map<String, Object>> getExpenseTrend(Integer months) {
        // 默认查询近6个月
        if (months == null || months <= 0) {
            months = 6;
        }
        return reportMapper.selectExpenseTrend(months);
    }

    @Override
    public List<Map<String, Object>> getMonthlyExpenseTop5(Integer year, Integer month) {
        // 参数为空则自动取上月数据
        if (year == null || month == null) {
            LocalDate lastMonth = LocalDate.now().minusMonths(1);
            year = lastMonth.getYear();
            month = lastMonth.getMonthValue();
        }
        return reportMapper.selectMonthlyExpenseTop5(year, month);
    }
}