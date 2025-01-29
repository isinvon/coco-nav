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
import com.ruoyi.project.admin.domain.Friendlink;
import com.ruoyi.project.admin.service.IFriendlinkCustomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 友情链接Controller
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/friendlink")
public class FriendlinkController extends BaseController
{
    @Autowired
    private IFriendlinkCustomService friendlinkCustomService;

    /**
     * 查询友情链接列表
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:list')")
    @GetMapping("/list")
    public TableDataInfo list(Friendlink friendlink)
    {
        startPage();
        List<Friendlink> list = friendlinkCustomService.selectFriendlinkList(friendlink);
        return getDataTable(list);
    }

    /**
     * 导出友情链接列表
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:export')")
    @Log(title = "友情链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Friendlink friendlink)
    {
        List<Friendlink> list = friendlinkCustomService.selectFriendlinkList(friendlink);
        ExcelUtil<Friendlink> util = new ExcelUtil<Friendlink>(Friendlink.class);
        util.exportExcel(response, list, "友情链接数据");
    }

    /**
     * 获取友情链接详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:query')")
    @GetMapping(value = "/{friendlinkId}")
    public AjaxResult getInfo(@PathVariable("friendlinkId") Long friendlinkId)
    {
        return success(friendlinkCustomService.selectFriendlinkByFriendlinkId(friendlinkId));
    }

    /**
     * 新增友情链接
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:add')")
    @Log(title = "友情链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Friendlink friendlink)
    {
        return toAjax(friendlinkCustomService.insertFriendlink(friendlink));
    }

    /**
     * 修改友情链接
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:edit')")
    @Log(title = "友情链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Friendlink friendlink)
    {
        return toAjax(friendlinkCustomService.updateFriendlink(friendlink));
    }

    /**
     * 删除友情链接
     */
    @PreAuthorize("@ss.hasPermi('admin:friendlink:remove')")
    @Log(title = "友情链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{friendlinkIds}")
    public AjaxResult remove(@PathVariable Long[] friendlinkIds)
    {
        return toAjax(friendlinkCustomService.deleteFriendlinkByFriendlinkIds(friendlinkIds));
    }
}
