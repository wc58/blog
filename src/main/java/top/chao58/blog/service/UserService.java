package top.chao58.blog.service;


import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.UserVo;

import java.util.List;

public interface UserService {


    List<UserVo> getLatelyUser(int visitorSize);

    UserVo getByThirdId(String thirdId);

    void addUser(User user);

    void updateModifyTime(User thirdId);

    void getAdminUserPage(Integer page, Integer limit, String nikeName, ResponseVo<User> responseVo);

    Integer getUserSize();
}