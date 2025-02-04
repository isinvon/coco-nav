package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.BookmarkLogService;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkLogCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkLogMapper;
import com.ruoyi.project.admin.domain.po.BookmarkLog;
import org.springframework.stereotype.Service;

/**
 * 书签操作日志Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkLogServiceImpl extends ServiceImpl<BookmarkLogMapper, BookmarkLog> implements  BookmarkLogService {
}
