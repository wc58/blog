package top.chao58.blog.properties;

import lombok.Data;

@Data
public class MailProperties {
    private String sendEmailAccount;
    private String sendEmailPassword;
    private String sendEmailSMTPHost;

}
