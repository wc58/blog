package top.chao58.blog.entity.vo.admin;

import lombok.Data;
import top.chao58.blog.entity.vo.ArticleListVo;

import java.util.Date;

@Data
public class AdminArticleVo extends ArticleListVo {
    private int hidden;
    private String author;
    private String content;
    private Date modifyTime;
    private Date deleteTime;
}
