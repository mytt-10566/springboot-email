package com.momo.springbootfreemarker.freemarker;

import com.momo.springbootfreemarker.freemarker.mail.MailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemarkerApplicationTests {

	@Autowired
	private MailSenderService mailSenderService;
	
	@Test
	public void testSendTemplateEmail() {
		mailSenderService.sendTemplateMail();
	}

}
