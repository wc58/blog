package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.chao58.blog.entity.po.Admin;
import top.chao58.blog.service.AdminService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @GetMapping("/login")
    public Integer login(String username, String password, HttpSession session) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null || !admin.getPassword().equals(password)) {
            return 1;
        }
        admin.setPassword("");
        session.setAttribute("admin", admin);
        session.setMaxInactiveInterval(60 * 60 * 24);
        return 0;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/admin/login";
    }

}
