package com.zlp.soap.util;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	
	final static String CONF_FILE = "email";

	/**
	 * 发送邮件的props文件
	 */
	private final static transient Properties props = System.getProperties();
	private final static ResourceBundle rb;
	private static String host = null;
	private static String account = null;
	private static String password = null;
	private static MailAuthenticator authenticator;
	/**
	 * 邮箱session
	 */
	private static transient Session session;

	static {
		rb = ResourceBundle.getBundle(CONF_FILE);
		host = rb.getString("email.host");
		account = rb.getString("email.account");
		password = rb.getString("email.passwd");
	}
	
	private static MailUtil mail;
	
	public static MailUtil getInstance() {
		if(mail == null) {
			mail = new MailUtil();
		}
		return mail;
	}
	
	private MailUtil() {
		init();
	}
	
	//
	private final static void init() {
		// 初始化props
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		// 验证
		authenticator = new MailAuthenticator(account, password);
		// 创建session
		session = Session.getInstance(props, authenticator);
	}//*/
	
	/**
	 * 发送邮件
	 * 
	 * @param recipient
	 *            收件人邮箱地址
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public boolean send(String recipient, String subject, Object content) {
		try {
			// 创建mime类型邮件
			final MimeMessage message = new MimeMessage(session);
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
			// 发送
			Transport.send(message);
			return true;
		} catch(AddressException e) {
			e.printStackTrace();
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 群发邮件
	 * 
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public boolean send(List<String> recipients, String subject, Object content) {
		try {
			// 创建mime类型邮件
			final MimeMessage message = new MimeMessage(session);
			// 设置发信人
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人们
			final int num = recipients.size();
			InternetAddress[] addresses = new InternetAddress[num];
			for (int i = 0; i < num; i++) {
				addresses[i] = new InternetAddress(recipients.get(i));
			}
			message.setRecipients(RecipientType.TO, addresses);
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
			// 发送
			Transport.send(message);
			return true;
		} catch(AddressException e) {
			e.printStackTrace();
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}
