package top.chao58.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.service.LeaveService;
import top.chao58.blog.service.MailService;
import top.chao58.blog.service.StatisticsService;

@Controller
@RequestMapping("/leave")
@SystemLog(module = "留言模块")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private MailService mailService;

    @Autowired
    private StatisticsService statisticsService;


    @PostMapping("/reply")
    @ResponseBody
    @SystemLog(method = "留言评论")
    public Integer reply(String userId, String pid, String content) {
        //后端也判断
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(content)) {
            return 500;
        }
        leaveService.addLeave(userId, pid, content);
        //统计
        statisticsService.incrData(new Statistics().setLeave(1));
        String mailContent = "<p><span style=\"white-space: pre-wrap;\">新增了一条留言信息，</span><a href=\"http://chao58.top/leave\" target=\"_blank\">点击查看</a><br></p>";
        mailService.sendMail("2258354832@qq.com", "往事随风博客", "留言", mailContent);
        return 200;
    }

}
