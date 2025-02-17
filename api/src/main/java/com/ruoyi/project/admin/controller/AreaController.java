package com.ruoyi.project.admin.controller;

import com.ruoyi.common.constant.PermissionConstants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.permission.CustomPermission;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.model.domain.po.Area;
import com.ruoyi.common.model.service.AreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 地区信息Controller
 *
 * @author sinvon
 * @date 2025-01-30
 */
@RestController
@RequestMapping("/admin/area")
public class AreaController extends BaseController {
    @Resource
    private AreaService areaService;

    /**
     * 查询地区信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_LIST)
    @GetMapping("/list")
    public TableDataInfo list(Area area) {
        startPage();
        List<Area> list = areaService.getAreaListByQueryCondition(area);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_EXPORT)
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        List<Area> list = areaService.list();
        ExcelUtil<Area> util = new ExcelUtil<Area>(Area.class);
        util.exportExcel(response, list, "地区信息数据");
    }

    /**
     * 获取地区信息详细信息
     */
    @CustomPermission(PermissionConstants.ADMIN_AREA_QUERY)
    @GetMapping(value = "/{areaId}")
    public AjaxResult getInfo(@PathVariable("areaId") Long areaId) {
        return success(areaService.getById(areaId));
    }

    // /**
    //  * 新增地区信息
    //  */
    // @CustomPermission(PermissionConstants.ADMIN_AREA_ADD)
    // @Log(title = "地区信息", businessType = BusinessType.INSERT)
    // @PostMapping
    // public AjaxResult add(@RequestBody Area area) {
    //     return toAjax(areaService.save(area));
    // }

    // /**
    //  * 修改地区信息
    //  */
    // @CustomPermission(PermissionConstants.ADMIN_AREA_EDIT)
    // @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    // @PutMapping
    // public AjaxResult edit(@RequestBody Area area) {
    //     return toAjax(areaService.updateById(area));
    // }

    // /**
    //  * 删除地区信息
    //  */
    // @CustomPermission(PermissionConstants.ADMIN_AREA_REMOVE)
    // @Log(title = "地区信息", businessType = BusinessType.DELETE)
    // @DeleteMapping("/{areaIds}")
    // public AjaxResult remove(@PathVariable Long[] areaIds) {
    //     List<Long> idList = Arrays.asList(areaIds);
    //     return toAjax(areaService.removeByIds(idList));
    // }

    /**
     * 获取类型
     */
    @GetMapping("/index")
    public AjaxResult index() {
        return success(Map.of(
                "areaDepthTypeList", Area.areaDepthTypeList // 地区深度类型
        ));
    }
}
