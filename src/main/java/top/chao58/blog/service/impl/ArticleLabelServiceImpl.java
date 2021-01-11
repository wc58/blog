package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.ArticleLabelDao;

import javax.annotation.Resource;

@Service("articleLabelService")
public class ArticleLabelServiceImpl {
    @Resource
    private ArticleLabelDao articleLabelDao;


}