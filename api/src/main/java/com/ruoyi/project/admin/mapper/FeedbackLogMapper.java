package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.FeedbackLog;

/**
 * 反馈处理日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface FeedbackLogMapper 
{
    /**
     * 查询反馈处理日志
     * 
     * @param feedbackLogId 反馈处理日志主键
     * @return 反馈处理日志
     */
    public FeedbackLog selectFeedbackLogByFeedbackLogId(Long feedbackLogId);

    /**
     * 查询反馈处理日志列表
     * 
     * @param feedbackLog 反馈处理日志
     * @return 反馈处理日志集合
     */
    public List<FeedbackLog> selectFeedbackLogList(FeedbackLog feedbackLog);

    /**
     * 新增反馈处理日志
     * 
     * @param feedbackLog 反馈处理日志
     * @return 结果
     */
    public int insertFeedbackLog(FeedbackLog feedbackLog);

    /**
     * 修改反馈处理日志
     * 
     * @param feedbackLog 反馈处理日志
     * @return 结果
     */
    public int updateFeedbackLog(FeedbackLog feedbackLog);

    /**
     * 删除反馈处理日志
     * 
     * @param feedbackLogId 反馈处理日志主键
     * @return 结果
     */
    public int deleteFeedbackLogByFeedbackLogId(Long feedbackLogId);

    /**
     * 批量删除反馈处理日志
     * 
     * @param feedbackLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFeedbackLogByFeedbackLogIds(Long[] feedbackLogIds);
}
