package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.accountjudgmentxref.AccountJudgmentXrefBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.judgment.JudgmentBO;
import gov.utcourts.coriscommon.dataaccess.judgmentcreditor.JudgmentCreditorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdebtor.JudgmentDebtorBO;
import gov.utcourts.coriscommon.dataaccess.judgmentdetl.JudgmentDetlBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.prosecagency.ProsecAgencyBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.coriscommon.dto.JudgmentDTO;
import gov.utcourts.coriscommon.xo.AccountXO;
import gov.utcourts.coriscommon.xo.CommonPartyXO;
import gov.utcourts.coriscommon.xo.KaseXO;
import gov.utcourts.coriscommon.xo.OsdcCancelXO;
import gov.utcourts.coriscommon.xo.PartyCaseXO;
import gov.utcourts.coriscommon.xo.PartyXO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author hongbo.tang
 *
 */
public class CaseSentenceAccountProcessor {
	
	private static Logger logger = Logger.getLogger(CaseSentenceAccountProcessor.class);

	private static List<String> INTEREST_TRUST_CODES = new ArrayList<String>(Arrays.asList("IA", "IN", "IV", "IG", "IW", "II", "IJ", "IR"));
	public static void main(String[] args) throws SQLException {
		DatabaseConnection.setUseJdbc();
		
		try {
			
			//IR to UI
			//createJudgmentEtc("D", 8084566, null, 86203, false, true);
			//List<Integer> accountNumbers = new ArrayList<Integer>();
			//accountNumbers.add(58934);
			//accountNumbers.add(59018);
			//List<String> notes = new ArrayList<String>();
			//notes.add("Note 1");
			//notes.add("Note 2");
			//AccountXO.processAccountsForOsdc(accountNumbers, 86201, notes, "D");
			//OsdcCancelXO.recallOsdcIntCaseNum("D", 86201, 8084566);
			
			//Every account type
			List<Integer> accountNumbers = new ArrayList<Integer>();
			accountNumbers.add(59139); //T
			accountNumbers.add(59144); //T-IR
			accountNumbers.add(59160); //F
			accountNumbers.add(59161); //I
			createJudgmentEtc("D", 8085763, accountNumbers, 86203, 0, true);
			List<String> notes = new ArrayList<String>();
			notes.add("Note 1");
			notes.add("Note 2");
			notes.add("Note 3");
			notes.add("Note 4");
			AccountXO.processAccountsForOsdc(accountNumbers, 86203, notes, "D");
			//OsdcCancelXO.recallOsdcIntCaseNum("D", 86203, 8085763);
			
			
			//Justice F/I
			//createJudgmentEtc("J", 8079885, null, 1, true, true);
			//List<Integer> accountNumbers = new ArrayList<Integer>();
			//accountNumbers.add(58751);
			//accountNumbers.add(58764);
			//List<String> notes = new ArrayList<String>();
			//notes.add("Note 1");
			//notes.add("Note 2");
			//AccountXO.processAccountsForOsdc(accountNumbers, 86201, notes, "J");
			//OsdcCancelXO.recallOsdcAccountNum("J", 86201, 58751);
			
			//Connection conn = DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB);
			//System.out.println(isOpenEndedTrust(conn, 39382, "D"));
			//conn.close();
			
		} catch (Exception e) {
			logger.error("Error in main.", e);
		} finally {
			
		}

	}
	

	/**
	 * This method will be called by PowerBuider to create judgments. 
	 * @param courtType: courtType
	 * @param intCaseNum: Int case number
	 * @param acctNumbers: You can pass null and the program will grab all accounts (T, F, I)
	 * @param userId: user serial ID
	 * @param partyId: This will have the party ID if the fine/fee accounts are payable to an agency (like AP&P)
	 * @param isPrison: set to true if the DEF will be sent to prison
	 * @throws Exception
	 */
	public static void createJudgmentEtc(String courtType, int intCaseNum, List<Integer> acctNumbers, int userId, int partyId, boolean isPrison) throws Exception {
		Connection conn = null;
		try {
			if ("D".equals(courtType))
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_DISTRICT_DB);
			else
				conn = DatabaseConnection.getConnection(AccountBO.CORIS_JUSTICE_DB);
			
			CaseSentenceAccountProcessor.createJudgmentEtc(courtType, intCaseNum, acctNumbers, userId, partyId, isPrison, conn);
			conn.commit();
		} catch (Exception e){
			if (conn != null)
				conn.rollback();
			
			StringBuffer sb = new StringBuffer("Error in createJudgmentEtc.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			sb.append("  User ID = ");
			sb.append(userId);
			logger.error(sb.toString(), e);
			throw new Exception(e.getMessage());
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * This method will be called by PowerBuider to create judgments. 
	 * @param courtType: courtType
	 * @param intCaseNum: Int case number
	 * @param acctNumbers: You can pass null and the program will grab all accounts (T, F, I)
	 * @param userId: user serial ID
	 * @param partyId: This will have the party ID if the fine/fee accounts are payable to an agency (like AP&P)
	 * @param isPrison: set to true if the DEF will be sent to prison
	 * @param conn: passed connection.
	 * @throws Exception
	 */
	public static void createJudgmentEtc(String courtType, int intCaseNum, List<Integer> acctNumbers, int userId, int partyId, boolean isPrison, Connection conn) throws Exception {
		try {
			
			String acctType = "";
			//Get defendant
			PartyCaseBO dParty = new PartyCaseBO(courtType).setUseConnection(conn)
				.where(PartyCaseBO.PARTYCODE, "DEF")
				.where(PartyCaseBO.INTCASENUM, intCaseNum).find();
			
			int dPartyNum = dParty.getPartyNum();
			
			//If acctNumbers are not passed in, we will get all accounts from intCaseNum
			//If acctNumbers are passed in, we will only deal with them
			List<AccountBO> accountList = new ArrayList<AccountBO>();
			if(acctNumbers == null || acctNumbers.size() == 0){
				accountList = getAccountByIntCaseNum(conn, courtType, intCaseNum, dPartyNum, false);
			} else {
				for (Integer acctNum:acctNumbers){
					accountList.add(AccountXO.getAccountByAcctNum(conn, acctNum, courtType));
				}
			}
			
			//Build all account hashmap
			HashMap<Integer, AccountBO> accountMap = new HashMap<Integer, AccountBO>();
			for (AccountBO acctBO:accountList){
				accountMap.put(acctBO.getAcctNum(), acctBO);
			}
			
			//Validate and build interest hashmap.  Changed isApp to partyId.  Selart  11/27/2018  Piv #162257292
			HashMap<Integer, AccountBO> interestAccountMap = validate(conn, intCaseNum, accountList, accountMap, courtType, partyId, isPrison);
			
			String descr = "";
			String partyCode = "PLA";
			int pPartyNum = 0;
			int relatedInterestAccount = 0;
			
			//Get judgment amount and code
			JudgmentDTO jdmtDTO = getJudgmentAmtAndCode(conn, courtType, intCaseNum, accountList);
			
			if (jdmtDTO.getJdmtAmt().signum() <= 0)
				throw new Exception("Judgment amount must be greater than zero.");
			
			FieldOperationDescriptor max = new FieldOperationDescriptor(JudgmentBO.JDMTSEQ, FieldOperationType.MAX, new TypeInteger());
		
			int jdmtSeq = (Integer) new JudgmentBO(courtType).setUseConnection(conn)
				.setFieldOperations(max)
				.where(JudgmentBO.INTCASENUM,intCaseNum).find().get(max) + 1;
		
			if (conn.getAutoCommit())
				conn.setAutoCommit(false);
			
			//Insert one judgment for all accounts
			new JudgmentBO(courtType).setUseConnection(conn).setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq)
				.setFilingDatetime(Calendar.getInstance().getTime()).setJdmtDatetime(Calendar.getInstance().getTime())
				.setJdmtAmt(jdmtDTO.getJdmtAmt()).setJdmtNum("1").setJdmtCode(jdmtDTO.getJdmtCode())
				.setLastUpdDate(Calendar.getInstance().getTime())
				.setUseridSrl(userId).setDescr("Criminal Sentence").insert();
			
			int detlSeq = 1;
			for (AccountBO accountBO:accountList){
				relatedInterestAccount = 0; //Reset - important
				
				BigDecimal balance = accountBO.getAmtDue().subtract(accountBO.getAmtPaid()).subtract(accountBO.getAmtCredit());
				acctType = accountBO.getAcctType();
			
				if ("I".equals(acctType) || "F".equals(acctType)){
					//Skip account with zero balance
					if (balance.signum() <= 0 )
						continue;
					
					if ("I".equals(acctType))
						descr = "Fine";
					else {
						descr = "Fees";
					}
					
					//State of Utah
					//if ("I".equals(acctType) || ("F".equals(acctType) && FEE_CODES.contains(feeCode))){
					//PartyCaseBO partyCaseBO = addStateOfUtahAsCreditor(conn, intCaseNum,  courtType);
					//pPartyNum = partyCaseBO.getPartyNum();
				//	partyCode = partyCaseBO.getPartyCode();
					//}
					
					//May need to add justice court as creditor
					if ("J".equals(courtType)){
						addJusticeCourtAsCreditor( conn,  intCaseNum,  jdmtSeq, detlSeq, courtType);
					}
					
				} else if ("T".equals(acctType)) {
					AcctTrustBO acctTrustBO = new AcctTrustBO(courtType).setUseConnection(conn)
						.where(AcctTrustBO.ACCTNUM, accountBO.getAcctNum()).find();
					
					//If this is an interest account, skip it because it will be pulled in
					//together with main account later
					if (interestAccountMap.get(accountBO.getAcctNum()) != null)
						continue;
					else {
						BigDecimal interestAcctBalance = new BigDecimal(0.0);
						relatedInterestAccount = acctTrustBO.getInterestAcctNum();
						if (relatedInterestAccount > 0){
							AccountBO interestAcctBO = interestAccountMap.get(relatedInterestAccount);
							interestAcctBalance =  interestAcctBO.getAmtDue().subtract(interestAcctBO.getAmtPaid()).subtract(interestAcctBO.getAmtCredit());
						}
						//If both main and associated interest accounts have zero balance, skip them
						if (balance.signum() <= 0 && interestAcctBalance.signum() <= 0)
							continue;
					}
					
					pPartyNum = acctTrustBO.getPayeePartyNum();
					
					TrustTypeBO trustTypeBO = new TrustTypeBO(courtType).setUseConnection(conn)
						.where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode()).find();
					descr = trustTypeBO.getDescr();
					partyCode = PartyCaseXO.findCasePartyCode(conn, intCaseNum, pPartyNum, courtType);
					if (partyCode == null || "".equals(partyCode.trim())){
						partyCode = "PYE";
						PartyCaseXO.insertPartyCase(conn, intCaseNum, pPartyNum, partyCode, courtType, "N");
					}
					
					// Add the payee on the trust account as a creditor.  Selart  03/25/2019  Piv #164413404
					if (!"PLA".equalsIgnoreCase(partyCode)) {
						// Only add if not a PLA.  The plantiff will get added later.
						new JudgmentCreditorBO(courtType).setUseConnection(conn)
						.setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq)
						.setDetlSeq(detlSeq).setPartyNum(pPartyNum)
						.setPartyCode(partyCode).insert();
					}
				}
				
				// Add OSDC as a creditor for all cases sent to OSDC.  Added back in 05/28/2019  Selart Piv #166195750
				if (partyId == 0) {
					// Check for party ID.  If 0 we are sending to OSDC.  Selart  02/07/2019
					addOsdcAsCreditor(conn, intCaseNum, jdmtSeq, detlSeq, courtType);
				}
								
				//May need to add APP as creditor
				//if (partyId > 0){
					// Changed to add a creditor instead of looking for AP&P as a creditor.  Selart  11/27/2018  Piv #162257292
					//Commented partyId addCreditor method, uncomment if required in future.
				//	addCreditor(conn, intCaseNum, jdmtSeq, detlSeq, courtType, partyId);
			//	}
				
				
				List<PartyCaseBO> plaPartyCaseList=new PartyCaseBO(courtType).setUseConnection(conn)
				.where(PartyCaseBO.PARTYCODE, "PLA")
				.where(PartyCaseBO.INTCASENUM, intCaseNum).search();
				
				if (plaPartyCaseList==null || plaPartyCaseList.size()==0)
				{
					 plaPartyCaseList=new ArrayList<PartyCaseBO>();
					 plaPartyCaseList.add(addStateOfUtahAsCreditor(conn, intCaseNum,  courtType));
						
				}
				
				
				insertJdmtDetail(conn, jdmtSeq, detlSeq, accountBO, plaPartyCaseList, dPartyNum, descr, courtType);

				
				/*for(PartyCaseBO partyCaseBO:plaPartyCaseList)
				{
					if(partyCaseBO.getPartyNum()>0)
					{
						insertJdmtDetail(conn, jdmtSeq, detlSeq, accountBO, plaPartyCaseList, dPartyNum, descr, courtType);
						detlSeq++;
					}
				}*/
			 
			
				
				
				
				//If it has interest account. The purpose is to put interest account with main account.
				//Say if main has detl_seq = N, the interest will have N+1
				if (relatedInterestAccount > 0){
					detlSeq++;
					AccountBO interestAccountBO = interestAccountMap.get(relatedInterestAccount);
					
					AcctTrustBO acctTrustBO = new AcctTrustBO(courtType).setUseConnection(conn)
					.where(AcctTrustBO.ACCTNUM, interestAccountBO.getAcctNum()).find();
					
					TrustTypeBO trustTypeBO = new TrustTypeBO(courtType).setUseConnection(conn)
					.where(TrustTypeBO.TRUSTCODE, acctTrustBO.getTrustCode()).find();
					
					if (isPrison){
						addOsdcAsCreditor(conn, intCaseNum, jdmtSeq, detlSeq, courtType);
					}
					
					//May need to add APP as creditor
				//	if (partyId > 0){
						// Changed to add a creditor instead of looking for AP&P as a creditor.  Selart  11/27/2018  Piv #162257292
						//Commented partyId addCreditor method, uncomment if required in future.
					//	addCreditor(conn, intCaseNum, jdmtSeq, detlSeq, courtType, partyId);
				//	}
					
					insertJdmtDetail(conn, jdmtSeq, detlSeq, interestAccountBO, plaPartyCaseList, dPartyNum, trustTypeBO.getDescr(), courtType);
					/*for(PartyCaseBO partyCaseBO:plaPartyCaseList)
					{
						if(partyCaseBO.getPartyNum()>0)
						{						
							insertJdmtDetail(conn, jdmtSeq, detlSeq, interestAccountBO, partyCaseBO.getPartyCode(), partyCaseBO.getPartyNum(), dPartyNum, trustTypeBO.getDescr(), courtType);
							//detlSeq++;
						}
					}*/
					
				}
				
				detlSeq++;
			}
			
			// Changed isApp to partyId.  Selart  11/27/2018  Piv #162257292
			if (partyId > 0){
				for (AccountBO acctBO : accountList)
					// Change note.  Selart 11/28/2018  Piv #162257292
					AccountXO.zeroOutAccounts(conn, acctBO.getAcctNum(), "set balance to zero for pay to agency", userId, courtType);
			}
			
		} catch (Exception e){
			
			StringBuffer sb = new StringBuffer("Error in createJudgmentEtc.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			sb.append("  User ID = ");
			sb.append(userId);
			logger.error(sb.toString(), e);
			throw new Exception(e.getMessage());
		}
	}

	
	
	/**
	 * @param conn
	 * @param courtType
	 * @param intCaseNum
	 * @param limitToTrust
	 * @return
	 * @throws Exception
	 */
	public static List<AccountBO> getAccountByIntCaseNum(Connection conn, String courtType, int intCaseNum, int defendantPartyNum, boolean limitToTrust) throws Exception{
		
		String acctType = "";
		List<AccountBO> acctNumbers = new ArrayList<AccountBO>();
		
		//Better to sort it
		List<AccountBO> accountList = new AccountBO(courtType).setUseConnection(conn)
			.where(AccountBO.INTCASENUM, intCaseNum).orderBy(AccountBO.ACCTNUM, DirectionType.ASC).search();
		
		for (AccountBO accountBO:accountList){
			acctType = accountBO.getAcctType();
			if ("I".equals(acctType) && !limitToTrust){
				acctNumbers.add(accountBO);
			} else if ("T".equals(acctType)){
				if (!isOpenEndedTrust(conn, accountBO.getAcctNum(), courtType))
					acctNumbers.add(accountBO);
			} else if ("F".equals(acctType) && !limitToTrust){
				if (accountBO.getPartyNum() == defendantPartyNum ){
					acctNumbers.add(accountBO);
				}
			}
		}	
		return acctNumbers;
	}
	
	/**
	 * @param conn
	 * @param intCaseNum
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static PartyCaseBO addStateOfUtahAsCreditor(Connection conn, int intCaseNum, String courtType) throws Exception{
		PartyCaseBO vo = new PartyCaseBO(courtType);
		
		try {
			//May need to add State of Utah as Creditor
			int pPartyNum = PartyCaseXO.findCasePartyNumForStateOfUtah(conn, intCaseNum, courtType);
			String partyCode = PartyCaseXO.findCasePartyCodeForStateOfUtah(conn, intCaseNum, pPartyNum, courtType);
			if (pPartyNum == 0){
				partyCode = "CRE";
				ProsecAgencyBO proscAgencyBO = new ProsecAgencyBO(courtType).where(ProsecAgencyBO.PROSECAGENCY, "UT").find();
				pPartyNum = proscAgencyBO.getPartyNum();
				PartyCaseBO partyCaseBO = PartyCaseXO.findCaseParty(conn, intCaseNum, pPartyNum, partyCode, courtType);
				if (partyCaseBO == null){
					PartyCaseXO.insertPartyCase(conn, intCaseNum, pPartyNum, partyCode, courtType, "N");
				}
			}
			
			vo.setPartyNum(pPartyNum);
			vo.setPartyCode(partyCode);
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("Error in addStateOfUtahAsCreditor.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			logger.error(sb.toString(), e);
			
			throw e;
		}
		
		return vo;
		
	}
	
	/**
	 * @param conn
	 * @param intCaseNum
	 * @param jdmtSeq
	 * @param detlSeq
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static PartyCaseBO addJusticeCourtAsCreditor(Connection conn, int intCaseNum, int jdmtSeq, int detlSeq, String courtType) throws Exception{
		PartyCaseBO vo = new PartyCaseBO(courtType);
		
		try {
			KaseBO kaseBO = new KaseBO(courtType).setUseConnection(conn).where(KaseBO.INTCASENUM, intCaseNum).find();
			
			ProfileBO profileBO = new ProfileBO(courtType).setUseConnection(conn)
				.where(ProfileBO.LOCNCODE, kaseBO.getLocnCode()).where(ProfileBO.COURTTYPE, courtType).find();
			
			int pPartyNum = PartyXO.findPartyNumByCourtTitle(conn, courtType, profileBO.getCourtTitle());
			if (pPartyNum == 0){
				pPartyNum = PartyXO.insertPartyForCourt(conn, courtType, profileBO.getCourtTitle());
			}
			
			PartyCaseBO partycaseBO = PartyCaseXO.findCaseParty(conn, intCaseNum, pPartyNum, null, courtType);
			
			String partyCode =  "";
			if (partycaseBO == null){
				partyCode =  "CRE";
				PartyCaseXO.insertPartyCase(conn, intCaseNum, pPartyNum, "CRE", courtType, "N");
			} else {
				partyCode =  partycaseBO.getPartyCode();
			}
			
			// Check to see if the party code is "PLA" if so it will be added later.  Selart  06/12/2019  Piv #166196062 (re-added see 163630564)
			if (!"PLA".equalsIgnoreCase(partyCode)) {
				new JudgmentCreditorBO(courtType).setUseConnection(conn)
					.setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq)
					.setDetlSeq(detlSeq).setPartyNum(pPartyNum)
					.setPartyCode(partyCode).insert();
			}			
			vo.setPartyNum(pPartyNum);
			vo.setPartyCode(partyCode);
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("Error in addJusticeCourtAsCreditor.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			sb.append("  Judgment sequence = ");
			sb.append(jdmtSeq);
			logger.error(sb.toString(), e);
			
			throw e;
		}
		return vo;
	}
	
	/**
	 * @param conn
	 * @param intCaseNum
	 * @param jdmtSeq
	 * @param detlSeq
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static PartyCaseBO addOsdcAsCreditor(Connection conn, int intCaseNum, int jdmtSeq, int detlSeq, String courtType) throws Exception{
		PartyCaseBO vo = new PartyCaseBO(courtType);
		try {
			int osdcPartyNum = PartyCaseXO.findCasePartyByLastName(conn, intCaseNum, "STATE DEBT COLLECT", courtType);
			
			//WE need to get it from Common Party
			if (osdcPartyNum == 0){
				KaseBO kaseBO = new KaseBO(courtType).setUseConnection(conn).where(KaseBO.INTCASENUM, intCaseNum).find();
				osdcPartyNum = CommonPartyXO.findCasePartyByLastName(conn, kaseBO.getLocnCode(), "STATE DEBT COLLECT", courtType);
				
				if (osdcPartyNum > 0)
					PartyCaseXO.insertPartyCase(conn, intCaseNum, osdcPartyNum, "CRE", courtType, "N");
				else {
					StringBuffer sb = new StringBuffer("Osdc party num cannot be found in common party table!  Court Type = ");
					sb.append(courtType);
					sb.append("  Internal case #");
					sb.append(intCaseNum);
					sb.append("  Judgment sequence = ");
					sb.append(jdmtSeq);
					sb.append("  Detail sequence = ");
					sb.append(detlSeq);
					logger.error(sb.toString());
					
					throw new Exception("Osdc party num cannot be found in common party table!");
				}
			} 
			
			//JudgmentCreditorBO judgmentCreditorBO = new JudgmentCreditorBO(courtType).setUseConnection(conn)
			//	.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
			//	.where(JudgmentCreditorBO.JDMTSEQ, jdmtSeq)
			//	.where(JudgmentCreditorBO.DETLSEQ, detlSeq)
			//	.where(JudgmentCreditorBO.PARTYNUM, osdcPartyNum)
			//	.where(JudgmentCreditorBO.PARTYCODE, "CRE").find();
			
			//if (judgmentCreditorBO == null)
			new JudgmentCreditorBO(courtType).setUseConnection(conn)
				.setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq)
				.setDetlSeq(detlSeq).setPartyNum(osdcPartyNum)
				.setPartyCode("CRE").insert();
			
			vo.setPartyNum(osdcPartyNum);
			vo.setPartyCode("CRE");
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("Error in addOsdcAsCreditor.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			sb.append("  Judgment sequence = ");
			sb.append(jdmtSeq);
			sb.append("  Detail sequence = ");
			sb.append(detlSeq);
			logger.error(sb.toString(), e);
			
			throw e;
		}
		
		return vo;
	}
	
	/**
	 * @param conn
	 * @param intCaseNum
	 * @param jdmtSeq
	 * @param detlSeq
	 * @param courtType
	 * @param partyId
	 * @return
	 * @throws Exception
	 */
	public static PartyCaseBO addCreditor(Connection conn, int intCaseNum, int jdmtSeq, int detlSeq, String courtType, int partyId) throws Exception{
		PartyCaseBO vo = new PartyCaseBO(courtType);
	
		try {
			// Changed to use party ID.  Selart  11/28/2018  Piv #162257292
			int partyNum = 0;
			PartyCaseBO tmpVo = PartyCaseXO.findCaseParty(conn, intCaseNum, partyId, "CRE", courtType);
			if (tmpVo != null) {
				partyNum = tmpVo.getPartyNum();
			}
			
			//WE need to get it from Common Party // Changed to use party ID.  Selart  11/28/2018  Piv #162257292
			if (partyNum == 0){
//				KaseBO kaseBO = new KaseBO(courtType).setUseConnection(conn).where(KaseBO.INTCASENUM, intCaseNum).find();
//				appPartyNum = CommonPartyXO.findCasePartyByLastName(conn, kaseBO.getLocnCode(), "ADULT P", courtType);
				
//				if (appPartyNum > 0)
					PartyCaseXO.insertPartyCase(conn, intCaseNum, partyId, "CRE", courtType, "N");
//				else {
//					StringBuffer sb = new StringBuffer("AP&P party num cannot be found in common party table!  Court Type = ");
//					sb.append(courtType);
//					sb.append("  Internal case #");
//					sb.append(intCaseNum);
//					sb.append("  Judgment sequence = ");
//					sb.append(jdmtSeq);
//					sb.append("  Detail sequence = ");
//					sb.append(detlSeq);
//					logger.error(sb.toString());
//					
//					throw new Exception("AP&P party num cannot be found in common party table!");
//				}
			} 
			
			//JudgmentCreditorBO judgmentCreditorBO = new JudgmentCreditorBO(courtType).setUseConnection(conn)
			//	.where(JudgmentCreditorBO.INTCASENUM, intCaseNum)
			//	.where(JudgmentCreditorBO.JDMTSEQ, jdmtSeq)
			//	.where(JudgmentCreditorBO.DETLSEQ, detlSeq)
			//	.where(JudgmentCreditorBO.PARTYNUM, appPartyNum)
			//	.where(JudgmentCreditorBO.PARTYCODE, "CRE").find();
			
			//if (judgmentCreditorBO == null)
			new JudgmentCreditorBO(courtType).setUseConnection(conn)
				.setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq)
				.setDetlSeq(detlSeq).setPartyNum(partyId)
				.setPartyCode("CRE").insert();
			
			vo.setPartyNum(partyId);
			vo.setPartyCode("CRE");
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("Error in addAPPAsCreditor.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal case #");
			sb.append(intCaseNum);
			sb.append("  Judgment sequence = ");
			sb.append(jdmtSeq);
			sb.append("  Detail sequence = ");
			sb.append(detlSeq);
			logger.error(sb.toString(), e);
			
			throw e;
		}
		
		return vo;
	}
	
	/**
	 * @param conn
	 * @param courtType
	 * @param intCaseNum
	 * @param accountVO
	 * @return
	 * @throws Exception
	 */
	public static JudgmentDTO getJudgmentAmtAndCode(Connection conn, String courtType, int intCaseNum, List<AccountBO> accountVOs) throws Exception{
		JudgmentDTO dto = new JudgmentDTO();
		
		BigDecimal jdmtAmt = new BigDecimal(0.0);
		
		for (AccountBO accountBO:accountVOs){
			jdmtAmt = jdmtAmt.add(accountBO.getAmtDue().subtract(accountBO.getAmtPaid()).subtract(accountBO.getAmtCredit()));
		}
	
		dto.setJdmtAmt(jdmtAmt);
		dto.setJdmtCode("CS");
		
		return dto;

	}
	
	/**
	 * This method validate the inputs. It will automatically add associated interest account if the main account is passed in
	 * @param conn
	 * @param intCaseNum
	 * @param accountList
	 * @param accountMap
	 * @param courtType
	 * @param partyId -- Changed 11/28/2018  Selart  Piv #162257292
	 * @param isPrison
	 * @return Interest account as a map
	 * @throws Exception
	 */
	public static HashMap<Integer, AccountBO> validate(Connection conn, int intCaseNum, List<AccountBO> accountList, HashMap<Integer, AccountBO> accountMap, String courtType, int partyId, boolean isPrison) throws Exception{
		
		String category = KaseXO.getCaseTypeCategory(conn, courtType, intCaseNum);
		if (!"R".equals(category)){ //not criminal Criminal
			throw new Exception ("Only criminal case is allowed!");
		}
		
		if (caseLevelJudementExist(conn, intCaseNum, courtType)){
			throw new Exception ( "A case level judgment already exists. Please review and modify judgments as needed.");
		}
		
		if (partyId > 0 && isPrison) {
			throw new Exception ("Incorrect isPrison and partyId combination!");
		}

		HashMap<Integer, AccountBO> interestAccountMap = new HashMap<Integer, AccountBO>();
		List<AccountBO> addedAccounts = new ArrayList<AccountBO>();
		StringBuilder judgementExistAccNums=new StringBuilder();
		int accJudgementCount=0;
		for (AccountBO acctBO:accountList){
			
			if (acctBO.getIntCaseNum() != intCaseNum){
				throw new Exception ("Account " + acctBO.getAcctNum() + " does not belong to intCaseNum " + intCaseNum + "!");
			}
			
			AccountJudgmentXrefBO foundXref = new AccountJudgmentXrefBO(courtType).setUseConnection(conn)
				.where(AccountJudgmentXrefBO.ACCTNUM, acctBO.getAcctNum())
				.where(AccountJudgmentXrefBO.INTCASENUM, intCaseNum).setReturnNull(true).find();
			
			
			
			if (foundXref != null)
			{
				accJudgementCount=accJudgementCount+1;
				judgementExistAccNums.append(acctBO.getAcctNum()+",");
				continue;
			}
		
			if(judgementExistAccNums.toString().length()>0)
			{
			    if(accJudgementCount<accountList.size())
			    {
			    	throw new Exception("Judgment exists, but some accounts may be missing from the judgment.  Please modify or add judgment as appropriate." );
			    }
			    else
			    {
				judgementExistAccNums.replace(judgementExistAccNums.lastIndexOf(","), judgementExistAccNums.lastIndexOf(",")+1, "");
				throw new Exception("Account(s) (" + judgementExistAccNums + ") already has judgement!" );
			    }
			}	
			
			if ("T".equals(acctBO.getAcctType())){
				
				AcctTrustBO acctTrustBO = new AcctTrustBO(courtType).setUseConnection(conn)
					.where(AcctTrustBO.ACCTNUM, acctBO.getAcctNum()).find();
				
				if (INTEREST_TRUST_CODES.contains(acctTrustBO.getTrustCode())){ //interest account
					acctTrustBO = new AcctTrustBO(courtType).setUseConnection(conn)
					.where(AcctTrustBO.INTERESTACCTNUM, acctBO.getAcctNum()).find();
			
					if (accountMap.get(acctTrustBO.getAcctNum()) == null){ 
						throw new Exception("Interest trust account (" +  acctBO.getAcctNum() + ") is provided without its associated main trust account (" + acctTrustBO.getAcctNum() + ")!");
					}
				} else if (acctTrustBO.getInterestAcctNum() > 0){ //must be a main trust account
					//If associated interest account is not in the list, we will add it automatically
					if (accountMap.get(acctTrustBO.getInterestAcctNum()) == null){
						AccountBO relatedInterestBO = new AccountBO(courtType).setUseConnection(conn).where(AccountBO.ACCTNUM, acctTrustBO.getInterestAcctNum()).find();
						accountMap.put(acctTrustBO.getInterestAcctNum(), relatedInterestBO);
						addedAccounts.add(relatedInterestBO);
					} 
					interestAccountMap.put(acctTrustBO.getInterestAcctNum(), accountMap.get(acctTrustBO.getInterestAcctNum()));
					
				}
					
			} 
		}
		if(judgementExistAccNums.toString().length()>0)
		{
		    if(accJudgementCount<accountList.size())
		    {
		    	throw new Exception("Judgment exists, but some accounts may be missing from the judgment.  Please modify or add judgment as appropriate." );
		    }
		    else
		    {
			judgementExistAccNums.replace(judgementExistAccNums.lastIndexOf(","), judgementExistAccNums.lastIndexOf(",")+1, "");
			throw new Exception("Account(s) (" + judgementExistAccNums + ") already has judgement!" );
		    }
		}
		//AccountList also needs to be changed because we will use it in zeroing account
		//We cannot add it directly because accountList is the loop
		if (addedAccounts.size() > 0)
			accountList.addAll(addedAccounts);
		
		return interestAccountMap;
	}
	
	/**
	 * @param conn
	 * @param jdmtSeq
	 * @param detailSeq
	 * @param accountVO
	 * @param partyCode
	 * @param pPartyNum
	 * @param dPartyNum
	 * @param descr
	 * @param courtType
	 * @throws Exception
	 */
	public static void insertJdmtDetail(Connection conn, int jdmtSeq, int detailSeq, AccountBO accountBO, 
			List<PartyCaseBO> partyCaseVOList, int dPartyNum, String descr, String courtType) throws Exception{
		
		try {
			int intCaseNum = accountBO.getIntCaseNum();
			BigDecimal balance = accountBO.getAmtDue().subtract(accountBO.getAmtPaid()).subtract(accountBO.getAmtCredit());
			
			new JudgmentDetlBO(courtType).setUseConnection(conn).setIntCaseNum(intCaseNum)
				.setJdmtSeq(jdmtSeq).setDetlSeq(detailSeq).setSeq(1).setDescr(descr).setAmt(balance).insert();
		
			if(partyCaseVOList!=null && partyCaseVOList.size()>0)
			{
				for(PartyCaseBO partyCaseBO:partyCaseVOList)
				{
				new JudgmentCreditorBO(courtType).setUseConnection(conn).setIntCaseNum(intCaseNum).setPartyNum(partyCaseBO.getPartyNum())
					.setJdmtSeq(jdmtSeq).setDetlSeq(detailSeq).setPartyCode(partyCaseBO.getPartyCode()).insert();
				}
			}
		
			new JudgmentDebtorBO(courtType).setUseConnection(conn).setIntCaseNum(intCaseNum).setPartyNum(dPartyNum)
				.setJdmtSeq(jdmtSeq).setDetlSeq(detailSeq).setPartyCode("DEF").insert();
		
			new AccountJudgmentXrefBO(courtType).setUseConnection(conn).setAcctNum(accountBO.getAcctNum())
				.setIntCaseNum(intCaseNum).setJdmtSeq(jdmtSeq).setDetlSeq(detailSeq).setSeq(1).insert(); 
		
		} catch (Exception e) {
			StringBuffer sb = new StringBuffer("Error in insertJdmtDetail.  Court Type = ");
			sb.append(courtType);
			sb.append("  Internal Case #");
			sb.append(accountBO.getIntCaseNum());
			sb.append("  Judgment sequence = ");
			sb.append(jdmtSeq);
			sb.append("  Detail sequence = ");
			sb.append(detailSeq);
			/*sb.append("  Party Code = ");
			sb.append(partyCode);*/
			/*sb.append("  Creditor Party #");
			sb.append(pPartyNum);*/
			sb.append("  Debtor Party #");
			sb.append(dPartyNum);
			sb.append("  Account #");
			sb.append(accountBO.getAcctNum());
			sb.append("  Description = ");
			sb.append(descr);
			logger.error(sb.toString(), e);
			
			throw e;
		}
	}
	
	public static boolean caseLevelJudementExist(Connection conn, int intCaseNum, String courtType) throws Exception{
		
//		SearchDescriptor s1 = new SearchDescriptor (new JudgmentBO(courtType).where(JudgmentBO.INTCASENUM, intCaseNum));
//    	SearchDescriptor s2 = new SearchDescriptor (
//    			new AccountJudgmentXrefBO(courtType).setOuter());
//    	
//    	new SearchDispatcher(s1, s2)
//    		.addForeignKey(JudgmentBO.INTCASENUM, AccountJudgmentXrefBO.INTCASENUM)
//    		.addForeignKey(JudgmentBO.JDMTSEQ, AccountJudgmentXrefBO.JDMTSEQ)
//    		.search();
//    	
//    	List<AccountJudgmentXrefBO> list = (List<AccountJudgmentXrefBO>) s2.getResults();
    	

		List<AccountJudgmentXrefBO> list = new  AccountJudgmentXrefBO(courtType).setOuter()
		.includeTables(
    		new JudgmentBO(courtType).where(JudgmentBO.INTCASENUM, intCaseNum)
    	)
    	.addForeignKey(JudgmentBO.INTCASENUM, AccountJudgmentXrefBO.INTCASENUM)
    	.addForeignKey(JudgmentBO.JDMTSEQ, AccountJudgmentXrefBO.JDMTSEQ)
    	.setUseConnection(conn)
    	.search();
		
    	if (list == null || list.size() == 0)
    		return false;
    	else {
    		for (AccountJudgmentXrefBO vo : list){
    			if (vo.getAccountJudgmentXrefId() == 0)
    				return true;
    		}
    	}
    	
    	return false;
    	
	}
	
	private static boolean isOpenEndedTrust(Connection conn, int acctNum, String courtType) throws Exception{
//		//select t.acct_num from acct_trust t, trust_type tt
//		//where  t.trust_code = tt.trust_code and tt.openend_flag = 'Y'
//		//and t.acct_num  = 39382
//		SearchDescriptor s1 = new SearchDescriptor (new AcctTrustBO(courtType).where(AcctTrustBO.ACCTNUM, acctNum));
//    	SearchDescriptor s2 = new SearchDescriptor (
//    			new TrustTypeBO(courtType).where(TrustTypeBO.OPENENDFLAG, "Y"));
//    	
//    	new SearchDispatcher(s1, s2)
//    		.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
//    		.search();
//    	
//    	List<AcctTrustBO> list = (List<AcctTrustBO>) s1.getResults();
    	
		List<AcctTrustBO> list = new AcctTrustBO(courtType)
		.where(AcctTrustBO.ACCTNUM, acctNum)
    	.includeTables(new TrustTypeBO(courtType).where(TrustTypeBO.OPENENDFLAG, "Y"))
    	.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
    	.setUseConnection(conn)
    	.search();
		
    	if (list == null || list.size() == 0)
    		return false;
    	else 
    		return true;
		
	}
	
	private static String getOsdcStatus(Connection conn, int acctNum, String courtType) throws Exception{
		String status = "";
		OsdcAcctHistoryBO statusBO = new OsdcAcctHistoryBO(courtType).setUseConnection(conn)
		.where(OsdcAcctHistoryBO.ACCTNUM, acctNum)
		.orderBy(new SortDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ, DirectionType.DESC))
		.setReturnNull(true)
		.find(OsdcAcctHistoryBO.OSDCSTATUS);
		
		if (statusBO != null)
			status = statusBO.getOsdcStatus();
		
		return status;
	}
	
}
