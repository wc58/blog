package top.chao58.blog.entity.vo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class AdminDiaryVo {
    private Integer id;

    private String content;

    private Object hidden;

    private Date createTime;

    private Date modifyTime;
    private Date deleteTime;

}
