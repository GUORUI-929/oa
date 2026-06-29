package com.buu.oa.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 部门人数导出VO
 * 对应数据看板-各部门人数分布导出字段
 */
@Data
public class DeptCountExportVO {

    @ExcelProperty("部门名称")
    private String deptName;

    @ExcelProperty("在职人数")
    private Integer personCount;
}