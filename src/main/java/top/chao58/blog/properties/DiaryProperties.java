package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DiaryProperties {


    private String startTime;
    private String endTime;

}
