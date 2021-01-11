package top.chao58.blog.entity.po;

import java.io.Serializable;

/**
 * (PageStat)实体类
 *
 * @author makejava
 * @since 2020-12-06 21:35:55
 */
public class PageStat implements Serializable {
    private static final long serialVersionUID = 372242951962805800L;

    private Object id;

    private Integer index;

    private Integer article;

    private Integer leave;

    private Integer link;

    private Integer diary;

    private Integer about;

    private Object day;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public Integer getDiary() {
        return diary;
    }

    public void setDiary(Integer diary) {
        this.diary = diary;
    }

    public Integer getAbout() {
        return about;
    }

    public void setAbout(Integer about) {
        this.about = about;
    }

    public Object getDay() {
        return day;
    }

    public void setDay(Object day) {
        this.day = day;
    }

}