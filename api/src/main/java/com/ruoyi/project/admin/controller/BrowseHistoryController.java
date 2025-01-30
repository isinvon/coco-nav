package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.BrowseHistory;
import com.ruoyi.project.admin.service.custom.BrowseHistoryCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户浏览历史Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/browseHistory")
public class BrowseHistoryController extends BaseController
{
    @Autowired
    private BrowseHistoryCustomService browseHistoryCustomService;

    /**
     * 查询用户浏览历史列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_LIST)
    @GetMapping("/list")
    public TableDataInfo list(BrowseHistory browseHistory)
    {
        startPage();
        List<BrowseHistory> list = browseHistoryCustomService.selectBrowseHistoryList(browseHistory);
        return getDataTable(list);
    }

    /**
     * 导出用户浏览历史列表
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_EXPORT)
    @Log(title = "用户浏览历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BrowseHistory browseHistory)
    {
        List<BrowseHistory> list = browseHistoryCustomService.selectBrowseHistoryList(browseHistory);
        ExcelUtil<BrowseHistory> util = new ExcelUtil<BrowseHistory>(BrowseHistory.class);
        util.exportExcel(response, list, "用户浏览历史数据");
    }

    /**
     * 获取用户浏览历史详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_QUERY)
    @GetMapping(value = "/{browseHistoryId}")
    public AjaxResult getInfo(@PathVariable("browseHistoryId") Long browseHistoryId)
    {
        return success(browseHistoryCustomService.selectBrowseHistoryByBrowseHistoryId(browseHistoryId));
    }

    /**
     * 新增用户浏览历史
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_ADD)
    @Log(title = "用户浏览历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrowseHistory browseHistory)
    {
        return toAjax(browseHistoryCustomService.insertBrowseHistory(browseHistory));
    }

    /**
     * 修改用户浏览历史
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_EDIT)
    @Log(title = "用户浏览历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrowseHistory browseHistory)
    {
        return toAjax(browseHistoryCustomService.updateBrowseHistory(browseHistory));
    }

    /**
     * 删除用户浏览历史
     */
    @CustomPermission(PermissionConstants.ADMIN_BROWSE_HISTORY_REMOVE)
    @Log(title = "用户浏览历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{browseHistoryIds}")
    public AjaxResult remove(@PathVariable Long[] browseHistoryIds)
    {
        return toAjax(browseHistoryCustomService.deleteBrowseHistoryByBrowseHistoryIds(browseHistoryIds));
    }
}
