package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.PointLog;
import com.ruoyi.project.admin.service.PointLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 积分流水Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/pointLog")
public class PointLogController extends BaseController {
    @Resource
    private PointLogService pointLogService;

    /**
     * 查询积分流水列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<PointLog> list = pointLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出积分流水列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_EXPORT)
    @Log(title = "积分流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<PointLog> list = pointLogService.list();
        ExcelUtil<PointLog> util = new ExcelUtil<>(PointLog.class);
        util.exportExcel(response, list, "积分流水数据");
    }

    /**
     * 获取积分流水详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_QUERY)
    @GetMapping(value = "/{pointLogId}")
    public AjaxResult getInfo(@PathVariable("pointLogId") Long pointLogId) {
        return success(pointLogService.getById(pointLogId));
    }

    /**
     * 新增积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_ADD)
    @Log(title = "积分流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointLog pointLog) {
        return toAjax(pointLogService.save(pointLog));
    }

    /**
     * 修改积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_EDIT)
    @Log(title = "积分流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointLog pointLog) {
        LambdaQueryWrapper<PointLog> qw = new LambdaQueryWrapper<>();
        qw.eq(PointLog::getPointLogId, pointLog.getPointLogId());
        return toAjax(pointLogService.update(pointLog, qw));
    }

    /**
     * 删除积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_REMOVE)
    @Log(title = "积分流水", businessType = BusinessType.DELETE)
    @DeleteMapping("/{pointLogIds}")
    public AjaxResult remove(@PathVariable Long[] pointLogIds) {
        List<Long> idList = Arrays.asList(pointLogIds);
        return toAjax(pointLogService.removeByIds(idList));
    }
}
