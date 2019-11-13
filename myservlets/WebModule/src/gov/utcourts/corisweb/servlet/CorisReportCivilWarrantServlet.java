package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.docissueparty.DocIssuePartyBO;
import gov.utcourts.coriscommon.dataaccess.docreturnparty.DocReturnPartyBO;
import gov.utcourts.coriscommon.dataaccess.document.DocumentBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.dto.CorisWarrantCivilDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisWarrantCivilReport;
import gov.utcourts.corisweb.report.CorisWarrantCivilReportSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportCivilWarrantServlet")
public class CorisReportCivilWarrantServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportCivilWarrantServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportCivilWarrant.jsp";
	public static final String RESULTS_PAGE = "/jsp/corisReportCivilWarrantResults.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportCivilWarrantServlet() {
	     super();
	}
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisWarrantCivilReportSearchCriteria criteria;
			
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
			logger.error("CorisAttorneyAttachDetachBatchServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
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
			request.setAttribute("warrantStatus", URLEncryption.getParamAsString(request, "warrantStatus"));

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
	
	protected void search(HttpServletRequest request, HttpServletResponse response, CorisWarrantCivilReportSearchCriteria criteria) throws ServletException, IOException {
		try {
			/////////////////////////////////////////////////////////////////////
			//get warrant data
			/////////////////////////////////////////////////////////////////////
			List<CorisWarrantCivilDTO> corisWarrantCivilListDTO = findReportDataResults(criteria);
			
			/////////////////////////////////////////////////////////////////////
			//get list of locations
			/////////////////////////////////////////////////////////////////////
			List<LocationBO> locationDistrictListBO = WebReportUtil.getLocationList(request, "D");
			List<LocationBO> locationJusticeListBO = WebReportUtil.getLocationList(request, "J");
			
			/////////////////////////////////////////////////////////////////////
			//return values screen setup
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", locationDistrictListBO);
			request.setAttribute("locationJusticeList", locationJusticeListBO);
			request.setAttribute("locnCode", criteria.getLocnCode());
			request.setAttribute("courtType", criteria.getCourtType());
			request.setAttribute("logName", criteria.getLogName());
			request.setAttribute("warrantStatus", criteria.getWarrantStatus());
			
			request.setAttribute("startDate", Constants.dateFormat.format(criteria.getStartDate()));
			request.setAttribute("endDate", Constants.dateFormat.format(criteria.getEndDate()));
	
			request.setAttribute("corisWarrantCivilListDTO", corisWarrantCivilListDTO);
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			locationDistrictListBO = null;
			locationJusticeListBO = null;
			corisWarrantCivilListDTO = null;
			

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
	private List<CorisWarrantCivilDTO> findReportDataResults(CorisWarrantCivilReportSearchCriteria warrantCivilReportSearchCriteria) throws Exception {
		List<CorisWarrantCivilDTO> corisWarrantCivilListDTO = new ArrayList<CorisWarrantCivilDTO>();
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(warrantCivilReportSearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			DocumentBO searchCriteria = new DocumentBO(warrantCivilReportSearchCriteria.getCourtType());
			// ///////////////////////////////////////////////////////////////
			// Get All ORder By
			// ///////////////////////////////////////////////////////////////
			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(warrantCivilReportSearchCriteria);
			if (orderByPairs != null) {
				for (OrderByPair orderByPair : orderByPairs) {
					switch(orderByPair.getColumnPosition()) {
						case 0: 
							searchCriteria.orderBy(KaseBO.CASENUM, orderByPair.getDirectionType()); 
							break;
						case 1: 
							searchCriteria.orderBy(PartyBO.LASTNAME, orderByPair.getDirectionType()); 
							break;
						case 2: 
							searchCriteria.orderBy(DocumentBO.CLERKID, orderByPair.getDirectionType()); 
							break;
						case 3: 
							searchCriteria.orderBy(WarrantBO.ISSUEDATE, orderByPair.getDirectionType()); 
							break;
						case 4: 
							searchCriteria.orderBy(WarrantBO.RECALLDATE, orderByPair.getDirectionType()); 
							break;
					}
				}
			}		
					
			orderByPairs = null;
			
			searchCriteria.includeTables(
					new DocumentBO(warrantCivilReportSearchCriteria.getCourtType()),
					new KaseBO(warrantCivilReportSearchCriteria.getCourtType()),
					new PartyCaseBO(warrantCivilReportSearchCriteria.getCourtType()),
					new PartyBO(warrantCivilReportSearchCriteria.getCourtType()),
					new PersonnelBO(warrantCivilReportSearchCriteria.getCourtType()),
					new DocumentBO(warrantCivilReportSearchCriteria.getCourtType())
			)
			.addForeignKey(DocumentBO.INTCASENUM, KaseBO.INTCASENUM)
			.addForeignKey(DocumentBO.INTCASENUM, PartyCaseBO.INTCASENUM)
			.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
			.addForeignKey(DocumentBO.CLERKID, PersonnelBO.USERIDSRL)
			.where(KaseBO.LOCNCODE, warrantCivilReportSearchCriteria.getLocnCode());
			
			
			if ("Issue".equals(warrantCivilReportSearchCriteria.getWarrantStatus())){
				searchCriteria.includeTables(			
						new DocIssuePartyBO(warrantCivilReportSearchCriteria.getCourtType()))
						.addForeignKey(DocumentBO.DOCUMENTNUM, DocIssuePartyBO.DOCUMENTNUM)
						.addForeignKey(DocIssuePartyBO.PARTYNUM, PartyCaseBO.PARTYNUM)
						.where(DocumentBO.CATEGORY,"I")								
						.where(DocumentBO.DETAILCODE,"I2")								
						.where(Exp.DATE,Exp.LEFT_PARENTHESIS,DocumentBO.CREATEDATETIME, Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, warrantCivilReportSearchCriteria.getStartDate(), Exp.AND, warrantCivilReportSearchCriteria.getEndDate())								
						.where(PartyCaseBO.WARRSTATUS,"C");								

						
			} else if ("Recall".equals(warrantCivilReportSearchCriteria.getWarrantStatus())){
				searchCriteria.includeTables(			
						new DocReturnPartyBO(warrantCivilReportSearchCriteria.getCourtType()))
						.addForeignKey(DocumentBO.DOCUMENTNUM, DocReturnPartyBO.DOCUMENTNUM)
						.addForeignKey(DocReturnPartyBO.PARTYNUM, PartyCaseBO.PARTYNUM)
						.where(Exp.DATE,Exp.LEFT_PARENTHESIS,DocumentBO.CREATEDATETIME,Exp.RIGHT_PARENTHESIS, Exp.BETWEEN, warrantCivilReportSearchCriteria.getStartDate(), Exp.AND, warrantCivilReportSearchCriteria.getEndDate())								
						.where(Exp.LEFT_PARENTHESIS, PartyCaseBO.WARRSTATUS, Exp.IS_NULL, Exp.OR, PartyCaseBO.WARRSTATUS, Exp.EQUALS, "", Exp.RIGHT_PARENTHESIS );								
			}
			
			searchCriteria.limit(0)
			.setReturnNull(true)
			.setResultContainer(new CorisWarrantCivilDTO());
			
			corisWarrantCivilListDTO = (List<CorisWarrantCivilDTO>) searchCriteria
			.searchAndGetResults(
					KaseBO.INTCASENUM.as("kaseIntCaseNum"), 
					KaseBO.LOCNCODE, 
					KaseBO.COURTTYPE, 
					KaseBO.CASENUM,
					PartyCaseBO.PARTYCODE.as("partyCode"),
					PartyBO.LASTNAME.as("partyLastName"),
					PartyBO.FIRSTNAME.as("partyFirstName"),
					DocumentBO.CREATEDATETIME.as("documentDate"),
					PersonnelBO.LASTNAME.as("clerkLastName"),
					PersonnelBO.FIRSTNAME.as("clerkFirstName"));

				
			/////////////////////////////////////////////////////////////////////
			//Close Connection
			/////////////////////////////////////////////////////////////////////
			conn.close();
			conn = null;
			
			/////////////////////////////////////////////////////////////////////
			//CleanUp
			/////////////////////////////////////////////////////////////////////
			searchCriteria = null;
			
		} catch (Exception e) {
			logger.error("getCorisCivilWarrantData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		return corisWarrantCivilListDTO;
	}

	
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisWarrantCivilDTO> corisWarrantCivilListDTO = (List<CorisWarrantCivilDTO>) getReportData(criteria);
		return new CorisWarrantCivilReport(criteria).generateReport(corisWarrantCivilListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisWarrantCivilReportSearchCriteria) criteria);
	}
	
	@Override
	CorisWarrantCivilReportSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisWarrantCivilReportSearchCriteria warrantCivilReportSearchCriteria = new CorisWarrantCivilReportSearchCriteria(request);
		warrantCivilReportSearchCriteria.setWarrantStatus(URLEncryption.getParamAsString(request, "warrantStatus"));
		warrantCivilReportSearchCriteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		warrantCivilReportSearchCriteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		
		StringBuilder fileName = new StringBuilder(); 
		
		fileName.append(warrantCivilReportSearchCriteria.getLocnDescr().toUpperCase());
		
		fileName.append(" CIVIL WARRANT ");
		fileName.append(warrantCivilReportSearchCriteria.getWarrantStatus().toUpperCase());
		fileName.append(" REPORT");

		warrantCivilReportSearchCriteria.setReportFileName(fileName.toString());
		fileName = null;
		
		return warrantCivilReportSearchCriteria;
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisWarrantCivilReportSearchCriteria criteria) throws Exception {
		String subject = "Coris Civil Warrant Report";
		String content = "Attached please find the civil warrant report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

}
