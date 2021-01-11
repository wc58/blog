package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.dto.LinkDto;
import top.chao58.blog.entity.po.Link;
import top.chao58.blog.entity.vo.LinkVo;
import top.chao58.blog.entity.vo.admin.AdminLinkVo;

import java.util.List;

public interface LinkDao {
    List<LinkVo> queryLinks();

    int getAdminLinkTotal(@Param("title") String title);

    List<AdminLinkVo> getAdminLinkPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("title") String title);

    void updateHiddenById(@Param("id") Integer id, @Param("hiddenValue") int hiddenValue);

    void updateDataById(@Param("linkDto") LinkDto linkDto);

    int deleteLinkById(@Param("id") Integer id);

    void addLink(@Param("link") Link link);

    Integer getLinkSize();

}