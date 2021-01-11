package top.chao58.blog.entity.po;

import java.io.Serializable;

/**
 * (OtherStat)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:55
 */
public class OtherStat implements Serializable {
    private static final long serialVersionUID = -22664160206852140L;

    private Object id;

    private String comment;

    private String leave;

    private String user;

    private String article;

    private String link;

    private Object day;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Object getDay() {
        return day;
    }

    public void setDay(Object day) {
        this.day = day;
    }

}