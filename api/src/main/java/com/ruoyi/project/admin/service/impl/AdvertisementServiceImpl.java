package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.AdvertisementAction;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.domain.AdvertisementLog;
import com.ruoyi.project.admin.mapper.AdvertisementMapper;
import com.ruoyi.project.admin.service.AdvertisementLogService;
import com.ruoyi.project.admin.service.AdvertisementService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 广告管理Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class AdvertisementServiceImpl extends ServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    @Resource
    private AdvertisementLogService advertisementLogService;

    /**
     * 获取广告列表并转换为 AdvertisementVo 类型
     *
     * @param advertisement 查询条件，包含标题、日期范围等字段
     * @return 广告列表（AdvertisementVo 类型）
     */
    @Override
    public List<Advertisement> getAdvertisementListByQueryCondition(Advertisement advertisement) {

        LambdaQueryWrapper<Advertisement> qw = new LambdaQueryWrapper<>(); // 调用查询方法获取广告数据

        // 标题条件查询
        if (!ObjectUtils.isEmpty(advertisement.getTitle())) {
            qw.like(Advertisement::getTitle, advertisement.getTitle());
        }

        // 状态条件查询
        if (!ObjectUtils.isEmpty(advertisement.getStatus())) {
            qw.eq(Advertisement::getStatus, advertisement.getStatus());
        }

        // 时间条件查询
        Date startTime = advertisement.getStartTime();
        Date endTime = advertisement.getEndTime();
        if (startTime != null && endTime != null) { // 添加时间范围条件，避免 NullPointerException
            qw.ge(Advertisement::getStartTime, startTime).le(Advertisement::getEndTime, endTime);
        }

        return list(qw);
    }

    /**
     * 添加广告
     *
     * @param advertisement 广告信息
     * @return
     */
    @Override
    public Boolean addAdvertisement(Advertisement advertisement) {
        boolean save = save(advertisement);

        if (save) {
            // 添加成功, 记录广告操作日志
            AdvertisementLog advertisementLog = new AdvertisementLog();
            advertisementLog.setAction(AdvertisementAction.ADD.getCode());
            advertisementLog.setAdvertisementId(advertisement.getId());
            advertisementLog.setOperatorId(SecurityUtils.getUserId());
            // 记录到 AdvertisementLog 表中
            advertisementLogService.save(advertisementLog);
        }

        return save;
    }

    @Override
    public Boolean updateAdvertisement(Advertisement advertisement) {
        boolean update = updateById(advertisement);

        if (update) {
            // 修改成功, 记录广告操作日志
            AdvertisementLog advertisementLog = new AdvertisementLog();
            advertisementLog.setAction(AdvertisementAction.EDIT.getCode());
            advertisementLog.setAdvertisementId(advertisement.getId());
            advertisementLog.setOperatorId(SecurityUtils.getUserId());
            // 记录到 AdvertisementLog 表中
            advertisementLogService.save(advertisementLog);
        }
        return update;
    }

}
