package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BookmarkLog;
import com.ruoyi.project.admin.service.custom.BookmarkLogCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 书签操作日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmarkLog")
public class BookmarkLogController extends BaseController
{
    @Autowired
    private BookmarkLogCustomService bookmarkLogCustomService;

    /**
     * 查询书签操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BookmarkLog bookmarkLog)
    {
        startPage();
        List<BookmarkLog> list = bookmarkLogCustomService.selectBookmarkLogList(bookmarkLog);
        return getDataTable(list);
    }

    /**
     * 导出书签操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_EXPORT)
    @Log(title = "书签操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookmarkLog bookmarkLog)
    {
        List<BookmarkLog> list = bookmarkLogCustomService.selectBookmarkLogList(bookmarkLog);
        ExcelUtil<BookmarkLog> util = new ExcelUtil<BookmarkLog>(BookmarkLog.class);
        util.exportExcel(response, list, "书签操作日志数据");
    }

    /**
     * 获取书签操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_QUERY)
    @GetMapping(value = "/{bookmarkLogId}")
    public AjaxResult getInfo(@PathVariable("bookmarkLogId") Long bookmarkLogId)
    {
        return success(bookmarkLogCustomService.selectBookmarkLogByBookmarkLogId(bookmarkLogId));
    }

    /**
     * 新增书签操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_ADD)
    @Log(title = "书签操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkLog bookmarkLog)
    {
        return toAjax(bookmarkLogCustomService.insertBookmarkLog(bookmarkLog));
    }

    /**
     * 修改书签操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_EDIT)
    @Log(title = "书签操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkLog bookmarkLog)
    {
        return toAjax(bookmarkLogCustomService.updateBookmarkLog(bookmarkLog));
    }

    /**
     * 删除书签操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LOG_REMOVE)
    @Log(title = "书签操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkLogIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkLogIds)
    {
        return toAjax(bookmarkLogCustomService.deleteBookmarkLogByBookmarkLogIds(bookmarkLogIds));
    }
}
