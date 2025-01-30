package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.UserLoginLog;

/**
 * 用户登录日志Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface UserLoginLogCustomService
{
    /**
     * 查询用户登录日志
     * 
     * @param userLoginLogId 用户登录日志主键
     * @return 用户登录日志
     */
    public UserLoginLog selectUserLoginLogByUserLoginLogId(Long userLoginLogId);

    /**
     * 查询用户登录日志列表
     * 
     * @param userLoginLog 用户登录日志
     * @return 用户登录日志集合
     */
    public List<UserLoginLog> selectUserLoginLogList(UserLoginLog userLoginLog);

    /**
     * 新增用户登录日志
     * 
     * @param userLoginLog 用户登录日志
     * @return 结果
     */
    public int insertUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 修改用户登录日志
     * 
     * @param userLoginLog 用户登录日志
     * @return 结果
     */
    public int updateUserLoginLog(UserLoginLog userLoginLog);

    /**
     * 批量删除用户登录日志
     * 
     * @param userLoginLogIds 需要删除的用户登录日志主键集合
     * @return 结果
     */
    public int deleteUserLoginLogByUserLoginLogIds(Long[] userLoginLogIds);

    /**
     * 删除用户登录日志信息
     * 
     * @param userLoginLogId 用户登录日志主键
     * @return 结果
     */
    public int deleteUserLoginLogByUserLoginLogId(Long userLoginLogId);
}
