package com.beans.api.mail.notification;

	public class Sender {

		public String sender;
		public String receiver;
		public String cc;
		public String bcc;
		public String body;
		public String subject;
		// public File attachment; 
		public String senderPassword;
		public String text;
		
		
		
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getSenderPassword() {
			return senderPassword;
		}
		public void setSenderPassword(String senderPassword) {
			this.senderPassword = senderPassword;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public String getReceiver() {
			return receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public String getCc() {
			return cc;
		}
		public void setCc(String cc) {
			this.cc = cc;
		}
		public String getBcc() {
			return bcc;
		}
		public void setBcc(String bcc) {
			this.bcc = bcc;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		/*public File getAttachment() {
			return attachment;
		}
		public void setAttachment(File attachment) {
			this.attachment = attachment;
		}*/
		
		public static void main(String[] args){
			System.out.println("in main method");
			
			Sender e = new Sender();
			e.setSender("pradeepchinta432@gmail.com");
			e.setReceiver("pradeepchinta.nxtg@gmail.com");
	e.setText("new text");
	e.setSenderPassword("pradeep12.");
	e.setSubject("mail notification");
			new SendEmail().sendMail(e);
			
		}
		
	}

	
	

