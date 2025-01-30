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
import com.ruoyi.project.admin.domain.PointLog;
import com.ruoyi.project.admin.service.PointLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 积分流水Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/pointLog")
public class PointLogController extends BaseController
{
    @Autowired
    private PointLogCustomService pointLogCustomService;

    /**
     * 查询积分流水列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(PointLog pointLog)
    {
        startPage();
        List<PointLog> list = pointLogCustomService.selectPointLogList(pointLog);
        return getDataTable(list);
    }

    /**
     * 导出积分流水列表
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_EXPORT)
    @Log(title = "积分流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointLog pointLog)
    {
        List<PointLog> list = pointLogCustomService.selectPointLogList(pointLog);
        ExcelUtil<PointLog> util = new ExcelUtil<PointLog>(PointLog.class);
        util.exportExcel(response, list, "积分流水数据");
    }

    /**
     * 获取积分流水详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_QUERY)
    @GetMapping(value = "/{pointLogId}")
    public AjaxResult getInfo(@PathVariable("pointLogId") Long pointLogId)
    {
        return success(pointLogCustomService.selectPointLogByPointLogId(pointLogId));
    }

    /**
     * 新增积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_ADD)
    @Log(title = "积分流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointLog pointLog)
    {
        return toAjax(pointLogCustomService.insertPointLog(pointLog));
    }

    /**
     * 修改积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_EDIT)
    @Log(title = "积分流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointLog pointLog)
    {
        return toAjax(pointLogCustomService.updatePointLog(pointLog));
    }

    /**
     * 删除积分流水
     */
    @CustomPermission(PermissionConstants.ADMIN_POINT_LOG_REMOVE)
    @Log(title = "积分流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pointLogIds}")
    public AjaxResult remove(@PathVariable Long[] pointLogIds)
    {
        return toAjax(pointLogCustomService.deletePointLogByPointLogIds(pointLogIds));
    }
}
