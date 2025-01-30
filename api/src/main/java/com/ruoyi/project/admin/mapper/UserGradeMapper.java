package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.UserGrade;

/**
 * 用户等级Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface UserGradeMapper extends BaseMapper<UserGrade>
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
     * 删除用户等级
     * 
     * @param userGradeId 用户等级主键
     * @return 结果
     */
    public int deleteUserGradeByUserGradeId(Long userGradeId);

    /**
     * 批量删除用户等级
     * 
     * @param userGradeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserGradeByUserGradeIds(Long[] userGradeIds);
}
