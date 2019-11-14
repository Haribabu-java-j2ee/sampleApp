package gov.utcourts.notifications.util;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.common.NoticeType;

import java.io.IOException;

import org.apache.log4j.Logger;


public class CorisNotificationEmailCommon {
	
	private static Logger logger = Logger.getLogger(CorisNotificationEmailCommon.class.getName());
	
	/**
	 * inserts new record
	 * @param String caseMgtId (case_numb, locn_code, court_type)
	 * @param byte bytes (data for attachment)
	 * @param String subject line
	 * @param String content (use <br> for new lines)
	 * @param String email_adress
	 * @param boolean isStatic
	 * @parm String courtType
	 * @parm String fileName + ".pdf"
	 * @parm boolean isStatic (true for batch processing false for web processing)
	 * @return NoticeRequestBO 
	 * <p>
	 * @throws Exception
	 */
	public static void corisNotificationEmail(int caseMgtId, int systemId, byte[] attachment, String subject, String content, String sendTo, String sendFrom, String sendCC, String sendBcc, String courtType, String fileName, boolean isStatic) throws IOException{
		try {
			// ///////////////////////////////////////////////////////////////
			// Send Email
			// ///////////////////////////////////////////////////////////////
			NoticeRequestBO noticeRequestBO = new NoticeRequestBO();
	
			// ///////////////////////////////////////////////////////////////
			// Build Notice Request
			// ///////////////////////////////////////////////////////////////
			noticeRequestBO.setCaseMgmtId(Integer.toString(caseMgtId));
			noticeRequestBO.setCaseMgmtTable("kase");
			
			if (attachment != null){
				noticeRequestBO.setAttachment(attachment);
				noticeRequestBO.setFilename(fileName);
			}
			noticeRequestBO.setNoticeType(NoticeType.EMAIL);
			noticeRequestBO.setSystemId(systemId);
			noticeRequestBO.setSendTo(sendTo);
			noticeRequestBO.setSendFrom(sendFrom);
			noticeRequestBO.setSendBcc(sendBcc);
			noticeRequestBO.setSendCc(sendCC);
			noticeRequestBO.setCourt(courtType);
			noticeRequestBO.setSubject(subject);
			
			if (attachment == null){
				noticeRequestBO.setContent(content);
			} else {
				noticeRequestBO.setContent(content + "<BR>" + " See Attachment.");
			}
			
			// ///////////////////////////////////////////////////////////////
			// Add the bulk email's to the table for processing latter
			// ///////////////////////////////////////////////////////////////
			NoticeRequestBO.insert(noticeRequestBO, isStatic);

			// ///////////////////////////////////////////////////////////////
			// Clean Up
			// ///////////////////////////////////////////////////////////////
			noticeRequestBO = null;
			attachment = null;
		} catch (Exception e) {
			logger.error("corisNotificationEmail(int caseMgtId,  byte[] bytes, String subject, String content, String emailAddress, String courtType, String fileName, boolean isStatic) ", e);
		}
	}
}



