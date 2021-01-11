package top.chao58.blog.service;

import cn.hutool.core.util.RandomUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.chao58.blog.dao.ImageDao;
import top.chao58.blog.entity.po.Image;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.properties.OssProperties;
import top.chao58.blog.properties.parse.PropertiesFactory;
import top.chao58.blog.util.PictureType;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OssService {

    @Autowired
    private PropertiesFactory propertiesFactory;

    @Autowired
    private ImageDao imageDao;

    private OssProperties ossProperties;
    private OSS oss;

    @PostConstruct
    public void init() {
        ossProperties = (OssProperties) propertiesFactory.getByClass(OssProperties.class);
        oss = getOss();
    }


    public void deleteImages(ResponseVo<Integer> responseVo) {
        List<Image> images = imageDao.getAllImages();
        List<Integer> count = Arrays.asList(0, 0, 0);
        responseVo.setData(count);
        images.forEach(image -> {
            String type = image.getType();
            String ossPath = image.getOssPath();
            switch (type) {
                case "cover/": {
                    boolean flag = deleteImage(ossPath, PictureType.cover, false);
                    if (flag) {
                        count.set(0, count.get(0) + 1);
                    }
                    break;
                }
                case "article/": {
                    boolean flag = deleteImage(ossPath, PictureType.article, false);
                    if (flag) {
                        count.set(1, count.get(1) + 1);
                    }
                    break;
                }
                case "diary/": {
                    boolean flag = deleteImage(ossPath, PictureType.diary, false);
                    if (flag) {
                        count.set(2, count.get(2) + 1);
                    }
                    break;
                }
            }
        });
    }

    /**
     * 删除封面
     */
    public boolean deleteImage(String ossPath, PictureType pictureType, Boolean force) {
        ossPath = ossPath.replace(ossProperties.getOssSite(), "");
        if (!force) {//是否强制
            //删除前先判断一下是否用其应用该图片
            switch (pictureType) {
                case cover:
                    Integer coverCount = imageDao.checkCoverImage(ossPath);
                    if (coverCount > 0) {
                        return false;
                    }
                    break;
                case article:
                    Integer articleCount = imageDao.checkArticleImage(ossPath);
                    if (articleCount > 0) {
                        return false;
                    }
                    break;
                case diary:
                    Integer diaryCount = imageDao.checkDiaryImage(ossPath);
                    if (diaryCount > 0) {
                        return false;
                    }
                    break;
            }
        }
        oss.deleteObject(ossProperties.getBucketName(), ossPath);
        //删除数据库
        imageDao.deleteImage(ossPath);
        return true;
    }

    /**
     * 上传单张照片
     */
    public String uploadPicture(MultipartFile file, PictureType pictureType) throws IOException {
        ArrayList<String> visitPaths = uploadPicture(new MultipartFile[]{file}, pictureType);
        return visitPaths.get(0);
    }

    /**
     * 上传多张照片
     */
    public ArrayList<String> uploadPicture(MultipartFile[] files, PictureType pictureType) throws IOException {
        ArrayList<String> visitPaths = new ArrayList<>();
        String type;
        String path = new DateTime().toString("yyyy/MM/dd/");
        switch (pictureType) {
            case cover:
                type = "cover/";
                break;
            case diary:
                type = "diary/";
                break;
            case article:
                type = "article/";
                break;
            default:
                type = "/";
        }
        for (MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf('.'));
            String ossName = RandomUtil.randomString(6) + suffix;
            //最终路径
            String ossPath = type + path + ossName;
            InputStream inputStream = file.getInputStream();
            oss.putObject(ossProperties.getBucketName(), ossPath, inputStream);
            //-------------
            //插入到数据库中
            Image image = new Image();
            image.setOssPath(ossPath);
            image.setOssName(ossName);
            image.setOriginalName(originalName);
            image.setType(type);
            imageDao.addImage(image);
            //-------------
            visitPaths.add(ossProperties.getOssSite() + ossPath);
        }
        return visitPaths;
    }


    private OSS getOss() {
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        //开始连接OSS
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }


}
