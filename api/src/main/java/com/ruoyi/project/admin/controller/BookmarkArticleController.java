package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.admin.domain.BookmarkArticle;
import com.ruoyi.project.admin.service.IBookmarkArticleCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    private IBookmarkArticleCustomService bookmarkArticleCustomService;

    /**
     * 查询书签文章列表
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:list')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:export')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return success(bookmarkArticleCustomService.selectBookmarkArticleByArticleId(articleId));
    }

    /**
     * 新增书签文章
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:add')")
    @Log(title = "书签文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkArticle bookmarkArticle)
    {
        return toAjax(bookmarkArticleCustomService.insertBookmarkArticle(bookmarkArticle));
    }

    /**
     * 修改书签文章
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:edit')")
    @Log(title = "书签文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkArticle bookmarkArticle)
    {
        return toAjax(bookmarkArticleCustomService.updateBookmarkArticle(bookmarkArticle));
    }

    /**
     * 删除书签文章
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkArticle:remove')")
    @Log(title = "书签文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(bookmarkArticleCustomService.deleteBookmarkArticleByArticleIds(articleIds));
    }
}
