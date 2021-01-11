package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAdminUserPage")
    public ResponseVo<User> getAdminUserPage(Integer page, Integer limit, String name) {
        ResponseVo<User> responseVo = new ResponseVo<>();
        responseVo.setCode(0);
        userService.getAdminUserPage(page, limit, name, responseVo);
        return responseVo;
    }

}
