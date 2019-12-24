package com.scrotify.flexibank.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.scrotify.flexibank.costants.Constant;
import com.scrotify.flexibank.dto.EmailDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailService {

	public String sendEmail(EmailDto emailDto) {

		String host = "smtp.gmail.com";
		final String user = "scrotify.flexiservice@gmail.com";
		final String password = Constant.EMAIL_PASSWORD;

		String to = emailDto.getEmail();

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Your credit card generated");
			message.setText("hello "+emailDto.getName() + " your credit card has been successfully generated below are its details \n\n"+" Credit card Number : " + emailDto.getCreditCardNumber() + "\n" + "Credit Card CVV      : "
					+ emailDto.getCvv() + "\n" + "Expiry Date              : " + emailDto.getExpiryDate().getMonth()
					+ "  " + emailDto.getExpiryDate().getYear() + "\n" + "Credit Card PIN       : " + emailDto.getPin()
					+ "\n\n" + "please dont share your credentials with anyone bank will never ask for it");

			Transport.send(message);
			log.info("registration email sent successfully");
			return "success";
			
		} catch (MessagingException e) {
			log.error(e.getMessage());
			return "failed";
		}
	}

}
