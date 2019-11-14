package gov.utcourts.notifications.util;


import gov.utcourts.notifications.constants.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	Properties properties = new Properties();
	final static String PROPERTY_FILE = "/usr/local/WebSphere/AppServer/customlib/properties/webService.properties";
	String mailserver;
	String defaultSender;
	
	public EmailSender(){
		
	}
	
	
	public void sendSimpleEmailHTML(
			String[] toList, 
			String[] ccList, 
			String[] bccList,
			String subject, 
			String content, 
			String sender) throws Exception{
		
		    if (mailserver == null){
				loadProperty();
			}
			
		    if ((toList == null || toList.length ==0) && (bccList == null || bccList.length == 0) && (ccList == null || ccList.length == 0)){
				throw new Exception("At least one recipient is needed!");
			}
			
			if (subject == null || subject.trim().length()==0){
				throw new Exception("Subject cannot be empty!");
			}
			
			if (subject.trim().length()> 70){
				subject = subject.trim().substring(0, 70);
			}
			
			if (content == null || content.trim().length()==0){
				throw new Exception("Content cannot be empty!");
			}
			
				
			try {
			    Properties props = new Properties();
		        props.put("mail.smtp.host", mailserver);
		        Session session = Session.getDefaultInstance(props, null);
		        Message message = new MimeMessage(session);
		        if (sender != null)
		        	message.setFrom(new InternetAddress(sender));
		        else
		        	message.setFrom(new InternetAddress(defaultSender));
		        
		        if(toList != null) {
		            InternetAddress[] addresses = new InternetAddress[toList.length];
		            for(int i = 0; i < toList.length; i++) {
		                addresses[i] = new InternetAddress(toList[i]);
		            }
		            message.setRecipients(Message.RecipientType.TO, addresses);
		        }
		        if(ccList != null) {
		            InternetAddress[] addresses = new InternetAddress[ccList.length];
		            for(int i = 0; i < ccList.length; i++) {
		                addresses[i] = new InternetAddress(ccList[i]);
		            }
		            message.setRecipients(Message.RecipientType.CC, addresses);
		        }
		        if(bccList != null) {
		            InternetAddress[] addresses = new InternetAddress[bccList.length];
		            for(int i = 0; i < bccList.length; i++) {
		                addresses[i] = new InternetAddress(bccList[i]);
		            }
		            message.setRecipients(Message.RecipientType.BCC, addresses);
		        }
		        message.setSentDate(new Date());
		        message.setSubject(subject);
		        message.setContent(content, "text/html");
		       
		        Transport.send(message); 
			} catch (MessagingException e){
				throw new Exception("Unable to send email: " + e.getMessage());
			}
		
	}
	
	public void sendSimpleEmail(
			String[] toList, 
			String[] ccList, 
			String[] bccList,
			String subject, 
			String content, 
			String sender) throws Exception{
		
		    if (mailserver == null){
				loadProperty();
			}
			
		    if ((toList == null || toList.length ==0) && (bccList == null || bccList.length == 0) && (ccList == null || ccList.length == 0)){
				throw new Exception("At least one recipient is needed!");
			}
			
			if (subject == null || subject.trim().length()==0){
				throw new Exception("Subject cannot be empty!");
			}
			
			if (subject.trim().length()> 70){
				subject = subject.trim().substring(0, 70);
			}
			
			if (content == null || content.trim().length()==0){
				throw new Exception("Content cannot be empty!");
			}
			
				
			try {
			    Properties props = new Properties();
		        props.put("mail.smtp.host", mailserver);
		        Session session = Session.getDefaultInstance(props, null);
		        Message message = new MimeMessage(session);
		        if (sender != null)
		        	message.setFrom(new InternetAddress(sender));
		        else
		        	message.setFrom(new InternetAddress(defaultSender));
		        
		        if(toList != null) {
		            InternetAddress[] addresses = new InternetAddress[toList.length];
		            for(int i = 0; i < toList.length; i++) {
		                addresses[i] = new InternetAddress(toList[i]);
		            }
		            message.setRecipients(Message.RecipientType.TO, addresses);
		        }
		        if(ccList != null) {
		            InternetAddress[] addresses = new InternetAddress[ccList.length];
		            for(int i = 0; i < ccList.length; i++) {
		                addresses[i] = new InternetAddress(ccList[i]);
		            }
		            message.setRecipients(Message.RecipientType.CC, addresses);
		        }
		        if(bccList != null) {
		            InternetAddress[] addresses = new InternetAddress[bccList.length];
		            for(int i = 0; i < bccList.length; i++) {
		                addresses[i] = new InternetAddress(bccList[i]);
		            }
		            message.setRecipients(Message.RecipientType.BCC, addresses);
		        }
		        message.setSentDate(new Date());
		        message.setSubject(subject);
		        message.setText(content);
		       
		        Transport.send(message); 
			} catch (MessagingException e){
				throw new Exception("Unable to send email: " + e.getMessage());
			}
		
	}
	
	public static String sendTextVerificationCode(String mobileNumber) throws Exception {
		String verificationCode = null;
		if (TextUtil.isEmpty(mobileNumber))
			throw new Exception("Cannot send text notification.  Email address is missing.");
		
		try {
			String smsEmailAddress = MobileUtil.getMMSAddressPlain(mobileNumber);
			int randomInt = ((int)(Math.random() * 9000) + 1000);
			verificationCode = Integer.toString(randomInt);
			String subject = verificationCode + " - Verifciation";
			String content = verificationCode + " is your Utah State Courts verification code.";
		
			String[] toList = new String[] { smsEmailAddress };
			String[] ccList = null;
			String[] bccList = null;
			String sender = Constants.EMAIL_WS_SENDEREMAIL;
			
			//Because some phones cannot handle complicated email body
			//we have to use a simpler way to send the code.
			EmailSender emailSender = new EmailSender();
			emailSender.sendSimpleEmail(toList, ccList, bccList, subject, content, sender);
			
			emailSender = null;
			toList = null;
			ccList = null;
			bccList = null;
		} catch (Exception e) {
			throw e;
		}
		
		return verificationCode;
		
	}
	
	public static String sendEmailVerificationCode(String emailAddr, String systemName) throws Exception {
		String verificationCode = null;
		if (TextUtil.isEmpty(emailAddr))
			throw new Exception("Cannot send email notification.  Email address is missing.");
		
		try {
			int randomInt = ((int)(Math.random() * 9000) + 1000);
			verificationCode = Integer.toString(randomInt);
			
			StringBuffer sb = new StringBuffer();
			sb.append("Your request for email notifications has been received. ")
		    .append("<br/><br/>" + verificationCode + " is your Utah State Courts verification code.")
			.append("<br/><br/>Please note that your request will not be reviewed until you verify your email address.<br/><br/>Please contact ")
			.append(Constants.COMMONEMAILVERIFY_HELPDESK_EMAIL)
			.append(" for questions relating to the request.")
			.append("<br><br><font size='1'>DO NOT REPLY TO THIS EMAIL MESSAGE.  THIS IS A SYSTEM GENERATED MESSAGE.<br>This message was created by " + systemName + ".</font>");
			
			String subject = "Email Verification - Utah State Courts";
			String content = sb.toString();

			String[] toList = new String[] { emailAddr };
			String[] ccList = null;
			String[] bccList = null;
			String sender = Constants.EMAIL_WS_SENDEREMAIL;
			
			//Because some phones cannot handle complicated email body
			//we have to use a simpler way to send the code.
			EmailSender emailSender = new EmailSender();
			emailSender.sendSimpleEmailHTML(toList, ccList, bccList, subject, content, sender);
			
			emailSender = null;
			toList = null;
			ccList = null;
			bccList = null;
		} catch (Exception e) {
			throw e;
		}
		
		return verificationCode;
		
	}
	
	private void loadProperty() throws Exception{
		
		try {
			properties.load(new FileInputStream(PROPERTY_FILE));
                               
		} catch (FileNotFoundException e) {
			throw new Exception("Property file is missing!");
		} catch (IOException e) {
			throw new Exception("IO error while attempting to retrieve the property file!");
		}
		
		mailserver = (String) properties.get("mail.smtp.host");
		defaultSender = (String)properties.get("mail.default.sender");
		
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args) {
		try { 
			System.out.println("code: " + sendEmailVerificationCode("fangz@utcourts.gov", "ODR"));
		} catch (Exception e) {
			System.out.println("MobileUtil error: " + e.getMessage());
		}
	}

}
