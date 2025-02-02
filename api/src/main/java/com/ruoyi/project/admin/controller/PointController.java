package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.Point;
import com.ruoyi.project.admin.service.PointService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 积分账户Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/point")
public class PointController extends BaseController {
    @Resource
    private PointService pointService;

    /**
     * 查询积分账户列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Point> list = pointService.list();
        return getDataTable(list);
    }

    /**
     * 导出积分账户列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_EXPORT)
    @Log(title = "积分账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Point> list = pointService.list();
        ExcelUtil<Point> util = new ExcelUtil<>(Point.class);
        util.exportExcel(response, list, "积分账户数据");
    }

    /**
     * 获取积分账户详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_QUERY)
    @GetMapping(value = "/{pointId}")
    public AjaxResult getInfo(@PathVariable("pointId") Long pointId) {
        return success(pointService.getById(pointId));
    }

    /**
     * 新增积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_ADD)
    @Log(title = "积分账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Point point) {
        return toAjax(pointService.save(point));
    }

    /**
     * 修改积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_EDIT)
    @Log(title = "积分账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Point point) {
        return toAjax(pointService.updateById(point));
    }

    /**
     * 删除积分账户
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_REMOVE)
    @Log(title = "积分账户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pointIds}")
    public AjaxResult remove(@PathVariable Long[] pointIds) {
        List<Long> idList = Arrays.asList(pointIds);
        return toAjax(pointService.removeByIds(idList));
    }
}
