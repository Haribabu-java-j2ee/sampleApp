package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.vehicle.VehicleBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.dto.CorisWarrantDTO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.container.OrderByPair;
import gov.utcourts.corisweb.report.CorisWarrantReport;
import gov.utcourts.corisweb.report.CorisWarrantReportSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.util.WebReportUtil;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
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

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;


@WebServlet("/CorisReportWarrantServlet")
public class CorisReportWarrantServlet extends ReportBaseServlet {
	private static final long serialVersionUID = -3335412659844L;
	private Connection conn = null;
	private static Logger logger = Logger.getLogger(CorisReportWarrantServlet.class.getName());

	public static final String SEARCH_PAGE = "/jsp/corisReportWarrant.jsp";
	public static final String RESULTS_PAGE = "/jsp/corisReportWarrantResults.jsp";
	
	/**
	  * @see HttpServlet#HttpServlet()
	*/
	public CorisReportWarrantServlet() {
	     super();
	}
	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisWarrantReportSearchCriteria criteria;
			
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
			
			String warrantStatus = URLEncryption.getParamAsString(request, "warrantStatus");
			
			/////////////////////////////////////////////////////////////////////
			//send to request
			/////////////////////////////////////////////////////////////////////
			request.setAttribute("locationDistrictList", WebReportUtil.getLocationList(request, "D"));
			request.setAttribute("locationJusticeList", WebReportUtil.getLocationList(request, "J"));
			request.setAttribute("locnCode", locnCode);
			request.setAttribute("courtType", courtType);
			request.setAttribute("logName", logName);
			request.setAttribute("warrantStatus", warrantStatus);
			request.setAttribute("address", false);
			request.setAttribute("charges", false);
			request.setAttribute("vehicle", false);
			
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
	
	protected void search(HttpServletRequest request, HttpServletResponse response, CorisWarrantReportSearchCriteria criteria) throws ServletException, IOException {
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
			request.setAttribute("warrantStatus", criteria.getWarrantStatus());
			request.setAttribute("judgeId",  String.valueOf(criteria.getJudge()));
			
			request.setAttribute("startDate", Constants.dateFormat.format(criteria.getStartDate()));
			request.setAttribute("endDate", Constants.dateFormat.format(criteria.getEndDate()));
			request.setAttribute("address", criteria.isAddress());
			request.setAttribute("charges", criteria.isCharges());
			request.setAttribute("vehicle", criteria.isVehicle());
	
			request.setAttribute("corisWarrantListDTO", findReportDataResults(criteria));
			
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
	private List<CorisWarrantDTO> findReportDataResults(CorisWarrantReportSearchCriteria warrantReportSearchCriteria) throws Exception {
		List<CorisWarrantDTO> corisWarrantListDTO = new ArrayList<CorisWarrantDTO>();
		try {
			
			/////////////////////////////////////////////////////////////////////
			//Connection
			/////////////////////////////////////////////////////////////////////
			if ("D".equals(warrantReportSearchCriteria.getCourtType())){;
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_DISTRICT_READONLY_DB);
			} else {
				conn = DatabaseConnection.getConnection(WarrantBO.CORIS_JUSTICE_READONLY_DB);
			}
			
			WarrantBO searchCriteria = new WarrantBO(warrantReportSearchCriteria.getCourtType());

			if ("Local".equals(warrantReportSearchCriteria.getWarrantType())){
				searchCriteria.where(WarrantBO.SWWSSTATUS,"L");								
				
			} else if ("State Wide".equals(warrantReportSearchCriteria.getWarrantType())){
				searchCriteria.where(WarrantBO.SWWSSTATUS,Exp.NOT_EQUALS, "L");								
			}
			
			if ("Expire".equals(warrantReportSearchCriteria.getWarrantStatus())){
				searchCriteria
					.where(WarrantBO.EXPDATE,Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(),Exp.AND, warrantReportSearchCriteria.getEndDate())
					.where(WarrantBO.RECALLDATE, Exp.GREATER_THAN_OR_EQUAL_TO, WarrantBO.EXPDATE);
			} else if ("Issue".equals(warrantReportSearchCriteria.getWarrantStatus())){
				searchCriteria
					.where(WarrantBO.ISSUEDATE,	Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(), Exp.AND, warrantReportSearchCriteria.getEndDate())								
					.where(WarrantBO.RECALLDATE, Exp.IS_NULL);
			} else if ("Order".equals(warrantReportSearchCriteria.getWarrantStatus())){
				searchCriteria
					.where(WarrantBO.ORDERDATE, Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(), Exp.AND, warrantReportSearchCriteria.getEndDate())								
					.where(WarrantBO.ISSUEDATE, Exp.IS_NULL)								
					.where(WarrantBO.RECALLDATE, Exp.IS_NULL);								
				
			} else if ("Recall".equals(warrantReportSearchCriteria.getWarrantStatus())){
				searchCriteria
					.where(WarrantBO.RECALLDATE, Exp.BETWEEN, warrantReportSearchCriteria.getStartDate(), Exp.AND, warrantReportSearchCriteria.getEndDate());								
			}
			
			if (warrantReportSearchCriteria.getJudge() > 0){
				searchCriteria
					.where(WarrantBO.ISSUEJUDGEID, warrantReportSearchCriteria.getJudge());
			}

			// ///////////////////////////////////////////////////////////////
			// Get All ORder By
			// ///////////////////////////////////////////////////////////////
			// order by
			List<OrderByPair> orderByPairs = WebReportUtil.getOrderByPairs(warrantReportSearchCriteria);
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
							searchCriteria.orderBy(PartyBO.BIRTHDATE, orderByPair.getDirectionType()); 
							break;
						case 3: 
							searchCriteria.orderBy(WarrantBO.BAILAMT, orderByPair.getDirectionType()); 
							break;
						case 4: 
							searchCriteria.orderBy(PersonnelBO.LASTNAME, orderByPair.getDirectionType()); 
							break;
						case 5: 
							searchCriteria.orderBy(WarrantBO.ORDERDATE, orderByPair.getDirectionType()); 
							break;
						case 6: 
							searchCriteria.orderBy(WarrantBO.ISSUEDATE, orderByPair.getDirectionType()); 
							break;
						case 7: 
							searchCriteria.orderBy(WarrantBO.EXPDATE, orderByPair.getDirectionType()); 
							break;
						case 8: 
							searchCriteria.orderBy(WarrantBO.RECALLDATE, orderByPair.getDirectionType()); 
							break;
					}
				}
			}		
					
			orderByPairs = null;
			
			searchCriteria.includeTables(
					new KaseBO(warrantReportSearchCriteria.getCourtType()),
					new PartyBO(warrantReportSearchCriteria.getCourtType()),
					new PersonnelBO(warrantReportSearchCriteria.getCourtType())
			)
			.addForeignKey(WarrantBO.INTCASENUM, KaseBO.INTCASENUM)
			.addForeignKey(WarrantBO.PARTYNUM, PartyBO.PARTYNUM)
			.addForeignKey(WarrantBO.ISSUEJUDGEID, PersonnelBO.USERIDSRL)
			.where(KaseBO.LOCNCODE, warrantReportSearchCriteria.getLocnCode())
			.limit(0)
			.setReturnNull(true)
			.setResultContainer(new CorisWarrantDTO());
			
			corisWarrantListDTO = (List<CorisWarrantDTO>) searchCriteria
			.searchAndGetResults(
					KaseBO.INTCASENUM.as("kaseIntCaseNum"), 
					KaseBO.LOCNCODE, 
					KaseBO.COURTTYPE, 
					KaseBO.CASENUM,
					PartyBO.LASTNAME.as("defLastName"),
					PartyBO.FIRSTNAME.as("defFirstName"),
					PartyBO.ADDRESS1.as("defAddress1"),
					PartyBO.ADDRESS2.as("defAddress2"),
					PartyBO.CITY.as("defCity"),
					PartyBO.STATECODE.as("defStateCode"),
					PartyBO.ZIPCODE.as("defzipCode"),
					PartyBO.BIRTHDATE,
					PersonnelBO.LASTNAME.as("judgeLastName"),
					PersonnelBO.FIRSTNAME.as("judgeFirstName"),
					WarrantBO.BAILAMT, 
					WarrantBO.ORDERDATE,
					WarrantBO.ISSUEDATE, 
					WarrantBO.RECALLDATE, 
					WarrantBO.EXPDATE);

				
			if (!TextUtil.isEmpty(warrantReportSearchCriteria.getReportformat())){
				if (warrantReportSearchCriteria.isVehicle() || warrantReportSearchCriteria.isCharges()){
					for (CorisWarrantDTO corisWarrantDTO :corisWarrantListDTO){
						if (warrantReportSearchCriteria.isVehicle()){
							
							
							VehicleBO vehicleBO = new VehicleBO(warrantReportSearchCriteria.getCourtType())
							.where (VehicleBO.INTCASENUM , corisWarrantDTO.getKaseIntCaseNum())
							.setUseConnection(conn)
							.setReturnNull(true)
							.find();
							
							if (vehicleBO != null){
								corisWarrantDTO.setVehicle(vehicleBO);
								corisWarrantListDTO.set(corisWarrantListDTO.indexOf(corisWarrantDTO), corisWarrantDTO);
							}
									
							vehicleBO = null;
						}
						
						if (warrantReportSearchCriteria.isCharges()){
							List<ChargeBO> chargeListBO = new ChargeBO(warrantReportSearchCriteria.getCourtType())
							.where (ChargeBO.INTCASENUM , corisWarrantDTO.getKaseIntCaseNum())
							.limit(0)
							.setReturnNull(true)
							.search();
							
							if (chargeListBO != null){
								corisWarrantDTO.setChargeList(chargeListBO);
								corisWarrantListDTO.set(corisWarrantListDTO.indexOf(corisWarrantDTO), corisWarrantDTO);
							}
							
							chargeListBO = null;
						}
						
						corisWarrantDTO =null;
					}
				}
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
			
		} catch (Exception e) {
			logger.error("getCorisWarrantData(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
		return corisWarrantListDTO;
	}
	
	@Override
	CorisWarrantReportSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisWarrantReportSearchCriteria warrantReportSearchCriteria = new CorisWarrantReportSearchCriteria(request);
		warrantReportSearchCriteria.setStartDate(URLEncryption.getParamAsDate(request, "startDate"));
		warrantReportSearchCriteria.setEndDate(URLEncryption.getParamAsDate(request, "endDate"));
		warrantReportSearchCriteria.setWarrantStatus(URLEncryption.getParamAsString(request, "warrantStatus"));
		warrantReportSearchCriteria.setWarrantType(URLEncryption.getParamAsString(request, "warrantType"));
		warrantReportSearchCriteria.setJudge(URLEncryption.getParamAsInt(request, "judge"));
		
		
		StringBuilder fileName = new StringBuilder(); 
		
		fileName.append(warrantReportSearchCriteria.getLocnDescr().toUpperCase());
		
		if (!"All".equals(warrantReportSearchCriteria.getWarrantType())){
			fileName.append(warrantReportSearchCriteria.getWarrantType());
			fileName.append(" WARRANT ");
			fileName.append(warrantReportSearchCriteria.getWarrantStatus().toUpperCase());
			fileName.append(" REPORT");
		} else {
			fileName.append(" WARRANT ");
			fileName.append(warrantReportSearchCriteria.getWarrantStatus().toUpperCase());
			fileName.append(" REPORT");
		}

		warrantReportSearchCriteria.setReportFileName(fileName.toString());
		fileName = null;
		
		if(!TextUtil.isEmpty(URLEncryption.getParameter(request, "address"))){
			warrantReportSearchCriteria.setAddress("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "address")));
		}
		
		if(!TextUtil.isEmpty(URLEncryption.getParameter(request, "charges"))){
			warrantReportSearchCriteria.setCharges("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "charges")));
		}
		
		if(!TextUtil.isEmpty(URLEncryption.getParameter(request, "vehicle"))){
			warrantReportSearchCriteria.setVehicle("on".equalsIgnoreCase(URLEncryption.getParamAsString(request, "vehicle")));
		}

		return warrantReportSearchCriteria;
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisWarrantReportSearchCriteria criteria) throws Exception {
		String subject = "Coris Warrant Report";
		String content = "Attached please find the warrant report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		@SuppressWarnings("unchecked")
		List<CorisWarrantDTO> corisWarrantListDTO = (List<CorisWarrantDTO>) getReportData(criteria);
		return new CorisWarrantReport(criteria).generateReport(corisWarrantListDTO);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return this.findReportDataResults((CorisWarrantReportSearchCriteria) criteria);
	}
}
