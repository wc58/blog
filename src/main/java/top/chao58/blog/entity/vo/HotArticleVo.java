package top.chao58.blog.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 首页的热门文章
 */
@Data
public class HotArticleVo {

    private Integer id;

    private String title;

    private String des;

    private String cover;

    private Date createTime;

}
