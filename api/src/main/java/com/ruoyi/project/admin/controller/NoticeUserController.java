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
import com.ruoyi.project.admin.domain.NoticeUser;
import com.ruoyi.project.admin.service.NoticeUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 通知用户关系Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/noticeUser")
public class NoticeUserController extends BaseController {
    @Resource
    private NoticeUserService noticeUserService;

    /**
     * 查询通知用户关系列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<NoticeUser> list = noticeUserService.list();
        return getDataTable(list);
    }

    /**
     * 导出通知用户关系列表
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_EXPORT)
    @Log(title = "通知用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<NoticeUser> list = noticeUserService.list();
        ExcelUtil<NoticeUser> util = new ExcelUtil<>(NoticeUser.class);
        util.exportExcel(response, list, "通知用户关系数据");
    }

    /**
     * 获取通知用户关系详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_QUERY)
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId) {
        return success(noticeUserService.getById(relationId));
    }

    /**
     * 新增通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_ADD)
    @Log(title = "通知用户关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoticeUser noticeUser) {
        return toAjax(noticeUserService.save(noticeUser));
    }

    /**
     * 修改通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_EDIT)
    @Log(title = "通知用户关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoticeUser noticeUser) {
        LambdaQueryWrapper<NoticeUser> qw = new LambdaQueryWrapper<>();
        qw.eq(NoticeUser::getRelationId, noticeUser.getRelationId());
        return toAjax(noticeUserService.update(noticeUser, qw));
    }

    /**
     * 删除通知用户关系
     */
    @CustomPermission(PermissionConstants.ADMIN_NOTICE_USER_REMOVE)
    @Log(title = "通知用户关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds) {
        List<Long> idList = Arrays.asList(relationIds);
        return toAjax(noticeUserService.removeByIds(idList));
    }
}
