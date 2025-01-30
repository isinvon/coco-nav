package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.FriendlinkLogMapper;
import com.ruoyi.project.admin.domain.FriendlinkLog;
import com.ruoyi.project.admin.service.custom.FriendlinkLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 友链操作日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class FriendlinkLogServiceImpl implements FriendlinkLogCustomService
{
    @Autowired
    private FriendlinkLogMapper friendlinkLogMapper;

    /**
     * 查询友链操作日志
     * 
     * @param friendlinkLogId 友链操作日志主键
     * @return 友链操作日志
     */
    @Override
    public FriendlinkLog selectFriendlinkLogByFriendlinkLogId(Long friendlinkLogId)
    {
        return friendlinkLogMapper.selectFriendlinkLogByFriendlinkLogId(friendlinkLogId);
    }

    /**
     * 查询友链操作日志列表
     * 
     * @param friendlinkLog 友链操作日志
     * @return 友链操作日志
     */
    @Override
    public List<FriendlinkLog> selectFriendlinkLogList(FriendlinkLog friendlinkLog)
    {
        return friendlinkLogMapper.selectFriendlinkLogList(friendlinkLog);
    }

    /**
     * 新增友链操作日志
     * 
     * @param friendlinkLog 友链操作日志
     * @return 结果
     */
    @Override
    public int insertFriendlinkLog(FriendlinkLog friendlinkLog)
    {
        friendlinkLog.setCreateTime(DateUtils.getNowDate());
        return friendlinkLogMapper.insertFriendlinkLog(friendlinkLog);
    }

    /**
     * 修改友链操作日志
     * 
     * @param friendlinkLog 友链操作日志
     * @return 结果
     */
    @Override
    public int updateFriendlinkLog(FriendlinkLog friendlinkLog)
    {
        friendlinkLog.setUpdateTime(DateUtils.getNowDate());
        return friendlinkLogMapper.updateFriendlinkLog(friendlinkLog);
    }

    /**
     * 批量删除友链操作日志
     * 
     * @param friendlinkLogIds 需要删除的友链操作日志主键
     * @return 结果
     */
    @Override
    public int deleteFriendlinkLogByFriendlinkLogIds(Long[] friendlinkLogIds)
    {
        return friendlinkLogMapper.deleteFriendlinkLogByFriendlinkLogIds(friendlinkLogIds);
    }

    /**
     * 删除友链操作日志信息
     * 
     * @param friendlinkLogId 友链操作日志主键
     * @return 结果
     */
    @Override
    public int deleteFriendlinkLogByFriendlinkLogId(Long friendlinkLogId)
    {
        return friendlinkLogMapper.deleteFriendlinkLogByFriendlinkLogId(friendlinkLogId);
    }
}
