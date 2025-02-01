package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.Feedback;
import com.ruoyi.project.admin.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 用户反馈Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/feedback")
public class FeedbackController extends BaseController {
    @Resource
    private FeedbackService feedbackService;

    /**
     * 查询用户反馈列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Feedback> list = feedbackService.list();
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_EXPORT)
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Feedback> list = feedbackService.list();
        ExcelUtil<Feedback> util = new ExcelUtil<>(Feedback.class);
        util.exportExcel(response, list, "用户反馈数据");
    }

    /**
     * 获取用户反馈详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_QUERY)
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId) {
        return success(feedbackService.getById(feedbackId));
    }

    /**
     * 新增用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_ADD)
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Feedback feedback) {
        return toAjax(feedbackService.save(feedback));
    }

    /**
     * 修改用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_EDIT)
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Feedback feedback) {
        return toAjax(feedbackService.updateById(feedback));
    }

    /**
     * 删除用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_REMOVE)
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds) {
        List<Long> idList = Arrays.asList(feedbackIds);
        return toAjax(feedbackService.removeByIds(idList));
    }
}
