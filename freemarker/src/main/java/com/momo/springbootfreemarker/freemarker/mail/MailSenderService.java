package com.momo.springbootfreemarker.freemarker.mail;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  //自动注入

    public void sendTemplateMail() {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("模板邮件");

            Map<String, Object> model = new HashMap();
            model.put("userName", "小明");

            // 读取模板
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("index.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
