package top.chao58.blog.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleOther implements Serializable {

    private Integer id;

    private String author;

    private String status;

    private Integer flow;

    private Integer login;

    private Integer top;

    private Integer hidden;

    private Integer del;

    private Date createTime;

    private Date modifyTime;


}