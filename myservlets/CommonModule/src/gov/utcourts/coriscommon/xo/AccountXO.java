package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctadj.AcctAdjBO;
import gov.utcourts.coriscommon.dataaccess.acctbail.AcctBailBO;
import gov.utcourts.coriscommon.dataaccess.acctbond.AcctBondBO;
import gov.utcourts.coriscommon.dataaccess.acctdist.AcctDistBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.checkbatch.CheckBatchBO;
import gov.utcourts.coriscommon.dataaccess.checkbatchdetl.CheckBatchDetlBO;
import gov.utcourts.coriscommon.dataaccess.dcaccount.DcAccountBO;
import gov.utcourts.coriscommon.dataaccess.dcacctdist.DcAcctDistBO;
import gov.utcourts.coriscommon.dataaccess.dcaccttrust.DcAcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.debtcoll.DebtCollBO;
import gov.utcourts.coriscommon.dataaccess.feetype.FeeTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;
import gov.utcourts.coriscommon.dto.DebtCollectionAccountDTO;
import gov.utcourts.coriscommon.dto.FeeDTO;
import gov.utcourts.coriscommon.util.CaseSentenceAccountProcessor;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ForeignKeyDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntegerArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.InsertSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TransactionDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeDouble;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.BaseStoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.BaseTransactionDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class AccountXO implements BaseConstants {
	
	private static final Logger log = Logger.getLogger(AccountXO.class);
	public static int PRINT_SQL = RUN;
	
	public static List<AccountBO> getAccountsByIntCaseNum(int intCaseNum, String courtType) throws Exception {
		log.debug("<< Entering  AccountFacade.getAccountsByIntCaseNum(int intCaseNum, String courtType, boolean useStatic)  >>");
		try {
			return new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum).toString(PRINT_SQL).search();
		} catch(Exception e) {
			log.error("Exception in AccountFacade.getAccountsByIntCaseNum(int intCaseNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static AccountBO getAccountByAcctNum(Connection connection, int acctNum, String courtType) throws Exception{
		log.debug("<< Entering AccountFacade.getAccountByAcctNum(int accountNum, String courtType, boolean useStatic)  >>");
		try {
			return new AccountBO(courtType).setUseConnection(connection).where(AccountBO.ACCTNUM, acctNum).toString(PRINT_SQL).find();	
		} catch(Exception e) {
			log.error("Exception in AccountFacade.getAccountByAcctNum(accountNum, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	
	public static List<AccountBO> getAccountsByAcctNumList(List<Integer> acctNums, String courtType, Connection conn) throws Exception{
		log.debug("<< Entering getAccountsByAcctNumList(List<Integer> acctNum, String courtType))  >>");
		try{
			return new AccountBO(courtType).where(AccountBO.ACCTNUM, Exp.IN, acctNums).setUseConnection(conn).toString(PRINT_SQL).search();	
		} catch(Exception e) {
			log.error("Exception in getAccountsByAcctNumList(List<Integer> acctNum, String courtType) ",e);
			throw e;
		} 
	}
	
	public static AccountBO getAccountByAcctNumAcctType(int acctNum, String acctType, String courtType) throws Exception{
		log.debug("<< Entering AccountFacade.getAccountByAcctNum(int accountNum, String acctType, String courtType, boolean useStatic)  >>");
		try{
			return new AccountBO(courtType)
					.where(AccountBO.ACCTNUM,acctNum)
					.where(AccountBO.ACCTTYPE,acctType)
					.toString(PRINT_SQL).find();
		} catch(Exception e) {
			log.error("Exception in AccountFacade.getAccountByAcctNum(accountNum, String acctType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static List<AccountBO> getAccountsOnTimePayByIntCaseNum(int intCaseNum, String courtType) throws Exception {
		log.debug("<< Entering  AccountFacade.getAccountsOnTimePayByIntCaseNum(int intCaseNum, String courtType, boolean useStatic)  >>");
		try{
			return (List<AccountBO>)new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum)
				.where(new FindDescriptor(AccountBO.TIMEPAYNUM).setCustomSearch("IS NOT NULL"))
				.where(new FindDescriptor(AccountBO.ACCTTYPE).setCustomSearch("IN ('F','I','T')")).toString(PRINT_SQL).search();
		} catch(Exception e) {
			log.error("Exception in AccountFacade.getAccountsOnTimePayByIntCaseNum(int intCaseNum, String courtType, boolean useStatic) ",e);
			throw e;
		} 
	}
	
	/**
     * Returns a List of DebtCollectionAccountDTOs using the given intCaseNumber.
     * 
     * @param int intCaseNumber
     * @param String courtType
     * @param boolean useStatic
     * @return List<DebtCollectionAccountDTO>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static List<DebtCollectionAccountDTO> findDebtCaseAccounts(int intCaseNumber, String courtType) throws Exception {

    	Connection conn = "D".equalsIgnoreCase(courtType) ? DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB): DatabaseConnection.getConnection(AccountBO.CORIS_JUSTICE_DB);
        List <DebtCollectionAccountDTO> returnList = new ArrayList<DebtCollectionAccountDTO>();

		try {
			FieldOperationDescriptor max = new FieldOperationDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ, FieldOperationType.MAX, new TypeInteger());
			AccountBO accountBO=new AccountBO(courtType).as("t10");
			
			
			BaseSearchDispatcher subQuerySearchFactory = new SearchDispatcher(					
					new SearchDescriptor(new AcctTrustBO(courtType).includeFields(AcctTrustBO.ACCTNUM)),
					new SearchDescriptor(new TrustTypeBO(courtType).includeFields(TrustTypeBO.NO_FIELDS).where(TrustTypeBO.OPENENDFLAG, "Y")))
			.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
			.addForeignKey(new TableAndFieldDescriptor(AcctTrustBO.ACCTNUM), new TableAndFieldDescriptor("t10", AccountBO.ACCTNUM))
			.toString(PRINT_SQL);
			
			
			accountBO.where(AccountBO.INTCASENUM, intCaseNumber)
			.where(new FindDescriptor(AccountBO.ACCTTYPE,Exp.IN,new StringArrayDescriptor("F","I","T")))	
			.as(AccountBO.USERIDSRL, "userId")
			.addWhereDescriptors(
			new WhereSelectDescriptor(
					new TableAndFieldDescriptor(AcctTrustBO.ACCTNUM),
					subQuerySearchFactory.getSearchDispatcherParameters(),
					Exp.NOT_IN
			)		
			)
			.orderBy(new SortCustomDescriptor("last_name, first_name, t10.acct_num"));
			
			SearchDescriptor sAcct = new SearchDescriptor(accountBO);
			
			OsdcAcctHistoryBO osdcAcctHistoryBO=new OsdcAcctHistoryBO(courtType).as(OsdcAcctHistoryBO.DEBTCOLLNOTE, "note")
			.addWhereDescriptors(
					new WhereSelectDescriptor(
					new TableAndFieldDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ),
					new OsdcAcctHistoryBO(courtType).setFieldOperations(max)
							.where(new FindDescriptor(OsdcAcctHistoryBO.ACCTNUM)
									.setCustomSearch("= t10.acct_num")))).setOuter();
			
			 
			SearchDescriptor sKase = new SearchDescriptor(new KaseBO(courtType));
			SearchDescriptor sParty = new SearchDescriptor(new PartyBO(courtType));
			SearchDescriptor sCaseType = new SearchDescriptor(new CaseTypeBO(courtType));
			SearchDescriptor sOsdcHist = new SearchDescriptor(osdcAcctHistoryBO);
			SearchDescriptor acctFeeSD = new SearchDescriptor(new AcctFeeBO(courtType).setOuter());
			SearchDescriptor acctTrustSD= new SearchDescriptor(new AcctTrustBO(courtType).setOuter()) ;
		 
					
			BaseSearchDispatcher searchFactory = new SearchDispatcher(sAcct, sKase, sParty, sCaseType, sOsdcHist,acctFeeSD,acctTrustSD)
				.setUseConnection(conn)
				.addForeignKey(
					new ForeignKeyDescriptor(PartyBO.PARTYNUM, AccountBO.PARTYNUM),
					new ForeignKeyDescriptor(AccountBO.INTCASENUM, KaseBO.INTCASENUM),
					new ForeignKeyDescriptor(CaseTypeBO.CASETYPE, KaseBO.CASETYPE),
					new ForeignKeyDescriptor(AcctFeeBO.ACCTNUM, AccountBO.ACCTNUM),
					new ForeignKeyDescriptor(AccountBO.ACCTNUM, OsdcAcctHistoryBO.ACCTNUM),
					new ForeignKeyDescriptor(AccountBO.ACCTNUM, AcctTrustBO.ACCTNUM)
				).setResultContainer(new DebtCollectionAccountDTO()).toString(PRINT_SQL).search(
											 AccountBO.ACCTTYPE,
											 AccountBO.AMTDUE,
											 AccountBO.AMTPAID,
											 AccountBO.AMTCREDIT,
											 AccountBO.DUEDATE,
											 AccountBO.STATUS,
											 AccountBO.USERIDSRL,
											 AccountBO.ACCTNUM,
											 PartyBO.PARTYNUM,
											 PartyBO.FIRSTNAME,
											 PartyBO.LASTNAME,
											 KaseBO.CASENUM,
											 KaseBO.LOCALDEBTCOLL,
											 CaseTypeBO.DESCR,
											 CaseTypeBO.CATEGORY,
											 OsdcAcctHistoryBO.OSDCSTATUS,
											 OsdcAcctHistoryBO.DEBTCOLLNOTE,
											 AcctFeeBO.FEECODE,
											 AcctTrustBO.INTERESTACCTNUM
											);	
			returnList = searchFactory.getResults();
		 
			int idx = 0;
			DebtCollectionAccountDTO dcaDTO = null;
			// get the fee and trust account descriptions
			for (DebtCollectionAccountDTO acct : returnList) {
				acct.setDaysPastDue(getDaysPastDue(acct.getUserId(), acct.getAcctNum(), courtType, conn));				
				String acctType = acct.getAcctType();
				if (acct.getAcctType().equalsIgnoreCase("F")) {
					SearchDescriptor sAcctFee = new SearchDescriptor(new AcctFeeBO(courtType)
						.where(AcctFeeBO.ACCTNUM, acct.getAcctNum()));
					SearchDescriptor sFeeType = new SearchDescriptor(new FeeTypeBO(courtType)
						.as(FeeTypeBO.DESCR, "acctDescr"));

					BaseSearchDispatcher searchFactory2 = new SearchDispatcher(sFeeType, sAcctFee)
					.setUseConnection(conn)
					.addForeignKey(
						new ForeignKeyDescriptor(FeeTypeBO.FEECODE, AcctFeeBO.FEECODE),
						new ForeignKeyDescriptor(FeeTypeBO.LASTEFFECTDATE, AcctFeeBO.FEEEFFECTDATE))
							.setResultContainer(new DebtCollectionAccountDTO())
							.toString(PRINT_SQL)
							.search(FeeTypeBO.DESCR);
					if (searchFactory2.getResults().size() > 0) {
						dcaDTO = (DebtCollectionAccountDTO) searchFactory2.getResults().get(0);
						acct.setAcctDescr(dcaDTO.getAcctDescr());
						returnList.set(idx, acct);
					}	
					 
				} else if (acctType.equalsIgnoreCase("T")) {
					SearchDescriptor sAcctTrust = new SearchDescriptor(new AcctTrustBO(courtType)
						.where(AcctTrustBO.ACCTNUM, acct.getAcctNum()));
					SearchDescriptor sTrustType = new SearchDescriptor(new TrustTypeBO(courtType)
						.as(TrustTypeBO.DESCR, "acctDescr"));

					BaseSearchDispatcher searchFactory2 = new SearchDispatcher(sAcctTrust, sTrustType)
					.setUseConnection(conn)
					.addForeignKey(
						new ForeignKeyDescriptor(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE))
							.setResultContainer(new DebtCollectionAccountDTO())
							.toString(PRINT_SQL)
							.search(TrustTypeBO.DESCR);
					if (searchFactory2.getResults().size() > 0) {
						dcaDTO = (DebtCollectionAccountDTO) searchFactory2.getResults().get(0);
						acct.setAcctDescr(dcaDTO.getAcctDescr());
						returnList.set(idx, acct);
					}
				 
				}
				idx++;
			}
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Exception in findDebtCaseAccounts: intCaseNumber = ".concat(Integer.toString(intCaseNumber).concat(" -- Court Type:").concat(courtType)));
            sb.append(e.getMessage());
            log.error(sb.toString());
            throw new SystemException(e, sb.toString());
        } finally {
        	conn.close();
        	conn = null;
        }
        return returnList;
    
    }
    
    /**
     * Take a list of account numbers and prepare them for OSDC.
     * 
     * @param List<Integer> accountNumbers
     * @param int userId
     * @param List<String> notes
     * @param String courtType
     * @param boolean useStatic
     * @return int
     * @throws Exception
     */
    public static int processAccountsForOsdc(List<Integer> accountNumbers, int userId, List<String> notes, String courtType) throws Exception {
    	int returnValue = 0;
    	Connection conn = null;
    	String judgmentError = "";
    	
        // Make sure the account numbers and notes are the same size.
        if (accountNumbers.size() != notes.size()) {
        	log.error("Account and note lists do not match.");
        	throw new Exception("Number of accounts and notes in lists do not match.");
        }
		try {
			
			if ("D".equals(courtType))
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB);
			else
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_JUSTICE_DB);
			
			conn.setAutoCommit(false);
			
	    	// Create a judgment if this is a criminal case.  Added 12/10/2018  Selart Piv #162420750
			// Get the int_case_num
    		AccountBO account = AccountXO.getAccountByAcctNum(conn, accountNumbers.get(0), courtType); 
    		int intCaseNum = account.getIntCaseNum();
    		
    		// Get the case category.
    		CaseTypeBO caseTypeBo = new CaseTypeBO(courtType).setUseConnection(conn);
    		KaseBO kaseBo = new KaseBO(courtType).setUseConnection(conn);
    		kaseBo.where(KaseBO.INTCASENUM,intCaseNum).includeTables(caseTypeBo);
    		kaseBo.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);
    		List<KaseBO> kases = kaseBo.search(CaseTypeBO.CATEGORY);
    		
    		if (kases.size() > 0) {
	    		String category = (String) kases.get(0).get(CaseTypeBO.CATEGORY);
	    		
	    		if ("R".equalsIgnoreCase(category)) {
	    			try{
	    				CaseSentenceAccountProcessor.createJudgmentEtc(courtType, intCaseNum, accountNumbers, userId, 0, false, conn);
	    			}
	    			catch(Exception e)
	    			{
	    				 judgmentError = e.getMessage();
	    				 conn.rollback();
	    				 conn.setAutoCommit(false);
	    				 e.printStackTrace();
	    				 log.error(e);
	    			}
	    		}
    		}
    		
			FieldOperationDescriptor max = new FieldOperationDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ, FieldOperationType.MAX, new TypeInteger());
	    	// Process each account.
	    	int count = 0;
	    	intCaseNum = 0;
	    	for (Integer accountNumber : accountNumbers) {
		    	// Get the current status for the account.
		    	String accountStatus = AccountXO.getStateDebtCollStatusByAccount(conn, accountNumber, courtType);
		    	
		    	if ("N".equalsIgnoreCase(accountStatus) || "P".equalsIgnoreCase(accountStatus)) {
		    		// The note has been saved before, just update the osdc history row.
		    		AccountXO.updateOsdcByAccount(conn, accountNumber, "R", notes.get(count), userId, courtType);
		    		returnValue++;
		    	} else {
		    		if ("R".equalsIgnoreCase(accountStatus) || "S".equalsIgnoreCase(accountStatus)) {
		    			log.error("This account has already been prepared or sent to OSDC: ".concat(Integer.toString(accountNumber)));
		    			throw new Exception("Account already processed.");
		    		} else {
		    			AccountXO.insertIntoOsdc(conn, accountNumber, userId, "R", notes.get(count), courtType);		    			
		    			returnValue++;
		    		}
		    	}
	    		
	    		// Get the account information.
	    		account = AccountXO.getAccountByAcctNum(conn, accountNumber, courtType); 
	    		intCaseNum = account.getIntCaseNum();
	    		
	    		// End "FIN" tracking if this is a fine account.
	    		if ("I".equalsIgnoreCase(account.getAcctType())) {
	    			new TrackingBO(courtType).setUseConnection(conn)
	    				.setEndDatetime(Calendar.getInstance().getTime())
	    				.setEndUseridSrl(userId)
	    				.where(new FindDescriptor(TrackingBO.INTCASENUM,intCaseNum),
	    							new FindDescriptor(TrackingBO.TRACKCODE,"FIN"),
	    							new FindDescriptor(TrackingBO.ENDDATETIME).setCustomSearch("IS NULL"))
	    				.toString(PRINT_SQL).update();
	    		}
	    		
		    	/*if (intCaseNum > 0) {
		    		// Insert a case history note.  (Per account or case?)
		    		CaseHistBO caseHistBo = new CaseHistBO(courtType);
		    		caseHistBo.setIntCaseNum(intCaseNum);
		    		caseHistBo.setClerkId(userId);
		    		caseHistBo.setFuncId("HISTNOTE");
		    		caseHistBo.setRemovedFlag("N");
		    		StringBuilder histNote = new StringBuilder("The balance of ");
		    		histNote.append(((DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US)).format(account.getAmtDue().floatValue() - account.getAmtPaid().floatValue() - account.getAmtCredit().floatValue()).replace("$",""));
		    		histNote.append(" for ");
		    		histNote.append(account.getAcctType());
		    		histNote.append(" on this case has been transferred to the Utah Office of State Debt Collection (OSDC) for collection purposes.");
		    		histNote.append(" Any payment made must be made to OSDC.");
		    		caseHistBo.setChText(histNote.toString());
		    		caseHistBo.setCreateDatetime(Calendar.getInstance().getTime());
		    		caseHistBo.setEntryDatetime(caseHistBo.getCreateDatetime());
		    		
		    		caseHistBo.setUseConnection(conn).toString(PRINT_SQL).insert();
		    	}*/
	    		
	    		// Copy the data into the dc_account, dc_acct_dist and dc_acct_trust tables.
		    	//insert into dc_account
		    	AccountXO.inserIntoDcAccount(conn, accountNumber, courtType);
		    	//insert into dc_acct_dist
		    	AccountXO.insertIntoDcAcctDist(conn, accountNumber, courtType);
		    	//insert into dc_acct_trust
	    		if ("T".equalsIgnoreCase(account.getAcctType())) {
	    			AccountXO.insertIntoDcAcctTrust(conn, accountNumber, courtType);
	    		}
	    		
	    		// Adjust account amount down to zero and set status to "DN".
	    		// First get the adjustment sequence.
	    		max = new FieldOperationDescriptor(AcctAdjBO.SEQ, FieldOperationType.MAX, new TypeInteger());
	    		int adjSeq = AcctAdjXO.getNextSequenceNumber(conn, accountNumber, courtType); 
	    		
	    		// Now get all of the Account Distributions.
	    		List<AcctDistBO> acctDistributions = new AcctDistBO(courtType).setUseConnection(conn).where(AcctDistBO.ACCTNUM,accountNumber).toString(PRINT_SQL).search(); 
	    		
	    		// For each distribution > 0, add an adjustment.
	    		AcctAdjBO acctAdjustment;
	    		BigDecimal balance;
	    		for (AcctDistBO acctDist : acctDistributions) {
	    			balance = acctDist.getAmtDue().subtract(acctDist.getAmtPaid().add(acctDist.getAmtCredit()));
	    			if (balance.signum() > 0) {
	    				// Because of triggers on acct_adj table, this will adjust the account to zero.
		    			acctAdjustment = new AcctAdjBO(courtType);
		    			acctAdjustment.setAcctNum(accountNumber);
		    			acctAdjustment.setDistCode(acctDist.getDistCode());
		    			acctAdjustment.setAdjAmt(balance.negate()); // Adjust by a negative amount of balance.
		    			acctAdjustment.setSeq(adjSeq); // Same sequence is used for each distribution code.
		    			acctAdjustment.setReason("Adjusted to zero and sent to State Debt Collection");
		    			acctAdjustment.setUseridSrl(userId);
		    			acctAdjustment.setAdjDatetime(Calendar.getInstance().getTime());
		    			acctAdjustment.setEffectiveDate(Calendar.getInstance().getTime());
		    			acctAdjustment.setUseConnection(conn).toString(PRINT_SQL).insert();
		    			
		    			// Get the amount to set in the acct_dist table.  Added 06/10/2019  Selart Piv #166595957
		    			BigDecimal updateValue = acctDist.getAmtDue().subtract(balance);
		    			
		    			//Added on 12/7/2018
		    			new AcctDistBO(courtType).setUseConnection(conn).where(AcctDistBO.ACCTNUM, accountNumber)
						.where(AcctDistBO.DISTCODE, acctDist.getDistCode())
						.toString(PRINT_SQL)
						.setAmtDue(updateValue).update();
	    			}	    			
	    		}
	    		
	    		// Set the account status to "DN"
	    		AccountXO.updateAccountStatus(conn, accountNumber, "DN", courtType);
	    		
	    		if ("F".equals(account.getAcctType())){
	    			AcctFeeBO vo = new AcctFeeBO(courtType).setUseConnection(conn).where(AcctFeeBO.ACCTNUM,accountNumber).toString(PRINT_SQL).find();
	        		if ("IR".equals(vo.getFeeCode()))
	        			AccountXO.updateAccountFeeCode(conn, accountNumber, "UI", courtType);
	    		}
	    		// Insert the account number into the debt_coll table.
	    		new DebtCollBO(courtType).setUseConnection(conn).setAcctNum(account.getAcctNum()).toString(PRINT_SQL).insert();
	    		// If this is a justice court, check to see if the justice court needs to be added as a creditor to the party_case table.
	    		if ("J".equalsIgnoreCase(courtType)) {
	    			// TODO!!!
//	    			ll_testing_count = 0
//	    			SELECT count(*)
//	    				INTO :ll_testing_count
//	    			FROM judgment_creditor jc
//	    			WHERE int_case_num = :il_int_case_num AND
//	    				detl_seq = 1 ;
//	    				
//	    			ll_testing_count1 = 0
//	    			SELECT count(*)
//	    				INTO :ll_testing_count1
//	    			FROM party_case
//	    			WHERE int_case_num = :il_int_case_num AND
//	    				party_code = 'CRE' ;
//	    					 
//	    			// Only do this part if il_jpartynum > 0.  Selart  05/14/2014  #205103
	    			
	    			//-------------OTHER PART to get justice party id.--------------//
//	    			ls_jcourt_title = gf_get_court_title(gCourtLocation,gCourtType)
//	    			il_jpartynum = 0
//	    			SELECT count(*)
//	    				INTO :il_jpartynum
//	    				FROM party
//	    			WHERE last_name matches :ls_jcourt_title;
//	    		
//	    			IF il_jpartynum <> 0 THEN
//	    				SELECT min(party_num)
//	    					INTO :il_jpartynum
//	    					FROM party
//	    				WHERE last_name matches :ls_jcourt_title;
//	    			ELSE
//	    				INSERT INTO party (party_num, last_name, last_sndx, first_name,
//	    								 first_sndx, address_1, address_2, city,
//	    								 state_code, zip_code, home_phone, ssn, disabled)
//	    				VALUES (0, :ls_jcourt_title,NULL,NULL,NULL,NULL,
//	    					  NULL,NULL,"UT",NULL,NULL,NULL,"N");
//	    				IF SQLCA.SqlCode <> 0 THEN
//	    					MessageBox("ERROR","Insert into party:~r" + SQLCA.SqlErrText)
//	    					close(parent)
//	    					return
//	    				END IF
//		    			SELECT count(*)
//    					INTO :il_jpartynum
//    					FROM party
//	    				WHERE last_name matches :ls_jcourt_title;
//	    				// instead of this --- il_jpartynum=Long(SQLCA.SqlReturnData)
//	    			END IF
//	    			IF il_jpartynum > 0 AND ll_testing_count <> ll_testing_count1 THEN
//	    				SELECT count(*)
//	    					INTO :ll_testing_countj
//	    				FROM party_case
//	    				WHERE int_case_num = :il_int_case_num AND
//	    					party_code = 'CRE' AND // Added AND here.  Selart  05/14/2014  #205103
//	    					 party_num = :il_jpartynum ;
//	    				IF ll_testing_countj < 1 THEN
//	    					INSERT INTO party_case(int_case_num, party_num, party_code, cash_bail_flag)
//	    						VALUES (:il_int_case_num, :il_jpartynum, "CRE", "N");
//	    						f_db_check("During Justice Party Case Insert")
//	    				END IF
//	    			END IF // ll_testing_count <> ll_testing_count1
	    		}
	    		
	    		count++;
	    	} // End for
    		
    		// Update the debt collection flag at the case level.
	    	if(intCaseNum > 0){
	    		new KaseBO(courtType).setUseConnection(conn)
	    			.setDebtCollection("Y")
	    			.where(KaseBO.INTCASENUM,intCaseNum).toString(PRINT_SQL).update(KaseBO.DEBTCOLLECTION); //select insert won't work
	    	}
	    	
	    	conn.commit();
        } catch (Exception e) {
        	conn.rollback();
            StringBuilder sb = new StringBuilder();
            sb.append("Exception in processAccountsForOsdc: first accountNumber = ".concat(Integer.toString(accountNumbers.get(0)).concat(" -- Court Type:").concat(courtType)));
            sb.append(e.getMessage());
            log.error(sb.toString());
            throw new SystemException(e, sb.toString());
        } finally {
        	if (conn != null)
        		conn.close();
        	
        	//A judgment error
        	if (!"".equals(judgmentError))
        		throw new Exception("Judgment Warning: " + judgmentError);
        }
        
        return returnValue;
    }
    
    /**
     * Save an OSDC note for an account.
     * 
     * @param int accountNumber
     * @param int userId
     * @param String note
     * @param String courtType
     * @param boolean useStatic
     * @return int
     * @throws Exception
     */
    public static int saveOsdcNoteByAccount(int accountNumber, int userId, String note, String courtType) throws Exception {int returnValue = 0;
	try {
		
    	// Check to see if we already have an entry that needs to be updated.
		returnValue = AccountXO.getActiveOsdcCount(accountNumber, courtType); 		 
		if (returnValue > 0) {
			// Have an entry, update it.

			OsdcAcctHistoryBO osdcAcctHistoryBO = new OsdcAcctHistoryBO(
					courtType);
			if (note == null || note.trim().length()==0) {
				osdcAcctHistoryBO.includeFields(OsdcAcctHistoryBO.DEBTCOLLNOTE
						.setAsNull());
			}

			else {
				osdcAcctHistoryBO.setDebtCollNote(note);
			}
			osdcAcctHistoryBO
					.where(OsdcAcctHistoryBO.ACCTNUM, accountNumber)
					.where(
							new FindDescriptor(
									OsdcAcctHistoryBO.OSDCSTATUS,
									Exp.IN,
									new StringArrayDescriptor("R", "N")))
					.toString(PRINT_SQL)
					.update();
		} else 	if (note != null  && note.trim().length()>0) {
    		// Don't have an entry, insert one.
    		new OsdcAcctHistoryBO(courtType)
    			.setAcctNum(accountNumber)
    			.setOsdcCreateUserid(userId)
    			.setOsdcStatus("N")
    			.setOsdcCreateDatetime(Calendar.getInstance().getTime())
    			.setDebtCollNote(note)
    			.toString(PRINT_SQL).insert();
    	}
    	
    } catch (Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Exception in processAccountsForOsdc: accountNumber = ".concat(Integer.toString(accountNumber).concat(" -- Court Type:").concat(courtType)));
        sb.append(e.getMessage());
        log.error(sb.toString());
        throw new SystemException(e, sb.toString());
    }
    
    return returnValue;}
    
    /**
	 * calcInterestAccrued
	 * @param userId
	 * @param intCaseNum
	 * @param courtType
	 * @param useStatic
	 * @return double[] - (status, accrued amt)
	 * @throws Exception
	 */
	public static double[] calcInterestAccrued(int userId, int intCaseNum, String courtType) throws Exception {
		
		Connection conn = "D".equalsIgnoreCase(courtType) ? DatabaseConnection
				.getConnection(AccountBO.CORIS_DISTRICT_DB)
				: DatabaseConnection
						.getConnection(AccountBO.CORIS_JUSTICE_DB);
				
		double[] returnArray = {0, 0};
		log.debug("<< Entering  AccountFacade.calcInterestAccrued(int userId, int intCaseNum, String courtType, boolean useStatic)  >>");
		try{
			conn.setAutoCommit(false);
			StoredProcedureDescriptor calcInterestAccrued = new StoredProcedureDescriptor (
					"calc_interest",
					"D".equalsIgnoreCase(courtType)?AccountBO.CORIS_DISTRICT_DB:AccountBO.CORIS_JUSTICE_DB,
					new InputParameters().addParameters(
						new TypeInteger().setValue(userId),
						new TypeInteger().setValue(intCaseNum),
						new TypeString().setValue("Y")
					),
					new OutputContainer().addResultTypes(
						new TypeDouble(),		// status  <- check this column to see if there were results from the stored proc
						new TypeDouble()
					)
				);
			BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(calcInterestAccrued).setUseConnection(conn).toString(PRINT_SQL).executeQuery();
			List<OutputContainer> results = storedProcedureFactory.getResults();
			if(results != null && results.size() > 0){
				returnArray[0] = (Double)results.get(0).getFields().get(0).getValue(); // status of stored proc run 0 is successful
				returnArray[1] = (Double)results.get(0).getFields().get(1).getValue(); // accrued amount
			}
        	return returnArray;
		} catch(Exception e) {
			log.error("Exception in calcInterestAccrued(int userId, int intCaseNum, String courtType) ",e);
			
			conn.rollback();
			log.error(".calcInterestAccrued method Exception: "
					+ e.getMessage());
			throw e;
		} finally {
			conn.setAutoCommit(true);
			conn.close();
			conn = null;

		}
	}
	
	 /**
     * Get the state debt collection status for the given account number.
     * This is NOT in the full stack but is called from the CrimCaseFacade.
     * 
     * @param int accountNumber
     * @return String state debt collection status
     * @throws Exception
     */
	public static String getStateDebtCollStatusByAccount(Connection connection, int accountNumber, String courtType) throws Exception {
		String osdcStatus = null;
		FieldOperationDescriptor max = new FieldOperationDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ, FieldOperationType.MAX, new TypeInteger());
		
		OsdcAcctHistoryBO osdcAcctHistoryBO = new OsdcAcctHistoryBO(courtType);
		
		if (connection != null)
			osdcAcctHistoryBO.setUseConnection(connection);
			
		OsdcAcctHistoryBO osdcAcctHistoryTwo = osdcAcctHistoryBO
		.where(OsdcAcctHistoryBO.ACCTNUM, accountNumber)
		.where(OsdcAcctHistoryBO.OSDCACCTSEQ, Exp.in(new OsdcAcctHistoryBO(courtType).setFieldOperations(max).where(OsdcAcctHistoryBO.ACCTNUM, accountNumber)))
		.setReturnNull(true)
		.find(OsdcAcctHistoryBO.OSDCSTATUS);

		if (osdcAcctHistoryTwo != null) {
			osdcStatus = osdcAcctHistoryTwo.getOsdcStatus();
		}

		return osdcStatus;
	}
	
	
	 /**
     * Get the state debt collection status for the given account number.
     * This is NOT in the full stack but is called from the CrimCaseFacade.
     * 
     * @param int accountNumber
     * @return String state debt collection status
     * @throws Exception
     */
	public static Map<Integer, String>  getMapOfAccNumAndLatestOsdcStatusByAccNums(Connection connection, List<Integer> accountNumbers,
			String courtType) throws Exception {
		 Map<Integer, String> accNumAndOsdcStatusMap = new HashMap<Integer, String>();
		FieldOperationDescriptor max = new FieldOperationDescriptor(
				OsdcAcctHistoryBO.OSDCACCTSEQ, FieldOperationType.MAX,
				new TypeInteger());
		
		OsdcAcctHistoryBO osdcAcctHistoryBO = new OsdcAcctHistoryBO(courtType);
		
		if (connection != null)
			osdcAcctHistoryBO.setUseConnection(connection);
			
		List<OsdcAcctHistoryBO> osdcAcctHistoryVOList = osdcAcctHistoryBO
				.setAllowDuplicateQuestionMarks(true)
				.where(new FindDescriptor(OsdcAcctHistoryBO.ACCTNUM,Exp.IN,new IntegerArrayDescriptor(accountNumbers)))
				.addWhereDescriptors(
						new WhereSelectDescriptor(new TableAndFieldDescriptor(
								OsdcAcctHistoryBO.OSDCACCTSEQ),
								new OsdcAcctHistoryBO(courtType).groupBy(OsdcAcctHistoryBO.ACCTNUM)
										.setFieldOperations(max).where(new FindDescriptor(OsdcAcctHistoryBO.ACCTNUM,Exp.IN,new IntegerArrayDescriptor(accountNumbers)))))
				.toString(PRINT_SQL).search(OsdcAcctHistoryBO.ACCTNUM,OsdcAcctHistoryBO.OSDCSTATUS);
		
		if(osdcAcctHistoryVOList!=null  && osdcAcctHistoryVOList.size()>0)
		{
			
			for(OsdcAcctHistoryBO osdcAcctHistoryTwo:osdcAcctHistoryVOList)
			{
				accNumAndOsdcStatusMap.put(osdcAcctHistoryTwo.getAcctNum(), osdcAcctHistoryTwo.getOsdcStatus());
			}
			
		}

		 

		return accNumAndOsdcStatusMap;
	}
	
	 /**
     * Get the state debt collection status for the given account number.
     * This is NOT in the full stack but is called from the CrimCaseFacade.
     * 
     * @param int accountNumber
     * @return String state debt collection status
     * @throws Exception
     */
	public static String getStateDebtCollStatusByAccount(int accountNumber,
			String courtType) throws Exception {

		return getStateDebtCollStatusByAccount(null, accountNumber, courtType);
	}
	
	/**
     * Take an account number and prepare it for OSDC.
     * 
     * @param int accountNumber
     * @param int userId
     * @param String note
     * @return int
     * @throws Exception
     */
	public static void insertIntoOsdc(Connection connection, int accountNumber, int userId,String status, String note, String courtType) throws Exception {

		try {
			new OsdcAcctHistoryBO(courtType).setUseConnection(connection)
					.setAcctNum(accountNumber)
					.setOsdcCreateUserid(userId)
					.setOsdcStatus(status)
					.setDebtCollNote(note)
					.setOsdcCreateDatetime(Calendar.getInstance().getTime())
					.toString(PRINT_SQL).insert();

		} catch (Exception e) {
			log.error("Exception in processAccountForOsdc: Account Number = "
					.concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
	
	/**
     * Get a count of all active osdc entries for this account number (should be one or zero).
     * 
     * @param int accountNumber
     * @return int
     * @throws Exception
     */
    public static int getActiveOsdcCount(int accountNumber, String courtType) throws Exception {
    	int returnValue = 0;
    	try {
    		// First make an entry in the osdc_acct_history table.
    		FieldOperationDescriptor count = new FieldOperationDescriptor(OsdcAcctHistoryBO.ACCTNUM, FieldOperationType.COUNT, new TypeInteger());
	    	// Check to see if we already have an entry that needs to be updated.
			returnValue = ((Integer)new OsdcAcctHistoryBO(courtType)
											.setFieldOperations(count)
											.where(OsdcAcctHistoryBO.ACCTNUM,accountNumber)
											.where(new FindDescriptor(OsdcAcctHistoryBO.OSDCSTATUS).setCustomSearch("IN ('R','N')"))
											.toString(PRINT_SQL).find().get(count)).intValue();
    	} catch (Exception e) {
			log.error("Exception in getActiveOsdcCount: Account Number = ".concat(Integer.toString(accountNumber)),e);
			throw e;
		} 
    	return returnValue;
    }
    
    /**
     * Save an OSDC note and status for an account that is being processed.
     * 
     * @param int accountNumber
     * @param String status
     * @param String note
     * @param int userId
     * @return int
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static void updateOsdcByAccount(Connection connection, int accountNumber, String status, String note, int userId, String courtType) throws Exception {
		try {
			new OsdcAcctHistoryBO(courtType).setUseConnection(connection)
				.setOsdcStatus(status)
				.setDebtCollNote(note)
				.setOsdcCreateUserid(userId)
				.setOsdcCreateDatetime(Calendar.getInstance().getTime())
				.where(OsdcAcctHistoryBO.ACCTNUM,accountNumber)
				.where(OsdcAcctHistoryBO.OSDCSTATUS, Exp.IN, new StringArrayDescriptor("R", "N", "P"))
				.toString(PRINT_SQL)
				.update();

		} catch (Exception e) {
			log.error("Exception in updateOsdcByAccount: Account Number = "
					.concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
	
	/**
     * Insert account information into the dc_account table prior to OSDC processing.
     * This is only called from the AccountFacade during OSDC processing.
     * 
     * @param int accountNumber
     * @return int
     * @throws Exception
     */
	public static void inserIntoDcAccount(Connection connection, int accountNumber, String courtType) throws Exception {
		try {

			TransactionDescriptor insertDcAcctDescriptor = new InsertSelectDescriptor().setInsertIntoTable(
					new DcAccountBO(courtType)).setSelectFromTable(
					new AccountBO(courtType).where(AccountBO.ACCTNUM,accountNumber));

			BaseTransactionDispatcher insertDcAcct = new TransactionDispatcher(
					insertDcAcctDescriptor).setUseConnection(connection).toString(PRINT_SQL).execute(
					AccountBO.ACCTNUM, AccountBO.ACCTTYPE,
					AccountBO.INTCASENUM, AccountBO.PARTYNUM,
					AccountBO.PARTYCODE, AccountBO.TIMEPAYNUM,
					AccountBO.TIMEPAYSEQ, AccountBO.ORIGAMTDUE,
					AccountBO.AMTDUE, AccountBO.AMTPAID, AccountBO.AMTCREDIT,
					AccountBO.STATUS, AccountBO.DUEDATE, AccountBO.USERIDSRL,
					AccountBO.INTEFFECTDATE);

			if (!insertDcAcct.successful()) {
				log.error("Failed to insert into dcAccount table");
				throw new Exception("Failed to insert into dcAccount table");
			}

		} catch (Exception e) {
			log.error("Exception in inserIntoDcAccount: Account Number = ".concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
	
	 /**
     * Insert account information into the dc_acct_dist table prior to OSDC processing.
     * This is only called from the AccountFacade during OSDC processing.
     * 
     * @param int accountNumber
     * @return int
     * @throws Exception
     */
	public static void insertIntoDcAcctDist(Connection connection, int accountNumber, String courtType)
			throws Exception {
		try {
			TransactionDescriptor insertDcDistAcctDescriptor = new InsertSelectDescriptor().setInsertIntoTable(
					new DcAcctDistBO(courtType)).setSelectFromTable(
					new AcctDistBO(courtType).where(AcctDistBO.ACCTNUM,accountNumber));

			BaseTransactionDispatcher insertDcAcctDist = new TransactionDispatcher(
					insertDcDistAcctDescriptor).setUseConnection(connection).toString(PRINT_SQL).execute(
					AcctDistBO.ACCTNUM, AcctDistBO.DISTCODE, AcctDistBO.AMTDUE,
					AcctDistBO.AMTPAID, AcctDistBO.AMTCREDIT);

			if (!insertDcAcctDist.successful()) {
				log.error("Failed to insert into dc_acct_dist table");
				throw new Exception("Failed to insert into dc_acct_dist table");
			}
		} catch (Exception e) {
			log.error("Exception in insertIntoDcAcctDist: Account Number = "
					.concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
    
    /**
     * Insert account information into the dc_acct_trust table prior to OSDC processing.
     * This is only called from the AccountFacade during OSDC processing.
     * 
     * @param int accountNumber
     * @return int
     * @throws Exception
     */
	public static void insertIntoDcAcctTrust(Connection connection, int accountNumber, String courtType)
			throws Exception {
		try {
			TransactionDescriptor insertDcTrustAcctDescriptor = new InsertSelectDescriptor().setInsertIntoTable(
					new DcAcctTrustBO(courtType)).setSelectFromTable(
					new AcctTrustBO(courtType).where(AcctTrustBO.ACCTNUM,accountNumber));

			BaseTransactionDispatcher insertDcTrustAcct = new TransactionDispatcher(
					insertDcTrustAcctDescriptor).setUseConnection(connection).toString(PRINT_SQL).execute(
					AcctTrustBO.ACCTNUM, AcctTrustBO.TRUSTCODE,
					AcctTrustBO.AMTPAIDOUT, AcctTrustBO.PAYEEPARTYNUM,
					AcctTrustBO.PAYORPARTYNUM, AcctTrustBO.PRIORITY,
					AcctTrustBO.CHECKSTUBDESCR, AcctTrustBO.JOINTSEVNUM,
					AcctTrustBO.INTERESTACCTNUM);

			if (!insertDcTrustAcct.successful()) {
				log.error("Failed to insert into dcTrustAccount table");
				throw new Exception(
						"Failed to insert into dcTrustAccount table");
			}
		} catch (Exception e) {
			log.error("Exception in insertIntoDcAcctTrust: Account Number = ".concat(Integer.toString(accountNumber)), e);
			throw e;
		}
	}
	
	 /**
     * Update an account status.
     * This is currently only called from the AccountFacade during OSDC processing.
     * May need to add to BO and facade separately if needed.
     * 
     * @param int accountNumber
     * @param String accountStatus
     * @return int
     * @throws Exception
     */
    public static void updateAccountStatus(Connection connection, int accountNumber, String accountStatus, String courtType) throws Exception {
    	try {
    		new AccountBO(courtType).setUseConnection(connection).setStatus(accountStatus).where(AccountBO.ACCTNUM,accountNumber).toString(PRINT_SQL).update(AccountBO.STATUS);
    	} catch (Exception e) {
			log.error("Exception in getActiveOsdcCount: Account Number = ".concat(Integer.toString(accountNumber)),e);
			throw e;
		}
    }
    

    /**
     * @param accountNumber
     * @param feeCode
     * @param courtType
     * @throws Exception
     */
    public static void updateAccountFeeCode(Connection connection, int accountNumber, String feeCode, String courtType) throws Exception {
    	try {
    		new AcctFeeBO(courtType).setUseConnection(connection).setFeeCode(feeCode).where(AcctFeeBO.ACCTNUM,accountNumber).toString(PRINT_SQL).update(AcctFeeBO.FEECODE);
    	} catch (Exception e) {
			log.error("Exception in updateAccountFeeCode: Account Number = ".concat(Integer.toString(accountNumber)),e);
			throw e;
		}
    }
    
    
     /**
      * Gets a count of acctNums that have amounts due
      * @param courtType
      * @param intCaseNum
      * @param acctFeeCode
      * @return count(int)
      * @throws Exception
      */
//    public static int getAcctFeeCountByCourtAndIntCaseNum(String courtType, int intCaseNum) throws Exception {
//    	int returnVal = 0;
//    	FieldOperationDescriptor count = new FieldOperationDescriptor(AccountBO.ACCTNUM,  FieldOperationType.COUNT, new TypeInteger());
//    	SearchDescriptor s1 = new SearchDescriptor (new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum)
//    			.setFieldOperations(count).addWhereDescriptors(
//    					new WhereCustomDescriptor("(t1.amt_due > t1.amt_paid + t1.amt_credit)")));
//    	SearchDescriptor s2 = new SearchDescriptor (new AcctFeeBO(courtType)
//    		.where(AcctFeeBO.FEECODE, Exp.IN, new StringArrayDescriptor("PN", "PS")));
//    	new SearchDispatcher(s1, s2)
//    	.addForeignKey(AccountBO.ACCTNUM, AcctFeeBO.ACCTNUM)
//    	.toString(PRINT_SQL).search(AccountBO.NO_FIELDS);
//    	//System.out.println(s1.get(count));
//    	
//    	return (Integer) s1.get(count);
//    }
    
    /**
     * Gets a count of acctNums that have bail amounts > 0	
     * @param courtType
     * @param intCaseNum
     * @return
     * @throws Exception
     */   
    public static int getBailCountByIntCaseNum(String courtType, int intCaseNum) throws Exception {
        FieldOperationDescriptor count = new FieldOperationDescriptor(AccountBO.ACCTNUM, FieldOperationType.COUNT, new TypeInteger());
        SearchDescriptor s1 = new SearchDescriptor (new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum)
        		.setFieldOperations(count));
        SearchDescriptor s2 = new SearchDescriptor (
    		new AcctBailBO(courtType)
    		.where(AcctBailBO.BAILAMT, Exp.GREATER_THAN, 0)
    		.where(AcctBailBO.DISPDATE, Exp.IS_NULL)
    		.addWhereDescriptors(
    			new WhereCustomDescriptor("(t2.refund_amt + t2.forfeit_amt < t2.bail_amt)"))
    		);
        new SearchDispatcher(s1, s2)
        .addForeignKey(AccountBO.ACCTNUM, AcctBailBO.ACCTNUM)
        .toString(PRINT_SQL).search(AccountBO.NO_FIELDS);
//        System.out.println(s1.get(count));
    	return (Integer) s1.get(count);
    }
    
    /**
     * Gets a count of acctNums that have bond amounts > 0
     * @param courtType
     * @param intCaseNum
     * @return
     * @throws Exception
     */
    public static int getBondCountByIntCaseNum(String courtType, int intCaseNum) throws Exception {
        FieldOperationDescriptor count = new FieldOperationDescriptor(AccountBO.ACCTNUM, FieldOperationType.COUNT, new TypeInteger());
        SearchDescriptor s1 = new SearchDescriptor (new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum)
        		.setFieldOperations(count));
        SearchDescriptor s2 = new SearchDescriptor (
    		new AcctBondBO(courtType)
    		.where(AcctBondBO.BONDAMT, Exp.GREATER_THAN, 0)
    		.where(AcctBondBO.DISPDATE, Exp.IS_NULL)
    		.addWhereDescriptors(
    			new WhereCustomDescriptor("(t2.exon_refund_amt + t2.forfeit_amt < t2.bond_amt)"))
    		);
        new SearchDispatcher(s1, s2)
        .addForeignKey(AccountBO.ACCTNUM, AcctBondBO.ACCTNUM)
        .toString(PRINT_SQL).search(AccountBO.NO_FIELDS);
//        System.out.println(s1.get(count));
    	return (Integer) s1.get(count);
    }
    
    /**
     * gets a count of acctNums that have a trust check waiting to be cut
     * @param courtType
     * @param intCaseNum
     * @return
     * @throws Exception
     */
    public static int getTrustCountByIntCaseNum(String courtType, int intCaseNum) throws Exception {
    	FieldOperationDescriptor count = new FieldOperationDescriptor (AccountBO.ACCTNUM, FieldOperationType.COUNT, new TypeInteger());
    	SearchDescriptor s1 = new SearchDescriptor (new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum)
    			.setFieldOperations(count));
    	SearchDescriptor s2 = new SearchDescriptor (
    			new CheckBatchBO(courtType)
    			.where(CheckBatchBO.CONFIRMDATETIME, Exp.IS_NULL)
    			.where(CheckBatchBO.CANCELDATETIME, Exp.IS_NULL));
    	SearchDescriptor s3 = new SearchDescriptor(new CheckBatchDetlBO(courtType));
    	new SearchDispatcher(s1, s2, s3)
    		.addForeignKey(AccountBO.ACCTNUM, CheckBatchDetlBO.ACCTNUM)
    		.addForeignKey(CheckBatchDetlBO.BATCHNUM, CheckBatchBO.BATCHNUM)
    		.addForeignKey(CheckBatchDetlBO.LOCNCODE, CheckBatchBO.LOCNCODE)
    		.addForeignKey(CheckBatchDetlBO.COURTTYPE, CheckBatchDetlBO.COURTTYPE)
    		.toString(PRINT_SQL).search(AccountBO.NO_FIELDS);
//    	System.out.println(s1.get(count));
    	return (Integer) s1.get(count);
    }
    
    /**
     * Gets a count of acctNums that 
     * @param courtType
     * @param intCaseNum
     * @return
     * @throws Exception
     */
    public static int getJSTrustCountByIntCaseNum(String courtType, int intCaseNum) throws Exception {
    	FieldOperationDescriptor count = new FieldOperationDescriptor(AccountBO.ACCTNUM, FieldOperationType.COUNT, new TypeInteger());
    	SearchDescriptor s1 = new SearchDescriptor (new AccountBO(courtType).where(AccountBO.INTCASENUM, intCaseNum).setFieldOperations(count));
    	SearchDescriptor s2 = new SearchDescriptor(new AcctTrustBO(courtType).where(AcctTrustBO.JOINTSEVNUM, Exp.IS_NOT_NULL));
    	new SearchDispatcher(s1, s2)
    		.addForeignKey(AccountBO.ACCTNUM, AcctTrustBO.ACCTNUM)
    		.toString(PRINT_SQL).search(AccountBO.NO_FIELDS);
//    	System.out.println(s1.get(count));
    	return (Integer) s1.get(count);
    }
    
    public static KaseBO getKase(String courtType, int intCaseNum) throws Exception {
    	return new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum).find(KaseBO.LINKEDCASEID, KaseBO.LINKEDDEFID);
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		try {
			DatabaseConnection.setUseJdbc();
//			AccountXO.getAcctFeeCountByCourtAndIntCaseNum("D", 11292486);
//			AccountXO.getBailCountByIntCaseNum("D", 10649548);
//			AccountXO.getBondCountByIntCaseNum("D", 1225769);
//			AccountXO.getTrustCountByIntCaseNum("D", 9856423);
//			AccountXO.getJSTrustCountByIntCaseNum("D", 1669342);
//			AccountXO.getAccountsByIntCaseNum(12363556, "D");
//			AccountXO.getAccountByAcctNum(54555552, "J");
//			AccountXO.getAccountByAcctNumAcctType(124555, "TEST", "D");
//			AccountXO.getAccountsOnTimePayByIntCaseNum(12457888, "D");
//			AccountXO.findDebtCaseAccounts(38018, "D");
//			List<Integer> accountNumbers = new ArrayList<Integer>();
//			accountNumbers.add(17083619);
//			accountNumbers.add(17083603);
//			List<String> notes = new ArrayList<String>();
//			notes.add("testing notes 1");
//			notes.add("testing notes 2");
//			AccountXO.processAccountsForOsdc(accountNumbers, 101, notes, "D");
//			AccountXO.getStateDebtCollStatusByAccount(11111, "D");
//			String courtType="D";
//			new OsdcAcctHistoryBO(courtType)
//			.setOsdcStatus("R")
//			.setDebtCollNote("testing Notes")
//			.setOsdcCreateUserid(10100)
//			.setOsdcCreateDatetime(Calendar.getInstance().getTime())
//			.where(new FindDescriptor(OsdcAcctHistoryBO.ACCTNUM,38271),
//						new FindDescriptor(OsdcAcctHistoryBO.OSDCSTATUS).setCustomSearch("IN ('R', 'S', 'N', 'P')"))
//			.toString(PRINT_SQL).update();
			
//			new TrackingBO(courtType)
//			.setEndDatetime(Calendar.getInstance().getTime())
//			.setEndUseridSrl(10001)
//			.where(new FindDescriptor(TrackingBO.INTCASENUM,52255),
//						new FindDescriptor(TrackingBO.TRACKCODE,"FIN"),
//						new FindDescriptor(TrackingBO.ENDDATETIME).setCustomSearch("IS NULL"))
//			.toString(PRINT_SQL).update();
			
//			CaseHistBO caseHistBo = new CaseHistBO(courtType);
//    		caseHistBo.setIntCaseNum(54122);
//    		caseHistBo.setClerkId(1001);
//    		caseHistBo.setFuncId("HISTNOTE");
//    		caseHistBo.setRemovedFlag("N");
//    		StringBuilder histNote = new StringBuilder("The balance of ");
//    		histNote.append("testing");
//    		histNote.append(" for ");
//    		histNote.append("accounType");
//    		histNote.append(" on this case has been transferred to the Utah Office of State Debt Collection (OSDC) for collection purposes.");
//    		histNote.append(" Any payment made must be made to OSDC.");
//    		caseHistBo.setChText(histNote.toString());
//    		caseHistBo.setCreateDatetime(Calendar.getInstance().getTime());
//    		caseHistBo.setEntryDatetime(caseHistBo.getCreateDatetime());
//    		
//    		caseHistBo.toString(PRINT_SQL).insert();
			
//			TransactionDescriptor insertDcTrustAcctDescriptor = new InsertSelectDescriptor(
//					DcAcctTrustBO.ACCTNUM,DcAcctTrustBO.TRUSTCODE,DcAcctTrustBO.AMTPAIDOUT,DcAcctTrustBO.PAYEEPARTYNUM,
//					DcAcctTrustBO.PAYORPARTYNUM,DcAcctTrustBO.PRIORITY,DcAcctTrustBO.CHECKSTUBDESCR,DcAcctTrustBO.JOINTSEVNUM,DcAcctTrustBO.INTERESTACCTNUM)
//				.setInsertIntoTable(new DcAcctTrustBO(courtType))
//				.setSelectFromTable(new AcctTrustBO(courtType)
//				.where(AcctTrustBO.ACCTNUM, 12455));
//	
//			TransactionFactory insertDcTrustAcct = new TransactionFactory(insertDcTrustAcctDescriptor)
//									.toString(true)
//									.execute(AcctTrustBO.ACCTNUM,AcctTrustBO.TRUSTCODE,AcctTrustBO.AMTPAIDOUT,AcctTrustBO.PAYEEPARTYNUM, AcctTrustBO.PAYORPARTYNUM,
//		    								AcctTrustBO.PRIORITY,AcctTrustBO.CHECKSTUBDESCR,AcctTrustBO.JOINTSEVNUM,AcctTrustBO.INTERESTACCTNUM);
	
			
//			new AcctAdjBO(courtType).setAcctNum(1000)
//			.setDistCode("code")
//			.setAdjAmt(new BigDecimal(20.20)) // Adjust by a negative amount of balance.
//			.setSeq(2) // Same sequence is used for each distribution code.
//			.setReason("Adjusted to zero and sent to State Debt Collection")
//			.setUseridSrl(1011)
//			.setEffectiveDate(Calendar.getInstance().getTime())
//			.toString(PRINT_SQL).insert();
			
			
//			AccountXO.saveOsdcNoteByAccount(38271, 202, "Testing Notes", "D");
//			new OsdcAcctHistoryBO(courtType)
//			.setDebtCollNote("test")
//			.where(new FindDescriptor(OsdcAcctHistoryBO.ACCTNUM, 145521),
//					   new FindDescriptor(OsdcAcctHistoryBO.OSDCSTATUS).setCustomSearch("IN ('R','S','N')"))
//			.toString(PRINT_SQL).update();
			
//			AccountXO.calcInterestAccrued(1001, 7153347, "D");

//			TransactionDescriptor insertDcAcctDescriptor = new InsertSelectDescriptor(
//					DcAccountBO.ACCTNUM,DcAccountBO.ACCTTYPE,DcAccountBO.INTCASENUM,DcAccountBO.PARTYNUM,DcAccountBO.PARTYCODE,DcAccountBO.TIMEPAYNUM,DcAccountBO.TIMEPAYSEQ,
//					DcAccountBO.ORIGAMTDUE,DcAccountBO.AMTDUE,DcAccountBO.AMTPAID,DcAccountBO.AMTCREDIT,DcAccountBO.STATUS,DcAccountBO.DUEDATE,DcAccountBO.USERIDSRL,DcAccountBO.INTEFFECTDATE)
//		.setInsertIntoTable(new DcAccountBO(courtType))
//		.setSelectFromTable(new AccountBO(courtType)
//		.where(AccountBO.ACCTNUM, 7153347));
//
//		TransactionFactory insertDcAcct = new TransactionFactory(insertDcAcctDescriptor).toString(true)
//							.execute(AccountBO.ACCTNUM,AccountBO.ACCTTYPE,AccountBO.INTCASENUM,AccountBO.PARTYNUM,AccountBO.PARTYCODE,AccountBO.TIMEPAYNUM,AccountBO.TIMEPAYSEQ,
//									AccountBO.ORIGAMTDUE,AccountBO.AMTDUE,AccountBO.AMTPAID,AccountBO.AMTCREDIT,AccountBO.STATUS,AccountBO.DUEDATE,AccountBO.USERIDSRL,AccountBO.INTEFFECTDATE);
			
			getStateDebtCollStatusByAccount(null, 14990259, "D");
			List<Integer> list = new ArrayList<Integer>();
			list.add(17592292);
			list.add(17592291);
			System.out.println(hasDefendantAsPayor("D", list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create entries in account and acct_dist tables based on Case Sentence judgments
	 * @param intCaseNum
	 * @param amt
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static List<Integer> createAccounts(Connection conn, int intCaseNum, List<CaseMeValueBO> cmvList, int userId, String courtType) throws Exception {
		List<Integer> accounts = new ArrayList<Integer>();
		
		//Trust accounts are there already, no need to add. Fine accounts and Fee accounts are added if under AP&P supervision
		//Otherwise, they are also created already.
		
		//Fee Accounts
		List<FeeDTO> list = getSentenceFeeAmount(conn, courtType, cmvList); 
		for (FeeDTO feeDTO : list){
			List<OutputContainer> feeAcounts = insertAcctFee(conn, userId, intCaseNum, feeDTO.getAmount(), feeDTO.getFeeCode(), courtType);
			int status =  ((TypeInteger) feeAcounts.get(0).getFields().get(0)).getValue();
			
			if (status == 0){
				int acctNum =  ((TypeInteger) feeAcounts.get(0).getFields().get(1)).getValue();
				accounts.add(acctNum);
			} else {
				throw new Exception("Error calling ins_fee_acct");
			}
		}
		
		//Fine accounts
		String accountType = "I";
		BigDecimal amtDue = getSentenceFineAmount(cmvList);
		if (amtDue != null){
			
			PartyCaseBO dParty = new PartyCaseBO(courtType).setUseConnection(conn)
			.where(PartyCaseBO.PARTYCODE, "DEF")
			.where(PartyCaseBO.INTCASENUM, intCaseNum).find();
			
			Date dueDate = getSentenceFineDueDate(cmvList);
			if (dueDate == null)
				dueDate = new Date();
			
			AccountBO acctBO = (AccountBO) new AccountBO(courtType).setUseConnection(conn)
				.setIntCaseNum(intCaseNum)
				.setAcctType(accountType)
				.setPartyNum(dParty.getPartyNum())
				.setPartyCode("DEF")
				.setStatus("FN")
				.setDueDate(dueDate)
				.setIntEffectDate(new Date())
				.setAmtCredit(new BigDecimal(0)) 
				.setAmtDue(amtDue) //passed in 
				.setAmtPaid(new BigDecimal(0)) 
				.setOrigAmtDue(amtDue)
				.setUseridSrl(userId)
				.toString(PRINT_SQL)
				.insert();
			
			int acctNum = acctBO.getAcctNum();
			accounts.add(acctNum);
			
			List<OutputContainer> acctDistList = calculateAccountDistribution(conn, userId, intCaseNum, cmvList, accountType);
			
			String distCd = null;
			BigDecimal distAmt = null;
			
			for(OutputContainer oc : acctDistList){
				for(int i=1; i<oc.getFields().size(); i++){
					if(i%2==0){
						distAmt = ((TypeBigDecimal)oc.getFields().get(i)).getValue();
						distCd = ((TypeString) oc.getFields().get(i-1)).getValue();
						
						if (distCd != null){
							new AcctDistBO(courtType).setUseConnection(conn)
								.setAcctNum(acctNum).setDistCode(distCd)
								.setAmtCredit(new BigDecimal(0)) 
								.setAmtDue(distAmt) //
								.setAmtPaid(new BigDecimal(0)) 
								.setDistCode(distCd)
								.toString(PRINT_SQL)
								.insert();
						}
					}
				}
			}
		}
		
		return accounts;
	}

	/**
	 * @param userId
	 * @param intCaseNum
	 * @param cmvList
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	private static List<OutputContainer> calculateAccountDistribution(Connection conn, int userId, int intCaseNum, List<CaseMeValueBO> cmvList, String courtType) throws Exception {
		String partyCode = "DEF"; //civil case? // TODO!!! Check this for civil cases
		PartyCaseBO pc = new PartyCaseBO(courtType).setUseConnection(conn)
			.where(PartyCaseBO.INTCASENUM, intCaseNum).where(PartyCaseBO.PARTYCODE, partyCode).find();
		
		List<OutputContainer> results = new ArrayList<OutputContainer>();
		boolean surchargeIncluded = isSurchargeIncluded(cmvList);
		List<Integer> chargeNums = getChargeNumbers(cmvList);
		
		//calc_acct_dist(<userId>, <intCaseNum>, <partyNum>, <partyCode>, <CHARGENO>, <amount>, 'I'): I for FINETOTL, 'A' FOR TFINE
		for(Integer chgNo:chargeNums){
			BigDecimal totalFine = getTotalFine(cmvList,surchargeIncluded,chgNo);
			StoredProcedureDescriptor calAcctDist = new StoredProcedureDescriptor(
					"calc_acct_dist", 
					null, //Conn is passed in
					new InputParameters().addParameters(
							new TypeInteger().setValue(userId), 
							new TypeInteger().setValue(intCaseNum), 
							new TypeInteger().setValue(pc.getPartyNum()), 
							new TypeString().setValue(partyCode),
							new TypeInteger().setValue(chgNo), 
							new TypeBigDecimal().setValue(totalFine), 
							new TypeString().setValue(surchargeIncluded ? "I" : "A")
					),
					new OutputContainer()
						.addResultTypes(new TypeInteger(), new TypeString(), new TypeBigDecimal(), new TypeString(), new TypeBigDecimal())
			).setUseConnection(conn);

			BaseStoredProcedureDispatcher storedProcedureDispatcher = new StoredProcedureDispatcher(calAcctDist).toString(PRINT_SQL).executeQuery();
			
			results.addAll(storedProcedureDispatcher.getResults());
		}

		return results ;
	}
	
	
	private static List<OutputContainer> insertAcctFee(Connection conn, int userId, int intCaseNum, BigDecimal feeAmt, String feeCode, String courtType) throws Exception {
		String partyCode = "DEF"; 
		List<PartyCaseBO>  pcs = PartyCaseXO.findCasePartyNum(conn, intCaseNum, partyCode, courtType);
		List<OutputContainer> results = new ArrayList<OutputContainer>();
		
		StoredProcedureDescriptor ins = new StoredProcedureDescriptor(
				"ins_acct_fee", 
				null, 
				new InputParameters().addParameters(
						new TypeInteger().setValue(userId), 
						new TypeInteger().setValue(intCaseNum), 
						new TypeInteger().setValue(pcs.get(0).getPartyNum()), 
						new TypeString().setValue(partyCode),
						new TypeString().setValue(feeCode), 
						new TypeDate().setValue(null), //last_effect_date
						new TypeBigDecimal().setValue(feeAmt), 
						new TypeDate().setValue(new Date()) //due_date
				),
				new OutputContainer()
					.addResultTypes(new TypeInteger(), new TypeInteger())).setUseConnection(conn);

		BaseStoredProcedureDispatcher storedProcedureDispatcher = new StoredProcedureDispatcher(ins).toString(RUN).executeQuery();
		
		results.addAll(storedProcedureDispatcher.getResults());
		

		return results;
	}

	/**
	 * @param cmvList
	 * @return
	 */
	private static List<Integer> getChargeNumbers(List<CaseMeValueBO> cmvList) {
		List<Integer> charges = new ArrayList<Integer>();
		try{
			for(CaseMeValueBO cmv:cmvList){
				if("chargeno".equalsIgnoreCase(cmv.getMeFieldId())){
					charges.add(Integer.parseInt(cmv.getMeFieldValue()));
				}
			}
		}catch(NumberFormatException e){
			//do nothing
		}
		return charges;
	}

	/**
	 * @param cmvList
	 * @return
	 */
	private static boolean isSurchargeIncluded(List<CaseMeValueBO> cmvList) {
		for(CaseMeValueBO cmv:cmvList){
			if("surcharg".equalsIgnoreCase(cmv.getMeFieldId()) && "including".equalsIgnoreCase(cmv.getMeFieldValue())){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param cmvList
	 * @param surchargeIncluded
	 * @param chgNo
	 * @return
	 */
	private static BigDecimal getTotalFine(List<CaseMeValueBO> cmvList, boolean surchargeIncluded, Integer chgNo) {
		try {
			for (CaseMeValueBO cmv : cmvList) {
				if (surchargeIncluded && "finetotl".equalsIgnoreCase(cmv.getMeFieldId()) && cmv.getMeFieldSeq()==chgNo) {
					return new BigDecimal(Double.parseDouble(cmv.getMeFieldValue().replace("$", "")));
				} else if ("tfine".equalsIgnoreCase(cmv.getMeFieldId()) && cmv.getMeFieldSeq()==chgNo) {
					return new BigDecimal(Double.parseDouble(cmv.getMeFieldValue().replace("$", "")));
				}
			}
		} catch (NumberFormatException e) {
				//do nothing
		}
		
		return null;
	}
	
	

	/**
	 * @param acctDist
	 * @param reason
	 * @param userId
	 * @param courtType
	 * @param conn
	 * @throws Exception
	 */
	public static void zeroOutAccounts(Connection conn, int acctNum, String reason, int userId, String courtType) throws Exception {
		
		List<AcctDistBO> dists = new AcctDistBO(courtType).setUseConnection(conn).where(AcctDistBO.ACCTNUM, acctNum)
		 .addWhereDescriptors(new WhereCustomDescriptor("(amt_due > (amt_paid + amt_credit))")).search();
		
		FieldOperationDescriptor max = new FieldOperationDescriptor(AcctAdjBO.SEQ, FieldOperationType.MAX, new TypeInteger()).setAlias("seq");
		
		int seq = (Integer) new AcctAdjBO(courtType).setUseConnection(conn)
				.setFieldOperations(max)
				.where(AcctAdjBO.ACCTNUM, acctNum).find().get("seq") + 1;
		
		for (AcctDistBO acctDist:dists){
	
			BigDecimal adjAmt = acctDist.getAmtDue().subtract(acctDist.getAmtPaid()).subtract(acctDist.getAmtCredit());
			//adj_type is null
			new AcctAdjBO(courtType).setUseConnection(conn)
					.setAcctNum(acctDist.getAcctNum()).setDistCode(acctDist.getDistCode())
					.setAdjAmt(adjAmt.negate())
					.setAdjDatetime(Calendar.getInstance().getTime())
					.setUseridSrl(userId)
					.setEffectiveDate(new Date())
					.setReason(reason).setSeq(seq).toString(PRINT_SQL)
					.insert();
			
			// Get the amount to set in the acct_dist table.  Added 06/10/2019  Selart Piv #166595957
			BigDecimal updateValue = acctDist.getAmtDue().subtract(adjAmt);
			
			new AcctDistBO(courtType).setUseConnection(conn).where(AcctDistBO.ACCTNUM, acctNum)
				.where(AcctDistBO.DISTCODE, acctDist.getDistCode())
				.setAmtDue(updateValue).toString(PRINT_SQL).update();
		}

	}
	
	/**
	 * @param cmvList
	 * @return
	 */
	public static BigDecimal getSentenceFineAmount(List<CaseMeValueBO> cmvList) {
		for(CaseMeValueBO cmv : cmvList){
			if("sentfine".equalsIgnoreCase(cmv.getMeScreenId()) && "tfinedue".equalsIgnoreCase(cmv.getMeFieldId())){
				String val = cmv.getMeFieldValue().replace("$", "");
				return new BigDecimal(val);
			}
		}
		return null;
	}
	
	/**
	 * @param cmvList
	 * @return
	 * @throws ParseException 
	 */
	public static Date getSentenceFineDueDate(List<CaseMeValueBO> cmvList) throws ParseException {
		for(CaseMeValueBO cmv : cmvList){
			if("sentfine".equalsIgnoreCase(cmv.getMeScreenId()) && "finedoby".equalsIgnoreCase(cmv.getMeFieldId())){
				String val = cmv.getMeFieldValue();
				return new SimpleDateFormat("MM/dd/yyyy").parse(val);
			}
		}
		return null;
	}
	
	/**
	 * @param cmvList
	 * @return
	 * @throws Exception 
	 */
	public static List<FeeDTO> getSentenceFeeAmount(Connection conn, String courtType, List<CaseMeValueBO> cmvList) throws Exception {
		
		List<FeeDTO> list = new ArrayList<FeeDTO>();
		// Removed the outer loop  In CORIS we only ever set FEE1AMT and FEE1.  No reason to try and find others.  Selart
		BigDecimal amt = null;
		String feeDescr = null;
		String feeCode = null;
		for(CaseMeValueBO cmv : cmvList){
			if("sentfine".equalsIgnoreCase(cmv.getMeScreenId()) && ("FEE1AMT").equalsIgnoreCase(cmv.getMeFieldId())){
				try{
					amt = new BigDecimal(Double.parseDouble(cmv.getMeFieldValue().replace("$", "")));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
			if("sentfine".equalsIgnoreCase(cmv.getMeScreenId()) && ("FEE1").equalsIgnoreCase(cmv.getMeFieldId())){
				feeDescr = cmv.getMeFieldValue();
				FeeTypeBO typeBO = new FeeTypeBO(courtType).setUseConnection(conn)
					.where(FeeTypeBO.DESCR, feeDescr)
					.where(new FindDescriptor(FeeTypeBO.LASTEFFECTDATE).setCustomSearch("> today")).find();
				feeCode = typeBO.getFeeCode();
			}
		}
		if (amt != null && feeCode != null)
			list.add(new FeeDTO(feeCode, amt));
		return list;
	}
    
    /**
     * Returns the number of days past due the case is..
     * 
     * @param intCaseNumber 
     * @param courtType
     * @param conn
     * @return int
     * @throws Exception
     */
    public static int getDaysPastDue(int userId, int accountNumber,String courtType, Connection conn) throws Exception {
    
    	int daysPastDue = 0;
    	try{
    	StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
    			"calc_overdue_acct",
    			AccountBO.CORIS_DISTRICT_DB,
    			new InputParameters().addParameters(
    				new TypeInteger().setValue(userId),
    				new TypeInteger().setValue(accountNumber),
    				new TypeString().setValue("3000-01-01")
    				 
    			),
    			new OutputContainer().addResultTypes(
    				new TypeInteger(),new TypeString(),new TypeBigDecimal(),new TypeInteger(),new TypeString()		 
    				 
    			)
    		);
        
    	BaseStoredProcedureDispatcher storedProcedureFactory = new StoredProcedureDispatcher(storedProcedure).setUseConnection(conn).toString(PRINT_SQL).executeQuery();
    	
    	
		List<OutputContainer> results = storedProcedureFactory.getResults();   
		if(results!=null && results.size()>0)
		{
			for (OutputContainer row : results) {
				for (int i=0; i< row.getFields().size();i++) {						 
					if(i==3 && row.getFields().get(i).getValue()!=null)
					{
						daysPastDue =(Integer) row.getFields().get(i).getValue();
						break;
					}
				 
				}
				 
			}
	       
			
		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		throw e;
    	}

        return daysPastDue;
    }
    
    public static boolean hasDefendantAsPayor(String courtType, List<Integer> accountNums) throws Exception{
    	boolean ret = false;
    	List<String> partyCodes = new ArrayList<String>();
    	partyCodes.add("DEF");
    	partyCodes.add("AKA");
    	partyCodes.add("FKA");
    	partyCodes.add("NKA");
    	partyCodes.add("TN");
    	List<AccountBO> list = new AccountBO(courtType)
    		.where(AccountBO.PARTYCODE, Exp.IN, partyCodes)
    		.where(AccountBO.ACCTNUM, Exp.IN, accountNums)
    		.toString(PRINT_SQL)
    		.search();
    	if (list != null && list.size() > 0)
    		return true;
    	
    	return ret;
    }
}
