package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.NoticeUserService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.NoticeUserMapper;
import com.ruoyi.project.admin.domain.NoticeUser;
import com.ruoyi.project.admin.service.custom.NoticeUserCustomService;
import org.springframework.stereotype.Service;

/**
 * 通知用户关系Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class NoticeUserServiceImpl extends ServiceImpl<NoticeUserMapper,NoticeUser> implements NoticeUserCustomService, NoticeUserService
{
    @Autowired
    private NoticeUserMapper noticeUserMapper;

    /**
     * 查询通知用户关系
     * 
     * @param relationId 通知用户关系主键
     * @return 通知用户关系
     */
    @Override
    public NoticeUser selectNoticeUserByRelationId(Long relationId)
    {
        return noticeUserMapper.selectNoticeUserByRelationId(relationId);
    }

    /**
     * 查询通知用户关系列表
     * 
     * @param noticeUser 通知用户关系
     * @return 通知用户关系
     */
    @Override
    public List<NoticeUser> selectNoticeUserList(NoticeUser noticeUser)
    {
        return noticeUserMapper.selectNoticeUserList(noticeUser);
    }

    /**
     * 新增通知用户关系
     * 
     * @param noticeUser 通知用户关系
     * @return 结果
     */
    @Override
    public int insertNoticeUser(NoticeUser noticeUser)
    {
        noticeUser.setCreateTime(DateUtils.getNowDate());
        return noticeUserMapper.insertNoticeUser(noticeUser);
    }

    /**
     * 修改通知用户关系
     * 
     * @param noticeUser 通知用户关系
     * @return 结果
     */
    @Override
    public int updateNoticeUser(NoticeUser noticeUser)
    {
        noticeUser.setUpdateTime(DateUtils.getNowDate());
        return noticeUserMapper.updateNoticeUser(noticeUser);
    }

    /**
     * 批量删除通知用户关系
     * 
     * @param relationIds 需要删除的通知用户关系主键
     * @return 结果
     */
    @Override
    public int deleteNoticeUserByRelationIds(Long[] relationIds)
    {
        return noticeUserMapper.deleteNoticeUserByRelationIds(relationIds);
    }

    /**
     * 删除通知用户关系信息
     * 
     * @param relationId 通知用户关系主键
     * @return 结果
     */
    @Override
    public int deleteNoticeUserByRelationId(Long relationId)
    {
        return noticeUserMapper.deleteNoticeUserByRelationId(relationId);
    }
}
