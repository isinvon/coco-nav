package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.PaymentTypeService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.PaymentTypeMapper;
import com.ruoyi.common.model.domain.po.PaymentType;
import com.ruoyi.common.model.service.custom.PaymentTypeCustomService;
import org.springframework.stereotype.Service;

/**
 * 支付方式Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper,PaymentType> implements PaymentTypeCustomService, PaymentTypeService
{
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    /**
     * 查询支付方式
     * 
     * @param paymentTypeId 支付方式主键
     * @return 支付方式
     */
    @Override
    public PaymentType selectPaymentTypeByPaymentTypeId(Long paymentTypeId)
    {
        return paymentTypeMapper.selectPaymentTypeByPaymentTypeId(paymentTypeId);
    }

    /**
     * 查询支付方式列表
     * 
     * @param paymentType 支付方式
     * @return 支付方式
     */
    @Override
    public List<PaymentType> selectPaymentTypeList(PaymentType paymentType)
    {
        return paymentTypeMapper.selectPaymentTypeList(paymentType);
    }

    /**
     * 新增支付方式
     * 
     * @param paymentType 支付方式
     * @return 结果
     */
    @Override
    public int insertPaymentType(PaymentType paymentType)
    {
        paymentType.setCreateTime(DateUtils.getNowDate());
        return paymentTypeMapper.insertPaymentType(paymentType);
    }

    /**
     * 修改支付方式
     * 
     * @param paymentType 支付方式
     * @return 结果
     */
    @Override
    public int updatePaymentType(PaymentType paymentType)
    {
        paymentType.setUpdateTime(DateUtils.getNowDate());
        return paymentTypeMapper.updatePaymentType(paymentType);
    }

    /**
     * 批量删除支付方式
     * 
     * @param paymentTypeIds 需要删除的支付方式主键
     * @return 结果
     */
    @Override
    public int deletePaymentTypeByPaymentTypeIds(Long[] paymentTypeIds)
    {
        return paymentTypeMapper.deletePaymentTypeByPaymentTypeIds(paymentTypeIds);
    }

    /**
     * 删除支付方式信息
     * 
     * @param paymentTypeId 支付方式主键
     * @return 结果
     */
    @Override
    public int deletePaymentTypeByPaymentTypeId(Long paymentTypeId)
    {
        return paymentTypeMapper.deletePaymentTypeByPaymentTypeId(paymentTypeId);
    }
}
