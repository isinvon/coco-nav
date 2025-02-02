package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.PaymentType;
import com.ruoyi.project.admin.service.PaymentTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 支付方式Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/paymentType")
public class PaymentTypeController extends BaseController {
    @Resource
    private PaymentTypeService paymentTypeService;

    /**
     * 查询支付方式列表
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<PaymentType> list = paymentTypeService.list();
        return getDataTable(list);
    }

    /**
     * 导出支付方式列表
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_EXPORT)
    @Log(title = "支付方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<PaymentType> list = paymentTypeService.list();
        ExcelUtil<PaymentType> util = new ExcelUtil<>(PaymentType.class);
        util.exportExcel(response, list, "支付方式数据");
    }

    /**
     * 获取支付方式详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_QUERY)
    @GetMapping(value = "/{paymentTypeId}")
    public AjaxResult getInfo(@PathVariable("paymentTypeId") Long paymentTypeId) {
        return success(paymentTypeService.getById(paymentTypeId));
    }

    /**
     * 新增支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_ADD)
    @Log(title = "支付方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentType paymentType) {
        return toAjax(paymentTypeService.save(paymentType));
    }

    /**
     * 修改支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_EDIT)
    @Log(title = "支付方式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentType paymentType) {
        return toAjax(paymentTypeService.updateById(paymentType));
    }

    /**
     * 删除支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_REMOVE)
    @Log(title = "支付方式", businessType = BusinessType.DELETE)
    @DeleteMapping("/{paymentTypeIds}")
    public AjaxResult remove(@PathVariable Long[] paymentTypeIds) {
        List<Long> idList = Arrays.asList(paymentTypeIds);
        return toAjax(paymentTypeService.removeByIds(idList));
    }
}
