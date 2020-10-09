package com.kuake.spingboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spingboot04AsyncApplicationTests {

	@Autowired
	private JavaMailSenderImpl mailSender;
	@Test
	public void testsender(){
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo("liuzihao_lzh@163.com");
		simpleMailMessage.setSubject("重要通知");
		simpleMailMessage.setText("今天晚上八点 天台见");
		//记得要设置一下 发送方的邮箱
		simpleMailMessage.setFrom("liuzihao_lhm@163.com");
		mailSender.send(simpleMailMessage);
	}

	//发送一个复杂的 带有附件的邮件
	@Test
	public void contextLoads() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//@multipart是否创建一个多部分文件
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

		mimeMessageHelper.setSubject("这是一个带有附件的邮件3");
		mimeMessageHelper.setText("附件邮件3");

		// 添加附件
		mimeMessageHelper.addAttachment("1.txt",new InputStreamSource(){

			@Override
			public InputStream getInputStream() throws IOException {
				FileInputStream fileInputStream = new FileInputStream(new File("F:\\1.txt"));
				return fileInputStream;
			}
		},"text/html;charset=utf-8");

		mimeMessageHelper.addAttachment("8d8ac84.jpg",new File("C:\\Users\\Administrator\\Pictures\\8d8ac84.jpg"));
		//设置发送者
		mimeMessageHelper.setTo("liuzihao_lzh@163.com");
		//设置接收者
		mimeMessageHelper.setFrom("liuzihao_lhm@163.com");
		//发送邮件
		mailSender.send(mimeMessage);


	}

}
