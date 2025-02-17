package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.CustomService;
import com.ruoyi.project.admin.service.CustomServiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 客服信息Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/customService")
public class CustomServiceController extends BaseController {
    @Resource
    private CustomServiceService customServiceService;

    /**
     * 查询客服信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<CustomService> list = customServiceService.list();
        return getDataTable(list);
    }

    /**
     * 导出客服信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_EXPORT)
    @Log(title = "客服信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<CustomService> list = customServiceService.list();
        ExcelUtil<CustomService> util = new ExcelUtil<>(CustomService.class);
        util.exportExcel(response, list, "客服信息数据");
    }

    /**
     * 获取客服信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_QUERY)
    @GetMapping(value = "/{customServiceId}")
    public AjaxResult getInfo(@PathVariable("customServiceId") Long customServiceId) {
        return success(customServiceService.getById(customServiceId));
    }

    /**
     * 新增客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_ADD)
    @Log(title = "客服信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomService customService) {
        return toAjax(customServiceService.save(customService));
    }

    /**
     * 修改客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_EDIT)
    @Log(title = "客服信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomService customService) {
        return toAjax(customServiceService.updateById(customService));
    }

    /**
     * 删除客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_REMOVE)
    @Log(title = "客服信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customServiceIds}")
    public AjaxResult remove(@PathVariable Long[] customServiceIds) {
        List<Long> idList = Arrays.asList(customServiceIds);
        return toAjax(customServiceService.removeByIds(idList));
    }
}
