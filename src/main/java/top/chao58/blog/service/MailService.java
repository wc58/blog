package top.chao58.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chao58.blog.properties.MailProperties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class MailService {


    @Autowired
    private MailProperties mailProperties;

    public boolean sendMail(String receiveMailAccount, String title, String subject, String content) {
        //获取发送者信息
        String sendEmailAccount = mailProperties.getSendEmailAccount();
        String sendEmailPassword = mailProperties.getSendEmailPassword();
        String sendEmailSMTPHost = mailProperties.getSendEmailSMTPHost();
        try {
            Session session = getSession(sendEmailSMTPHost);
            MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, title, subject, content);
            //连接邮箱服务器
            Transport transport = session.getTransport();
            transport.connect(sendEmailAccount, sendEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static Session getSession(String sendEmailSMTPHost) {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", sendEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        return session;
    }

    public static MimeMessage createMimeMessage(Session session, String sendEmailAccount, String receiveMailAccount, String title, String subject, String content) throws Exception {
        MimeMessage message = new MimeMessage(session);
        //标题
        message.setFrom(new InternetAddress(sendEmailAccount, title, "UTF-8"));
        //发送的地址
        InternetAddress[] toAddr = new InternetAddress[2];
        toAddr[0] = new InternetAddress(sendEmailAccount);
        toAddr[1] = new InternetAddress(receiveMailAccount);
        message.setRecipients(MimeMessage.RecipientType.TO, toAddr);
        //主题
        message.setSubject(subject, "UTF-8");
        //内容
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

}
