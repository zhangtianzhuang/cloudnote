package com.ztz.utils;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

import com.ztz.entity.Email;

public class MailSender {
	private static MailSender mailSender;
	private LinkedBlockingQueue<Email> linkedBlockingQueue;
	private Timer timer;
	private TimerTask timerTask;
	private int queueSize;

	// 私有的构造方法
	private MailSender() {
		queueSize = 100;
		linkedBlockingQueue = new LinkedBlockingQueue<Email>(queueSize);
		timerTask = new TimerTask() {
			@Override
			public void run() {
				handleTask();
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 0);
	}

	// 发送者执行的任务
	private void handleTask() {
		while (true) {
			try {
				send(linkedBlockingQueue.take());
			} catch (InterruptedException e) {
				System.err.println("handleTask");
			}
		}
	}

	// 发送
	private void send(final Email email) {
		try {
			// 得到一个系统配置
			Properties properties = new Properties();
			MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
			mailSSLSocketFactory.setTrustAllHosts(true);
			Authenticator authenticator = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email.getUserName(), email.getPassword());
				}
			};
			properties.put("mail.smtp.host", email.getMailHost());
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);
			properties.setProperty("mail.smtp.port", email.getMailHostPort());
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(properties, authenticator);
			session.setDebug(true);
			// 构建一封邮件
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email.getFrom()));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
			message.setSubject(email.getTitle());
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(email.getContent(), "text/plain;charset=UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
			message.setSentDate(new Date());
			// 发送一封邮件
			Transport.send(message);
		} catch (Exception e) {
			System.err.println("发送邮件异常！");
			e.printStackTrace();
		}
	}

	// 发送一封邮件
	public boolean sendAEmail(Email email) {
		try {
			linkedBlockingQueue.put(email);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

	// 获得邮件发送者
	public synchronized static MailSender getMailSender() {
		if (mailSender == null) {
			mailSender = new MailSender();
		}
		return mailSender;
	}

}
