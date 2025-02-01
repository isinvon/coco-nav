package com.ruoyi.project.admin.domain.vo;

import com.ruoyi.project.admin.domain.Advertisement;
import lombok.Data;

import java.util.Date;

/**
 * @author : sinvon
 * @since :  2025/2/2 上午2:20
 */
@Data
public class AdvertisementVo extends Advertisement {
    /**
     * 广告标题
     */
    private String title;
    /**
     * 状态 (0-下架 1-正常)
     */
    private Integer status;
    /**
     * 日期范围 (startTime~endTime)
     */
    private Date[] dateRange;
}
