package com.buu.oa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_notice")
public class SysNotice {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String noticeType;

    private Integer status;

    private String createBy;

    /** 接收角色：逗号分隔多选 */
    private String targetRoles;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}