package com.momo.springbootemail.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @author: MQG
 * @date: 2018/11/8
 */
@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${spring.mail.to}")
    private String receiver;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("简单文本邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }

    public void sendHtmlMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("html邮件");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
            helper.setSentDate(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    public void sendAttachmentMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("带附件的邮件");
            helper.setText("带附件的邮件内容");
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/1.jpg"));
            // 加入邮件
            helper.addAttachment("图片.jpg", file);
            helper.setSentDate(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

}
