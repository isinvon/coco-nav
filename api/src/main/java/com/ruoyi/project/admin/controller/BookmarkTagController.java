package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BookmarkTag;
import com.ruoyi.project.admin.service.BookmarkTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 书签标签Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkTag")
public class BookmarkTagController extends BaseController {
    @Autowired
    private BookmarkTagService bookmarkTagService;

    /**
     * 查询书签标签列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<BookmarkTag> list = bookmarkTagService.list();
        return getDataTable(list);
    }

    /**
     * 导出书签标签列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_EXPORT)
    @Log(title = "书签标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<BookmarkTag> list = bookmarkTagService.list();
        ExcelUtil<BookmarkTag> util = new ExcelUtil<>(BookmarkTag.class);
        util.exportExcel(response, list, "书签标签数据");
    }

    /**
     * 获取书签标签详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_QUERY)
    @GetMapping(value = "/{bookmarkTagId}")
    public AjaxResult getInfo(@PathVariable("bookmarkTagId") Long bookmarkTagId) {
        return success(bookmarkTagService.getById(bookmarkTagId));
    }

    /**
     * 新增书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_ADD)
    @Log(title = "书签标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkTag bookmarkTag) {
        return toAjax(bookmarkTagService.save(bookmarkTag));
    }

    /**
     * 修改书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_EDIT)
    @Log(title = "书签标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkTag bookmarkTag) {
        LambdaQueryWrapper<BookmarkTag> qw = new LambdaQueryWrapper<>();
        qw.eq(BookmarkTag::getBookmarkTagId, bookmarkTag.getBookmarkTagId());
        return toAjax(bookmarkTagService.update(bookmarkTag, qw));
    }

    /**
     * 删除书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_REMOVE)
    @Log(title = "书签标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bookmarkTagIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkTagIds) {
        List<Long> idList = Arrays.asList(bookmarkTagIds);
        return toAjax(bookmarkTagService.removeByIds(idList));
    }
}
