package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisAttorneyCasesReportCriteria;
import gov.utcourts.corisweb.report.CorisAttorneyCasesReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;


/**
* Servlet implementation class CorisAttorneyCasesServlet
*/
@WebServlet("/CorisAttorneyCasesServlet")
public class CorisAttorneyCasesServlet extends ReportBaseServlet {
	private static Logger logger = Logger.getLogger(CorisReportCaseStayLookupServlet.class.getName());
	
	public CorisAttorneyCasesServlet() {
		super();
	}
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisAttorneyCasesReportCriteria criteria = new CorisAttorneyCasesReportCriteria(request);
		criteria.setBarNum(URLEncryption.getParamAsInt(request, "barNum"));
		criteria.setBarState(URLEncryption.getParamAsString(request, "barState"));
		criteria.setCaseNum(URLEncryption.getParamAsString(request, "caseNum"));
		if ("on".equals(URLEncryption.getParamAsString(request, "checkboxIncludeDetached"))) {
			criteria.setIncludeDetached(true);
		} else {
			criteria.setIncludeDetached(false);
		}
		criteria.setAttachDatetimeStart(URLEncryption.getParamAsDate(request, "attachDatetimeStart"));
		criteria.setAttachDatetimeEnd(URLEncryption.getParamAsDate(request, "attachDatetimeEnd"));
		criteria.setCasesFiledFrom(URLEncryption.getParamAsDate(request, "caseFiledFrom"));
		criteria.setCasesFiledTo(URLEncryption.getParamAsDate(request, "caseFiledTo"));
		criteria.setReportFileName("AttorneyCases " + new Date());
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		List<AttyPartyBO> reportData = searchAttorneyCases((CorisAttorneyCasesReportCriteria) criteria);
		return new CorisAttorneyCasesReportGenerator(criteria).generateReport(reportData);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return searchAttorneyCases((CorisAttorneyCasesReportCriteria) criteria);
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisAttorneyCasesReportCriteria myCriteria = (CorisAttorneyCasesReportCriteria) generateReportCriteria(request);
			if (myCriteria.getCasesFiledFrom() == null || myCriteria.getCasesFiledTo() == null) {
				Calendar calcDate = Calendar.getInstance();
				myCriteria.setCasesFiledTo(calcDate.getTime());
				calcDate.add(Calendar.YEAR, -3);
				myCriteria.setCasesFiledFrom(calcDate.getTime());
			}
			request.setAttribute("myCriteria", myCriteria);
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", true);
			getAttorneyName(request);
			String mode = URLEncryption.getParamAsString(request, "mode");
			if ("searchAttorneyCases".equalsIgnoreCase(mode)) {
				request.setAttribute("attorneyCasesList", searchAttorneyCases(myCriteria));
				getLocnList(request, response, logName);
				getServletContext().getRequestDispatcher("/jsp/corisAttorneyCases.jsp").forward(request, response);
			} else if ("detach".equalsIgnoreCase(mode)) {
				detachAttorney(request, response, myCriteria);
			} else if ("save".equalsIgnoreCase(mode)){
				saveReport(request, response, myCriteria);
			} else if ("email".equalsIgnoreCase(mode)){
				emailReport(request, myCriteria);
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(retValObj.toString());
			}else{
				displaySearchPage(request, response);
			}
		} catch (Exception e) {
			logger.error("CorisAttorneyCasesServlet performTask(HttpServletRequest request, HttpServletResponse response", e);
		}
		
	}
	
	public void displaySearchPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String courtType = "";
			if (!TextUtil.isEmpty((String) request.getParameter("courtType"))) {
				courtType = (String) request.getParameter("courtType");
			} else {
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			String locnCode = "";
			if (!TextUtil.isEmpty((String) request.getParameter("locnCode"))) {
				locnCode = (String) request.getParameter("locnCode");
			} else {
				locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE);
			}
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			getLocnList(request, response, logName);
			getServletContext().getRequestDispatcher("/jsp/corisAttorneyCases.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("CorisAttorneyCasesServlet displaySearchPage(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	public void getLocnList(HttpServletRequest request, HttpServletResponse response, String logName) throws Exception {
		try {
			List<LocationBO> distLocationList = getLocations("D", logName);
			List<LocationBO> justLocationList = getLocations("J", logName);
			request.setAttribute("distLocationList", distLocationList);
			request.setAttribute("justLocationList", justLocationList);
			List<LocationBO> allLocations = new ArrayList(distLocationList);
			allLocations.addAll(new ArrayList(justLocationList));
			Collections.sort(allLocations, new Comparator<LocationBO>() {
				@Override
				public int compare(LocationBO s1, LocationBO s2) {
					return s1.getLocnDescr().compareTo(s2.getLocnDescr());
				}
			});
			request.setAttribute("locationList", allLocations);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static List<LocationBO> getLocations(String courtType, String logName) throws Exception {
		return new LocationBO(courtType).setOuter().orderBy(LocationBO.LOCNDESCR).includeTables(new PersonnelBO(courtType).where(PersonnelBO.COURTTYPE, courtType).where(PersonnelBO.LOGNAME, logName))
				.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE).search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR);
	}
	
	private List<AttyPartyBO> searchAttorneyCases(CorisAttorneyCasesReportCriteria criteria) throws Exception {
		List<AttyPartyBO> attyPartyCaseList = null;
		try{
			int barNum = criteria.getBarNum();
			String barState = criteria.getBarState();
			String caseNum = criteria.getCaseNum();
			Date attachDatetimeStart = null;
			if(criteria.getAttachDatetimeStart() != null){
				attachDatetimeStart = criteria.getAttachDatetimeStart();
			}
			Date attachDatetimeEnd = null;
			if(criteria.getAttachDatetimeEnd() != null){
				attachDatetimeEnd = criteria.getAttachDatetimeEnd();
			}
			Date caseFiledFrom = null;
			if (criteria.getCasesFiledFrom() != null){
				caseFiledFrom = criteria.getCasesFiledFrom();
			}
			Date caseFiledTo = null;
			if (criteria.getCasesFiledTo() != null){
				caseFiledTo = criteria.getCasesFiledTo();
			}
			if (attachDatetimeEnd != null) { //always add one date to the end date because it's setting the HH:MM:SS to 00:00:00 instead of 23:59:59
				attachDatetimeEnd = DateUtil.addDays(attachDatetimeEnd, 1);
			}
			
			AttyPartyBO attyPartyBO = new AttyPartyBO(criteria.getCourtType()).as("ap");
					attyPartyBO.includeFields(AttyPartyBO.ALL_FIELDS);
					if (!criteria.isIncludeDetached()) {
						attyPartyBO.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL);
					}
					attyPartyBO.where(AttyPartyBO.BARNUM, barNum);
					attyPartyBO.where(AttyPartyBO.BARSTATE, barState);
					if(attachDatetimeStart != null || attachDatetimeEnd != null){
						if(attachDatetimeStart != null && attachDatetimeEnd != null && attachDatetimeStart != attachDatetimeEnd){
							attyPartyBO.where(Exp.LEFT_PARENTHESIS, AttyPartyBO.ATTACHDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, attachDatetimeStart, Exp.AND, AttyPartyBO.ATTACHDATETIME, Exp.LESS_THAN_OR_EQUAL_TO, attachDatetimeEnd, Exp.RIGHT_PARENTHESIS);
						}else if(attachDatetimeStart != null){
							attyPartyBO.where(AttyPartyBO.ATTACHDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, attachDatetimeStart);
						}
					}
					
					PartyBO partyBO = new PartyBO(criteria.getCourtType()).as("p");
						partyBO.includeFields(PartyBO.FIRSTNAME, PartyBO.LASTNAME);
						
					KaseBO kaseBO = new KaseBO(criteria.getCourtType()).as("k");
						kaseBO.where(KaseBO.LOCNCODE, criteria.getLocnCode());
						kaseBO.where(KaseBO.COURTTYPE, criteria.getCourtType());
						if(!TextUtil.isEmpty(caseNum)){
							kaseBO.where(KaseBO.CASENUM, caseNum);
						}
							if(caseFiledFrom != caseFiledTo){
								kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.FILINGDATE, Exp.GREATER_THAN_OR_EQUAL_TO, caseFiledFrom, Exp.AND, KaseBO.FILINGDATE, Exp.LESS_THAN_OR_EQUAL_TO, caseFiledTo, Exp.RIGHT_PARENTHESIS);
							}else if(caseFiledFrom != null){
								kaseBO.where(AttyPartyBO.ATTACHDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, caseFiledFrom);
							}
					AttorneyBO attorneyBO = new AttorneyBO(criteria.getCourtType()).as("a");
						attorneyBO.setFieldAlias(AttorneyBO.LASTNAME, "attorney_last_name");
						attorneyBO.setFieldAlias(AttorneyBO.FIRSTNAME, "attorney_first_name");
						attorneyBO.where(AttorneyBO.BARNUM, barNum);
						attorneyBO.where(AttorneyBO.BARSTATE, barState);
						
					PersonnelBO personnelBO = new PersonnelBO(criteria.getCourtType());
						personnelBO.setFieldAlias(PersonnelBO.FIRSTNAME, "judge_first_name");
						personnelBO.setFieldAlias(PersonnelBO.LASTNAME, "judge_last_name");
						attyPartyBO.includeTables(
							partyBO,
							kaseBO,
							personnelBO,
							attorneyBO
						);
					attyPartyBO.addForeignKey(PersonnelBO.USERIDSRL, KaseBO.ASSNJUDGEID);
					attyPartyBO.addForeignKey(AttyPartyBO.PARTYNUM, PartyBO.PARTYNUM);
					attyPartyBO.addForeignKey(AttyPartyBO.INTCASENUM, KaseBO.INTCASENUM);
					attyPartyBO.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM);
					attyPartyBO.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE);
					attyPartyCaseList = attyPartyBO.search(
							AttyPartyBO.PARTYCODE,
							AttyPartyBO.ATTYPARTYID,
							AttyPartyBO.BARNUM,
							AttyPartyBO.BARSTATE,
							AttyPartyBO.INTCASENUM,
							AttyPartyBO.PARTYNUM,
							AttorneyBO.LASTNAME,
							AttorneyBO.FIRSTNAME,
							PartyBO.LASTNAME,
							PartyBO.FIRSTNAME,
							AttyPartyBO.CREATEDATETIME,
							AttyPartyBO.ATTACHDATETIME,
							AttyPartyBO.DETACHDATETIME,
							PersonnelBO.FIRSTNAME,
							PersonnelBO.LASTNAME,
							KaseBO.CASENUM,
							KaseBO.CASETYPE,
							KaseBO.FILINGDATE
							);
		} catch (Exception e){
			logger.error("CorisAttorneyCasesServlet attorneyCases(CorisAttorneyCasesReportCriteria criteria)", e);
		}
		return attyPartyCaseList;
	}
	
	private void emailReport(HttpServletRequest request, CorisAttorneyCasesReportCriteria criteria) throws Exception {
		String subject = "Cases For Attorney";
		String content = "Attached please find the document report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}
	
	public void detachAttorney(HttpServletRequest request, HttpServletResponse response, CorisAttorneyCasesReportCriteria criteria) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		int attyPartyId = TextUtil.getParamAsInt(request, "attyPartyId");
		Date currentDatetime = new Date();
		
		try{
			int userid = new PersonnelBO(criteria.getCourtType()).where(PersonnelBO.LOGNAME, criteria.getLogName()).find().getUseridSrl();
			AttyPartyBO attyPartyUpdateBO = new AttyPartyBO(criteria.getCourtType());
			attyPartyUpdateBO.setDetachDatetime(currentDatetime);
			attyPartyUpdateBO.setDetachUserid(userid);
			attyPartyUpdateBO.where(AttyPartyBO.ATTYPARTYID, attyPartyId);
			attyPartyUpdateBO.update();
			AttyPartyBO attyPartyInfoBO = new AttyPartyBO(criteria.getCourtType())
					.where(AttyPartyBO.ATTYPARTYID, attyPartyId)
					.find();			
			int count = new AttyPartyBO(criteria.getCourtType())
					.where(AttyPartyBO.INTCASENUM, attyPartyInfoBO.getIntCaseNum())
					.where(AttyPartyBO.PARTYNUM, attyPartyInfoBO.getPartyNum())
					.where(AttyPartyBO.PARTYCODE, attyPartyInfoBO.getPartyCode())
					.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
					.find(AttyPartyBO.INTCASENUM.count())
					.getCount();
			if(count == 0){
				new PartyCaseBO(criteria.getCourtType())
					.setProSe(null)
					.where(PartyCaseBO.INTCASENUM, attyPartyInfoBO.getIntCaseNum())
					.where(PartyCaseBO.PARTYCODE, attyPartyInfoBO.getPartyCode())
					.where(PartyCaseBO.PARTYNUM, attyPartyInfoBO.getPartyNum())
					.update();
			}
		} catch (Exception e){
			errorMessage = "There was an error and changes have not been saved.";
			logger.error("CorisAttorneyCasesServlet detachAttorney(HttpServletRequest request, HttpServletResponse response, String courtType, String logName)", e);
			e.printStackTrace();
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		}else{
			retValObj.put("error", false);
		}
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(retValObj.toString());
	}
	
	private void getAttorneyName(HttpServletRequest request) throws Exception {
		try{
			int barNum = TextUtil.getParamAsInt(request, "barNum");
			String barState = TextUtil.getParamAsString(request, "barState");
			List<AttorneyBO> attorneyName = new AttorneyBO("D")
					.includeFields(AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME)
					.where(AttorneyBO.BARNUM, barNum)
					.where(AttorneyBO.BARSTATE, barState)
					.search();
			request.setAttribute("attorneyName", attorneyName);			
		} catch (Exception e){
			logger.error("CorisAttorneyCasesServlet getAttorneyName(HttpServletRequest request)", e);
		}
	}
}