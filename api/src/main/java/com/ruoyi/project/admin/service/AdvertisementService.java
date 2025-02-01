package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.Advertisement;
import com.ruoyi.project.admin.domain.vo.AdvertisementVo;

import java.util.Date;
import java.util.List;

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface AdvertisementService extends IService<Advertisement> {
    List<Advertisement> getAdvertisementList(AdvertisementVo advertisementVo);
}
