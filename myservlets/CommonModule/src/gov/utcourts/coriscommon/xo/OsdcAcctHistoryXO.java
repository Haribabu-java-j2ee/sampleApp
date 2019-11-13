package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class OsdcAcctHistoryXO implements BaseConstants {
	
	private static final Logger log = Logger.getLogger(OsdcAcctHistoryXO.class);
	public static int PRINT_SQL = RUN;
	
	public static OsdcAcctHistoryBO getMostRecentStatusByAcctNum(int acctNum, String courtType) throws Exception {
		log.debug("<< Entering OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(int AcctNum, String courtType)  >>");
		
		try {
			OsdcAcctHistoryBO status = new OsdcAcctHistoryBO(courtType)
			.where(OsdcAcctHistoryBO.ACCTNUM, acctNum)
			.orderBy(OsdcAcctHistoryBO.OSDCACCTSEQ.desc()).setReturnNull(true)
			.toString(PRINT_SQL).find(OsdcAcctHistoryBO.OSDCSTATUS);
			return status;
		} catch(Exception e) {
			log.error("Exception in OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(int intCaseNum, String courtType) ",e);
			throw e;
		} 
	}
	public static OsdcAcctHistoryBO getMostRecentStatusByAcctNum(int acctNum, String courtType,Connection conn) throws Exception {
		log.debug("<< Entering OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(int AcctNum, String courtType)  >>");
		
		try {
			OsdcAcctHistoryBO status = new OsdcAcctHistoryBO(courtType)
			.where(OsdcAcctHistoryBO.ACCTNUM, acctNum)
			.orderBy(OsdcAcctHistoryBO.OSDCACCTSEQ.desc())
			.setUseConnection(conn).setReturnNull(true)
			.toString(PRINT_SQL).find(OsdcAcctHistoryBO.OSDCSTATUS);
			return status;
		} catch(Exception e) {
			log.error("Exception in OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(int intCaseNum, String courtType) ",e);
			throw e;
		} 
	}
	
	public static void updateStatusToSent(int accountNumber, String courtType) throws Exception {
    	try {
	
    		new OsdcAcctHistoryBO(courtType)
	    	.setOsdcStatus("S")
	    	.setOsdcSentDatetime(Calendar.getInstance().getTime())
	    	.where(OsdcAcctHistoryBO.OSDCACCTSEQ, Exp.EQUALS, 
	    		Exp.select(
	    			new OsdcAcctHistoryBO(courtType)
	    			.max(OsdcAcctHistoryBO.OSDCACCTSEQ.as("osdc_acct_seq_max"))
	    			.where(OsdcAcctHistoryBO.ACCTNUM, accountNumber)
	    			.where(OsdcAcctHistoryBO.OSDCSTATUS, "R")
	    		)
			)
			.update();
	
	    } catch (Exception e) {
	    	log.error("Exception in updateOsdcByAccount: Account Number = "
	    	.concat(Integer.toString(accountNumber)), e);
	    	throw e;
    	}
    }
	
	public static void main(String[] args){
		DatabaseConnection.setUseJdbc();
		try {
			updateStatusToSent(12345, "D");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
