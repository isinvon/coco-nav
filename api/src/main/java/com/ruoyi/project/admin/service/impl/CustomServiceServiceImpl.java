package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.CustomServiceMapper;
import com.ruoyi.project.admin.domain.CustomService;
import com.ruoyi.project.admin.service.ICustomServiceCustomService;
import org.springframework.stereotype.Service;

/**
 * 客服信息Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class CustomServiceServiceImpl implements ICustomServiceCustomService
{
    @Autowired
    private CustomServiceMapper customServiceMapper;

    /**
     * 查询客服信息
     * 
     * @param customServiceId 客服信息主键
     * @return 客服信息
     */
    @Override
    public CustomService selectCustomServiceByCustomServiceId(Long customServiceId)
    {
        return customServiceMapper.selectCustomServiceByCustomServiceId(customServiceId);
    }

    /**
     * 查询客服信息列表
     * 
     * @param customService 客服信息
     * @return 客服信息
     */
    @Override
    public List<CustomService> selectCustomServiceList(CustomService customService)
    {
        return customServiceMapper.selectCustomServiceList(customService);
    }

    /**
     * 新增客服信息
     * 
     * @param customService 客服信息
     * @return 结果
     */
    @Override
    public int insertCustomService(CustomService customService)
    {
        customService.setCreateTime(DateUtils.getNowDate());
        return customServiceMapper.insertCustomService(customService);
    }

    /**
     * 修改客服信息
     * 
     * @param customService 客服信息
     * @return 结果
     */
    @Override
    public int updateCustomService(CustomService customService)
    {
        customService.setUpdateTime(DateUtils.getNowDate());
        return customServiceMapper.updateCustomService(customService);
    }

    /**
     * 批量删除客服信息
     * 
     * @param customServiceIds 需要删除的客服信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomServiceByCustomServiceIds(Long[] customServiceIds)
    {
        return customServiceMapper.deleteCustomServiceByCustomServiceIds(customServiceIds);
    }

    /**
     * 删除客服信息信息
     * 
     * @param customServiceId 客服信息主键
     * @return 结果
     */
    @Override
    public int deleteCustomServiceByCustomServiceId(Long customServiceId)
    {
        return customServiceMapper.deleteCustomServiceByCustomServiceId(customServiceId);
    }
}
