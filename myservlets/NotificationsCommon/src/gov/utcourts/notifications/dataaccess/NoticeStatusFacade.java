package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.NoticeStatusBO;
import gov.utcourts.notifications.util.TextUtil;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeStatusFacade {

	private static Logger logger = Logger.getLogger(NoticeStatusFacade.class);
	
	/**
	 * find
	 * @param String caseMgmtId
	 * @param String caseMgmtTable
	 * @return List<NoticeStatusBO>
	 * @throws Exception
	 */
	public static List<NoticeStatusBO> findByCaseMgmtId(String caseMgmtId, String caseMgmtTable) throws Exception {
		Connection conn = null;
		try {
			conn = DatabaseConnection.getJDBCConnectCommonDB();
			return getNoticeStatusDAO(conn).findByCaseMgmtId(caseMgmtId, caseMgmtTable);
		} catch (Exception e) {
			logger.info(".findByCaseMgmtId(String caseMgmtId, String caseMgmtTable) [Exception]", e);
			throw e;
		} finally {
			try {
				conn.close();
			} catch (Exception e) { 
				// do nothing
			}
		}
	}
	
	public static NoticeStatusBO findByRequestId(NoticeRequestBO noticeRequestBO) throws Exception {
		Connection conn = null;
		try {
			if (TextUtil.isEmpty(noticeRequestBO.getCourt())){
				conn = JDBCConnect.getJDBCConnection(noticeRequestBO.getSystemId());
			} else {
				conn = JDBCConnect.getJDBCConnectionByCourt(noticeRequestBO.getSystemId(), noticeRequestBO.getCourt());
			}
			return getNoticeStatusDAO(conn).findByRequestId(noticeRequestBO.getRequestId());
		} catch (Exception e) {
			logger.info(".findByRequestId(int requestId) [Exception]", e);
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
	 * @param bo NoticeStatusBO
	 * @return NoticeStatusBO  
	 * 
	 * @throws Exception 
	 */
	public static NoticeStatusBO insert(NoticeStatusBO noticeStatusBO) throws Exception {
		Connection conn = null;
		try {
			
			if (TextUtil.isEmpty(noticeStatusBO.getCourt())){
				conn = JDBCConnect.getJDBCConnection(noticeStatusBO.getSystemId());
			} else{
				conn = JDBCConnect.getJDBCConnectionByCourt(noticeStatusBO.getSystemId(), noticeStatusBO.getCourt());
			}
			conn.setAutoCommit(false);
			noticeStatusBO = getNoticeStatusDAO(conn).insert(noticeStatusBO);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in insert(NoticeStatusBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
		return noticeStatusBO;
	}
	
	public static void insert(NoticeRequestBO noticeRequestBO, String processed) throws Exception {
		Connection conn = null;
		try {
			if (TextUtil.isEmpty(noticeRequestBO.getCourt())){
				conn = JDBCConnect.getJDBCConnection(noticeRequestBO.getSystemId());
			} else {
				conn = JDBCConnect.getJDBCConnectionByCourt(noticeRequestBO.getSystemId(), noticeRequestBO.getCourt());
			}
			conn.setAutoCommit(false);
			getNoticeStatusDAO(conn).insert(noticeRequestBO, processed);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in insert(NoticeRequestBO bo, String processed)", e);
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
	 * Updates existing record.
	 *
	 * Handles exception from DAO if any
	 * <p>
	 * @param bo NoticeStatusBO 
	 * 
	 * @throws Exception 
	 */
	public static void update(NoticeStatusBO bo) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCConnect.getJDBCConnection(bo.getSystemId());
			conn.setAutoCommit(false);
			getNoticeStatusDAO(conn).update(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in update(NoticeStatusBO bo)", e);
			throw e;	
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				// nothing
			}
		}
	}
	
	public static void updateProcessed(NoticeStatusBO bo, String processed) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCConnect.getJDBCConnection(bo.getSystemId());
			conn.setAutoCommit(false);
			getNoticeStatusDAO(conn).updateProcessed(bo, processed);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in updateProcessed(NoticeStatusBO bo, String processed)", e);
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
	 * @param bo NoticeStatusBO 
	 * 
	 * @throws Exception 
	 */
	public static void delete(NoticeStatusBO bo) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCConnect.getJDBCConnection(bo.getSystemId());
			conn.setAutoCommit(false);
			getNoticeStatusDAO(conn).delete(bo);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			logger.error("Exception in delete(NoticeStatusBO bo)", e);
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
	 * @return {@link NoticeStatusDAO}
	 */
	private static NoticeStatusDAO getNoticeStatusDAO(Connection conn) {
		NoticeStatusDAO noticeStatusDAO = DAOFactory.getFactory().getNoticeStatusDAO();
		noticeStatusDAO.setCon(conn);
		return noticeStatusDAO;
	}
}
