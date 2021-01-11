package top.chao58.blog.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * (Leave)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:50
 */
public class Leave implements Serializable {
    private static final long serialVersionUID = 836321513925297614L;

    private Object id;

    private Integer pid;

    private Integer uid;

    private String content;

    private Object hidden;

    private Date createTime;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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