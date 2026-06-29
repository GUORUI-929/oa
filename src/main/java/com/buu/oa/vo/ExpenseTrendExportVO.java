package com.buu.oa.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 报销趋势导出VO
 * 对应数据看板-近6个月报销金额趋势导出字段
 */
@Data
public class ExpenseTrendExportVO {

    @ExcelProperty("统计月份")
    private String reportMonth;

    @ExcelProperty("报销总金额(元)")
    private BigDecimal totalAmount;
}