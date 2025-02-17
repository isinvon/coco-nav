package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.Setting;
import com.ruoyi.common.model.service.SettingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 系统设置Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {
    @Resource
    private SettingService settingService;

    /**
     * 查询系统设置列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Setting> list = settingService.list();
        return getDataTable(list);
    }

    /**
     * 导出系统设置列表
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_EXPORT)
    @Log(title = "系统设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Setting> list = settingService.list();
        ExcelUtil<Setting> util = new ExcelUtil<>(Setting.class);
        util.exportExcel(response, list, "系统设置数据");
    }

    /**
     * 获取系统设置详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_QUERY)
    @GetMapping(value = "/{settingId}")
    public AjaxResult getInfo(@PathVariable("settingId") Long settingId) {
        return success(settingService.getById(settingId));
    }

    /**
     * 新增系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_ADD)
    @Log(title = "系统设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Setting setting) {
        return toAjax(settingService.save(setting));
    }

    /**
     * 修改系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_EDIT)
    @Log(title = "系统设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Setting setting) {
        return toAjax(settingService.updateById(setting));
    }

    /**
     * 删除系统设置
     */
    @CustomPermission(PermissionConstants.ADMIN_SETTING_REMOVE)
    @Log(title = "系统设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{settingIds}")
    public AjaxResult remove(@PathVariable Long[] settingIds) {
        List<Long> idList = Arrays.asList(settingIds);
        return toAjax(settingService.removeByIds(idList));
    }
}
