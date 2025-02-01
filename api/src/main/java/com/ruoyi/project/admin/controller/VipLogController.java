package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.VipLog;
import com.ruoyi.project.admin.service.VipLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * VIP操作日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vipLog")
public class VipLogController extends BaseController {
    @Resource
    private VipLogService vipLogService;

    /**
     * 查询VIP操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<VipLog> list = vipLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出VIP操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_EXPORT)
    @Log(title = "VIP操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<VipLog> list = vipLogService.list();
        ExcelUtil<VipLog> util = new ExcelUtil<>(VipLog.class);
        util.exportExcel(response, list, "VIP操作日志数据");
    }

    /**
     * 获取VIP操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_QUERY)
    @GetMapping(value = "/{vipLogId}")
    public AjaxResult getInfo(@PathVariable("vipLogId") Long vipLogId) {
        return success(vipLogService.getById(vipLogId));
    }

    /**
     * 新增VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_ADD)
    @Log(title = "VIP操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipLog vipLog) {
        return toAjax(vipLogService.save(vipLog));
    }

    /**
     * 修改VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_EDIT)
    @Log(title = "VIP操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipLog vipLog) {
        LambdaUpdateWrapper<VipLog> uw = new LambdaUpdateWrapper<>();
        uw.eq(VipLog::getVipLogId, vipLog.getVipLogId());
        return toAjax(vipLogService.update(vipLog, uw));
    }

    /**
     * 删除VIP操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LOG_REMOVE)
    @Log(title = "VIP操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vipLogIds}")
    public AjaxResult remove(@PathVariable Long[] vipLogIds) {
        List<Long> idList = Arrays.asList(vipLogIds);
        return toAjax(vipLogService.removeByIds(idList));
    }
}
