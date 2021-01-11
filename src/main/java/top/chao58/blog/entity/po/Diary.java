package top.chao58.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Diary {

    private Integer id;
    private String content;
    private String contentImg;
    private Integer hidden;
    private Integer del;
    private Date createTime;

}