package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.NoticeStatusBO;
import gov.utcourts.notifications.constants.Constants;
import gov.utcourts.notifications.util.TextUtil;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeRequestFacade {

	private static Logger logger = Logger.getLogger(NoticeRequestFacade.class);
	
	/**
	 * find
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> findAllStatic() throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJDBCConnectCommonDB();
			return getNoticeRequestDAO(conn).findAllStatic();
		} catch (Exception e) {
			logger.info(".findAllStatic() [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	public static List<NoticeRequestBO> findAllForBatchStatic() throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJDBCConnectCommonDB();
			return getNoticeRequestDAO(conn).findAllForBatchStatic();
		} catch (Exception e) {
			logger.info(".findAllForBatchStatic() [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	public static NoticeRequestBO findByRequestId(int requestId, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			return getNoticeRequestDAO(conn).findByRequestId(requestId);
		} catch (Exception e) {
			logger.info(".findByRequestId() [Exception]", e);
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
	 * Inserts new record
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeRequestBO
	 * @return NoticeRequestBO  
	 * 
	 * @throws Exception 
	 */
	public static NoticeRequestBO insert(NoticeRequestBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			bo = getNoticeRequestDAO(conn).insert(bo);
			conn.commit();
			
			// insert status record
			try {
				String excludedSystems = Constants.SYSTEMS_WITH_NO_NOTICE_STATUS_UPDATES;
				if (TextUtil.isEmpty(excludedSystems) || !(Arrays.asList(excludedSystems.split(",")).contains(String.valueOf(bo.getSystemId())))) {
					NoticeStatusBO.insert(bo, "N");
				}
			} catch (Exception e) {
				logger.error("Exception inserting notice_status record in insert(NoticeRequestBO bo, boolean isStatic)", e);
			}
			
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in insert(NoticeRequestBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return bo;
	}
		
	public static List<NoticeRequestBO> bulkInsert(List<NoticeRequestBO> noticeRequests, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			noticeRequests = getNoticeRequestDAO(conn).bulkInsert(noticeRequests, conn);
			conn.commit();
			
			// update request ids
			String excludedSystems = Constants.SYSTEMS_WITH_NO_NOTICE_STATUS_UPDATES;
			NoticeRequestBO bo = (noticeRequests!=null && noticeRequests.size()>0)? noticeRequests.get(0):null;
			if (TextUtil.isEmpty(excludedSystems) || !(Arrays.asList(excludedSystems.split(",")).contains(String.valueOf(bo.getSystemId())))) {
				int lastRequestId = getNoticeRequestDAO(conn).getLastRequestId();
				if (lastRequestId > 0) {
					for (int i=noticeRequests.size()-1; i >= 0; i--) {
						noticeRequests.get(i).setRequestId(lastRequestId--);
						
						// insert status record
						try {
							NoticeStatusBO.insert(noticeRequests.get(i), "N");
						} catch (Exception e) {
							logger.error("Exception inserting notice_status record in bulkInsert(List<NoticeRequestBO> noticeRequests)", e);
						}
					}
				}
			}
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in bulkInsert(List<NoticeRequestBO> noticeRequests)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return noticeRequests;
	}
	
	/**
	 * Updates existing record.
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeRequestBO 
	 * 
	 * @throws Exception 
	 */
	public static void update(NoticeRequestBO bo) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJNDIConnectCommonDB();
			conn.setAutoCommit(false);
			getNoticeRequestDAO(conn).update(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in update(NoticeRequestBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void updateAttempts(NoticeRequestBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			getNoticeRequestDAO(conn).updateAttempts(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateAttempts(NoticeRequestBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void updateAttachment(NoticeRequestBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			getNoticeRequestDAO(conn).updateAttachment(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateAttachment(NoticeRequestBO bo)", e);
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
	 * @param bo NoticeRequestBO
	 * @param boolean isStatic
	 * 
	 * @throws Exception 
	 */
	public static void delete(NoticeRequestBO bo, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			getNoticeRequestDAO(conn).delete(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in delete(NoticeRequestBO bo)", e);
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
	 * Delete existing record by case mgmt id
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeRequestBO
	 * @param boolean isStatic
	 * 
	 * @throws Exception 
	 */
	public static void deleteByCaseMgmtId(int caseMgmtId, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			conn.setAutoCommit(false);
			getNoticeRequestDAO(conn).deleteByCaseMgmtId(caseMgmtId);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in deleteByCaseMgmtId(caseMgmtId)", e);
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
	 * findByCaseMgmtId
	 * @param int caseMmgtId
	 * @param string subject
	 * @param boolean isStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public static List<NoticeRequestBO> findByCaseMgmtId(int caseMgmtId, String subject, boolean isStatic) throws Exception {
		Connection conn = null;
		try {
			if (isStatic)
				conn = JDBCConnect.getJDBCConnectCommonDB();
			else
				conn = DatabaseConnection.getJNDIConnectCommonDB();
			
			return getNoticeRequestDAO(conn).findByCaseMgmtId(caseMgmtId,subject);
		} catch (Exception e) {
			logger.info(".findByCaseMgmtId() [Exception]", e);
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
	 * @param conn
	 * @return {@link NoticeRequestDAO}
	 */
	private static NoticeRequestDAO getNoticeRequestDAO(Connection conn) {
		NoticeRequestDAO NoticeRequestDAO = DAOFactory.getFactory().getNoticeRequestDAO();
		NoticeRequestDAO.setCon(conn);
		return NoticeRequestDAO;
	}
}
