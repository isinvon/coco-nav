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
import com.ruoyi.project.admin.domain.SearchLog;
import com.ruoyi.project.admin.service.ISearchLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 搜索日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/searchLog")
public class SearchLogController extends BaseController
{
    @Autowired
    private ISearchLogCustomService searchLogCustomService;

    /**
     * 查询搜索日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(SearchLog searchLog)
    {
        startPage();
        List<SearchLog> list = searchLogCustomService.selectSearchLogList(searchLog);
        return getDataTable(list);
    }

    /**
     * 导出搜索日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_EXPORT)
    @Log(title = "搜索日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SearchLog searchLog)
    {
        List<SearchLog> list = searchLogCustomService.selectSearchLogList(searchLog);
        ExcelUtil<SearchLog> util = new ExcelUtil<SearchLog>(SearchLog.class);
        util.exportExcel(response, list, "搜索日志数据");
    }

    /**
     * 获取搜索日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_QUERY)
    @GetMapping(value = "/{searchLogId}")
    public AjaxResult getInfo(@PathVariable("searchLogId") Long searchLogId)
    {
        return success(searchLogCustomService.selectSearchLogBySearchLogId(searchLogId));
    }

    /**
     * 新增搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_ADD)
    @Log(title = "搜索日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SearchLog searchLog)
    {
        return toAjax(searchLogCustomService.insertSearchLog(searchLog));
    }

    /**
     * 修改搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_EDIT)
    @Log(title = "搜索日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SearchLog searchLog)
    {
        return toAjax(searchLogCustomService.updateSearchLog(searchLog));
    }

    /**
     * 删除搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_REMOVE)
    @Log(title = "搜索日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{searchLogIds}")
    public AjaxResult remove(@PathVariable Long[] searchLogIds)
    {
        return toAjax(searchLogCustomService.deleteSearchLogBySearchLogIds(searchLogIds));
    }
}
