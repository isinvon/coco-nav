package com.ruoyi.common.model.service.custom;

import java.util.List;
import com.ruoyi.common.model.domain.po.BrowseHistory;

/**
 * 用户浏览历史Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface BrowseHistoryCustomService
{
    /**
     * 查询用户浏览历史
     * 
     * @param browseHistoryId 用户浏览历史主键
     * @return 用户浏览历史
     */
    public BrowseHistory selectBrowseHistoryByBrowseHistoryId(Long browseHistoryId);

    /**
     * 查询用户浏览历史列表
     * 
     * @param browseHistory 用户浏览历史
     * @return 用户浏览历史集合
     */
    public List<BrowseHistory> selectBrowseHistoryList(BrowseHistory browseHistory);

    /**
     * 新增用户浏览历史
     * 
     * @param browseHistory 用户浏览历史
     * @return 结果
     */
    public int insertBrowseHistory(BrowseHistory browseHistory);

    /**
     * 修改用户浏览历史
     * 
     * @param browseHistory 用户浏览历史
     * @return 结果
     */
    public int updateBrowseHistory(BrowseHistory browseHistory);

    /**
     * 批量删除用户浏览历史
     * 
     * @param browseHistoryIds 需要删除的用户浏览历史主键集合
     * @return 结果
     */
    public int deleteBrowseHistoryByBrowseHistoryIds(Long[] browseHistoryIds);

    /**
     * 删除用户浏览历史信息
     * 
     * @param browseHistoryId 用户浏览历史主键
     * @return 结果
     */
    public int deleteBrowseHistoryByBrowseHistoryId(Long browseHistoryId);
}
