package top.chao58.blog.service;

import top.chao58.blog.entity.po.Image;

import java.util.List;

public interface ImageService {
    List<Image> getPictureList(Integer currentPage, String type);

    Integer getTotal(String type);
}
