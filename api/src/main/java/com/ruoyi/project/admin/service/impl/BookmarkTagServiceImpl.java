package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.admin.service.BookmarkTagService;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkTagCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkTagMapper;
import com.ruoyi.project.admin.domain.po.BookmarkTag;
import org.springframework.stereotype.Service;

/**
 * 书签标签Service业务层处理
 *
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkTagServiceImpl extends ServiceImpl<BookmarkTagMapper, BookmarkTag> implements BookmarkTagService {

    @Override
    public List<BookmarkTag> getBookmarkTagListByQueryCondition(BookmarkTag bookmarkTag) {

        LambdaQueryWrapper<BookmarkTag> qw = new LambdaQueryWrapper<>();

        // 通过标签名称查询
        if (bookmarkTag.getTagName() != null) {
            qw.like(BookmarkTag::getTagName, bookmarkTag.getTagName());
        }

        return list(qw);
    }
}
