package com.buu.oa.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 报销Top5导出VO
 * 对应数据看板-本月报销金额Top5导出字段
 */
@Data
public class ExpenseTop5ExportVO {

    @ExcelProperty("排名")
    private Integer rank;

    @ExcelProperty("员工姓名")
    private String name;

    @ExcelProperty("所属部门")
    private String deptName;

    @ExcelProperty("报销次数")
    private Integer reportCount;

    @ExcelProperty("报销总金额(元)")
    private BigDecimal totalAmount;
}