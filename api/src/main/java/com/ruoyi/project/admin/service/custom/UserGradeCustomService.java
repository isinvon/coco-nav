package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.UserGrade;

/**
 * 用户等级Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface UserGradeCustomService
{
    /**
     * 查询用户等级
     * 
     * @param userGradeId 用户等级主键
     * @return 用户等级
     */
    public UserGrade selectUserGradeByUserGradeId(Long userGradeId);

    /**
     * 查询用户等级列表
     * 
     * @param userGrade 用户等级
     * @return 用户等级集合
     */
    public List<UserGrade> selectUserGradeList(UserGrade userGrade);

    /**
     * 新增用户等级
     * 
     * @param userGrade 用户等级
     * @return 结果
     */
    public int insertUserGrade(UserGrade userGrade);

    /**
     * 修改用户等级
     * 
     * @param userGrade 用户等级
     * @return 结果
     */
    public int updateUserGrade(UserGrade userGrade);

    /**
     * 批量删除用户等级
     * 
     * @param userGradeIds 需要删除的用户等级主键集合
     * @return 结果
     */
    public int deleteUserGradeByUserGradeIds(Long[] userGradeIds);

    /**
     * 删除用户等级信息
     * 
     * @param userGradeId 用户等级主键
     * @return 结果
     */
    public int deleteUserGradeByUserGradeId(Long userGradeId);
}
