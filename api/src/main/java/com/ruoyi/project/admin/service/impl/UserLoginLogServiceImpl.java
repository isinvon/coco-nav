package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.UserLoginLogMapper;
import com.ruoyi.project.admin.domain.UserLoginLog;
import com.ruoyi.project.admin.service.IUserLoginLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 用户登录日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class UserLoginLogServiceImpl implements IUserLoginLogCustomService
{
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    /**
     * 查询用户登录日志
     * 
     * @param userLoginLogId 用户登录日志主键
     * @return 用户登录日志
     */
    @Override
    public UserLoginLog selectUserLoginLogByUserLoginLogId(Long userLoginLogId)
    {
        return userLoginLogMapper.selectUserLoginLogByUserLoginLogId(userLoginLogId);
    }

    /**
     * 查询用户登录日志列表
     * 
     * @param userLoginLog 用户登录日志
     * @return 用户登录日志
     */
    @Override
    public List<UserLoginLog> selectUserLoginLogList(UserLoginLog userLoginLog)
    {
        return userLoginLogMapper.selectUserLoginLogList(userLoginLog);
    }

    /**
     * 新增用户登录日志
     * 
     * @param userLoginLog 用户登录日志
     * @return 结果
     */
    @Override
    public int insertUserLoginLog(UserLoginLog userLoginLog)
    {
        userLoginLog.setCreateTime(DateUtils.getNowDate());
        return userLoginLogMapper.insertUserLoginLog(userLoginLog);
    }

    /**
     * 修改用户登录日志
     * 
     * @param userLoginLog 用户登录日志
     * @return 结果
     */
    @Override
    public int updateUserLoginLog(UserLoginLog userLoginLog)
    {
        userLoginLog.setUpdateTime(DateUtils.getNowDate());
        return userLoginLogMapper.updateUserLoginLog(userLoginLog);
    }

    /**
     * 批量删除用户登录日志
     * 
     * @param userLoginLogIds 需要删除的用户登录日志主键
     * @return 结果
     */
    @Override
    public int deleteUserLoginLogByUserLoginLogIds(Long[] userLoginLogIds)
    {
        return userLoginLogMapper.deleteUserLoginLogByUserLoginLogIds(userLoginLogIds);
    }

    /**
     * 删除用户登录日志信息
     * 
     * @param userLoginLogId 用户登录日志主键
     * @return 结果
     */
    @Override
    public int deleteUserLoginLogByUserLoginLogId(Long userLoginLogId)
    {
        return userLoginLogMapper.deleteUserLoginLogByUserLoginLogId(userLoginLogId);
    }
}
