package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.LinkDao;
import top.chao58.blog.entity.dto.LinkDto;
import top.chao58.blog.entity.po.Link;
import top.chao58.blog.entity.vo.LinkVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.entity.vo.admin.AdminLinkVo;
import top.chao58.blog.service.LinkService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Link)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:51
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkDao linkDao;

    @Override
    public List<LinkVo> getLinks() {
        return linkDao.queryLinks();
    }

    /*=============start=====================admin======================================================================================*/
    @Override
    public void getAdminLinkPage(Integer page, Integer limit, String siteName, ResponseVo<AdminLinkVo> responseVo) {
        responseVo.setCount(linkDao.getAdminLinkTotal(siteName));
        page = (page - 1) * limit;
        responseVo.setData(linkDao.getAdminLinkPage(page, limit, siteName));
    }

    @Override
    public void updateHiddenById(Integer id, Boolean isHidden) {
        int hiddenValue = isHidden ? 0 : 1;
        linkDao.updateHiddenById(id, hiddenValue);
    }

    @Override
    public void updateDataById(LinkDto linkDto) {
        linkDao.updateDataById(linkDto);
    }

    @Override
    public void deleteLinkById(ResponseVo<AdminLabelVo> responseVo, Integer id) {
        int count = linkDao.deleteLinkById(id);
        if (count == 1) {
            responseVo.setCode(0);
        } else {
            responseVo.setCode(1);
        }
    }

    @Override
    public void addLink(Link link) {
        Date date = new Date();
        link.setSort(0);
        link.setHidden(0);
        link.setCreateTime(date);
        link.setModifyTime(date);
        linkDao.addLink(link);
    }

    @Override
    public Integer getLinkSize() {
        return linkDao.getLinkSize();
    }
    /*=============end=====================admin======================================================================================*/
}