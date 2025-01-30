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
import com.ruoyi.project.admin.domain.User;
import com.ruoyi.project.admin.service.UserCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController
{
    @Autowired
    private UserCustomService userCustomService;

    /**
     * 查询用户管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LIST)
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userCustomService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_EXPORT)
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userCustomService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_QUERY)
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userCustomService.selectUserByUserId(userId));
    }

    /**
     * 新增用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_ADD)
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        return toAjax(userCustomService.insertUser(user));
    }

    /**
     * 修改用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_EDIT)
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userCustomService.updateUser(user));
    }

    /**
     * 删除用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_REMOVE)
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userCustomService.deleteUserByUserIds(userIds));
    }
}
