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
import com.ruoyi.project.admin.domain.FriendlinkLog;
import com.ruoyi.project.admin.service.IFriendlinkLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 友链操作日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/friendlinkLog")
public class FriendlinkLogController extends BaseController
{
    @Autowired
    private IFriendlinkLogCustomService friendlinkLogCustomService;

    /**
     * 查询友链操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(FriendlinkLog friendlinkLog)
    {
        startPage();
        List<FriendlinkLog> list = friendlinkLogCustomService.selectFriendlinkLogList(friendlinkLog);
        return getDataTable(list);
    }

    /**
     * 导出友链操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:export')")
    @Log(title = "友链操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FriendlinkLog friendlinkLog)
    {
        List<FriendlinkLog> list = friendlinkLogCustomService.selectFriendlinkLogList(friendlinkLog);
        ExcelUtil<FriendlinkLog> util = new ExcelUtil<FriendlinkLog>(FriendlinkLog.class);
        util.exportExcel(response, list, "友链操作日志数据");
    }

    /**
     * 获取友链操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:query')")
    @GetMapping(value = "/{friendlinkLogId}")
    public AjaxResult getInfo(@PathVariable("friendlinkLogId") Long friendlinkLogId)
    {
        return success(friendlinkLogCustomService.selectFriendlinkLogByFriendlinkLogId(friendlinkLogId));
    }

    /**
     * 新增友链操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:add')")
    @Log(title = "友链操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FriendlinkLog friendlinkLog)
    {
        return toAjax(friendlinkLogCustomService.insertFriendlinkLog(friendlinkLog));
    }

    /**
     * 修改友链操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:edit')")
    @Log(title = "友链操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FriendlinkLog friendlinkLog)
    {
        return toAjax(friendlinkLogCustomService.updateFriendlinkLog(friendlinkLog));
    }

    /**
     * 删除友链操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlinkLog:remove')")
    @Log(title = "友链操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{friendlinkLogIds}")
    public AjaxResult remove(@PathVariable Long[] friendlinkLogIds)
    {
        return toAjax(friendlinkLogCustomService.deleteFriendlinkLogByFriendlinkLogIds(friendlinkLogIds));
    }
}
