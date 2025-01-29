package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.project.admin.mapper.AdvertisementMapper;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.service.IAdvertisementCustomService;
import org.springframework.stereotype.Service;

/**
 * 广告管理Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementCustomService
{
    @Autowired
    private AdvertisementMapper advertisementMapper;

    /**
     * 查询广告管理
     * 
     * @param advertisementId 广告管理主键
     * @return 广告管理
     */
    @Override
    public Advertisement selectAdvertisementByAdvertisementId(Long advertisementId)
    {
        return advertisementMapper.selectAdvertisementByAdvertisementId(advertisementId);
    }

    /**
     * 查询广告管理列表
     * 
     * @param advertisement 广告管理
     * @return 广告管理
     */
    @Override
    public List<Advertisement> selectAdvertisementList(Advertisement advertisement)
    {
        return advertisementMapper.selectAdvertisementList(advertisement);
    }

    /**
     * 新增广告管理
     * 
     * @param advertisement 广告管理
     * @return 结果
     */
    @Override
    public int insertAdvertisement(Advertisement advertisement)
    {
        advertisement.setCreateTime(DateUtils.getNowDate());
        return advertisementMapper.insertAdvertisement(advertisement);
    }

    /**
     * 修改广告管理
     * 
     * @param advertisement 广告管理
     * @return 结果
     */
    @Override
    public int updateAdvertisement(Advertisement advertisement)
    {
        advertisement.setUpdateTime(DateUtils.getNowDate());
        return advertisementMapper.updateAdvertisement(advertisement);
    }

    /**
     * 批量删除广告管理
     * 
     * @param advertisementIds 需要删除的广告管理主键
     * @return 结果
     */
    @Override
    public int deleteAdvertisementByAdvertisementIds(Long[] advertisementIds)
    {
        return advertisementMapper.deleteAdvertisementByAdvertisementIds(advertisementIds);
    }

    /**
     * 删除广告管理信息
     * 
     * @param advertisementId 广告管理主键
     * @return 结果
     */
    @Override
    public int deleteAdvertisementByAdvertisementId(Long advertisementId)
    {
        return advertisementMapper.deleteAdvertisementByAdvertisementId(advertisementId);
    }
}
