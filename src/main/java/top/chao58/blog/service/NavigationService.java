package top.chao58.blog.service;

import top.chao58.blog.entity.po.Navigation;

import java.util.List;


public interface NavigationService {

    /**
     * 查询左边导航
     */
    List<Navigation> getAllLeft();

    /**
     * 查询底部导航
     */
    List<Navigation> getAllBottom();

    /**
     * 查询顶部导航
     */
    List<Navigation> getAllTop();


    List<Navigation> getAll();

    void addNavigation(Navigation navigation);

    void deleteNavigation(Integer id);

    void updateData(Navigation navigation);

    void updateHidden(Integer id, Boolean isHidden);

    void updateLeft(Integer id, Boolean isLeft);

    void updateBottom(Integer id, Boolean isBottom);

}