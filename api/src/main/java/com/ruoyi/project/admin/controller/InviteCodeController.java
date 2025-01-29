package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.admin.domain.InviteCode;
import com.ruoyi.project.admin.service.IInviteCodeCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 邀请码管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/InviteCode")
public class InviteCodeController extends BaseController
{
    @Autowired
    private IInviteCodeCustomService inviteCodeCustomService;

    /**
     * 查询邀请码管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:list')")
    @GetMapping("/list")
    public TableDataInfo list(InviteCode inviteCode)
    {
        startPage();
        List<InviteCode> list = inviteCodeCustomService.selectInviteCodeList(inviteCode);
        return getDataTable(list);
    }

    /**
     * 导出邀请码管理列表
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:export')")
    @Log(title = "邀请码管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InviteCode inviteCode)
    {
        List<InviteCode> list = inviteCodeCustomService.selectInviteCodeList(inviteCode);
        ExcelUtil<InviteCode> util = new ExcelUtil<InviteCode>(InviteCode.class);
        util.exportExcel(response, list, "邀请码管理数据");
    }

    /**
     * 获取邀请码管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:query')")
    @GetMapping(value = "/{inviteCodeId}")
    public AjaxResult getInfo(@PathVariable("inviteCodeId") Long inviteCodeId)
    {
        return success(inviteCodeCustomService.selectInviteCodeByInviteCodeId(inviteCodeId));
    }

    /**
     * 新增邀请码管理
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:add')")
    @Log(title = "邀请码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InviteCode inviteCode)
    {
        return toAjax(inviteCodeCustomService.insertInviteCode(inviteCode));
    }

    /**
     * 修改邀请码管理
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:edit')")
    @Log(title = "邀请码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InviteCode inviteCode)
    {
        return toAjax(inviteCodeCustomService.updateInviteCode(inviteCode));
    }

    /**
     * 删除邀请码管理
     */
    @PreAuthorize("@ss.hasPermi('admin:InviteCode:remove')")
    @Log(title = "邀请码管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inviteCodeIds}")
    public AjaxResult remove(@PathVariable Long[] inviteCodeIds)
    {
        return toAjax(inviteCodeCustomService.deleteInviteCodeByInviteCodeIds(inviteCodeIds));
    }
}
