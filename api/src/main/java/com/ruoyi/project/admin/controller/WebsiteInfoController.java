package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.WebsiteInfo;
import com.ruoyi.project.admin.service.WebsiteInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 网站信息Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/websiteInfo")
public class WebsiteInfoController extends BaseController {
    @Resource
    private WebsiteInfoService websiteInfoService;

    /**
     * 查询网站信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<WebsiteInfo> list = websiteInfoService.list();
        return getDataTable(list);
    }

    /**
     * 导出网站信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_EXPORT)
    @Log(title = "网站信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<WebsiteInfo> list = websiteInfoService.list();
        ExcelUtil<WebsiteInfo> util = new ExcelUtil<>(WebsiteInfo.class);
        util.exportExcel(response, list, "网站信息数据");
    }

    /**
     * 获取网站信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_QUERY)
    @GetMapping(value = "/{websiteInfoId}")
    public AjaxResult getInfo(@PathVariable("websiteInfoId") Long websiteInfoId) {
        return success(websiteInfoService.getById(websiteInfoId));
    }

    /**
     * 新增网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_ADD)
    @Log(title = "网站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebsiteInfo websiteInfo) {
        return toAjax(websiteInfoService.save(websiteInfo));
    }

    /**
     * 修改网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_EDIT)
    @Log(title = "网站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebsiteInfo websiteInfo) {
        LambdaUpdateWrapper<WebsiteInfo> uw = new LambdaUpdateWrapper<>();
        return toAjax(websiteInfoService.update(websiteInfo, uw));
    }

    /**
     * 删除网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_REMOVE)
    @Log(title = "网站信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{websiteInfoIds}")
    public AjaxResult remove(@PathVariable Long[] websiteInfoIds) {
        List<Long> idList = Arrays.asList(websiteInfoIds);
        return toAjax(websiteInfoService.removeByIds(idList));
    }
}
