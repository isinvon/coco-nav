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
import com.ruoyi.project.admin.domain.BookmarkLog;
import com.ruoyi.project.admin.service.IBookmarkLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    private IBookmarkLogCustomService bookmarkLogCustomService;

    /**
     * 查询书签操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:list')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:export')")
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
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:query')")
    @GetMapping(value = "/{bookmarkLogId}")
    public AjaxResult getInfo(@PathVariable("bookmarkLogId") Long bookmarkLogId)
    {
        return success(bookmarkLogCustomService.selectBookmarkLogByBookmarkLogId(bookmarkLogId));
    }

    /**
     * 新增书签操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:add')")
    @Log(title = "书签操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookmarkLog bookmarkLog)
    {
        return toAjax(bookmarkLogCustomService.insertBookmarkLog(bookmarkLog));
    }

    /**
     * 修改书签操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:edit')")
    @Log(title = "书签操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookmarkLog bookmarkLog)
    {
        return toAjax(bookmarkLogCustomService.updateBookmarkLog(bookmarkLog));
    }

    /**
     * 删除书签操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmarkLog:remove')")
    @Log(title = "书签操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkLogIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkLogIds)
    {
        return toAjax(bookmarkLogCustomService.deleteBookmarkLogByBookmarkLogIds(bookmarkLogIds));
    }
}
