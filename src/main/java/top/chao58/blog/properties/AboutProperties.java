package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AboutProperties {

    private String header;
    private String description;
    private String aboutImage;


}
