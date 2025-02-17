package com.ruoyi.common.model.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.common.model.domain.po.BookmarkTag;
import lombok.Data;

import java.util.List;

/**
 * 书签视图对象
 *
 * @author : sinvon
 * @since :  2025/2/9 下午2:52
 */
@Data
public class BookmarkVo extends BaseEntity {

    /**
     * 书签ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Excel(name = "书签ID")
    private Long id;

    /**
     * 网站标题
     */
    @Excel(name = "网站标题")
    private String title;

    /**
     * 网站地址
     */
    @Excel(name = "网站地址")
    private String url;

    /**
     * 网站描述
     */
    @Excel(name = "网站描述")
    private String description;

    /**
     * 分类ID
     */
    @Excel(name = "分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long bookmarkCategoryId;

    /**
     * 网站图标
     */
    @Excel(name = "网站图标")
    private String icon;

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
     * 0-禁用 1-启用
     */
    @Excel(name = "0-禁用 1-启用")
    private Integer status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
    private Integer isDeleted;

    /**
     * 标签
     * <p/>
     * 书签实体中增加非持久化属性tags，用于封装查询到的标签集合
     */
    @Excel(name = "标签")
    private List<BookmarkTag> bookmarkTags;
}
