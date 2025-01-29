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
import com.ruoyi.project.admin.domain.Bookmark;
import com.ruoyi.project.admin.service.IBookmarkCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 书签管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmark")
public class BookmarkController extends BaseController
{
    @Autowired
    private IBookmarkCustomService bookmarkCustomService;

    /**
     * 查询书签管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bookmark bookmark)
    {
        startPage();
        List<Bookmark> list = bookmarkCustomService.selectBookmarkList(bookmark);
        return getDataTable(list);
    }

    /**
     * 导出书签管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:export')")
    @Log(title = "书签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bookmark bookmark)
    {
        List<Bookmark> list = bookmarkCustomService.selectBookmarkList(bookmark);
        ExcelUtil<Bookmark> util = new ExcelUtil<Bookmark>(Bookmark.class);
        util.exportExcel(response, list, "书签管理数据");
    }

    /**
     * 获取书签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:query')")
    @GetMapping(value = "/{bookmarkId}")
    public AjaxResult getInfo(@PathVariable("bookmarkId") Long bookmarkId)
    {
        return success(bookmarkCustomService.selectBookmarkByBookmarkId(bookmarkId));
    }

    /**
     * 新增书签管理
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:add')")
    @Log(title = "书签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bookmark bookmark)
    {
        return toAjax(bookmarkCustomService.insertBookmark(bookmark));
    }

    /**
     * 修改书签管理
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:edit')")
    @Log(title = "书签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bookmark bookmark)
    {
        return toAjax(bookmarkCustomService.updateBookmark(bookmark));
    }

    /**
     * 删除书签管理
     */
    @PreAuthorize("@ss.hasPermi('admin:bookmark:remove')")
    @Log(title = "书签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkIds)
    {
        return toAjax(bookmarkCustomService.deleteBookmarkByBookmarkIds(bookmarkIds));
    }
}
