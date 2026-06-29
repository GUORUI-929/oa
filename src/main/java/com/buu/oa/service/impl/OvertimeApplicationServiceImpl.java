package com.buu.oa.service.impl;

import com.buu.oa.entity.OvertimeApplication;
import com.buu.oa.mapper.OvertimeApplicationMapper;
import com.buu.oa.service.OvertimeApplicationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 加班申请服务实现类
 * 处理加班申请提交、单号生成、状态初始化等业务逻辑
 */
@Service
public class OvertimeApplicationServiceImpl implements OvertimeApplicationService {

    @Resource
    private OvertimeApplicationMapper overtimeApplicationMapper;

    @Override
    public Long submitOvertimeApplication(OvertimeApplication overtimeApplication) {
        // 生成唯一加班单号：前缀JB + 年月日时分 + 6位随机标识
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        String overtimeNo = "JB" + dateStr + uuid;
        overtimeApplication.setOvertimeNo(overtimeNo);

        // 初始化审批状态为待审批
        overtimeApplication.setStatus("PENDING");

        // 后端二次计算加班时长（小时），保留1位小数，四舍五入
        // 改用毫秒差计算，不依赖ChronoUnit
        long startMilli = overtimeApplication.getStartTime()
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endMilli = overtimeApplication.getEndTime()
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long minutes = (endMilli - startMilli) / (1000 * 60);

        // 改用BigDecimal内置舍入常量，不依赖RoundingMode
        BigDecimal hours = BigDecimal.valueOf(minutes)
                .divide(BigDecimal.valueOf(60), 1, BigDecimal.ROUND_HALF_UP);
        overtimeApplication.setHours(hours);

        overtimeApplicationMapper.insertOvertimeApplication(overtimeApplication);
        return overtimeApplication.getId();
    }
}