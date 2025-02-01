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
import com.ruoyi.project.admin.domain.UserGrade;
import com.ruoyi.project.admin.service.UserGradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 用户等级Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/userGrade")
public class UserGradeController extends BaseController {
    @Resource
    private UserGradeService userGradeService;

    /**
     * 查询用户等级列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<UserGrade> list = userGradeService.list();
        return getDataTable(list);
    }

    /**
     * 导出用户等级列表
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_EXPORT)
    @Log(title = "用户等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<UserGrade> list = userGradeService.list();
        ExcelUtil<UserGrade> util = new ExcelUtil<>(UserGrade.class);
        util.exportExcel(response, list, "用户等级数据");
    }

    /**
     * 获取用户等级详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_QUERY)
    @GetMapping(value = "/{userGradeId}")
    public AjaxResult getInfo(@PathVariable("userGradeId") Long userGradeId) {
        return success(userGradeService.getById(userGradeId));
    }

    /**
     * 新增用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_ADD)
    @Log(title = "用户等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserGrade userGrade) {
        return toAjax(userGradeService.save(userGrade));
    }

    /**
     * 修改用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_EDIT)
    @Log(title = "用户等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserGrade userGrade) {
        LambdaUpdateWrapper<UserGrade> uw = new LambdaUpdateWrapper<>();
        uw.eq(UserGrade::getId, userGrade.getId());
        return toAjax(userGradeService.update(userGrade, uw));
    }

    /**
     * 删除用户等级
     */
    @CustomPermission(PermissionConstants.ADMIN_USER_GRADE_REMOVE)
    @Log(title = "用户等级", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userGradeIds}")
    public AjaxResult remove(@PathVariable Long[] userGradeIds) {
        List<Long> idList = Arrays.asList(userGradeIds);
        return toAjax(userGradeService.removeByIds(idList));
    }
}
