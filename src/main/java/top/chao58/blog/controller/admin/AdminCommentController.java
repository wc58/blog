package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;
import top.chao58.blog.service.ArticleCommentService;
import top.chao58.blog.service.LeaveService;

@RestController
@RequestMapping("/admin/comment")
@SystemLog(module = "后台评论模块")
public class AdminCommentController {

    @Autowired
    private ArticleCommentService articleCommentService;

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/getAdminArticleCommentPage")
    @SystemLog(method = "分页查询文章评论")
    public ResponseVo<AdminCommentVo> getAdminArticleCommentPage(Integer page, Integer limit, CommentQo commentQo) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        articleCommentService.getAdminArticleCommentPage(page, limit, commentQo, responseVo);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getAdminLeaveCommentPage")
    @SystemLog(method = "分页查询留言评论")
    public ResponseVo<AdminCommentVo> getAdminLeaveCommentPage(Integer page, Integer limit, CommentQo commentQo) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        leaveService.getAdminLeaveCommentPage(page, limit, commentQo, responseVo);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateAdminArticleComment")
    @SystemLog(method = "修盖文章评论")
    public ResponseVo<AdminCommentVo> updateAdminArticleComment(Integer id, String content) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        articleCommentService.updateAdminArticleComment(id, content);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateAdminLeaveComment")
    @SystemLog(method = "修改留言评论")
    public ResponseVo<AdminCommentVo> updateAdminLeaveComment(Integer id, String content) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        leaveService.updateAdminLeaveComment(id, content);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteAdminArticleComment")
    @SystemLog(method = "删除文章评论")
    public ResponseVo<AdminCommentVo> deleteAdminArticleComment(Integer id) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        articleCommentService.deleteAdminArticleCommentById(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteAdminLeaveComment")
    @SystemLog(method = "删除留言评论")
    public ResponseVo<AdminCommentVo> deleteAdminLeaveComment(Integer id) {
        ResponseVo<AdminCommentVo> responseVo = new ResponseVo<>();
        leaveService.deleteAdminLeaveCommentById(id);
        responseVo.setCode(0);
        return responseVo;
    }

}
