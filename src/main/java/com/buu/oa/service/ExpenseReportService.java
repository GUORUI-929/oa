package com.buu.oa.service;

import com.buu.oa.entity.ExpenseReport;
import com.buu.oa.entity.ExpenseReportItem;
import java.util.List;

/**
 * 报销单服务接口
 * 定义报销单提交、明细管理等业务方法
 */
public interface ExpenseReportService {

    /**
     * 提交报销单，包含主表和明细
     * @param expenseReport 报销单主信息
     * @param itemList 报销明细列表
     * @return 生成的报销单ID
     */
    Long submitExpenseReport(ExpenseReport expenseReport, List<ExpenseReportItem> itemList);
}