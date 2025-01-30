package com.ruoyi.project.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.Point;

import java.util.List;

/**
 * 积分账户Mapper接口
 *
 * @author sinvon
 * @date 2025-01-30
 */
public interface PointMapper extends BaseMapper<Point> {
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
     * 删除积分账户
     *
     * @param pointId 积分账户主键
     * @return 结果
     */
    public int deletePointByPointId(Long pointId);

    /**
     * 批量删除积分账户
     *
     * @param pointIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointByPointIds(Long[] pointIds);
}
