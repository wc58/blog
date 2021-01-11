package top.chao58.blog.entity.vo.admin;

import lombok.Data;

import java.util.Date;

@Data
public class LogVo {

    private Integer id;
    private String name;
    private String ip;
    private String module;
    private String method;
    private String client;
    private Date currentDate;
    private Integer totalTime;
    private Integer result;
    private Integer type;

}
