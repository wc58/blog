package top.chao58.blog.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class LeaveVo {

    private Integer id;
    private String content;
    private Date createTime;

    private String name;
    private String parentName;
    private String icon;
    private String tid;


    private List<LeaveVo> childrenLeaves = new ArrayList<>();

}
