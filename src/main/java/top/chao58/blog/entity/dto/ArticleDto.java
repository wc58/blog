package top.chao58.blog.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDto {

    private Integer id;

    private String title;

    private String des;

    private String cover;

    private String content;

    private String status;

    private Boolean show;

    private Boolean top;

    private Boolean login;

    private Integer[] labels;

    private String author;

    private Date modifyTime = new Date();

}
