package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.AreaCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.project.admin.mapper.AreaMapper;
import com.ruoyi.project.admin.domain.Area;
import org.springframework.stereotype.Service;

/**
 * 地区信息Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class AreaServiceImpl implements AreaCustomService
{
    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询地区信息
     * 
     * @param areaId 地区信息主键
     * @return 地区信息
     */
    @Override
    public Area selectAreaByAreaId(Long areaId)
    {
        return areaMapper.selectAreaByAreaId(areaId);
    }

    /**
     * 查询地区信息列表
     * 
     * @param area 地区信息
     * @return 地区信息
     */
    @Override
    public List<Area> selectAreaList(Area area)
    {
        return areaMapper.selectAreaList(area);
    }

    /**
     * 新增地区信息
     * 
     * @param area 地区信息
     * @return 结果
     */
    @Override
    public int insertArea(Area area)
    {
        area.setCreateTime(DateUtils.getNowDate());
        return areaMapper.insertArea(area);
    }

    /**
     * 修改地区信息
     * 
     * @param area 地区信息
     * @return 结果
     */
    @Override
    public int updateArea(Area area)
    {
        area.setUpdateTime(DateUtils.getNowDate());
        return areaMapper.updateArea(area);
    }

    /**
     * 批量删除地区信息
     * 
     * @param areaIds 需要删除的地区信息主键
     * @return 结果
     */
    @Override
    public int deleteAreaByAreaIds(Long[] areaIds)
    {
        return areaMapper.deleteAreaByAreaIds(areaIds);
    }

    /**
     * 删除地区信息信息
     * 
     * @param areaId 地区信息主键
     * @return 结果
     */
    @Override
    public int deleteAreaByAreaId(Long areaId)
    {
        return areaMapper.deleteAreaByAreaId(areaId);
    }
}
