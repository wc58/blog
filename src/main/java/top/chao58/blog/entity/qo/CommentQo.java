package top.chao58.blog.entity.qo;

import lombok.Data;

@Data
public class CommentQo extends BaseQo {

    private String name;
    private String content;

}
