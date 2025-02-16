package com.ruoyi.project.admin.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 网站信息日志对象 coco_website_info_log
 * <p>
 * 用于记录网站信息的操作日志，如新增、修改、删除等操作记录
 *
 * @author sinvon
 * @since 2025年2月9日13:39:35
 */
@Data
@TableName("coco_website_info_log")
public class WebsiteInfoLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 网站信息ID
     */
    @Excel(name = "网站信息ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long websiteInfoId;

    /**
     * 操作人ID
     */
    @Excel(name = "操作人ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long operatorId;

    /**
     * 操作人名称
     */
    @Excel(name = "操作人名称")
    private String operatorName;

    /**
     * 操作描述
     */
    @Excel(name = "操作描述")
    private String operationDesc;
}
