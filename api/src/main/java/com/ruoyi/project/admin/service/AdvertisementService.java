package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.po.Advertisement;

import java.util.List;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface AdvertisementService extends IService<Advertisement> {
    List<Advertisement> getAdvertisementListByQueryCondition(Advertisement advertisement);

    Boolean addAdvertisement(Advertisement advertisement);

    Boolean updateAdvertisement(Advertisement advertisement);

    Boolean deleteAdvertisement(List<Long> idList);
}
