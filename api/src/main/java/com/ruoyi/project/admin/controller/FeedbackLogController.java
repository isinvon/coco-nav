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
import com.ruoyi.project.admin.domain.FeedbackLog;
import com.ruoyi.project.admin.service.FeedbackLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 反馈处理日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/feedbackLog")
public class FeedbackLogController extends BaseController {
    @Resource
    private FeedbackLogService feedbackLogService;

    /**
     * 查询反馈处理日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<FeedbackLog> list = feedbackLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出反馈处理日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_EXPORT)
    @Log(title = "反馈处理日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<FeedbackLog> list = feedbackLogService.list();
        ExcelUtil<FeedbackLog> util = new ExcelUtil<>(FeedbackLog.class);
        util.exportExcel(response, list, "反馈处理日志数据");
    }

    /**
     * 获取反馈处理日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_QUERY)
    @GetMapping(value = "/{feedbackLogId}")
    public AjaxResult getInfo(@PathVariable("feedbackLogId") Long feedbackLogId) {
        return success(feedbackLogService.getById(feedbackLogId));
    }

    /**
     * 新增反馈处理日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_ADD)
    @Log(title = "反馈处理日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeedbackLog feedbackLog) {
        return toAjax(feedbackLogService.save(feedbackLog));
    }

    /**
     * 修改反馈处理日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_EDIT)
    @Log(title = "反馈处理日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeedbackLog feedbackLog) {
        LambdaUpdateWrapper<FeedbackLog> uw = new LambdaUpdateWrapper<>();
        uw.eq(FeedbackLog::getFeedbackLogId, feedbackLog.getFeedbackLogId());
        return toAjax(feedbackLogService.update(feedbackLog, uw));
    }

    /**
     * 删除反馈处理日志
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LOG_REMOVE)
    @Log(title = "反馈处理日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{feedbackLogIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackLogIds) {
        List<Long> idList = Arrays.asList(feedbackLogIds);
        return toAjax(feedbackLogService.removeByIds(idList));
    }
}
