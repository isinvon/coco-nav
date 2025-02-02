package com.ruoyi.project.admin.service.custom;

import java.util.List;
import com.ruoyi.project.admin.domain.po.InviteCode;

/**
 * 邀请码管理Service接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
public interface InviteCodeCustomService
{
    /**
     * 查询邀请码管理
     * 
     * @param inviteCodeId 邀请码管理主键
     * @return 邀请码管理
     */
    public InviteCode selectInviteCodeByInviteCodeId(Long inviteCodeId);

    /**
     * 查询邀请码管理列表
     * 
     * @param inviteCode 邀请码管理
     * @return 邀请码管理集合
     */
    public List<InviteCode> selectInviteCodeList(InviteCode inviteCode);

    /**
     * 新增邀请码管理
     * 
     * @param inviteCode 邀请码管理
     * @return 结果
     */
    public int insertInviteCode(InviteCode inviteCode);

    /**
     * 修改邀请码管理
     * 
     * @param inviteCode 邀请码管理
     * @return 结果
     */
    public int updateInviteCode(InviteCode inviteCode);

    /**
     * 批量删除邀请码管理
     * 
     * @param inviteCodeIds 需要删除的邀请码管理主键集合
     * @return 结果
     */
    public int deleteInviteCodeByInviteCodeIds(Long[] inviteCodeIds);

    /**
     * 删除邀请码管理信息
     * 
     * @param inviteCodeId 邀请码管理主键
     * @return 结果
     */
    public int deleteInviteCodeByInviteCodeId(Long inviteCodeId);
}
