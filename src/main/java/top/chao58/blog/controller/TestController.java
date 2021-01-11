package top.chao58.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.properties.IndexProperties;
import top.chao58.blog.properties.parse.PropertiesFactory;
import top.chao58.blog.service.MailService;
import top.chao58.blog.service.StatisticsService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PropertiesFactory propertiesFactory;
    @Autowired
    private MailService mailService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/setHeader")
    @ResponseBody
    public void setUseName() {
        IndexProperties indexProperties = (IndexProperties) propertiesFactory.getByClass(IndexProperties.class);
        indexProperties.setHeader("测试");
    }

    @GetMapping("/sendMail")
    @ResponseBody
    public void sendMail() {
//        mailService.sendMail("279895774@qq.com", "往事随风博客", "信息", "你的验证码为", responseVo);
    }

    @GetMapping("/incrStatistics")
    public void incrStatistics(Integer type) {
        Statistics statistics = new Statistics();
        switch (type) {
            case 1:
                statistics.setArticleFlow(1);
                break;
            case 2:
                statistics.setUser(1);
                break;
            case 3:
                statistics.setArticleComment(1);
                break;
            case 4:
                statistics.setLeave(1);
                break;
            case 5:
                statistics.setLink(1);
                break;
        }
        statisticsService.incrData(statistics);
    }

}
