package top.chao58.blog.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Statistics {

    private Integer id;
    private Integer articleFlow;
    private Integer user;
    private Integer articleComment;
    private Integer leave;
    private Integer link;
    private String date;

}
