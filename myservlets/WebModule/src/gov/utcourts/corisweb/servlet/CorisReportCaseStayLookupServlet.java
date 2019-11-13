package gov.utcourts.corisweb.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.staytype.StayTypeBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportCaseStayLookupReportCriteria;
import gov.utcourts.corisweb.report.CorisReportCaseStayLookupReportGenerator;
import gov.utcourts.corisweb.report.DocumentReportSearchCriteria;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
 * Servlet implementation class CaseStayLookupServlet
 */
@WebServlet("/CorisReportCaseStayLookupServlet")
public class CorisReportCaseStayLookupServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static Logger logger = Logger.getLogger(CorisReportCaseStayLookupServlet.class.getName());

	public static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL);
	public static final Font FONT_BOLD = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD);
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorisReportCaseStayLookupServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportCaseStayLookupReportCriteria myCriteria = (CorisReportCaseStayLookupReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
 			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
 			JSONObject retValObj = new JSONObject();
 			retValObj.put("success", true);
			String mode = URLEncryption.getParamAsString(request, "mode");
			if ("changeJudgeList".equalsIgnoreCase(mode)) {
				changeJudgeList(request, response);
			} else if ("searchCaseStay".equalsIgnoreCase(mode)) {
				request.setAttribute("caseStayList", searchCaseStay(myCriteria));
				getLocnList(request, response, logName);
				getCaseTypeList(request, response);
				getStayReasonList(request, response);
				getServletContext().getRequestDispatcher("/jsp/corisReportCaseStayLookup.jsp").forward(request, response);
			} else if ("save".equalsIgnoreCase(mode)) {
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
			logger.error("CaseStayLookupServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
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
			getCaseTypeList(request, response);
			getStayReasonList(request, response);
			getServletContext().getRequestDispatcher("/jsp/corisReportCaseStayLookup.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("CaseStayLookupServlet displaySearchPage(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	public List<StayBO> searchCaseStay(CorisReportCaseStayLookupReportCriteria criteria) throws Exception {
		
		List<StayBO> caseStayList = new ArrayList<StayBO>();
		try {
			String courtType = "";
			if (!TextUtil.isEmpty(criteria.getCourtType())) {
				courtType = criteria.getCourtType();
			}
			String locnCode = "";
			if (!TextUtil.isEmpty(criteria.getLocnCode())) {
				locnCode = criteria.getLocnCode();
			}
			Date stayDateFrom = null;
			if (criteria.getStayDateFrom() != null) {
				stayDateFrom = criteria.getStayDateFrom();
			}
			Date stayDateTo = null;
			if (criteria.getStayDateTo() != null) {
				stayDateTo = criteria.getStayDateTo();
			}
			String caseType = "";
			if (!TextUtil.isEmpty(criteria.getCaseType())) {
				caseType = criteria.getCaseType();
			}
			
			
			String stayReason = "";
			if (!TextUtil.isEmpty(criteria.getStayReason())) {
				stayReason = criteria.getStayReason();
			}
			
			int judgeCommId = criteria.getJudgeCommId();
			
			StayBO stayBO = new StayBO(criteria.getCourtType()).as("s");
				stayBO.includeFields(StayBO.ALL_FIELDS);
				if (stayDateFrom != null) {
					stayBO.where(StayBO.BEGINDATE, Exp.GREATER_THAN_OR_EQUAL_TO, stayDateFrom);
					if(stayDateTo != null){
						stayBO.where(StayBO.BEGINDATE, Exp.LESS_THAN_OR_EQUAL_TO, stayDateTo);
					}else{
						Calendar calcDate = Calendar.getInstance();
						calcDate.setTimeInMillis(stayDateFrom.getTime());
						calcDate.add(Calendar.YEAR, 1);
						stayBO.where(StayBO.BEGINDATE, Exp.LESS_THAN_OR_EQUAL_TO, calcDate.getTime());
					}
					stayBO.where(Exp.LEFT_PARENTHESIS, StayBO.ENDDATE, Exp.IS_NULL, Exp.OR, StayBO.ENDDATE, Exp.GREATER_THAN_OR_EQUAL_TO, new Date(), Exp.RIGHT_PARENTHESIS);
				} else {
					Calendar calcDate = Calendar.getInstance();
					calcDate.add(Calendar.YEAR, -1);
					stayBO.where(StayBO.BEGINDATE, Exp.GREATER_THAN_OR_EQUAL_TO, calcDate.getTime());
					stayBO.where(Exp.LEFT_PARENTHESIS, StayBO.ENDDATE, Exp.IS_NULL, Exp.OR, StayBO.ENDDATE, Exp.GREATER_THAN_OR_EQUAL_TO, new Date(), Exp.RIGHT_PARENTHESIS);
				}
				
				if (!TextUtil.isEmpty(stayReason)) {
					stayBO.where(StayBO.STAYCODE, stayReason);
				}
				
			StayTypeBO stayTypeBO = new StayTypeBO(criteria.getCourtType()).as("st");
				stayTypeBO.includeFields(
						new FieldConstant("st.descr").as("stay_code_descr"));
			
			CaseTypeBO caseTypeBO = new CaseTypeBO(criteria.getCourtType()).as("ct");
				caseTypeBO.includeFields(CaseTypeBO.DESCR.as("case_type_descr"));
			
			KaseBO kaseBO = new KaseBO(criteria.getCourtType()).as("k");
				kaseBO.includeFields(KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.ASSNCOMMID, KaseBO.ASSNJUDGEID);
				if (!TextUtil.isEmpty(caseType)) {
					kaseBO.where(KaseBO.CASETYPE, caseType);
				}
				kaseBO.where(KaseBO.LOCNCODE, locnCode);
				kaseBO.where(KaseBO.COURTTYPE, criteria.getCourtType());
				if (judgeCommId > 0) {
					kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.ASSNCOMMID, Exp.EQUALS, judgeCommId, Exp.OR, KaseBO.ASSNJUDGEID, Exp.EQUALS, judgeCommId, Exp.RIGHT_PARENTHESIS);
				} else {
					kaseBO.where(PersonnelBO.TYPE, Exp.IN, new String[]{"J", "C"});
				}
			
			PersonnelBO personnelJudgeBO = new PersonnelBO(criteria.getCourtType()).as("judge").setOuter();
				personnelJudgeBO.includeFields(
						new FieldConstant("judge.first_name").as("judge_first_name"), 
						new FieldConstant("judge.last_name").as("judge_last_name"), 
						new FieldConstant("judge.userid_srl").as("judge_userid_srl"), 
						new FieldConstant("judge.logname").as("judge_logname")
					);
				
			PersonnelBO personnelCommissionerBO = new PersonnelBO(criteria.getCourtType()).as("commissioner").setOuter();
				personnelCommissionerBO.includeFields(
						new FieldConstant("commissioner.first_name").as("commissioner_first_name"),
						new FieldConstant("commissioner.last_name").as("commissioner_last_name"),
						new FieldConstant("commissioner.userid_srl").as("commissioner_userid_srl"),
						new FieldConstant("commissioner.logname").as("commissioner_logname")
					);
						
			stayBO.includeTables(kaseBO, stayTypeBO, personnelJudgeBO, personnelCommissionerBO, caseTypeBO);
			stayBO.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);
			stayBO.addForeignKey(KaseBO.INTCASENUM, StayBO.INTCASENUM);
			stayBO.addForeignKey(StayBO.STAYCODE, StayTypeBO.STAYCODE);
			stayBO.addForeignKey(new TableAndFieldDescriptor("k", KaseBO.ASSNCOMMID), new TableAndFieldDescriptor("commissioner", PersonnelBO.USERIDSRL));
			stayBO.addForeignKey(new TableAndFieldDescriptor("k", KaseBO.ASSNJUDGEID), new TableAndFieldDescriptor("judge", PersonnelBO.USERIDSRL));
			caseStayList = stayBO.search();
			
			courtType = null;
			locnCode = null;
			stayDateFrom = null;
			stayDateTo = null;
			caseType = null;
			stayReason = null;
			
		} catch (Exception e) {
			System.out.println("Exception in searchCaseStay: " + e.getMessage());
		}
		return caseStayList;
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
	
	public void getStayReasonList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String courtType = "";
			if (!TextUtil.isEmpty((String) request.getParameter("courtType"))) {
				courtType = (String) request.getParameter("courtType");
			} else {
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			request.setAttribute("courtType", courtType);
			
			List<StayTypeBO> results = new StayTypeBO(courtType).orderBy(StayTypeBO.DESCR).search();
			request.setAttribute("stayReasonList", results);
			results = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getCaseTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String courtType = "";
			if (!TextUtil.isEmpty((String) request.getParameter("courtType"))) {
				courtType = (String) request.getParameter("courtType");
			} else {
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			request.setAttribute("courtType", courtType);
			List<CaseTypeBO> results = new CaseTypeBO(courtType).orderBy(CaseTypeBO.DESCR).search();
			request.setAttribute("caseTypeList", results);
			results = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static List<LocationBO> getLocations(String courtType, String logName) throws Exception {
		return new LocationBO(courtType).setOuter().orderBy(LocationBO.LOCNDESCR).includeTables(new PersonnelBO(courtType).where(PersonnelBO.COURTTYPE, courtType).where(PersonnelBO.LOGNAME, logName))
				.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE).search(LocationBO.LOCNCODE, LocationBO.LOCNDESCR);
	}
	
	private void changeJudgeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			
			List<PersonnelBO> judgeAndComms = getJudgeCommList(courtType, locnCode);
			
			JSONArray retValArr = new JSONArray();
			for (PersonnelBO judgeComms : judgeAndComms) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("logName", judgeComms.getLogname());
				jsonObject.put("type", judgeComms.getType());
				jsonObject.put("useridSrl", judgeComms.getUseridSrl());
				jsonObject.put("firstName", judgeComms.getFirstName());
				jsonObject.put("lastName", judgeComms.getLastName());
				retValArr.add(jsonObject);
			}
			retValObj.put("judgeCommList", retValArr);
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private List<PersonnelBO> getJudgeCommList(String courtType, String locnCode) throws Exception {
			return new PersonnelBO(courtType)
				.where(PersonnelBO.TYPE, Exp.IN, new String[] {"J", "C"})
				.where(PersonnelBO.REMOVEDFLAG, "N")
				.where(PersonnelBO.COURTTYPE, courtType)
				.where(PersonnelBO.LOCNCODE, locnCode)
				.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME)
				.search(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LOGNAME, PersonnelBO.USERIDSRL, PersonnelBO.TYPE);
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportCaseStayLookupReportCriteria criteria) throws Exception {
		String subject = "Case Stay Report";
		String content = "Attached please find the document report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
		
	}
	
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportCaseStayLookupReportCriteria criteria = new CorisReportCaseStayLookupReportCriteria(request);
		criteria.setStayDateFrom(URLEncryption.getParamAsDate(request, "stayDateFrom"));
		criteria.setStayDateTo(URLEncryption.getParamAsDate(request, "stayDateTo"));
		criteria.setCaseType(URLEncryption.getParamAsString(request, "caseType"));
		criteria.setStayReason(URLEncryption.getParamAsString(request, "stayReason"));
		criteria.setJudgeCommId(URLEncryption.getParamAsInt(request, "judgeCommId"));
		criteria.setReportFileName("CaseStayReport " + new Date());
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		List<StayBO> reportData = searchCaseStay((CorisReportCaseStayLookupReportCriteria)criteria);
		return new CorisReportCaseStayLookupReportGenerator(criteria).generateReport(reportData);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return searchCaseStay((CorisReportCaseStayLookupReportCriteria)criteria);
	}
	
}
