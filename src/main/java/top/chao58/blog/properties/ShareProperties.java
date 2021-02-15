package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ShareProperties {

    private String logo;
    private String backgroundImage;
    private String copyright;
    private String record_title;
    private String record_url;

}
