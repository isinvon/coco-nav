package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.framework.security.permission.CustomPermission;
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
import com.ruoyi.project.admin.domain.Notice;
import com.ruoyi.project.admin.service.custom.NoticeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 通知管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController
{
    @Autowired
    private NoticeCustomService noticeCustomService;

    /**
     * 查询通知管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Notice notice)
    {
        startPage();
        List<Notice> list = noticeCustomService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 导出通知管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_EXPORT)
    @Log(title = "通知管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notice notice)
    {
        List<Notice> list = noticeCustomService.selectNoticeList(notice);
        ExcelUtil<Notice> util = new ExcelUtil<Notice>(Notice.class);
        util.exportExcel(response, list, "通知管理数据");
    }

    /**
     * 获取通知管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_QUERY)
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId)
    {
        return success(noticeCustomService.selectNoticeByNoticeId(noticeId));
    }

    /**
     * 新增通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_ADD)
    @Log(title = "通知管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice)
    {
        return toAjax(noticeCustomService.insertNotice(notice));
    }

    /**
     * 修改通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_EDIT)
    @Log(title = "通知管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice)
    {
        return toAjax(noticeCustomService.updateNotice(notice));
    }

    /**
     * 删除通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_REMOVE)
    @Log(title = "通知管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeCustomService.deleteNoticeByNoticeIds(noticeIds));
    }
}
