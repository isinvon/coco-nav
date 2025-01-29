package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.SettingLog;

/**
 * 设置变更日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface SettingLogMapper 
{
    /**
     * 查询设置变更日志
     * 
     * @param settingLogId 设置变更日志主键
     * @return 设置变更日志
     */
    public SettingLog selectSettingLogBySettingLogId(Long settingLogId);

    /**
     * 查询设置变更日志列表
     * 
     * @param settingLog 设置变更日志
     * @return 设置变更日志集合
     */
    public List<SettingLog> selectSettingLogList(SettingLog settingLog);

    /**
     * 新增设置变更日志
     * 
     * @param settingLog 设置变更日志
     * @return 结果
     */
    public int insertSettingLog(SettingLog settingLog);

    /**
     * 修改设置变更日志
     * 
     * @param settingLog 设置变更日志
     * @return 结果
     */
    public int updateSettingLog(SettingLog settingLog);

    /**
     * 删除设置变更日志
     * 
     * @param settingLogId 设置变更日志主键
     * @return 结果
     */
    public int deleteSettingLogBySettingLogId(Long settingLogId);

    /**
     * 批量删除设置变更日志
     * 
     * @param settingLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSettingLogBySettingLogIds(Long[] settingLogIds);
}
