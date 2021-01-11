package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.dto.LabelDto;
import top.chao58.blog.entity.dto.LinkDto;
import top.chao58.blog.entity.po.Link;
import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.entity.vo.admin.AdminLinkVo;
import top.chao58.blog.service.LinkService;
import top.chao58.blog.service.UserService;

@RestController
@RequestMapping("/admin/link")
@SystemLog(module = "后台友链模块")
public class AdminLinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("getAdminLinkPage")
    @SystemLog(method = "分页查询友链")
    public ResponseVo<AdminLinkVo> getAdminLinkPage(Integer page, Integer limit, String title) {
        ResponseVo<AdminLinkVo> responseVo = new ResponseVo<>();
        responseVo.setCode(0);
        linkService.getAdminLinkPage(page, limit, title, responseVo);
        return responseVo;
    }

    @GetMapping("/updateHidden")
    @SystemLog(method = "修改友链隐藏状态")
    public ResponseVo<AdminLabelVo> updateHidden(Integer id, Boolean isHidden) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        linkService.updateHiddenById(id, isHidden);
        responseVo.setCode(0);
        return responseVo;
    }

    @PostMapping("/updateData")
    @SystemLog(method = "修改友链单条数据")
    public ResponseVo<AdminLabelVo> updateData(@RequestBody LinkDto linkDto) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        linkService.updateDataById(linkDto);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteLink")
    @SystemLog(method = "真实删除友链")
    public ResponseVo<AdminLabelVo> deleteLabel(Integer id) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        linkService.deleteLinkById(responseVo, id);
        return responseVo;
    }

}
