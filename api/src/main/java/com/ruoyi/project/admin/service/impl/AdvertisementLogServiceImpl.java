package com.ruoyi.project.admin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.AdvertisementLogService;
import com.ruoyi.project.admin.service.custom.AdvertisementLogCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.project.admin.mapper.AdvertisementLogMapper;
import com.ruoyi.project.admin.domain.AdvertisementLog;
import org.springframework.stereotype.Service;

/**
 * 广告操作日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class AdvertisementLogServiceImpl extends ServiceImpl<AdvertisementLogMapper,AdvertisementLog> implements AdvertisementLogCustomService, AdvertisementLogService
{
    @Autowired
    private AdvertisementLogMapper advertisementLogMapper;

    /**
     * 查询广告操作日志
     * 
     * @param advertisementLogId 广告操作日志主键
     * @return 广告操作日志
     */
    @Override
    public AdvertisementLog selectAdvertisementLogByAdvertisementLogId(Long advertisementLogId)
    {
        return advertisementLogMapper.selectAdvertisementLogByAdvertisementLogId(advertisementLogId);
    }

    /**
     * 查询广告操作日志列表
     * 
     * @param advertisementLog 广告操作日志
     * @return 广告操作日志
     */
    @Override
    public List<AdvertisementLog> selectAdvertisementLogList(AdvertisementLog advertisementLog)
    {
        return advertisementLogMapper.selectAdvertisementLogList(advertisementLog);
    }

    /**
     * 新增广告操作日志
     * 
     * @param advertisementLog 广告操作日志
     * @return 结果
     */
    @Override
    public int insertAdvertisementLog(AdvertisementLog advertisementLog)
    {
        advertisementLog.setCreateTime(DateUtils.getNowDate());
        return advertisementLogMapper.insertAdvertisementLog(advertisementLog);
    }

    /**
     * 修改广告操作日志
     * 
     * @param advertisementLog 广告操作日志
     * @return 结果
     */
    @Override
    public int updateAdvertisementLog(AdvertisementLog advertisementLog)
    {
        advertisementLog.setUpdateTime(DateUtils.getNowDate());
        return advertisementLogMapper.updateAdvertisementLog(advertisementLog);
    }

    /**
     * 批量删除广告操作日志
     * 
     * @param advertisementLogIds 需要删除的广告操作日志主键
     * @return 结果
     */
    @Override
    public int deleteAdvertisementLogByAdvertisementLogIds(Long[] advertisementLogIds)
    {
        return advertisementLogMapper.deleteAdvertisementLogByAdvertisementLogIds(advertisementLogIds);
    }

    /**
     * 删除广告操作日志信息
     * 
     * @param advertisementLogId 广告操作日志主键
     * @return 结果
     */
    @Override
    public int deleteAdvertisementLogByAdvertisementLogId(Long advertisementLogId)
    {
        return advertisementLogMapper.deleteAdvertisementLogByAdvertisementLogId(advertisementLogId);
    }

}
