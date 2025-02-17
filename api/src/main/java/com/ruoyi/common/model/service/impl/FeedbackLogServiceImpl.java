package com.ruoyi.common.model.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.FeedbackLogService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.common.model.mapper.FeedbackLogMapper;
import com.ruoyi.common.model.domain.po.FeedbackLog;
import com.ruoyi.common.model.service.custom.FeedbackLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 反馈处理日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class FeedbackLogServiceImpl extends ServiceImpl<FeedbackLogMapper,FeedbackLog> implements FeedbackLogCustomService, FeedbackLogService
{
    @Autowired
    private FeedbackLogMapper feedbackLogMapper;

    /**
     * 查询反馈处理日志
     * 
     * @param feedbackLogId 反馈处理日志主键
     * @return 反馈处理日志
     */
    @Override
    public FeedbackLog selectFeedbackLogByFeedbackLogId(Long feedbackLogId)
    {
        return feedbackLogMapper.selectFeedbackLogByFeedbackLogId(feedbackLogId);
    }

    /**
     * 查询反馈处理日志列表
     * 
     * @param feedbackLog 反馈处理日志
     * @return 反馈处理日志
     */
    @Override
    public List<FeedbackLog> selectFeedbackLogList(FeedbackLog feedbackLog)
    {
        return feedbackLogMapper.selectFeedbackLogList(feedbackLog);
    }

    /**
     * 新增反馈处理日志
     * 
     * @param feedbackLog 反馈处理日志
     * @return 结果
     */
    @Override
    public int insertFeedbackLog(FeedbackLog feedbackLog)
    {
        feedbackLog.setCreateTime(DateUtils.getNowDate());
        return feedbackLogMapper.insertFeedbackLog(feedbackLog);
    }

    /**
     * 修改反馈处理日志
     * 
     * @param feedbackLog 反馈处理日志
     * @return 结果
     */
    @Override
    public int updateFeedbackLog(FeedbackLog feedbackLog)
    {
        feedbackLog.setUpdateTime(DateUtils.getNowDate());
        return feedbackLogMapper.updateFeedbackLog(feedbackLog);
    }

    /**
     * 批量删除反馈处理日志
     * 
     * @param feedbackLogIds 需要删除的反馈处理日志主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackLogByFeedbackLogIds(Long[] feedbackLogIds)
    {
        return feedbackLogMapper.deleteFeedbackLogByFeedbackLogIds(feedbackLogIds);
    }

    /**
     * 删除反馈处理日志信息
     * 
     * @param feedbackLogId 反馈处理日志主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackLogByFeedbackLogId(Long feedbackLogId)
    {
        return feedbackLogMapper.deleteFeedbackLogByFeedbackLogId(feedbackLogId);
    }
}
