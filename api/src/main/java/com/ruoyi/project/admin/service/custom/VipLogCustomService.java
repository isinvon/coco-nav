package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.po.VipLog;

/**
 * VIP操作日志Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface VipLogCustomService
{
    /**
     * 查询VIP操作日志
     * 
     * @param vipLogId VIP操作日志主键
     * @return VIP操作日志
     */
    public VipLog selectVipLogByVipLogId(Long vipLogId);

    /**
     * 查询VIP操作日志列表
     * 
     * @param vipLog VIP操作日志
     * @return VIP操作日志集合
     */
    public List<VipLog> selectVipLogList(VipLog vipLog);

    /**
     * 新增VIP操作日志
     * 
     * @param vipLog VIP操作日志
     * @return 结果
     */
    public int insertVipLog(VipLog vipLog);

    /**
     * 修改VIP操作日志
     * 
     * @param vipLog VIP操作日志
     * @return 结果
     */
    public int updateVipLog(VipLog vipLog);

    /**
     * 批量删除VIP操作日志
     * 
     * @param vipLogIds 需要删除的VIP操作日志主键集合
     * @return 结果
     */
    public int deleteVipLogByVipLogIds(Long[] vipLogIds);

    /**
     * 删除VIP操作日志信息
     * 
     * @param vipLogId VIP操作日志主键
     * @return 结果
     */
    public int deleteVipLogByVipLogId(Long vipLogId);
}
