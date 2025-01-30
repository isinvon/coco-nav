package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BookmarkArticle;
import com.ruoyi.project.admin.service.BookmarkArticleCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 书签文章Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkArticle")
public class BookmarkArticleController extends BaseController
{
    @Autowired
    private BookmarkArticleCustomService bookmarkArticleCustomService;

    /**
     * 查询书签文章列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BookmarkArticle bookmarkArticle)
    {
        startPage();
        List<BookmarkArticle> list = bookmarkArticleCustomService.selectBookmarkArticleList(bookmarkArticle);
        return getDataTable(list);
    }

    /**
     * 导出书签文章列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_EXPORT)
    @Log(title = "书签文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookmarkArticle bookmarkArticle)
    {
        List<BookmarkArticle> list = bookmarkArticleCustomService.selectBookmarkArticleList(bookmarkArticle);
        ExcelUtil<BookmarkArticle> util = new ExcelUtil<BookmarkArticle>(BookmarkArticle.class);
        util.exportExcel(response, list, "书签文章数据");
    }

    /**
     * 获取书签文章详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_QUERY)
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return success(bookmarkArticleCustomService.selectBookmarkArticleByArticleId(articleId));
    }

    /**
     * 新增书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_ADD)
    @Log(title = "书签文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkArticle bookmarkArticle)
    {
        return toAjax(bookmarkArticleCustomService.insertBookmarkArticle(bookmarkArticle));
    }

    /**
     * 修改书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_EDIT)
    @Log(title = "书签文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkArticle bookmarkArticle)
    {
        return toAjax(bookmarkArticleCustomService.updateBookmarkArticle(bookmarkArticle));
    }

    /**
     * 删除书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_REMOVE)
    @Log(title = "书签文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(bookmarkArticleCustomService.deleteBookmarkArticleByArticleIds(articleIds));
    }
}
