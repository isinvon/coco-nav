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
import com.ruoyi.project.admin.domain.VipLog;
import com.ruoyi.project.admin.service.VipLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * VIP操作日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vipLog")
public class VipLogController extends BaseController
{
    @Autowired
    private VipLogCustomService vipLogCustomService;

    /**
     * 查询VIP操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(VipLog vipLog)
    {
        startPage();
        List<VipLog> list = vipLogCustomService.selectVipLogList(vipLog);
        return getDataTable(list);
    }

    /**
     * 导出VIP操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_EXPORT)
    @Log(title = "VIP操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipLog vipLog)
    {
        List<VipLog> list = vipLogCustomService.selectVipLogList(vipLog);
        ExcelUtil<VipLog> util = new ExcelUtil<VipLog>(VipLog.class);
        util.exportExcel(response, list, "VIP操作日志数据");
    }

    /**
     * 获取VIP操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_QUERY)
    @GetMapping(value = "/{vipLogId}")
    public AjaxResult getInfo(@PathVariable("vipLogId") Long vipLogId)
    {
        return success(vipLogCustomService.selectVipLogByVipLogId(vipLogId));
    }

    /**
     * 新增VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_ADD)
    @Log(title = "VIP操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipLog vipLog)
    {
        return toAjax(vipLogCustomService.insertVipLog(vipLog));
    }

    /**
     * 修改VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_EDIT)
    @Log(title = "VIP操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipLog vipLog)
    {
        return toAjax(vipLogCustomService.updateVipLog(vipLog));
    }

    /**
     * 删除VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_REMOVE)
    @Log(title = "VIP操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vipLogIds}")
    public AjaxResult remove(@PathVariable Long[] vipLogIds)
    {
        return toAjax(vipLogCustomService.deleteVipLogByVipLogIds(vipLogIds));
    }
}
