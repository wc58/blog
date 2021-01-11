package top.chao58.blog.entity.vo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class AdminLinkVo {

    private Integer id;

    private String name;

    private String title;

    private String icon;

    private String des;

    private String url;

    private Integer sort;

    private Integer hidden;

    private Date createTime;

    private Date modifyTime;
}
