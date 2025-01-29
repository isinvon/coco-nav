package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.project.admin.domain.FeedbackLog;
import com.ruoyi.project.admin.service.IFeedbackLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 反馈处理日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/feedbackLog")
public class FeedbackLogController extends BaseController
{
    @Autowired
    private IFeedbackLogCustomService feedbackLogCustomService;

    /**
     * 查询反馈处理日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(FeedbackLog feedbackLog)
    {
        startPage();
        List<FeedbackLog> list = feedbackLogCustomService.selectFeedbackLogList(feedbackLog);
        return getDataTable(list);
    }

    /**
     * 导出反馈处理日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:export')")
    @Log(title = "反馈处理日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FeedbackLog feedbackLog)
    {
        List<FeedbackLog> list = feedbackLogCustomService.selectFeedbackLogList(feedbackLog);
        ExcelUtil<FeedbackLog> util = new ExcelUtil<FeedbackLog>(FeedbackLog.class);
        util.exportExcel(response, list, "反馈处理日志数据");
    }

    /**
     * 获取反馈处理日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:query')")
    @GetMapping(value = "/{feedbackLogId}")
    public AjaxResult getInfo(@PathVariable("feedbackLogId") Long feedbackLogId)
    {
        return success(feedbackLogCustomService.selectFeedbackLogByFeedbackLogId(feedbackLogId));
    }

    /**
     * 新增反馈处理日志
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:add')")
    @Log(title = "反馈处理日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeedbackLog feedbackLog)
    {
        return toAjax(feedbackLogCustomService.insertFeedbackLog(feedbackLog));
    }

    /**
     * 修改反馈处理日志
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:edit')")
    @Log(title = "反馈处理日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeedbackLog feedbackLog)
    {
        return toAjax(feedbackLogCustomService.updateFeedbackLog(feedbackLog));
    }

    /**
     * 删除反馈处理日志
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackLog:remove')")
    @Log(title = "反馈处理日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackLogIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackLogIds)
    {
        return toAjax(feedbackLogCustomService.deleteFeedbackLogByFeedbackLogIds(feedbackLogIds));
    }
}
