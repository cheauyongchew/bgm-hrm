package com.beans.util.email;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import com.beans.util.config.ConfigurationHolder;

public class EmailSender {
	
	private ConfigurationHolder config;
	public EmailSender() {
		try {
			config = new ConfigurationHolder();
		} catch(ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void sendEmail(Email email) throws EmailException{
		email.setHostName(config.getString("mail.smtp.host"));
		email.setSmtpPort(config.getInt("mail.smtp.port"));
		email.setSSLOnConnect(config.getBoolean("mail.smtp.ssl"));
		if(config.getBoolean("mail.smtp.auth")) {
			email.setAuthentication(config.getString("mail.smtp.sender.username"), config.getString("mail.smtp.sender.password"));
		}
		email.setFrom(config.getString("mail.smtp.sender.address"));
		
		email.send();
	}
}
