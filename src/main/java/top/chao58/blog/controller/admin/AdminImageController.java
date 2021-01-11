package top.chao58.blog.controller.admin;

import com.qq.connect.utils.http.ImageItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Image;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.template.ImageTemplate;
import top.chao58.blog.properties.OssProperties;
import top.chao58.blog.properties.parse.PropertiesFactory;
import top.chao58.blog.service.ImageService;
import top.chao58.blog.service.OssService;
import top.chao58.blog.util.PictureType;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/admin/image")
@SystemLog(module = "后台图片管理")
public class AdminImageController {

    @Autowired
    private PropertiesFactory propertiesFactory;

    @Autowired
    private OssService ossService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/getPictureList")
    @SystemLog(method = "分页查询图片")
    public HashMap<String, Object> getPictureList(Integer currentPage, String type) {
        List<Image> images = imageService.getPictureList(currentPage, type);
        Integer pages = (imageService.getTotal(type) / 10) + 1;
        OssProperties ossProperties = (OssProperties) propertiesFactory.getByClass(OssProperties.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pages", pages);
        map.put("images", renderTemplate(images, ossProperties.getOssSite()));
        return map;
    }

    private String renderTemplate(List<Image> images, String ossSite) {
        StringBuilder template = new StringBuilder();
        for (Image image : images) {
            ImageTemplate imageTemplate = new ImageTemplate();
            imageTemplate.setPictureName(image.getOssName());
            String ossPath = image.getOssPath();
            imageTemplate.setPictureUrl(ossSite + ossPath);
            imageTemplate.setDeleteUrl(ossPath);
            template.append(imageTemplate.build());
        }
        return template.toString();
    }

    @PostMapping("/uploadCoverImage")
    @SystemLog(method = "上传封面图片")
    public ResponseVo<Object> uploadCoverImage(MultipartFile file) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        try {
            String visitPath = ossService.uploadPicture(file, PictureType.cover);
            responseVo.setData(Collections.singletonList(visitPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        responseVo.setCode(0);
        return responseVo;
    }

    @PostMapping("/uploadArticleImage")
    @SystemLog(method = "上传文章图片")
    public Map<String, Object> uploadArticleImage(@RequestParam("article") MultipartFile[] files) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            ArrayList<String> visitPath = ossService.uploadPicture(files, PictureType.article);
            map.put("errno", 0);
            map.put("data", visitPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/uploadDiaryImage")
    @SystemLog(method = "上传日记图片")
    public Map<String, Object> uploadDiaryImage(@RequestParam("diary") MultipartFile[] files) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            ArrayList<String> visitPath = ossService.uploadPicture(files, PictureType.diary);
            map.put("errno", 0);
            map.put("data", visitPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    @GetMapping("deleteImages")
    @SystemLog(method = "删除无用图片")
    public ResponseVo<Integer> deleteImages() {
        ResponseVo<Integer> responseVo = new ResponseVo<>();
        ossService.deleteImages(responseVo);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("deleteCoverImage")
    @SystemLog(method = "删除封面图片")
    public ResponseVo<Object> deleteCoverImage(String ossPath, @RequestParam(defaultValue = "false") Boolean force) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        boolean flag = ossService.deleteImage(ossPath, PictureType.cover, force);
        responseVo.setCode(flag ? 0 : 1);
        return responseVo;
    }

    @GetMapping("deleteArticleImage")
    @SystemLog(method = "删除文章图片")
    public ResponseVo<Object> deleteArticleImage(String ossPath, @RequestParam(defaultValue = "false") Boolean force) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        boolean flag = ossService.deleteImage(ossPath, PictureType.article, force);
        responseVo.setCode(flag ? 0 : 1);
        return responseVo;
    }

    @GetMapping("deleteDiaryImage")
    @SystemLog(method = "删除日记图片")
    public ResponseVo<Object> deleteDiaryImage(String ossPath, @RequestParam(defaultValue = "false") Boolean force) {
        ResponseVo<Object> responseVo = new ResponseVo<>();
        boolean flag = ossService.deleteImage(ossPath, PictureType.diary, force);
        responseVo.setCode(flag ? 0 : 1);
        return responseVo;
    }


}
