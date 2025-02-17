package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.po.Setting;

/**
 * 系统设置Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface SettingCustomService
{
    /**
     * 查询系统设置
     * 
     * @param settingId 系统设置主键
     * @return 系统设置
     */
    public Setting selectSettingBySettingId(Long settingId);

    /**
     * 查询系统设置列表
     * 
     * @param setting 系统设置
     * @return 系统设置集合
     */
    public List<Setting> selectSettingList(Setting setting);

    /**
     * 新增系统设置
     * 
     * @param setting 系统设置
     * @return 结果
     */
    public int insertSetting(Setting setting);

    /**
     * 修改系统设置
     * 
     * @param setting 系统设置
     * @return 结果
     */
    public int updateSetting(Setting setting);

    /**
     * 批量删除系统设置
     * 
     * @param settingIds 需要删除的系统设置主键集合
     * @return 结果
     */
    public int deleteSettingBySettingIds(Long[] settingIds);

    /**
     * 删除系统设置信息
     * 
     * @param settingId 系统设置主键
     * @return 结果
     */
    public int deleteSettingBySettingId(Long settingId);
}
