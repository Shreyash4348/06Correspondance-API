package com.correspondance.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmailMessage(String to, String subject, String body, File file) {

		boolean isMailSent = false;

		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage); //Used to attach files, images and HTML text in email

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.addAttachment("Elig-Notice", file);

			
			mailSender.send(mimeMessage);
			isMailSent = true;

		} catch (MessagingException e) {

			e.printStackTrace();
		}

		return isMailSent;
	}
}

