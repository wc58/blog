package top.chao58.blog.entity.po;

import java.io.Serializable;

/**
 * (ArticleLabel)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:47
 */
public class ArticleLabel implements Serializable {
    private static final long serialVersionUID = -42502092830573748L;

    private Object id;

    private Integer articleId;

    private Integer labelId;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

}