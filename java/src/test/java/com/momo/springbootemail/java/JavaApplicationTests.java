package com.momo.springbootemail.java;

import com.momo.springbootemail.java.service.MailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaApplicationTests {

    @Autowired
    private MailSenderService mailSenderService;

    @Test
    public void testSendTextMessage() {
        mailSenderService.sendSimpleMail();
    }

    @Test
    public void testSendHtmlMessage() {
        mailSenderService.sendHtmlMail();
    }

    @Test
    public void testSendAttachmentMessage() {
        mailSenderService.sendAttachmentMail();
    }

}
