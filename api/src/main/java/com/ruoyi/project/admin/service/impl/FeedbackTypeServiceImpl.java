package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.FeedbackTypeService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.FeedbackTypeMapper;
import com.ruoyi.project.admin.domain.po.FeedbackType;
import com.ruoyi.project.admin.service.custom.FeedbackTypeCustomService;
import org.springframework.stereotype.Service;

/**
 * 反馈类型Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class FeedbackTypeServiceImpl extends ServiceImpl<FeedbackTypeMapper,FeedbackType> implements FeedbackTypeCustomService, FeedbackTypeService
{
    @Autowired
    private FeedbackTypeMapper feedbackTypeMapper;

    /**
     * 查询反馈类型
     * 
     * @param feedbackTypeId 反馈类型主键
     * @return 反馈类型
     */
    @Override
    public FeedbackType selectFeedbackTypeByFeedbackTypeId(Long feedbackTypeId)
    {
        return feedbackTypeMapper.selectFeedbackTypeByFeedbackTypeId(feedbackTypeId);
    }

    /**
     * 查询反馈类型列表
     * 
     * @param feedbackType 反馈类型
     * @return 反馈类型
     */
    @Override
    public List<FeedbackType> selectFeedbackTypeList(FeedbackType feedbackType)
    {
        return feedbackTypeMapper.selectFeedbackTypeList(feedbackType);
    }

    /**
     * 新增反馈类型
     * 
     * @param feedbackType 反馈类型
     * @return 结果
     */
    @Override
    public int insertFeedbackType(FeedbackType feedbackType)
    {
        feedbackType.setCreateTime(DateUtils.getNowDate());
        return feedbackTypeMapper.insertFeedbackType(feedbackType);
    }

    /**
     * 修改反馈类型
     * 
     * @param feedbackType 反馈类型
     * @return 结果
     */
    @Override
    public int updateFeedbackType(FeedbackType feedbackType)
    {
        feedbackType.setUpdateTime(DateUtils.getNowDate());
        return feedbackTypeMapper.updateFeedbackType(feedbackType);
    }

    /**
     * 批量删除反馈类型
     * 
     * @param feedbackTypeIds 需要删除的反馈类型主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackTypeByFeedbackTypeIds(Long[] feedbackTypeIds)
    {
        return feedbackTypeMapper.deleteFeedbackTypeByFeedbackTypeIds(feedbackTypeIds);
    }

    /**
     * 删除反馈类型信息
     * 
     * @param feedbackTypeId 反馈类型主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackTypeByFeedbackTypeId(Long feedbackTypeId)
    {
        return feedbackTypeMapper.deleteFeedbackTypeByFeedbackTypeId(feedbackTypeId);
    }
}
