package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.ArticleCommentVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;

import java.util.List;

public interface ArticleCommentDao {
    Integer queryCommentSizeByArticleId(int articleId);

    List<ArticleCommentVo> queryCommentByArticleId(@Param("articleId") Integer articleId, @Param("pid") Integer pid);

    int getAdminArticleCommentTotal(@Param("commentQo") CommentQo commentQo);

    List<AdminCommentVo> getAdminArticleCommentPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("commentQo") CommentQo commentQo);

    void updateAdminArticleComment(@Param("id") Integer id, @Param("content") String content);

    void deleteAdminArticleCommentById(@Param("id") Integer id);

    List<Integer> getAdminArticleCommentsByPid(@Param("pid") Integer pid);

    Integer getArticleCommentSize();


}