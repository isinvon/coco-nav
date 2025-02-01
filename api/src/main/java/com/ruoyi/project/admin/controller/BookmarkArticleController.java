package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BookmarkArticle;
import com.ruoyi.project.admin.service.BookmarkArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 书签文章Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkArticle")
public class BookmarkArticleController extends BaseController {
    @Resource
    private BookmarkArticleService bookmarkArticleService;

    /**
     * 查询书签文章列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<BookmarkArticle> list = bookmarkArticleService.list();
        return getDataTable(list);
    }

    /**
     * 导出书签文章列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_EXPORT)
    @Log(title = "书签文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<BookmarkArticle> list = bookmarkArticleService.list();
        ExcelUtil<BookmarkArticle> util = new ExcelUtil<>(BookmarkArticle.class);
        util.exportExcel(response, list, "书签文章数据");
    }

    /**
     * 获取书签文章详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_QUERY)
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
        return success(bookmarkArticleService.getById(articleId));
    }

    /**
     * 新增书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_ADD)
    @Log(title = "书签文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkArticle bookmarkArticle) {
        return toAjax(bookmarkArticleService.save(bookmarkArticle));
    }

    /**
     * 修改书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_EDIT)
    @Log(title = "书签文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkArticle bookmarkArticle) {
        LambdaUpdateWrapper<BookmarkArticle> uw = new LambdaUpdateWrapper<>();
        uw.eq(BookmarkArticle::getArticleId, bookmarkArticle.getArticleId());
        return toAjax(bookmarkArticleService.update(bookmarkArticle, uw));
    }

    /**
     * 删除书签文章
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ARTICLE_REMOVE)
    @Log(title = "书签文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds) {
        List<Long> idList = Arrays.asList(articleIds);
        return toAjax(bookmarkArticleService.removeByIds(idList));
    }
}
