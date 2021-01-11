package top.chao58.blog.properties;

import lombok.Data;

@Data
public class IndexProperties {

    //主要内容
    private String header;
    private String description;
    private String toIndexTitle;
    private String toIndexUrl;
    private String oneBackgroundPictureUrl;
    private String twoBackgroundPictureUrl;

    //文章内容及其设置
    private String article_header;
    private String article_description;
    private String article_pcSize;
    private String article_phoneSize;

    //爱好信息
    private String me_header;
    private String me_description;

    //诗词
    private String poem_header;
    private String poem_description;

    //联系信息
    private String bottom_address;
    private String bottom_qq;
    private String bottom_email;
}
