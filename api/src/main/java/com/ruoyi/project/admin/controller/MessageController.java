package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.po.Message;
import com.ruoyi.project.admin.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 留言管理Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/message")
public class MessageController extends BaseController {
    @Resource
    private MessageService messageService;

    /**
     * 查询留言管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Message> list = messageService.list();
        return getDataTable(list);
    }

    /**
     * 导出留言管理列表
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_EXPORT)
    @Log(title = "留言管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Message> list = messageService.list();
        ExcelUtil<Message> util = new ExcelUtil<>(Message.class);
        util.exportExcel(response, list, "留言管理数据");
    }

    /**
     * 获取留言管理详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_QUERY)
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId) {
        return success(messageService.getById(messageId));
    }

    /**
     * 新增留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_ADD)
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Message message) {
        return toAjax(messageService.save(message));
    }

    /**
     * 修改留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_EDIT)
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Message message) {
        return toAjax(messageService.updateById(message));
    }

    /**
     * 删除留言管理
     */
    @CustomPermission(PermissionConstants.ADMIN_MESSAGE_REMOVE)
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds) {
        List<Long> idList = Arrays.asList(messageIds);
        return toAjax(messageService.removeByIds(idList));
    }
}
