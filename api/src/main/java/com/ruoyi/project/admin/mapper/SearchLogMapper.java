package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.SearchLog;

/**
 * 搜索日志Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface SearchLogMapper extends BaseMapper<SearchLog>
{
    /**
     * 查询搜索日志
     * 
     * @param searchLogId 搜索日志主键
     * @return 搜索日志
     */
    public SearchLog selectSearchLogBySearchLogId(Long searchLogId);

    /**
     * 查询搜索日志列表
     * 
     * @param searchLog 搜索日志
     * @return 搜索日志集合
     */
    public List<SearchLog> selectSearchLogList(SearchLog searchLog);

    /**
     * 新增搜索日志
     * 
     * @param searchLog 搜索日志
     * @return 结果
     */
    public int insertSearchLog(SearchLog searchLog);

    /**
     * 修改搜索日志
     * 
     * @param searchLog 搜索日志
     * @return 结果
     */
    public int updateSearchLog(SearchLog searchLog);

    /**
     * 删除搜索日志
     * 
     * @param searchLogId 搜索日志主键
     * @return 结果
     */
    public int deleteSearchLogBySearchLogId(Long searchLogId);

    /**
     * 批量删除搜索日志
     * 
     * @param searchLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSearchLogBySearchLogIds(Long[] searchLogIds);
}
