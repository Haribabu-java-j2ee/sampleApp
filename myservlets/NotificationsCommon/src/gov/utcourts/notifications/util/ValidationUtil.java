package gov.utcourts.notifications.util;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.common.XMLConfig;
import gov.utcourts.notifications.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

	public static String validateAttachment(NoticeRequestBO noticeRequestBO) {
		StringBuilder sb = new StringBuilder();
		
		if (!TextUtil.isEmpty(noticeRequestBO.getFilename()) && !ValidationUtil.hasAttachment(noticeRequestBO.getAttachment())) 
			appendMessage(sb, "File name is not null, attachment is null");
		else if (TextUtil.isEmpty(noticeRequestBO.getFilename()) && ValidationUtil.hasAttachment(noticeRequestBO.getAttachment())) 
			appendMessage(sb, "File name is null, attachment is not null");
			
		if (ValidationUtil.hasAttachment(noticeRequestBO.getAttachment())) {
			if (noticeRequestBO.getAttachment().length > Constants.MAIL_FILESIZE){
				appendMessage(sb, "File size exceeds limit");
			}
			
			if (noticeRequestBO.getFilename().indexOf(".")<0){
				appendMessage(sb, "File name must contain an extension");
			}
			
			String ext = noticeRequestBO.getFilename().substring(noticeRequestBO.getFilename().indexOf(".")+1);
			if (!Constants.MAIL_FILE_EXTENSIONS.contains(ext.toUpperCase())){
				appendMessage(sb, "Invalid file extension - " + ext.toUpperCase());
			}
		}
		
		return sb.toString();
	}
	
	public static NoticeRequestBO validateNoticeRequest(NoticeRequestBO noticeRequestBO) {
		StringBuilder sb = new StringBuilder();
		
		try {
			String attachmentValidation = validateAttachment(noticeRequestBO);
			if (!TextUtil.isEmpty(attachmentValidation))
				appendMessage(sb, attachmentValidation);
			
			if (noticeRequestBO.getSystemId() == 0) 
				appendMessage(sb, "System not specified");
			
			if (noticeRequestBO.getNoticeType() == null) 
				appendMessage(sb, "Notice Type not specified");
			
			if (TextUtil.isEmpty(noticeRequestBO.getCaseMgmtId()) || Integer.parseInt(noticeRequestBO.getCaseMgmtId()) == 0) 
				appendMessage(sb, "CaseMgmtId not specified");
			
			if (TextUtil.isEmpty(noticeRequestBO.getSendTo())) 
				appendMessage(sb, "sendTo not specified");
			
			if (TextUtil.isEmpty(noticeRequestBO.getSendFrom())) 
				appendMessage(sb, "sendFrom not specified");
			
			if (TextUtil.isEmpty(noticeRequestBO.getSubject())) 
				appendMessage(sb, "subject not specified");
			
			if (TextUtil.isEmpty(noticeRequestBO.getContent())) 
				appendMessage(sb, "content not specified");
			
		} catch (Exception e) {
			appendMessage(sb, "Error - " + e.getMessage());
		}
		
		if (!TextUtil.isEmpty(sb.toString())) {
			noticeRequestBO.setValidationMessage(sb.toString());
		}
		sb = null;
		
		return noticeRequestBO;
	}
	
	public static boolean hasAttachment(byte[] attachment) {
		return attachment != null && attachment.length > 0;
	}
	
	public static String validateNoticeRequests(List<NoticeRequestBO> noticeRequests) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < (noticeRequests != null ? noticeRequests.size() : 0); i++) {
			NoticeRequestBO noticeRequestBO = validateNoticeRequest(noticeRequests.get(i));
			if (!TextUtil.isEmpty(noticeRequestBO.getValidationMessage())) {
				appendMessage(sb, noticeRequestBO.getValidationMessage());
				
				noticeRequests.set(i, noticeRequestBO);
			}
		}
		return sb.toString();
	}
	
	private static void appendMessage(StringBuilder sb, String message) {
		if (!TextUtil.isEmpty(sb.toString()))
			sb.append("<br><br>");
		
		sb.append(message);
	}
	
	public static List<String> getMailExtensions() {
		List<String> extensions = new ArrayList<String>();
		
		String[] exten = XMLConfig.getProperty("PROPERTY.mail.file.extensions").split(",");
		for (String str : exten){
			extensions.add(str.trim());
		}
		exten = null;
		
		return extensions;
	}
}
