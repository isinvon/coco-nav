package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.Vip;
import com.ruoyi.common.model.service.VipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * VIP会员Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/vip")
public class VipController extends BaseController {
    @Resource
    private VipService vipService;

    /**
     * 查询VIP会员列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Vip> list = vipService.list();
        return getDataTable(list);
    }

    /**
     * 导出VIP会员列表
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_EXPORT)
    @Log(title = "VIP会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Vip> list = vipService.list();
        ExcelUtil<Vip> util = new ExcelUtil<>(Vip.class);
        util.exportExcel(response, list, "VIP会员数据");
    }

    /**
     * 获取VIP会员详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_QUERY)
    @GetMapping(value = "/{vipId}")
    public AjaxResult getInfo(@PathVariable("vipId") Long vipId) {
        return success(vipService.getById(vipId));
    }

    /**
     * 新增VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_ADD)
    @Log(title = "VIP会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Vip vip) {
        return toAjax(vipService.save(vip));
    }

    /**
     * 修改VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_EDIT)
    @Log(title = "VIP会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Vip vip) {
        return toAjax(vipService.updateById(vip));
    }

    /**
     * 删除VIP会员
     */
    @CustomPermission(PermissionConstants.ADMIN_VIP_REMOVE)
    @Log(title = "VIP会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vipIds}")
    public AjaxResult remove(@PathVariable Long[] vipIds) {
        List<Long> idList = Arrays.asList(vipIds);
        return toAjax(vipService.removeByIds(idList));
    }
}
