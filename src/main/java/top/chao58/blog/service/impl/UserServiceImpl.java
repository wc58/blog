package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.UserDao;
import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.UserVo;
import top.chao58.blog.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<UserVo> getLatelyUser(int visitorSize) {
        return userDao.queryLatelyUser(visitorSize);
    }

    @Override
    public UserVo getByThirdId(String thirdId) {
        return userDao.queryByThirdId(thirdId);
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateModifyTime(User user) {
        userDao.updateUser(user);
    }

    /*=============start=====================admin======================================================================================*/

    @Override
    public void getAdminUserPage(Integer page, Integer limit, String name, ResponseVo<User> responseVo) {
        responseVo.setCount(userDao.getAdminUserTotal(name));
        page = (page - 1) * limit;
        responseVo.setData(userDao.getAdminUserPage(page, limit, name));
    }

    @Override
    public Integer getUserSize() {
        return userDao.getUserSize();
    }
    /*=============end=====================admin======================================================================================*/
}