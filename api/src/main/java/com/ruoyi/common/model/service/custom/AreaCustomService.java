package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.Area;

/**
 * 地区信息Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface AreaCustomService
{
    /**
     * 查询地区信息
     * 
     * @param areaId 地区信息主键
     * @return 地区信息
     */
    public Area selectAreaByAreaId(Long areaId);

    /**
     * 查询地区信息列表
     * 
     * @param area 地区信息
     * @return 地区信息集合
     */
    public List<Area> selectAreaList(Area area);

    /**
     * 新增地区信息
     * 
     * @param area 地区信息
     * @return 结果
     */
    public int insertArea(Area area);

    /**
     * 修改地区信息
     * 
     * @param area 地区信息
     * @return 结果
     */
    public int updateArea(Area area);

    /**
     * 批量删除地区信息
     * 
     * @param areaIds 需要删除的地区信息主键集合
     * @return 结果
     */
    public int deleteAreaByAreaIds(Long[] areaIds);

    /**
     * 删除地区信息信息
     * 
     * @param areaId 地区信息主键
     * @return 结果
     */
    public int deleteAreaByAreaId(Long areaId);
}
