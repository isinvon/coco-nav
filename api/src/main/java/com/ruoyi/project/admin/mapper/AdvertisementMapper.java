package com.ruoyi.project.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.Advertisement;

/**
 * 广告管理Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface AdvertisementMapper extends BaseMapper<Advertisement>
{
    /**
     * 查询广告管理
     * 
     * @param advertisementId 广告管理主键
     * @return 广告管理
     */
    public Advertisement selectAdvertisementByAdvertisementId(Long advertisementId);

    /**
     * 查询广告管理列表
     * 
     * @param advertisement 广告管理
     * @return 广告管理集合
     */
    public List<Advertisement> selectAdvertisementList(Advertisement advertisement);

    /**
     * 新增广告管理
     * 
     * @param advertisement 广告管理
     * @return 结果
     */
    public int insertAdvertisement(Advertisement advertisement);

    /**
     * 修改广告管理
     * 
     * @param advertisement 广告管理
     * @return 结果
     */
    public int updateAdvertisement(Advertisement advertisement);

    /**
     * 删除广告管理
     * 
     * @param advertisementId 广告管理主键
     * @return 结果
     */
    public int deleteAdvertisementByAdvertisementId(Long advertisementId);

    /**
     * 批量删除广告管理
     * 
     * @param advertisementIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdvertisementByAdvertisementIds(Long[] advertisementIds);
}
