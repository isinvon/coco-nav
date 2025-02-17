package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.SearchLog;
import com.ruoyi.common.model.service.SearchLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 搜索日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/searchLog")
public class SearchLogController extends BaseController {
    @Resource
    private SearchLogService searchLogService;

    /**
     * 查询搜索日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<SearchLog> list = searchLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出搜索日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_EXPORT)
    @Log(title = "搜索日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<SearchLog> list = searchLogService.list();
        ExcelUtil<SearchLog> util = new ExcelUtil<>(SearchLog.class);
        util.exportExcel(response, list, "搜索日志数据");
    }

    /**
     * 获取搜索日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_QUERY)
    @GetMapping(value = "/{searchLogId}")
    public AjaxResult getInfo(@PathVariable("searchLogId") Long searchLogId) {
        return success(searchLogService.getById(searchLogId));
    }

    /**
     * 新增搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_ADD)
    @Log(title = "搜索日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SearchLog searchLog) {
        return toAjax(searchLogService.save(searchLog));
    }

    /**
     * 修改搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_EDIT)
    @Log(title = "搜索日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SearchLog searchLog) {
        return toAjax(searchLogService.updateById(searchLog));
    }

    /**
     * 删除搜索日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SEARCH_LOG_REMOVE)
    @Log(title = "搜索日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{searchLogIds}")
    public AjaxResult remove(@PathVariable Long[] searchLogIds) {
        List<Long> idList = Arrays.asList(searchLogIds);
        return toAjax(searchLogService.removeByIds(idList));
    }
}
