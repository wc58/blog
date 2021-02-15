package top.chao58.blog.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.chao58.blog.entity.vo.ArticleListVo;
import top.chao58.blog.service.ArticleCommentService;
import top.chao58.blog.service.ArticleService;
import top.chao58.blog.service.LabelService;

import java.util.Collections;
import java.util.List;

@Component
public class ArticleFacade {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private LabelService labelService;


    public List<ArticleListVo> getArticleListByCondition(Integer currentPage, Integer showSize) {
        //1、查找文章信息
        List<ArticleListVo> articleList = articleService.getArticlePage(currentPage, showSize);
        getOtherInfo(articleList);
        return articleList;
    }


    public List<ArticleListVo> getArticleListByCondition(Integer currentPage, Integer showSize, Integer labelId, String searchTxt) {
        List<Integer> articleIds = labelService.getArticleIdsByLabelId(labelId);
        //有标签条件，但没有一个文章符合
        if (articleIds.size() == 0 && labelId != -1) {
            articleIds = Collections.singletonList(-1);
        }
        //1、查找文章信息
        List<ArticleListVo> articleList = articleService.getArticlePageByCondition(currentPage, showSize, articleIds, searchTxt);
        getOtherInfo(articleList);
        return articleList;
    }

    private void getOtherInfo(List<ArticleListVo> articleList) {
        articleList.forEach(articleVo -> {
            int articleId = articleVo.getId();
            // 2、评论数量
            Integer commentSize = articleCommentService.queryCommentSizeByArticleId(articleId);
            //3、对应的标签
            List<String> labels = labelService.getLabelsByArticleId(articleId);
            articleVo.setCommentSize(commentSize);
            articleVo.setLabels(labels);
        });
    }

}
