package top.chao58.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Object del;

    private Date createTime;

    private Date modifyTime;


}