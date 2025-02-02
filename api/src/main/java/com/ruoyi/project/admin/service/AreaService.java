package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.Area;

import java.util.List;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface AreaService extends IService<Area> {
    /**
     * 根据查询条件获取地区信息
     *
     * @param area
     * @return
     */
    List<Area> getAreaListByQueryCondition(Area area);
}
