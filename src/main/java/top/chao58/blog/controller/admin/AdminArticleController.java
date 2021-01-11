package top.chao58.blog.controller.admin;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.dto.ArticleDto;
import top.chao58.blog.entity.qo.ArticleQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.service.ArticleService;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RestController
@RequestMapping("/admin/article")
@Log
@SystemLog(module = "后台文章模块")
public class AdminArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getAdminArticlePage")
    @SystemLog(method = "分页查询文章")
    public ResponseVo<AdminArticleVo> getAdminArticlePage(Integer page, Integer limit, ArticleQo articleQo) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.getAdminArticlePage(responseVo, page, limit, articleQo, 0);//0：代表未删除
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getAdminArticleById")
    @SystemLog(method = "获取文章信息")
    public ResponseVo<AdminArticleVo> getAdminArticleById(Integer id) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        responseVo.setData(Collections.singletonList(articleService.getAdminArticleById(id)));
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getAdminArticleRecyclePage")
    @SystemLog(method = "分页查询文章回收站")
    public ResponseVo<AdminArticleVo> getAdminArticleRecyclePage(Integer page, Integer limit, ArticleQo articleQo) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.getAdminArticlePage(responseVo, page, limit, articleQo, 1);//1：代表已删除
        responseVo.setCode(0);
        return responseVo;
    }

    @PostMapping("/addOrUpdateArticle")
    @SystemLog(method = "添加或者修改文章")
    public ResponseVo<AdminArticleVo> addOrUpdateArticle(@RequestBody ArticleDto articleDto, HttpSession session) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        Integer articleId;
        if (articleDto.getId() == null || articleDto.getId() == -1) {
            articleId = articleService.addArticle(articleDto,session);
        } else {
            articleId = articleService.updateArticle(articleDto,session);
        }
        responseVo.setId(articleId);
        responseVo.setCode(0);
        return responseVo;
    }


    @GetMapping("/updateTop")
    @SystemLog(method = "修改文章置顶状态")
    public ResponseVo<AdminArticleVo> updateTop(Integer id, int topWeight) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.updateTopById(id, topWeight);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateHidden")
    @SystemLog(method = "修改文章隐藏状态")
    public ResponseVo<AdminArticleVo> updateHidden(Integer id, Boolean isHidden) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.updateHiddenById(id, isHidden);
        responseVo.setCode(0);
        return responseVo;
    }


    @PostMapping("/updateData")
    @SystemLog(method = "修改文章单条数据")
    public ResponseVo<AdminArticleVo> updateData(@RequestBody ArticleDto articleDto) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.updateDataById(articleDto);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/recycleArticle")
    @SystemLog(method = "回收文章")
    public ResponseVo<AdminArticleVo> recycleArticle(Integer id) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.recycleArticleById(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/restoreArticle")
    @SystemLog(method = "还原文章")
    public ResponseVo<AdminArticleVo> restoreArticle(Integer id) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.restoreArticleById(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteArticle")
    @SystemLog(method = "真实删除文章")
    public ResponseVo<AdminArticleVo> deleteArticle(Integer id) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        articleService.deleteArticleById(responseVo, id);
        return responseVo;
    }

}
