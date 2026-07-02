package com.buu.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buu.oa.entity.SysNotice;
import java.util.List;

public interface NoticeService extends IService<SysNotice> {
    /**
     * 获取所有已发布的公告列表
     */
    List<SysNotice> getPublishedList();

    /**
     * 发布新公告
     */
    boolean publishNotice(SysNotice notice);

    /**
     * 根据ID查询公告详情
     */
    SysNotice getNoticeById(Long id);
}