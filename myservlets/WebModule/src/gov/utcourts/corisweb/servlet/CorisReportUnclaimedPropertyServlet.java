package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctbail.AcctBailBO;
import gov.utcourts.coriscommon.dataaccess.acctbond.AcctBondBO;
import gov.utcourts.coriscommon.dataaccess.accttrust.AcctTrustBO;
import gov.utcourts.coriscommon.dataaccess.checkdetl.CheckDetlBO;
import gov.utcourts.coriscommon.dataaccess.journal.JournalBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.trans.TransBO;
import gov.utcourts.coriscommon.dataaccess.transacct.TransAcctBO;
import gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO;
import gov.utcourts.coriscommon.dataaccess.trusttype.TrustTypeBO;
import gov.utcourts.coriscommon.dto.CorisUnclaimedPropertyDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisReportUnclaimedProperty;
import gov.utcourts.corisweb.report.CorisReportUnclaimedPropertySearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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

import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportUnclaimedPropertyServlet")
public class CorisReportUnclaimedPropertyServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportUnclaimedPropertyServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportUnclaimedProperty.jsp";
	public static final String RESULTS_PAGE = "/jsp/corisReportUnclaimedPropertyResults.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportUnclaimedPropertyServlet() {
	     super();
	}

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportUnclaimedPropertySearchCriteria criteria;
			
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
					screenSetUp(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			logger.error("CorisReportUnclaimedPropertyServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
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
	
	protected void search(HttpServletRequest request, HttpServletResponse response, CorisReportUnclaimedPropertySearchCriteria criteria) throws ServletException, IOException {
		try {
			/////////////////////////////////////////////////////////////////////
			//return values screen setup
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", WebReportUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", WebReportUtil.getLocationList(request, "J"));
			request.setAttribute("locnCode", criteria.getLocnCode());
			request.setAttribute("courtType", criteria.getCourtType());
			request.setAttribute("logName", criteria.getLogName());
			if (criteria.getStartDate() != null){
				request.setAttribute("startDate", Constants.dateFormat.format(criteria.getStartDate()));
			}
			if (criteria.getEndDate() != null){
				request.setAttribute("endDate", Constants.dateFormat.format(criteria.getEndDate()));
			}
			
			if (criteria.getStartJournal() > 0){
				request.setAttribute("startJournal", criteria.getStartJournal());
			}
			if (criteria.getEndJournal() > 0){
				request.setAttribute("endJournal", criteria.getEndJournal());
			}
	
			request.setAttribute("corisReportUnclaimedPropertyListDTO", findReportDataResults(criteria));
			
		} catch (Exception e) {
			logger.error("search(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<CorisUnclaimedPropertyDTO> findReportDataResults(CorisReportUnclaimedPropertySearchCriteria corisReportUnclaimedPropertySearchCriteria) throws Exception {
		List<CorisUnclaimedPropertyDTO> corisUnclaimedPropertyListDTO = new ArrayList<CorisUnclaimedPropertyDTO>();		
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(corisReportUnclaimedPropertySearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(KaseBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			if (corisReportUnclaimedPropertySearchCriteria.getStartDate() != null &&
				corisReportUnclaimedPropertySearchCriteria.getEndDate() != null){
				
				// ///////////////////////////////////////////////////////////////
				// Get Min Journal Number From Start Date
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor minJournalNumFO = new FieldOperationDescriptor(JournalBO.JOURNALNUM, FieldOperationType.MIN, new TypeInteger());
				JournalBO minJournalBO = new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.setFieldOperations(minJournalNumFO)
				.where(JournalBO.LOCNCODE, corisReportUnclaimedPropertySearchCriteria.getLocnCode())
				.where(JournalBO.COURTTYPE, corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.where(Exp.DATE, Exp.LEFT_PARENTHESIS, JournalBO.JOURNALDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, corisReportUnclaimedPropertySearchCriteria.getStartDate(), Exp.AND, corisReportUnclaimedPropertySearchCriteria.getEndDate())
				.where(Exp.DATE, Exp.LEFT_PARENTHESIS, JournalBO.JOURNALDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, corisReportUnclaimedPropertySearchCriteria.getStartDate(), Exp.AND, corisReportUnclaimedPropertySearchCriteria.getEndDate())
				.setUseConnection(conn)
				.find(JournalBO.JOURNALNUM);
				
				corisReportUnclaimedPropertySearchCriteria.setStartJournal((Integer) minJournalBO.get(minJournalNumFO));
				minJournalBO = null;
				minJournalNumFO = null;
				
				// ///////////////////////////////////////////////////////////////
				// Get Max Journal Number From End Date
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor maxJournalNumFO = new FieldOperationDescriptor(JournalBO.JOURNALNUM, FieldOperationType.MAX, new TypeInteger());
				JournalBO maxJournalBO = new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.setFieldOperations(maxJournalNumFO)
				.where(JournalBO.LOCNCODE, corisReportUnclaimedPropertySearchCriteria.getLocnCode())
				.where(JournalBO.COURTTYPE, corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.where(Exp.DATE, Exp.LEFT_PARENTHESIS, JournalBO.JOURNALDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, corisReportUnclaimedPropertySearchCriteria.getStartDate(), Exp.AND, corisReportUnclaimedPropertySearchCriteria.getEndDate())
				.where(Exp.DATE, Exp.LEFT_PARENTHESIS, JournalBO.JOURNALDATE, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, corisReportUnclaimedPropertySearchCriteria.getStartDate(), Exp.AND, corisReportUnclaimedPropertySearchCriteria.getEndDate())
				.setUseConnection(conn)
				.find(JournalBO.JOURNALNUM);
				
				corisReportUnclaimedPropertySearchCriteria.setEndJournal((Integer) maxJournalBO.get(maxJournalNumFO));
				maxJournalBO = null;
				maxJournalNumFO = null;
			}  else {
				// ///////////////////////////////////////////////////////////////
				// Get Min Journal Date From Journal Numbers
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor minStartDateFO = new FieldOperationDescriptor(JournalBO.JOURNALDATE, FieldOperationType.MIN, new TypeDate());
				JournalBO minStartDateBO = new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.setFieldOperations(minStartDateFO)
				.where(JournalBO.LOCNCODE, corisReportUnclaimedPropertySearchCriteria.getLocnCode())
				.where(JournalBO.COURTTYPE, corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.where(JournalBO.JOURNALNUM, Exp.GREATER_THAN_OR_EQUAL_TO, corisReportUnclaimedPropertySearchCriteria.getStartJournal())
				.where(JournalBO.JOURNALNUM, Exp.LESS_THAN_OR_EQUAL_TO, corisReportUnclaimedPropertySearchCriteria.getEndJournal())
				.setUseConnection(conn)
				.find(JournalBO.JOURNALDATE);
				
				corisReportUnclaimedPropertySearchCriteria.setStartDate((Date)minStartDateBO.get(minStartDateFO));
				minStartDateBO = null;
				minStartDateFO = null;
				
				// ///////////////////////////////////////////////////////////////
				// Get Max Journal Date From Journal Numbers
				// ///////////////////////////////////////////////////////////////
				FieldOperationDescriptor maxStartDateFO = new FieldOperationDescriptor(JournalBO.JOURNALDATE, FieldOperationType.MAX, new TypeDate());
				JournalBO maxJournalBO = new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.setFieldOperations(maxStartDateFO)
				.where(JournalBO.LOCNCODE, corisReportUnclaimedPropertySearchCriteria.getLocnCode())
				.where(JournalBO.COURTTYPE, corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.where(JournalBO.JOURNALNUM, Exp.GREATER_THAN_OR_EQUAL_TO, corisReportUnclaimedPropertySearchCriteria.getStartJournal())
				.where(JournalBO.JOURNALNUM, Exp.LESS_THAN_OR_EQUAL_TO, corisReportUnclaimedPropertySearchCriteria.getEndJournal())
				.setUseConnection(conn)
				.find(JournalBO.JOURNALDATE);
				
				corisReportUnclaimedPropertySearchCriteria.setEndDate((Date) maxJournalBO.get(maxStartDateFO));
				maxJournalBO = null;
				maxStartDateFO = null;
			}
			
			JournalBO searchCriteria = new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType());

			searchCriteria.includeTables(
					new JournalBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new TransBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new CheckDetlBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new AccountBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new AcctTrustBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
					new KaseBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
			)
			.addForeignKey(JournalBO.INTJOURNALNUM, TransBO.INTJOURNALNUM)
			.addForeignKey(TransBO.INTJOURNALNUM, CheckDetlBO.INTJOURNALNUM)
			.addForeignKey(TransBO.TRANSNUM, CheckDetlBO.TRANSNUM)
			.addForeignKey(TransBO.INTJOURNALNUM, TransDistBO.INTJOURNALNUM)
			.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
			.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
			.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
			.addForeignKey(KaseBO.INTCASENUM, AccountBO.INTCASENUM)
			.addForeignKey(AcctTrustBO.ACCTNUM, AccountBO.ACCTNUM)
			.where(AcctTrustBO.TRUSTCODE, "UP")
			.where(CheckDetlBO.VOIDDATE, Exp.IS_NULL)
			.where(JournalBO.LOCNCODE, corisReportUnclaimedPropertySearchCriteria.getLocnCode())
			.where(JournalBO.COURTTYPE, corisReportUnclaimedPropertySearchCriteria.getCourtType())
			.where(JournalBO.JOURNALNUM, Exp.BETWEEN, corisReportUnclaimedPropertySearchCriteria.getStartJournal(), Exp.AND, corisReportUnclaimedPropertySearchCriteria.getEndJournal())
			.limit(0)
			.setUseConnection(conn)
			.setResultContainer(new CorisUnclaimedPropertyDTO());

			corisUnclaimedPropertyListDTO = (List<CorisUnclaimedPropertyDTO>) searchCriteria
			.searchAndGetResults(JournalBO.JOURNALNUM,
								TransBO.TRANSNUM,
								AcctTrustBO.ACCTNUM.as("acctTrustAcctNum"),
								TransDistBO.AMTPAID,
								CheckDetlBO.CHECKNUM,
								KaseBO.CASENUM,
								JournalBO.INTJOURNALNUM,
								TransBO.TRANSDATETIME,
								KaseBO.LOCNCODE,
								KaseBO.COURTTYPE
								);
			
			for (CorisUnclaimedPropertyDTO corisUnclaimedPropertyDTO : corisUnclaimedPropertyListDTO ){
				
				TransDistBO transDistBO = new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.includeTables(
						new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
						new TransAcctBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
						new AccountBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				)
				.addForeignKey(TransDistBO.INTJOURNALNUM, TransAcctBO.INTJOURNALNUM)
				.addForeignKey(TransDistBO.TRANSNUM, TransAcctBO.TRANSNUM)
				.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
				.where(TransAcctBO.ACCTNUM, corisUnclaimedPropertyDTO.getAcctTrustAcctNum())
				.where(TransDistBO.ACCTNUM, Exp.NOT_EQUALS, corisUnclaimedPropertyDTO.getAcctTrustAcctNum())
				.setUseConnection(conn)
				.setReturnNull(true)
				.find(TransDistBO.ACCTNUM); 
				
				corisUnclaimedPropertyDTO.setOrigAccountNum(transDistBO.getAcctNum());
				
				AccountBO accountBO = new AccountBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				.includeTables(
						new AccountBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
						new TransAcctBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()),
						new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
				)
				.addForeignKey(TransDistBO.INTJOURNALNUM, TransAcctBO.INTJOURNALNUM)
				.addForeignKey(TransDistBO.TRANSNUM, TransAcctBO.TRANSNUM)
				.addForeignKey(AccountBO.ACCTNUM, TransDistBO.ACCTNUM)
				.where(TransAcctBO.ACCTNUM, corisUnclaimedPropertyDTO.getAcctTrustAcctNum())
				.where(TransDistBO.ACCTNUM, Exp.NOT_EQUALS, corisUnclaimedPropertyDTO.getAcctTrustAcctNum())
				.setUseConnection(conn)
				.setReturnNull(true)
				.find(AccountBO.ACCTTYPE, AccountBO.ACCTNUM); 
				
				if (accountBO != null){
					if ("B".equals(accountBO.getAcctType())){
						// ///////////////////////////////////////////////////////////////
						// Get Party Information
						// ///////////////////////////////////////////////////////////////
						PartyBO partyBO = new PartyBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.includeTables(
								new AcctBailBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						)
						.addForeignKey(PartyBO.PARTYNUM, AcctBailBO.POSTPARTYNUM)
						.where (AcctBailBO.ACCTNUM, corisUnclaimedPropertyDTO.getOrigAccountNum())
						.find(PartyBO.SSN, PartyBO.ADDRESS1, PartyBO.ADDRESS2, PartyBO.CITY, PartyBO.STATECODE, PartyBO.ZIPCODE);
						
						partyBO.setSsn(maskSsn(partyBO.getSsn()));
						
						// ///////////////////////////////////////////////////////////////
						// Get Description Information
						// ///////////////////////////////////////////////////////////////
						corisUnclaimedPropertyDTO.setAcctDescr("Bail");
						
						
						// ///////////////////////////////////////////////////////////////
						// Get Last TransDate
						// ///////////////////////////////////////////////////////////////
						TransBO lastTransDateBO = new TransBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.min(TransBO.TRANSDATETIME.as("transDatetimeMax"))
						.includeTables(
								new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()).includeFields(TransDistBO.NO_FIELDS)
						)
						.addForeignKey(TransBO.INTJOURNALNUM, TransDistBO.INTJOURNALNUM)
						.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
						.where(TransDistBO.ACCTNUM, corisUnclaimedPropertyDTO.getOrigAccountNum())
						.where(TransBO.OUTTYPE, Exp.IS_NULL)
						.where(TransDistBO.APPLYCODE, "BA")
						.find();
						
						corisUnclaimedPropertyDTO.setLastTransDateTime((Date)lastTransDateBO.get("transDatetimeMax"));
					}else if ("O".equals(accountBO.getAcctType())){
						// ///////////////////////////////////////////////////////////////
						// Get Party Information
						// ///////////////////////////////////////////////////////////////
						PartyBO partyBO = new PartyBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.includeTables(
								new AcctBondBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						)
						.addForeignKey(PartyBO.PARTYNUM, AcctBondBO.POSTPARTYNUM)
						.where (AcctBondBO.ACCTNUM, corisUnclaimedPropertyDTO.getOrigAccountNum())
						.find(PartyBO.SSN, PartyBO.ADDRESS1, PartyBO.ADDRESS2, PartyBO.CITY, PartyBO.STATECODE, PartyBO.ZIPCODE);
						
						partyBO.setSsn(maskSsn(partyBO.getSsn()));
						
						// ///////////////////////////////////////////////////////////////
						// Get Description Information
						// ///////////////////////////////////////////////////////////////
						corisUnclaimedPropertyDTO.setAcctDescr("Cash Bond");
						// ///////////////////////////////////////////////////////////////
						// Get Last TransDate
						// ///////////////////////////////////////////////////////////////
						TransBO lastTransDateBO = new TransBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.min(TransBO.TRANSDATETIME.as("transDatetimeMax"))
						.includeTables(
								new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()).includeFields(TransDistBO.NO_FIELDS)
						)
						.addForeignKey(TransBO.INTJOURNALNUM, TransDistBO.INTJOURNALNUM)
						.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
						.where(TransDistBO.ACCTNUM, corisUnclaimedPropertyDTO.getOrigAccountNum())
						.where(TransBO.OUTTYPE, Exp.IS_NULL)
						.where(TransDistBO.APPLYCODE, "OA")
						.find();
						
						corisUnclaimedPropertyDTO.setLastTransDateTime((Date)lastTransDateBO.get("transDatetimeMax"));
					}else if ("T".equals(accountBO.getAcctType())){
						// ///////////////////////////////////////////////////////////////
						// Get Party Information
						// ///////////////////////////////////////////////////////////////
						PartyBO partyBO = new PartyBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.includeTables(
								new AcctTrustBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						)
						.addForeignKey(PartyBO.PARTYNUM, AcctTrustBO.PAYEEPARTYNUM)
						.where (AcctTrustBO.ACCTNUM, accountBO.getAcctNum())
						.find(PartyBO.SSN, PartyBO.LASTNAME, PartyBO.FIRSTNAME, PartyBO.ADDRESS1, PartyBO.ADDRESS2, PartyBO.CITY, PartyBO.STATECODE, PartyBO.ZIPCODE);
						
						corisUnclaimedPropertyDTO.setLastName(partyBO.getLastName());
						corisUnclaimedPropertyDTO.setFirstName(partyBO.getFirstName());
						corisUnclaimedPropertyDTO.setSsn(maskSsn(partyBO.getSsn()));
						corisUnclaimedPropertyDTO.setSsn(maskSsn(partyBO.getSsn()));
						corisUnclaimedPropertyDTO.setAddress1(partyBO.getAddress1());
						corisUnclaimedPropertyDTO.setAddress2(partyBO.getAddress2());
						corisUnclaimedPropertyDTO.setCity(partyBO.getCity());
						corisUnclaimedPropertyDTO.setState(partyBO.getStateCode());
						corisUnclaimedPropertyDTO.setZip(partyBO.getZipCode());
						partyBO = null;
						
						// ///////////////////////////////////////////////////////////////
						// Get Trust Description Information
						// ///////////////////////////////////////////////////////////////
						TrustTypeBO trustTypeBO = new TrustTypeBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.includeTables(
								new AcctTrustBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						)
						.addForeignKey(AcctTrustBO.TRUSTCODE, TrustTypeBO.TRUSTCODE)
						.where (AcctTrustBO.ACCTNUM, accountBO.getAcctNum())
						.find(TrustTypeBO.DESCR);
						
						corisUnclaimedPropertyDTO.setAcctDescr(trustTypeBO.getDescr());
						trustTypeBO = null;
						
						
						// ///////////////////////////////////////////////////////////////
						// Get Last TransDate
						// ///////////////////////////////////////////////////////////////
						TransBO lastTransDateBO = new TransBO(corisReportUnclaimedPropertySearchCriteria.getCourtType())
						.max(TransBO.TRANSDATETIME.as("transDatetimeMax"))
						.includeTables(
								new TransDistBO(corisReportUnclaimedPropertySearchCriteria.getCourtType()).includeFields(TransDistBO.NO_FIELDS)
						)
						.addForeignKey(TransBO.INTJOURNALNUM, TransDistBO.INTJOURNALNUM)
						.addForeignKey(TransBO.TRANSNUM, TransDistBO.TRANSNUM)
						.where(TransDistBO.ACCTNUM, corisUnclaimedPropertyDTO.getOrigAccountNum())
						.where(TransBO.OUTTYPE, Exp.IS_NULL)
						.where(TransDistBO.APPLYCODE, Exp.IS_NULL)
						.find();
						
						corisUnclaimedPropertyDTO.setLastTransDateTime((Date)lastTransDateBO.get("transDatetimeMax"));
						
						lastTransDateBO = null;
					}
					corisUnclaimedPropertyDTO.setOrigAccountNum(transDistBO.getAcctNum());
					corisUnclaimedPropertyDTO.setAcctType(accountBO.getAcctType());
					
					corisUnclaimedPropertyListDTO.set(corisUnclaimedPropertyListDTO.indexOf(corisUnclaimedPropertyDTO ),corisUnclaimedPropertyDTO);
					
					corisUnclaimedPropertyDTO = null;
					transDistBO = null;
					accountBO = null;
				}
				
			}
			

			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			// Do a sort after the retrieve since some columns are not on the 
			// master retrieve
			/////////////////////////////////////////////////////////////////////
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(corisReportUnclaimedPropertySearchCriteria);
			if (orderByPairs != null) {
				for (final OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0:
							
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
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
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										return s1.getCheckNum().compareTo(s2.getCheckNum());
									} else {
										return s2.getCheckNum().compareTo(s1.getCheckNum());
									}
								}
							});
							break;
						case 2: 
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										return s1.getTransDateTime().compareTo(s2.getTransDateTime());
									} else {
										return s2.getTransDateTime().compareTo(s1.getTransDateTime());
									}
								}
							});
							
							break;
						case 3: 
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										if (TextUtil.isEmpty(s1.getFirstName())){
											return s1.getLastName().compareTo(s2.getLastName());
										} else {
											return (s1.getLastName() + " " + s1.getFirstName()).compareTo((s2.getLastName() + " " + s2.getFirstName()));
										}
										
									} else {
										if (TextUtil.isEmpty(s2.getFirstName())){
											return s2.getLastName().compareTo(s1.getLastName());
										} else{
											return (s2.getLastName() + " " + s2.getFirstName()).compareTo((s1.getLastName() + " " + s1.getFirstName()));
										}
									}
								}
							});
							
							break;
						case 4: 
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										return s1.getAmtPaid().compareTo(s2.getAmtPaid());
									} else {
										return s2.getAmtPaid().compareTo(s1.getAmtPaid());
									}
								}
							});
							
							break;
						case 5: 
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										return s1.getAcctDescr().compareTo(s2.getAcctDescr());
									} else {
										return s2.getAcctDescr().compareTo(s1.getAcctDescr());
									}
								}
							});
							
							break;
						case 6: 
							Collections.sort(corisUnclaimedPropertyListDTO, new Comparator<CorisUnclaimedPropertyDTO>(){
								@Override
								public int compare(CorisUnclaimedPropertyDTO s1, CorisUnclaimedPropertyDTO s2) {
									if("ASC".equals(orderByPair.getDirectionType().name())){
										return s1.getLastTransDateTime().compareTo(s2.getLastTransDateTime());
									} else {
										return s2.getLastTransDateTime().compareTo(s1.getLastTransDateTime());
									}
								}
							});
							
							break;
					}
				}
			}		
					
			/////////////////////////////////////////////////////////////////////
			//Clean Up
			/////////////////////////////////////////////////////////////////////
			orderByPairs = null;
			


		} catch (Exception e) {
			logger.error("getCorisUnclaimedPropertyDTOData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		return corisUnclaimedPropertyListDTO;
	}
	
	@Override
	CorisReportUnclaimedPropertySearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		
		CorisReportUnclaimedPropertySearchCriteria corisReportUnclaimedPropertySearchCriteria = new CorisReportUnclaimedPropertySearchCriteria(request);
		corisReportUnclaimedPropertySearchCriteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		corisReportUnclaimedPropertySearchCriteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		corisReportUnclaimedPropertySearchCriteria.setStartJournal(URLEncryption.getParamAsInt(request, "startJournal"));
		corisReportUnclaimedPropertySearchCriteria.setEndJournal(URLEncryption.getParamAsInt(request, "endJournal"));
		corisReportUnclaimedPropertySearchCriteria.setReportFileName(corisReportUnclaimedPropertySearchCriteria.getLocnDescr().toUpperCase());
		
		return corisReportUnclaimedPropertySearchCriteria;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportUnclaimedPropertySearchCriteria criteria) throws Exception {
		String subject = "Coris Money With Out A Case Report";
		String content = "Attached please find the MoneyWithOutACase report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisUnclaimedPropertyDTO> corisReportUnclaimedPropertyListDTO = (List<CorisUnclaimedPropertyDTO>) getReportData(criteria);
		return new CorisReportUnclaimedProperty(criteria).generateReport(corisReportUnclaimedPropertyListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisReportUnclaimedPropertySearchCriteria) criteria);
	}

	
	private String maskSsn(String ssn) throws Exception {
		String returnSsn = null;
		if (TextUtil.isEmpty(ssn)){
			returnSsn = "";
		} else if (ssn.length() > 4){
		    returnSsn = "***-**- " + ssn.substring(ssn.length() - 4);
		}else{
		    returnSsn = "***-**- " + ssn;
		}		
		return returnSsn;
	}
}
