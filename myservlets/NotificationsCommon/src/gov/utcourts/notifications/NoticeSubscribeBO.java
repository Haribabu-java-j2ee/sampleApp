package gov.utcourts.notifications;

import gov.utcourts.notifications.dataaccess.NoticeSubscribeFacade;
import gov.utcourts.notifications.util.TextUtil;

import java.util.List;

import org.apache.log4j.Logger;

public class NoticeSubscribeBO extends NoticeSubscribeVO {
	
	public enum SubscribeType { VERIFY, EMAIL, MOBILE, ID  };
	
	private static Logger log = Logger.getLogger(NoticeSubscribeBO.class);
	
	public NoticeSubscribeBO(){
		super();
	}	
	
	/**
	 * find - Method to return NoticeSubscribeBO record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param SubscribeType subscribeType
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) throws Exception {
		return NoticeSubscribeFacade.find(noticeSubscribeBO, subscribeType);		
	}
	
	/**
	 * findBySystem - Method to return NoticeSubscribeBO record
	 * @param String caseMgmtId
	 * @param String table
	 * @param String systemId
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO findBySystem(String caseMgmtId, String table, String systemId) throws Exception {
		return NoticeSubscribeFacade.findBySystem(caseMgmtId, table, systemId, false);
	}
	
	/**
	 * Find NoticeSubscribeBO record
	 * 
	 * @param caseMgmtId String
	 * @param table String
	 * @param systemId String
	 * @param isStatic boolean
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO findBySystem(String caseMgmtId, String table, String systemId, boolean isStatic) throws Exception {
		return NoticeSubscribeFacade.findBySystem(caseMgmtId, table, systemId, isStatic);
	}
	
	/**
	 * getFromDB
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param SubscribeType subscribeType
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO getFromDB(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) throws Exception {
		noticeSubscribeBO = NoticeSubscribeBO.find(noticeSubscribeBO, subscribeType);
		noticeSubscribeBO = NoticeSubscribeBO.insert(noticeSubscribeBO);
		return noticeSubscribeBO;
	}
	
	/**
	 * retrieveForVerification
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param String uniqueId
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO retrieveForVerification(NoticeSubscribeBO noticeSubscribeBO, String uniqueId) throws Exception {
		return NoticeSubscribeFacade.retrieveForVerification(noticeSubscribeBO, uniqueId);
	}
	
	/**
	 * search - returns the search results for the specified search criteria.
	 * @param NoticeSubscribeBO bo
	 * @return List<NoticeSubscribeBO>
	 * @throws Exception
	 */
	public static List<NoticeSubscribeBO> search(NoticeSubscribeBO bo) throws Exception {
		return NoticeSubscribeFacade.search(bo);
	}
	
	/**
	 * inserts new record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @return NoticeSubscribeBO 
	 * <p>
	 * @throws Exception
	 */
	public static NoticeSubscribeBO insert(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		if (noticeSubscribeBO.getSubscribeId() == 0 && !TextUtil.isEmpty(noticeSubscribeBO.getCaseMgmtId())) {
			if (!TextUtil.isEmpty(noticeSubscribeBO.getEmail()) || !TextUtil.isEmpty(noticeSubscribeBO.getMobile())) 
				noticeSubscribeBO = NoticeSubscribeFacade.insert(noticeSubscribeBO, false);
		}
		return noticeSubscribeBO;
	}
	
	/**
	 * Insert new record
	 * 
	 * @param noticeSubscribeBO NoticeSubscribeBO
	 * @param isStatic boolean
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO insert(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		if (noticeSubscribeBO.getSubscribeId() == 0 && !TextUtil.isEmpty(noticeSubscribeBO.getCaseMgmtId())) {
			if (!TextUtil.isEmpty(noticeSubscribeBO.getEmail()) || !TextUtil.isEmpty(noticeSubscribeBO.getMobile())) 
				noticeSubscribeBO = NoticeSubscribeFacade.insert(noticeSubscribeBO, isStatic);
		}
		return noticeSubscribeBO;
	}

	/**
	 * update record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void update(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.update(noticeSubscribeBO, false);
	}
	
	/**
	 * Update a record
	 * 
	 * @param noticeSubscribeBO NoticeSubscribeBO
	 * @param isStatic boolean
	 * @throws Exception
	 */
	public static void update(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		NoticeSubscribeFacade.update(noticeSubscribeBO, isStatic);
	}
	
	/**
	 * updateSms
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void updateSms(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.updateSms(noticeSubscribeBO);
	}
	
	/**
	 * changeEmailAddress
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param String newEmailAddress
	 * <p>
	 * @throws Exception
	 */
	public static NoticeSubscribeBO changeEmailAddress(NoticeSubscribeBO noticeSubscribeBO, String newEmailAddress) throws Exception {
		return NoticeSubscribeFacade.changeEmailAddress(noticeSubscribeBO, newEmailAddress);
	}
	
	/**
	 * changeMobileNumber
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param String newMobileNumber
	 * <p>
	 * @throws Exception
	 */
	public static NoticeSubscribeBO changeMobileNumber(NoticeSubscribeBO noticeSubscribeBO, String newMobileNumber) throws Exception {
		return NoticeSubscribeFacade.changeMobileNumber(noticeSubscribeBO, newMobileNumber);
	}
	
	/**
	 * updateEmailVerifiedDate
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.updateEmailVerifiedDate(noticeSubscribeBO, false);
	}
	
	/**
	 * @param noticeSubscribeBO NoticeSubscribeBO
	 * @param isStatic boolean
	 * @throws Exception
	 */
	public static void updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		NoticeSubscribeFacade.updateEmailVerifiedDate(noticeSubscribeBO, isStatic);
	}
	
	/**
	 * updateMobileVerifiedDate
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void updateMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.updateMobileVerifiedDate(noticeSubscribeBO);
	}
	
	/**
	 * emailMatches
	 * @param String emailAddress
	 * <p>
	 * @throws Exception
	 */
	public boolean emailMatches(String emailAddress) throws Exception {
		return getEmail().equalsIgnoreCase(emailAddress);
	}
	
	/**
	 * mobileMatches
	 * @param String mobileNumber
	 * <p>
	 * @throws Exception
	 */
	public boolean mobileMatches(String mobileNumber) throws Exception {
		return getMobile().equalsIgnoreCase(mobileNumber);
	}
	
	/**
	 * delete record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void delete(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.delete(noticeSubscribeBO, false);
	}
	
	/**
	 * delete record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void delete(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		NoticeSubscribeFacade.delete(noticeSubscribeBO, isStatic);
	}
	
	/**
	 * removeEmailVerifiedDate
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void removeEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.removeEmailVerifiedDate(noticeSubscribeBO);
	}
	
	/**
	 * removeMobileVerifiedDate
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public static void removeMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		NoticeSubscribeFacade.removeMobileVerifiedDate(noticeSubscribeBO, false);
	}
	
	/**
	 * removeMobileVerifiedDate
	 * 
	 * @param noticeSubscribeBO NoticeSubscribeBO
	 * @param isStatic boolean
	 * @throws Exception
	 */
	public static void removeMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		NoticeSubscribeFacade.removeMobileVerifiedDate(noticeSubscribeBO, isStatic);
	}
	
	public boolean isEmailVerified() {
		return getEmailVerified() != null;
	}
	
	public boolean isEmailAddressMissing() {
		return TextUtil.isEmpty(getEmail());
	}
	
	public boolean isMobileVerified() {
		return getMobileVerified() != null;
	}
	
	public boolean isMobileNumberMissing() {
		return TextUtil.isEmpty(getMobile());
	}
}
