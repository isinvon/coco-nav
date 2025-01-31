package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.service.AdvertisementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 广告管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/advertisement")
public class AdvertisementController extends BaseController {

    @Resource
    private AdvertisementService advertisementService;

    /**
     * 查询广告管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Advertisement> list = advertisementService.list();
        return getDataTable(list);
    }

    /**
     * 导出广告管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_EXPORT)
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Advertisement> list = advertisementService.list();
        ExcelUtil<Advertisement> util = new ExcelUtil<Advertisement>(Advertisement.class);
        util.exportExcel(response, list, "广告管理数据");
    }

    /**
     * 获取广告管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_QUERY)
    @GetMapping(value = "/{advertisementId}")
    public AjaxResult getInfo(@PathVariable("advertisementId") Long advertisementId) {
        return success(advertisementService.getById(advertisementId));
    }

    /**
     * 新增广告管理
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_ADD)
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Advertisement advertisement) {
        return toAjax(advertisementService.save(advertisement));
    }

    /**
     * 修改广告管理
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_EDIT)
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Advertisement advertisement) {
        LambdaQueryWrapper<Advertisement> qw = new LambdaQueryWrapper<>();
        qw.eq(Advertisement::getAdvertisementId, advertisement.getAdvertisementId());
        return toAjax(advertisementService.update(advertisement, qw));
    }

    /**
     * 删除广告管理
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_REMOVE)
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{advertisementIds}")
    public AjaxResult remove(@PathVariable Long[] advertisementIds) {
        List<Long> idList = Arrays.asList(advertisementIds);
        return toAjax(advertisementService.removeByIds(idList));
    }
}
