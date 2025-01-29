package com.ruoyi.project.admin.service;

import java.util.List;
import com.ruoyi.project.admin.domain.CustomService;

/**
 * 客服信息Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface ICustomServiceCustomService
{
    /**
     * 查询客服信息
     * 
     * @param customServiceId 客服信息主键
     * @return 客服信息
     */
    public CustomService selectCustomServiceByCustomServiceId(Long customServiceId);

    /**
     * 查询客服信息列表
     * 
     * @param customService 客服信息
     * @return 客服信息集合
     */
    public List<CustomService> selectCustomServiceList(CustomService customService);

    /**
     * 新增客服信息
     * 
     * @param customService 客服信息
     * @return 结果
     */
    public int insertCustomService(CustomService customService);

    /**
     * 修改客服信息
     * 
     * @param customService 客服信息
     * @return 结果
     */
    public int updateCustomService(CustomService customService);

    /**
     * 批量删除客服信息
     * 
     * @param customServiceIds 需要删除的客服信息主键集合
     * @return 结果
     */
    public int deleteCustomServiceByCustomServiceIds(Long[] customServiceIds);

    /**
     * 删除客服信息信息
     * 
     * @param customServiceId 客服信息主键
     * @return 结果
     */
    public int deleteCustomServiceByCustomServiceId(Long customServiceId);
}
