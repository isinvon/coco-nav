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
import com.ruoyi.project.admin.domain.AdvertisementLog;
import com.ruoyi.project.admin.service.IAdvertisementLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 广告操作日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/advertisementLog")
public class AdvertisementLogController extends BaseController
{
    @Autowired
    private IAdvertisementLogCustomService advertisementLogCustomService;

    /**
     * 查询广告操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdvertisementLog advertisementLog)
    {
        startPage();
        List<AdvertisementLog> list = advertisementLogCustomService.selectAdvertisementLogList(advertisementLog);
        return getDataTable(list);
    }

    /**
     * 导出广告操作日志列表
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:export')")
    @Log(title = "广告操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdvertisementLog advertisementLog)
    {
        List<AdvertisementLog> list = advertisementLogCustomService.selectAdvertisementLogList(advertisementLog);
        ExcelUtil<AdvertisementLog> util = new ExcelUtil<AdvertisementLog>(AdvertisementLog.class);
        util.exportExcel(response, list, "广告操作日志数据");
    }

    /**
     * 获取广告操作日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:query')")
    @GetMapping(value = "/{advertisementLogId}")
    public AjaxResult getInfo(@PathVariable("advertisementLogId") Long advertisementLogId)
    {
        return success(advertisementLogCustomService.selectAdvertisementLogByAdvertisementLogId(advertisementLogId));
    }

    /**
     * 新增广告操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:add')")
    @Log(title = "广告操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdvertisementLog advertisementLog)
    {
        return toAjax(advertisementLogCustomService.insertAdvertisementLog(advertisementLog));
    }

    /**
     * 修改广告操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:edit')")
    @Log(title = "广告操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdvertisementLog advertisementLog)
    {
        return toAjax(advertisementLogCustomService.updateAdvertisementLog(advertisementLog));
    }

    /**
     * 删除广告操作日志
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisementLog:remove')")
    @Log(title = "广告操作日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{advertisementLogIds}")
    public AjaxResult remove(@PathVariable Long[] advertisementLogIds)
    {
        return toAjax(advertisementLogCustomService.deleteAdvertisementLogByAdvertisementLogIds(advertisementLogIds));
    }
}
