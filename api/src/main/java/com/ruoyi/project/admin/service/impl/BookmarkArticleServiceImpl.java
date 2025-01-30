package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.service.custom.BookmarkArticleCustomService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruoyi.project.admin.mapper.BookmarkArticleMapper;
import com.ruoyi.project.admin.domain.BookmarkArticle;
import org.springframework.stereotype.Service;

/**
 * 书签文章Service业务层处理
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Service
public class BookmarkArticleServiceImpl implements BookmarkArticleCustomService
{
    @Autowired
    private BookmarkArticleMapper bookmarkArticleMapper;

    /**
     * 查询书签文章
     * 
     * @param articleId 书签文章主键
     * @return 书签文章
     */
    @Override
    public BookmarkArticle selectBookmarkArticleByArticleId(Long articleId)
    {
        return bookmarkArticleMapper.selectBookmarkArticleByArticleId(articleId);
    }

    /**
     * 查询书签文章列表
     * 
     * @param bookmarkArticle 书签文章
     * @return 书签文章
     */
    @Override
    public List<BookmarkArticle> selectBookmarkArticleList(BookmarkArticle bookmarkArticle)
    {
        return bookmarkArticleMapper.selectBookmarkArticleList(bookmarkArticle);
    }

    /**
     * 新增书签文章
     * 
     * @param bookmarkArticle 书签文章
     * @return 结果
     */
    @Override
    public int insertBookmarkArticle(BookmarkArticle bookmarkArticle)
    {
        bookmarkArticle.setCreateTime(DateUtils.getNowDate());
        return bookmarkArticleMapper.insertBookmarkArticle(bookmarkArticle);
    }

    /**
     * 修改书签文章
     * 
     * @param bookmarkArticle 书签文章
     * @return 结果
     */
    @Override
    public int updateBookmarkArticle(BookmarkArticle bookmarkArticle)
    {
        bookmarkArticle.setUpdateTime(DateUtils.getNowDate());
        return bookmarkArticleMapper.updateBookmarkArticle(bookmarkArticle);
    }

    /**
     * 批量删除书签文章
     * 
     * @param articleIds 需要删除的书签文章主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkArticleByArticleIds(Long[] articleIds)
    {
        return bookmarkArticleMapper.deleteBookmarkArticleByArticleIds(articleIds);
    }

    /**
     * 删除书签文章信息
     * 
     * @param articleId 书签文章主键
     * @return 结果
     */
    @Override
    public int deleteBookmarkArticleByArticleId(Long articleId)
    {
        return bookmarkArticleMapper.deleteBookmarkArticleByArticleId(articleId);
    }
}
