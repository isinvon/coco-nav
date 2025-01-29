package com.ruoyi.project.admin.service;

import java.util.List;
import com.ruoyi.project.admin.domain.FriendlinkLog;

/**
 * 友链操作日志Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface IFriendlinkLogCustomService
{
    /**
     * 查询友链操作日志
     * 
     * @param friendlinkLogId 友链操作日志主键
     * @return 友链操作日志
     */
    public FriendlinkLog selectFriendlinkLogByFriendlinkLogId(Long friendlinkLogId);

    /**
     * 查询友链操作日志列表
     * 
     * @param friendlinkLog 友链操作日志
     * @return 友链操作日志集合
     */
    public List<FriendlinkLog> selectFriendlinkLogList(FriendlinkLog friendlinkLog);

    /**
     * 新增友链操作日志
     * 
     * @param friendlinkLog 友链操作日志
     * @return 结果
     */
    public int insertFriendlinkLog(FriendlinkLog friendlinkLog);

    /**
     * 修改友链操作日志
     * 
     * @param friendlinkLog 友链操作日志
     * @return 结果
     */
    public int updateFriendlinkLog(FriendlinkLog friendlinkLog);

    /**
     * 批量删除友链操作日志
     * 
     * @param friendlinkLogIds 需要删除的友链操作日志主键集合
     * @return 结果
     */
    public int deleteFriendlinkLogByFriendlinkLogIds(Long[] friendlinkLogIds);

    /**
     * 删除友链操作日志信息
     * 
     * @param friendlinkLogId 友链操作日志主键
     * @return 结果
     */
    public int deleteFriendlinkLogByFriendlinkLogId(Long friendlinkLogId);
}
