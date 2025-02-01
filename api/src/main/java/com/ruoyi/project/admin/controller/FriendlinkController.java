package com.ruoyi.project.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.Friendlink;
import com.ruoyi.project.admin.service.FriendlinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 友情链接Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/friendlink")
public class FriendlinkController extends BaseController {
    @Resource
    private FriendlinkService friendlinkService;

    /**
     * 查询友情链接列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_LIST)
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Friendlink> list = friendlinkService.list();
        return getDataTable(list);
    }

    /**
     * 导出友情链接列表
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_EXPORT)
    @Log(title = "友情链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Friendlink> list = friendlinkService.list();
        ExcelUtil<Friendlink> util = new ExcelUtil<>(Friendlink.class);
        util.exportExcel(response, list, "友情链接数据");
    }

    /**
     * 获取友情链接详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_QUERY)
    @GetMapping(value = "/{friendlinkId}")
    public AjaxResult getInfo(@PathVariable("friendlinkId") Long friendlinkId) {
        return success(friendlinkService.getById(friendlinkId));
    }

    /**
     * 新增友情链接
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_ADD)
    @Log(title = "友情链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Friendlink friendlink) {
        return toAjax(friendlinkService.save(friendlink));
    }

    /**
     * 修改友情链接
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_EDIT)
    @Log(title = "友情链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Friendlink friendlink) {
        LambdaUpdateWrapper<Friendlink> uw = new LambdaUpdateWrapper<>();
        uw.eq(Friendlink::getId, friendlink.getId());
        return toAjax(friendlinkService.update(friendlink, uw));
    }

    /**
     * 删除友情链接
     */
    @CustomPermission(PermissionConstants.ADMIN_FRIENDLINK_REMOVE)
    @Log(title = "友情链接", businessType = BusinessType.DELETE)
    @DeleteMapping("/{friendlinkIds}")
    public AjaxResult remove(@PathVariable Long[] friendlinkIds) {
        List<Long> idList = Arrays.asList(friendlinkIds);
        return toAjax(friendlinkService.removeByIds(idList));
    }
}
