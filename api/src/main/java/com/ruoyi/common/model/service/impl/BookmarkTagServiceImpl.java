package com.ruoyi.common.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.model.service.BookmarkTagService;

import java.util.List;

import com.ruoyi.common.model.mapper.BookmarkTagMapper;
import com.ruoyi.common.model.domain.po.BookmarkTag;
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

    @Override
    public List<String> getBookmarkTagNameList() {
        LambdaQueryWrapper<BookmarkTag> qw = new LambdaQueryWrapper<>();
        qw.select(BookmarkTag::getTagName);
        return list(qw).stream().map(BookmarkTag::getTagName).toList();
    }
}
