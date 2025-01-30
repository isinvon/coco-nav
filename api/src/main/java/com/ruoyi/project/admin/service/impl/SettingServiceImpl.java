package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.SettingService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.SettingMapper;
import com.ruoyi.project.admin.domain.Setting;
import com.ruoyi.project.admin.service.custom.SettingCustomService;
import org.springframework.stereotype.Service;

/**
 * 系统设置Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper,Setting> implements SettingCustomService, SettingService
{
    @Autowired
    private SettingMapper settingMapper;

    /**
     * 查询系统设置
     * 
     * @param settingId 系统设置主键
     * @return 系统设置
     */
    @Override
    public Setting selectSettingBySettingId(Long settingId)
    {
        return settingMapper.selectSettingBySettingId(settingId);
    }

    /**
     * 查询系统设置列表
     * 
     * @param setting 系统设置
     * @return 系统设置
     */
    @Override
    public List<Setting> selectSettingList(Setting setting)
    {
        return settingMapper.selectSettingList(setting);
    }

    /**
     * 新增系统设置
     * 
     * @param setting 系统设置
     * @return 结果
     */
    @Override
    public int insertSetting(Setting setting)
    {
        setting.setCreateTime(DateUtils.getNowDate());
        return settingMapper.insertSetting(setting);
    }

    /**
     * 修改系统设置
     * 
     * @param setting 系统设置
     * @return 结果
     */
    @Override
    public int updateSetting(Setting setting)
    {
        setting.setUpdateTime(DateUtils.getNowDate());
        return settingMapper.updateSetting(setting);
    }

    /**
     * 批量删除系统设置
     * 
     * @param settingIds 需要删除的系统设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingBySettingIds(Long[] settingIds)
    {
        return settingMapper.deleteSettingBySettingIds(settingIds);
    }

    /**
     * 删除系统设置信息
     * 
     * @param settingId 系统设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingBySettingId(Long settingId)
    {
        return settingMapper.deleteSettingBySettingId(settingId);
    }
}
