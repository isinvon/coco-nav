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
import com.ruoyi.project.admin.domain.PaymentType;
import com.ruoyi.project.admin.service.custom.PaymentTypeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 支付方式Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/paymentType")
public class PaymentTypeController extends BaseController
{
    @Autowired
    private PaymentTypeCustomService paymentTypeCustomService;

    /**
     * 查询支付方式列表
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(PaymentType paymentType)
    {
        startPage();
        List<PaymentType> list = paymentTypeCustomService.selectPaymentTypeList(paymentType);
        return getDataTable(list);
    }

    /**
     * 导出支付方式列表
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_EXPORT)
    @Log(title = "支付方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentType paymentType)
    {
        List<PaymentType> list = paymentTypeCustomService.selectPaymentTypeList(paymentType);
        ExcelUtil<PaymentType> util = new ExcelUtil<PaymentType>(PaymentType.class);
        util.exportExcel(response, list, "支付方式数据");
    }

    /**
     * 获取支付方式详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_QUERY)
    @GetMapping(value = "/{paymentTypeId}")
    public AjaxResult getInfo(@PathVariable("paymentTypeId") Long paymentTypeId)
    {
        return success(paymentTypeCustomService.selectPaymentTypeByPaymentTypeId(paymentTypeId));
    }

    /**
     * 新增支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_ADD)
    @Log(title = "支付方式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentType paymentType)
    {
        return toAjax(paymentTypeCustomService.insertPaymentType(paymentType));
    }

    /**
     * 修改支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_EDIT)
    @Log(title = "支付方式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentType paymentType)
    {
        return toAjax(paymentTypeCustomService.updatePaymentType(paymentType));
    }

    /**
     * 删除支付方式
     */
    @CustomPermission(PermissionConstants.ADMIN_PAYMENT_TYPE_REMOVE)
    @Log(title = "支付方式", businessType = BusinessType.DELETE)
	@DeleteMapping("/{paymentTypeIds}")
    public AjaxResult remove(@PathVariable Long[] paymentTypeIds)
    {
        return toAjax(paymentTypeCustomService.deletePaymentTypeByPaymentTypeIds(paymentTypeIds));
    }
}
