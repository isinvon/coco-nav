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
import com.ruoyi.project.admin.domain.Setting;
import com.ruoyi.project.admin.service.ISettingCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 系统设置Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/setting")
public class SettingController extends BaseController
{
    @Autowired
    private ISettingCustomService settingCustomService;

    /**
     * 查询系统设置列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Setting setting)
    {
        startPage();
        List<Setting> list = settingCustomService.selectSettingList(setting);
        return getDataTable(list);
    }

    /**
     * 导出系统设置列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_EXPORT)
    @Log(title = "系统设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Setting setting)
    {
        List<Setting> list = settingCustomService.selectSettingList(setting);
        ExcelUtil<Setting> util = new ExcelUtil<Setting>(Setting.class);
        util.exportExcel(response, list, "系统设置数据");
    }

    /**
     * 获取系统设置详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_QUERY)
    @GetMapping(value = "/{settingId}")
    public AjaxResult getInfo(@PathVariable("settingId") Long settingId)
    {
        return success(settingCustomService.selectSettingBySettingId(settingId));
    }

    /**
     * 新增系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_ADD)
    @Log(title = "系统设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Setting setting)
    {
        return toAjax(settingCustomService.insertSetting(setting));
    }

    /**
     * 修改系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_EDIT)
    @Log(title = "系统设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Setting setting)
    {
        return toAjax(settingCustomService.updateSetting(setting));
    }

    /**
     * 删除系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_REMOVE)
    @Log(title = "系统设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{settingIds}")
    public AjaxResult remove(@PathVariable Long[] settingIds)
    {
        return toAjax(settingCustomService.deleteSettingBySettingIds(settingIds));
    }
}
