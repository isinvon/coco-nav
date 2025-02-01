package com.ruoyi.project.admin.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 用户浏览历史对象 coco_browse_history
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_browse_history")
public class BrowseHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 书签ID */
    @Excel(name = "书签ID")
    private Long bookmarkId;

    /** 浏览时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "浏览时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date viewTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;
}
