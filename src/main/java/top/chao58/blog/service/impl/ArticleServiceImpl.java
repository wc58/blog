package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.chao58.blog.dao.ArticleCoreDao;
import top.chao58.blog.dao.LabelDao;
import top.chao58.blog.entity.dto.ArticleDto;
import top.chao58.blog.entity.po.Admin;
import top.chao58.blog.entity.po.ArticleComment;
import top.chao58.blog.entity.po.ArticleCore;
import top.chao58.blog.entity.po.ArticleOther;
import top.chao58.blog.entity.qo.ArticleQo;
import top.chao58.blog.entity.vo.*;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.service.ArticleService;
import top.chao58.blog.util.CommonUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleCoreDao articleCoreDao;

    @Resource
    private LabelDao labelDao;

    /*=============start=====================admin======================================================================================*/

    /**
     * 查询文章信息的核心方法
     */
    @Override
    public void getAdminArticlePage(ResponseVo<AdminArticleVo> responseVo, Integer page, Integer limit, ArticleQo articleQo, int recycle) {
        responseVo.setCount(articleCoreDao.getAdminArticleTotal(articleQo, recycle));
        page = (page - 1) * limit;//计算起始处
        responseVo.setData(articleCoreDao.getAdminArticlePage(page, limit, articleQo, recycle));
    }


    @Override
    public void updateTopById(Integer id, int topWeight) {
        articleCoreDao.updateTopById(id, topWeight);
    }

    @Override
    public void updateHiddenById(Integer id, Boolean isHidden) {
        //true：显示
        int hiddenValue = isHidden ? 0 : 1;
        articleCoreDao.updateHiddenById(id, hiddenValue);
    }

    @Override
    public void updateDataById(ArticleDto articleDto) {
        //修改核心表
        if (!StringUtils.isEmpty(articleDto.getTitle()) || !StringUtils.isEmpty(articleDto.getDes()))
            articleCoreDao.updateCoreDataById(articleDto);
        //修改其他表
        articleCoreDao.updateOtherDataById(articleDto);
    }

    @Override
    public void recycleArticleById(Integer id) {
        articleCoreDao.recycleArticleById(id, new Date());
    }

    @Override
    public void restoreArticleById(Integer id) {
        articleCoreDao.restoreArticleById(id);
    }

    @Override
    @Transactional
    public void deleteArticleById(ResponseVo<AdminArticleVo> responseVo, Integer id) {
        int count = articleCoreDao.deleteOtherArticleById(id);
        responseVo.setCode(1);
        //只有其他表中的数据删除后，才可以删除核心表的数据
        if (count == 1) {
            labelDao.deleteLabelById(id);
            articleCoreDao.deleteCoreArticleById(id);
            articleCoreDao.deleteLabelArticleById(id);
            responseVo.setCode(0);
        }
    }

    @Override
    public Integer addArticle(ArticleDto articleDto, HttpSession session) {
        //-----start----------core-------------------------------------------
        ArticleCore core = packageCore(articleDto);
        articleCoreDao.addCore(core);
        //-----start----------label-------------------------------------------
        Integer[] labels = articleDto.getLabels();
        Arrays.stream(labels).forEach(labelId -> articleCoreDao.addLabel(core.getId(), labelId));
        //-----start----------other-------------------------------------------
        ArticleOther other = packageOther(articleDto, core, session);
        articleCoreDao.addOther(other);
        return core.getId();
    }

    @Override
    public Integer updateArticle(ArticleDto articleDto, HttpSession session) {
        //-----start----------core-------------------------------------------
        ArticleCore core = packageCore(articleDto);
        articleCoreDao.updateCore(core);
        //-----start----------label-------------------------------------------
        articleCoreDao.deleteLabel(core.getId());
        Integer[] labels = articleDto.getLabels();
        Arrays.stream(labels).forEach(labelId -> articleCoreDao.addLabel(core.getId(), labelId));
        //-----start----------other-------------------------------------------
        ArticleOther other = packageOther(articleDto, core, session);
        articleCoreDao.updateOther(other);
        return core.getId();
    }

    @Override
    public AdminArticleVo getAdminArticleById(Integer id) {
        return articleCoreDao.getAdminArticleById(id);
    }

    @Override
    public Integer getArticleLoginStatusById(Integer id) {
        return articleCoreDao.getArticleLoginStatusById(id);
    }


    private ArticleOther packageOther(ArticleDto articleDto, ArticleCore core, HttpSession session) {
        ArticleOther other = new ArticleOther();
        other.setId(core.getId());
        //从登陆的状态中去值
        Admin admin = (Admin) session.getAttribute("admin");
        other.setAuthor(admin.getNickname());
        other.setStatus(articleDto.getStatus());
        other.setFlow(0);
        other.setTop(articleDto.getTop() ? 1 : 0);
        other.setHidden(articleDto.getShow() ? 0 : 1);
        other.setLogin(articleDto.getLogin() ? 1 : 0);
        other.setDel(0);
        Date date = new Date();
        other.setCreateTime(date);
        other.setModifyTime(date);
        return other;
    }

    private ArticleCore packageCore(ArticleDto articleDto) {
        ArticleCore core = new ArticleCore();
        core.setId(articleDto.getId());
        core.setTitle(articleDto.getTitle());
        core.setCover(articleDto.getCover());
        core.setDes(articleDto.getDes());
        core.setContent(articleDto.getContent());
        //分析文章中图片链接，然后单独插入到数据库中
        core.setContentImg(CommonUtils.analyseHtmlByImg(articleDto.getContent()));
        return core;
    }


    /*=============end=====================admin======================================================================================*/


    @Override
    public List<ArticleListVo> getArticlePage(Integer currentPage, Integer showSize) {
        //计算起始页
        currentPage = (currentPage - 1) * showSize;
        return articleCoreDao.queryArticleList(currentPage, showSize);
    }

    @Override
    public List<ArticleListVo> getArticlePageByCondition(Integer currentPage, Integer showSize, List<Integer> articleIds, String searchTxt) {
        //计算起始页
        currentPage = (currentPage - 1) * showSize;
        return articleCoreDao.queryArticleListByCondition(currentPage, showSize, articleIds, searchTxt);
    }

    @Override
    public Integer getTotalByCondition(Integer labelId, String searchTxt) {
        List<Integer> articleIds = labelDao.getArticleIdsByLabelId(labelId);
        return articleCoreDao.queryTotalByCondition(articleIds, searchTxt);
    }

    @Override
    public Integer getArticleSize() {
        return articleCoreDao.getArticleSize();
    }

    @Override
    public Integer getFlowSize() {
        return articleCoreDao.getFlowSize();
    }

    @Override
    public Integer getArticleTopSize() {
        return articleCoreDao.getArticleTopSize();
    }

    @Override
    public List<TopArticleVo> getTopArticle() {
        return articleCoreDao.queryTopArticle();
    }

    @Override
    public ReadArticleVo getArticleById(Integer id) {
        return articleCoreDao.queryArticleById(id);
    }

    @Override
    public Integer getTotal() {
        return articleCoreDao.queryTotal();
    }

    @Override
    public void addComment(String articleId, String uid, String pid, String content) {
        ArticleComment articleComment = new ArticleComment();
        //为空说明为顶级评论
        pid = StringUtils.isEmpty(pid) ? "0" : pid;
        articleComment.setArticleId(Integer.parseInt(articleId));
        articleComment.setPid(Integer.parseInt(pid));
        articleComment.setUid(Integer.parseInt(uid));
        articleComment.setContent(content);
        articleComment.setHidden(0);
        articleComment.setCreateTime(new Date());
        articleCoreDao.insertComment(articleComment);
    }

    @Override
    public void increaseFlow(Integer id) {
        articleCoreDao.increaseFlow(id);
    }

    @Override
    public List<HotArticleVo> getHotArticle(int size) {
        return articleCoreDao.queryHotArticle(size);
    }

}
