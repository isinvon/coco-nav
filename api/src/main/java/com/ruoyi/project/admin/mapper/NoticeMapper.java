package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Notice;

/**
 * 通知管理Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface NoticeMapper extends BaseMapper<Notice>
{
    /**
     * 查询通知管理
     * 
     * @param noticeId 通知管理主键
     * @return 通知管理
     */
    public Notice selectNoticeByNoticeId(Long noticeId);

    /**
     * 查询通知管理列表
     * 
     * @param notice 通知管理
     * @return 通知管理集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增通知管理
     * 
     * @param notice 通知管理
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改通知管理
     * 
     * @param notice 通知管理
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 删除通知管理
     * 
     * @param noticeId 通知管理主键
     * @return 结果
     */
    public int deleteNoticeByNoticeId(Long noticeId);

    /**
     * 批量删除通知管理
     * 
     * @param noticeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoticeByNoticeIds(Long[] noticeIds);
}
