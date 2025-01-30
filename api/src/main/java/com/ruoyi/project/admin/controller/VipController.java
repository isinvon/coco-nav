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
import com.ruoyi.project.admin.domain.Vip;
import com.ruoyi.project.admin.service.custom.VipCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * VIP会员Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vip")
public class VipController extends BaseController
{
    @Autowired
    private VipCustomService vipCustomService;

    /**
     * 查询VIP会员列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Vip vip)
    {
        startPage();
        List<Vip> list = vipCustomService.selectVipList(vip);
        return getDataTable(list);
    }

    /**
     * 导出VIP会员列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_EXPORT)
    @Log(title = "VIP会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Vip vip)
    {
        List<Vip> list = vipCustomService.selectVipList(vip);
        ExcelUtil<Vip> util = new ExcelUtil<Vip>(Vip.class);
        util.exportExcel(response, list, "VIP会员数据");
    }

    /**
     * 获取VIP会员详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_QUERY)
    @GetMapping(value = "/{vipId}")
    public AjaxResult getInfo(@PathVariable("vipId") Long vipId)
    {
        return success(vipCustomService.selectVipByVipId(vipId));
    }

    /**
     * 新增VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_ADD)
    @Log(title = "VIP会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Vip vip)
    {
        return toAjax(vipCustomService.insertVip(vip));
    }

    /**
     * 修改VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_EDIT)
    @Log(title = "VIP会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Vip vip)
    {
        return toAjax(vipCustomService.updateVip(vip));
    }

    /**
     * 删除VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_REMOVE)
    @Log(title = "VIP会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vipIds}")
    public AjaxResult remove(@PathVariable Long[] vipIds)
    {
        return toAjax(vipCustomService.deleteVipByVipIds(vipIds));
    }
}
