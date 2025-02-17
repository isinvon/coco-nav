package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.Point;

/**
 * 积分账户Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface PointCustomService
{
    /**
     * 查询积分账户
     * 
     * @param pointId 积分账户主键
     * @return 积分账户
     */
    public Point selectPointByPointId(Long pointId);

    /**
     * 查询积分账户列表
     * 
     * @param point 积分账户
     * @return 积分账户集合
     */
    public List<Point> selectPointList(Point point);

    /**
     * 新增积分账户
     * 
     * @param point 积分账户
     * @return 结果
     */
    public int insertPoint(Point point);

    /**
     * 修改积分账户
     * 
     * @param point 积分账户
     * @return 结果
     */
    public int updatePoint(Point point);

    /**
     * 批量删除积分账户
     * 
     * @param pointIds 需要删除的积分账户主键集合
     * @return 结果
     */
    public int deletePointByPointIds(Long[] pointIds);

    /**
     * 删除积分账户信息
     * 
     * @param pointId 积分账户主键
     * @return 结果
     */
    public int deletePointByPointId(Long pointId);
}
