package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 客服信息对象 coco_custom_service
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Data
public class CustomService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客服ID */
    private Long customServiceId;

    /** 客服昵称 */
    @Excel(name = "客服昵称")
    private String nickname;

    /** 1-QQ 2-微信 3-电话 */
    @Excel(name = "1-QQ 2-微信 3-电话")
    private Integer accountType;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String account;

    /** 0-离线 1-在线 */
    @Excel(name = "0-离线 1-在线")
    private Integer onlineStatus;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;
}
