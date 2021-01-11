package top.chao58.blog.entity.po;

import lombok.Data;

import java.util.Date;

@Data
public class Log {

    private Integer id;
    private Integer uid;
    private String ip;
    private String module;
    private String method;
    private String client;
    private Date currentDate;
    private Integer totalTime;
    private Integer result;
    private Integer type;

}
