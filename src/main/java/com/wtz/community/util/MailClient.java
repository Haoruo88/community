package com.wtz.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Description：
 * Date:2021/11/15 10:13
 **/
@Component
public class MailClient {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    @Autowired
    private JavaMailSender mailSender;
    //发件人-从配置文件读取
    @Value("${spring.mail.username}")
    private String from;
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);//辅助构建mail类
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("邮件发送失败：" + e.getMessage());
        }


    }
}
