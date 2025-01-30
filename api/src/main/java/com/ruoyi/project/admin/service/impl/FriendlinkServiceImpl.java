package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.FriendlinkMapper;
import com.ruoyi.project.admin.domain.Friendlink;
import com.ruoyi.project.admin.service.custom.FriendlinkCustomService;
import org.springframework.stereotype.Service;

/**
 * 友情链接Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class FriendlinkServiceImpl implements FriendlinkCustomService
{
    @Autowired
    private FriendlinkMapper friendlinkMapper;

    /**
     * 查询友情链接
     * 
     * @param friendlinkId 友情链接主键
     * @return 友情链接
     */
    @Override
    public Friendlink selectFriendlinkByFriendlinkId(Long friendlinkId)
    {
        return friendlinkMapper.selectFriendlinkByFriendlinkId(friendlinkId);
    }

    /**
     * 查询友情链接列表
     * 
     * @param friendlink 友情链接
     * @return 友情链接
     */
    @Override
    public List<Friendlink> selectFriendlinkList(Friendlink friendlink)
    {
        return friendlinkMapper.selectFriendlinkList(friendlink);
    }

    /**
     * 新增友情链接
     * 
     * @param friendlink 友情链接
     * @return 结果
     */
    @Override
    public int insertFriendlink(Friendlink friendlink)
    {
        friendlink.setCreateTime(DateUtils.getNowDate());
        return friendlinkMapper.insertFriendlink(friendlink);
    }

    /**
     * 修改友情链接
     * 
     * @param friendlink 友情链接
     * @return 结果
     */
    @Override
    public int updateFriendlink(Friendlink friendlink)
    {
        friendlink.setUpdateTime(DateUtils.getNowDate());
        return friendlinkMapper.updateFriendlink(friendlink);
    }

    /**
     * 批量删除友情链接
     * 
     * @param friendlinkIds 需要删除的友情链接主键
     * @return 结果
     */
    @Override
    public int deleteFriendlinkByFriendlinkIds(Long[] friendlinkIds)
    {
        return friendlinkMapper.deleteFriendlinkByFriendlinkIds(friendlinkIds);
    }

    /**
     * 删除友情链接信息
     * 
     * @param friendlinkId 友情链接主键
     * @return 结果
     */
    @Override
    public int deleteFriendlinkByFriendlinkId(Long friendlinkId)
    {
        return friendlinkMapper.deleteFriendlinkByFriendlinkId(friendlinkId);
    }
}
