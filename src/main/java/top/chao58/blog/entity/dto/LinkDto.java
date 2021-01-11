package top.chao58.blog.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LinkDto {

    private Integer id;
    private String title;
    private String logo;
    private String dsc;
    private String url;
    private String sort;
    private Date modifyTime = new Date();

}
