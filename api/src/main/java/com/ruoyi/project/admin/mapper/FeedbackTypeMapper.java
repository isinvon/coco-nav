package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.FeedbackType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反馈类型Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface FeedbackTypeMapper extends BaseMapper<FeedbackType>
{
    /**
     * 查询反馈类型
     * 
     * @param feedbackTypeId 反馈类型主键
     * @return 反馈类型
     */
    public FeedbackType selectFeedbackTypeByFeedbackTypeId(Long feedbackTypeId);

    /**
     * 查询反馈类型列表
     * 
     * @param feedbackType 反馈类型
     * @return 反馈类型集合
     */
    public List<FeedbackType> selectFeedbackTypeList(FeedbackType feedbackType);

    /**
     * 新增反馈类型
     * 
     * @param feedbackType 反馈类型
     * @return 结果
     */
    public int insertFeedbackType(FeedbackType feedbackType);

    /**
     * 修改反馈类型
     * 
     * @param feedbackType 反馈类型
     * @return 结果
     */
    public int updateFeedbackType(FeedbackType feedbackType);

    /**
     * 删除反馈类型
     * 
     * @param feedbackTypeId 反馈类型主键
     * @return 结果
     */
    public int deleteFeedbackTypeByFeedbackTypeId(Long feedbackTypeId);

    /**
     * 批量删除反馈类型
     * 
     * @param feedbackTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFeedbackTypeByFeedbackTypeIds(Long[] feedbackTypeIds);
}
