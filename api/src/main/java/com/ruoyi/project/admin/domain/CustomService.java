package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 客服信息对象 coco_custom_service
 * 
 * @author sinvon
 * @date 2025-01-30
 */
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

    public void setCustomServiceId(Long customServiceId) 
    {
        this.customServiceId = customServiceId;
    }

    public Long getCustomServiceId() 
    {
        return customServiceId;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setAccountType(Integer accountType) 
    {
        this.accountType = accountType;
    }

    public Integer getAccountType() 
    {
        return accountType;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setOnlineStatus(Integer onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public Integer getOnlineStatus() 
    {
        return onlineStatus;
    }
    public void setSortOrder(Integer sortOrder) 
    {
        this.sortOrder = sortOrder;
    }

    public Integer getSortOrder() 
    {
        return sortOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customServiceId", getCustomServiceId())
            .append("nickname", getNickname())
            .append("accountType", getAccountType())
            .append("account", getAccount())
            .append("onlineStatus", getOnlineStatus())
            .append("sortOrder", getSortOrder())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
