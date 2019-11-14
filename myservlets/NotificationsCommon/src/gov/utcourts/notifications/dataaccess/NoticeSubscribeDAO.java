package gov.utcourts.notifications.dataaccess;

import gov.utcourts.notifications.NoticeSubscribeBO;
import gov.utcourts.notifications.NoticeSubscribeBO.SubscribeType;
import gov.utcourts.notifications.util.SQLPropertiesUtil;
import gov.utcourts.notifications.util.TextUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class NoticeSubscribeDAO extends BaseDAO {

	private static Logger logger = Logger.getLogger(NoticeSubscribeDAO.class);
	
	/**
	 * Method to return NoticeSubscribeBO record
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @param  SubscribeType subscribeType
	 * @return NoticeSubscribeBO
	 * @throws Exception
	 */
	public NoticeSubscribeBO find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) throws Exception {
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			if (SubscribeType.EMAIL.equals(subscribeType)) {
				setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.email"));
				setPStmtValue(1, noticeSubscribeBO.getCaseMgmtId());
				setPStmtValue(2, noticeSubscribeBO.getCaseMgmtTable());
				setPStmtValue(3, noticeSubscribeBO.getEmail());
			} else if (SubscribeType.MOBILE.equals(subscribeType)) {
				setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.mobile"));
				setPStmtValue(1, noticeSubscribeBO.getCaseMgmtId());
				setPStmtValue(2, noticeSubscribeBO.getCaseMgmtTable());
				setPStmtValue(3, noticeSubscribeBO.getMobile());
			} else if (SubscribeType.ID.equals(subscribeType)) {
				setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.by.system"));
				setPStmtValue(1, noticeSubscribeBO.getCaseMgmtId());
				setPStmtValue(2, noticeSubscribeBO.getCaseMgmtTable()!=null?noticeSubscribeBO.getCaseMgmtTable().toLowerCase():"");
				setPStmtValue(3, noticeSubscribeBO.getSystemId());
			}
			executeQuery();
			
			if (hasMoreResults()) {
				noticeSubscribeBO.setSubscribeId(getRSValueInt("subscribeid"));
				noticeSubscribeBO.setCaseMgmtId(getRSValueString("casemgmt_id"));
				noticeSubscribeBO.setCaseMgmtTable(getRSValueString("casemgmt_table"));
				noticeSubscribeBO.setSystemId(getRSValueInt("systemid"));
				noticeSubscribeBO.setEmail(getRSValueString("email"));
				noticeSubscribeBO.setEmailVerified(getRSValueDate("emailverified"));
				noticeSubscribeBO.setMobile(getRSValueString("mobile"));
				noticeSubscribeBO.setMobileVerified(getRSValueDate("mobileverified"));
				noticeSubscribeBO.setSmsAddress(getRSValueString("smsaddress"));
				noticeSubscribeBO.setSmsProvider(getRSValueString("smsprovider"));
			}
		} catch (Exception e) {
			logger.error(".find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
		
			close();
		}
		return noticeSubscribeBO;
	}
	
	public NoticeSubscribeBO retrieveForVerification(NoticeSubscribeBO noticeSubscribeBO, String uniqueId) throws Exception {
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.to.verify"));
			setPStmtValue(1, noticeSubscribeBO.getEmail());
			setPStmtValue(2, noticeSubscribeBO.getSystemId());
			executeQuery();
			
			while (hasMoreResults()) {
				int uniqueIdentifier = TextUtil.getUniqueId(getRSValueInt("casemgmt_id"), getRSValueString("casemgmt_table"));
				if (uniqueId.equalsIgnoreCase(Integer.toString(uniqueIdentifier))) {
					noticeSubscribeBO.setSubscribeId(getRSValueInt("subscribeid"));
					noticeSubscribeBO.setCaseMgmtId(getRSValueString("casemgmt_id"));
					noticeSubscribeBO.setCaseMgmtTable(getRSValueString("casemgmt_table"));
					noticeSubscribeBO.setSystemId(getRSValueInt("systemid"));
					noticeSubscribeBO.setEmail(getRSValueString("email"));
					noticeSubscribeBO.setEmailVerified(getRSValueDate("emailverified"));
					noticeSubscribeBO.setMobile(getRSValueString("mobile"));
					noticeSubscribeBO.setMobileVerified(getRSValueDate("mobileverified"));
					noticeSubscribeBO.setSmsAddress(getRSValueString("smsaddress"));
					noticeSubscribeBO.setSmsProvider(getRSValueString("smsprovider"));
				}
			}
		} catch (Exception e) {
			logger.error(".find(NoticeSubscribeBO noticeSubscribeBO, SubscribeType subscribeType) [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
		
			close();
		}
		return noticeSubscribeBO;
	}
	
	public NoticeSubscribeBO findBySystem(String caseMgmtId, String table, String systemId) throws Exception {
		NoticeSubscribeBO noticeSubscribeBO = null;
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.find.by.system"));
			setPStmtValue(1, caseMgmtId);
			setPStmtValue(2, table.toLowerCase());
			setPStmtValue(3, systemId);
			executeQuery();
			
			if (hasMoreResults()) {
				noticeSubscribeBO = new NoticeSubscribeBO();
				noticeSubscribeBO.setCaseMgmtId(caseMgmtId);
				noticeSubscribeBO.setSubscribeId(getRSValueInt("subscribeid"));
				noticeSubscribeBO.setEmail(getRSValueString("email"));
				noticeSubscribeBO.setMobile(getRSValueString("mobile"));
				noticeSubscribeBO.setSmsAddress(getRSValueString("smsaddress"));
				noticeSubscribeBO.setSmsProvider(getRSValueString("smsprovider"));
			}
		} catch (Exception e) {
			logger.error(".findBySystem(String caseMgmtId, String systemId) [Exception]", e);
			throw e;
		} finally {
			setPStmt("set isolation to committed read");
			execute();
		
			close();
		}
		return noticeSubscribeBO;
	}
	
	/**
	 * returns the search results for the specified search criteria.
	 * @param NoticeSubscribeBO noticeSubscribeBO
	 * @return List<NoticeSubscribeBO>
	 * @throws Exception
	 */
	public List<NoticeSubscribeBO> search(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		List<NoticeSubscribeBO> retVal = new ArrayList<NoticeSubscribeBO>();
		
		StringBuilder fields = new StringBuilder();
		fields.append("select *");
		
		ArrayList<String>tables = new ArrayList<String>();
		ArrayList<String>where = new ArrayList<String>();
		ArrayList<String>psValues = new ArrayList<String>();
		
		// add in the base tables and linking fields
		tables.add("notice_subscribe");
		
		// build the dynamic sql
		if (!TextUtil.isEmpty(noticeSubscribeBO.getMobile())) {
			where.add("mobile like ?");
			psValues.add((noticeSubscribeBO.getMobile()) + "%");
		}
		
		if (!TextUtil.isEmpty(noticeSubscribeBO.getEmail())) {
			where.add("email like ?");
			psValues.add((noticeSubscribeBO.getEmail()) + "%");
		}
		
		String countSql = buildSQL(fields.toString(), tables, where);
		String sql = buildSQL(fields.toString(), tables, where) + " order by subscribeid";
		logger.debug("dynamic sql [" + sql + "]");
		
		try {
			setPStmt("set isolation to dirty read");
			execute();
			
			doSearch(countSql, sql, psValues);
			
			while (hasMoreResults()) {
				retVal.add(createNoticeSubscribeBO());
			}
			
			setPStmt("set isolation to committed read");
			execute();
		} catch (Exception e) {
			logger.error("Exception in search", e);
			throw e;
		} finally {
			close();
		}	
		
		fields = null;
		tables = null;
		where = null;
		psValues = null;
		
		return retVal;
	}
	
	/**
	 * inserts new record 
	 * @param noticeSubscribeBO NoticeSubscribeBO 
	 * @return NoticeSubscribeBO
	 * <p>
	 * @throws Exception
	 */
	public NoticeSubscribeBO insert(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.insert"));
			setPStmtValue(1, noticeSubscribeBO.getSystemId());
			setPStmtValue(2, noticeSubscribeBO.getCaseMgmtId());
			setPStmtValue(3, noticeSubscribeBO.getCaseMgmtTable());
			setPStmtValue(4, noticeSubscribeBO.getMobile());
			setPStmtValue(5, noticeSubscribeBO.getEmail());
			setPStmtValue(6, noticeSubscribeBO.getSmsAddress());
			setPStmtValue(7, noticeSubscribeBO.getSmsProvider());
			setPStmtValue(8, new Date());
			execute();
			
			noticeSubscribeBO.setSubscribeId(getIdentityValueInt());
			
			return noticeSubscribeBO;
		} catch (Exception e) {
			logger.error("Exception in NoticeSubscribeBO insert(NoticeSubscribeBO noticeSubscribeBO)", e);
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
	 * @param noticeSubscribeBO NoticeSubscribeBO 
	 * <p>
	 * @throws Exception
	 */
	public void update(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update"));
			setPStmtValue(1, noticeSubscribeBO.getMobile());
			setPStmtValue(2, noticeSubscribeBO.getEmail());
			setPStmtValue(3, noticeSubscribeBO.getSmsAddress());
			setPStmtValue(4, noticeSubscribeBO.getSmsProvider());
			setPStmtValue(5, new Date());
			setPStmtValue(6, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeSubscribeBO update(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateSms(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.sms"));
			setPStmtValue(1, noticeSubscribeBO.getSmsAddress());
			setPStmtValue(2, noticeSubscribeBO.getSmsProvider());
			setPStmtValue(3, new Date());
			setPStmtValue(4, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeSubscribeBO updateSMSSetting(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public NoticeSubscribeBO changeEmailAddress(NoticeSubscribeBO noticeSubscribeBO, String newEmailAddress) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.email"));
			setPStmtValue(1, newEmailAddress);
			setPStmtValue(2, new Date());
			setPStmtValue(3, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeSubscribeBO changeEmailAddress(NoticeSubscribeBO noticeSubscribeBO, String newEmailAddress)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		noticeSubscribeBO.setEmail(newEmailAddress);
		return noticeSubscribeBO;
	}
	
	public NoticeSubscribeBO changeMobileNumber(NoticeSubscribeBO noticeSubscribeBO, String newMobileNumber) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.mobile"));
			setPStmtValue(1, newMobileNumber);
			setPStmtValue(2, new Date());
			setPStmtValue(3, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in NoticeSubscribeBO changeMobileNumber(NoticeSubscribeBO noticeSubscribeBO, String newMobileNumber)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		noticeSubscribeBO.setMobile(newMobileNumber);
		return noticeSubscribeBO;
	}
	
	public void updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.emailverified"));
			setPStmtValue(1, new Date());
			setPStmtValue(2, new Date());
			setPStmtValue(3, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in void updateEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) ", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.update.mobileverified"));
			setPStmtValue(1, new Date());
			setPStmtValue(2, new Date());
			setPStmtValue(3, noticeSubscribeBO.getSubscribeId());
			execute();
		} catch (Exception e) {
			logger.error("Exception in void updateMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO)", e);
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
	 * @param noticeSubscribeBO NoticeSubscribeBO 
	 * <p>
	 * @throws Exception
	 */
	public void delete(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.delete"));
			setPStmtValue(1, noticeSubscribeBO.getSubscribeId());

			execute();
		} catch (Exception e) {
			logger.error("Exception in void delete(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.remove.emailverified"));
			setPStmtValue(1, new Date());
			setPStmtValue(2, noticeSubscribeBO.getSubscribeId());

			execute();
		} catch (Exception e) {
			logger.error("Exception in void removeEmailVerifiedDate(NoticeSubscribeBO noticeSubscribeBO)", e);
			throw e;
		} finally {
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO) throws Exception {
		try {
			setPStmt(SQLPropertiesUtil.getProperty(getClass().getName(), "sql.remove.mobileverified"));
			setPStmtValue(1, new Date());
			setPStmtValue(2, noticeSubscribeBO.getSubscribeId());

			execute();
		} catch (Exception e) {
			logger.error("Exception in void removeMobileVerifiedDate(NoticeSubscribeBO noticeSubscribeBO)", e);
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
	 * returns a definition object
	 * @return List<NoticeSubscribeBO>
	 * @throws Exception
	 */
	private NoticeSubscribeBO createNoticeSubscribeBO() throws Exception {
		NoticeSubscribeBO noticeSubscribeBO = new NoticeSubscribeBO();
		noticeSubscribeBO.setSubscribeId(getRSValueInt("subscribeid"));
		noticeSubscribeBO.setSystemId(getRSValueInt("systemid"));
		noticeSubscribeBO.setCaseMgmtId(getRSValueString("casemgmt_id"));
		noticeSubscribeBO.setCaseMgmtTable(getRSValueString("casemgmt_table"));
		noticeSubscribeBO.setMobile(getRSValueString("mobile"));
		noticeSubscribeBO.setMobileVerified(getRSValueDate("mobileverified"));
		noticeSubscribeBO.setEmail(getRSValueString("email"));
		noticeSubscribeBO.setEmailVerified(getRSValueDate("emailverified"));
		noticeSubscribeBO.setSmsAddress(getRSValueString("smsaddress"));
		noticeSubscribeBO.setSmsProvider(getRSValueString("smsprovider"));
		return noticeSubscribeBO;
	}
}
