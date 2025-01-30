package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.CustomService;
import com.ruoyi.project.admin.service.CustomServiceCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客服信息Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/customService")
public class CustomServiceController extends BaseController
{
    @Autowired
    private CustomServiceCustomService customServiceCustomService;

    /**
     * 查询客服信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(CustomService customService)
    {
        startPage();
        List<CustomService> list = customServiceCustomService.selectCustomServiceList(customService);
        return getDataTable(list);
    }

    /**
     * 导出客服信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_EXPORT)
    @Log(title = "客服信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomService customService)
    {
        List<CustomService> list = customServiceCustomService.selectCustomServiceList(customService);
        ExcelUtil<CustomService> util = new ExcelUtil<CustomService>(CustomService.class);
        util.exportExcel(response, list, "客服信息数据");
    }

    /**
     * 获取客服信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_QUERY)
    @GetMapping(value = "/{customServiceId}")
    public AjaxResult getInfo(@PathVariable("customServiceId") Long customServiceId)
    {
        return success(customServiceCustomService.selectCustomServiceByCustomServiceId(customServiceId));
    }

    /**
     * 新增客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_ADD)
    @Log(title = "客服信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomService customService)
    {
        return toAjax(customServiceCustomService.insertCustomService(customService));
    }

    /**
     * 修改客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_EDIT)
    @Log(title = "客服信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomService customService)
    {
        return toAjax(customServiceCustomService.updateCustomService(customService));
    }

    /**
     * 删除客服信息
     */
    @CustomPermission(PermissionConstants.ADMIN_CUSTOMER_SERVICE_REMOVE)
    @Log(title = "客服信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customServiceIds}")
    public AjaxResult remove(@PathVariable Long[] customServiceIds)
    {
        return toAjax(customServiceCustomService.deleteCustomServiceByCustomServiceIds(customServiceIds));
    }
}
