package com.ruoyi.framework.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.constant.DateConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @JsonIgnore
    @TableField(value = "search_value", exist = false)
    private String searchValue;

    /**
     * 创建者
     */
    @TableField(value = "create_by", exist = false)
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by", exist = false)
    private String updateBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateConstants.DATE_FORMAT_PATTERN, timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateConstants.DATE_FORMAT_PATTERN, timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）(TableLogic的逻辑在application.yml中定义了)
     */
    @TableLogic
    @TableField(value = "is_deleted", exist = false)
    private Integer isDeleted;

    /**
     * 备注
     */
    @TableField(value = "remark", exist = false)
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY) // 只返回非空的字段
    @TableField(exist = false)
    private Map<String, Object> params;


    // 手动添加 getParams 方法，保证 params 不为 null
    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
