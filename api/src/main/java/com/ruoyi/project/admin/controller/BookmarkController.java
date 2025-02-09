package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.domain.vo.BookmarkVo;
import com.ruoyi.project.admin.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 书签管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/bookmark")
public class BookmarkController extends BaseController {
    @Resource
    private BookmarkService bookmarkService;

    /**
     * 查询书签管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BookmarkVo bookmarkVo) {
        startPage();
        List<BookmarkVo> list = bookmarkService.getBookmarkListByQueryCondition(bookmarkVo);
        return getDataTable(list);
    }

    /**
     * 导出书签管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_EXPORT)
    @Log(title = "书签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Bookmark> list = bookmarkService.list();
        ExcelUtil<Bookmark> util = new ExcelUtil<>(Bookmark.class);
        util.exportExcel(response, list, "书签管理数据");
    }

    /**
     * 获取书签管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_QUERY)
    @GetMapping(value = "/{bookmarkId}")
    public AjaxResult getInfo(@PathVariable("bookmarkId") Long bookmarkId) {
        return success(bookmarkService.getBookmarkById(bookmarkId));
    }

    /**
     * 新增书签管理
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_ADD)
    @Log(title = "书签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bookmark bookmark) {
        return toAjax(bookmarkService.addBookmark(bookmark));
    }

    /**
     * 修改书签管理
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_EDIT)
    @Log(title = "书签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bookmark bookmark) {
        return toAjax(bookmarkService.updateBookmark(bookmark));
    }

    /**
     * 删除书签管理
     */
    @CustomPermission(PermissionConstants.ADMIN_BOOKMARK_REMOVE)
    @Log(title = "书签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bookmarkIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkIds) {
        List<Long> idList = Arrays.asList(bookmarkIds);
        return toAjax(bookmarkService.deleteBookmark(idList));
    }

    /**
     * 获取 url 信息
     *
     * @param url 要爬取的网站链接
     * @return AjaxResult , 返回 Map, 包含 icon、title 和 description
     */
    @GetMapping("/getUrlInfoByCrawler")
    public AjaxResult getUrlInfoByCrawler(String url) {
        Map<String, String> map = bookmarkService.getUrlInfoByCrawler(url);
        return success(map);
    }

    @GetMapping("/index")
    public AjaxResult index() {
        return success(Map.of(
                "bookmarkStatusTypeList", Bookmark.bookmarkStatusTypeList, // 书签状态类型;
                "bookmarkDeleteStatusList", Bookmark.bookmarkDeleteStatusList, // 书签删除状态类型
                "bookmarkDefaultIcon", Bookmark.BOOKMARK_ICON_DEFAULT // 书签默认图标
        ));
    }
}
