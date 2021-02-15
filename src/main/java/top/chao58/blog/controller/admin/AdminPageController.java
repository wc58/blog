package top.chao58.blog.controller.admin;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.properties.OtherProperties;
import top.chao58.blog.service.*;

import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    private OtherProperties otherProperties;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/desk")
    public String desk(Model model, HttpSession session) {
        //========数据统计  1==============================
        packageDataOne(model, otherProperties);
        //========数据统计  2==============================
        packageDataTwo(model);
        //========系统信息==============================
        packageSystemInfo(model, session);
        return "admin/desk";
    }

    private void packageSystemInfo(Model model, HttpSession session) {
        model.addAttribute("sessionId", session.getId());

        Map<String, String> map = System.getenv();
        Properties props = System.getProperties();
        model.addAttribute("USERNAME", map.get("USERNAME"));
        model.addAttribute("COMPUTERNAME", map.get("COMPUTERNAME"));

        model.addAttribute("osName", props.getProperty("os.name"));
        model.addAttribute("osArch", props.getProperty("os.arch"));
        model.addAttribute("osVersion", props.getProperty("os.version"));

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            model.addAttribute("ip", ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        model.addAttribute("userDir", props.getProperty("user.dir"));
        model.addAttribute("javaVersion", props.getProperty("java.version"));
        model.addAttribute("javaHome", props.getProperty("java.home"));
        model.addAttribute("javaVmName", props.getProperty("java.vm.name"));

        Runtime r = Runtime.getRuntime();
        model.addAttribute("availableProcessors", r.availableProcessors());
        model.addAttribute("totalMemory", r.totalMemory() / 1024 / 1024);
        model.addAttribute("freeMemory", r.freeMemory() / 1024 / 1024);
    }

    private void packageDataTwo(Model model) {
        Statistics yesterday = statisticsService.getYesterday();
        Statistics today = statisticsService.getToday();
        Statistics week = statisticsService.getWeek();
        Statistics month = statisticsService.getMonth();
        Statistics year = statisticsService.getYear();
        model.addAttribute("yesterday", yesterday);
        model.addAttribute("today", today);
        model.addAttribute("week", week);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
    }

    private void packageDataOne(Model model, OtherProperties otherProperties) {
        DateTime buildDate = DateUtil.parse(otherProperties.getBuildDate());
        DateTime practiceDate = DateUtil.parse(otherProperties.getPracticeDate());
        Date date = new Date();
        //建站时间
        long build = DateUtil.between(buildDate, date, DateUnit.DAY);
        //实习倒计时
        long practice = DateUtil.between(date, practiceDate, DateUnit.DAY);
        //文章
        Integer articleSize = articleService.getArticleSize();
        //用户
        Integer userSize = userService.getUserSize();
        //评论
        Integer articleCommentSize = articleCommentService.getArticleCommentSize();
        //吐槽
        Integer leaveSize = leaveService.getLeaveSize();
        //标签
        Integer labelSize = labelService.getLabelSize();
        //友链
        Integer linkSize = linkService.getLinkSize();
        //文章浏览
        Integer flowSize = articleService.getFlowSize();
        //日记
        Integer diarySize = diaryService.getDiarySize();
        //置顶
        Integer articleTopSize = articleService.getArticleTopSize();
        //管理员
        Integer adminSize = adminService.getAdminSize();
        model.addAttribute("build", build);
        model.addAttribute("practice", practice);
        model.addAttribute("articleSize", articleSize);
        model.addAttribute("userSize", userSize);
        model.addAttribute("articleCommentSize", articleCommentSize);
        model.addAttribute("leaveSize", leaveSize);
        model.addAttribute("labelSize", labelSize);
        model.addAttribute("linkSize", linkSize);
        model.addAttribute("flowSize", flowSize);
        model.addAttribute("diarySize", diarySize);
        model.addAttribute("articleTopSize", articleTopSize);
        model.addAttribute("adminSize", adminSize);
    }


}
