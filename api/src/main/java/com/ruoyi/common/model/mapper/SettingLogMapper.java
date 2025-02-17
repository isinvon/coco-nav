package com.ruoyi.common.model.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.common.model.domain.po.SettingLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设置变更日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface SettingLogMapper extends BaseMapper<SettingLog>
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
