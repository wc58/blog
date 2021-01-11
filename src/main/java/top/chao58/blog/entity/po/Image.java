package top.chao58.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Image {

    private Integer id;
    private String ossPath;
    private String ossName;
    private String originalName;
    private String type;
    private Date createTime = new Date();

}
