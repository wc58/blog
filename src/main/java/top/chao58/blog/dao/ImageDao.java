package top.chao58.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Image;

import java.util.List;

public interface ImageDao {


    void addImage(@Param("image") Image image);

    Integer checkCoverImage(@Param("ossPath") String ossPath);

    void deleteImage(@Param("ossPath") String ossPath);

    Integer checkArticleImage(@Param("ossPath") String ossPath);

    Integer checkDiaryImage(@Param("ossPath") String ossPath);

    List<Image> getPictureList(@Param("currentPage") Integer currentPage, @Param("type") String type);

    Integer getTotal(@Param("type") String type);

    List<Image> getAllImages();

}
