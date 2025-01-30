package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.framework.security.permission.CustomPermission;
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
import com.ruoyi.project.admin.domain.WebsiteInfo;
import com.ruoyi.project.admin.service.IWebsiteInfoCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 网站信息Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/websiteInfo")
public class WebsiteInfoController extends BaseController
{
    @Autowired
    private IWebsiteInfoCustomService websiteInfoCustomService;

    /**
     * 查询网站信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LIST)
    @GetMapping("/list")
    public TableDataInfo list(WebsiteInfo websiteInfo)
    {
        startPage();
        List<WebsiteInfo> list = websiteInfoCustomService.selectWebsiteInfoList(websiteInfo);
        return getDataTable(list);
    }

    /**
     * 导出网站信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_EXPORT)
    @Log(title = "网站信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WebsiteInfo websiteInfo)
    {
        List<WebsiteInfo> list = websiteInfoCustomService.selectWebsiteInfoList(websiteInfo);
        ExcelUtil<WebsiteInfo> util = new ExcelUtil<WebsiteInfo>(WebsiteInfo.class);
        util.exportExcel(response, list, "网站信息数据");
    }

    /**
     * 获取网站信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_QUERY)
    @GetMapping(value = "/{websiteInfoId}")
    public AjaxResult getInfo(@PathVariable("websiteInfoId") Long websiteInfoId)
    {
        return success(websiteInfoCustomService.selectWebsiteInfoByWebsiteInfoId(websiteInfoId));
    }

    /**
     * 新增网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_ADD)
    @Log(title = "网站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebsiteInfo websiteInfo)
    {
        return toAjax(websiteInfoCustomService.insertWebsiteInfo(websiteInfo));
    }

    /**
     * 修改网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_EDIT)
    @Log(title = "网站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebsiteInfo websiteInfo)
    {
        return toAjax(websiteInfoCustomService.updateWebsiteInfo(websiteInfo));
    }

    /**
     * 删除网站信息
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_REMOVE)
    @Log(title = "网站信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{websiteInfoIds}")
    public AjaxResult remove(@PathVariable Long[] websiteInfoIds)
    {
        return toAjax(websiteInfoCustomService.deleteWebsiteInfoByWebsiteInfoIds(websiteInfoIds));
    }
}
