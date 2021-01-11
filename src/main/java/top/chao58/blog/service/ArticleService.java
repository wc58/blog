package top.chao58.blog.service;

import top.chao58.blog.entity.dto.ArticleDto;
import top.chao58.blog.entity.qo.ArticleQo;
import top.chao58.blog.entity.vo.*;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ArticleService {
    List<HotArticleVo> getHotArticle(int size);

    List<ArticleListVo> getArticlePage(Integer currentPage, Integer showSize);

    List<TopArticleVo> getTopArticle();

    ReadArticleVo getArticleById(Integer id);

    Integer getTotal();

    void addComment(String articleId, String userId, String pid, String content);

    void increaseFlow(Integer id);

    void getAdminArticlePage(ResponseVo<AdminArticleVo> responseVo, Integer page, Integer limit, ArticleQo articleQo, int i);

    void updateTopById(Integer id, int isTop);

    void updateHiddenById(Integer id, Boolean isHidden);

    void updateDataById(ArticleDto articleDto);

    void recycleArticleById(Integer id);

    void restoreArticleById(Integer id);

    void deleteArticleById(ResponseVo<AdminArticleVo> responseVo, Integer id);

    Integer addArticle(ArticleDto articleDto, HttpSession session);

    Integer updateArticle(ArticleDto articleDto, HttpSession session);

    AdminArticleVo getAdminArticleById(Integer id);

    Integer getArticleLoginStatusById(Integer id);

    List<ArticleListVo> getArticlePageByCondition(Integer currentPage, Integer showSize, List<Integer> articleIds, String searchTxt);

    Integer getTotalByCondition(Integer labelId, String searchTxt);

    Integer getArticleSize();

    Integer getFlowSize();

    Integer getArticleTopSize();
}
