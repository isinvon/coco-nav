package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.domain.po.Bookmark;
import com.ruoyi.project.admin.mapper.BookmarkMapper;
import com.ruoyi.project.admin.service.BookmarkService;
import com.ruoyi.project.admin.service.custom.BookmarkCustomService;
import org.springframework.stereotype.Service;

/**
 * 书签管理Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkServiceImpl extends ServiceImpl<BookmarkMapper, Bookmark> implements BookmarkService {
}
