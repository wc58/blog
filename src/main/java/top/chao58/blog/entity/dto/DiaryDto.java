package top.chao58.blog.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryDto {

    private Integer id;

    private String content;
    private String contentImg;

    private Date modifyTime = new Date();

}
