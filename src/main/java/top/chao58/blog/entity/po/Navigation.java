package top.chao58.blog.entity.po;

import lombok.Data;

@Data
public class Navigation {

    private Integer id;

    private String title;

    private String url;

    private Integer left;

    private Integer bottom;

    private Integer hidden;

}