package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LeaveProperties {


    //主要内容
    private String header;
    private String description;
    //    private String commentSize;
    private String placeholder;

}
