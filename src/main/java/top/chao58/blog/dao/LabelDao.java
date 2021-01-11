package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.dto.LabelDto;
import top.chao58.blog.entity.vo.LabelVo;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;

import java.util.List;

public interface LabelDao {

    List<String> queryLabelsByArticleId(int articleId);

    List<LabelVo> queryAllLabels();

    int getAdminLabelTotal();

    List<AdminLabelVo> getAdminLabelPage(@Param("page") Integer page, @Param("limit") Integer limit);

    Integer getAdminLabelArticleSize(@Param("id") Integer id);

    void updateHiddenById(@Param("id") Integer id, @Param("hiddenValue") int hiddenValue);

    void updateDataById(@Param("labelDto") LabelDto labelDto);

    Integer deleteLabelById(@Param("id") Integer id);

    void addLabel(@Param("name") String name);

    List<Integer> getLabelIdsByArticleId(@Param("id") Integer id);

    List<Integer> getArticleIdsByLabelId(@Param("labelId") Integer labelId);

    Integer getLabelSize();
}