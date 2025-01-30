package com.ruoyi.project.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.InviteCodeService;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.InviteCodeMapper;
import com.ruoyi.project.admin.domain.InviteCode;
import com.ruoyi.project.admin.service.custom.InviteCodeCustomService;
import org.springframework.stereotype.Service;

/**
 * 邀请码管理Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class InviteCodeServiceImpl extends ServiceImpl<InviteCodeMapper,InviteCode> implements InviteCodeCustomService, InviteCodeService
{
    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    /**
     * 查询邀请码管理
     * 
     * @param inviteCodeId 邀请码管理主键
     * @return 邀请码管理
     */
    @Override
    public InviteCode selectInviteCodeByInviteCodeId(Long inviteCodeId)
    {
        return inviteCodeMapper.selectInviteCodeByInviteCodeId(inviteCodeId);
    }

    /**
     * 查询邀请码管理列表
     * 
     * @param inviteCode 邀请码管理
     * @return 邀请码管理
     */
    @Override
    public List<InviteCode> selectInviteCodeList(InviteCode inviteCode)
    {
        return inviteCodeMapper.selectInviteCodeList(inviteCode);
    }

    /**
     * 新增邀请码管理
     * 
     * @param inviteCode 邀请码管理
     * @return 结果
     */
    @Override
    public int insertInviteCode(InviteCode inviteCode)
    {
        inviteCode.setCreateTime(DateUtils.getNowDate());
        return inviteCodeMapper.insertInviteCode(inviteCode);
    }

    /**
     * 修改邀请码管理
     * 
     * @param inviteCode 邀请码管理
     * @return 结果
     */
    @Override
    public int updateInviteCode(InviteCode inviteCode)
    {
        return inviteCodeMapper.updateInviteCode(inviteCode);
    }

    /**
     * 批量删除邀请码管理
     * 
     * @param inviteCodeIds 需要删除的邀请码管理主键
     * @return 结果
     */
    @Override
    public int deleteInviteCodeByInviteCodeIds(Long[] inviteCodeIds)
    {
        return inviteCodeMapper.deleteInviteCodeByInviteCodeIds(inviteCodeIds);
    }

    /**
     * 删除邀请码管理信息
     * 
     * @param inviteCodeId 邀请码管理主键
     * @return 结果
     */
    @Override
    public int deleteInviteCodeByInviteCodeId(Long inviteCodeId)
    {
        return inviteCodeMapper.deleteInviteCodeByInviteCodeId(inviteCodeId);
    }
}
