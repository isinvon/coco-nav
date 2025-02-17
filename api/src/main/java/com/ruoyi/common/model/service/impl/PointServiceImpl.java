package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.PointService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.PointMapper;
import com.ruoyi.common.model.domain.po.Point;
import com.ruoyi.common.model.service.custom.PointCustomService;
import org.springframework.stereotype.Service;

/**
 * 积分账户Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper,Point> implements PointCustomService, PointService
{
    @Autowired
    private PointMapper pointMapper;

    /**
     * 查询积分账户
     * 
     * @param pointId 积分账户主键
     * @return 积分账户
     */
    @Override
    public Point selectPointByPointId(Long pointId)
    {
        return pointMapper.selectPointByPointId(pointId);
    }

    /**
     * 查询积分账户列表
     * 
     * @param point 积分账户
     * @return 积分账户
     */
    @Override
    public List<Point> selectPointList(Point point)
    {
        return pointMapper.selectPointList(point);
    }

    /**
     * 新增积分账户
     * 
     * @param point 积分账户
     * @return 结果
     */
    @Override
    public int insertPoint(Point point)
    {
        point.setCreateTime(DateUtils.getNowDate());
        return pointMapper.insertPoint(point);
    }

    /**
     * 修改积分账户
     * 
     * @param point 积分账户
     * @return 结果
     */
    @Override
    public int updatePoint(Point point)
    {
        point.setUpdateTime(DateUtils.getNowDate());
        return pointMapper.updatePoint(point);
    }

    /**
     * 批量删除积分账户
     * 
     * @param pointIds 需要删除的积分账户主键
     * @return 结果
     */
    @Override
    public int deletePointByPointIds(Long[] pointIds)
    {
        return pointMapper.deletePointByPointIds(pointIds);
    }

    /**
     * 删除积分账户信息
     * 
     * @param pointId 积分账户主键
     * @return 结果
     */
    @Override
    public int deletePointByPointId(Long pointId)
    {
        return pointMapper.deletePointByPointId(pointId);
    }
}
