package com.buu.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 公告实体类
 * 对应数据库 notice 表，存储企业富文本公告信息，无新增字段/表
 */
@Data
@TableName("notice")
public class Notice {
    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 公告标题 */
    private String title;
    /** 富文本正文内容 */
    private String content;
    /** 发布人用户ID，关联sys_user.id */
    private Long publisherId;
    /** 发布时间 */
    private LocalDateTime publishTime;
    /** 阅读次数，0代表未读，打开后自增 */
    private Integer readCount;
}