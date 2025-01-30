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
import com.ruoyi.project.admin.domain.UserLoginLog;
import com.ruoyi.project.admin.service.IUserLoginLogCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户登录日志Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/userLoginLog")
public class UserLoginLogController extends BaseController
{
    @Autowired
    private IUserLoginLogCustomService userLoginLogCustomService;

    /**
     * 查询用户登录日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_LIST)
    @GetMapping("/list")
    public TableDataInfo list(UserLoginLog userLoginLog)
    {
        startPage();
        List<UserLoginLog> list = userLoginLogCustomService.selectUserLoginLogList(userLoginLog);
        return getDataTable(list);
    }

    /**
     * 导出用户登录日志列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_EXPORT)
    @Log(title = "用户登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLoginLog userLoginLog)
    {
        List<UserLoginLog> list = userLoginLogCustomService.selectUserLoginLogList(userLoginLog);
        ExcelUtil<UserLoginLog> util = new ExcelUtil<UserLoginLog>(UserLoginLog.class);
        util.exportExcel(response, list, "用户登录日志数据");
    }

    /**
     * 获取用户登录日志详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_QUERY)
    @GetMapping(value = "/{userLoginLogId}")
    public AjaxResult getInfo(@PathVariable("userLoginLogId") Long userLoginLogId)
    {
        return success(userLoginLogCustomService.selectUserLoginLogByUserLoginLogId(userLoginLogId));
    }

    /**
     * 新增用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_ADD)
    @Log(title = "用户登录日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserLoginLog userLoginLog)
    {
        return toAjax(userLoginLogCustomService.insertUserLoginLog(userLoginLog));
    }

    /**
     * 修改用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_EDIT)
    @Log(title = "用户登录日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserLoginLog userLoginLog)
    {
        return toAjax(userLoginLogCustomService.updateUserLoginLog(userLoginLog));
    }

    /**
     * 删除用户登录日志
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LOGIN_LOG_REMOVE)
    @Log(title = "用户登录日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userLoginLogIds}")
    public AjaxResult remove(@PathVariable Long[] userLoginLogIds)
    {
        return toAjax(userLoginLogCustomService.deleteUserLoginLogByUserLoginLogIds(userLoginLogIds));
    }
}
