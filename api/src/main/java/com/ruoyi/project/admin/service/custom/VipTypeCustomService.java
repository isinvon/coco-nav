package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.po.VipType;

/**
 * VIP类型Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface VipTypeCustomService
{
    /**
     * 查询VIP类型
     * 
     * @param vipTypeId VIP类型主键
     * @return VIP类型
     */
    public VipType selectVipTypeByVipTypeId(Long vipTypeId);

    /**
     * 查询VIP类型列表
     * 
     * @param vipType VIP类型
     * @return VIP类型集合
     */
    public List<VipType> selectVipTypeList(VipType vipType);

    /**
     * 新增VIP类型
     * 
     * @param vipType VIP类型
     * @return 结果
     */
    public int insertVipType(VipType vipType);

    /**
     * 修改VIP类型
     * 
     * @param vipType VIP类型
     * @return 结果
     */
    public int updateVipType(VipType vipType);

    /**
     * 批量删除VIP类型
     * 
     * @param vipTypeIds 需要删除的VIP类型主键集合
     * @return 结果
     */
    public int deleteVipTypeByVipTypeIds(Long[] vipTypeIds);

    /**
     * 删除VIP类型信息
     * 
     * @param vipTypeId VIP类型主键
     * @return 结果
     */
    public int deleteVipTypeByVipTypeId(Long vipTypeId);
}
