package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Navigation;

import java.util.List;

public interface NavigationDao {
    List<Navigation> queryAllLeft();

    List<Navigation> queryAllBottom();

    List<Navigation> queryAllTop();

    List<Navigation> getAll();

    void deleteById(@Param("id") Integer id);

    void updateLeftById(@Param("id") Integer id, @Param("leftValue") Integer leftValue);

    void updateBottomById(@Param("id") Integer id, @Param("bottomValue") Integer bottomValue);

    void updateHiddenById(@Param("id") Integer id, @Param("hiddenValue") Integer hiddenValue);

    void updateDataById(@Param("navigation") Navigation navigation);

    void addNavigation(@Param("navigation") Navigation navigation);

}