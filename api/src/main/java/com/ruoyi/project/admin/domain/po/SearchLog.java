package com.ruoyi.project.admin.domain.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;


import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 搜索日志对象 coco_search_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_search_log")
public class SearchLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /** 搜索关键词 */
    @Excel(name = "搜索关键词")
    private String keyword;

    /** 搜索时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "搜索时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date searchTime;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;
}
