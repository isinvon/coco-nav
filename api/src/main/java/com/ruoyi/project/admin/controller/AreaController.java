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
import com.ruoyi.project.admin.domain.Area;
import com.ruoyi.project.admin.service.AreaCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 地区信息Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/area")
public class AreaController extends BaseController
{
    @Autowired
    private AreaCustomService areaCustomService;

    /**
     * 查询地区信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Area area)
    {
        startPage();
        List<Area> list = areaCustomService.selectAreaList(area);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_EXPORT)
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Area area)
    {
        List<Area> list = areaCustomService.selectAreaList(area);
        ExcelUtil<Area> util = new ExcelUtil<Area>(Area.class);
        util.exportExcel(response, list, "地区信息数据");
    }

    /**
     * 获取地区信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_QUERY)
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId)
    {
        return success(areaCustomService.selectAreaByAreaId(areaId));
    }

    /**
     * 新增地区信息
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_ADD)
    @Log(title = "地区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Area area)
    {
        return toAjax(areaCustomService.insertArea(area));
    }

    /**
     * 修改地区信息
     */
    @PreAuthorize("@ss.hasPermi('admin:area:edit')")
    @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Area area)
    {
        return toAjax(areaCustomService.updateArea(area));
    }

    /**
     * 删除地区信息
     */
    @PreAuthorize("@ss.hasPermi('admin:area:remove')")
    @Log(title = "地区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{areaIds}")
    public AjaxResult remove(@PathVariable Long[] areaIds)
    {
        return toAjax(areaCustomService.deleteAreaByAreaIds(areaIds));
    }
}
