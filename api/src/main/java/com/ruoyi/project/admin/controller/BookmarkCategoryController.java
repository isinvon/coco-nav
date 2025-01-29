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
import com.ruoyi.project.admin.domain.BookmarkCategory;
import com.ruoyi.project.admin.service.IBookmarkCategoryCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    private IBookmarkCategoryCustomService bookmarkCategoryCustomService;

    /**
     * 查询书签分类列表
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:list')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:export')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:query')")
    @GetMapping(value = "/{bookmarkCategoryId}")
    public AjaxResult getInfo(@PathVariable("bookmarkCategoryId") Long bookmarkCategoryId)
    {
        return success(bookmarkCategoryCustomService.selectBookmarkCategoryByBookmarkCategoryId(bookmarkCategoryId));
    }

    /**
     * 新增书签分类
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:add')")
    @Log(title = "书签分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkCategory bookmarkCategory)
    {
        return toAjax(bookmarkCategoryCustomService.insertBookmarkCategory(bookmarkCategory));
    }

    /**
     * 修改书签分类
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:edit')")
    @Log(title = "书签分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkCategory bookmarkCategory)
    {
        return toAjax(bookmarkCategoryCustomService.updateBookmarkCategory(bookmarkCategory));
    }

    /**
     * 删除书签分类
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkCategory:remove')")
    @Log(title = "书签分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkCategoryIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkCategoryIds)
    {
        return toAjax(bookmarkCategoryCustomService.deleteBookmarkCategoryByBookmarkCategoryIds(bookmarkCategoryIds));
    }
}
