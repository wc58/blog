package top.chao58.blog.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Navigation;
import top.chao58.blog.entity.vo.*;
import top.chao58.blog.properties.*;
import top.chao58.blog.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@Log
@SystemLog(module = "页面跳转模块")
public class PageController {

    @Autowired
    private ShareProperties shareProperties;

    @Autowired
    private IndexProperties indexProperties;

    @Autowired
    private OtherProperties otherProperties;

    @Autowired
    private ArticleProperties articleProperties;

    @Autowired
    private LeaveProperties leaveProperties;

    @Autowired
    private LinkProperties linkProperties;

    @Autowired
    private DiaryProperties diaryProperties;

    @Autowired
    private AboutProperties aboutProperties;

    @Autowired
    private NavigationService navigationService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private UserService userService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private DiaryService diaryService;


    @SystemLog(method = "跳转博客首页")
    @GetMapping({"/", "/index"})
    public String toPageIndex(Model model, HttpServletRequest request) {
        log.info("----用户点击了首页----");
        //查询热度前几个的文章，电脑显示6个，其他都3个
        log.info("准备查询热门文章");
        List<HotArticleVo> hotArticleList = articleService.getHotArticle(decideClient(request));
        //文本信息
        log.info("准备查询文本信息");
        //导航信息
        log.info("准备查询导航信息");
        List<Navigation> navigationLefts = navigationService.getAllLeft();
        List<Navigation> navigationBottoms = navigationService.getAllBottom();
        List<Navigation> navigations = navigationService.getAllTop();
        //传到前端
        log.info("准备封装数据");
        model.addAttribute("indexProperties", indexProperties);
        model.addAttribute("shareProperties", shareProperties);
        model.addAttribute("navigationLefts", navigationLefts);
        model.addAttribute("navigationBottoms", navigationBottoms);
        model.addAttribute("navigations", navigations);
        model.addAttribute("hotArticleList", hotArticleList);
        log.info("----!!!开始响应前端!!!----");
        return "index";
    }

    private int decideClient(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        return agent.contains("Windows") ? 6 : 3;
    }

    @SystemLog(method = "跳转文章列表")
    @GetMapping("/article")
    public String toArticle(Model model) {
        //标签
        List<LabelVo> labels = labelService.getAllLabels();
        //热门文章
        List<HotArticleVo> hotArticles = articleService.getHotArticle(Integer.parseInt(articleProperties.getHotSize()));
        //置顶文章
        List<TopArticleVo> topArticles = articleService.getTopArticle();
        //用户
        List<UserVo> users = userService.getLatelyUser(Integer.parseInt(otherProperties.getVisitorSize()));
        setCommonModel(model);
        model.addAttribute("labels", labels);
        model.addAttribute("hotArticles", hotArticles);
        model.addAttribute("topArticles", topArticles);
        model.addAttribute("users", users);
        return "article";
    }

    @SystemLog(method = "跳转留言页面")
    @GetMapping("/leave")
    public String toLeave(Model model) {
        List<LeaveVo> leaves = leaveService.getLeave();
        model.addAttribute("leaveProperties", leaveProperties);
        model.addAttribute("leaves", leaves);
        setCommonModel(model);
        return "leave";
    }

    @SystemLog(method = "跳转友链页面")
    @GetMapping("/link")
    public String toLink(Model model) {
        List<LinkVo> linkVos = linkService.getLinks();
        model.addAttribute("linkProperties", linkProperties);
        model.addAttribute("linkVos", linkVos);
        setCommonModel(model);
        return "link";
    }

    @SystemLog(method = "跳转日记页面")
    @GetMapping("/diary")
    public String toDiary(Model model) {
        HashMap<String, List<DiaryVo>> diaryVos = diaryService.getDiaries(diaryProperties.getStartTime(), diaryProperties.getEndTime());
        setCommonModel(model);
        model.addAttribute("diaryVos", diaryVos);
        return "diary";
    }

    @SystemLog(method = "跳转关于页面")
    @GetMapping("/about")
    public String toAbout(Model model) {
        setCommonModel(model);
        model.addAttribute("aboutProperties", aboutProperties);
        return "about";
    }

    /**
     * 设置公共数据
     */
    private void setCommonModel(Model model) {
        List<Navigation> navigations = navigationService.getAllTop();
        model.addAttribute("shareProperties", shareProperties);
        model.addAttribute("navigations", navigations);
    }

}
