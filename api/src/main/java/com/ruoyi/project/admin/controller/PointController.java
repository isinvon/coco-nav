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
import com.ruoyi.project.admin.domain.Point;
import com.ruoyi.project.admin.service.IPointCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 积分账户Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/point")
public class PointController extends BaseController
{
    @Autowired
    private IPointCustomService pointCustomService;

    /**
     * 查询积分账户列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Point point)
    {
        startPage();
        List<Point> list = pointCustomService.selectPointList(point);
        return getDataTable(list);
    }

    /**
     * 导出积分账户列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_EXPORT)
    @Log(title = "积分账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Point point)
    {
        List<Point> list = pointCustomService.selectPointList(point);
        ExcelUtil<Point> util = new ExcelUtil<Point>(Point.class);
        util.exportExcel(response, list, "积分账户数据");
    }

    /**
     * 获取积分账户详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_QUERY)
    @GetMapping(value = "/{pointId}")
    public AjaxResult getInfo(@PathVariable("pointId") Long pointId)
    {
        return success(pointCustomService.selectPointByPointId(pointId));
    }

    /**
     * 新增积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_ADD)
    @Log(title = "积分账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Point point)
    {
        return toAjax(pointCustomService.insertPoint(point));
    }

    /**
     * 修改积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_EDIT)
    @Log(title = "积分账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Point point)
    {
        return toAjax(pointCustomService.updatePoint(point));
    }

    /**
     * 删除积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_REMOVE)
    @Log(title = "积分账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pointIds}")
    public AjaxResult remove(@PathVariable Long[] pointIds)
    {
        return toAjax(pointCustomService.deletePointByPointIds(pointIds));
    }
}
