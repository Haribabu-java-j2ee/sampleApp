package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.calendar.CalendarBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.event.EventBO;
import gov.utcourts.coriscommon.dataaccess.hearingtype.HearingTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistoryBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.coriscommon.dto.CaseHeaderDTO;
import gov.utcourts.coriscommon.dto.CrimCaseDTO;
import gov.utcourts.coriscommon.dto.DebtCollectionCaseDTO;
import gov.utcourts.coriscommon.dto.DebtCollectionSearchDTO;
import gov.utcourts.coriscommon.dto.WorkCalCaseDTO;
import gov.utcourts.coriscommon.sp.GetCaseTitle;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinFindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.InsertSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TransactionDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.TruncateDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.UpdateDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.transaction.UpdateSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeDouble;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.BaseTransactionDispatcher;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.TransactionDispatcher;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class KaseXO implements BaseConstants { 

	private static Logger logger = Logger.getLogger(KaseXO.class);
	public static int PRINT_SQL = RUN;
	public static void main(String args[]) throws Exception {

		DatabaseConnection.setUseJdbc();
		Connection conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_DB);
		System.out.print(getCaseTypeCategory(conn, "D", 10));
		conn.close();

		//System.out.print(getCaseTypeCategory("D", 10));
	//	findCaseHeader("D",8084535);
		
		isCrimCase("D",41435);

/*		
		DebtCollectionSearchDTO mySearch = new DebtCollectionSearchDTO();
		mySearch.setCourtType("D");
		mySearch.setLocationCode("1868");
		mySearch.setAccountTypes("'T','I','F'");
		mySearch.setCountLimit(10);
		mySearch.setAmountLimit(1.01);
		mySearch.setOverDueLimit(90);
		mySearch.setSortType(2);
 		//mySearch.setIntCaseNum(41337);
		mySearch.setFromSentDate(new Date());
		mySearch.setToSentDate(new Date());
 		getDebtCollectionCase(mySearch);*/
//		queryTest("12345", "loc", "D");
		//testPopulateWorkCalCaseTable("D"); //passed all
//		ProfileBO profileBO = new ProfileBO("D");
//		profileBO.setLocnCode("1868");
//		profileBO.setCourtType("D");
//		findCases(profileBO, "D");
		/*Connection conn = null;
    	try{
    		conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_DB);
    		testPopulateWorkCalCaseTable("D",conn);
    	}finally{
    		if(conn!=null){
    			conn.close();
    		}
    	}*/

	}
	 
	private static void testPopulateWorkCalCaseTable(String courtType, Connection conn) throws Exception {
		
		insertWorkCalCase(30,courtType, conn); //passed
//		truncateWorkCalCaseTable(false, courtType,conn); //passed
//		updateWorkCalCaseCivilTitleName(courtType,conn); //passed
//		updateWorkCalCasePlaintiffCount(courtType,conn); //passed 
//		updateWorkCalCaseDefendantCount(courtType); //passed
//		updateWorkCalCaseTitlePlaNum(courtType, conn); // passed
//		updateWorkCalCaseTitleDefNum(courtType,conn); //passed
		
	}
	
	private static void queryTest(String caseNum, String locationCode, String courtType) throws Exception {
		new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE,locationCode).toString(PRINT_SQL).find();
	}
	
	public static KaseBO getKaseByCaseNum(String caseNum, String locationCode, String courtType) throws Exception {
		try{
			return new KaseBO(courtType).where(KaseBO.CASENUM, caseNum).where(KaseBO.LOCNCODE, locationCode).toString(PRINT_SQL).find();
		} catch(Exception e) {
			logger.error("Exception in KaseXO getKaseByCaseNum ",e);
			throw e;
		} 
	}
	
	public static List<DebtCollectionCaseDTO> findDebtCollectionEligible(HttpServletRequest request, DebtCollectionSearchDTO searchValues) throws Exception {
		 logger.debug("<< Entering KaseXO.findDebtCollectionEligible  >>");
		 
		  List<DebtCollectionCaseDTO> returnList = new ArrayList<DebtCollectionCaseDTO>();
		  try {
			 List<DebtCollectionCaseDTO> debtCollectionCase = getDebtCollectionCase(request, searchValues);			  
			 filterDebtCollectionCaseList(searchValues, returnList, debtCollectionCase);
	    	 
		  } catch(Exception e) {
			  e.printStackTrace();
			  logger.error("Exception in KaseXO findDebtCollectionEligible ",e);
			  throw e;
		  }
		  return returnList;
	}
	
	private static void filterDebtCollectionCaseList(DebtCollectionSearchDTO searchValues, List<DebtCollectionCaseDTO> returnList, List<DebtCollectionCaseDTO> debtCollectionCase) throws Exception {
		logger.debug("<< Entering KaseXO.getDebtCollectionCaseList >>");
		Connection conn = DatabaseConnection.getConnection("D".equalsIgnoreCase(searchValues.getCourtType())? KaseBO.CORIS_DISTRICT_DB: KaseBO.CORIS_JUSTICE_DB);
		try {		 
		 	List<Integer> accountNumbers; 
		    Iterator<DebtCollectionCaseDTO> iter = debtCollectionCase.iterator();
			
			DebtCollectionCaseDTO singleCase;
			int daysPastDue = 0;
			String acctStateStatus;
			boolean someNotSent = false;
			boolean acctPaid = false;
			boolean currentlyAtState = false;
			int lowerBound = 1;
			int upperBound = 0;
			
			if (searchValues.getOverDueLimit() != 90) {
			 	if (searchValues.getOverDueLimit() != 0) {
					lowerBound = searchValues.getOverDueLimit() ;
				}
				upperBound = searchValues.getOverDueLimit() + 29;
			}
			//OsdcAcctHistoryBO oaHBO;
			int numSent = 0;
			String descrForCodeR = CodeDescriptionXrefXO.getDescriptionByCode("R", "osdc_acct_history", searchValues.getCourtType(),conn).getDescription();
			String descrForCodeS = CodeDescriptionXrefXO.getDescriptionByCode("S", "osdc_acct_history", searchValues.getCourtType(),conn).getDescription();
			
			
		     int maxDaysPastDue = 0;
			 List<AccountBO> accountVOList=null;
			 Map<Integer, String> accNumAndOsdcStatusMap=null;
			while (iter.hasNext()) {
				maxDaysPastDue = 0;
				daysPastDue=0;
				someNotSent = false;
				acctPaid = false;
				currentlyAtState = false;
				numSent = 0;
				singleCase = iter.next();
				// Get a list of accounts.  The stored procedure that is used runs by account.
				accountNumbers = CrimCaseXO.getAccounts(singleCase.getIntCaseNum(), searchValues.getAccountTypes(),searchValues.getCourtType(),conn);
				//  accountNumbers = intCaseNumAccountsMap.get(singleCase.getIntCaseNum());
				  if(accountNumbers!=null && accountNumbers.size()>0)
					{	
					  accountVOList = AccountXO.getAccountsByAcctNumList(accountNumbers,searchValues.getCourtType(),conn);
					  accNumAndOsdcStatusMap=AccountXO.getMapOfAccNumAndLatestOsdcStatusByAccNums(conn,accountNumbers,searchValues.getCourtType());
					//	List<AccountBO> accountVOList = AccountXO.getAccountsByAcctNumList(accountNumbers,searchValues.getCourtType());
						for (AccountBO accountBO : accountVOList) {
						
							if(maxDaysPastDue<upperBound||(searchValues.getOverDueLimit() == 90 && maxDaysPastDue < 90))
							{
							daysPastDue = AccountXO.getDaysPastDue(singleCase.getUserId(), accountBO.getAcctNum(),searchValues.getCourtType(),conn);
							
								if (daysPastDue > maxDaysPastDue) {
									maxDaysPastDue = daysPastDue;
								}
							}
						
						
					    	 
					    //	AccountBO accountBO = AccountXO.getAccountByAcctNum(anAccount,searchValues.getCourtType());

				    	  	acctPaid = "AP".equalsIgnoreCase(accountBO.getStatus());
					    	// See if any are currently at state osdc
					    	if (!currentlyAtState) {
					    	//	oaHBO = OsdcAcctHistoryXO.getMostRecentStatusByAcctNum(accountBO.getAcctNum(), searchValues.getCourtType());
					    		String osdcStatus=accNumAndOsdcStatusMap.get(accountBO.getAcctNum());
					    		if (osdcStatus != null)
					    			currentlyAtState = "S".equalsIgnoreCase(osdcStatus) || "R".equalsIgnoreCase(osdcStatus);
					    		else
					    			currentlyAtState = false;
					    	}	
				    		
				        
							// Determine the case OSDC status - some at State debt Coll & some not?
							 
							//acctStateStatus = AccountXO.getStateDebtCollStatusByAccount(accountBO.getAcctNum(),searchValues.getCourtType());
					    	  acctStateStatus=accNumAndOsdcStatusMap.get(accountBO.getAcctNum());
							if (acctPaid) {
								if (acctStateStatus==null 
					    				||"".equalsIgnoreCase(acctStateStatus) 
					    				|| ("C".equalsIgnoreCase(acctStateStatus) 
					    				|| "N".equalsIgnoreCase(acctStateStatus)))
					    			continue;
							}
					    	if ("R".equalsIgnoreCase(acctStateStatus)) { // R = Ready
					    		singleCase.setDebtCollStatusDesc(descrForCodeR);
					    		//break;
					    	} else if ("S".equalsIgnoreCase(acctStateStatus)) { // S = Sent 
					    		numSent++;
					    	} else 
					    		someNotSent = true;    		
						}
					
					  
					}
				 
			 
					    			
				// Determine the case status.
				if (!descrForCodeR.equals(singleCase.getDebtCollStatusDesc())) {
			    	if (numSent > 0) {
			    		if (someNotSent) {
			    			singleCase.setDebtCollStatusDesc("Some Accounts Sent");
			    		} else {
			    			singleCase.setDebtCollStatusDesc(descrForCodeS);
			    		}
			    	} else if (currentlyAtState) { // for those recalled
			    		singleCase.setDebtCollStatusDesc(descrForCodeR);
			    	} else {
			    		singleCase.setDebtCollStatusDesc("No");
			    	}	    		
				}
				if ((searchValues.isIgnoreOverDueLimit()) ||
						(searchValues.getOverDueLimit() == 90 && maxDaysPastDue >= 90) || 
					(maxDaysPastDue <=upperBound && maxDaysPastDue >= lowerBound)){
					
					
					// Get the last paid date.
					singleCase.setLastPaidDate(CrimCaseXO.getLastPaidDate(singleCase.getIntCaseNum(), searchValues.getAccountTypes(),searchValues.getCourtType(),conn));
					returnList.add(singleCase);
				}
			}
		 
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception in KaseXO getDebtCollectionCaseList ",e);
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
 	}
	
	 private static List<DebtCollectionCaseDTO> getDebtCollectionCase(HttpServletRequest request, DebtCollectionSearchDTO searchValues) throws Exception
	 {		 
		 logger.debug("<< Entering KaseXO.getDebtCollectionCase  >>"); 
		 List<DebtCollectionCaseDTO> results=new ArrayList<DebtCollectionCaseDTO>();
		 String courtDatabaseToConnect="D".equalsIgnoreCase(searchValues.getCourtType())?"D":"J";
		 FieldOperationDescriptor maxAccountDueDate = new FieldOperationDescriptor(AccountBO.DUEDATE, FieldOperationType.MAX, new TypeDate()).setAlias("dueDate");
		 FieldOperationDescriptor maxAccountUserIdSRL = new FieldOperationDescriptor(AccountBO.USERIDSRL, FieldOperationType.MAX, new TypeInteger()).setAlias("userId");
		 FieldOperationDescriptor maxEventBeginDateTime = new FieldOperationDescriptor(EventBO.BEGINDATETIME, FieldOperationType.MAX, new TypeDate()).setAlias("sentenceDate");
		 Expression expressionDescriptor = new Expression(
				 FieldOperationType.SUM,
					Exp.LEFT_PARENTHESIS,
					Exp.literal("totalAmountDue"),					 
					Exp.LEFT_PARENTHESIS,
						AccountBO.AMTDUE,
						Exp.literal(","),							 
							AccountBO.AMTPAID,
							Exp.literal(","),
							AccountBO.AMTCREDIT,
						Exp.RIGHT_PARENTHESIS,
					Exp.RIGHT_PARENTHESIS
				 );            
		 
		 FieldOperationDescriptor selectExpression = new FieldOperationDescriptor(expressionDescriptor, FieldOperationType.EXPRESSION, new TypeDouble()).setAlias("balance");	  
		  
		 AccountBO accountBO = new AccountBO(courtDatabaseToConnect);
		 accountBO.limit(Constants.MAX_RESULTS);
		 
		 KaseBO kaseBO= new KaseBO(courtDatabaseToConnect);
		 EventBO eventBO=new EventBO(courtDatabaseToConnect);
		 PartyBO partyBO= new PartyBO(courtDatabaseToConnect);
		  
		 SearchDescriptor accountSD=new SearchDescriptor(accountBO.setFieldOperations(maxAccountDueDate,maxAccountUserIdSRL,selectExpression));
		 SearchDescriptor kaseSD=new SearchDescriptor(kaseBO);
		 SearchDescriptor eventSD=new SearchDescriptor( eventBO.where(EventBO.EVENTCODE, "SNT" ));
		  
		 SearchDescriptor partySD=new SearchDescriptor(partyBO);
		 if (!(searchValues.getFromSentDate() != null || searchValues.getToSentDate() != null))
		 {
			 eventBO.setOuter();
		 }
		  
		


		 accountBO.groupBy(AccountBO.INTCASENUM);
		  
		 kaseBO.groupBy(KaseBO.CASENUM,KaseBO.DEBTCOLLECTION,
				  KaseBO.CASETYPE,KaseBO.LOCALDEBTCOLL);
		  
		 partyBO.groupBy( PartyBO.FIRSTNAME,PartyBO.LASTNAME,PartyBO.BIRTHDATE,PartyBO.SSN);
	
		 eventBO.setFieldOperations(maxEventBeginDateTime);
		  
		 eventBO.addWhereDescriptors(new WhereSelectDescriptor(
				new TableAndFieldDescriptor(EventBO.BEGINDATETIME),
				new EventBO(courtDatabaseToConnect).setFieldOperations(maxEventBeginDateTime)
				.where(new FindDescriptor(EventBO.INTCASENUM).setCustomSearch("= t3."+KaseBO.INTCASENUM.getDbFieldName()))
				.where(EventBO.EVENTCODE,"SNT"),
				Exp.EQUALS		
		 ));
		
		 
		 if (searchValues.getIntCaseNum() > 0
			|| !TextUtil.isEmpty(searchValues.getFirstName())
			|| !TextUtil.isEmpty(searchValues.getLastName())) {
			
			if (searchValues.getIntCaseNum() > 0) {
				accountBO.where(AccountBO.INTCASENUM, searchValues.getIntCaseNum());
		 
			}
			
			accountBO.where(new FindDescriptor(AccountBO.ACCTTYPE,
					Exp.IN, new StringArrayDescriptor(
							"I","T","F")));
			
			 accountBO.where(new Expression( Exp.LEFT_PARENTHESIS,
					 Exp.LEFT_PARENTHESIS, AccountBO.AMTDUE,Exp.MINUS,
					 Exp.LEFT_PARENTHESIS,AccountBO.AMTPAID,
					 Exp.PLUS,AccountBO.AMTCREDIT,Exp.RIGHT_PARENTHESIS,
					 Exp.GREATER_THAN,0,Exp.RIGHT_PARENTHESIS,
					 Exp.OR, 
					 Exp.LEFT_PARENTHESIS,
					 
					new TableAndFieldDescriptor("t5",  OsdcAcctHistoryBO.OSDCSTATUS),
					 Exp.IN,
					 new StringArrayDescriptor("S", "R"),
					 Exp.RIGHT_PARENTHESIS,
				     Exp.RIGHT_PARENTHESIS
					 
					 //((t1.amt_due - (t1.amt_paid + t1.amt_credit) > ?) or (t1.osdc_status in (?,?)))
			 ));
			
			kaseBO.where(KaseBO.LOCNCODE, searchValues.getLocationCode());
				
			kaseBO.where(KaseBO.COURTTYPE, searchValues.getCourtType());
			 
			if (!TextUtil.isEmpty(searchValues.getFirstName())) {
		 
				
				partyBO.where(new FindDescriptor(PartyBO.FIRSTNAME,Exp.LIKE,new StringArrayDescriptor(searchValues.getFirstName().replace("*", "%"))));
				
			}
			if (!TextUtil.isEmpty(searchValues.getLastName())) {
			
				partyBO.where(new FindDescriptor(PartyBO.LASTNAME,Exp.LIKE,new StringArrayDescriptor(searchValues.getLastName().replace("*", "%"))));
			
			}
		} 
		else {
			
			 accountBO.where(new Expression(
					 Exp.LEFT_PARENTHESIS, 
					 Exp.literal("totalAmountDue"),	 
					 Exp.LEFT_PARENTHESIS,
					 AccountBO.AMTDUE,
				     Exp.literal(","),	
					 AccountBO.AMTPAID,
					 Exp.literal(","),	
					 AccountBO.AMTCREDIT,
					 Exp.RIGHT_PARENTHESIS,
					 Exp.GREATER_THAN,0,Exp.RIGHT_PARENTHESIS
					 
			 ));
			kaseBO.where(KaseBO.LOCNCODE, searchValues.getLocationCode());
			
			kaseBO.where(KaseBO.COURTTYPE, searchValues.getCourtType());
			
			
			
			if (searchValues.getFromSentDate() != null && searchValues.getToSentDate() != null) {
			
				 
				 Expression dateBetweenExpDesc = new Expression(
					EventBO.BEGINDATETIME,
					Exp.BETWEEN,
					searchValues.getFromSentDate(),
					Exp.AND,
					searchValues.getToSentDate()
				 );								
				 eventBO.where(dateBetweenExpDesc);
				
			
			} else {
				if (searchValues.getFromSentDate() != null) {
					 Expression dateExpDesc = new Expression(
								Exp.DATE,
								Exp.LEFT_PARENTHESIS,
								EventBO.BEGINDATETIME,
								Exp.RIGHT_PARENTHESIS,
								Exp.EQUALS,
								searchValues.getFromSentDate()
							 );								
							 eventBO.where(dateExpDesc);
				}
				if (searchValues.getToSentDate() != null) {
					 Expression dateExpDesc = new Expression(
								Exp.DATE,
								Exp.LEFT_PARENTHESIS,
								EventBO.BEGINDATETIME,
								Exp.RIGHT_PARENTHESIS,
								Exp.EQUALS,
								searchValues.getToSentDate()
							 );								
							 eventBO.where(dateExpDesc);
				} 
			}
			
			
			if (searchValues.getJudgeId() > 0) {
				kaseBO.where(KaseBO.ASSNJUDGEID, searchValues.getJudgeId());
				
			}
	
			
			if (searchValues.getAccountTypes() != null && searchValues.getAccountTypes().length() > 0) {
				List<String> accountTypeList=new ArrayList<String>();
					 
				for (String accType : searchValues.getAccountTypes()
						.replace("'", "").split(",")) {
					accountTypeList.add(accType.trim());
				}
				if (accountTypeList != null && accountTypeList.size() > 0) {
					accountBO.where(new FindDescriptor(AccountBO.ACCTTYPE,
							Exp.IN, new StringArrayDescriptor(
									accountTypeList)));
				}
			
			}
			
			
		 	if (searchValues.getCaseType() != null && !"".equals(searchValues.getCaseType())) {
		 		kaseBO.where(KaseBO.CASETYPE, searchValues.getCaseType());
			
			}
							
			
			if (searchValues.getDebtStatus() != null && searchValues.getDebtStatus().length() > 0) {			
				kaseBO.where(new FindDescriptor(KaseBO.DEBTCOLLECTION,Exp.IS_NOT_NULL));			
			} 
			
		} 	
		 try {
			// Determine the sort order.
			/*switch (searchValues.getSortType()) {
			case 1: // By case
				kaseBO.orderBy(KaseBO.CASENUM);
				partyBO.orderBy(PartyBO.LASTNAME);
			
				break;
			case 2: // By date
				accountBO.orderBy(new SortCustomDescriptor("dueDate"));
				kaseBO.orderBy(KaseBO.CASENUM);
				
				break;
			case 3: // By Amount
				accountBO.orderBy(new SortCustomDescriptor("customOperation_sum desc"));
				kaseBO.orderBy(KaseBO.CASENUM);
				
				
				break;
			case 4: // By debt collection status.
				kaseBO.orderBy(new SortCustomDescriptor("debt_collection desc"));
				kaseBO.orderBy(KaseBO.CASENUM);
				
				break;
			default:
				kaseBO.orderBy(KaseBO.CASENUM);
				partyBO.orderBy(PartyBO.LASTNAME);
				
				break;
			}*/
			
			BaseSearchDispatcher searchFactory = null;

			// Added local debt collection check.  Selart 03/26/2019  Piv #164913366
			if (searchValues.getIntCaseNum() > 0 || !TextUtil.isEmpty(searchValues.getFirstName()) || !TextUtil.isEmpty(searchValues.getLastName())) {
					 
				searchFactory =new SearchDispatcher(accountSD, partySD, kaseSD, eventSD, new SearchDescriptor(new OsdcAcctHistoryBO(courtDatabaseToConnect).setOuter()))
				.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
				.addForeignKey(AccountBO.PARTYNUM, PartyBO.PARTYNUM)
				.addForeignKey(AccountBO.ACCTNUM, OsdcAcctHistoryBO.ACCTNUM)
				.addForeignKey(KaseBO.INTCASENUM, EventBO.INTCASENUM)
				.setResultContainer(new DebtCollectionCaseDTO())	
				.addSearchWrapDescriptor("select * from (", ") where debt_collection in ('S','Y') or balance > 0 or local_debt_coll = 'Y'")
				.toString(PRINT_SQL)
				.search(
					AccountBO.INTCASENUM,KaseBO.CASENUM,KaseBO.CASETYPE, KaseBO.DEBTCOLLECTION,KaseBO.LOCALDEBTCOLL,
				    PartyBO.FIRSTNAME,PartyBO.LASTNAME,PartyBO.BIRTHDATE,PartyBO.SSN
				)
				.setIsolationDirtyRead(true);
				 
			} else {
				 
				searchFactory = new SearchDispatcher(accountSD, partySD, kaseSD, eventSD)
				.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
				.addForeignKey(AccountBO.PARTYNUM, PartyBO.PARTYNUM)
				.addForeignKey(KaseBO.INTCASENUM, EventBO.INTCASENUM)
				.setResultContainer(new DebtCollectionCaseDTO())
				.addSearchWrapDescriptor("select * from (", ") where balance > " + searchValues.getBalance())					  
				.toString(PRINT_SQL)
				.search(
					AccountBO.INTCASENUM, KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.DEBTCOLLECTION, KaseBO.LOCALDEBTCOLL,
					PartyBO.FIRSTNAME, PartyBO.LASTNAME, PartyBO.BIRTHDATE, PartyBO.SSN
				)
				.setIsolationDirtyRead(true);
				
			}
		
			results = (List<DebtCollectionCaseDTO>) searchFactory.getResults();
		 	
			request.setAttribute("resultsSize", results != null ? Integer.toString(results.size()) : "0");
			
		} catch (Exception e) {
			 e.printStackTrace();
			 logger.error("Exception in KaseXO getDebtCollectionCase ",e);
			 throw e;
		}
		 return results;
	 
	 }
	 
	/**
	 * Prepares the work_cal_case table for the calendar unload batch process.
	 * 
	 * @param daysToDisplay
	 *            The number of Calendar days you want to unload.
	 * @param conn 
	 * @throws Exception
	 *             Unexpected Exception
	 */
	public static void populateWorkCalCaseTable(int daysToDisplay, String courtType) throws Exception {
		
		Connection conn = null;
    	try{
    		if("D".equalsIgnoreCase(courtType)){
    			conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_DB);
    		}else{
    			conn = DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_DB);
    		}
    		conn.setAutoCommit(false);
			truncateWorkCalCaseTable(false, courtType, conn);
			
			conn.commit();
	
			insertWorkCalCase(daysToDisplay, courtType, conn);
			
			conn.commit();
	
			// get civil case title (not protected) and put into work_cal_case table
			updateWorkCalCaseCivilTitleName(courtType, conn);
			
			conn.commit();
	
			// Update work_cal_case plaintiff_count from the party_case.
			updateWorkCalCasePlaintiffCount(courtType, conn);
			
			conn.commit();
	
			// // Update work_cal_case defendant_count from the party_case.
			updateWorkCalCaseDefendantCount(courtType, conn);
			
			conn.commit();
	
			// // Update work_cal_case title_pla_num.
			updateWorkCalCaseTitlePlaNum(courtType, conn);
			
			conn.commit();
	
			// // Update work_cal_case title_def_num.
			updateWorkCalCaseTitleDefNum(courtType, conn);
			
			conn.commit();
			
			logger.info("Populate WorkCalCase Table successful");
		}catch(Exception e){
			conn.rollback();
			throw e;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
	}

	private static void updateWorkCalCaseTitleDefNum(String courtType, Connection conn) throws Exception {

		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyCaseBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
		PartyCaseBO partyCaseBO = new PartyCaseBO(courtType).setFieldOperations(min)
			.where(new FindDescriptor(PartyCaseBO.PARTYCODE, Exp.IN,  new StringArrayDescriptor("DEF","RES")),
				new FindDescriptor(PartyCaseBO.INTCASENUM).setCustomSearch("=work_cal_case.int_case_num"));

		// WorkCalCaseBO workCalCaseBO = new
		// WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.NO_FIELDS);

		BaseSearchDispatcher search = new SearchDispatcher(partyCaseBO).setUseConnection(conn);
		// .addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM);

		TransactionDescriptor updateSelectDescriptor = new UpdateSelectDescriptor().setUpdateTable(
				new WorkCalCaseBO(courtType).where(new FindDescriptor(WorkCalCaseBO.TITLEDEFNUM, Exp.IS_NULL)).includeFields(WorkCalCaseBO.TITLEDEFNUM))
				.setSelectFromTable(search.getSearchDispatcherParameters());

		BaseTransactionDispatcher transactionFactory = null;
		try {

			transactionFactory = new TransactionDispatcher(updateSelectDescriptor).setUseConnection(conn).toString(PRINT_SQL).execute();

			if (transactionFactory.successful()) {
				logger.debug(Calendar.getInstance().getTime() + ": updateWorkCalCaseTitleDefNum successful");
			} else {
				logger.error(transactionFactory.getErrorMessage());
				throw new Exception(transactionFactory.getErrorMessage());
			}

		} catch (Exception e) {
			logger.error("Errors in updating WorkCalCase title_pla_num field", e);
			throw e;

		} finally {

			transactionFactory = null;
		}

	}

	private static void updateWorkCalCaseTitlePlaNum(String courtType, Connection conn) throws Exception {
		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyCaseBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
		PartyCaseBO partyCaseBO = new PartyCaseBO(courtType).setFieldOperations(min)
			.where(new FindDescriptor(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("PLA","PET")),
				new FindDescriptor(new Expression(PartyCaseBO.INTCASENUM, Exp.EQUALS, new TableAndFieldDescriptor("work_cal_case", WorkCalCaseBO.INTCASENUM))));

		// WorkCalCaseBO workCalCaseBO = new
		// WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.NO_FIELDS);

		BaseSearchDispatcher search = new SearchDispatcher(partyCaseBO).setUseConnection(conn);
		// .addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM);

		TransactionDescriptor updateSelectDescriptor = new UpdateSelectDescriptor().setUpdateTable(
				new WorkCalCaseBO(courtType).where(new FindDescriptor(WorkCalCaseBO.TITLEPLANUM, Exp.IS_NULL)).includeFields(WorkCalCaseBO.TITLEPLANUM))
				.setSelectFromTable(search.getSearchDispatcherParameters());

		BaseTransactionDispatcher transactionFactory = null;
		try {

			transactionFactory = new TransactionDispatcher(updateSelectDescriptor).setUseConnection(conn).toString(PRINT_SQL).execute();

			if (transactionFactory.successful()) {
				logger.debug(Calendar.getInstance().getTime() + ": updateWorkCalCaseTitlePlaNum select successful");
			} else {
				logger.error(transactionFactory.getErrorMessage());
				throw new Exception(transactionFactory.getErrorMessage());
			}

		} catch (Exception e) {
			logger.error("Errors in updating WorkCalCase title_pla_num field", e);
			throw e;

		} finally {

			transactionFactory = null;
		}

	}

	private static void updateWorkCalCaseDefendantCount(String courtType, Connection conn) throws Exception {
		FieldOperationDescriptor count = new FieldOperationDescriptor(PartyCaseBO.INTCASENUM, FieldOperationType.COUNT, new TypeInteger());
		PartyCaseBO partyCaseBO = new PartyCaseBO(courtType).setFieldOperations(count)
			.where(new FindDescriptor(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF","RES")),
				new FindDescriptor(PartyCaseBO.INTCASENUM).setCustomSearch("=work_cal_case.int_case_num"));

		// WorkCalCaseBO workCalCaseBO = new
		// WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.NO_FIELDS);

		BaseSearchDispatcher search = new SearchDispatcher(partyCaseBO).setUseConnection(conn); // .addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM);

		TransactionDescriptor updateSelectDescriptor = new UpdateSelectDescriptor().setUpdateTable(new WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.DEFENDANTCOUNT))
				.setSelectFromTable(search.getSearchDispatcherParameters());

		BaseTransactionDispatcher transactionFactory = null;
		try {

			transactionFactory = new TransactionDispatcher(updateSelectDescriptor).setUseConnection(conn).toString(PRINT_SQL).execute();

			if (transactionFactory.successful()) {
				logger.debug(Calendar.getInstance().getTime() + ": updateWorkCalCaseDefendantCount successful");
			} else {
				logger.error(transactionFactory.getErrorMessage());
				throw new Exception(transactionFactory.getErrorMessage());
			}

		} catch (Exception e) {
			logger.error("Errors in updatg WorkCalCase Defendant count", e);
			throw e;

		} finally {

			transactionFactory = null;
		}

	}

	private static void updateWorkCalCasePlaintiffCount(String courtType, Connection conn) throws Exception {

		FieldOperationDescriptor count = new FieldOperationDescriptor(PartyCaseBO.INTCASENUM, FieldOperationType.COUNT, new TypeInteger());
		PartyCaseBO partyCaseBO = new PartyCaseBO(courtType).setFieldOperations(count)
			.where(new FindDescriptor(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("PLA","PET")),
				new FindDescriptor(PartyCaseBO.INTCASENUM).setCustomSearch("=work_cal_case.int_case_num"));

		// WorkCalCaseBO workCalCaseBO = new
		// WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.NO_FIELDS);

		BaseSearchDispatcher search = new SearchDispatcher(partyCaseBO).setUseConnection(conn); // .addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM);

		TransactionDescriptor updateSelectDescriptor = new UpdateSelectDescriptor().setUpdateTable(new WorkCalCaseBO(courtType).includeFields(WorkCalCaseBO.PLAINTIFFCOUNT))
				.setSelectFromTable(search.getSearchDispatcherParameters());

		BaseTransactionDispatcher transactionFactory = null;
		try {

			transactionFactory = new TransactionDispatcher(updateSelectDescriptor).setUseConnection(conn).toString(PRINT_SQL).execute();

			if (transactionFactory.successful()) {
				logger.debug(Calendar.getInstance().getTime() + ": updateWorkCalCasePlaintiffCount successful");
			} else {
				logger.error(transactionFactory.getErrorMessage());
				throw new Exception(transactionFactory.getErrorMessage());
			}

		} catch (Exception e) {
			logger.error("Errors in updatg WorkCalCase Plaintiff_Count", e);
			throw e;

		} finally {

			transactionFactory = null;
		}

	}

	private static void updateWorkCalCaseCivilTitleName(String courtType, Connection conn) throws Exception {
		BaseSearchDispatcher searchDispatch = new SearchDispatcher(
				new WorkCalCaseBO(courtType)).setUseConnection(conn)
				.setResultContainer(new WorkCalCaseDTO()).toString(PRINT_SQL)
				.search(WorkCalCaseBO.INTCASENUM).setIsolationDirtyRead(true);
			if (searchDispatch != null && searchDispatch.getResults() != null) {
					List<WorkCalCaseDTO> workCalCaseDTOList = (List<WorkCalCaseDTO>) searchDispatch.getResults();
						if (workCalCaseDTOList != null && workCalCaseDTOList.size() > 0) {
							BaseTransactionDispatcher transactionDispatcher = null;
			
							try {
								//long start = 0;
								//long end = 0;
								for (WorkCalCaseDTO workCalCaseDTO : workCalCaseDTOList) {
									//start = System.currentTimeMillis();
									String title = GetCaseTitle.getCaseTitle("inquiry",workCalCaseDTO.getIntCaseNum(), courtType, conn);
									//end = System.currentTimeMillis();
									//System.out.println("Operation took " + (end - start) + " ms. " + title);
									if(!TextUtil.isEmpty(title))
										{
											TransactionDescriptor updateDescriptor = new UpdateDescriptor()
									        .setUpdateTable(new WorkCalCaseBO(courtType)
									        .setCaseTitle(title)
											.where(WorkCalCaseBO.INTCASENUM,workCalCaseDTO.getIntCaseNum()));
	
											transactionDispatcher = new TransactionDispatcher(updateDescriptor).setUseConnection(conn)
														.toString(PRINT_SQL).execute();
										}
				
										
				
									if (transactionDispatcher.successful()) {
											// successful
										} else {
											logger.error(transactionDispatcher.getErrorMessage());
										}
									
									if((workCalCaseDTOList.indexOf(workCalCaseDTO) % 100) == 0){
										conn.commit();
									}
				
								}
							} catch (Exception e) {
								logger.error(e.getMessage());
							} finally {
								transactionDispatcher = null;
							}
			
						}
			}

	}

	/**
	 * Truncates the work_cal_case table
	 * 
	 * @param courtType
	 *            Database type
	 * @param dropStorage
	 *            Drop the storage for the table.
	 * @param conn 
	 * @return Number of rows affected.
	 * @throws Exception
	 *             Unexpected Exception
	 */
	private static void truncateWorkCalCaseTable(Boolean dropStorage, String courtType, Connection conn) throws Exception {
		BaseTransactionDispatcher transactionFactory = null;
		TransactionDescriptor truncateDescriptor = null;
		try {
			truncateDescriptor = new TruncateDescriptor().setTruncateTable(new WorkCalCaseBO(courtType)).setDropStorage(dropStorage);

			transactionFactory = new TransactionDispatcher(truncateDescriptor).setUseConnection(conn).toString(PRINT_SQL).execute();

			if (transactionFactory.successful()) {
				conn.commit();
				logger.debug(Calendar.getInstance().getTime() + ": truncateWorkCalCaseTable successful.");
			} else {
				logger.error(transactionFactory.getErrorMessage());
				throw new Exception(transactionFactory.getErrorMessage());
			}
		} catch (Exception e) {
			logger.error("Failed to truncate WorkCalCase Table", e);
			throw e;

		} finally {

			transactionFactory = null;
		}
	}

	private static void insertWorkCalCase(int daysToDisplay, String courtType, Connection conn) throws Exception {
		
		SearchDescriptor calendar = new SearchDescriptor(new CalendarBO(courtType).setIsolationDirtyRead(true)
				.where(new FindDescriptor(CalendarBO.APPEARDATE).setCustomSearch("between TODAY and TODAY + " + daysToDisplay))
				.where(new FindDescriptor(CalendarBO.CANCELDATETIME,Exp.IS_NULL))
				.includeFields(CalendarBO.INTCASENUM, CalendarBO.HEARINGCODE, CalendarBO.APPEARDATE, CalendarBO.CREATEDATETIME, CalendarBO.TIME, CalendarBO.PRIORITY,
						CalendarBO.JUDGEID, CalendarBO.ROOM, CalendarBO.EVENTDESCR));
		
		SearchDescriptor kase = new SearchDescriptor(new KaseBO(courtType).setIsolationDirtyRead(true).includeFields(KaseBO.CASETYPE, KaseBO.LOCNCODE, KaseBO.COURTTYPE, KaseBO.CASENUM, KaseBO.CASESECURITY));

		SearchDescriptor caseType = new SearchDescriptor(new CaseTypeBO(courtType).setIsolationDirtyRead(true)
				.as(CaseTypeBO.DESCR, "case_type_descr")
				.includeFields(CaseTypeBO.DESCR, CaseTypeBO.CATEGORY));

		SearchDescriptor civilCase = new SearchDescriptor(new CivilCaseBO(courtType).setIsolationDirtyRead(true)
				.includeFields(CivilCaseBO.TITLEDEFNUM, CivilCaseBO.TITLEPLANUM));
		
		SearchDescriptor personnel = new SearchDescriptor(new PersonnelBO(courtType).setIsolationDirtyRead(true)
				.includeFields(PersonnelBO.FIRSTNAME, PersonnelBO.LASTNAME));
		
		SearchDescriptor hearingType = new SearchDescriptor(new HearingTypeBO(courtType).setIsolationDirtyRead(true)
				.as(HearingTypeBO.DESCR, "hearing_descr")
				.includeFields(HearingTypeBO.DESCR));

		BaseSearchDispatcher searchDispatch = new SearchDispatcher(calendar, kase, caseType, civilCase, personnel, hearingType).setIsolationDirtyRead(true).setUseConnection(conn)
				.addJoin(JoinType.INNER_JOIN, KaseBO.TABLE.getTableName(), KaseBO.INTCASENUM, CalendarBO.INTCASENUM)
				.addJoin(JoinType.INNER_JOIN, CaseTypeBO.TABLE.getTableName(), CaseTypeBO.CASETYPE, KaseBO.CASETYPE)
				.addJoin(JoinType.LEFT_OUTER_JOIN, CivilCaseBO.TABLE.getTableName(), CivilCaseBO.INTCASENUM, CalendarBO.INTCASENUM)
				.addJoin(JoinType.LEFT_OUTER_JOIN, PersonnelBO.TABLE.getTableName(), PersonnelBO.USERIDSRL, CalendarBO.JUDGEID)
				.addJoin(JoinType.LEFT_OUTER_JOIN, HearingTypeBO.TABLE.getTableName(), HearingTypeBO.HEARINGCODE, CalendarBO.HEARINGCODE,
						// new JoinFindDescriptor("hearing_type.", HearingTypeBO.LOCNCODE, Exp.EQUALS, "kase.locn_code",""),
						new JoinFindDescriptor(
							new Expression(
								new TableAndFieldDescriptor(HearingTypeBO.TABLE.getTableName(), HearingTypeBO.LOCNCODE), 
								Exp.EQUALS, 
								new TableAndFieldDescriptor(KaseBO.TABLE.getTableName(), KaseBO.LOCNCODE)
							)
						),
						// new JoinFindDescriptor("hearing_type.", HearingTypeBO.COURTTYPE, Exp.EQUALS, "kase.court_type","")
						new JoinFindDescriptor(
							new Expression(
								new TableAndFieldDescriptor(HearingTypeBO.TABLE.getTableName(), HearingTypeBO.COURTTYPE), 
								Exp.EQUALS, 
								new TableAndFieldDescriptor(KaseBO.TABLE.getTableName(), KaseBO.COURTTYPE)
							)
						)
				);

		// get the kases and calendar data.
		TransactionDescriptor insertWorkCalCaseDescriptor = new InsertSelectDescriptor(WorkCalCaseBO.INTCASENUM, WorkCalCaseBO.HEARINGCODE, WorkCalCaseBO.APPEARDATE,
				WorkCalCaseBO.CREATEDATETIME, WorkCalCaseBO.HEARINGTIME, WorkCalCaseBO.PRIORITY, WorkCalCaseBO.JUDGEID, WorkCalCaseBO.ROOM, WorkCalCaseBO.EVENTDESCR,
				WorkCalCaseBO.CASETYPE, WorkCalCaseBO.LOCNCODE, WorkCalCaseBO.COURTTYPE, WorkCalCaseBO.CASENUM, WorkCalCaseBO.CASESECURITY, WorkCalCaseBO.CASETYPEDESCR,
				WorkCalCaseBO.CASETYPECATEGORY, WorkCalCaseBO.TITLEDEFNUM, WorkCalCaseBO.TITLEPLANUM, WorkCalCaseBO.JUDGEFIRSTNAME, WorkCalCaseBO.JUDGELASTNAME,
				WorkCalCaseBO.HEARINGDESCR).setInsertIntoTable(new WorkCalCaseBO(courtType))
				.setSelectFromDispatcherParameters(searchDispatch.getSearchDispatcherParameters());

		BaseTransactionDispatcher insertWorkCalCase = new TransactionDispatcher(insertWorkCalCaseDescriptor).setIsolationDirtyRead(true).setUseConnection(conn).toString(PRINT_SQL).execute();

		if (!insertWorkCalCase.successful()) {
			logger.error("Failed to insert into WorkCalCase table");
			throw new Exception(insertWorkCalCase.getErrorMessage());
		}
		logger.debug(Calendar.getInstance().getTime() + ": Insert WorkCalCase table successfull");
	}

	@SuppressWarnings("unchecked")
	public static List<CrimCaseDTO> findCases(ProfileBO profileBO, String courtType, Connection conn) throws Exception {

//		BaseSearchDispatcher searchFactory = new SearchDispatcher(new WorkCalCaseBO(courtType)
//				.where(new FindDescriptor(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode()))
//					.includeFields(WorkCalCaseBO.INTCASENUM, WorkCalCaseBO.CASENUM, WorkCalCaseBO.LOCNCODE, WorkCalCaseBO.COURTTYPE, WorkCalCaseBO.CASETYPE, WorkCalCaseBO.CASETYPEDESCR,
//							WorkCalCaseBO.CASETYPECATEGORY, WorkCalCaseBO.CASETITLE, WorkCalCaseBO.TITLEDEFNUM, WorkCalCaseBO.TITLEPLANUM, WorkCalCaseBO.PLAINTIFFCOUNT,
//							WorkCalCaseBO.DEFENDANTCOUNT))
//				.setResultContainer(new CrimCaseDTO(courtType)).setUseConnection(conn)
//				.toString(PRINT_SQL)
//				.search();
//
//		return (List<CrimCaseDTO>) searchFactory.getResults();
		
		return new WorkCalCaseBO(courtType)
		.where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode())
		.includeFields(
			WorkCalCaseBO.INTCASENUM, WorkCalCaseBO.CASENUM, WorkCalCaseBO.LOCNCODE, WorkCalCaseBO.COURTTYPE, WorkCalCaseBO.CASETYPE, 
			WorkCalCaseBO.CASETYPEDESCR, WorkCalCaseBO.CASETYPECATEGORY, WorkCalCaseBO.CASETITLE, WorkCalCaseBO.TITLEDEFNUM, 
			WorkCalCaseBO.TITLEPLANUM, WorkCalCaseBO.PLAINTIFFCOUNT, WorkCalCaseBO.DEFENDANTCOUNT
		)
		.setResultContainer(new CrimCaseDTO(courtType))
		.setUseConnection(conn)
		.toString(PRINT_SQL)
		.searchAndGetResults();
		
	}
	
	 /**
     * Mark a case as set to local debt collection.
     * 
     * @param intCaseNumber
     * @param courtType
     * @param settingLocalDebtCollection
     * @return int
     * @throws Exception
     */

	public static int setLocalDebtCollectionFlag(int intCaseNum, String courtType, boolean settingLocalDebtCollection) throws Exception {

		int returnValue = 0;
		try {
			KaseBO baseBO = null;
			if (settingLocalDebtCollection) {

				baseBO = new KaseBO(courtType)
				.setLocalDebtColl("Y")
				.setDebtCollDate(new Date())
				.where(KaseBO.INTCASENUM, intCaseNum)
				.toString(PRINT_SQL)
				.update();

			} else {
				
				baseBO = new KaseBO(courtType)
				.setLocalDebtColl("N")
				.setDebtCollDate(null)
				.where(KaseBO.INTCASENUM, intCaseNum)
				.toString(PRINT_SQL)
				.update();
				
			}

			if (baseBO != null) {
				returnValue = (Integer) baseBO.get(BaseConstants.UPDATE_COUNT);
			}
		} catch (Exception e) {
			logger.error("Exception in KaseXO setLocalDebtCollectionFlag", e);
			throw e;
		}
		return returnValue;

	}
	
	public static String getCaseTypeCategory(Connection conn, String courtType, int intCaseNum) throws Exception{
		try {
			
//			SearchDescriptor kase = new SearchDescriptor(new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum));
//
//			SearchDescriptor caseType = new SearchDescriptor(new CaseTypeBO(courtType));
//			BaseSearchDispatcher searchFactory = new SearchDispatcher(kase, caseType).setUseConnection(conn)
//				.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
//				.search(CaseTypeBO.CATEGORY);
//			
//			return ((CaseTypeBO) caseType.getResults().get(0)).getCategory();
			
    		return (String) new KaseBO(courtType)
    		.where(KaseBO.INTCASENUM, intCaseNum)
    		.includeTables(new CaseTypeBO(courtType))
			.setUseConnection(conn)
			.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
			.find(CaseTypeBO.CATEGORY)
			.get(CaseTypeBO.CATEGORY);
    		
		} catch (Exception e){
			logger.error("Exception in KaseXO setLocalDebtCollectionFalg", e);
			throw e;
		}
		
	}
	
	 /**
	 * Find case header info
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static CaseHeaderDTO  findCaseHeader(String courtType, int intCaseNum) throws Exception { 
		CaseHeaderDTO caseHeaderDTO = null;
		try { 
			
//			KaseBO kaseBO=new KaseBO(courtType).where(KaseBO.INTCASENUM, intCaseNum);
//	        CrimCaseBO crimCaseBO=new CrimCaseBO(courtType).setOuter();
//	         
//	     
//	        CaseTypeBO caseTypeBO=new CaseTypeBO(courtType);
//	        
//	        SearchDescriptor kaseSD=new SearchDescriptor(kaseBO);
//	        SearchDescriptor crimCaseSD=new SearchDescriptor(crimCaseBO);
//	       
//	        SearchDescriptor caseTypeSD=new SearchDescriptor(caseTypeBO);
//			
//			BaseSearchDispatcher searchFactory=new SearchDispatcher(kaseSD,crimCaseSD,caseTypeSD)
//	        
//	       .addForeignKey(KaseBO.INTCASENUM, CrimCaseBO.INTCASENUM)
//	       .addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
//	      
//	       .setResultContainer(new CaseHeaderDTO())
//	       .toString(PRINT_SQL)
//	       .search(KaseBO.INTCASENUM,KaseBO.LOCNCODE,KaseBO.COURTTYPE,KaseBO.CASENUM,KaseBO.CASETYPE,KaseBO.LOCALDEBTCOLL,KaseBO.DEBTCOLLECTION,CaseTypeBO.DESCR,      		 
//	      		 KaseBO.ASSNJUDGEID,CrimCaseBO.PROSECAGENCY);
//			 
//			List<CaseHeaderDTO>  results= (List<CaseHeaderDTO>) searchFactory.getResults();
			
			List<CaseHeaderDTO> results = new KaseBO(courtType)
    		.where(KaseBO.INTCASENUM, intCaseNum)
	        .includeTables(
	        	new CrimCaseBO(courtType).setOuter(),
	        	new CaseTypeBO(courtType)
	        )
	        .addForeignKey(KaseBO.INTCASENUM, CrimCaseBO.INTCASENUM)
	        .addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
	        .setResultContainer(new CaseHeaderDTO())
	        .toString(PRINT_SQL)
	        .searchAndGetResults(
	        	KaseBO.INTCASENUM, KaseBO.LOCNCODE, KaseBO.COURTTYPE, KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.LOCALDEBTCOLL,
	        	KaseBO.DEBTCOLLECTION, CaseTypeBO.DESCR, KaseBO.ASSNJUDGEID, CrimCaseBO.PROSECAGENCY
	        );
			 
			if (results != null && results.size() == 1) {
				caseHeaderDTO = results.get(0);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return caseHeaderDTO;
	}
	
	public static boolean isCrimCase(String courtType,int intCaseNum) throws Exception {
		Connection conn = "D".equalsIgnoreCase(courtType) ? DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_DB): DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_DB);
		try {
			String category = getCaseTypeCategory(conn, courtType, intCaseNum);
			if ("R".equalsIgnoreCase(category)) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
			conn = null;
		}
		return false;
	}
		
//	private static List<ArrayList<Integer>> getAccountNumSplitArrayLists(ArrayList<Integer> intCaseNumList) {
//		List<ArrayList<Integer>> splitNumList = new ArrayList<ArrayList<Integer>>();
//		if (intCaseNumList.size() < 501) {
//			splitNumList.add(intCaseNumList);
//		} else {
//			int count = 0;
//
//			int startElement = 0;
//			for (int i = 0; i < ((intCaseNumList.size()) / 500); i++) {
//				ArrayList<Integer> splitList = new ArrayList<Integer>();
//				startElement = count;
//				count = count + 500;
//
//				for (int j = startElement; j < count; j++) {
//					splitList.add(intCaseNumList.get(j));
//				}
//				splitNumList.add(splitList);
//			}
//			ArrayList<Integer> splitList = new ArrayList<Integer>();
//			if (count < intCaseNumList.size()) {
//				for (int j = count; j < intCaseNumList.size(); j++) {
//					splitList.add(intCaseNumList.get(j));
//				}
//				splitNumList.add(splitList);
//			}
//		}
//		return splitNumList;
//	}
}
