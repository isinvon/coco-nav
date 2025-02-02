package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.po.Bookmark;

import java.util.List;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface BookmarkService extends IService<Bookmark> {
    List<Bookmark> getBookmarkListByQueryCondition(Bookmark bookmark);

    Boolean addBookmark(Bookmark bookmark);

    Boolean updateBookmark(Bookmark bookmark);

    Boolean deleteBookmark(List<Long> idList);
}
