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
import com.ruoyi.project.admin.domain.InviteCode;
import com.ruoyi.project.admin.service.InviteCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 邀请码管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/InviteCode")
public class InviteCodeController extends BaseController {
    @Resource
    private InviteCodeService inviteCodeService;

    /**
     * 查询邀请码管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<InviteCode> list = inviteCodeService.list();
        return getDataTable(list);
    }

    /**
     * 导出邀请码管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_EXPORT)
    @Log(title = "邀请码管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<InviteCode> list = inviteCodeService.list();
        ExcelUtil<InviteCode> util = new ExcelUtil<>(InviteCode.class);
        util.exportExcel(response, list, "邀请码管理数据");
    }

    /**
     * 获取邀请码管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_QUERY)
    @GetMapping(value = "/{inviteCodeId}")
    public AjaxResult getInfo(@PathVariable("inviteCodeId") Long inviteCodeId) {
        return success(inviteCodeService.getById(inviteCodeId));
    }

    /**
     * 新增邀请码管理
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_ADD)
    @Log(title = "邀请码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteCode inviteCode) {
        return toAjax(inviteCodeService.save(inviteCode));
    }

    /**
     * 修改邀请码管理
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_EDIT)
    @Log(title = "邀请码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteCode inviteCode) {
        LambdaQueryWrapper<InviteCode> qw = new LambdaQueryWrapper<>();
        qw.eq(InviteCode::getInviteCodeId, inviteCode.getInviteCodeId());
        return toAjax(inviteCodeService.update(inviteCode, qw));
    }

    /**
     * 删除邀请码管理
     */
    @CustomPermission(PermissionConstants.ADMIN_INVITE_CODE_REMOVE)
    @Log(title = "邀请码管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inviteCodeIds}")
    public AjaxResult remove(@PathVariable Long[] inviteCodeIds) {
        List<Long> idList = Arrays.asList(inviteCodeIds);
        return toAjax(inviteCodeService.removeByIds(idList));
    }
}
