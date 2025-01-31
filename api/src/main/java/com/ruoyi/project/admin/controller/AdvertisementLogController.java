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
import com.ruoyi.project.admin.domain.AdvertisementLog;
import com.ruoyi.project.admin.service.AdvertisementLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 广告操作日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/advertisementLog")
public class AdvertisementLogController extends BaseController {
    @Resource
    private AdvertisementLogService advertisementLogService;

    /**
     * 查询广告操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<AdvertisementLog> list = advertisementLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出广告操作日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_EXPORT)
    @Log(title = "广告操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<AdvertisementLog> list = advertisementLogService.list();
        ExcelUtil<AdvertisementLog> util = new ExcelUtil<AdvertisementLog>(AdvertisementLog.class);
        util.exportExcel(response, list, "广告操作日志数据");
    }

    /**
     * 获取广告操作日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_QUERY)
    @GetMapping(value = "/{advertisementLogId}")
    public AjaxResult getInfo(@PathVariable("advertisementLogId") Long advertisementLogId) {
        return success(advertisementLogService.getById(advertisementLogId));
    }

    /**
     * 新增广告操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_ADD)
    @Log(title = "广告操作日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdvertisementLog advertisementLog) {
        return toAjax(advertisementLogService.save(advertisementLog));
    }

    /**
     * 修改广告操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_EDIT)
    @Log(title = "广告操作日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdvertisementLog advertisementLog) {
        LambdaQueryWrapper<AdvertisementLog> qw = new LambdaQueryWrapper<>();
        qw.eq(AdvertisementLog::getAdvertisementLogId, advertisementLog.getAdvertisementLogId());
        return toAjax(advertisementLogService.update(advertisementLog, qw));
    }

    /**
     * 删除广告操作日志
     */
    @CustomPermission(PermissionConstants.ADMIN_ADVERTISEMENT_LOG_REMOVE)
    @Log(title = "广告操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{advertisementLogIds}")
    public AjaxResult remove(@PathVariable Long[] advertisementLogIds) {
        List<Long> idList = Arrays.asList(advertisementLogIds);
        return toAjax(advertisementLogService.removeByIds(idList));
    }
}
