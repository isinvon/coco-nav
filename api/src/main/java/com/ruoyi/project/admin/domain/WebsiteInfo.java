package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 网站信息对象 coco_website_info
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class WebsiteInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息ID */
    private Long websiteInfoId;

    /** 网站标题 */
    @Excel(name = "网站标题")
    private String title;

    /** SEO关键词 */
    @Excel(name = "SEO关键词")
    private String keywords;

    /** 网站描述 */
    @Excel(name = "网站描述")
    private String description;

    /** 备案号 */
    @Excel(name = "备案号")
    private String icp;

    /** 系统版本 */
    @Excel(name = "系统版本")
    private String version;

    public void setWebsiteInfoId(Long websiteInfoId) 
    {
        this.websiteInfoId = websiteInfoId;
    }

    public Long getWebsiteInfoId() 
    {
        return websiteInfoId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIcp(String icp) 
    {
        this.icp = icp;
    }

    public String getIcp() 
    {
        return icp;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("websiteInfoId", getWebsiteInfoId())
            .append("title", getTitle())
            .append("keywords", getKeywords())
            .append("description", getDescription())
            .append("icp", getIcp())
            .append("version", getVersion())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
