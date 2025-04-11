package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.domain.po.BookmarkCategory;
import com.ruoyi.project.admin.domain.vo.BookmarkVo;

import java.util.List;
import java.util.Map;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface BookmarkService extends IService<Bookmark> {
    List<BookmarkVo> getBookmarkListByQueryCondition(BookmarkVo bookmarkVo);

    Boolean addBookmark(Bookmark bookmark);

    Boolean updateBookmark(BookmarkVo bookmarkVo);

    Boolean deleteBookmark(List<Long> idList);

    Map<String, String> getUrlInfoByCrawler(String url);

    BookmarkVo getBookmarkById(Long bookmarkId);

    Map<BookmarkCategory, List<BookmarkVo>> getCategoryAndBookmark();
}
