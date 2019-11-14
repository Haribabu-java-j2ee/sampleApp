package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.NoticeStatusBO;
import gov.utcourts.notifications.common.NoticeType;
import gov.utcourts.notifications.util.SQLPropertiesUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeStatusDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(NoticeStatusDAO.class);
	
	/**
	 * find
	 * @return List<NoticeStatusBO>
	 * @throws Exception
	 */
	public List<NoticeStatusBO> findByCaseMgmtId(String caseMgmtId, String caseMgmtTable) throws Exception {
		List<NoticeStatusBO> requests = new ArrayList<NoticeStatusBO>();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.casemgmtid"));
			setPStmtValue(1, caseMgmtId);
			setPStmtValue(2, caseMgmtTable);
			executeQuery();
			
			while (hasMoreResults()) {
				requests.add(createNoticeStatusBO());
			}
		} catch (Exception e) {
			logger.error(".findByCaseMgmtId() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();

			close();
		}
		return requests;
	}
	
	public NoticeStatusBO findByRequestId(int requestId) throws Exception {
		NoticeStatusBO statusBO = new NoticeStatusBO();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.requestid"));
			setPStmtValue(1, requestId);
			executeQuery();
			
			if (hasMoreResults()) 
				statusBO = createNoticeStatusBO();
			
		} catch (Exception e) {
			logger.error(".findByRequestId() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();

			close();
		}
		return statusBO;
	}
	
	/**
	 * inserts new record 
	 * @param boNoticeStatusBO 
	 * @returnNoticeStatusBO
	 * <p>
	 * @throws Exception
	 */
	public NoticeStatusBO insert(NoticeStatusBO bo) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert"));
			setPStmtValue(1,  bo.getRequestId());
			setPStmtValue(2,  bo.getSystemId());
			setPStmtValue(3,  bo.getNoticeType().type);
			setPStmtValue(4,  bo.getCaseMgmtId());
			setPStmtValue(5,  bo.getCaseMgmtTable());
			setPStmtValue(6,  bo.getSendTo());
			setPStmtValue(7,  bo.getSendFrom());
			setPStmtValue(8,  bo.getSendCc());
			setPStmtValue(9,  bo.getSendBcc());
			setPStmtValue(10, bo.getSubject());
			setPStmtValue(11, bo.isProcessed() ? "Y" : "N");
			setPStmtValue(12, new Date());
			
			execute();
			
			bo.setStatusId(getIdentityValueInt());
		} catch (Exception e) {
			logger.error("Exception in NoticeStatusBO insert(NoticeStatusBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bo;
	}
	
	public void insert(NoticeRequestBO bo, String processed) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert"));
			setPStmtValue(1,  bo.getRequestId());
			setPStmtValue(2,  bo.getSystemId());
			setPStmtValue(3,  bo.getNoticeType().type);
			setPStmtValue(4,  bo.getCaseMgmtId());
			setPStmtValue(5,  bo.getCaseMgmtTable());
			setPStmtValue(6,  bo.getSendTo());
			setPStmtValue(7,  bo.getSendFrom());
			setPStmtValue(8,  bo.getSendCc());
			setPStmtValue(9,  bo.getSendBcc());
			setPStmtValue(10, bo.getSubject());
			setPStmtValue(11, processed);
			setPStmtValue(12, bo.getAttachment() != null ? "Y" : "N");
			setPStmtValue(13, new Date());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in void insert(NoticeRequestBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * update existing record
	 * @param boNoticeStatusBO 
	 * <p>
	 * @throws Exception
	 */
	public void update(NoticeStatusBO bo) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update"));
			setPStmtValue(1,  bo.getSystemId());
			setPStmtValue(2,  bo.getNoticeType().type);
			setPStmtValue(3,  bo.getCaseMgmtId());
			setPStmtValue(4,  bo.getCaseMgmtTable());
			setPStmtValue(5,  bo.getSendTo());
			setPStmtValue(6,  bo.getSendFrom());
			setPStmtValue(7,  bo.getSendCc());
			setPStmtValue(8,  bo.getSendBcc());
			setPStmtValue(9,  bo.getSubject());
			setPStmtValue(10, bo.getDateSent());
			setPStmtValue(11, bo.isProcessed() ? "Y" : "N");
			setPStmtValue(12, bo.getStatusId());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeStatusBO update(NoticeStatusBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateProcessed(NoticeStatusBO bo, String processed) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.processed"));
			setPStmtValue(1, processed);
			setPStmtValue(2, bo.getStatusId());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeStatusBO update(NoticeStatusBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * delete existing record
	 * @param boNoticeStatusBO 
	 * <p>
	 * @throws Exception
	 */
	public void delete(NoticeStatusBO bo) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.delete"));
			setPStmtValue(1, bo.getStatusId());

			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeStatusBO delete(NoticeStatusBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @return NoticeStatusBO
	 * @throws Exception
	 */
	private NoticeStatusBO createNoticeStatusBO() throws Exception {
		NoticeStatusBO bo = new NoticeStatusBO();
		bo.setStatusId(getRSValueInt("statusid"));
		bo.setSystemId(getRSValueInt("systemid"));
		bo.setCaseMgmtId(getRSValueString("casemgmt_id"));
		bo.setCaseMgmtTable(getRSValueString("casemgmt_table"));
		bo.setNoticeType(NoticeType.resolve(getRSValueString("noticetype")));
		bo.setSendTo(getRSValueString("sendto"));
		bo.setSendFrom(getRSValueString("sendfrom"));
		bo.setSendCc(getRSValueString("sendcc"));
		bo.setSendBcc(getRSValueString("sendbcc"));
		bo.setSubject(getRSValueString("subject"));
		bo.setProcessed("Y".equalsIgnoreCase(getRSValueString("processed")));
		bo.setAttachment("Y".equalsIgnoreCase(getRSValueString("attachment")));
		bo.setDateSent(getRSValueDate("datesent"));
		return bo;
	}
}
