package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.dto.LabelDto;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.service.LabelService;

@RestController
@RequestMapping("/admin/label")
@SystemLog(module = "后台标签管理")
public class AdminLabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/getAdminLabelPage")
    @SystemLog(method = "分页查询标签")
    public ResponseVo<AdminLabelVo> getAdminLabelPage(Integer page, Integer limit) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        labelService.getAdminLabelPage(responseVo, page, limit);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateHidden")
    @SystemLog(method = "修改标签隐藏状态")
    public ResponseVo<AdminLabelVo> updateHidden(Integer id, Boolean isHidden) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        labelService.updateHiddenById(id, isHidden);
        responseVo.setCode(0);
        return responseVo;
    }

    @PostMapping("/updateData")
    @SystemLog(method = "修改标签单条数据")
    public ResponseVo<AdminLabelVo> updateData(@RequestBody LabelDto labelDto) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        labelService.updateDataById(labelDto);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteLabel")
    @SystemLog(method = "真实删除标签")
    public ResponseVo<AdminLabelVo> deleteLabel(Integer id) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        labelService.deleteLabelById(responseVo, id);
        return responseVo;
    }

    @GetMapping("/addLabel")
    @SystemLog(method = "添加标签")
    public ResponseVo<AdminLabelVo> addLabel(String name) {
        ResponseVo<AdminLabelVo> responseVo = new ResponseVo<>();
        labelService.addLabel(name);
        return responseVo;
    }


}
