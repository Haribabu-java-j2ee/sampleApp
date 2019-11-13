package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.coriscommon.dto.CrimCaseDTO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinFindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CrimCaseXO implements BaseConstants { 

	private static Logger logger = Logger.getLogger(CrimCaseXO.class);
	public static int PRINT_SQL = RUN;

	public static void main(String args[]) throws Exception
	{
		DatabaseConnection.setUseJdbc();
//		getAccounts(123456,"'d','s','k'","D");
//		getDaysPastDue(12345, 987645, "D");
//		getLastPaidDate(123456,"'d','s','k'","D");
		ProfileBO profileBO = new ProfileBO("D");
		profileBO.setLocnCode("1868");
		profileBO.setCourtType("D");
		
//		processDomesticViolance(12222, "D");
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(CrimCaseBO.CORIS_DISTRICT_DB);
			findCrimCases(profileBO, "D",conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	 
	  /**
     * Returns list of account numbers.
     * 
     * @param intCaseNumber 
     * @param accountTypes
     * @param courtType
     * @return List<Integer> 
     * @throws Exception
     */
	
	 public static List<Integer> getAccounts(int intCaseNumber, String accountTypes,String courtType, Connection conn) throws Exception {
		List<String> accountTypeList = getAcountTypeList(accountTypes);
			 
		String courtDatabaseToConnect = "D".equalsIgnoreCase(courtType) ? "D" : "J";
		AccountBO accountBO = new AccountBO(courtDatabaseToConnect).where(AccountBO.INTCASENUM, intCaseNumber);
	  	if (accountTypeList.size() > 0)
	  		accountBO.where(AccountBO.ACCTTYPE, Exp.IN, accountTypeList);
	
	  	List<AccountBO> results = accountBO.includeTables(
	  		new AcctTrustBO(courtType),
	  		new TrustTypeBO(courtType)
	  	)
	  	.setOuter(new AcctTrustBO(courtType), new TrustTypeBO(courtType))
	  	.addForeignKey(TrustTypeBO.TRUSTCODE, AcctTrustBO.TRUSTCODE)
	  	.addForeignKey(AcctTrustBO.ACCTNUM, AccountBO.ACCTNUM)
	  	.addSearchWrapDescriptor("select * from (", ") where openend_flag is null or openend_flag != 'Y'")
	  	.toString(PRINT_SQL)
	  	.setUseConnection(conn)
	  	.search(AccountBO.ACCTNUM, TrustTypeBO.OPENENDFLAG);		  
 
	  	List<Integer> accounts = new ArrayList<Integer>();
		if (results != null && results.size() > 0) {
			for (AccountBO accBO : results) 
				accounts.add(accBO.getAcctNum());
		}
		return accounts;
	 }
	 
	 /**
	     * Returns list of account numbers.
	     * 
	     * @param intCaseNumber 
	     * @param accountTypes
	     * @param courtType
	     * @return List<Integer> 
	     * @throws Exception
	     */
		
	 	public static Map<Integer, List<Integer>> getIntCaseNumAndAccountsMap(List<Integer> intCaseNumList, String accountTypes,String courtType,Connection conn) throws Exception {
			Map<Integer, List<Integer>> intCaseNumAccountsMap = new HashMap<Integer, List<Integer>>();
			List<String> accountTypeList = getAcountTypeList(accountTypes);
					 
			String courtDatabaseToConnect = "D".equalsIgnoreCase(courtType) ? "D" : "J";
			
			AccountBO accountBO = new AccountBO(courtDatabaseToConnect).where(AccountBO.INTCASENUM, Exp.IN, intCaseNumList);
			if (accountTypeList.size() > 0)
				accountBO.where(AccountBO.ACCTTYPE, Exp.IN, accountTypeList);
			
			List<AccountBO> results = accountBO.includeTables(
				new AcctTrustBO(courtType),
				new TrustTypeBO(courtType)
			)
			.setOuter(new AcctTrustBO(courtType), new TrustTypeBO(courtType))
			.addForeignKey(TrustTypeBO.TRUSTCODE, AcctTrustBO.TRUSTCODE)
			.addForeignKey(AcctTrustBO.ACCTNUM, AccountBO.ACCTNUM)
			.addSearchWrapDescriptor("select * from (", ") where openend_flag is null or openend_flag != 'Y'")
			.toString(PRINT_SQL)
			.setUseConnection(conn)
			.search(AccountBO.INTCASENUM, AccountBO.ACCTNUM, TrustTypeBO.OPENENDFLAG);		  
		 
			if (results != null && results.size() > 0) {
				List<Integer> accNumList = null;
				for (AccountBO accBO : results) {
					int caseNum = accBO.getIntCaseNum();
					if (intCaseNumAccountsMap.containsKey(caseNum)) {
						accNumList.add(accBO.getAcctNum());
					} else {
						accNumList = new ArrayList<Integer>();
						accNumList.add(accBO.getAcctNum());
						intCaseNumAccountsMap.put(caseNum, accNumList);
					}
				}
			}
			return intCaseNumAccountsMap;
		}
	    
	    /**
	     * Returns the last date a payment was made on the given case.
	     * 
	     * @param intCaseNumber 
	     * @param accountTypes
         * @param courtType
	     * @return Date
	     * @throws Exception
	     */
	    public static Date getLastPaidDate(int intCaseNumber, String accountTypes,String courtType,Connection conn) throws Exception {
	    	Date lastPayDate = null;

	    	List<String> accountTypeList = getAcountTypeList(accountTypes);
	    	
	    	AccountBO accountBO = new AccountBO(courtType)
	    	.includeTables(
    			new TransBO(courtType).max(TransBO.TRANSDATETIME.as("trans_datetime_max")),
    			new TransDistBO(courtType).includeFields(TransDistBO.NO_FIELDS).where(
    				Exp.LEFT_PARENTHESIS, 
    					TransDistBO.APPLYCODE, Exp.IS_NULL,
    					Exp.OR,
    					TransDistBO.APPLYCODE, Exp.NOT_EQUALS, "TO",
    				Exp.RIGHT_PARENTHESIS
    			)
    		)
    		.includeFields(AccountBO.NO_FIELDS)
    		.addForeignKey(TransBO.INTJOURNALNUM, TransDistBO.INTJOURNALNUM)
			.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
			.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
    		.where(
	    		Exp.LEFT_PARENTHESIS, 
	    			AccountBO.AMTDUE, Exp.MINUS,
					Exp.LEFT_PARENTHESIS,
						AccountBO.AMTPAID, Exp.PLUS, AccountBO.AMTCREDIT,
					Exp.RIGHT_PARENTHESIS,
					Exp.GREATER_THAN, 0,
				Exp.RIGHT_PARENTHESIS
			)
			.where(AccountBO.INTCASENUM, intCaseNumber);
	    	
	    	if (accountTypeList.size() > 0)
	    		 accountBO.where(AccountBO.ACCTTYPE, Exp.IN, new StringArrayDescriptor(accountTypeList));
	    	 
	    	List<AccountBO> results = accountBO.toString(PRINT_SQL).setUseConnection(conn).search();
	    	if (results != null && results.size() == 1)     		   
	    		lastPayDate = (Date) results.get(0).get("trans_datetime_max");
	     
	    	return lastPayDate;
	    }

	    private static List<String> getAcountTypeList(String accountTypes) {
	    	List<String> accountTypeList = new ArrayList<String>();
			if (accountTypes != null && accountTypes.length() > 0) {
				for (String accType : accountTypes.replace("'", "").split(",")) {
					accountTypeList.add(accType.trim());
				} 
			}	
			return accountTypeList;
	    }
	    
	    @SuppressWarnings("unchecked")
		public static List<CrimCaseDTO> findCrimCases(ProfileBO profileBO, String courtType, Connection conn) throws Exception {

			List<CrimCaseDTO> results = (List<CrimCaseDTO>) new WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.INTCASENUM, WorkCalCaseBO.CASENUM,	WorkCalCaseBO.LOCNCODE,	WorkCalCaseBO.COURTTYPE, WorkCalCaseBO.CASETYPE, WorkCalCaseBO.CASETYPECATEGORY, WorkCalCaseBO.CASETYPEDESCR, WorkCalCaseBO.CASETITLE).where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode())
    		.setDistinct()
			.includeTables(
				new CrimCaseBO(courtType).includeFields(CrimCaseBO.CITNUM, CrimCaseBO.LEA, CrimCaseBO.LEACASENUM, CrimCaseBO.PROSECAGENCY, CrimCaseBO.PROSECAGENCYNUM, CrimCaseBO.SHERIFFNUM),
    			new PartyCaseBO(courtType).includeFields(PartyCaseBO.PARTYNUM, PartyCaseBO.PARTYCODE, PartyCaseBO.OTN),
    			new WarrantBO(courtType).includeFields(WarrantBO.ISSUEDATE),
    			new KaseBO(courtType).includeFields(KaseBO.DOMVIOLENCE)
    		)
			.addJoin(JoinType.LEFT_OUTER_JOIN, CrimCaseBO.TABLE.getTableName(), CrimCaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
			.addJoin(JoinType.LEFT_OUTER_JOIN, PartyCaseBO.TABLE.getTableName(), PartyCaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM, new JoinFindDescriptor(PartyCaseBO.PARTYCODE, Exp.EQUALS, "DEF"))
			.addJoin(JoinType.LEFT_OUTER_JOIN, WarrantBO.TABLE.getTableName(), WarrantBO.INTCASENUM, WorkCalCaseBO.INTCASENUM, new JoinFindDescriptor(WarrantBO.RECALLDATE, Exp.IS_NULL))
			.addJoin(JoinType.LEFT_OUTER_JOIN, KaseBO.TABLE.getTableName(), KaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
			.setResultContainer(new CrimCaseDTO(courtType))
			.setUseConnection(conn)
			.toString(PRINT_SQL)
			.searchAndGetResults();

			return results;
		}  
		
		public static String processDomesticViolance(int intCaseNum, String courtType) throws SystemException {

	        int dvFlag = 0;

	        try {
	        	dvFlag = new KaseBO(courtType)
	            .count(KaseBO.INTCASENUM)
	            .where(KaseBO.INTCASENUM, intCaseNum)
	            .where(KaseBO.DOMVIOLENCE, "Y")
	            .toString(PRINT_SQL)
	            .find()
	            .getCount();
	        } catch (Exception e) {
	            throw new SystemException(e, "Exception processDomesticViolance(int intCaseNum)");
	        }

	        return dvFlag == 0 ? "N" : "Y";
	    }

}
