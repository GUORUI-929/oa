package com.buu.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buu.oa.entity.SysNotice;
import com.buu.oa.mapper.SysNoticeMapper;
import com.buu.oa.service.NoticeService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements NoticeService {

    @Override
    public List<SysNotice> getPublishedList() {
        LambdaQueryWrapper<SysNotice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysNotice::getStatus, 1)
                .orderByDesc(SysNotice::getCreateTime);
        return this.list(wrapper);
    }

    @Override
    public boolean publishNotice(SysNotice notice) {
        notice.setStatus(1);
        notice.setCreateTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        notice.setCreateBy("admin");
        return this.save(notice);
    }

    @Override
    public SysNotice getNoticeById(Long id) {
        return this.getById(id);
    }
}