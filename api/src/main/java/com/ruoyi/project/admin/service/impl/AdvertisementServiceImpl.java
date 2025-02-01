package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.domain.vo.AdvertisementVo;
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
     * @param advertisementVo 查询条件，包含标题、日期范围等字段
     * @return 广告列表（AdvertisementVo 类型）
     */
    @Override
    public List<Advertisement> getAdvertisementList(AdvertisementVo advertisementVo) {

        LambdaQueryWrapper<Advertisement> qw = new LambdaQueryWrapper<>(); // 调用查询方法获取广告数据

        // 标题条件查询
        if (!ObjectUtils.isEmpty(advertisementVo.getTitle())) {
            qw.like(Advertisement::getTitle, advertisementVo.getTitle());
        }

        // 状态条件查询
        if (!ObjectUtils.isEmpty(advertisementVo.getStatus())) {
            qw.eq(Advertisement::getStatus, advertisementVo.getStatus());
        }

        // 时间条件查询
        Date startTime = null, endTime = null;

        if (!ObjectUtils.isEmpty(advertisementVo.getDateRange()) && advertisementVo.getDateRange().length == 2) { // 先检查是否有 dateRange，避免空指针异常
            startTime = advertisementVo.getDateRange()[0];
            endTime = advertisementVo.getDateRange()[1];
        }


        if (startTime != null && endTime != null) { // 添加时间范围条件，避免 NullPointerException
            qw.ge(Advertisement::getStartTime, startTime).le(Advertisement::getEndTime, endTime);
        }

        return list(qw);
    }
}
