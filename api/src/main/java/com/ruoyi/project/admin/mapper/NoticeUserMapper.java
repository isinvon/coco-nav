package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.NoticeUser;

/**
 * 通知用户关系Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface NoticeUserMapper extends BaseMapper<NoticeUser>
{
    /**
     * 查询通知用户关系
     * 
     * @param relationId 通知用户关系主键
     * @return 通知用户关系
     */
    public NoticeUser selectNoticeUserByRelationId(Long relationId);

    /**
     * 查询通知用户关系列表
     * 
     * @param noticeUser 通知用户关系
     * @return 通知用户关系集合
     */
    public List<NoticeUser> selectNoticeUserList(NoticeUser noticeUser);

    /**
     * 新增通知用户关系
     * 
     * @param noticeUser 通知用户关系
     * @return 结果
     */
    public int insertNoticeUser(NoticeUser noticeUser);

    /**
     * 修改通知用户关系
     * 
     * @param noticeUser 通知用户关系
     * @return 结果
     */
    public int updateNoticeUser(NoticeUser noticeUser);

    /**
     * 删除通知用户关系
     * 
     * @param relationId 通知用户关系主键
     * @return 结果
     */
    public int deleteNoticeUserByRelationId(Long relationId);

    /**
     * 批量删除通知用户关系
     * 
     * @param relationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoticeUserByRelationIds(Long[] relationIds);
}
