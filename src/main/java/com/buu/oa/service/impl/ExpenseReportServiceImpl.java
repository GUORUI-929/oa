package com.buu.oa.service.impl;

import com.buu.oa.entity.ExpenseReport;
import com.buu.oa.entity.ExpenseReportItem;
import com.buu.oa.mapper.ExpenseReportItemMapper;
import com.buu.oa.mapper.ExpenseReportMapper;
import com.buu.oa.service.ExpenseReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * 报销单服务实现类
 * 处理报销单提交、单号生成、金额汇总、明细批量插入等业务逻辑
 */
@Service
public class ExpenseReportServiceImpl implements ExpenseReportService {

    @Resource
    private ExpenseReportMapper expenseReportMapper;

    @Resource
    private ExpenseReportItemMapper expenseReportItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long submitExpenseReport(ExpenseReport expenseReport, List<ExpenseReportItem> itemList) {
        // 1. 生成唯一报销单号
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        String reportNo = "BX" + dateStr + uuid;
        expenseReport.setReportNo(reportNo);

        // 2. 初始化审批状态为待审批
        expenseReport.setStatus("PENDING");

        // 3. 后端二次汇总总金额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (ExpenseReportItem item : itemList) {
            totalAmount = totalAmount.add(item.getAmount());
        }
        expenseReport.setTotalAmount(totalAmount);

        // 4. 插入报销单主记录
        expenseReportMapper.insertExpenseReport(expenseReport);
        Long reportId = expenseReport.getId();

        // 5. 回填报销单ID到所有明细
        for (ExpenseReportItem item : itemList) {
            item.setReportId(reportId);
        }

        // 6. 批量插入明细
        if (!itemList.isEmpty()) {
            expenseReportItemMapper.batchInsertItems(itemList);
        }

        return reportId;
    }
}