package com.ruoyi.common.model.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.common.model.domain.po.Setting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统设置Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface SettingMapper extends BaseMapper<Setting>
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
     * 删除系统设置
     * 
     * @param settingId 系统设置主键
     * @return 结果
     */
    public int deleteSettingBySettingId(Long settingId);

    /**
     * 批量删除系统设置
     * 
     * @param settingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSettingBySettingIds(Long[] settingIds);
}
