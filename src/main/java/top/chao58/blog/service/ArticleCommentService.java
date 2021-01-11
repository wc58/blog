package top.chao58.blog.service;

import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.ArticleCommentVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;

import java.util.List;

public interface ArticleCommentService {


    Integer queryCommentSizeByArticleId(int articleId);

    List<ArticleCommentVo> getCommentByArticleId(Integer id);

    void getAdminArticleCommentPage(Integer page, Integer limit, CommentQo commentQo, ResponseVo<AdminCommentVo> responseVo);

    void updateAdminArticleComment(Integer id, String content);

    void deleteAdminArticleCommentById(Integer id);

    Integer getArticleCommentSize();
}