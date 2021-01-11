package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.ArticleCommentDao;
import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.ArticleCommentVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;
import top.chao58.blog.service.ArticleCommentService;

import javax.annotation.Resource;
import java.util.List;

@Service("articleCommentService")
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Resource
    private ArticleCommentDao articleCommentDao;

    @Override
    public Integer queryCommentSizeByArticleId(int articleId) {
        return articleCommentDao.queryCommentSizeByArticleId(articleId);
    }

    @Override
    public List<ArticleCommentVo> getCommentByArticleId(Integer articleId) {
        List<ArticleCommentVo> articleCommentVos = articleCommentDao.queryCommentByArticleId(articleId, 0);
        for (ArticleCommentVo articleCommentVo : articleCommentVos) {
            childrenArticleComment(articleCommentVo.getId(), articleId, articleCommentVo.getName(), articleCommentVo.getChildrenArticleCommons());
        }
        return articleCommentVos;

    }

    private void childrenArticleComment(Integer pid, Integer articleId, String parentName, List<ArticleCommentVo> childrenArticleCommons) {
        List<ArticleCommentVo> articleCommentVos = articleCommentDao.queryCommentByArticleId(articleId, pid);
        for (ArticleCommentVo articleCommentVo : articleCommentVos) {
            articleCommentVo.setParentName(parentName);
            childrenArticleCommons.add(articleCommentVo);
            childrenArticleComment(articleCommentVo.getId(), articleId, articleCommentVo.getName(), childrenArticleCommons);
        }
    }

    /*=============start=====================admin======================================================================================*/
    @Override
    public void getAdminArticleCommentPage(Integer page, Integer limit, CommentQo commentQo, ResponseVo<AdminCommentVo> responseVo) {
        responseVo.setCount(articleCommentDao.getAdminArticleCommentTotal(commentQo));
        page = (page - 1) * limit;//计算起始处
        responseVo.setData(articleCommentDao.getAdminArticleCommentPage(page, limit, commentQo));
    }

    @Override
    public void updateAdminArticleComment(Integer id, String content) {
        articleCommentDao.updateAdminArticleComment(id, content);
    }

    @Override
    public void deleteAdminArticleCommentById(Integer id) {
        deleteChildrenArticleComment(id);
    }

    @Override
    public Integer getArticleCommentSize() {
        return articleCommentDao.getArticleCommentSize();
    }

    /**
     * 递归删除子评论
     */
    private void deleteChildrenArticleComment(Integer id) {
        //首先先删除这个评论
        articleCommentDao.deleteAdminArticleCommentById(id);
        //然后以该评论的id作为父id，然后再递归的删除
        List<Integer> ids = articleCommentDao.getAdminArticleCommentsByPid(id);
        if (ids != null && ids.size() != 0) {
            ids.forEach(this::deleteChildrenArticleComment);
        }
    }
    /*=============end=====================admin======================================================================================*/

}