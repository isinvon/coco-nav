package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.SettingLogMapper;
import com.ruoyi.project.admin.domain.SettingLog;
import com.ruoyi.project.admin.service.custom.SettingLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 设置变更日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class SettingLogServiceImpl implements SettingLogCustomService
{
    @Autowired
    private SettingLogMapper settingLogMapper;

    /**
     * 查询设置变更日志
     * 
     * @param settingLogId 设置变更日志主键
     * @return 设置变更日志
     */
    @Override
    public SettingLog selectSettingLogBySettingLogId(Long settingLogId)
    {
        return settingLogMapper.selectSettingLogBySettingLogId(settingLogId);
    }

    /**
     * 查询设置变更日志列表
     * 
     * @param settingLog 设置变更日志
     * @return 设置变更日志
     */
    @Override
    public List<SettingLog> selectSettingLogList(SettingLog settingLog)
    {
        return settingLogMapper.selectSettingLogList(settingLog);
    }

    /**
     * 新增设置变更日志
     * 
     * @param settingLog 设置变更日志
     * @return 结果
     */
    @Override
    public int insertSettingLog(SettingLog settingLog)
    {
        settingLog.setCreateTime(DateUtils.getNowDate());
        return settingLogMapper.insertSettingLog(settingLog);
    }

    /**
     * 修改设置变更日志
     * 
     * @param settingLog 设置变更日志
     * @return 结果
     */
    @Override
    public int updateSettingLog(SettingLog settingLog)
    {
        settingLog.setUpdateTime(DateUtils.getNowDate());
        return settingLogMapper.updateSettingLog(settingLog);
    }

    /**
     * 批量删除设置变更日志
     * 
     * @param settingLogIds 需要删除的设置变更日志主键
     * @return 结果
     */
    @Override
    public int deleteSettingLogBySettingLogIds(Long[] settingLogIds)
    {
        return settingLogMapper.deleteSettingLogBySettingLogIds(settingLogIds);
    }

    /**
     * 删除设置变更日志信息
     * 
     * @param settingLogId 设置变更日志主键
     * @return 结果
     */
    @Override
    public int deleteSettingLogBySettingLogId(Long settingLogId)
    {
        return settingLogMapper.deleteSettingLogBySettingLogId(settingLogId);
    }
}
