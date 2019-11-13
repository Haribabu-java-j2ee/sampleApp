package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CorisBatch implements BaseConstants {
	
	public static int PRINT_SQL = RUN;
	private static Logger log = null;

	static {
	  try {
	    URL url = ClassLoader.getSystemResource("gov/utcourts/coriscommon/properties/logging.properties");
	    PropertyConfigurator.configure(url);
	    log = Logger.getLogger(CorisBatch.class.getName());
	  } catch (Throwable t) {
	    System.err.println("ERROR configuring Log4J: " + t.getMessage());
	  }
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DatabaseConnection.setUseJdbc();
		//getKaseVOListByOsdcStatus("S","D");
		//sendAcctNumsToOsdcByStatus("S","D");
		sendAcctNumsToOsdcByStatus("P","D");

	}
	
	public static void sendAcctNumsToOsdcByStatus(String status,String courtType) throws Exception {
		log.info("start of sendAcctNumsToOsdcByStatus method in CorisBatch class ");
		try{
			List<KaseBO> kaseVOList = getKaseVOListByOsdcStatus(status, courtType);
			if (kaseVOList != null && kaseVOList.size() > 0) {
				List<Integer> accNumList=null;
				for (KaseBO kaseBO : kaseVOList) {
					accNumList = new ArrayList<Integer>();
					
					String caseNum = kaseBO.getCaseNum();
					String locnCode = kaseBO.getLocnCode();
					
//					OsdcAcctHistoryBO osdcAcctHistoryBO=new OsdcAcctHistoryBO(courtType).where(OsdcAcctHistoryBO.OSDCSTATUS,status);
//					AccountBO accountBO=new AccountBO(courtType);
//					KaseBO kaseTwo=new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locnCode);
//					PersonnelBO personnelBO=new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, "dpx");
//					
//					SearchDescriptor osdcAcctHistorySD=new SearchDescriptor(osdcAcctHistoryBO);
//					SearchDescriptor accountSD=new SearchDescriptor(accountBO);
//					SearchDescriptor kaseSD=new SearchDescriptor(kaseTwo);
//					SearchDescriptor personnelSD = new SearchDescriptor(personnelBO);
//					
//					BaseSearchDispatcher searchDispatcher=new SearchDispatcher(osdcAcctHistorySD,accountSD,kaseSD,personnelSD)
//					.addForeignKey(OsdcAcctHistoryBO.ACCTNUM, AccountBO.ACCTNUM)
//					.addForeignKey(KaseBO.LOCNCODE, PersonnelBO.LOCNCODE)
//					.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
//					.toString(PRINT_SQL).search(OsdcAcctHistoryBO.ACCTNUM,PersonnelBO.USERIDSRL);
//					
//					List<OsdcAcctHistoryBO> osdcAcctHistoryList = (List<OsdcAcctHistoryBO>) osdcAcctHistorySD.getResults();
//					List<PersonnelBO> personnelList = (List<PersonnelBO>) personnelSD.getResults();
					
					List<OsdcAcctHistoryBO> osdcAcctHistoryList = new OsdcAcctHistoryBO(courtType)
		    		.where(OsdcAcctHistoryBO.OSDCSTATUS, status)
		    		.includeTables(
		    			new AccountBO(courtType),
		    			new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locnCode)
		    		)
		    		.addForeignKey(OsdcAcctHistoryBO.ACCTNUM, AccountBO.ACCTNUM)
					.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
					.toString(PRINT_SQL)
					.search(OsdcAcctHistoryBO.ACCTNUM, OsdcAcctHistoryBO.OSDCCREATEUSERID);
		    		
					List<String> notes = new ArrayList<String>();
					Integer userId = null;
					if (osdcAcctHistoryList != null && osdcAcctHistoryList.size() > 0) {
						userId = (Integer) osdcAcctHistoryList.get(0).get(OsdcAcctHistoryBO.OSDCCREATEUSERID);
						for(OsdcAcctHistoryBO osdcAcctHistoryBO : osdcAcctHistoryList) {
							accNumList.add(osdcAcctHistoryBO.getAcctNum());
							notes.add("Send to OSDC on Prison Sentence.");
						}
					}
					
					if (userId != null &&  accNumList.size() > 0) {
						try {
							AccountXO.processAccountsForOsdc(accNumList, userId, notes, courtType);
						} catch (Exception ex) {
							if (!ex.getMessage().startsWith("Judgment Warning")) {
								// This is not a judgment warning.  Throw the exception on.
								throw ex;
							}
						}
					}
					
				}
			}
		
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Failed: Exception in sendAcctNumsToOsdcByStatus method, CorisBatch class "+ e);
			throw e;
		}
		
		log.info("Success: end of sendAcctNumsToOsdcByStatus method in CorisBatch class ");
	}
	
	private static List<KaseBO> getKaseVOListByOsdcStatus(String status,String courtType) throws Exception {
		log.info("start of getKaseVOListByOsdcStatus method in CorisBatch class ");
		List<KaseBO> kaseList = new ArrayList<KaseBO>();
		try {
	
//			OsdcAcctHistoryBO osdcAcctHistoryBO=new OsdcAcctHistoryBO(courtType).where(OsdcAcctHistoryBO.OSDCSTATUS,status);
//			AccountBO accountBO=new AccountBO(courtType);
//			KaseBO kaseBO=new KaseBO(courtType).includeFields(KaseBO.CASENUM).setDistinct();
//			
//			SearchDescriptor osdcAcctHistorySD=new SearchDescriptor(osdcAcctHistoryBO);
//			SearchDescriptor accountSD=new SearchDescriptor(accountBO);
//			SearchDescriptor kaseSD=new SearchDescriptor(kaseBO);
//			
//			BaseSearchDispatcher searchDispatcher=new SearchDispatcher(osdcAcctHistorySD,accountSD,kaseSD)
//			.addForeignKey(OsdcAcctHistoryBO.ACCTNUM, AccountBO.ACCTNUM)
//			.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
//			.toString(PRINT_SQL).search(KaseBO.CASENUM,KaseBO.LOCNCODE,KaseBO.INTCASENUM);
//			
//			List<KaseBO> kaseList = (List<KaseBO>) kaseSD.getResults();
			
    		kaseList = new KaseBO(courtType).includeFields(KaseBO.CASENUM, KaseBO.LOCNCODE, KaseBO.INTCASENUM).setDistinct()
    		.includeTables(
				new OsdcAcctHistoryBO(courtType).where(OsdcAcctHistoryBO.OSDCSTATUS, status),
				new AccountBO(courtType)
    		)
    		.addForeignKey(OsdcAcctHistoryBO.ACCTNUM, AccountBO.ACCTNUM)
			.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
			.toString(PRINT_SQL)
			.search(KaseBO.CASENUM, KaseBO.LOCNCODE, KaseBO.INTCASENUM);
			
		} catch(Exception e) {
			e.printStackTrace();
			log.error("Exception in getKaseVOListByOsdcStatus method, CorisBatch class "+ e);
			throw e;
		}
		log.info("end of getKaseVOListByOsdcStatus method in CorisBatch class ");
		return kaseList;
	}
	

}
