package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.PointLogMapper;
import com.ruoyi.project.admin.domain.PointLog;
import com.ruoyi.project.admin.service.custom.PointLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 积分流水Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class PointLogServiceImpl implements PointLogCustomService
{
    @Autowired
    private PointLogMapper pointLogMapper;

    /**
     * 查询积分流水
     * 
     * @param pointLogId 积分流水主键
     * @return 积分流水
     */
    @Override
    public PointLog selectPointLogByPointLogId(Long pointLogId)
    {
        return pointLogMapper.selectPointLogByPointLogId(pointLogId);
    }

    /**
     * 查询积分流水列表
     * 
     * @param pointLog 积分流水
     * @return 积分流水
     */
    @Override
    public List<PointLog> selectPointLogList(PointLog pointLog)
    {
        return pointLogMapper.selectPointLogList(pointLog);
    }

    /**
     * 新增积分流水
     * 
     * @param pointLog 积分流水
     * @return 结果
     */
    @Override
    public int insertPointLog(PointLog pointLog)
    {
        pointLog.setCreateTime(DateUtils.getNowDate());
        return pointLogMapper.insertPointLog(pointLog);
    }

    /**
     * 修改积分流水
     * 
     * @param pointLog 积分流水
     * @return 结果
     */
    @Override
    public int updatePointLog(PointLog pointLog)
    {
        pointLog.setUpdateTime(DateUtils.getNowDate());
        return pointLogMapper.updatePointLog(pointLog);
    }

    /**
     * 批量删除积分流水
     * 
     * @param pointLogIds 需要删除的积分流水主键
     * @return 结果
     */
    @Override
    public int deletePointLogByPointLogIds(Long[] pointLogIds)
    {
        return pointLogMapper.deletePointLogByPointLogIds(pointLogIds);
    }

    /**
     * 删除积分流水信息
     * 
     * @param pointLogId 积分流水主键
     * @return 结果
     */
    @Override
    public int deletePointLogByPointLogId(Long pointLogId)
    {
        return pointLogMapper.deletePointLogByPointLogId(pointLogId);
    }
}
