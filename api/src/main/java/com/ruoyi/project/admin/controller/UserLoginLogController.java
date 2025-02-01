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
import com.ruoyi.project.admin.domain.UserLoginLog;
import com.ruoyi.project.admin.service.UserLoginLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 用户登录日志Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/userLoginLog")
public class UserLoginLogController extends BaseController {
    @Resource
    private UserLoginLogService userLoginLogService;

    /**
     * 查询用户登录日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<UserLoginLog> list = userLoginLogService.list();
        return getDataTable(list);
    }

    /**
     * 导出用户登录日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_EXPORT)
    @Log(title = "用户登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<UserLoginLog> list = userLoginLogService.list();
        ExcelUtil<UserLoginLog> util = new ExcelUtil<>(UserLoginLog.class);
        util.exportExcel(response, list, "用户登录日志数据");
    }

    /**
     * 获取用户登录日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_QUERY)
    @GetMapping(value = "/{userLoginLogId}")
    public AjaxResult getInfo(@PathVariable("userLoginLogId") Long userLoginLogId) {
        return success(userLoginLogService.getById(userLoginLogId));
    }

    /**
     * 新增用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_ADD)
    @Log(title = "用户登录日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserLoginLog userLoginLog) {
        return toAjax(userLoginLogService.save(userLoginLog));
    }

    /**
     * 修改用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_EDIT)
    @Log(title = "用户登录日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserLoginLog userLoginLog) {
        LambdaUpdateWrapper<UserLoginLog> uw = new LambdaUpdateWrapper<>();
        uw.eq(UserLoginLog::getUserLoginLogId, userLoginLog.getUserLoginLogId());
        return toAjax(userLoginLogService.update(userLoginLog, uw));
    }

    /**
     * 删除用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_REMOVE)
    @Log(title = "用户登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userLoginLogIds}")
    public AjaxResult remove(@PathVariable Long[] userLoginLogIds) {
        List<Long> idList = Arrays.asList(userLoginLogIds);
        return toAjax(userLoginLogService.removeByIds(idList));
    }
}
