package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.journal.JournalBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.dto.CorisWarrantWithPaymentsMadeDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisReportWarrantWithPaymentsMadeReport;
import gov.utcourts.corisweb.report.CorisReportWarrantWithPaymentsMadeSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportWarrantWithPaymentsMadeServlet")
public class CorisReportWarrantWithPaymentsMadeServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportWarrantServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportWarrantWithPaymentsMade.jsp";
	public static final String RESULTS_PAGE = "/jsp/corisReportWarrantWithPaymentsMadeResults.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportWarrantWithPaymentsMadeServlet() {
	     super();
	}
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportWarrantWithPaymentsMadeSearchCriteria criteria;
			
			PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
			
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", true);
			
			criteria = this.generateReportCriteria(request);
			
			switch (mode) {
				case SAVE:
					saveReport(request, response, criteria);
					break;
				case EMAIL:
					emailReport(request, criteria);
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(retValObj.toString());
					break;
				case FIND:
					search(request, response, criteria);
					request.getRequestDispatcher(RESULTS_PAGE).forward(request, response);
					break;
				default:
					String otherMode = URLEncryption.getParamAsString(request, "mode");
					if ("loadJudges".equals(otherMode)){
						getJudges(request, response);
					} else {
						screenSetUp(request, response);
					}
					break;
			}	
			mode = null;
		} catch (Exception e){
			logger.error("CorisAttorneyAttachDetachBatchServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
			// error handling
            JSONObject retValObj = new JSONObject();
            retValObj.put("success", false);
            retValObj.put("errorMessage", e);
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write(retValObj.toString());			
		}
	}
		
	protected void screenSetUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			if (TextUtil.isEmpty(locnCode)){
				locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE);
			}
			
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			if (TextUtil.isEmpty(courtType)){
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			
			/////////////////////////////////////////////////////////////////////
			//send to request
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", WebReportUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", WebReportUtil.getLocationList(request, "J"));
			request.setAttribute("locnCode", locnCode);
			request.setAttribute("courtType", courtType);
			request.setAttribute("logName", logName);
			
			getServletContext().getRequestDispatcher(SEARCH_PAGE).forward(request, response);
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			logName = null;
			locnCode = null;
			courtType = null;
			
		} catch (Exception e) {
			logger.error("performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response, CorisReportWarrantWithPaymentsMadeSearchCriteria criteria) throws ServletException, IOException {
		try {
			/////////////////////////////////////////////////////////////////////
			//return values screen setup
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", WebReportUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", WebReportUtil.getLocationList(request, "J"));
			request.setAttribute("locnCode", criteria.getLocnCode());
			request.setAttribute("courtType", criteria.getCourtType());
			request.setAttribute("logName", criteria.getLogName());
			request.setAttribute("warrantType", criteria.getWarrantType());
			request.setAttribute("judgeId",  String.valueOf(criteria.getJudge()));
			
			request.setAttribute("startDate", Constants.dateFormat.format(criteria.getStartDate()));
			request.setAttribute("endDate", Constants.dateFormat.format(criteria.getEndDate()));
			request.setAttribute("corisWarrantWithPaymentsMadeListDTO", findReportDataResults(criteria));
			
		} catch (Exception e) {
			logger.error("search(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
	}
	
	private void getJudges(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			JSONObject retValObj = new JSONObject();
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			PersonnelBO seachCriteria; 
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(courtType)){
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_DISTRICT_READONLY_DB);
				seachCriteria = new PersonnelBO("D_READONLY");
				
			} else {
				seachCriteria = new PersonnelBO("J_READONLY");
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			
			List<PersonnelBO> judgeListBO = seachCriteria
			.where(PersonnelBO.LOCNCODE, locnCode)
			.where(PersonnelBO.COURTTYPE, courtType)
			.where(PersonnelBO.TYPE, "J")
			.where(PersonnelBO.REMOVEDFLAG, "N")
			.orderBy(PersonnelBO.LASTNAME, DirectionType.ASC)
			.orderBy(PersonnelBO.FIRSTNAME, DirectionType.ASC)
			.limit(0)
			.setUseConnection(conn)
			.search(PersonnelBO.USERIDSRL, PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME);
	
			JSONArray retValArr = new JSONArray();
			for (PersonnelBO judge : judgeListBO) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("useridSrl", judge.getUseridSrl());
				jsonObject.put("firstName", judge.getFirstName());
				jsonObject.put("lastName", judge.getLastName());
				retValArr.add(jsonObject);
			}
			
			retValObj.put("judgeList", retValArr);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		
			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			retValObj = null;
			locnCode = null;
			courtType = null;
			seachCriteria = null;
			judgeListBO = null; 
				
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<CorisWarrantWithPaymentsMadeDTO> findReportDataResults(CorisReportWarrantWithPaymentsMadeSearchCriteria warrantReportSearchCriteria) throws Exception {
		List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO = new ArrayList<CorisWarrantWithPaymentsMadeDTO>();
		List<CorisWarrantWithPaymentsMadeDTO> returnCorisWarrantWithPaymentsMadeListDTO = new ArrayList<CorisWarrantWithPaymentsMadeDTO>();
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(warrantReportSearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			KaseBO searchCriteria = new KaseBO(warrantReportSearchCriteria.getCourtType());

			if ("Local".equals(warrantReportSearchCriteria.getWarrantType())){
				searchCriteria.where(WarrantBO.SWWSSTATUS,"L");								
				
			} else if ("State Wide".equals(warrantReportSearchCriteria.getWarrantType())){
				searchCriteria.where(WarrantBO.SWWSSTATUS,Exp.NOT_EQUALS, "L");								
			}
			
			if (warrantReportSearchCriteria.getJudge() > 0){
				searchCriteria
					.where(WarrantBO.ISSUEJUDGEID, warrantReportSearchCriteria.getJudge());
			}

			searchCriteria.includeTables(
					new KaseBO(warrantReportSearchCriteria.getCourtType()),
					new AccountBO(warrantReportSearchCriteria.getCourtType()),
					new TransDistBO(warrantReportSearchCriteria.getCourtType()),
					new JournalBO(warrantReportSearchCriteria.getCourtType()),
					new CrimCaseBO(warrantReportSearchCriteria.getCourtType())
			)
			.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
			.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
			.addForeignKey(TransDistBO.INTJOURNALNUM, JournalBO.INTJOURNALNUM)
			.addForeignKey(KaseBO.INTCASENUM, CrimCaseBO.INTCASENUM)
			.where(JournalBO.LOCNCODE, warrantReportSearchCriteria.getLocnCode())
			.where(JournalBO.COURTTYPE, warrantReportSearchCriteria.getCourtType())
			.where(Exp.LEFT_PARENTHESIS, 
					JournalBO.JOURNALDATE, 
					Exp.IS_NULL, 
					Exp.OR, 
					Exp.LEFT_PARENTHESIS,
						Exp.DATE,
						Exp.LEFT_PARENTHESIS,
						JournalBO.JOURNALDATE, 
						Exp.RIGHT_PARENTHESIS, 
						Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(), 
						Exp.AND, 
						warrantReportSearchCriteria.getEndDate(), 
						Exp.RIGHT_PARENTHESIS,
					Exp.RIGHT_PARENTHESIS)
			.limit(0)
			.setUnique()
			.setReturnNull(true)
			.setUseConnection(conn)
			.setResultContainer(new CorisWarrantWithPaymentsMadeDTO());
			
			corisWarrantWithPaymentsMadeListDTO = (List<CorisWarrantWithPaymentsMadeDTO>) searchCriteria
			.searchAndGetResults(
					KaseBO.INTCASENUM.as("kaseIntCaseNum"), 
					KaseBO.CASENUM,
					KaseBO.LOCNCODE,
					KaseBO.COURTTYPE,
					KaseBO.CASETYPE,
					CrimCaseBO.FTASTATUS);

			/////////////////////////////////////////////////////////////////////
			//Check Account Warrant Information
			/////////////////////////////////////////////////////////////////////
			StringBuilder status;
			
			for (CorisWarrantWithPaymentsMadeDTO corisWarrantWithPaymentsMadeDTO : corisWarrantWithPaymentsMadeListDTO){
				status = new StringBuilder();
				WarrantBO warrantBO = new WarrantBO(warrantReportSearchCriteria.getCourtType())
					.where(WarrantBO.INTCASENUM, corisWarrantWithPaymentsMadeDTO.getKaseIntCaseNum())
					.where(WarrantBO.ISSUEDATE, Exp.IS_NOT_NULL)
					.where(WarrantBO.WARRNUM, Exp.EQUALS, Exp.select(new WarrantBO("D").where(WarrantBO.INTCASENUM, corisWarrantWithPaymentsMadeDTO.getKaseIntCaseNum()).max(WarrantBO.WARRNUM)))
					.setReturnNull(true)
					.setUseConnection(conn)
					.find(WarrantBO.SWWSSTATUS, WarrantBO.PARTYNUM, WarrantBO.ISSUEJUDGEID, WarrantBO.ORDERDATE, WarrantBO.ISSUEDATE, WarrantBO.RECALLDATE, WarrantBO.EXPDATE);

				if (warrantBO != null){
					if (warrantBO.getIssueDate().equals(null)){
						continue;
					}	
					if (warrantBO.getOrderDate().equals(null) && TextUtil.isEmpty(corisWarrantWithPaymentsMadeDTO.getFtaStatus())){
						continue;
					}	
					
					
					int accountFound = new AccountBO(warrantReportSearchCriteria.getCourtType())
					.count(AccountBO.ACCTNUM)
					.where(AccountBO.INTCASENUM, corisWarrantWithPaymentsMadeDTO.getKaseIntCaseNum())
					.where(AccountBO.ACCTTYPE, Exp.IN, new StringArrayDescriptor("I", "F", "T"))
					.where(AccountBO.AMTDUE, Exp.GREATER_THAN, AccountBO.AMTPAID, Exp.PLUS, AccountBO.AMTCREDIT)
					.setUseConnection(conn)
					.find(AccountBO.NO_FIELDS)
					.getCount();
					
					
				    if (accountFound > 0){
				    	status.append("Partial ");
				    }
					
					if (warrantBO.getRecallDate() == null){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						warrantBO.setRecallDate(sdf.parse("3000-01-01"));
						sdf = null;
					}
					
					/////////////////////////////////////////////////////////////////////
					//Amount Paid Information
					/////////////////////////////////////////////////////////////////////
					AccountBO accountBO = new AccountBO(warrantReportSearchCriteria.getCourtType())
					.includeTables(
							new AccountBO(warrantReportSearchCriteria.getCourtType()).includeFields(TransBO.NO_FIELDS),
							new TransDistBO(warrantReportSearchCriteria.getCourtType()).includeFields(TransBO.NO_FIELDS),
							new TransBO(warrantReportSearchCriteria.getCourtType()).includeFields(TransBO.NO_FIELDS)
						)	
					.includeFields(
							new Expression(Exp.SUM, Exp.LEFT_PARENTHESIS, TransDistBO.AMTPAID,Exp.PLUS,TransDistBO.AMTCREDIT, Exp.RIGHT_PARENTHESIS).as("paidAmt")
					)		
					.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
					.addForeignKey(TransDistBO.INTJOURNALNUM, TransBO.INTJOURNALNUM)
					.addForeignKey(TransDistBO.TRANSNUM, TransBO.TRANSNUM)
					.where(AccountBO.INTCASENUM, corisWarrantWithPaymentsMadeDTO.getKaseIntCaseNum())
					.where(Exp.DATE,Exp.LEFT_PARENTHESIS,TransBO.TRANSDATETIME, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(), Exp.AND, warrantReportSearchCriteria.getEndDate())
					.where(Exp.DATE,Exp.LEFT_PARENTHESIS,TransBO.TRANSDATETIME, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, warrantBO.getIssueDate(), Exp.AND, warrantBO.getRecallDate())
					.where(TransDistBO.APPLYCODE, Exp.IS_NULL)
					.where(TransBO.OUTTYPE, Exp.IS_NULL)
					.where(Exp.LEFT_PARENTHESIS, AccountBO.ACCTTYPE, Exp.EQUALS, "I", 
  					       		Exp.OR,  
  					       			Exp.LEFT_PARENTHESIS, AccountBO.ACCTTYPE, Exp.EQUALS, "F", Exp.AND,
  					       				Exp.NOT, Exp.EXISTS, 
  					       						Exp.select(new AcctFeeBO(warrantReportSearchCriteria.getCourtType())
  					       								.where(AccountBO.ACCTNUM, Exp.EQUALS, AcctFeeBO.ACCTNUM)
  					       								.where(AcctFeeBO.FEECODE, Exp.IN, new StringArrayDescriptor("AC","CC","CE","CF","EC","EX","FC","OP","TP","VC"))),
  					       			Exp.RIGHT_PARENTHESIS,
  					       			
					       		Exp.OR,  
  					       			Exp.LEFT_PARENTHESIS, AccountBO.ACCTTYPE, Exp.EQUALS, "T", Exp.AND,
  					       				Exp.NOT, Exp.EXISTS, 
  					       						Exp.select(new AcctTrustBO(warrantReportSearchCriteria.getCourtType())
  					       								.where(AccountBO.ACCTNUM, Exp.EQUALS, AcctTrustBO.ACCTNUM)
  					       								.where(AcctTrustBO.TRUSTCODE, Exp.IN, new StringArrayDescriptor("BF","BR","O","RB","TR","UP"))),
  					       			Exp.RIGHT_PARENTHESIS,
  					       			
  					       	Exp.RIGHT_PARENTHESIS
					) 
					.setUseConnection(conn)
					.find();
					
					
					String amtPaid = (String) accountBO.get("paidAmt");
					
					if(TextUtil.isEmpty(amtPaid)){
						continue;
					}
					corisWarrantWithPaymentsMadeDTO.setAmtPaid(new BigDecimal(amtPaid));
					
					/////////////////////////////////////////////////////////////////////
					//Party Information
					/////////////////////////////////////////////////////////////////////
					PartyBO partyBO = new PartyBO(warrantReportSearchCriteria.getCourtType())
					.where(PartyBO.PARTYNUM, warrantBO.getPartyNum())
					.find(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
					
					corisWarrantWithPaymentsMadeDTO.setDefLastName(partyBO.getLastName());
					corisWarrantWithPaymentsMadeDTO.setDefFirstName(partyBO.getFirstName());
					
					/////////////////////////////////////////////////////////////////////
					//Judge Information
					/////////////////////////////////////////////////////////////////////
					PersonnelBO personnelBO = new PersonnelBO(warrantReportSearchCriteria.getCourtType())
					.where(PersonnelBO.USERIDSRL, warrantBO.getIssueJudgeId())
					.find(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME);
					
					corisWarrantWithPaymentsMadeDTO.setJudgeLastName(personnelBO.getLastName());
					corisWarrantWithPaymentsMadeDTO.setJudgeFirstName(personnelBO.getFirstName());
					
					/////////////////////////////////////////////////////////////////////
					//Set Status
					/////////////////////////////////////////////////////////////////////
					if ("R".equals(warrantBO.getSwwsStatus())){
				    	status.append("Warrant Flagged to Recall ");
					} else if ("A".equals(warrantBO.getSwwsStatus())){
				    	status.append("Warrant Flagged to Issue ");
					} else if ("S".equals(warrantBO.getSwwsStatus()) || 
							   "L".equals(warrantBO.getSwwsStatus()) ||
							   "M".equals(warrantBO.getSwwsStatus())){
								Date today = Calendar.getInstance().getTime();
								if (warrantBO.getRecallDate().compareTo(today) < 0){
									status.append("Warrant Recalled ");
								} else {
									status.append("Warrant Active ");
								}
								today = null;
					}
											
					
					
					if ("OM".equals(corisWarrantWithPaymentsMadeDTO.getFtaStatus()) ||
						"OA".equals(corisWarrantWithPaymentsMadeDTO.getFtaStatus())){
							status.append("FTA/FTC Ordered ");
					} else if ("IM".equals(corisWarrantWithPaymentsMadeDTO.getFtaStatus()) ||
							   "IA".equals(corisWarrantWithPaymentsMadeDTO.getFtaStatus())){
							status.append("FTA/FTC Issued ");
					} else if ("CL".equals(corisWarrantWithPaymentsMadeDTO.getFtaStatus())){
							status.append("FTA/FTC Cleared ");
					}	

					corisWarrantWithPaymentsMadeDTO.setStatus(status.toString());
					
					
					warrantBO = null;
					accountFound = 0;
					accountBO = null;
					partyBO = null;
					personnelBO = null;
					amtPaid = "";
					
					returnCorisWarrantWithPaymentsMadeListDTO.add(corisWarrantWithPaymentsMadeDTO);
				}
				
				corisWarrantWithPaymentsMadeDTO = null;
			}
				
			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			searchCriteria = null;
			
			corisWarrantWithPaymentsMadeListDTO = null;

			
		} catch (Exception e) {
			logger.error("getCorisWarrantData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		/////////////////////////////////////////////////////////////////////
		// Do a sort after the retrieve since some columns are not on the 
		// master retrieve
		/////////////////////////////////////////////////////////////////////
		List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(warrantReportSearchCriteria);
		if (orderByPairs != null) {
			for (final OrderByPair orderByPair : orderByPairs) {
				switch(orderByPair.getColumnPosition()) {
					case 0:
						
						Collections.sort(returnCorisWarrantWithPaymentsMadeListDTO, new Comparator<CorisWarrantWithPaymentsMadeDTO>(){
							@Override
							public int compare(CorisWarrantWithPaymentsMadeDTO s1, CorisWarrantWithPaymentsMadeDTO s2) {
								if("ASC".equals(orderByPair.getDirectionType().name())){
									return s1.getCaseNum().compareTo(s2.getCaseNum());
								} else {
									return s2.getCaseNum().compareTo(s1.getCaseNum());
								}
							}
						})
						;
						break;
					case 1: 
						Collections.sort(returnCorisWarrantWithPaymentsMadeListDTO, new Comparator<CorisWarrantWithPaymentsMadeDTO>(){
							@Override
							public int compare(CorisWarrantWithPaymentsMadeDTO s1, CorisWarrantWithPaymentsMadeDTO s2) {
								if("ASC".equals(orderByPair.getDirectionType().name())){
									return s1.getDefLastName().compareTo(s2.getDefLastName());
								} else {
									return s2.getDefLastName().compareTo(s1.getDefLastName());
								}
							}
						});
						break;
					case 2: 
						Collections.sort(returnCorisWarrantWithPaymentsMadeListDTO, new Comparator<CorisWarrantWithPaymentsMadeDTO>(){
							@Override
							public int compare(CorisWarrantWithPaymentsMadeDTO s1, CorisWarrantWithPaymentsMadeDTO s2) {
								if("ASC".equals(orderByPair.getDirectionType().name())){
									return s1.getAmtPaid().compareTo(s2.getAmtPaid());
								} else {
									return s2.getAmtPaid().compareTo(s1.getAmtPaid());
								}
							}
						});
						
						break;
					case 3: 
						Collections.sort(returnCorisWarrantWithPaymentsMadeListDTO, new Comparator<CorisWarrantWithPaymentsMadeDTO>(){
							@Override
							public int compare(CorisWarrantWithPaymentsMadeDTO s1, CorisWarrantWithPaymentsMadeDTO s2) {
								if("ASC".equals(orderByPair.getDirectionType().name())){
									return s1.getJudgeLastName().compareTo(s2.getJudgeLastName());
								} else {
									return s2.getJudgeLastName().compareTo(s1.getJudgeLastName());
								}
							}
						});
						
						break;
					case 4: 
						Collections.sort(returnCorisWarrantWithPaymentsMadeListDTO, new Comparator<CorisWarrantWithPaymentsMadeDTO>(){
							@Override
							public int compare(CorisWarrantWithPaymentsMadeDTO s1, CorisWarrantWithPaymentsMadeDTO s2) {
								if("ASC".equals(orderByPair.getDirectionType().name())){
									return s1.getStatus().compareTo(s2.getStatus());
								} else {
									return s2.getStatus().compareTo(s1.getStatus());
								}
							}
						});
						
						break;
				}
			}
		}		
				
		orderByPairs = null;
		
		return returnCorisWarrantWithPaymentsMadeListDTO;
	}
	
	@Override
	CorisReportWarrantWithPaymentsMadeSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportWarrantWithPaymentsMadeSearchCriteria warrantReportSearchCriteria = new CorisReportWarrantWithPaymentsMadeSearchCriteria(request);
		warrantReportSearchCriteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		warrantReportSearchCriteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		warrantReportSearchCriteria.setWarrantType(URLEncryption.getParamAsString(request, "warrantType"));
		warrantReportSearchCriteria.setJudge(URLEncryption.getParamAsInt(request, "judge"));
		
		
		StringBuilder fileName = new StringBuilder(); 
		
		fileName.append(warrantReportSearchCriteria.getLocnDescr().toUpperCase());
		
		if (!"All".equals(warrantReportSearchCriteria.getWarrantType())){
			fileName.append(warrantReportSearchCriteria.getWarrantType());
			fileName.append(" WARRANT REPORT");
		} else {
			fileName.append(" WARRANT REPORT");
		}

		warrantReportSearchCriteria.setReportFileName(fileName.toString());
		fileName = null;
		
		return warrantReportSearchCriteria;
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportWarrantWithPaymentsMadeSearchCriteria criteria) throws Exception {
		String subject = "Coris Warrant Report";
		String content = "Attached please find the warrant report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisWarrantWithPaymentsMadeDTO> corisWarrantListDTO = (List<CorisWarrantWithPaymentsMadeDTO>) getReportData(criteria);
		return new CorisReportWarrantWithPaymentsMadeReport(criteria).generateReport(corisWarrantListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisReportWarrantWithPaymentsMadeSearchCriteria) criteria);
	}
	
}
