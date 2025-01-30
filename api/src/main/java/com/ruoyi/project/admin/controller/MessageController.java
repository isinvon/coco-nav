package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.framework.security.permission.CustomPermission;
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
import com.ruoyi.project.admin.domain.Message;
import com.ruoyi.project.admin.service.MessageCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 留言管理Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/message")
public class MessageController extends BaseController
{
    @Autowired
    private MessageCustomService messageCustomService;

    /**
     * 查询留言管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Message message)
    {
        startPage();
        List<Message> list = messageCustomService.selectMessageList(message);
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_EXPORT)
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Message message)
    {
        List<Message> list = messageCustomService.selectMessageList(message);
        ExcelUtil<Message> util = new ExcelUtil<Message>(Message.class);
        util.exportExcel(response, list, "留言管理数据");
    }

    /**
     * 获取留言管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_QUERY)
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return success(messageCustomService.selectMessageByMessageId(messageId));
    }

    /**
     * 新增留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_ADD)
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Message message)
    {
        return toAjax(messageCustomService.insertMessage(message));
    }

    /**
     * 修改留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_EDIT)
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Message message)
    {
        return toAjax(messageCustomService.updateMessage(message));
    }

    /**
     * 删除留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_REMOVE)
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(messageCustomService.deleteMessageByMessageIds(messageIds));
    }
}
