package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OtherProperties {

    private String visitorSize;
    private String buildDate;
    private String practiceDate;
}
