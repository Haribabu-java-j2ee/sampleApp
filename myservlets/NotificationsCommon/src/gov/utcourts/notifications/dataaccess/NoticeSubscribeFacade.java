package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeSubscribeBO;
import gov.utcourts.notifications.NoticeSubscribeBO.SubscribeType;
import gov.utcourts.notifications.util.TooManyResultsException;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeSubscribeFacade {

	private static Logger logger = Logger.getLogger(NoticeSubscribeFacade.class);
	
	/**
	 * Method to return NoticeSubscribeBO record
	 * @param String caseMgmtId
	 * @param StringemailAddress
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public static NoticeSubscribeBO find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			return getNoticeSubscribeDAO(conn).find(noticeSubscribeBO, subscribeType);
		} catch (Exception e) {
			logger.info(".find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	public static NoticeSubscribeBO retrieveForVerification(NoticeSubscribeBO noticeSubscribeBO, String uniqueId) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			return getNoticeSubscribeDAO(conn).retrieveForVerification(noticeSubscribeBO, uniqueId);
		} catch (Exception e) {
			logger.info(".retrieveForVerification(NoticeSubscribeBO noticeSubscribeBO, String uniqueId) [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	public static NoticeSubscribeBO findBySystem(String caseMgmtId, String table, String systemId, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			return getNoticeSubscribeDAO(conn).findBySystem(caseMgmtId, table, systemId);
		} catch (Exception e) {
			logger.info(".findBySystem(String caseMgmtId, String systemId) [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	/**
	 * returns the search results for the specified search criteria.
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @return List<NoticeSubscribeBO>
	 * @throws Exception
	 */
	public static List<NoticeSubscribeBO> search(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			return getNoticeSubscribeDAO(conn).search(noticeSubscribeBO);
		} catch (TooManyResultsException tme) {
			throw tme;
		} catch (Exception e) {
			logger.error("Exception in List<NoticeSubscribeBO> search(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * Inserts new record
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeSubscribeBO
	 * @param isStatic boolean
	 * @return NoticeSubscribeVO  
	 * 
	 * @throws Exception 
	 */
	public static NoticeSubscribeBO insert(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			noticeSubscribeBO = getNoticeSubscribeDAO(conn).insert(noticeSubscribeBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in insert(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return noticeSubscribeBO;
	}
	
	/**
	 * Updates existing record.
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeSubscribeBO
	 * @param isStatic boolean
	 * 
	 * @throws Exception 
	 */
	public static void update(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).update(noticeSubscribeBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in update(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void updateSms(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).updateSms(noticeSubscribeBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateSms(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static NoticeSubscribeBO changeEmailAddress(NoticeSubscribeBO noticeSubscribeBO, String newEmailAddress) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			noticeSubscribeBO = getNoticeSubscribeDAO(conn).changeEmailAddress(noticeSubscribeBO, newEmailAddress);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in changeEmailAddress(NoticeSubscribeBO noticeSubscribeBO, String newEmailAddress)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return noticeSubscribeBO;
	}
	
	public static NoticeSubscribeBO changeMobileNumber(NoticeSubscribeBO noticeSubscribeBO, String newMobileNumber) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			noticeSubscribeBO = getNoticeSubscribeDAO(conn).changeMobileNumber(noticeSubscribeBO, newMobileNumber);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in changeMobileNumber(NoticeSubscribeBO noticeSubscribeBO, String newMobileNumber)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return noticeSubscribeBO;
	}
	
	public static void updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).updateEmailVerifiedDate(noticeSubscribeBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void updateMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).updateMobileVerifiedDate(noticeSubscribeBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	/**
	 * Delete existing record.
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeSubscribeBO 
	 * @param isStatic boolean
	 * 
	 * @throws Exception 
	 */
	public static void delete(NoticeSubscribeBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic) {
				conn = JDBCConnect.getJDBCConnectCommonDB();
			} else {
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			}
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).delete(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in delete(NoticeSubscribeBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void removeEmailVerifiedDate(NoticeSubscribeBO bo) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).removeEmailVerifiedDate(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in removeEmailVerifiedDate(NoticeSubscribeBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void removeMobileVerifiedDate(NoticeSubscribeBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeSubscribeDAO(conn).removeMobileVerifiedDate(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in removeMobileVerifiedDate(NoticeSubscribeBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	/**
	 * @param conn
	 * @return {@link NoticeSubscribeDAO}
	 */
	private static NoticeSubscribeDAO getNoticeSubscribeDAO(Connection conn) {
		NoticeSubscribeDAO noticeSubscribeDAO = DAOFactory.getFactory().getNoticeSubscribeDAO();
		noticeSubscribeDAO.setCon(conn);
		return noticeSubscribeDAO;
	}
}
