package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Vip;
import org.apache.ibatis.annotations.Mapper;

/**
 * VIP会员Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface VipMapper extends BaseMapper<Vip>
{
    /**
     * 查询VIP会员
     * 
     * @param vipId VIP会员主键
     * @return VIP会员
     */
    public Vip selectVipByVipId(Long vipId);

    /**
     * 查询VIP会员列表
     * 
     * @param vip VIP会员
     * @return VIP会员集合
     */
    public List<Vip> selectVipList(Vip vip);

    /**
     * 新增VIP会员
     * 
     * @param vip VIP会员
     * @return 结果
     */
    public int insertVip(Vip vip);

    /**
     * 修改VIP会员
     * 
     * @param vip VIP会员
     * @return 结果
     */
    public int updateVip(Vip vip);

    /**
     * 删除VIP会员
     * 
     * @param vipId VIP会员主键
     * @return 结果
     */
    public int deleteVipByVipId(Long vipId);

    /**
     * 批量删除VIP会员
     * 
     * @param vipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVipByVipIds(Long[] vipIds);
}
