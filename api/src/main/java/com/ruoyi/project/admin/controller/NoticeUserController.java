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
import com.ruoyi.project.admin.domain.NoticeUser;
import com.ruoyi.project.admin.service.INoticeUserCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 通知用户关系Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/noticeUser")
public class NoticeUserController extends BaseController
{
    @Autowired
    private INoticeUserCustomService noticeUserCustomService;

    /**
     * 查询通知用户关系列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_LIST)
    @GetMapping("/list")
    public TableDataInfo list(NoticeUser noticeUser)
    {
        startPage();
        List<NoticeUser> list = noticeUserCustomService.selectNoticeUserList(noticeUser);
        return getDataTable(list);
    }

    /**
     * 导出通知用户关系列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_EXPORT)
    @Log(title = "通知用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoticeUser noticeUser)
    {
        List<NoticeUser> list = noticeUserCustomService.selectNoticeUserList(noticeUser);
        ExcelUtil<NoticeUser> util = new ExcelUtil<NoticeUser>(NoticeUser.class);
        util.exportExcel(response, list, "通知用户关系数据");
    }

    /**
     * 获取通知用户关系详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_QUERY)
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId)
    {
        return success(noticeUserCustomService.selectNoticeUserByRelationId(relationId));
    }

    /**
     * 新增通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_ADD)
    @Log(title = "通知用户关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoticeUser noticeUser)
    {
        return toAjax(noticeUserCustomService.insertNoticeUser(noticeUser));
    }

    /**
     * 修改通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_EDIT)
    @Log(title = "通知用户关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoticeUser noticeUser)
    {
        return toAjax(noticeUserCustomService.updateNoticeUser(noticeUser));
    }

    /**
     * 删除通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_REMOVE)
    @Log(title = "通知用户关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds)
    {
        return toAjax(noticeUserCustomService.deleteNoticeUserByRelationIds(relationIds));
    }
}
