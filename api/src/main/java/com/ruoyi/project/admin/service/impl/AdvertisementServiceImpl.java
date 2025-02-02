package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.mapper.AdvertisementMapper;
import com.ruoyi.project.admin.service.AdvertisementService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    /**
     * 获取广告列表并转换为 AdvertisementVo 类型
     *
     * @param advertisement 查询条件，包含标题、日期范围等字段
     * @return 广告列表（AdvertisementVo 类型）
     */
    @Override
    public List<Advertisement> getAdvertisementList(Advertisement advertisement) {

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
}
