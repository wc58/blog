package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ArticleProperties {

    private String showSize;
    private String hotSize;
    private String site;



}
