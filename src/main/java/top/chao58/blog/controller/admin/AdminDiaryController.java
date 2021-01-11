package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.dto.ArticleDto;
import top.chao58.blog.entity.dto.DiaryDto;
import top.chao58.blog.entity.qo.BaseQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.entity.vo.admin.AdminDiaryVo;
import top.chao58.blog.service.DiaryService;

@RestController
@RequestMapping("/admin/diary")
@SystemLog(module = "后台日记模块")
public class AdminDiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping("addOrUpdateDiary")
    @SystemLog(method = "添加或者修改日记")
    public ResponseVo<AdminDiaryVo> addOrUpdateDiary(@RequestBody DiaryDto diaryDto) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        Integer id = diaryDto.getId();
        if (id == null || id == -1) {
            id = diaryService.addDiary(diaryDto);
        } else {
            diaryService.updateDiary(diaryDto);
        }
        responseVo.setId(id);
        return responseVo;
    }

    @GetMapping("/getAdminDiaryPage")
    @SystemLog(method = "分页查询日记")
    public ResponseVo<AdminDiaryVo> getAdminDiaryPage(Integer page, Integer limit, BaseQo baseQo) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.getAdminDiaryPage(responseVo, page, limit, baseQo, 0);//0：代表未删除
        return responseVo;
    }

    @GetMapping("/getAdminDiaryRecyclePage")
    @SystemLog(method = "分页查询日记回收站")
    public ResponseVo<AdminDiaryVo> getAdminDiaryRecyclePage(Integer page, Integer limit, BaseQo baseQo) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.getAdminDiaryPage(responseVo, page, limit, baseQo, 1);//1：代表已删除
        return responseVo;
    }

    @GetMapping("/getAdminDiaryById")
    @SystemLog(method = "获取日记信息")
    public ResponseVo<AdminDiaryVo> getAdminDiaryById(Integer id) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        responseVo.setData(diaryService.getAdminDiaryById(id));
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateHidden")
    @SystemLog(method = "修改日记隐藏状态")
    public ResponseVo<AdminDiaryVo> updateHidden(Integer id, Boolean isHidden) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.updateHiddenById(id, isHidden);
        responseVo.setCode(0);
        return responseVo;
    }


 /*   @PostMapping("/updateData")
    @SystemLog(method = "")
    public ResponseVo<AdminDiaryVo> updateData(@RequestBody DiaryDto diaryDto) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.updateDataById(diaryDto);
        responseVo.setCode(0);
        return responseVo;
    }*/

    @GetMapping("/recycleDiary")
    @SystemLog(method = "回收日记")
    public ResponseVo<AdminDiaryVo> recycleDiary(Integer id) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.recycleDiary(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/restoreDiary")
    @SystemLog(method = "还原日记")
    public ResponseVo<AdminDiaryVo> restoreDiary(Integer id) {
        ResponseVo<AdminDiaryVo> responseVo = new ResponseVo<>();
        diaryService.restoreDiary(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteDiary")
    @SystemLog(method = "真实删除日记")
    public ResponseVo<AdminArticleVo> deleteDiary(Integer id) {
        ResponseVo<AdminArticleVo> responseVo = new ResponseVo<>();
        diaryService.deleteDiaryById(responseVo, id);
        return responseVo;
    }
}
