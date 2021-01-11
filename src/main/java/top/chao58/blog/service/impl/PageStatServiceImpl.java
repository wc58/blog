package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.PageStatDao;
import top.chao58.blog.entity.po.PageStat;
import top.chao58.blog.service.PageStatService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PageStat)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:55
 */
@Service("pageStatService")
public class PageStatServiceImpl implements PageStatService {
    @Resource
    private PageStatDao pageStatDao;

}