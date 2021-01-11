package top.chao58.blog.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Link)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:50
 */
@Data
public class Link {

    private Integer id;

    private Integer uid;

    private String title;

    private String icon;

    private String des;

    private String url;

    private Integer sort;

    private Integer hidden;

    private Date createTime;

    private Date modifyTime;

}