package top.chao58.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.dto.ArticleDto;
import top.chao58.blog.entity.po.ArticleComment;
import top.chao58.blog.entity.po.ArticleCore;
import top.chao58.blog.entity.po.ArticleOther;
import top.chao58.blog.entity.qo.ArticleQo;
import top.chao58.blog.entity.vo.*;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;

import java.util.Date;
import java.util.List;


public interface ArticleCoreDao {

    List<HotArticleVo> queryHotArticle(int size);

    List<ArticleListVo> queryArticleList(@Param("currentPage") Integer currentPage, @Param("showSize") Integer showSize);

    List<ArticleListVo> queryArticleListByCondition(@Param("currentPage") Integer currentPage, @Param("showSize") Integer showSize, @Param("articleIds") List<Integer> articleIds, @Param("searchTxt") String searchTxt);

    Integer queryTotalByCondition(@Param("articleIds") List<Integer> articleIds, @Param("searchTxt") String searchTxt);

    List<TopArticleVo> queryTopArticle();

    ReadArticleVo queryArticleById(Integer id);

    Integer queryTotal();

    void insertComment(ArticleComment articleComment);

    void increaseFlow(Integer id);

    List<AdminArticleVo> getAdminArticlePage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("articleQo") ArticleQo articleQo, @Param("recycle") int recycle);

    int getAdminArticleTotal(@Param("articleQo") ArticleQo articleQo, @Param("recycle") int recycle);

    void updateTopById(@Param("id") Integer id, @Param("topWeight") Integer topWeight);

    void updateHiddenById(@Param("id") Integer id, @Param("isHidden") int isHidden);

    void updateOtherDataById(@Param("articleDto") ArticleDto articleDto);

    void updateCoreDataById(@Param("articleDto") ArticleDto articleDto);

    void recycleArticleById(@Param("id") Integer id, @Param("deleteTime") Date deleteTime);

    void restoreArticleById(Integer id);

    void deleteCoreArticleById(Integer id);

    int deleteOtherArticleById(Integer id);

    void addCore(@Param("core") ArticleCore core);

    void addLabel(@Param("articleId") Integer articleId, @Param("labelId") Integer labelId);

    void addOther(@Param("other") ArticleOther other);

    void deleteLabelArticleById(@Param("id") Integer id);

    void updateCore(@Param("core") ArticleCore core);

    void updateOther(@Param("other") ArticleOther other);

    void deleteLabel(@Param("id") Integer id);

    AdminArticleVo getAdminArticleById(@Param("id") Integer id);

    Integer getArticleLoginStatusById(@Param("id") Integer id);


    Integer getArticleSize();

    Integer getFlowSize();

    Integer getArticleTopSize();

}