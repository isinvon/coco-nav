package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.framework.security.permission.CustomPermission;
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
import com.ruoyi.project.admin.domain.BookmarkTag;
import com.ruoyi.project.admin.service.IBookmarkTagCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 书签标签Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkTag")
public class BookmarkTagController extends BaseController
{
    @Autowired
    private IBookmarkTagCustomService bookmarkTagCustomService;

    /**
     * 查询书签标签列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BookmarkTag bookmarkTag)
    {
        startPage();
        List<BookmarkTag> list = bookmarkTagCustomService.selectBookmarkTagList(bookmarkTag);
        return getDataTable(list);
    }

    /**
     * 导出书签标签列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_EXPORT)
    @Log(title = "书签标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookmarkTag bookmarkTag)
    {
        List<BookmarkTag> list = bookmarkTagCustomService.selectBookmarkTagList(bookmarkTag);
        ExcelUtil<BookmarkTag> util = new ExcelUtil<BookmarkTag>(BookmarkTag.class);
        util.exportExcel(response, list, "书签标签数据");
    }

    /**
     * 获取书签标签详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_QUERY)
    @GetMapping(value = "/{bookmarkTagId}")
    public AjaxResult getInfo(@PathVariable("bookmarkTagId") Long bookmarkTagId)
    {
        return success(bookmarkTagCustomService.selectBookmarkTagByBookmarkTagId(bookmarkTagId));
    }

    /**
     * 新增书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_ADD)
    @Log(title = "书签标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkTag bookmarkTag)
    {
        return toAjax(bookmarkTagCustomService.insertBookmarkTag(bookmarkTag));
    }

    /**
     * 修改书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_EDIT)
    @Log(title = "书签标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkTag bookmarkTag)
    {
        return toAjax(bookmarkTagCustomService.updateBookmarkTag(bookmarkTag));
    }

    /**
     * 删除书签标签
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_TAG_REMOVE)
    @Log(title = "书签标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkTagIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkTagIds)
    {
        return toAjax(bookmarkTagCustomService.deleteBookmarkTagByBookmarkTagIds(bookmarkTagIds));
    }
}
