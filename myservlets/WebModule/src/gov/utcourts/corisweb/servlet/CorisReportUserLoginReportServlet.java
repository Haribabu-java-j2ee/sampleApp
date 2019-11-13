package gov.utcourts.corisweb.servlet;

import java.io.IOException;
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

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisReportUserLoginReportCriteria;
import gov.utcourts.corisweb.report.CorisReportUserLoginReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;


/**
 * Servlet implementation class UserLoginReportServlet
 */
@WebServlet("/CorisReportUserLoginReportServlet")
public class CorisReportUserLoginReportServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static Logger logger = Logger.getLogger(CorisReportUserLoginReportServlet.class.getName());

	public static final Font FONT = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL);
	public static final Font FONT_BOLD = FontFactory.getFont(FontFactory.COURIER, 11, Font.BOLD);
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorisReportUserLoginReportServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisReportUserLoginReportCriteria myCriteria = (CorisReportUserLoginReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
			String logname = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			JSONObject retValObj = new JSONObject();
			retValObj.put("success", true);
			String mode = URLEncryption.getParamAsString(request, "mode");
			if ("changePersonnelList".equalsIgnoreCase(mode)) {
				changePersonnelList(request, response);
			} else if ("searchUserLogin".equalsIgnoreCase(mode)) {
				request.setAttribute("userLoginList", searchUserLogin(myCriteria));
				getLocnList(request, response, logname);
				request.setAttribute("personnelList", getPersonnelList(myCriteria.getCourtType(), myCriteria.getLocnCode(), myCriteria.getStatus()));
				getServletContext().getRequestDispatcher("/jsp/corisReportUserLoginReport.jsp").forward(request, response);
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
			logger.error("UserLoginReportServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	public void displaySearchPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CorisReportUserLoginReportCriteria myCriteria = (CorisReportUserLoginReportCriteria) generateReportCriteria(request);
			request.setAttribute("myCriteria", myCriteria);
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
			request.setAttribute("personnelList", getPersonnelList(myCriteria.getCourtType(), myCriteria.getLocnCode(), "A"));
			getServletContext().getRequestDispatcher("/jsp/corisReportUserLoginReport.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error("UserLoginReportServlet displaySearchPage(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	public List<PersonnelBO> searchUserLogin(CorisReportUserLoginReportCriteria criteria) throws Exception {
		
		List<PersonnelBO> userLoginList = new ArrayList<PersonnelBO>();
		try {
			String courtType = "";
			if (!TextUtil.isEmpty(criteria.getCourtType())) {
				courtType = criteria.getCourtType();
			}
			String locnCode = "";
			if (!TextUtil.isEmpty(criteria.getLocnCode())) {
				locnCode = criteria.getLocnCode();
			}
			Date loginFrom = null;
			if (criteria.getLoginFrom() != null) {
				loginFrom = criteria.getLoginFrom();
			}
			Date loginTo = null;
			if (criteria.getLoginTo() != null) {
				loginTo = criteria.getLoginTo();
			}
			if(loginTo != null){
				loginTo = DateUtil.addDays(loginTo, 1);
			}
			String status = criteria.getStatus();
			String selectedLogName = "";
			if (!TextUtil.isEmpty(criteria.getSelectedLogName())) {
				selectedLogName = criteria.getSelectedLogName();
			}
			
			PersonnelBO personnelBO = new PersonnelBO(criteria.getCourtType());
				personnelBO.includeFields(
						PersonnelBO.FIRSTNAME,
						PersonnelBO.LASTNAME,
						PersonnelBO.LOGNAME,
						PersonnelBO.LASTLOGIN,
						PersonnelBO.LOCNCODE,
						PersonnelBO.USERIDSRL,
						PersonnelBO.REMOVEDFLAG
						);
				personnelBO.where(PersonnelBO.COURTTYPE, courtType);
				personnelBO.where(PersonnelBO.LASTLOGIN, Exp.GREATER_THAN_OR_EQUAL_TO, loginFrom);
				personnelBO.where(PersonnelBO.LASTLOGIN, Exp.LESS_THAN_OR_EQUAL_TO, loginTo);
				if (!TextUtil.isEmpty(selectedLogName)) {
					personnelBO.where(PersonnelBO.LOGNAME, selectedLogName);
				}
				if(!TextUtil.isEmpty(locnCode)) {
					personnelBO.where(PersonnelBO.LOCNCODE, locnCode);
				}
				if("A".equals(status)) {
					personnelBO.where(PersonnelBO.REMOVEDFLAG, Exp.IN, new String[]{"N", "Y"});
				} else {
					personnelBO.where(PersonnelBO.REMOVEDFLAG, status);
				}
				
			ProfileBO profileBO = new ProfileBO(criteria.getCourtType());
				profileBO.includeFields(ProfileBO.COURTTITLE);
			personnelBO.includeTables(profileBO);
			personnelBO.addForeignKey(PersonnelBO.LOCNCODE, ProfileBO.LOCNCODE);
			userLoginList = personnelBO.search();
			
			courtType = null;
			locnCode = null;
			loginFrom = null;
			loginTo = null;
			status = null;
			
		} catch (Exception e) {
			System.out.println("Exception in searchUserLogin: " + e.getMessage());
		}
		return userLoginList;
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
	
	
	private List<PersonnelBO> getPersonnelList(String courtType, String locnCode, String status) throws Exception {
			List<PersonnelBO> personnelList = new ArrayList<PersonnelBO>();
			PersonnelBO personnelBO = new PersonnelBO(courtType);
			if("A".equals(status)){
				personnelBO.where(PersonnelBO.REMOVEDFLAG, Exp.IN, new String[]{"N", "Y"});
			} else {
				personnelBO.where(PersonnelBO.REMOVEDFLAG, status);
			}
			personnelBO.where(PersonnelBO.COURTTYPE, courtType);
			if(!TextUtil.isEmpty(locnCode)) {
				personnelBO.where(PersonnelBO.LOCNCODE, locnCode);
			}
			personnelBO.orderBy(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME);
			personnelList = personnelBO.setDistinct().search(PersonnelBO.LASTNAME, PersonnelBO.FIRSTNAME, PersonnelBO.LOGNAME);
			return personnelList;
	}
	
	private void changePersonnelList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject retValObj = new JSONObject();
			
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			String status = URLEncryption.getParamAsString(request, "status");
			String logName = URLEncryption.getParamAsString(request, "logName");
			
			List<PersonnelBO> personnelList = getPersonnelList(courtType, locnCode, status);
			
			JSONArray retValArr = new JSONArray();
			for (PersonnelBO personnel : personnelList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("logName", personnel.getLogname());
				jsonObject.put("firstName", personnel.getFirstName());
				jsonObject.put("lastName", personnel.getLastName());
				retValArr.add(jsonObject);
			}
			retValObj.put("personnelList", retValArr);
			
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
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void emailReport(HttpServletRequest request, CorisReportUserLoginReportCriteria criteria) throws Exception {
		String subject = "User Login Report";
		String content = "Attached please find the document report in " + criteria.getReportformat() + "format";
		byte[] rprtAttachment = this.generateReport(criteria);
		this.emailReport(subject, content, rprtAttachment, criteria);
	}
	
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisReportUserLoginReportCriteria criteria = new CorisReportUserLoginReportCriteria(request);
		criteria.setLoginFrom(URLEncryption.getParamAsDate(request, "loginFrom"));
		criteria.setLoginTo(URLEncryption.getParamAsDate(request, "loginTo"));
		criteria.setStatus(URLEncryption.getParamAsString(request, "status"));
		criteria.setSelectedLogName(URLEncryption.getParamAsString(request, "selectedLogName"));
		criteria.setReportFileName("UserLoginReport " + new Date());
		return criteria;
	}

	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		List<PersonnelBO> reportData = searchUserLogin((CorisReportUserLoginReportCriteria) criteria);
		return new CorisReportUserLoginReportGenerator(criteria).generateReport(reportData);
	}

	@Override
	List<?> getReportData(ReportBaseSearchCriteria criteria) throws Exception {
		return searchUserLogin((CorisReportUserLoginReportCriteria) criteria);
	}
	
}
