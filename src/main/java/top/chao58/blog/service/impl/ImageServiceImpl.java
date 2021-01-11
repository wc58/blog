package top.chao58.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chao58.blog.dao.ImageDao;
import top.chao58.blog.entity.po.Image;
import top.chao58.blog.service.ImageService;

import java.util.List;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public List<Image> getPictureList(Integer currentPage, String type) {
        currentPage = (currentPage - 1) * 10;
        return imageDao.getPictureList(currentPage, type);
    }

    @Override
    public Integer getTotal(String type) {
        return imageDao.getTotal(type);
    }
}
