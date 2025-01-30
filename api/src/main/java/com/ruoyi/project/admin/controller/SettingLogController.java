package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.framework.security.permission.CustomPermission;
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
import com.ruoyi.project.admin.domain.SettingLog;
import com.ruoyi.project.admin.service.custom.SettingLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 设置变更日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/settingLog")
public class SettingLogController extends BaseController
{
    @Autowired
    private SettingLogCustomService settingLogCustomService;

    /**
     * 查询设置变更日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(SettingLog settingLog)
    {
        startPage();
        List<SettingLog> list = settingLogCustomService.selectSettingLogList(settingLog);
        return getDataTable(list);
    }

    /**
     * 导出设置变更日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_EXPORT)
    @Log(title = "设置变更日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SettingLog settingLog)
    {
        List<SettingLog> list = settingLogCustomService.selectSettingLogList(settingLog);
        ExcelUtil<SettingLog> util = new ExcelUtil<SettingLog>(SettingLog.class);
        util.exportExcel(response, list, "设置变更日志数据");
    }

    /**
     * 获取设置变更日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_QUERY)
    @GetMapping(value = "/{settingLogId}")
    public AjaxResult getInfo(@PathVariable("settingLogId") Long settingLogId)
    {
        return success(settingLogCustomService.selectSettingLogBySettingLogId(settingLogId));
    }

    /**
     * 新增设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_ADD)
    @Log(title = "设置变更日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SettingLog settingLog)
    {
        return toAjax(settingLogCustomService.insertSettingLog(settingLog));
    }

    /**
     * 修改设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_EDIT)
    @Log(title = "设置变更日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SettingLog settingLog)
    {
        return toAjax(settingLogCustomService.updateSettingLog(settingLog));
    }

    /**
     * 删除设置变更日志
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LOG_REMOVE)
    @Log(title = "设置变更日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{settingLogIds}")
    public AjaxResult remove(@PathVariable Long[] settingLogIds)
    {
        return toAjax(settingLogCustomService.deleteSettingLogBySettingLogIds(settingLogIds));
    }
}
