package top.chao58.blog.entity.vo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class AdminCommentVo {

    private Integer id;
    private String title;
    private String name;
    private String content;
    private Date createTime;

}
