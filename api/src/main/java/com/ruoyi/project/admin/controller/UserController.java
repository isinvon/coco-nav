package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.User;
import com.ruoyi.project.admin.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 用户管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    /**
     * 查询用户管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<User> list = userService.list();
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_EXPORT)
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<User> list = userService.list();
        ExcelUtil<User> util = new ExcelUtil<>(User.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_QUERY)
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(userService.getById(userId));
    }

    /**
     * 新增用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_ADD)
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user) {
        return toAjax(userService.save(user));
    }

    /**
     * 修改用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_EDIT)
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user) {
        return toAjax(userService.updateById(user));
    }

    /**
     * 删除用户管理
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_REMOVE)
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        List<Long> idList = Arrays.asList(userIds);
        return toAjax(userService.removeByIds(idList));
    }
}
