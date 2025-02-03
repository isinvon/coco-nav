package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.po.BookmarkTag;

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
}
