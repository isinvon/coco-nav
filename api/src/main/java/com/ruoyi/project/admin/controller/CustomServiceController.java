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
import com.ruoyi.project.admin.domain.CustomService;
import com.ruoyi.project.admin.service.ICustomServiceCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    private ICustomServiceCustomService customServiceCustomService;

    /**
     * 查询客服信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:customService:list')")
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
    @PreAuthorize("@ss.hasPermi('admin:customService:export')")
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
    @PreAuthorize("@ss.hasPermi('admin:customService:query')")
    @GetMapping(value = "/{customServiceId}")
    public AjaxResult getInfo(@PathVariable("customServiceId") Long customServiceId)
    {
        return success(customServiceCustomService.selectCustomServiceByCustomServiceId(customServiceId));
    }

    /**
     * 新增客服信息
     */
    @PreAuthorize("@ss.hasPermi('admin:customService:add')")
    @Log(title = "客服信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomService customService)
    {
        return toAjax(customServiceCustomService.insertCustomService(customService));
    }

    /**
     * 修改客服信息
     */
    @PreAuthorize("@ss.hasPermi('admin:customService:edit')")
    @Log(title = "客服信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomService customService)
    {
        return toAjax(customServiceCustomService.updateCustomService(customService));
    }

    /**
     * 删除客服信息
     */
    @PreAuthorize("@ss.hasPermi('admin:customService:remove')")
    @Log(title = "客服信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customServiceIds}")
    public AjaxResult remove(@PathVariable Long[] customServiceIds)
    {
        return toAjax(customServiceCustomService.deleteCustomServiceByCustomServiceIds(customServiceIds));
    }
}
