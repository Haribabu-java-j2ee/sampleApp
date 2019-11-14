package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeRequestBO;
import gov.utcourts.notifications.common.NoticeType;
import gov.utcourts.notifications.util.SQLPropertiesUtil;
import gov.utcourts.notifications.util.TextUtil;
import gov.utcourts.notifications.util.ValidationUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.log4j.Logger;

public class NoticeRequestDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(NoticeRequestDAO.class);
	
	/**
	 * find
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public List<NoticeRequestBO> findAllStatic() throws Exception {
		List<NoticeRequestBO> requests = new ArrayList<NoticeRequestBO>();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.select.static"));
			executeQuery();
			
			while (hasMoreResults()) {
				requests.add(createNoticeRequestBO());
			}
		} catch (Exception e) {
			logger.error(".findAllStatic() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
			
			close();
		}
		return requests;
	}
	
	public List<NoticeRequestBO> findAllForBatchStatic() throws Exception {
		List<NoticeRequestBO> requests = new ArrayList<NoticeRequestBO>();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.batch.static"));
			setPStmtValue(1, new Date());
			executeQuery();
			
			while (hasMoreResults()) {
				requests.add(createNoticeRequestBO());
			}
		} catch (Exception e) {
			logger.error(".findAllForBatchStatic() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
			
			close();
		}
		return requests;
	}
	
	public NoticeRequestBO findByRequestId(int requestId) throws Exception {
		NoticeRequestBO noticeRequestBO = new NoticeRequestBO();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find"));
			setPStmtValue(1, requestId);
			executeQuery();
			
			if (hasMoreResults()) 
				noticeRequestBO = createNoticeRequestBO();
			
		} catch (Exception e) {
			logger.error(".findAllStatic() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
			
			close();
		}
		return noticeRequestBO;
	}
	
	/**
	 * inserts new record 
	 * @param bo NoticeRequestBO 
	 * @return NoticeRequestBO
	 * <p>
	 * @throws Exception
	 */
	public NoticeRequestBO insert(NoticeRequestBO bo) throws Exception {
		try {
			boolean hasAttachment = ValidationUtil.hasAttachment(bo.getAttachment());
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert"));
			setPStmtValue(1,  bo.getSystemId());
			setPStmtValue(2,  bo.getNoticeType().type);
			setPStmtValue(3,  bo.getCaseMgmtId());
			setPStmtValue(4,  TextUtil.isEmpty(bo.getCaseMgmtTable()) ? "" : bo.getCaseMgmtTable());
			setPStmtValue(5,  bo.getSendTo());
			setPStmtValue(6,  bo.getSendFrom());
			setPStmtValue(7,  bo.getSendCc());
			setPStmtValue(8,  bo.getSendBcc());
			setPStmtValue(9,  bo.getSubject());
			setPStmtValue(10, bo.getContent());
			setPStmtValue(11, hasAttachment ? bo.getFilename() : null);
			setPStmtValue(12, bo.getScheduledTime());
			setPStmtValue(13, bo.getCourt());
			setPStmtValue(14, hasAttachment ? bo.getAttachment(): null);
			
			execute();
			
			bo.setRequestId(getIdentityValueInt());
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO insert(NoticeRequestBO bo)", e);
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
		
	public List<NoticeRequestBO> bulkInsert(List<NoticeRequestBO> noticeRequests, Connection dbConnection) throws Exception {
		PreparedStatement preparedStatementWithAttachment = dbConnection.prepareStatement(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert"));
		PreparedStatement preparedStatementNoAttachment = dbConnection.prepareStatement(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert.no.attachment"));
		
		NoticeRequestBO bo = null;
		for (int i=0; i < (noticeRequests != null ? noticeRequests.size() : 0); i++) {
			bo = noticeRequests.get(i);
			
			boolean hasAttachment = ValidationUtil.hasAttachment(bo.getAttachment());
			if (hasAttachment) {
				preparedStatementWithAttachment.setInt(1,  bo.getSystemId());
				preparedStatementWithAttachment.setString(2,  bo.getNoticeType().type);
				preparedStatementWithAttachment.setString(3,  bo.getCaseMgmtId());
				preparedStatementWithAttachment.setString(4,  bo.getCaseMgmtTable());
				preparedStatementWithAttachment.setString(5,  bo.getSendTo());
				preparedStatementWithAttachment.setString(6,  bo.getSendFrom());
				preparedStatementWithAttachment.setString(7,  bo.getSendCc());
				preparedStatementWithAttachment.setString(8,  bo.getSendBcc());
				preparedStatementWithAttachment.setString(9,  bo.getSubject());
				preparedStatementWithAttachment.setString(10, bo.getContent());
				preparedStatementWithAttachment.setString(11, hasAttachment ? bo.getFilename() : null);
				preparedStatementWithAttachment.setDate(12, bo.getScheduledTime() != null ? new java.sql.Date(bo.getScheduledTime().getTime()) : null);
				preparedStatementWithAttachment.setString(13, bo.getCourt());
				preparedStatementWithAttachment.setBlob(14, hasAttachment ? new SerialBlob(bo.getAttachment()) : null);
				preparedStatementWithAttachment.addBatch();
			}else {
				preparedStatementNoAttachment.setInt(1,  bo.getSystemId());
				preparedStatementNoAttachment.setString(2,  bo.getNoticeType().type);
				preparedStatementNoAttachment.setString(3,  bo.getCaseMgmtId());
				preparedStatementNoAttachment.setString(4,  bo.getCaseMgmtTable());
				preparedStatementNoAttachment.setString(5,  bo.getSendTo());
				preparedStatementNoAttachment.setString(6,  bo.getSendFrom());
				preparedStatementNoAttachment.setString(7,  bo.getSendCc());
				preparedStatementNoAttachment.setString(8,  bo.getSendBcc());
				preparedStatementNoAttachment.setString(9,  bo.getSubject());
				preparedStatementNoAttachment.setString(10, bo.getContent());
				preparedStatementNoAttachment.setString(11, hasAttachment ? bo.getFilename() : null);
				preparedStatementNoAttachment.setDate(12, bo.getScheduledTime() != null ? new java.sql.Date(bo.getScheduledTime().getTime()) : null);
				preparedStatementNoAttachment.setString(13, bo.getCourt());
				preparedStatementNoAttachment.addBatch();
			}
			
			bo = null;
		}
		preparedStatementWithAttachment.executeBatch();
		preparedStatementNoAttachment.executeBatch();
		
		return noticeRequests;
	}
	
	public int getLastRequestId() throws Exception {
		int lastRequestId = 0;
		try {
			setPStmt("set isolation to dirty read");
			execute();
			closePartial();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.last.requestid"));
			executeQuery();
			
			if (hasMoreResults()) 
				lastRequestId = getRSValueInt("requestid");
			
		} catch (Exception e) {
			logger.error(".getLastRequestId() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
			
			close();
		}
		return lastRequestId;
	}
	
	/**
	 * update existing record
	 * @param boNoticeRequestBO 
	 * <p>
	 * @throws Exception
	 */
	public void update(NoticeRequestBO bo) throws Exception {
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
			setPStmtValue(10, bo.getContent());
			setPStmtValue(11, bo.getScheduledTime());
			setPStmtValue(12, bo.getRequestId());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO update(NoticeRequestBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAttempts(NoticeRequestBO bo) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.attempts"));
			setPStmtValue(1, bo.getAttempts() + 1);
			setPStmtValue(2, bo.getRequestId());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO updateAttempts(NoticeRequestBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateAttachment(NoticeRequestBO bo) throws Exception {
		try {
			boolean hasAttachment = ValidationUtil.hasAttachment(bo.getAttachment());
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.attachment"));
			setPStmtValue(1, hasAttachment ? bo.getFilename() : null);
			setPStmtValue(2, hasAttachment ? bo.getAttachment() : null);
			setPStmtValue(3, bo.getRequestId());
			
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO updateAttachment(NoticeRequestBO bo)", e);
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
	 * @param boNoticeRequestBO 
	 * <p>
	 * @throws Exception
	 */
	public void delete(NoticeRequestBO bo) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.delete"));
			setPStmtValue(1, bo.getRequestId());

			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO delete(NoticeRequestBO bo)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteByCaseMgmtId(int caseMgmtId) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.delete.by.casemgmtid"));
			setPStmtValue(1, caseMgmtId);

			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeRequestBO deleteByCaseMgmtId(NoticeRequestBO bo)", e);
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
	 * findByCaseMgmtId
	 * @param int caseMmgtId
	 * @param string subject
	 * @param boolean isStatic
	 * @return List<NoticeRequestBO>
	 * @throws Exception
	 */
	public List<NoticeRequestBO> findByCaseMgmtId(int caseMgmtId,String subject) throws Exception {
		List<NoticeRequestBO> noticeRequestBOs= new ArrayList<NoticeRequestBO>();
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			String sql = SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.by.casemgmtId");
			if (!TextUtil.isEmpty(subject))
				sql = sql + " and subject like '%"+subject + "%'";
			setPStmt(sql);
			setPStmtValue(1, caseMgmtId);
			executeQuery();
			
			while (hasMoreResults()) 
				noticeRequestBOs.add(createNoticeRequestBO());
			
		} catch (Exception e) {
			logger.error(".findByCaseMgmtId() [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
			
			close();
		}
		return noticeRequestBOs;
	}
	
	/**
	 * 
	 * @return NoticeRequestBO
	 * @throws Exception
	 */
	private NoticeRequestBO createNoticeRequestBO() throws Exception {
		NoticeRequestBO bo = new NoticeRequestBO();
		bo.setRequestId(getRSValueInt("requestid"));
		bo.setSystemId(getRSValueInt("systemid"));
		bo.setAttempts(getRSValueInt("attempts"));
		bo.setCaseMgmtId(getRSValueString("casemgmt_id"));
		bo.setCaseMgmtTable(getRSValueString("casemgmt_table"));
		bo.setNoticeType(NoticeType.resolve(getRSValueString("noticetype")));
		bo.setSendTo(getRSValueString("sendto"));
		bo.setSendFrom(getRSValueString("sendfrom"));
		bo.setSendCc(getRSValueString("sendcc"));
		bo.setSendBcc(getRSValueString("sendbcc"));
		bo.setSubject(getRSValueString("subject"));
		bo.setContent(getRSValueString("content"));
		bo.setFilename(getRSValueString("filename"));
		bo.setAttachment(getRSValueBlob("attachment"));
		bo.setScheduledTime(getRSValueDateTime("scheduled_time"));
		bo.setCourt(getRSValueString("court"));
		return bo;
	}
}
