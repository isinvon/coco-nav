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
import com.ruoyi.project.admin.domain.BrowseHistory;
import com.ruoyi.project.admin.service.IBrowseHistoryCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

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
    private IBrowseHistoryCustomService browseHistoryCustomService;

    /**
     * 查询用户浏览历史列表
     */
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:list')")
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
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:export')")
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
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:query')")
    @GetMapping(value = "/{browseHistoryId}")
    public AjaxResult getInfo(@PathVariable("browseHistoryId") Long browseHistoryId)
    {
        return success(browseHistoryCustomService.selectBrowseHistoryByBrowseHistoryId(browseHistoryId));
    }

    /**
     * 新增用户浏览历史
     */
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:add')")
    @Log(title = "用户浏览历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrowseHistory browseHistory)
    {
        return toAjax(browseHistoryCustomService.insertBrowseHistory(browseHistory));
    }

    /**
     * 修改用户浏览历史
     */
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:edit')")
    @Log(title = "用户浏览历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrowseHistory browseHistory)
    {
        return toAjax(browseHistoryCustomService.updateBrowseHistory(browseHistory));
    }

    /**
     * 删除用户浏览历史
     */
    @PreAuthorize("@ss.hasPermi('admin:browseHistory:remove')")
    @Log(title = "用户浏览历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{browseHistoryIds}")
    public AjaxResult remove(@PathVariable Long[] browseHistoryIds)
    {
        return toAjax(browseHistoryCustomService.deleteBrowseHistoryByBrowseHistoryIds(browseHistoryIds));
    }
}
