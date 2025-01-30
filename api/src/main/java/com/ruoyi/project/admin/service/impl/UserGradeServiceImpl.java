package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.UserGradeMapper;
import com.ruoyi.project.admin.domain.UserGrade;
import com.ruoyi.project.admin.service.custom.UserGradeCustomService;
import org.springframework.stereotype.Service;

/**
 * 用户等级Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class UserGradeServiceImpl implements UserGradeCustomService
{
    @Autowired
    private UserGradeMapper userGradeMapper;

    /**
     * 查询用户等级
     * 
     * @param userGradeId 用户等级主键
     * @return 用户等级
     */
    @Override
    public UserGrade selectUserGradeByUserGradeId(Long userGradeId)
    {
        return userGradeMapper.selectUserGradeByUserGradeId(userGradeId);
    }

    /**
     * 查询用户等级列表
     * 
     * @param userGrade 用户等级
     * @return 用户等级
     */
    @Override
    public List<UserGrade> selectUserGradeList(UserGrade userGrade)
    {
        return userGradeMapper.selectUserGradeList(userGrade);
    }

    /**
     * 新增用户等级
     * 
     * @param userGrade 用户等级
     * @return 结果
     */
    @Override
    public int insertUserGrade(UserGrade userGrade)
    {
        userGrade.setCreateTime(DateUtils.getNowDate());
        return userGradeMapper.insertUserGrade(userGrade);
    }

    /**
     * 修改用户等级
     * 
     * @param userGrade 用户等级
     * @return 结果
     */
    @Override
    public int updateUserGrade(UserGrade userGrade)
    {
        userGrade.setUpdateTime(DateUtils.getNowDate());
        return userGradeMapper.updateUserGrade(userGrade);
    }

    /**
     * 批量删除用户等级
     * 
     * @param userGradeIds 需要删除的用户等级主键
     * @return 结果
     */
    @Override
    public int deleteUserGradeByUserGradeIds(Long[] userGradeIds)
    {
        return userGradeMapper.deleteUserGradeByUserGradeIds(userGradeIds);
    }

    /**
     * 删除用户等级信息
     * 
     * @param userGradeId 用户等级主键
     * @return 结果
     */
    @Override
    public int deleteUserGradeByUserGradeId(Long userGradeId)
    {
        return userGradeMapper.deleteUserGradeByUserGradeId(userGradeId);
    }
}
