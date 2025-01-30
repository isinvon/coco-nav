package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.WebsiteInfo;

/**
 * 网站信息Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface WebsiteInfoMapper extends BaseMapper<WebsiteInfo>
{
    /**
     * 查询网站信息
     * 
     * @param websiteInfoId 网站信息主键
     * @return 网站信息
     */
    public WebsiteInfo selectWebsiteInfoByWebsiteInfoId(Long websiteInfoId);

    /**
     * 查询网站信息列表
     * 
     * @param websiteInfo 网站信息
     * @return 网站信息集合
     */
    public List<WebsiteInfo> selectWebsiteInfoList(WebsiteInfo websiteInfo);

    /**
     * 新增网站信息
     * 
     * @param websiteInfo 网站信息
     * @return 结果
     */
    public int insertWebsiteInfo(WebsiteInfo websiteInfo);

    /**
     * 修改网站信息
     * 
     * @param websiteInfo 网站信息
     * @return 结果
     */
    public int updateWebsiteInfo(WebsiteInfo websiteInfo);

    /**
     * 删除网站信息
     * 
     * @param websiteInfoId 网站信息主键
     * @return 结果
     */
    public int deleteWebsiteInfoByWebsiteInfoId(Long websiteInfoId);

    /**
     * 批量删除网站信息
     * 
     * @param websiteInfoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWebsiteInfoByWebsiteInfoIds(Long[] websiteInfoIds);
}
