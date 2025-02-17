package com.ruoyi.common.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.model.domain.po.BookmarkTag;

import java.util.List;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface BookmarkTagService extends IService<BookmarkTag> {

    /**
     * 根据查询条件获取书签标签列表
     *
     * @param bookmarkTag
     * @return
     */
    List<BookmarkTag> getBookmarkTagListByQueryCondition(BookmarkTag bookmarkTag);

    /**
     * 查询书签标签列表,返回List<String>的tagName
     *
     * @return List<String>
     */
    List<String> getBookmarkTagNameList();
}
