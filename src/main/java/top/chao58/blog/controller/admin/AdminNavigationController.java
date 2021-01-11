package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Navigation;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.service.NavigationService;

@RestController
@RequestMapping("/admin/navigation")
@SystemLog(module = "后台导航模块")
public class AdminNavigationController {

    @Autowired
    private NavigationService navigationService;


    @GetMapping("/getAll")
    @SystemLog(method = "查询所有导航")
    public ResponseVo<Navigation> getAll() {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        responseVo.setData(navigationService.getAll());
        responseVo.setCode(0);
        return responseVo;
    }


    @PostMapping("/addNavigation")
    @SystemLog(method = "添加导航")
    public ResponseVo<Navigation> addNavigation(@RequestBody Navigation navigation) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.addNavigation(navigation);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteNavigation")
    @SystemLog(method = "真实删除导航")
    public ResponseVo<Navigation> deleteNavigation(Integer id) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.deleteNavigation(id);
        responseVo.setCode(0);
        return responseVo;
    }

    @PostMapping("/updateData")
    @SystemLog(method = "修改导航单条数据")
    public ResponseVo<Navigation> updateData(@RequestBody Navigation navigation) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.updateData(navigation);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateHidden")
    @SystemLog(method = "修改导航隐藏状态")
    public ResponseVo<Navigation> updateHidden(Integer id, Boolean isHidden) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.updateHidden(id, isHidden);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateLeft")
    @SystemLog(method = "修改导航右边栏状态")
    public ResponseVo<Navigation> updateLeft(Integer id, Boolean isLeft) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.updateLeft(id, isLeft);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/updateBottom")
    @SystemLog(method = "修改导航底部状态")
    public ResponseVo<Navigation> updateBottom(Integer id, Boolean isBottom) {
        ResponseVo<Navigation> responseVo = new ResponseVo<>();
        navigationService.updateBottom(id, isBottom);
        responseVo.setCode(0);
        return responseVo;
    }

}
