package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.VipTypeService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.VipTypeMapper;
import com.ruoyi.project.admin.domain.VipType;
import com.ruoyi.project.admin.service.custom.VipTypeCustomService;
import org.springframework.stereotype.Service;

/**
 * VIP类型Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class VipTypeServiceImpl extends ServiceImpl<VipTypeMapper,VipType> implements VipTypeCustomService, VipTypeService
{
    @Autowired
    private VipTypeMapper vipTypeMapper;

    /**
     * 查询VIP类型
     * 
     * @param vipTypeId VIP类型主键
     * @return VIP类型
     */
    @Override
    public VipType selectVipTypeByVipTypeId(Long vipTypeId)
    {
        return vipTypeMapper.selectVipTypeByVipTypeId(vipTypeId);
    }

    /**
     * 查询VIP类型列表
     * 
     * @param vipType VIP类型
     * @return VIP类型
     */
    @Override
    public List<VipType> selectVipTypeList(VipType vipType)
    {
        return vipTypeMapper.selectVipTypeList(vipType);
    }

    /**
     * 新增VIP类型
     * 
     * @param vipType VIP类型
     * @return 结果
     */
    @Override
    public int insertVipType(VipType vipType)
    {
        vipType.setCreateTime(DateUtils.getNowDate());
        return vipTypeMapper.insertVipType(vipType);
    }

    /**
     * 修改VIP类型
     * 
     * @param vipType VIP类型
     * @return 结果
     */
    @Override
    public int updateVipType(VipType vipType)
    {
        vipType.setUpdateTime(DateUtils.getNowDate());
        return vipTypeMapper.updateVipType(vipType);
    }

    /**
     * 批量删除VIP类型
     * 
     * @param vipTypeIds 需要删除的VIP类型主键
     * @return 结果
     */
    @Override
    public int deleteVipTypeByVipTypeIds(Long[] vipTypeIds)
    {
        return vipTypeMapper.deleteVipTypeByVipTypeIds(vipTypeIds);
    }

    /**
     * 删除VIP类型信息
     * 
     * @param vipTypeId VIP类型主键
     * @return 结果
     */
    @Override
    public int deleteVipTypeByVipTypeId(Long vipTypeId)
    {
        return vipTypeMapper.deleteVipTypeByVipTypeId(vipTypeId);
    }
}
