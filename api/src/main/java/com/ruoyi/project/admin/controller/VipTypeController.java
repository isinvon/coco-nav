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
import com.ruoyi.project.admin.domain.VipType;
import com.ruoyi.project.admin.service.custom.VipTypeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * VIP类型Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vipType")
public class VipTypeController extends BaseController
{
    @Autowired
    private VipTypeCustomService vipTypeCustomService;

    /**
     * 查询VIP类型列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(VipType vipType)
    {
        startPage();
        List<VipType> list = vipTypeCustomService.selectVipTypeList(vipType);
        return getDataTable(list);
    }

    /**
     * 导出VIP类型列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_EXPORT)
    @Log(title = "VIP类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipType vipType)
    {
        List<VipType> list = vipTypeCustomService.selectVipTypeList(vipType);
        ExcelUtil<VipType> util = new ExcelUtil<VipType>(VipType.class);
        util.exportExcel(response, list, "VIP类型数据");
    }

    /**
     * 获取VIP类型详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_QUERY)
    @GetMapping(value = "/{vipTypeId}")
    public AjaxResult getInfo(@PathVariable("vipTypeId") Long vipTypeId)
    {
        return success(vipTypeCustomService.selectVipTypeByVipTypeId(vipTypeId));
    }

    /**
     * 新增VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_ADD)
    @Log(title = "VIP类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipType vipType)
    {
        return toAjax(vipTypeCustomService.insertVipType(vipType));
    }

    /**
     * 修改VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_EDIT)
    @Log(title = "VIP类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipType vipType)
    {
        return toAjax(vipTypeCustomService.updateVipType(vipType));
    }

    /**
     * 删除VIP类型
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_TYPE_REMOVE)
    @Log(title = "VIP类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vipTypeIds}")
    public AjaxResult remove(@PathVariable Long[] vipTypeIds)
    {
        return toAjax(vipTypeCustomService.deleteVipTypeByVipTypeIds(vipTypeIds));
    }
}
