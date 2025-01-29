package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 友链操作日志对象 coco_friendlink_log
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class FriendlinkLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long friendlinkLogId;

    /** 友链ID */
    @Excel(name = "友链ID")
    private Long friendlinkId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer action;

    /** 操作人ID */
    @Excel(name = "操作人ID")
    private Long operatorId;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date logTime;

    public void setFriendlinkLogId(Long friendlinkLogId) 
    {
        this.friendlinkLogId = friendlinkLogId;
    }

    public Long getFriendlinkLogId() 
    {
        return friendlinkLogId;
    }
    public void setFriendlinkId(Long friendlinkId) 
    {
        this.friendlinkId = friendlinkId;
    }

    public Long getFriendlinkId() 
    {
        return friendlinkId;
    }
    public void setAction(Integer action) 
    {
        this.action = action;
    }

    public Integer getAction() 
    {
        return action;
    }
    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }
    public void setLogTime(Date logTime) 
    {
        this.logTime = logTime;
    }

    public Date getLogTime() 
    {
        return logTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("friendlinkLogId", getFriendlinkLogId())
            .append("friendlinkId", getFriendlinkId())
            .append("action", getAction())
            .append("operatorId", getOperatorId())
            .append("logTime", getLogTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
