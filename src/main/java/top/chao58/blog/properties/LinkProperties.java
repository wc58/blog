package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LinkProperties {


    //主要内容
    private String header;
    private String description;
    private String linkSize;


    private String title;
    private String site;
    private String icon;
    private String siteDescription;

    private String bottomDescription;

}
