package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.domain.po.Area;
import com.ruoyi.project.admin.mapper.AreaMapper;
import com.ruoyi.project.admin.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区信息Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Override
    public List<Area> getAreaListByQueryCondition(Area area) {

        LambdaQueryWrapper<Area> qw = new LambdaQueryWrapper<>();

        // 根据地区名称
        if (area.getName() != null) {
            qw.like(Area::getName, area.getName());
        }

        // 根据行政编码
        if (area.getId() != null) {
            qw.like(Area::getId, area.getId());
        }

        // 根据行政级别
        if (area.getDepth() != null) {
            qw.eq(Area::getDepth, area.getDepth());
        }

        return list(qw);
    }
}
