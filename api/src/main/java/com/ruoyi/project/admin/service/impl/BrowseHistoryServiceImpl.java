package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.BrowseHistoryService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BrowseHistoryMapper;
import com.ruoyi.project.admin.domain.po.BrowseHistory;
import com.ruoyi.project.admin.service.custom.BrowseHistoryCustomService;
import org.springframework.stereotype.Service;

/**
 * 用户浏览历史Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BrowseHistoryServiceImpl extends ServiceImpl<BrowseHistoryMapper,BrowseHistory> implements BrowseHistoryCustomService, BrowseHistoryService
{
    @Autowired
    private BrowseHistoryMapper browseHistoryMapper;

    /**
     * 查询用户浏览历史
     * 
     * @param browseHistoryId 用户浏览历史主键
     * @return 用户浏览历史
     */
    @Override
    public BrowseHistory selectBrowseHistoryByBrowseHistoryId(Long browseHistoryId)
    {
        return browseHistoryMapper.selectBrowseHistoryByBrowseHistoryId(browseHistoryId);
    }

    /**
     * 查询用户浏览历史列表
     * 
     * @param browseHistory 用户浏览历史
     * @return 用户浏览历史
     */
    @Override
    public List<BrowseHistory> selectBrowseHistoryList(BrowseHistory browseHistory)
    {
        return browseHistoryMapper.selectBrowseHistoryList(browseHistory);
    }

    /**
     * 新增用户浏览历史
     * 
     * @param browseHistory 用户浏览历史
     * @return 结果
     */
    @Override
    public int insertBrowseHistory(BrowseHistory browseHistory)
    {
        browseHistory.setCreateTime(DateUtils.getNowDate());
        return browseHistoryMapper.insertBrowseHistory(browseHistory);
    }

    /**
     * 修改用户浏览历史
     * 
     * @param browseHistory 用户浏览历史
     * @return 结果
     */
    @Override
    public int updateBrowseHistory(BrowseHistory browseHistory)
    {
        browseHistory.setUpdateTime(DateUtils.getNowDate());
        return browseHistoryMapper.updateBrowseHistory(browseHistory);
    }

    /**
     * 批量删除用户浏览历史
     * 
     * @param browseHistoryIds 需要删除的用户浏览历史主键
     * @return 结果
     */
    @Override
    public int deleteBrowseHistoryByBrowseHistoryIds(Long[] browseHistoryIds)
    {
        return browseHistoryMapper.deleteBrowseHistoryByBrowseHistoryIds(browseHistoryIds);
    }

    /**
     * 删除用户浏览历史信息
     * 
     * @param browseHistoryId 用户浏览历史主键
     * @return 结果
     */
    @Override
    public int deleteBrowseHistoryByBrowseHistoryId(Long browseHistoryId)
    {
        return browseHistoryMapper.deleteBrowseHistoryByBrowseHistoryId(browseHistoryId);
    }
}
