package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.VipType;
import com.ruoyi.common.model.service.VipTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * VIP类型Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vipType")
public class VipTypeController extends BaseController {
    @Resource
    private VipTypeService vipTypeService;

    /**
     * 查询VIP类型列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<VipType> list = vipTypeService.list();
        return getDataTable(list);
    }

    /**
     * 导出VIP类型列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_EXPORT)
    @Log(title = "VIP类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<VipType> list = vipTypeService.list();
        ExcelUtil<VipType> util = new ExcelUtil<>(VipType.class);
        util.exportExcel(response, list, "VIP类型数据");
    }

    /**
     * 获取VIP类型详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_QUERY)
    @GetMapping(value = "/{vipTypeId}")
    public AjaxResult getInfo(@PathVariable("vipTypeId") Long vipTypeId) {
        return success(vipTypeService.getById(vipTypeId));
    }

    /**
     * 新增VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_ADD)
    @Log(title = "VIP类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipType vipType) {
        return toAjax(vipTypeService.save(vipType));
    }

    /**
     * 修改VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_EDIT)
    @Log(title = "VIP类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipType vipType) {
        return toAjax(vipTypeService.updateById(vipType));
    }

    /**
     * 删除VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_REMOVE)
    @Log(title = "VIP类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vipTypeIds}")
    public AjaxResult remove(@PathVariable Long[] vipTypeIds) {
        List<Long> idList = Arrays.asList(vipTypeIds);
        return toAjax(vipTypeService.removeByIds(idList));
    }
}
