package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.Feedback;

/**
 * 用户反馈Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface FeedbackCustomService
{
    /**
     * 查询用户反馈
     * 
     * @param feedbackId 用户反馈主键
     * @return 用户反馈
     */
    public Feedback selectFeedbackByFeedbackId(Long feedbackId);

    /**
     * 查询用户反馈列表
     * 
     * @param feedback 用户反馈
     * @return 用户反馈集合
     */
    public List<Feedback> selectFeedbackList(Feedback feedback);

    /**
     * 新增用户反馈
     * 
     * @param feedback 用户反馈
     * @return 结果
     */
    public int insertFeedback(Feedback feedback);

    /**
     * 修改用户反馈
     * 
     * @param feedback 用户反馈
     * @return 结果
     */
    public int updateFeedback(Feedback feedback);

    /**
     * 批量删除用户反馈
     * 
     * @param feedbackIds 需要删除的用户反馈主键集合
     * @return 结果
     */
    public int deleteFeedbackByFeedbackIds(Long[] feedbackIds);

    /**
     * 删除用户反馈信息
     * 
     * @param feedbackId 用户反馈主键
     * @return 结果
     */
    public int deleteFeedbackByFeedbackId(Long feedbackId);
}
