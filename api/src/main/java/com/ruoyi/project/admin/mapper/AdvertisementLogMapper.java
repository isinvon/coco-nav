package com.ruoyi.project.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.AdvertisementLog;

/**
 * 广告操作日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface AdvertisementLogMapper extends BaseMapper<AdvertisementLog>
{
    /**
     * 查询广告操作日志
     * 
     * @param advertisementLogId 广告操作日志主键
     * @return 广告操作日志
     */
    public AdvertisementLog selectAdvertisementLogByAdvertisementLogId(Long advertisementLogId);

    /**
     * 查询广告操作日志列表
     * 
     * @param advertisementLog 广告操作日志
     * @return 广告操作日志集合
     */
    public List<AdvertisementLog> selectAdvertisementLogList(AdvertisementLog advertisementLog);

    /**
     * 新增广告操作日志
     * 
     * @param advertisementLog 广告操作日志
     * @return 结果
     */
    public int insertAdvertisementLog(AdvertisementLog advertisementLog);

    /**
     * 修改广告操作日志
     * 
     * @param advertisementLog 广告操作日志
     * @return 结果
     */
    public int updateAdvertisementLog(AdvertisementLog advertisementLog);

    /**
     * 删除广告操作日志
     * 
     * @param advertisementLogId 广告操作日志主键
     * @return 结果
     */
    public int deleteAdvertisementLogByAdvertisementLogId(Long advertisementLogId);

    /**
     * 批量删除广告操作日志
     * 
     * @param advertisementLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdvertisementLogByAdvertisementLogIds(Long[] advertisementLogIds);
}
