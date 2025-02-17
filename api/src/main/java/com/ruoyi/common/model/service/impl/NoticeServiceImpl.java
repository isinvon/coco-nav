package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.NoticeService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.NoticeMapper;
import com.ruoyi.common.model.domain.po.Notice;
import com.ruoyi.common.model.service.custom.NoticeCustomService;
import org.springframework.stereotype.Service;

/**
 * 通知管理Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeCustomService, NoticeService
{
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询通知管理
     * 
     * @param noticeId 通知管理主键
     * @return 通知管理
     */
    @Override
    public Notice selectNoticeByNoticeId(Long noticeId)
    {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }

    /**
     * 查询通知管理列表
     * 
     * @param notice 通知管理
     * @return 通知管理
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增通知管理
     * 
     * @param notice 通知管理
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改通知管理
     * 
     * @param notice 通知管理
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        notice.setUpdateTime(DateUtils.getNowDate());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除通知管理
     * 
     * @param noticeIds 需要删除的通知管理主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除通知管理信息
     * 
     * @param noticeId 通知管理主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeId(Long noticeId)
    {
        return noticeMapper.deleteNoticeByNoticeId(noticeId);
    }
}
