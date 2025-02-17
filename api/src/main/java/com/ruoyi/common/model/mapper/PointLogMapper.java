package com.ruoyi.common.model.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.common.model.domain.po.PointLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分流水Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface PointLogMapper extends BaseMapper<PointLog>
{
    /**
     * 查询积分流水
     * 
     * @param pointLogId 积分流水主键
     * @return 积分流水
     */
    public PointLog selectPointLogByPointLogId(Long pointLogId);

    /**
     * 查询积分流水列表
     * 
     * @param pointLog 积分流水
     * @return 积分流水集合
     */
    public List<PointLog> selectPointLogList(PointLog pointLog);

    /**
     * 新增积分流水
     * 
     * @param pointLog 积分流水
     * @return 结果
     */
    public int insertPointLog(PointLog pointLog);

    /**
     * 修改积分流水
     * 
     * @param pointLog 积分流水
     * @return 结果
     */
    public int updatePointLog(PointLog pointLog);

    /**
     * 删除积分流水
     * 
     * @param pointLogId 积分流水主键
     * @return 结果
     */
    public int deletePointLogByPointLogId(Long pointLogId);

    /**
     * 批量删除积分流水
     * 
     * @param pointLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointLogByPointLogIds(Long[] pointLogIds);
}
