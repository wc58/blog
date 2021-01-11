package top.chao58.blog.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ReadArticleVo {
    private int id;
    private String title;
    private String content;

    private String author;
    private Integer flow;

    private Integer login;

    private Date createTime;
    private Date modifyTime;

}
