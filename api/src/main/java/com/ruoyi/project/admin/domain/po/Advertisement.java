package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.constant.DateConstants;
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
    @JsonFormat(pattern = DateConstants.DATE_FORMAT_PATTERN, timezone = DateConstants.TIME_ZONE)
    @Excel(name = "开始时间", width = 30, dateFormat = DateConstants.DATE_FORMAT_PATTERN)
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = DateConstants.DATE_FORMAT_PATTERN, timezone = DateConstants.TIME_ZONE)
    @Excel(name = "结束时间", width = 30, dateFormat = DateConstants.DATE_FORMAT_PATTERN)
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

    // ============================================

    /**
     * 广告状态 0-下架
     */
    private static final Integer ADVERTISEMENT_STATUS_DISABLED = 0;
    /**
     * 广告状态 1-正常
     */
    private static final Integer ADVERTISEMENT_STATUS_ENABLED = 1;
    /**
     * 默认广告宽度
     */
    public static final int ADVERTISEMENT_DEFAULT_WIDTH = 1;
    /**
     * 默认广告高度
     */
    public static final int ADVERTISEMENT_DEFAULT_HEIGHT = 1;
    /**
     * 默认广告点击次数
     */
    public static final int ADVERTISEMENT_DEFAULT_CLICK_COUNT = 0;
}
