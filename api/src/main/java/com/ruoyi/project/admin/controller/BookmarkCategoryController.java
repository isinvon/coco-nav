package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BookmarkCategory;
import com.ruoyi.project.admin.service.BookmarkCategoryCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 书签分类Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkCategory")
public class BookmarkCategoryController extends BaseController
{
    @Autowired
    private BookmarkCategoryCustomService bookmarkCategoryCustomService;

    /**
     * 查询书签分类列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BookmarkCategory bookmarkCategory)
    {
        startPage();
        List<BookmarkCategory> list = bookmarkCategoryCustomService.selectBookmarkCategoryList(bookmarkCategory);
        return getDataTable(list);
    }

    /**
     * 导出书签分类列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_EXPORT)
    @Log(title = "书签分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookmarkCategory bookmarkCategory)
    {
        List<BookmarkCategory> list = bookmarkCategoryCustomService.selectBookmarkCategoryList(bookmarkCategory);
        ExcelUtil<BookmarkCategory> util = new ExcelUtil<BookmarkCategory>(BookmarkCategory.class);
        util.exportExcel(response, list, "书签分类数据");
    }

    /**
     * 获取书签分类详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_QUERY)
    @GetMapping(value = "/{bookmarkCategoryId}")
    public AjaxResult getInfo(@PathVariable("bookmarkCategoryId") Long bookmarkCategoryId)
    {
        return success(bookmarkCategoryCustomService.selectBookmarkCategoryByBookmarkCategoryId(bookmarkCategoryId));
    }

    /**
     * 新增书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_ADD)
    @Log(title = "书签分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkCategory bookmarkCategory)
    {
        return toAjax(bookmarkCategoryCustomService.insertBookmarkCategory(bookmarkCategory));
    }

    /**
     * 修改书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_EDIT)
    @Log(title = "书签分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkCategory bookmarkCategory)
    {
        return toAjax(bookmarkCategoryCustomService.updateBookmarkCategory(bookmarkCategory));
    }

    /**
     * 删除书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_REMOVE)
    @Log(title = "书签分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkCategoryIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkCategoryIds)
    {
        return toAjax(bookmarkCategoryCustomService.deleteBookmarkCategoryByBookmarkCategoryIds(bookmarkCategoryIds));
    }
}
