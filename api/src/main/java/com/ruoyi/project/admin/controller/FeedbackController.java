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
import com.ruoyi.project.admin.domain.Feedback;
import com.ruoyi.project.admin.service.custom.FeedbackCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户反馈Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private FeedbackCustomService feedbackCustomService;

    /**
     * 查询用户反馈列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Feedback feedback) {
        startPage();
        List<Feedback> list = feedbackCustomService.selectFeedbackList(feedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_EXPORT)
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Feedback feedback) {
        List<Feedback> list = feedbackCustomService.selectFeedbackList(feedback);
        ExcelUtil<Feedback> util = new ExcelUtil<Feedback>(Feedback.class);
        util.exportExcel(response, list, "用户反馈数据");
    }

    /**
     * 获取用户反馈详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_QUERY)
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId) {
        return success(feedbackCustomService.selectFeedbackByFeedbackId(feedbackId));
    }

    /**
     * 新增用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_ADD)
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Feedback feedback) {
        return toAjax(feedbackCustomService.insertFeedback(feedback));
    }

    /**
     * 修改用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_EDIT)
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Feedback feedback) {
        return toAjax(feedbackCustomService.updateFeedback(feedback));
    }

    /**
     * 删除用户反馈
     */
    @CustomPermission(PermissionConstants.ADMIN_FEEDBACK_REMOVE)
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds) {
        return toAjax(feedbackCustomService.deleteFeedbackByFeedbackIds(feedbackIds));
    }
}
