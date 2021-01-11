package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.UserVo;

import java.util.Date;
import java.util.List;

public interface UserDao {

    List<UserVo> queryLatelyUser(int visitorSize);

    UserVo queryByThirdId(String thirdId);

    void insertUser(User user);

    void updateUser(User user);

    int getAdminUserTotal(@Param("name") String name);

    List<User> getAdminUserPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("name") String name);

    Integer getUserSize();

}