package top.chao58.blog.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MailProperties {
    private String sendEmailAccount;
    private String sendEmailPassword;
    private String sendEmailSMTPHost;

}
