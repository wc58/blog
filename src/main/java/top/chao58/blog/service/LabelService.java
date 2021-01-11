package top.chao58.blog.service;

import top.chao58.blog.entity.dto.LabelDto;
import top.chao58.blog.entity.vo.LabelVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;

import java.util.List;

public interface LabelService {


    List<String> getLabelsByArticleId(int articleId);

    List<LabelVo> getAllLabels();

    void getAdminLabelPage(ResponseVo<AdminLabelVo> responseVo, Integer page, Integer limit);

    void updateHiddenById(Integer id, Boolean isHidden);

    void updateDataById(LabelDto labelDto);

    void deleteLabelById(ResponseVo<AdminLabelVo> responseVo, Integer id);

    void addLabel(String name);

    List<LabelVo> getSelectLabels(Integer id);

    List<Integer> getArticleIdsByLabelId(Integer labelId);

    Integer getLabelSize();


}