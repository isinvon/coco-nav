package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.SettingLog;
import com.ruoyi.project.admin.service.SettingLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 设置变更日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/settingLog")
public class SettingLogController extends BaseController {
    @Resource
    private SettingLogService settingLogService;

    /**
     * 查询设置变更日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<SettingLog> list = settingLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出设置变更日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_EXPORT)
    @Log(title = "设置变更日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<SettingLog> list = settingLogService.list();
        ExcelUtil<SettingLog> util = new ExcelUtil<>(SettingLog.class);
        util.exportExcel(response, list, "设置变更日志数据");
    }

    /**
     * 获取设置变更日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_QUERY)
    @GetMapping(value = "/{settingLogId}")
    public AjaxResult getInfo(@PathVariable("settingLogId") Long settingLogId) {
        return success(settingLogService.getById(settingLogId));
    }

    /**
     * 新增设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_ADD)
    @Log(title = "设置变更日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SettingLog settingLog) {
        return toAjax(settingLogService.save(settingLog));
    }

    /**
     * 修改设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_EDIT)
    @Log(title = "设置变更日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SettingLog settingLog) {
        return toAjax(settingLogService.updateById(settingLog));
    }

    /**
     * 删除设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_REMOVE)
    @Log(title = "设置变更日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{settingLogIds}")
    public AjaxResult remove(@PathVariable Long[] settingLogIds) {
        List<Long> idList = Arrays.asList(settingLogIds);
        return toAjax(settingLogService.removeByIds(idList));
    }
}
