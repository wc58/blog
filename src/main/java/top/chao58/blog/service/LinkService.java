package top.chao58.blog.service;


import top.chao58.blog.entity.dto.LinkDto;
import top.chao58.blog.entity.po.Link;
import top.chao58.blog.entity.vo.LinkVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminLabelVo;
import top.chao58.blog.entity.vo.admin.AdminLinkVo;

import java.util.List;

public interface LinkService {
    List<LinkVo> getLinks();

    void getAdminLinkPage(Integer page, Integer limit, String siteName, ResponseVo<AdminLinkVo> responseVo);

    void updateHiddenById(Integer id, Boolean isHidden);

    void updateDataById(LinkDto labelDto);

    void deleteLinkById(ResponseVo<AdminLabelVo> responseVo, Integer id);

    void addLink(Link link);

    Integer getLinkSize();
}