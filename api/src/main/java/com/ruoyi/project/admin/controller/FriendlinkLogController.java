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
import com.ruoyi.project.admin.domain.FriendlinkLog;
import com.ruoyi.project.admin.service.FriendlinkLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 友链操作日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/friendlinkLog")
public class FriendlinkLogController extends BaseController {
    @Resource
    private FriendlinkLogService friendlinkLogService;

    /**
     * 查询友链操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<FriendlinkLog> list = friendlinkLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出友链操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_EXPORT)
    @Log(title = "友链操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<FriendlinkLog> list = friendlinkLogService.list();
        ExcelUtil<FriendlinkLog> util = new ExcelUtil<>(FriendlinkLog.class);
        util.exportExcel(response, list, "友链操作日志数据");
    }

    /**
     * 获取友链操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_QUERY)
    @GetMapping(value = "/{friendlinkLogId}")
    public AjaxResult getInfo(@PathVariable("friendlinkLogId") Long friendlinkLogId) {
        return success(friendlinkLogService.getById(friendlinkLogId));
    }

    /**
     * 新增友链操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_ADD)
    @Log(title = "友链操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FriendlinkLog friendlinkLog) {
        return toAjax(friendlinkLogService.save(friendlinkLog));
    }

    /**
     * 修改友链操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_EDIT)
    @Log(title = "友链操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FriendlinkLog friendlinkLog) {
        LambdaUpdateWrapper<FriendlinkLog> uw = new LambdaUpdateWrapper<>();
        uw.eq(FriendlinkLog::getFriendlinkLogId, friendlinkLog.getFriendlinkLogId());
        return toAjax(friendlinkLogService.update(friendlinkLog, uw));
    }

    /**
     * 删除友链操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LOG_REMOVE)
    @Log(title = "友链操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{friendlinkLogIds}")
    public AjaxResult remove(@PathVariable Long[] friendlinkLogIds) {
        List<Long> idList = Arrays.asList(friendlinkLogIds);
        return toAjax(friendlinkLogService.removeByIds(idList));
    }
}
