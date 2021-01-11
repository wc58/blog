package top.chao58.blog.entity.po;

import lombok.Data;


@Data
public class ArticleCore {

    private Integer id;

    private String title;

    private String des;

    private String cover;

    private String content;

    private String contentImg;

}