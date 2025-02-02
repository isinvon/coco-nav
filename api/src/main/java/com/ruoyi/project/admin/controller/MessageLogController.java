package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.MessageLog;
import com.ruoyi.project.admin.service.MessageLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 留言操作日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/messageLog")
public class MessageLogController extends BaseController {
    @Resource
    private MessageLogService messageLogService;

    /**
     * 查询留言操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<MessageLog> list = messageLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出留言操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_EXPORT)
    @Log(title = "留言操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<MessageLog> list = messageLogService.list();
        ExcelUtil<MessageLog> util = new ExcelUtil<>(MessageLog.class);
        util.exportExcel(response, list, "留言操作日志数据");
    }

    /**
     * 获取留言操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_QUERY)
    @GetMapping(value = "/{messageLogId}")
    public AjaxResult getInfo(@PathVariable("messageLogId") Long messageLogId) {
        return success(messageLogService.getById(messageLogId));
    }

    /**
     * 新增留言操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_ADD)
    @Log(title = "留言操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MessageLog messageLog) {
        return toAjax(messageLogService.save(messageLog));
    }

    /**
     * 修改留言操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_EDIT)
    @Log(title = "留言操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MessageLog messageLog) {
        return toAjax(messageLogService.updateById(messageLog));
    }

    /**
     * 删除留言操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LOG_REMOVE)
    @Log(title = "留言操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageLogIds}")
    public AjaxResult remove(@PathVariable Long[] messageLogIds) {
        List<Long> idList = Arrays.asList(messageLogIds);
        return toAjax(messageLogService.removeByIds(idList));
    }
}
