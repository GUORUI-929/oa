package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.ExpenseReport;
import com.buu.oa.entity.ExpenseReportItem;
import com.buu.oa.service.ExpenseReportService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 报销单控制器
 * 处理审批流模块的报销单相关请求，包含发票上传、报销单提交接口
 */
@RestController
@RequestMapping("/api/expense")
public class ExpenseReportController {

    @Resource
    private ExpenseReportService expenseReportService;

    /**
     * 发票文件上传保存路径
     */
    @Value("${file.upload.path:D:/IDEA/oa/upload/}")
    private String uploadPath;

    /**
     * 发票图片上传接口
     * @param file 上传的发票图片文件
     * @return 图片访问URL
     */
    @PostMapping("/upload-invoice")
    public Result<String> uploadInvoice(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传文件不能为空");
        }

        // 校验文件类型
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.startsWith("image/jpeg") && !contentType.startsWith("image/png"))) {
            return Result.error("仅支持JPG/PNG格式的图片");
        }

        // 校验文件大小 10MB
        if (file.getSize() > 10 * 1024 * 1024) {
            return Result.error("单张图片大小不能超过10MB");
        }

        try {
            // 按日期分目录存储
            String dateDir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            File dirFile = new File(uploadPath + "invoice/" + dateDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            // 生成唯一文件名，避免重名覆盖
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;

            // 保存文件
            File destFile = new File(dirFile, newFileName);
            file.transferTo(destFile);

            // 拼接访问URL，通过静态资源映射访问
            String fileUrl = "/upload/invoice/" + dateDir + "/" + newFileName;
            return Result.success(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 提交报销单
     * @param params 包含报销单主信息和明细列表的参数
     * @return 提交结果，包含报销单ID
     */
    @PostMapping("/submit")
    public Result<Long> submitExpense(@RequestBody Map<String, Object> params) {
        // 解析主表信息
        ExpenseReport report = new ExpenseReport();
        report.setExpenseType((String) params.get("expenseType"));
        report.setInvoiceUrl((String) params.get("invoiceUrl"));
        report.setDescription((String) params.get("description"));

        // 默认当前登录员工ID
        if (params.get("empId") == null) {
            report.setEmpId(1L);
        } else {
            report.setEmpId(Long.valueOf(params.get("empId").toString()));
        }

        // 解析明细列表
        List<Map<String, Object>> itemMapList = (List<Map<String, Object>>) params.get("itemList");
        if (itemMapList == null || itemMapList.isEmpty()) {
            return Result.error("请至少添加一条报销明细");
        }

        List<ExpenseReportItem> itemList = itemMapList.stream().map(map -> {
            ExpenseReportItem item = new ExpenseReportItem();
            item.setItemName((String) map.get("itemName"));
            item.setAmount(new BigDecimal(map.get("amount").toString()));
            if (map.get("expenseDate") != null && !map.get("expenseDate").toString().isEmpty()) {
                item.setExpenseDate(LocalDate.parse(map.get("expenseDate").toString()));
            }
            item.setRemark((String) map.get("remark"));
            return item;
        }).toList();

        Long id = expenseReportService.submitExpenseReport(report, itemList);
        return Result.success(id);
    }
}