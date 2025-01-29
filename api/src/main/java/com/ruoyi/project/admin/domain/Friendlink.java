package com.ruoyi.project.admin.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 友情链接对象 coco_friendlink
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public class Friendlink extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 友链ID */
    private Long friendlinkId;

    /** 网站作者 */
    @Excel(name = "网站作者")
    private String author;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String name;

    /** 网站地址 */
    @Excel(name = "网站地址")
    private String url;

    /** 网站LOGO */
    @Excel(name = "网站LOGO")
    private String logo;

    /** 网站描述 */
    @Excel(name = "网站描述")
    private String description;

    /** 0-下架 1-正常 */
    @Excel(name = "0-下架 1-正常")
    private Integer status;

    /** 排序值 */
    @Excel(name = "排序值")
    private Integer sortOrder;

    public void setFriendlinkId(Long friendlinkId) 
    {
        this.friendlinkId = friendlinkId;
    }

    public Long getFriendlinkId() 
    {
        return friendlinkId;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("friendlinkId", getFriendlinkId())
            .append("author", getAuthor())
            .append("name", getName())
            .append("url", getUrl())
            .append("logo", getLogo())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("sortOrder", getSortOrder())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
