package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.PaymentType;

/**
 * 支付方式Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface PaymentTypeCustomService
{
    /**
     * 查询支付方式
     * 
     * @param paymentTypeId 支付方式主键
     * @return 支付方式
     */
    public PaymentType selectPaymentTypeByPaymentTypeId(Long paymentTypeId);

    /**
     * 查询支付方式列表
     * 
     * @param paymentType 支付方式
     * @return 支付方式集合
     */
    public List<PaymentType> selectPaymentTypeList(PaymentType paymentType);

    /**
     * 新增支付方式
     * 
     * @param paymentType 支付方式
     * @return 结果
     */
    public int insertPaymentType(PaymentType paymentType);

    /**
     * 修改支付方式
     * 
     * @param paymentType 支付方式
     * @return 结果
     */
    public int updatePaymentType(PaymentType paymentType);

    /**
     * 批量删除支付方式
     * 
     * @param paymentTypeIds 需要删除的支付方式主键集合
     * @return 结果
     */
    public int deletePaymentTypeByPaymentTypeIds(Long[] paymentTypeIds);

    /**
     * 删除支付方式信息
     * 
     * @param paymentTypeId 支付方式主键
     * @return 结果
     */
    public int deletePaymentTypeByPaymentTypeId(Long paymentTypeId);
}
