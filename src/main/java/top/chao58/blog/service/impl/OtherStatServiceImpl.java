package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.OtherStatDao;
import top.chao58.blog.entity.po.OtherStat;
import top.chao58.blog.service.OtherStatService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OtherStat)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:55
 */
@Service("otherStatService")
public class OtherStatServiceImpl implements OtherStatService {
    @Resource
    private OtherStatDao otherStatDao;


}