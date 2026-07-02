package com.buu.oa.controller;

import com.buu.oa.common.Result;
import com.buu.oa.entity.SysNotice;
import com.buu.oa.service.NoticeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    // 构造器注入，消除字段注入警告
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 获取全部已发布公告列表
     */
    @GetMapping("/list")
    public Result<List<SysNotice>> getNoticeList() {
        List<SysNotice> list = noticeService.getPublishedList();
        return Result.success(list);
    }

    /**
     * 发布公告
     */
    @PostMapping("/publish")
    public Result<Void> publishNotice(@RequestBody SysNotice notice) {
        boolean flag = noticeService.publishNotice(notice);
        if (flag) {
            return Result.success(null);
        } else {
            return Result.error("公告发布失败");
        }
    }

    /**
     * 根据ID查询公告详情
     */
    @GetMapping("/{id}")
    public Result<SysNotice> getNoticeDetail(@PathVariable Long id) {
        SysNotice notice = noticeService.getNoticeById(id);
        return Result.success(notice);
    }
}