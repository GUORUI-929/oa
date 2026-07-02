package com.buu.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buu.oa.entity.SysNoticeRead;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysNoticeReadMapper extends BaseMapper<SysNoticeRead> {
    /**
     * 查询用户已读公告数量
     */
    Long countReadByUserId(@Param("userId") Long userId);

    /**
     * 查询用户是否已读某公告
     */
    Integer checkRead(@Param("noticeId") Long noticeId, @Param("userId") Long userId);
}