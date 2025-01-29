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
import com.ruoyi.project.admin.domain.FeedbackType;
import com.ruoyi.project.admin.service.IFeedbackTypeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 反馈类型Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/feedbackType")
public class FeedbackTypeController extends BaseController
{
    @Autowired
    private IFeedbackTypeCustomService feedbackTypeCustomService;

    /**
     * 查询反馈类型列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:list')")
    @GetMapping("/list")
    public TableDataInfo list(FeedbackType feedbackType)
    {
        startPage();
        List<FeedbackType> list = feedbackTypeCustomService.selectFeedbackTypeList(feedbackType);
        return getDataTable(list);
    }

    /**
     * 导出反馈类型列表
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:export')")
    @Log(title = "反馈类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FeedbackType feedbackType)
    {
        List<FeedbackType> list = feedbackTypeCustomService.selectFeedbackTypeList(feedbackType);
        ExcelUtil<FeedbackType> util = new ExcelUtil<FeedbackType>(FeedbackType.class);
        util.exportExcel(response, list, "反馈类型数据");
    }

    /**
     * 获取反馈类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:query')")
    @GetMapping(value = "/{feedbackTypeId}")
    public AjaxResult getInfo(@PathVariable("feedbackTypeId") Long feedbackTypeId)
    {
        return success(feedbackTypeCustomService.selectFeedbackTypeByFeedbackTypeId(feedbackTypeId));
    }

    /**
     * 新增反馈类型
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:add')")
    @Log(title = "反馈类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FeedbackType feedbackType)
    {
        return toAjax(feedbackTypeCustomService.insertFeedbackType(feedbackType));
    }

    /**
     * 修改反馈类型
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:edit')")
    @Log(title = "反馈类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FeedbackType feedbackType)
    {
        return toAjax(feedbackTypeCustomService.updateFeedbackType(feedbackType));
    }

    /**
     * 删除反馈类型
     */
    @PreAuthorize("@ss.hasPermi('admin:feedbackType:remove')")
    @Log(title = "反馈类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackTypeIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackTypeIds)
    {
        return toAjax(feedbackTypeCustomService.deleteFeedbackTypeByFeedbackTypeIds(feedbackTypeIds));
    }
}
