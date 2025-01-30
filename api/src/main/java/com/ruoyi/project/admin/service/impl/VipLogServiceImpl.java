package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.VipLogService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.VipLogMapper;
import com.ruoyi.project.admin.domain.VipLog;
import com.ruoyi.project.admin.service.custom.VipLogCustomService;
import org.springframework.stereotype.Service;

/**
 * VIP操作日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class VipLogServiceImpl extends ServiceImpl<VipLogMapper,VipLog> implements VipLogCustomService, VipLogService
{
    @Autowired
    private VipLogMapper vipLogMapper;

    /**
     * 查询VIP操作日志
     * 
     * @param vipLogId VIP操作日志主键
     * @return VIP操作日志
     */
    @Override
    public VipLog selectVipLogByVipLogId(Long vipLogId)
    {
        return vipLogMapper.selectVipLogByVipLogId(vipLogId);
    }

    /**
     * 查询VIP操作日志列表
     * 
     * @param vipLog VIP操作日志
     * @return VIP操作日志
     */
    @Override
    public List<VipLog> selectVipLogList(VipLog vipLog)
    {
        return vipLogMapper.selectVipLogList(vipLog);
    }

    /**
     * 新增VIP操作日志
     * 
     * @param vipLog VIP操作日志
     * @return 结果
     */
    @Override
    public int insertVipLog(VipLog vipLog)
    {
        vipLog.setCreateTime(DateUtils.getNowDate());
        return vipLogMapper.insertVipLog(vipLog);
    }

    /**
     * 修改VIP操作日志
     * 
     * @param vipLog VIP操作日志
     * @return 结果
     */
    @Override
    public int updateVipLog(VipLog vipLog)
    {
        vipLog.setUpdateTime(DateUtils.getNowDate());
        return vipLogMapper.updateVipLog(vipLog);
    }

    /**
     * 批量删除VIP操作日志
     * 
     * @param vipLogIds 需要删除的VIP操作日志主键
     * @return 结果
     */
    @Override
    public int deleteVipLogByVipLogIds(Long[] vipLogIds)
    {
        return vipLogMapper.deleteVipLogByVipLogIds(vipLogIds);
    }

    /**
     * 删除VIP操作日志信息
     * 
     * @param vipLogId VIP操作日志主键
     * @return 结果
     */
    @Override
    public int deleteVipLogByVipLogId(Long vipLogId)
    {
        return vipLogMapper.deleteVipLogByVipLogId(vipLogId);
    }
}
