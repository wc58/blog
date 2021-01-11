package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.NavigationDao;
import top.chao58.blog.entity.po.Navigation;
import top.chao58.blog.service.NavigationService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Navigation)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:53
 */
@Service("navigationService")
public class NavigationServiceImpl implements NavigationService {
    @Resource
    private NavigationDao navigationDao;

    @Override
    public List<Navigation> getAllLeft() {
        return navigationDao.queryAllLeft();
    }

    @Override
    public List<Navigation> getAllBottom() {
        return navigationDao.queryAllBottom();
    }

    @Override
    public List<Navigation> getAllTop() {
        return navigationDao.queryAllTop();
    }

    @Override
    public List<Navigation> getAll() {
        return navigationDao.getAll();
    }

    @Override
    public void addNavigation(Navigation navigation) {
        navigation.setLeft(0);
        navigation.setBottom(0);
        navigation.setHidden(0);
        navigationDao.addNavigation(navigation);
    }

    @Override
    public void deleteNavigation(Integer id) {
        navigationDao.deleteById(id);
    }

    @Override
    public void updateData(Navigation navigation) {
        navigationDao.updateDataById(navigation);
    }

    @Override
    public void updateHidden(Integer id, Boolean isHidden) {
        int hiddenValue = isHidden ? 0 : 1;
        navigationDao.updateHiddenById(id, hiddenValue);
    }

    @Override
    public void updateLeft(Integer id, Boolean isLeft) {
        int leftValue = isLeft ? 1 : 0;
        navigationDao.updateLeftById(id, leftValue);
    }

    @Override
    public void updateBottom(Integer id, Boolean isBottom) {
        int bottomValue = isBottom ? 1 : 0;
        navigationDao.updateBottomById(id, bottomValue);
    }

}