package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 广告管理对象 coco_advertisement
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Data
@TableName("coco_advertisement")
public class Advertisement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     */
    private Long advertisementId;

    /**
     * 广告标题
     */
    @Excel(name = "广告标题")
    private String title;

    /**
     * 广告内容
     */
    @Excel(name = "广告内容")
    private String content;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 0-下架 1-正常
     */
    @Excel(name = "0-下架 1-正常")
    private Integer status;

    /**
     * 点击次数
     */
    @Excel(name = "点击次数")
    private Integer clickCount;

    /**
     * 排序值
     */
    @Excel(name = "排序值")
    private Integer sortOrder;

    /**
     * 广告的宽度
     */
    @Excel(name = "广告的宽度")
    private Integer width;

    /**
     * 广告的高度
     */
    @Excel(name = "广告的高度")
    private Integer height;
}
