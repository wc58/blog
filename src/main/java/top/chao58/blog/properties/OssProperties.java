package top.chao58.blog.properties;

import lombok.Data;

@Data
public class OssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String ossSite;
}
