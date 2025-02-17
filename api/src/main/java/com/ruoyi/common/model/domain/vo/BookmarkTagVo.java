package com.ruoyi.common.model.domain.vo;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serial;

/**
 * 书签标签视图对象
 *
 * @author sinvon
 * @since 2025年2月16日02:30:57
 */
@Data
public class BookmarkTagVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    @Excel(name = "标签名称")
    private String tagName;
}
