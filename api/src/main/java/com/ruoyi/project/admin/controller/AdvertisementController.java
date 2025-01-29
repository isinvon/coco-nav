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
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.service.IAdvertisementCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 广告管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/advertisement")
public class AdvertisementController extends BaseController
{
    @Autowired
    private IAdvertisementCustomService advertisementCustomService;

    /**
     * 查询广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:list')")
    @GetMapping("/list")
    public TableDataInfo list(Advertisement advertisement)
    {
        startPage();
        List<Advertisement> list = advertisementCustomService.selectAdvertisementList(advertisement);
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:export')")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Advertisement advertisement)
    {
        List<Advertisement> list = advertisementCustomService.selectAdvertisementList(advertisement);
        ExcelUtil<Advertisement> util = new ExcelUtil<Advertisement>(Advertisement.class);
        util.exportExcel(response, list, "广告管理数据");
    }

    /**
     * 获取广告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:query')")
    @GetMapping(value = "/{advertisementId}")
    public AjaxResult getInfo(@PathVariable("advertisementId") Long advertisementId)
    {
        return success(advertisementCustomService.selectAdvertisementByAdvertisementId(advertisementId));
    }

    /**
     * 新增广告管理
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:add')")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Advertisement advertisement)
    {
        return toAjax(advertisementCustomService.insertAdvertisement(advertisement));
    }

    /**
     * 修改广告管理
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:edit')")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Advertisement advertisement)
    {
        return toAjax(advertisementCustomService.updateAdvertisement(advertisement));
    }

    /**
     * 删除广告管理
     */
    @PreAuthorize("@ss.hasPermi('admin:advertisement:remove')")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{advertisementIds}")
    public AjaxResult remove(@PathVariable Long[] advertisementIds)
    {
        return toAjax(advertisementCustomService.deleteAdvertisementByAdvertisementIds(advertisementIds));
    }
}
