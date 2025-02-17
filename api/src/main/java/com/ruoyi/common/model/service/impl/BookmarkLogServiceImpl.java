package com.ruoyi.common.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.BookmarkLogService;

import com.ruoyi.common.model.mapper.BookmarkLogMapper;
import com.ruoyi.common.model.domain.po.BookmarkLog;
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
