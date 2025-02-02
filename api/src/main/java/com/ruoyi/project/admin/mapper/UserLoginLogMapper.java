package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.po.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog>
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
     * 删除用户登录日志
     * 
     * @param userLoginLogId 用户登录日志主键
     * @return 结果
     */
    public int deleteUserLoginLogByUserLoginLogId(Long userLoginLogId);

    /**
     * 批量删除用户登录日志
     * 
     * @param userLoginLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserLoginLogByUserLoginLogIds(Long[] userLoginLogIds);
}
