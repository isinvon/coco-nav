package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.VipService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.VipMapper;
import com.ruoyi.project.admin.domain.Vip;
import com.ruoyi.project.admin.service.custom.VipCustomService;
import org.springframework.stereotype.Service;

/**
 * VIP会员Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper,Vip> implements VipCustomService, VipService
{
    @Autowired
    private VipMapper vipMapper;

    /**
     * 查询VIP会员
     * 
     * @param vipId VIP会员主键
     * @return VIP会员
     */
    @Override
    public Vip selectVipByVipId(Long vipId)
    {
        return vipMapper.selectVipByVipId(vipId);
    }

    /**
     * 查询VIP会员列表
     * 
     * @param vip VIP会员
     * @return VIP会员
     */
    @Override
    public List<Vip> selectVipList(Vip vip)
    {
        return vipMapper.selectVipList(vip);
    }

    /**
     * 新增VIP会员
     * 
     * @param vip VIP会员
     * @return 结果
     */
    @Override
    public int insertVip(Vip vip)
    {
        vip.setCreateTime(DateUtils.getNowDate());
        return vipMapper.insertVip(vip);
    }

    /**
     * 修改VIP会员
     * 
     * @param vip VIP会员
     * @return 结果
     */
    @Override
    public int updateVip(Vip vip)
    {
        vip.setUpdateTime(DateUtils.getNowDate());
        return vipMapper.updateVip(vip);
    }

    /**
     * 批量删除VIP会员
     * 
     * @param vipIds 需要删除的VIP会员主键
     * @return 结果
     */
    @Override
    public int deleteVipByVipIds(Long[] vipIds)
    {
        return vipMapper.deleteVipByVipIds(vipIds);
    }

    /**
     * 删除VIP会员信息
     * 
     * @param vipId VIP会员主键
     * @return 结果
     */
    @Override
    public int deleteVipByVipId(Long vipId)
    {
        return vipMapper.deleteVipByVipId(vipId);
    }
}
