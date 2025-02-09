package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.WebsiteInfoService;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.WebsiteInfoMapper;
import com.ruoyi.project.admin.domain.po.WebsiteInfo;
import com.ruoyi.project.admin.service.custom.WebsiteInfoCustomService;
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
