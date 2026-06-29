package com.buu.oa.mapper;

import com.buu.oa.entity.MeetingRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会议室Mapper接口
 * 定义会议室相关数据库操作方法
 */
public interface MeetingRoomMapper {

    /**
     * 查询可用会议室列表
     * @return 会议室列表
     */
    List<MeetingRoom> selectAvailableRoomList();

    /**
     * 根据ID查询会议室详情
     * @param id 会议室ID
     * @return 会议室详情
     */
    MeetingRoom selectRoomById(@Param("id") Long id);
}