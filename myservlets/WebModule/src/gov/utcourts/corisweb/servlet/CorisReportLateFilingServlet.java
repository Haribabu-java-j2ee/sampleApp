package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.sentence.SentenceBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportLateFilingReportCriteria;
import gov.utcourts.corisweb.report.CorisReportLateFilingReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;


/**
* Servlet implementation class CorisAttorneyCasesServlet
*/
@WebServlet("/CorisReportLateFilingServlet")
public class CorisReportLateFilingServlet extends ReportBaseServlet {
	private static Logger logger = Logger.getLogger(CorisReportLateFilingServlet.class.getName());
	
	public CorisReportLateFilingServlet() {
		super();
	}
	
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		List<KaseBO> reportData = searchLateFiling((CorisReportLateFilingReportCriteria) criteria);
		return new CorisReportLateFilingReportGenerator(criteria).generateReport(reportData);
	}
	
	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return searchLateFiling((CorisReportLateFilingReportCriteria) criteria);
	}
	
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception { 
		CorisReportLateFilingReportCriteria criteria = new CorisReportLateFilingReportCriteria(request);
		criteria.setCaseFilingFrom(URLEncryption.getParamAsDate(request, "caseFilingFrom"));
		criteria.setCaseFilingTo(URLEncryption.getParamAsDate(request, "caseFilingTo"));
		criteria.setViolationDateFrom(URLEncryption.getParamAsDate(request, "violationDateFrom"));
		criteria.setViolationDateTo(URLEncryption.getParamAsDate(request, "violationDateTo"));
		criteria.setReportFileName("LateFilingReport " + new Date());
		return criteria;
	}

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportLateFilingReportCriteria myCriteria = (CorisReportLateFilingReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
			String mode = URLEncryption.getParamAsString(request, "mode");
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", true);
			if ("searchLateFiling".equalsIgnoreCase(mode)) {
				request.setAttribute("lateCaseFilingList", searchLateFiling(myCriteria));
				getLocnList(request, response, myCriteria.getLogName());
				getServletContext().getRequestDispatcher("/jsp/corisReportLateFiling.jsp").forward(request, response);
			}  else if ("save".equalsIgnoreCase(mode)) {
				saveReport(request, response, myCriteria);
			} else if ("email".equalsIgnoreCase(mode)) {
				emailReport(request, myCriteria);
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(retValObj.toString()); 
			} else {
				displaySearchPage(request, response);
			}
		} catch (Exception e) {
			logger.error("LateFilingServlet performTask(HttpServletRequest request, HttpServletResponse response", e);
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
			getServletContext().getRequestDispatcher("/jsp/corisReportLateFiling.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("LateFilingServlet displaySearchPage(HttpServletRequest request, HttpServletResponse response)", e);
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
	
	private List<KaseBO> searchLateFiling(CorisReportLateFilingReportCriteria criteria) throws Exception {
		List<KaseBO> lateCaseFilingList = null;
		try {
			Date caseFilingFrom = null;
			if (criteria.getCaseFilingFrom() != null) {
				caseFilingFrom = criteria.getCaseFilingFrom();
			}
			Date caseFilingTo = null;
			if (criteria.getCaseFilingTo() != null) {
				caseFilingTo = criteria.getCaseFilingTo();
			}
			Date violationDateFrom = null;
			if (criteria.getViolationDateFrom() != null) {
				violationDateFrom = criteria.getViolationDateFrom();
			}
			Date violationDateTo = null;
			if (criteria.getViolationDateTo() != null) {
				violationDateTo = criteria.getViolationDateTo();
			}
			
			KaseBO kaseBO = new KaseBO(criteria.getCourtType());
				kaseBO.includeFields(
						KaseBO.CASETYPE,
						KaseBO.CASENUM,
						KaseBO.DISPCODE,
						KaseBO.FILINGTYPE,
						KaseBO.FILINGDATE,
						KaseBO.NOTE,
						KaseBO.LOCNCODE,
						KaseBO.COURTTYPE
						);
				kaseBO.where(KaseBO.NOTE, Exp.IS_NOT_NULL);
				if (caseFilingFrom != null) {
					kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.FILINGDATE, Exp.GREATER_THAN_OR_EQUAL_TO, caseFilingFrom, Exp.AND, KaseBO.FILINGDATE, Exp.LESS_THAN_OR_EQUAL_TO, caseFilingTo, Exp.RIGHT_PARENTHESIS);
				}
				kaseBO.where(KaseBO.COURTTYPE, criteria.getCourtType());
				kaseBO.where(KaseBO.LOCNCODE, criteria.getLocnCode());
				
			ChargeBO chargeBO = new ChargeBO(criteria.getCourtType());
				chargeBO.includeFields(
						ChargeBO.VIOLDATETIME,
						ChargeBO.VIOLCODE,
						ChargeBO.JDMTCODE
						);
				if (violationDateFrom != null) {
					chargeBO.where(Exp.LEFT_PARENTHESIS, ChargeBO.VIOLDATETIME, Exp.GREATER_THAN_OR_EQUAL_TO, violationDateFrom, Exp.AND, ChargeBO.VIOLDATETIME, Exp.LESS_THAN_OR_EQUAL_TO, violationDateTo, Exp.RIGHT_PARENTHESIS);
				}
				chargeBO.where(ChargeBO.SEQ, Exp.EQUALS, Exp.select(
						new ChargeBO(criteria.getCourtType()).as("charge2")
						.min(ChargeBO.SEQ)
						.where(new TableAndFieldDescriptor("charge2", ChargeBO.INTCASENUM), Exp.EQUALS, KaseBO.INTCASENUM)));
				chargeBO.where(ChargeBO.INTCASENUM, Exp.NOT_IN, Exp.select(
						new SentenceBO(criteria.getCourtType())
						.includeFields(SentenceBO.INTCASENUM)
						.where(SentenceBO.INTCASENUM, Exp.EQUALS, ChargeBO.INTCASENUM)
						));
				chargeBO.where(ChargeBO.JDMTCODE, Exp.IS_NULL);
				chargeBO.where(ChargeBO.PLEACODE, Exp.IS_NULL);
				chargeBO.where(ChargeBO.PLEADATE, Exp.IS_NULL);
				chargeBO.where(Exp.DATE, Exp.LEFT_PARENTHESIS, ChargeBO.VIOLDATETIME, Exp.RIGHT_PARENTHESIS, Exp.LESS_THAN, KaseBO.FILINGDATE);
				chargeBO.where(ChargeBO.VIOLCODE, Exp.NOT_EQUALS, "X11");
				
			CrimCaseBO crimCaseBO = new CrimCaseBO(criteria.getCourtType());
				crimCaseBO.includeFields(
						CrimCaseBO.CITNUM,
						CrimCaseBO.LEA,
						CrimCaseBO.FTASTATUS
						);
				crimCaseBO.where(CrimCaseBO.DELINQNOTICEDATE, Exp.IS_NULL);
				
			PartyBO partyBO = new PartyBO(criteria.getCourtType());
				partyBO.includeFields(
						PartyBO.LASTNAME,
						PartyBO.FIRSTNAME,
						PartyBO.ADDRESS1,
						PartyBO.ADDRESS2,
						PartyBO.CITY,
						PartyBO.STATECODE,
						PartyBO.ZIPCODE
						);
				
			OffenseBO offenseBO = new OffenseBO(criteria.getCourtType());
				offenseBO.includeFields(OffenseBO.MANDAPPEARFLAG);
				
			kaseBO.includeTables(chargeBO, crimCaseBO, partyBO, offenseBO);
			kaseBO.addForeignKey(CrimCaseBO.INTCASENUM, KaseBO.INTCASENUM);
			kaseBO.addForeignKey(ChargeBO.INTCASENUM, KaseBO.INTCASENUM);
			kaseBO.addForeignKey(PartyBO.PARTYNUM, ChargeBO.PARTYNUM);
			kaseBO.addForeignKey(OffenseBO.OFFSVIOLCODE, ChargeBO.OFFSVIOLCODE);
			kaseBO.addForeignKey(OffenseBO.LASTEFFECTDATE, ChargeBO.OFFSEFFECTDATE);
			kaseBO.orderBy(KaseBO.CASENUM);
			
			lateCaseFilingList = kaseBO.search();
		} catch (Exception e){
			logger.error("LateFilingServlet searchLateFiling(LateFilingReportCriteria criteria)", e);
		}
		return lateCaseFilingList;
	}
	
	private void emailReport(HttpServletRequest request, CorisReportLateFilingReportCriteria criteria) throws Exception {
		String subject = "Late Filing Report";
		String content = "Attached please find the document report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}


}