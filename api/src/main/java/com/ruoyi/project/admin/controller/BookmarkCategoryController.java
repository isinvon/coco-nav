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
import com.ruoyi.project.admin.domain.BookmarkCategory;
import com.ruoyi.project.admin.service.BookmarkCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 书签分类Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkCategory")
public class BookmarkCategoryController extends BaseController {
    @Resource
    private BookmarkCategoryService bookmarkCategoryService;

    /**
     * 查询书签分类列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<BookmarkCategory> list = bookmarkCategoryService.list();
        return getDataTable(list);
    }

    /**
     * 导出书签分类列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_EXPORT)
    @Log(title = "书签分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<BookmarkCategory> list = bookmarkCategoryService.list();
        ExcelUtil<BookmarkCategory> util = new ExcelUtil<>(BookmarkCategory.class);
        util.exportExcel(response, list, "书签分类数据");
    }

    /**
     * 获取书签分类详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_QUERY)
    @GetMapping(value = "/{bookmarkCategoryId}")
    public AjaxResult getInfo(@PathVariable("bookmarkCategoryId") Long bookmarkCategoryId) {
        return success(bookmarkCategoryService.getById(bookmarkCategoryId));
    }

    /**
     * 新增书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_ADD)
    @Log(title = "书签分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkCategory bookmarkCategory) {
        return toAjax(bookmarkCategoryService.save(bookmarkCategory));
    }

    /**
     * 修改书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_EDIT)
    @Log(title = "书签分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkCategory bookmarkCategory) {
        LambdaUpdateWrapper<BookmarkCategory> uw = new LambdaUpdateWrapper<>();
        uw.eq(BookmarkCategory::getId, bookmarkCategory.getId());
        return toAjax(bookmarkCategoryService.update(bookmarkCategory, uw));
    }

    /**
     * 删除书签分类
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_CATEGORY_REMOVE)
    @Log(title = "书签分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bookmarkCategoryIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkCategoryIds) {
        List<Long> idList = Arrays.asList(bookmarkCategoryIds);
        return toAjax(bookmarkCategoryService.removeByIds(idList));
    }
}
