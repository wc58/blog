package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.LabelDao;
import top.chao58.blog.entity.dto.LabelDto;
import top.chao58.blog.entity.vo.LabelVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.service.LabelService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Label)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:49
 */
@Service("labelService")
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelDao labelDao;


    @Override
    public List<String> getLabelsByArticleId(int articleId) {
        return labelDao.queryLabelsByArticleId(articleId);
    }

    @Override
    public List<LabelVo> getAllLabels() {
        return labelDao.queryAllLabels();
    }


    /*=============start=====================admin======================================================================================*/
    @Override
    public void getAdminLabelPage(ResponseVo<AdminLabelVo> responseVo, Integer page, Integer limit) {
        //标签总数
        responseVo.setCount(labelDao.getAdminLabelTotal());
        page = (page - 1) * limit;//计算起始处
        //当前页的标签信息
        List<AdminLabelVo> adminLabelPage = labelDao.getAdminLabelPage(page, limit);
        //查出该标签对应的文章数量
        adminLabelPage.forEach(adminLabelVo -> {
            adminLabelVo.setArticleSize(labelDao.getAdminLabelArticleSize(adminLabelVo.getId()));
        });
        responseVo.setData(adminLabelPage);
    }

    @Override
    public void updateHiddenById(Integer id, Boolean isHidden) {
        int hiddenValue = isHidden ? 0 : 1;
        labelDao.updateHiddenById(id, hiddenValue);
    }

    @Override
    public void updateDataById(LabelDto labelDto) {
        labelDao.updateDataById(labelDto);
    }

    @Override
    public void deleteLabelById(ResponseVo<AdminLabelVo> responseVo, Integer id) {
        Integer size = labelDao.getAdminLabelArticleSize(id);
        //该标签下面还有文章，所有不能删除
        if (size != 0) {
            responseVo.setCode(1);
            return;
        }
        Integer count = labelDao.deleteLabelById(id);
        //表示没有删除成功
        if (count == 0) {
            responseVo.setCode(1);
            return;
        }
        responseVo.setCode(0);
    }

    @Override
    public void addLabel(String name) {
        labelDao.addLabel(name);
    }

    @Override
    public List<LabelVo> getSelectLabels(Integer articleId) {
        List<LabelVo> labels = labelDao.queryAllLabels();
        List<Integer> ids = labelDao.getLabelIdsByArticleId(articleId);
        /*找出文章对应的标签*/
        for (LabelVo label : labels) {
            for (Integer id : ids) {
                if (label.getId().equals(id)) {
                    label.setSelected(true);
                }
            }
        }
        return labels;
    }

    @Override
    public List<Integer> getArticleIdsByLabelId(Integer labelId) {
        return labelDao.getArticleIdsByLabelId(labelId);
    }

    @Override
    public Integer getLabelSize() {
        return labelDao.getLabelSize();
    }

    /*=============end=====================admin======================================================================================*/


}