package com.ruoyi.project.admin.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import com.ruoyi.project.admin.domain.BookmarkArticle;
import org.apache.ibatis.annotations.Mapper;

/**
 * 书签文章Mapper接口
 * 
 * @author sinvon
 * @date 2025-01-30
 */
@Mapper
public interface BookmarkArticleMapper extends BaseMapper<BookmarkArticle>
{
    /**
     * 查询书签文章
     * 
     * @param articleId 书签文章主键
     * @return 书签文章
     */
    public BookmarkArticle selectBookmarkArticleByArticleId(Long articleId);

    /**
     * 查询书签文章列表
     * 
     * @param bookmarkArticle 书签文章
     * @return 书签文章集合
     */
    public List<BookmarkArticle> selectBookmarkArticleList(BookmarkArticle bookmarkArticle);

    /**
     * 新增书签文章
     * 
     * @param bookmarkArticle 书签文章
     * @return 结果
     */
    public int insertBookmarkArticle(BookmarkArticle bookmarkArticle);

    /**
     * 修改书签文章
     * 
     * @param bookmarkArticle 书签文章
     * @return 结果
     */
    public int updateBookmarkArticle(BookmarkArticle bookmarkArticle);

    /**
     * 删除书签文章
     * 
     * @param articleId 书签文章主键
     * @return 结果
     */
    public int deleteBookmarkArticleByArticleId(Long articleId);

    /**
     * 批量删除书签文章
     * 
     * @param articleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookmarkArticleByArticleIds(Long[] articleIds);
}
