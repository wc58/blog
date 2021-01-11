package top.chao58.blog.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * (Label)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:49
 */
public class Label implements Serializable {
    private static final long serialVersionUID = 258082020354020242L;

    private Integer id;

    private String title;

    private String sort;

    private Object hidden;

    private Date createTime;


    public Object getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Object getHidden() {
        return hidden;
    }

    public void setHidden(Object hidden) {
        this.hidden = hidden;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}