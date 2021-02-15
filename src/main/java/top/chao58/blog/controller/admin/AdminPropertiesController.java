package top.chao58.blog.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.properties.*;
import top.chao58.blog.service.MailService;

@RestController
@RequestMapping("/admin/properties")
@SystemLog(module = "系统设置模块")
public class AdminPropertiesController {

    @Autowired
    private OssProperties ossProperties;

    @Autowired
    private IndexProperties indexProperties;

    @Autowired
    private ShareProperties shareProperties;

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
    private MailProperties mailProperties;


    @Autowired
    private MailService mailService;

    @GetMapping("/getOssProperties")
    @SystemLog(method = "查询OSS设置")
    public OssProperties getOssProperties() {
        return ossProperties;
    }


    @PostMapping("/updateOssProperties")
    @SystemLog(method = "修改OSS设置")
    public ResponseVo<Object> updateOssProperties(@RequestBody OssProperties ossProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(ossProperties, this.ossProperties);
        responseVo.setCode(0);
        return responseVo;
    }


    @GetMapping("/getIndexProperties")
    @SystemLog(method = "查询首页设置")
    public IndexProperties getIndexProperties() {
        return indexProperties;
    }


    @PostMapping("/updateIndexProperties")
    @SystemLog(method = "修改首页设置")
    public ResponseVo<Object> updateIndexProperties(@RequestBody IndexProperties indexProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(indexProperties, this.indexProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getArticleProperties")
    @SystemLog(method = "查询文章页设置")
    public ArticleProperties getArticleProperties() {
        return articleProperties;
    }


    @PostMapping("/updateArticleProperties")
    @SystemLog(method = "修改文章页设置")
    public ResponseVo<Object> updateArticleProperties(@RequestBody ArticleProperties articleProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(articleProperties, this.articleProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getLeaveProperties")
    @SystemLog(method = "查询留言页设置")
    public LeaveProperties getLeaveProperties() {
        return leaveProperties;
    }


    @PostMapping("/updateLeaveProperties")
    @SystemLog(method = "修改留言页设置")
    public ResponseVo<Object> updateLeaveProperties(@RequestBody LeaveProperties leaveProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(leaveProperties, this.leaveProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getLinkProperties")
    @SystemLog(method = "查询友链页设置")
    public LinkProperties getLinkProperties() {
        return linkProperties;
    }


    @PostMapping("/updateLinkProperties")
    @SystemLog(method = "修改友链页设置")
    public ResponseVo<Object> updateLinkProperties(@RequestBody LinkProperties linkProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(linkProperties, this.linkProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getDiaryProperties")
    @SystemLog(method = "查询日记页设置")
    public DiaryProperties getDiaryProperties() {
        return diaryProperties;
    }

    @PostMapping("/updateDiaryProperties")
    @SystemLog(method = "修改日记页设置")
    public ResponseVo<Object> updateDiaryProperties(@RequestBody DiaryProperties diaryProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(diaryProperties, this.diaryProperties);
        responseVo.setCode(0);
        return responseVo;
    }


    @GetMapping("/getAboutProperties")
    @SystemLog(method = "查询关于页设置")
    public AboutProperties getAboutProperties() {
        return aboutProperties;
    }

    @PostMapping("/updateAboutProperties")
    @SystemLog(method = "修改关于也设置")
    public ResponseVo<Object> updateAboutProperties(@RequestBody AboutProperties aboutProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(aboutProperties, this.aboutProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getMailProperties")
    @SystemLog(method = "查询邮箱设置")
    public MailProperties getMailProperties() {
        return mailProperties;
    }

    @PostMapping("/updateMailProperties")
    @SystemLog(method = "修改邮箱设置")
    public ResponseVo<Object> updateMailProperties(@RequestBody MailProperties mailProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(mailProperties, this.mailProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getShareProperties")
    @SystemLog(method = "查询共享设置")
    public ShareProperties getShareProperties() {
        return shareProperties;
    }

    @PostMapping("/updateShareProperties")
    @SystemLog(method = "修改共享设置")
    public ResponseVo<Object> updateShareProperties(@RequestBody ShareProperties shareProperties) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        BeanUtils.copyProperties(shareProperties, this.shareProperties);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/testMail")
    @SystemLog(method = "邮箱测试")
    public ResponseVo<Object> testMail(String receiveMailAccount, String title, String subject, String content) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        boolean flag = mailService.sendMail(receiveMailAccount, title, subject, content);
        responseVo.setCode(flag ? 0 : 1);
        return responseVo;
    }

}
