package com.ruoyi.project.app.controller;


import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.domain.po.BookmarkCategory;
import com.ruoyi.project.admin.domain.vo.BookmarkVo;
import com.ruoyi.project.admin.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 书签
 *
 * @author sinvon
 * @since 2025年2月17日23:37:11
 */
@Slf4j
@RestController
@RequestMapping("/app/bookmark")
public class AppBookmarkController extends BaseController {

    @Resource
    private BookmarkService bookmarkService;

    /**
     * 获取书签列表
     *
     * @return
     */
    @PostMapping("/list")
    public AjaxResult list() {
        List<Bookmark> list = bookmarkService.list();
        return success(list);
    }

    /**
     * 获取书签分类和书签列表
     * <p>
     * 示例:
     * <p>
     * 分类1 - 书签1, 书签2, 书签3
     * <p>
     * 分类2 - 书签4, 书签5, 书签6
     * <p>
     * ...
     *
     * @return
     */
    @PostMapping("/getCategoryAndBookmark")
    public AjaxResult getCategoryAndBookmark() {
        Map<BookmarkCategory, List<BookmarkVo>> map = bookmarkService.getCategoryAndBookmark();
        return success(map);
    }

    /**
     * 根据查询条件获取书签列表
     *
     * @param bookmarkVo
     * @return
     */
    @PostMapping("/getBookmarkListByQueryCondition")
    public AjaxResult getBookmarkListByQueryCondition(@RequestBody BookmarkVo bookmarkVo) {
        List<BookmarkVo> list = bookmarkService.getBookmarkListByQueryCondition(bookmarkVo);
        return success(list);
    }

    /**
     * 获取 url 信息
     *
     * @param url 要爬取的网站链接
     * @return AjaxResult , 返回 Map, 包含 icon、title 和 description
     */
    @PostMapping("/getUrlInfoByCrawler")
    public AjaxResult getUrlInfoByCrawler(String url) {
        Map<String, String> map = bookmarkService.getUrlInfoByCrawler(url);
        return success(map);
    }
}
