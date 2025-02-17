package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.Notice;
import com.ruoyi.common.model.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 通知管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController {
    @Resource
    private NoticeService noticeService;

    /**
     * 查询通知管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Notice> list = noticeService.list();
        return getDataTable(list);
    }

    /**
     * 导出通知管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_EXPORT)
    @Log(title = "通知管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Notice> list = noticeService.list();
        ExcelUtil<Notice> util = new ExcelUtil<>(Notice.class);
        util.exportExcel(response, list, "通知管理数据");
    }

    /**
     * 获取通知管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_QUERY)
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable("noticeId") Long noticeId) {
        return success(noticeService.getById(noticeId));
    }

    /**
     * 新增通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_ADD)
    @Log(title = "通知管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice) {
        return toAjax(noticeService.save(notice));
    }

    /**
     * 修改通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_EDIT)
    @Log(title = "通知管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice) {
        return toAjax(noticeService.updateById(notice));
    }

    /**
     * 删除通知管理
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_REMOVE)
    @Log(title = "通知管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        List<Long> idList = Arrays.asList(noticeIds);
        return toAjax(noticeService.removeByIds(idList));
    }
}
