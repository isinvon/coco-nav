package com.ruoyi.common.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.WebsiteInfoService;

import com.ruoyi.common.model.mapper.WebsiteInfoMapper;
import com.ruoyi.common.model.domain.po.WebsiteInfo;
import org.springframework.stereotype.Service;

/**
 * 网站信息Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class WebsiteInfoServiceImpl extends ServiceImpl<WebsiteInfoMapper, WebsiteInfo> implements WebsiteInfoService {
}
