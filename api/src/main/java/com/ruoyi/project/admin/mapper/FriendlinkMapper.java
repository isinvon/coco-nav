package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Friendlink;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友情链接Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface FriendlinkMapper extends BaseMapper<Friendlink>
{
    /**
     * 查询友情链接
     * 
     * @param friendlinkId 友情链接主键
     * @return 友情链接
     */
    public Friendlink selectFriendlinkByFriendlinkId(Long friendlinkId);

    /**
     * 查询友情链接列表
     * 
     * @param friendlink 友情链接
     * @return 友情链接集合
     */
    public List<Friendlink> selectFriendlinkList(Friendlink friendlink);

    /**
     * 新增友情链接
     * 
     * @param friendlink 友情链接
     * @return 结果
     */
    public int insertFriendlink(Friendlink friendlink);

    /**
     * 修改友情链接
     * 
     * @param friendlink 友情链接
     * @return 结果
     */
    public int updateFriendlink(Friendlink friendlink);

    /**
     * 删除友情链接
     * 
     * @param friendlinkId 友情链接主键
     * @return 结果
     */
    public int deleteFriendlinkByFriendlinkId(Long friendlinkId);

    /**
     * 批量删除友情链接
     * 
     * @param friendlinkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFriendlinkByFriendlinkIds(Long[] friendlinkIds);
}
