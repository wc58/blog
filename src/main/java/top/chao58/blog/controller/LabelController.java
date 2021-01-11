package top.chao58.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.vo.LabelVo;
import top.chao58.blog.service.LabelService;

import java.util.List;

@RestController
@RequestMapping("/admin/label")
@SystemLog(module = "标签模块")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/getAllLabels")
    @SystemLog(method = "查询所有标签")
    public List<LabelVo> getAllLabels() {
        return labelService.getAllLabels();
    }


    @GetMapping("/getSelectLabels")
    @SystemLog(method = "查询指定文章标签")
    public List<LabelVo> getSelectLabels(Integer id) {
        if (id == null || id == -1) {
            return labelService.getAllLabels();
        } else {
            return labelService.getSelectLabels(id);
        }
    }

}
