package com.buu.oa.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.buu.oa.service.ReportService;
import com.buu.oa.vo.DeptCountExportVO;
import com.buu.oa.vo.ExpenseTop5ExportVO;
import com.buu.oa.vo.ExpenseTrendExportVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据看板统计控制器
 * 处理数据看板相关前端请求，包含部门人数、报销趋势、报销Top5查询及报表导出
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 各部门人数分布
     * @return 部门人数列表
     */
    @GetMapping("/dept-count")
    public Map<String, Object> getDeptCount() {
        List<Map<String, Object>> data = reportService.getDeptPersonCount();
        return result(200, "操作成功", data);
    }

    /**
     * 近N个月报销金额趋势
     * @param months 月份数，可选，默认6
     * @return 报销趋势列表
     */
    @GetMapping("/expense-trend")
    public Map<String, Object> getExpenseTrend(
            @RequestParam(required = false) Integer months) {
        List<Map<String, Object>> data = reportService.getExpenseTrend(months);
        return result(200, "操作成功", data);
    }

    /**
     * 指定年月报销金额Top5
     * @param year 年份，可选，默认上月
     * @param month 月份，可选，默认上月
     * @return 报销Top5列表
     */
    @GetMapping("/expense-top5")
    public Map<String, Object> getMonthlyExpenseTop5(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        List<Map<String, Object>> data = reportService.getMonthlyExpenseTop5(year, month);
        return result(200, "操作成功", data);
    }

    /**
     * 数据看板报表多Sheet导出
     * @param modules 要导出的模块列表，可选值：dept(部门人数)、trend(报销趋势)、top5(报销Top5)
     * @param response HTTP响应对象
     * @throws Exception 导出异常
     */
    @GetMapping("/export")
    public void exportReport(@RequestParam List<String> modules, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("数据看板报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        try (ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build()) {
            int sheetIndex = 0;

            // 导出部门人数Sheet
            if (modules.contains("dept")) {
                List<Map<String, Object>> deptList = reportService.getDeptPersonCount();
                List<DeptCountExportVO> deptVOList = deptList.stream().map(item -> {
                    DeptCountExportVO vo = new DeptCountExportVO();
                    vo.setDeptName((String) item.get("deptName"));
                    vo.setPersonCount(((Number) item.get("personCount")).intValue());
                    return vo;
                }).collect(Collectors.toList());
                WriteSheet sheet = EasyExcel.writerSheet(sheetIndex++, "各部门人数分布")
                        .head(DeptCountExportVO.class)
                        .build();
                excelWriter.write(deptVOList, sheet);
            }

            // 导出报销趋势Sheet
            if (modules.contains("trend")) {
                List<Map<String, Object>> trendList = reportService.getExpenseTrend(6);
                List<ExpenseTrendExportVO> trendVOList = trendList.stream().map(item -> {
                    ExpenseTrendExportVO vo = new ExpenseTrendExportVO();
                    vo.setReportMonth((String) item.get("reportMonth"));
                    vo.setTotalAmount((BigDecimal) item.get("totalAmount"));
                    return vo;
                }).collect(Collectors.toList());
                WriteSheet sheet = EasyExcel.writerSheet(sheetIndex++, "近6个月报销趋势")
                        .head(ExpenseTrendExportVO.class)
                        .build();
                excelWriter.write(trendVOList, sheet);
            }

            // 导出报销Top5 Sheet
            if (modules.contains("top5")) {
                List<Map<String, Object>> top5List = reportService.getMonthlyExpenseTop5(null, null);
                List<ExpenseTop5ExportVO> top5VOList = new ArrayList<>();
                for (int i = 0; i < top5List.size(); i++) {
                    Map<String, Object> item = top5List.get(i);
                    ExpenseTop5ExportVO vo = new ExpenseTop5ExportVO();
                    vo.setRank(i + 1);
                    vo.setName((String) item.get("name"));
                    vo.setDeptName((String) item.get("deptName"));
                    vo.setReportCount(((Number) item.get("reportCount")).intValue());
                    vo.setTotalAmount((BigDecimal) item.get("totalAmount"));
                    top5VOList.add(vo);
                }
                WriteSheet sheet = EasyExcel.writerSheet(sheetIndex++, "本月报销Top5")
                        .head(ExpenseTop5ExportVO.class)
                        .build();
                excelWriter.write(top5VOList, sheet);
            }
        }
    }

    /**
     * 统一返回结果封装
     * @param code 状态码
     * @param msg 提示信息
     * @param data 返回数据
     * @return 封装后的Map结果
     */
    private Map<String, Object> result(int code, String msg, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", code);
        res.put("msg", msg);
        res.put("data", data);
        return res;
    }
}