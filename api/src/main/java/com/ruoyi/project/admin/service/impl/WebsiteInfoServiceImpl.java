package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.WebsiteInfoMapper;
import com.ruoyi.project.admin.domain.WebsiteInfo;
import com.ruoyi.project.admin.service.custom.WebsiteInfoCustomService;
import org.springframework.stereotype.Service;

/**
 * 网站信息Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class WebsiteInfoServiceImpl implements WebsiteInfoCustomService
{
    @Autowired
    private WebsiteInfoMapper websiteInfoMapper;

    /**
     * 查询网站信息
     * 
     * @param websiteInfoId 网站信息主键
     * @return 网站信息
     */
    @Override
    public WebsiteInfo selectWebsiteInfoByWebsiteInfoId(Long websiteInfoId)
    {
        return websiteInfoMapper.selectWebsiteInfoByWebsiteInfoId(websiteInfoId);
    }

    /**
     * 查询网站信息列表
     * 
     * @param websiteInfo 网站信息
     * @return 网站信息
     */
    @Override
    public List<WebsiteInfo> selectWebsiteInfoList(WebsiteInfo websiteInfo)
    {
        return websiteInfoMapper.selectWebsiteInfoList(websiteInfo);
    }

    /**
     * 新增网站信息
     * 
     * @param websiteInfo 网站信息
     * @return 结果
     */
    @Override
    public int insertWebsiteInfo(WebsiteInfo websiteInfo)
    {
        websiteInfo.setCreateTime(DateUtils.getNowDate());
        return websiteInfoMapper.insertWebsiteInfo(websiteInfo);
    }

    /**
     * 修改网站信息
     * 
     * @param websiteInfo 网站信息
     * @return 结果
     */
    @Override
    public int updateWebsiteInfo(WebsiteInfo websiteInfo)
    {
        websiteInfo.setUpdateTime(DateUtils.getNowDate());
        return websiteInfoMapper.updateWebsiteInfo(websiteInfo);
    }

    /**
     * 批量删除网站信息
     * 
     * @param websiteInfoIds 需要删除的网站信息主键
     * @return 结果
     */
    @Override
    public int deleteWebsiteInfoByWebsiteInfoIds(Long[] websiteInfoIds)
    {
        return websiteInfoMapper.deleteWebsiteInfoByWebsiteInfoIds(websiteInfoIds);
    }

    /**
     * 删除网站信息信息
     * 
     * @param websiteInfoId 网站信息主键
     * @return 结果
     */
    @Override
    public int deleteWebsiteInfoByWebsiteInfoId(Long websiteInfoId)
    {
        return websiteInfoMapper.deleteWebsiteInfoByWebsiteInfoId(websiteInfoId);
    }
}
