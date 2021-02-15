package top.chao58.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Navigation;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.entity.vo.ArticleCommentVo;
import top.chao58.blog.entity.vo.ArticleListVo;
import top.chao58.blog.entity.vo.template.ArticleTemplate;
import top.chao58.blog.entity.vo.ReadArticleVo;
import top.chao58.blog.facade.ArticleFacade;
import top.chao58.blog.properties.ArticleProperties;
import top.chao58.blog.properties.ShareProperties;
import top.chao58.blog.service.*;

import java.util.*;

@Controller
@RequestMapping("/article")
@SystemLog(module = "文章模块")
public class ArticleController {

    @Autowired
    private ArticleProperties articleProperties;
    @Autowired
    private ShareProperties shareProperties;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private ArticleFacade articleFacade;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private MailService mailService;
    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/list")
    @ResponseBody
    @SystemLog(method = "分页查询文章列表")
    public Map<String, Object> list(Integer currentPage, Integer labelId, String searchTxt) {
        HashMap<String, Object> map = new HashMap<>();
        Integer size = Integer.valueOf(articleProperties.getShowSize());
        //首先查出总页数，总条数/每页数
        Integer pageTotal = (articleService.getTotalByCondition(labelId, searchTxt) / size) + 1;
        //查出文章信息
        List<ArticleListVo> articleList = articleFacade.getArticleListByCondition(currentPage, size, labelId, searchTxt);
        map.put("pageTotal", pageTotal);
        map.put("articles", renderTemplate(articleList/*, articleProperties.getSite()*/));
        return map;
    }

    @GetMapping("/read/{id}")
    @SystemLog(method = "点击文章内容")
    public String read(@PathVariable Integer id, Model model) {
        //点击量加一
        articleService.increaseFlow(id);
        //统计
        statisticsService.incrData(new Statistics().setArticleFlow(1));
        ReadArticleVo readArticleVo = articleService.getArticleById(id);
        List<Navigation> navigations = navigationService.getAllTop();
        //延伸阅读
        List<ArticleListVo> extendArticles = extendArticle(articleFacade.getArticleListByCondition(1, articleService.getTotal()), readArticleVo);
        //评论
        List<ArticleCommentVo> articleCommentVos = articleCommentService.getCommentByArticleId(id);
        model.addAttribute("extendArticles", extendArticles);
        model.addAttribute("shareProperties", shareProperties);
        model.addAttribute("navigations", navigations);
        model.addAttribute("readArticleVo", readArticleVo);
        model.addAttribute("articleProperties", articleProperties);
        model.addAttribute("articleCommentVos", articleCommentVos);
        return "read";
    }

    @PostMapping("/reply")
    @ResponseBody
    @SystemLog(method = "文章评论")
    public Integer reply(String articleId, String userId, String pid, String content) {
        //后端也判断
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(content)) {
            return 500;
        }
        //发送邮箱
        articleService.addComment(articleId, userId, pid, content);
        //统计
        statisticsService.incrData(new Statistics().setArticleComment(1));
        String mailContent = "<pre>有人在<a href=\"" + articleProperties.getSite() + "/article/read/" + articleId + "\" target=\"_blank\">该文章</a>进行了评论</pre>";
        mailService.sendMail("2258354832@qq.com", "往事随风博客", "评论", mailContent);
        return 200;
    }

    private String renderTemplate(List<ArticleListVo> articleListVos/*, String site*/) {
        StringBuilder templates = new StringBuilder();
        for (ArticleListVo articleListVo : articleListVos) {
            ArticleTemplate template = new ArticleTemplate();
            //=============================================================================
            //封装数据
            template.setLink(/*site +*/ "/article/read/" + articleListVo.getId());
            template.setTop(articleListVo.getTop() != 0);
            template.setTitle(articleListVo.getTitle());
            template.setDescription(articleListVo.getDes());
            template.setCommentSize(articleListVo.getCommentSize());
            template.setFlow(articleListVo.getFlow());
            template.setCover(articleListVo.getCover());
            template.setLately(articleListVo.getCreateTime());
            template.setStatus(articleListVo.getStatus());
            template.setLabels(articleListVo.getLabels());
            //=============================================================================
            templates.append(template.build());
        }
        return templates.toString();
    }

    public List<ArticleListVo> extendArticle(List<ArticleListVo> articles, ReadArticleVo readArticleVo) {
        Random random = new Random();
        ArrayList<ArticleListVo> randomArticle = new ArrayList<>();
        //如果最大4条文章，则直接删除本文章返回
        if (articles.size() <= 4) {
            for (ArticleListVo article : articles) {
                if (article.getId() != readArticleVo.getId()) {
                    randomArticle.add(article);
                }
            }
            return randomArticle;
        }

        vo1:
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(articles.size());
            ArticleListVo article = articles.get(index);
            if (article.getId() == readArticleVo.getId()) {
                i--;
                continue;
            }
            for (ArticleListVo randomArticleListVo : randomArticle) {
                if (article.getId() == randomArticleListVo.getId()) {
                    i--;
                    continue vo1;
                }
            }
            randomArticle.add(article);
        }

        return randomArticle;
    }


}
