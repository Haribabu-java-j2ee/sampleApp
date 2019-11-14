package gov.utcourts.notifications;

import gov.utcourts.notifications.dataaccess.NoticeStatusFacade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeStatusBO extends NoticeStatusVO {
	
	private static Logger log = Logger.getLogger(NoticeStatusBO.class);
	
	public NoticeStatusBO(){
		super();
	}	
	
	/**
	 * findByCaseMgmtId
	 * @param String caseMgmtId
	 * @param String caseMgmtTable
	 * @return List<NoticeStatusBO>
	 */
	public static List<NoticeStatusBO> findByCaseMgmtId(String caseMgmtId, String caseMgmtTable) {
		try { 
			return NoticeStatusFacade.findByCaseMgmtId(caseMgmtId, caseMgmtTable);
		} catch (Exception e) {
			log.error("NoticeStatusBO.findByCaseMgmtId error - " + e.getMessage());
		}
		return new ArrayList<NoticeStatusBO>();
	}
	
	/**
	 * findByRequestId
	 * @param NoticeRequestBO noticeRequestBO
	 * @return List<NoticeStatusBO>
	 */
	public static NoticeStatusBO findByRequestId(NoticeRequestBO noticeRequestBO) {
		try { 
			return NoticeStatusFacade.findByRequestId(noticeRequestBO);
		} catch (Exception e) {
			log.error("NoticeStatusBO.findByRequestId error - " + e.getMessage());
		}
		return new NoticeStatusBO();
	}
	
	/**
	 * insert
	 * @param NoticeStatusBO noticeStatusBO
	 * @return NoticeStatusBO 
	 */
	public static NoticeStatusBO insert(NoticeStatusBO noticeStatusBO) {
		try { 
			return NoticeStatusFacade.insert(noticeStatusBO);
		} catch (Exception e) {
			log.error("NoticeStatusBO.insert error - " + e.getMessage());
		}
		return noticeStatusBO;
	}
	
	/**
	 * insert
	 * @param NoticeRequestBO noticeRequestBO
	 * @param String processed
	 * @return void 
	 */
	public static void insert(NoticeRequestBO noticeRequestBO, String processed) {
		try { 
			NoticeStatusFacade.insert(noticeRequestBO, processed);
		} catch (Exception e) {
			log.error("NoticeStatusBO.insert error - " + e.getMessage());
		}
	}
	
	/**
	 * update 
	 * @param NoticeStatusBO noticeStatusBO
	 * @return void
	 */
	public static void update(NoticeStatusBO noticeStatusBO) {
		try { 
			NoticeStatusFacade.update(noticeStatusBO);
		} catch (Exception e) {
			log.error("NoticeStatusBO.update error - " + e.getMessage());
		}
	}

	/**
	 * updateProccessedFlag 
	 * @param NoticeRequestBO noticeRequestBO
	 * @param String processed
	 * @return void
	 */
	public static void updateProccessedFlag(NoticeRequestBO noticeRequestBO, String processed) {
		try { 
			NoticeStatusBO noticeStatusBO = NoticeStatusBO.findByRequestId(noticeRequestBO);
			if (noticeStatusBO.getStatusId() > 0) {
				NoticeStatusFacade.updateProcessed(noticeStatusBO, processed);
			} else {
				NoticeStatusFacade.insert(noticeRequestBO, processed);
			}
			noticeStatusBO = null;
		} catch (Exception e) {
			log.error("NoticeStatusBO.updateProccessedFlag error - " + e.getMessage());
		}
	}
	
	/**
	 * delete record
	 * @param NoticeStatusBO noticeStatusBO
	 * @return void
	 */
	public static void delete(NoticeStatusBO noticeStatusBO) {
		try { 
			NoticeStatusFacade.delete(noticeStatusBO);
		} catch (Exception e) {
			log.error("NoticeStatusBO.delete error - " + e.getMessage());
		}
	}
}
