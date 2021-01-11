package top.chao58.blog.entity.vo;

import lombok.Data;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

@Data
public class ResponseVo<T> {

    private Integer id;
    private int code;
    private int count;
    private String message;
    private List<T> data;

}
