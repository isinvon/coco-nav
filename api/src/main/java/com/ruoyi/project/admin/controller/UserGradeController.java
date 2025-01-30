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
import com.ruoyi.project.admin.domain.UserGrade;
import com.ruoyi.project.admin.service.IUserGradeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户等级Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/userGrade")
public class UserGradeController extends BaseController
{
    @Autowired
    private IUserGradeCustomService userGradeCustomService;

    /**
     * 查询用户等级列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(UserGrade userGrade)
    {
        startPage();
        List<UserGrade> list = userGradeCustomService.selectUserGradeList(userGrade);
        return getDataTable(list);
    }

    /**
     * 导出用户等级列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_EXPORT)
    @Log(title = "用户等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserGrade userGrade)
    {
        List<UserGrade> list = userGradeCustomService.selectUserGradeList(userGrade);
        ExcelUtil<UserGrade> util = new ExcelUtil<UserGrade>(UserGrade.class);
        util.exportExcel(response, list, "用户等级数据");
    }

    /**
     * 获取用户等级详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_QUERY)
    @GetMapping(value = "/{userGradeId}")
    public AjaxResult getInfo(@PathVariable("userGradeId") Long userGradeId)
    {
        return success(userGradeCustomService.selectUserGradeByUserGradeId(userGradeId));
    }

    /**
     * 新增用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_ADD)
    @Log(title = "用户等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserGrade userGrade)
    {
        return toAjax(userGradeCustomService.insertUserGrade(userGrade));
    }

    /**
     * 修改用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_EDIT)
    @Log(title = "用户等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserGrade userGrade)
    {
        return toAjax(userGradeCustomService.updateUserGrade(userGrade));
    }

    /**
     * 删除用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_REMOVE)
    @Log(title = "用户等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userGradeIds}")
    public AjaxResult remove(@PathVariable Long[] userGradeIds)
    {
        return toAjax(userGradeCustomService.deleteUserGradeByUserGradeIds(userGradeIds));
    }
}
