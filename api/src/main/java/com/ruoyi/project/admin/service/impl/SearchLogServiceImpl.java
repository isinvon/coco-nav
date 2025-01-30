package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.SearchLogMapper;
import com.ruoyi.project.admin.domain.SearchLog;
import com.ruoyi.project.admin.service.custom.SearchLogCustomService;
import org.springframework.stereotype.Service;

/**
 * 搜索日志Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class SearchLogServiceImpl implements SearchLogCustomService
{
    @Autowired
    private SearchLogMapper searchLogMapper;

    /**
     * 查询搜索日志
     * 
     * @param searchLogId 搜索日志主键
     * @return 搜索日志
     */
    @Override
    public SearchLog selectSearchLogBySearchLogId(Long searchLogId)
    {
        return searchLogMapper.selectSearchLogBySearchLogId(searchLogId);
    }

    /**
     * 查询搜索日志列表
     * 
     * @param searchLog 搜索日志
     * @return 搜索日志
     */
    @Override
    public List<SearchLog> selectSearchLogList(SearchLog searchLog)
    {
        return searchLogMapper.selectSearchLogList(searchLog);
    }

    /**
     * 新增搜索日志
     * 
     * @param searchLog 搜索日志
     * @return 结果
     */
    @Override
    public int insertSearchLog(SearchLog searchLog)
    {
        searchLog.setCreateTime(DateUtils.getNowDate());
        return searchLogMapper.insertSearchLog(searchLog);
    }

    /**
     * 修改搜索日志
     * 
     * @param searchLog 搜索日志
     * @return 结果
     */
    @Override
    public int updateSearchLog(SearchLog searchLog)
    {
        searchLog.setUpdateTime(DateUtils.getNowDate());
        return searchLogMapper.updateSearchLog(searchLog);
    }

    /**
     * 批量删除搜索日志
     * 
     * @param searchLogIds 需要删除的搜索日志主键
     * @return 结果
     */
    @Override
    public int deleteSearchLogBySearchLogIds(Long[] searchLogIds)
    {
        return searchLogMapper.deleteSearchLogBySearchLogIds(searchLogIds);
    }

    /**
     * 删除搜索日志信息
     * 
     * @param searchLogId 搜索日志主键
     * @return 结果
     */
    @Override
    public int deleteSearchLogBySearchLogId(Long searchLogId)
    {
        return searchLogMapper.deleteSearchLogBySearchLogId(searchLogId);
    }
}
