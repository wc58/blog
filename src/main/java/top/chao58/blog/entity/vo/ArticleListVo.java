package top.chao58.blog.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleListVo {

    private int id;
    private String title;
    private String des;
    private String cover;


    private String status;
    private Integer login;
    private Integer flow;
    private Integer commentSize;
    private int top;
    private Date createTime;

    private List<String> labels;

}
