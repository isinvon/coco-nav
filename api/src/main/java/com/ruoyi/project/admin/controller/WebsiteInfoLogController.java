package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.WebsiteInfoLog;
import com.ruoyi.common.model.service.WebsiteInfoLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 网站信息日志Controller
 * <p>
 * 用于记录网站信息操作的日志，如新增、修改、删除等操作记录
 *
 * @author sinvon
 * @since 2025年2月9日13:39:35
 */
@RestController
@RequestMapping("/admin/websiteInfoLog")
public class WebsiteInfoLogController extends BaseController {

    @Resource
    private WebsiteInfoLogService websiteInfoLogService;

    /**
     * 查询网站信息日志列表
     *
     * @return 表格数据
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<WebsiteInfoLog> list = websiteInfoLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出网站信息日志列表
     *
     * @param response HttpServletResponse对象
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_EXPORT)
    @Log(title = "网站信息日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<WebsiteInfoLog> list = websiteInfoLogService.list();
        ExcelUtil<WebsiteInfoLog> util = new ExcelUtil<>(WebsiteInfoLog.class);
        util.exportExcel(response, list, "网站信息日志数据");
    }

    /**
     * 获取网站信息日志详细信息
     *
     * @param logId 网站信息日志ID
     * @return AjaxResult对象
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_QUERY)
    @GetMapping("/{logId}")
    public AjaxResult getInfo(@PathVariable("logId") Long logId) {
        return success(websiteInfoLogService.getById(logId));
    }

    /**
     * 新增网站信息日志
     *
     * @param websiteInfoLog 网站信息日志对象
     * @return AjaxResult对象
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_ADD)
    @Log(title = "网站信息日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebsiteInfoLog websiteInfoLog) {
        return toAjax(websiteInfoLogService.save(websiteInfoLog));
    }

    /**
     * 修改网站信息日志
     *
     * @param websiteInfoLog 网站信息日志对象
     * @return AjaxResult对象
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_EDIT)
    @Log(title = "网站信息日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebsiteInfoLog websiteInfoLog) {
        return toAjax(websiteInfoLogService.updateById(websiteInfoLog));
    }

    /**
     * 删除网站信息日志
     *
     * @param websiteInfoLogIds 网站信息日志ID数组
     * @return AjaxResult对象
     */
    @CustomPermission(PermissionConstants.ADMIN_WEBSITE_INFO_LOG_REMOVE)
    @Log(title = "网站信息日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{websiteInfoLogIds}")
    public AjaxResult remove(@PathVariable Long[] websiteInfoLogIds) {
        List<Long> idList = Arrays.asList(websiteInfoLogIds);
        return toAjax(websiteInfoLogService.removeByIds(idList));
    }
}
