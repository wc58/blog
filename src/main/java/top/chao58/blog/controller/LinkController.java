package top.chao58.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Link;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.service.LinkService;
import top.chao58.blog.service.MailService;
import top.chao58.blog.service.StatisticsService;

@RestController
@RequestMapping("/link")
@SystemLog(module = "友链模块")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private MailService mailService;

    @Autowired
    private StatisticsService statisticsService;


    @PostMapping("/addLink")
    @SystemLog(method = "添加友链")
    public ResponseVo<AdminLabelVo> addLink(@RequestBody Link link) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        linkService.addLink(link);
        //统计
        statisticsService.incrData(new Statistics().setLink(1));
        String content = "<p><span style=\"white-space: pre-wrap;\">后台新增了一条友链信息，</span><a href=\"http://chao58.top/link\" target=\"_blank\">点击查看</a><br></p>";
        mailService.sendMail("2258354832@qq.com", "往事随风博客", "友链", content);
        return responseVo;
    }

}
