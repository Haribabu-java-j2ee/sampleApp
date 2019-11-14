package gov.utcourts.notifications;

import gov.utcourts.notifications.common.NoticeType;
import gov.utcourts.notifications.dataaccess.NoticeRequestFacade;
import gov.utcourts.notifications.util.TextUtil;
import gov.utcourts.notifications.util.ValidationUtil;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeRequestBO extends NoticeRequestVO {
	
	private static Logger log = Logger.getLogger(NoticeRequestBO.class);
	
	private String validationMessage = null;
	
	public NoticeRequestBO(){
		super();
	}	
	
	/**
	 * findAllStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> findAllStatic() throws Exception {
		return NoticeRequestFacade.findAllStatic();		
	}
	
	/**
	 * findAllForBatchStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> findAllForBatchStatic() throws Exception {
		return NoticeRequestFacade.findAllForBatchStatic();		
	}
	
	/**
	 * findByRequestId
	 * @param int requestId
	 * @param boolean isStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static NoticeRequestBO findByRequestId(int requestId, boolean isStatic) throws Exception {
		return NoticeRequestFacade.findByRequestId(requestId, isStatic);		
	}
		
	/**
	 * inserts new record
	 * @param NoticeRequestBO noticeRequestBO
	 * @param boolean isStatic
	 * @return NoticeRequestBO 
	 * <p>
	 * @throws Exception
	 */
	public static NoticeRequestBO insert(NoticeRequestBO noticeRequestBO, boolean isStatic) throws Exception {
		noticeRequestBO = ValidationUtil.validateNoticeRequest(noticeRequestBO);
		if (!TextUtil.isEmpty(noticeRequestBO.getValidationMessage())) {
			throw new Exception("Error occurred inserting Notice Request - " + noticeRequestBO.getValidationMessage());
		}
		return NoticeRequestFacade.insert(noticeRequestBO, isStatic);
	}
	
	/**
	 * bulkInsert - inserts new records in bulk
	 * @param List<NoticeRequestBO>
	 * @param boolean isStatic
	 * @return List<NoticeRequestBO> 
	 * <p>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> bulkInsert(List<NoticeRequestBO> noticeRequests, boolean isStatic) throws Exception {
		String errorMessage = ValidationUtil.validateNoticeRequests(noticeRequests);
		if (!TextUtil.isEmpty(errorMessage)) {
			throw new Exception("Error occurred inserting Notice Request - " + errorMessage);
		}
		return NoticeRequestFacade.bulkInsert(noticeRequests, isStatic);
	}

	/**
	 * update record
	 * @param NoticeRequestBO noticeRequestBO
	 * <p>
	 * @throws Exception
	 */
	public static void update(NoticeRequestBO noticeRequestBO) throws Exception {
		NoticeRequestFacade.update(noticeRequestBO);
	}
	
	/**
	 * updateAttempts
	 * @param NoticeRequestBO noticeRequestBO
	 * @param isStatic
	 * <p>
	 * @throws Exception
	 */
	public static void updateAttempts(NoticeRequestBO bo, boolean isStatic) throws Exception {
		NoticeRequestFacade.updateAttempts(bo, isStatic);
	}

	/**
	 * updateAttachment
	 * @param NoticeRequestBO noticeRequestBO
	 * @param isStatic
	 * <p>
	 * @throws Exception
	 */
	public static void updateAttachment(NoticeRequestBO bo, boolean isStatic) throws Exception {
		if (TextUtil.isEmpty(bo.getFilename()) && bo.getAttachment() != null)
			throw new Exception("Filename cannot be blank if there is an attachment");
			
		NoticeRequestFacade.updateAttachment(bo, isStatic);
	}
	
	/**
	 * delete record
	 * @param NoticeRequestBO noticeRequestBO
	 * @param boolean isStatic
	 * <p>
	 * @throws Exception
	 */
	public static void delete(NoticeRequestBO noticeRequestBO, boolean isStatic) throws Exception {
		NoticeRequestFacade.delete(noticeRequestBO, isStatic);
	}
	
	/**
	 * delete record by casemgmt id
	 * @param bo NoticeRequestBO
	 * @param boolean isStatic
	 * <p>
	 * @throws Exception
	 */
	public static void deleteByCaseMgmtId(int caseMgmtId, boolean isStatic) throws Exception {
		NoticeRequestFacade.deleteByCaseMgmtId(caseMgmtId, isStatic);
	}
	
	/**
	 * findByCaseMgmtId
	 * @param int caseMmgtId
	 * @param string subject
	 * @param boolean isStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> findByCaseMgmtId(int caseMgmtId, String subject, boolean isStatic) throws Exception {
		return NoticeRequestFacade.findByCaseMgmtId(caseMgmtId, subject,isStatic);		
	}
		
	public String getValidationMessage() {
		return validationMessage;
	}
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	public static void main(String[] args) {
		try {
			Date scheduledTime = null;
			String scheduled = "12/24/2016 21:54";
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		    try {
		    	scheduledTime = simpleDateFormat.parse(scheduled);
		        System.out.println("date : " + simpleDateFormat.format(scheduledTime));
		    } catch (ParseException ex) {
		        System.out.println("Exception " + ex);
		    }
			
			File file = new File("c:\\delete\\attachment.pdf");
		    byte[] attachment = new byte[(int) file.length()];
		    DataInputStream dis = new DataInputStream(new FileInputStream(file));
		    dis.readFully(attachment);
		    dis.close();
			
			List<NoticeRequestBO> noticeRequests = new ArrayList<NoticeRequestBO>();
			for (int i=0; i < 1; i++) {
				NoticeRequestBO noticeRequestBO = new NoticeRequestBO();
				noticeRequestBO.setSystemId(25);
				noticeRequestBO.setNoticeType(NoticeType.EMAIL);
				noticeRequestBO.setCaseMgmtId(Integer.toString(i+490));
				noticeRequestBO.setCaseMgmtTable("");
				noticeRequestBO.setSendTo("troyc@utcourts.gov");
				noticeRequestBO.setSendFrom("courts@utcourts.gov");
				noticeRequestBO.setSubject("test bulk email");
				noticeRequestBO.setContent("test file");
				noticeRequestBO.setFilename("attachment.pdf");
				noticeRequestBO.setScheduledTime(null);
				noticeRequestBO.setAttachment(attachment);
				
				noticeRequests.add(noticeRequestBO);
				
				noticeRequestBO = null;
			}
		
			NoticeRequestBO.bulkInsert(noticeRequests, true);
			
			noticeRequests = null;
			attachment = null;
		} catch (Exception e) {
			System.out.println("Error occurred - " + e.getMessage());
		}
	}
}
