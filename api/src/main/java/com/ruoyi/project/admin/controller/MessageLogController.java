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
import com.ruoyi.project.admin.domain.MessageLog;
import com.ruoyi.project.admin.service.IMessageLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 留言操作日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/messageLog")
public class MessageLogController extends BaseController
{
    @Autowired
    private IMessageLogCustomService messageLogCustomService;

    /**
     * 查询留言操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(MessageLog messageLog)
    {
        startPage();
        List<MessageLog> list = messageLogCustomService.selectMessageLogList(messageLog);
        return getDataTable(list);
    }

    /**
     * 导出留言操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:export')")
    @Log(title = "留言操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MessageLog messageLog)
    {
        List<MessageLog> list = messageLogCustomService.selectMessageLogList(messageLog);
        ExcelUtil<MessageLog> util = new ExcelUtil<MessageLog>(MessageLog.class);
        util.exportExcel(response, list, "留言操作日志数据");
    }

    /**
     * 获取留言操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:query')")
    @GetMapping(value = "/{messageLogId}")
    public AjaxResult getInfo(@PathVariable("messageLogId") Long messageLogId)
    {
        return success(messageLogCustomService.selectMessageLogByMessageLogId(messageLogId));
    }

    /**
     * 新增留言操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:add')")
    @Log(title = "留言操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MessageLog messageLog)
    {
        return toAjax(messageLogCustomService.insertMessageLog(messageLog));
    }

    /**
     * 修改留言操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:edit')")
    @Log(title = "留言操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MessageLog messageLog)
    {
        return toAjax(messageLogCustomService.updateMessageLog(messageLog));
    }

    /**
     * 删除留言操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:messageLog:remove')")
    @Log(title = "留言操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageLogIds}")
    public AjaxResult remove(@PathVariable Long[] messageLogIds)
    {
        return toAjax(messageLogCustomService.deleteMessageLogByMessageLogIds(messageLogIds));
    }
}
