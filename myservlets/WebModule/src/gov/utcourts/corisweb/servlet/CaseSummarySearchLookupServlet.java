package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dto.CorisSingleReportParameters;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.corisinterface.report.CorisCaseHistorySingleReport;
import gov.utcourts.corisweb.report.CorisCaseHistoryAllReport;
import gov.utcourts.corisweb.report.CorisSummaryEventReport;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereCustomDescriptor;
import gov.utcourts.courtscommon.util.TextUtil;
import gov.utcourts.notifications.util.CorisNotificationEmailCommon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class CaseSummarySearchLookupServlet
*/
@WebServlet("/CaseSummarySearchLookupServlet")
public class CaseSummarySearchLookupServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CaseSummarySearchLookupServlet.class.getName());
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CaseSummarySearchLookupServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String page = "";
			String courtType = URLEncryption.getParamAsString(request, "courtType"); //NOTE courtType can be "D" or "J" or "both"
			String logName = URLEncryption.getParamAsString(request, "logName", (String) request.getParameter("logName"));
			PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));
			String locnCode = URLEncryption.getParamAsString(request, "locnCode");
			if(locnCode != null && !"".equals(locnCode)){
				request.setAttribute("locnCode", locnCode);
			}
			if (TextUtil.isEmpty(courtType)){
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
			}
			if (TextUtil.isEmpty(logName)){
				logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			}
			if(logName == null || "".equals(logName)) {
				page = "/login.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
			} else {
				switch (mode) {
					case GETLOCATIONSLIST:	 			
						getLocationsList(request, response, logName);			
						break;
					case GETCASESLIST:				
						getCasesList(request, response, courtType, logName, locnCode);			
						break;
					case GETEVENTS:				
						getEvents(request, response);
						break;
					case GETPDF:				
						getPDF(request, response);			
						break;
					case GETSINGLEPDF:				
						getSinglePDF(request, response);			
						break;
					default:
						displaySearch(request, response);
						break;
				}				
			}
			//cleanup
			courtType = null;
			logName = null;
			mode = null;
			page = null;
			locnCode = null;
		} catch (Exception e){
			logger.error("performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	public void displaySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "/jsp/caseSummarySearchLookup.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			logger.error("displaySearch(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

	public void getLocationsList(HttpServletRequest request, HttpServletResponse response, String logName) throws ServletException, IOException {
		try {
			// get only the locations available to the logged in person
			JSONArray retValArr = new JSONArray();
			JSONObject retValObj = new JSONObject();
			JSONObject jsonObject = null;
			// District
			List<PersonnelBO> resultsD = new PersonnelBO("D")
					.where(PersonnelBO.COURTTYPE, "D")
					.where(PersonnelBO.LOGNAME, logName)
					.includeTables(new LocationBO("D").setOuter().orderBy(LocationBO.LOCNDESCR))
					.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE)
					.search(PersonnelBO.COURTTYPE, PersonnelBO.LOCNCODE, PersonnelBO.LOGNAME, PersonnelBO.DEFLTLOGIN, LocationBO.LOCNDESCR);
			for (PersonnelBO personnelBO : resultsD) {
				jsonObject = new JSONObject();
				jsonObject.put("courtType", personnelBO.getCourtType());
				jsonObject.put("locnCode", personnelBO.getLocnCode());
				jsonObject.put("logName", personnelBO.getLogname());
				jsonObject.put("defltLogin", personnelBO.getDefltLogin());
				jsonObject.put("locnDescr", (String) personnelBO.get(LocationBO.LOCNDESCR));
				retValArr.add(jsonObject);
			}
			resultsD = null;
			// Justice
			List<PersonnelBO> resultsJ = new PersonnelBO("J")
					.where(PersonnelBO.COURTTYPE, "J")
					.where(PersonnelBO.LOGNAME, logName)
					.includeTables(new LocationBO("J").setOuter().orderBy(LocationBO.LOCNDESCR))
					.addForeignKey(PersonnelBO.LOCNCODE, LocationBO.LOCNCODE)
					.search(PersonnelBO.COURTTYPE, PersonnelBO.LOCNCODE, PersonnelBO.LOGNAME, PersonnelBO.DEFLTLOGIN, LocationBO.LOCNDESCR);
			for (PersonnelBO personnelBO : resultsJ) {
				jsonObject = new JSONObject();
				jsonObject.put("courtType", personnelBO.getCourtType());
				jsonObject.put("locnCode", personnelBO.getLocnCode());
				jsonObject.put("logName", personnelBO.getLogname());
				jsonObject.put("defltLogin", personnelBO.getDefltLogin());
				jsonObject.put("locnDescr", (String) personnelBO.get(LocationBO.LOCNDESCR));
				retValArr.add(jsonObject);
			}
			resultsJ = null;

			retValObj.put("personnelLocationList", retValArr);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
			jsonObject = null;
			retValObj = null;
			retValArr = null;
		} catch (Exception e) {
			logger.error("getLocationsList(HttpServletRequest request, HttpServletResponse response, String logName)", e);
		}
	}
	
	public void getCasesList(HttpServletRequest request, HttpServletResponse response, String courtType, String logName, String locnCode) throws ServletException, IOException {
		try {
			boolean inquiryUser = false;
			boolean locnAccess = false;
			boolean caseAccess = false;
			boolean privateProtectedAccess = false;
			boolean expungedAccess = false;
			boolean mentalAccess = false;
			boolean sealedAccess = false;
			boolean safeGuardedAccess = false;
			String caseSecurity;
			
			String caseNum = URLEncryption.getParamAsString(request, "caseNum");
			String lastName = URLEncryption.getParamAsString(request,"lastName").toUpperCase();
			String firstName = URLEncryption.getParamAsString(request,"firstName").toUpperCase();
			if(!"".equals(logName) && !"".equals(locnCode) && !"".equals(courtType)){
				/////////////////////////////////////////////////////
				// User Security
				/////////////////////////////////////////////////////
				String courtTypeTemp = "";
				if("both".equals(courtType)) { courtTypeTemp = "D"; } else if("J".equals(courtType)) { courtTypeTemp = "J"; }
				inquiryUser = CorisSecurityCommon.checkSecurityAccess(logName, "Inquirylogon", locnCode, courtTypeTemp, null);
				locnAccess = CorisSecurityCommon.checkLocationAccess(logName, locnCode, courtTypeTemp, null);
				if (!locnAccess) {
					inquiryUser = true; 
				}
			}
			//get the list of cases (we already know the caseNum but there might be multiple locations)
			if(!TextUtil.isEmpty(caseNum) || !TextUtil.isEmpty(locnCode) || !TextUtil.isEmpty(lastName)) {

				List<KaseBO> casesList = new ArrayList<KaseBO>();
				if("both".equals(courtType) || "D".equals(courtType)) {
					KaseBO kaseBO = new KaseBO("D");
					kaseBO.limit(Constants.MAX_RESULTS);
					if(!TextUtil.isEmpty(caseNum)) {
						kaseBO.where(KaseBO.CASENUM, caseNum);
					}
					if(!TextUtil.isEmpty(locnCode)) {
						kaseBO.where(KaseBO.LOCNCODE, locnCode);
					}
					kaseBO.includeTables(new LocationBO("D").setOuter());
					kaseBO.addForeignKey(KaseBO.LOCNCODE, LocationBO.LOCNCODE);
					kaseBO.includeTables(new CaseTypeBO("D").setOuter());
					kaseBO.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);
					if(!TextUtil.isEmpty(lastName)) {
						kaseBO.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM);
						kaseBO.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM);
						kaseBO.includeTables(new PartyCaseBO("D"));
						kaseBO.includeTables(new PartyBO("D"));
						kaseBO.addWhereDescriptors(new WhereCustomDescriptor("t5.last_name MATCHES '"+lastName+"'"));
						if(!TextUtil.isEmpty(firstName)) {
							kaseBO.addWhereDescriptors(new WhereCustomDescriptor("t5.first_name MATCHES '"+firstName+"'"));
						}
					}
					List tempList = kaseBO.search();
					casesList.addAll(tempList);
					kaseBO = null;
					tempList = null;
				}
				if("both".equals(courtType) || "J".equals(courtType)) {
					KaseBO kaseBO = new KaseBO("J");
					kaseBO.limit(Constants.MAX_RESULTS);
					if(!TextUtil.isEmpty(caseNum)) {
						kaseBO.where(KaseBO.CASENUM, caseNum);
					}
					if(!TextUtil.isEmpty(locnCode)) {
						kaseBO.where(KaseBO.LOCNCODE, locnCode);
					}
					kaseBO.includeTables(new LocationBO("J").setOuter());
					kaseBO.addForeignKey(KaseBO.LOCNCODE, LocationBO.LOCNCODE);
					kaseBO.includeTables(new CaseTypeBO("J").setOuter());
					kaseBO.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);
					if(!TextUtil.isEmpty(lastName)) {
						kaseBO.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM);
						kaseBO.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM);
						kaseBO.includeTables(new PartyCaseBO("J"));
						kaseBO.includeTables(new PartyBO("J"));
						kaseBO.addWhereDescriptors(new WhereCustomDescriptor("t5.last_name MATCHES '"+lastName+"'"));
						if(!TextUtil.isEmpty(firstName)) {
							kaseBO.addWhereDescriptors(new WhereCustomDescriptor("t5.first_name MATCHES '"+firstName+"'"));
						}
					}
					List tempList = kaseBO.search();
					casesList.addAll(tempList);
					kaseBO = null;
					tempList = null;
				}
				
				for (KaseBO kaseBO: casesList) {
					privateProtectedAccess = false;
					sealedAccess= false;
					expungedAccess= false;
					mentalAccess= false;
					safeGuardedAccess= false;
					
					if ("IC".equals(kaseBO.getCaseType()) || "IS".equals(kaseBO.getCaseType())|| "OU".equals(kaseBO.getCaseType())){
						mentalAccess = CorisSecurityCommon.checkSecurityAccess(logName, "mental", kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						caseAccess = CorisSecurityCommon.grantAccess(logName, kaseBO.getCaseSecurity(), kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						if (!mentalAccess || !caseAccess){
							casesList.set(casesList.indexOf(kaseBO), kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists"));
						}
					} else if ("AD".equals(kaseBO.getCaseType()) || "GA".equals(kaseBO.getCaseType())){;
						caseAccess = CorisSecurityCommon.grantAccess(logName, kaseBO.getCaseSecurity(), kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						if (inquiryUser || !caseAccess){
							casesList.set(casesList.indexOf(kaseBO), kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists"));
						}
					} else if ("S".equals(kaseBO.getCaseSecurity())){
						sealedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "sealed", kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						if (!sealedAccess){
							casesList.set(casesList.indexOf(kaseBO), kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists"));
						}
					} else if ("X".equals(kaseBO.getCaseSecurity())){
						expungedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "expunged", kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						if (!expungedAccess){
							casesList.set(casesList.indexOf(kaseBO), kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists"));
						}
//					} else if ("V".equals(kaseBO.getCaseSecurity())){
//						privateProtectedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "privateprotected", kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
//						if (!privateProtectedAccess){
//							kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists");
//							//iter.remove();
//						}
					} else if ("O".equals(kaseBO.getCaseSecurity())){
						privateProtectedAccess = CorisSecurityCommon.checkSecurityAccess(logName, "privateprotected", kaseBO.getLocnCode(), kaseBO.getCourtType(), null);
						if (!privateProtectedAccess){
							casesList.set(casesList.indexOf(kaseBO), kaseBO.setCaseNum(kaseBO.getCaseNum() + " Case Exists"));
						}
					}
					//cleanup
					kaseBO = null;
				}

				request.setAttribute("casesList", casesList);
				
				
				//cleanup
				casesList = null;
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", TextUtil.isEmpty(e.getMessage()) ? "Null Error Occurred": e.getMessage());
			logger.error("getCasesList(HttpServletRequest request, HttpServletResponse response, String courtType, String logName, String locnCode)", e);
		}
		String page = "/jsp/caseSummarySearchLookupCases.jsp";
		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}

	public void getEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CorisSummaryEventReport.find(request, response);
			String page = "/jsp/caseSummarySearchLookupDetail.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			logger.error("getEvents(HttpServletRequest request, HttpServletResponse response)", e);

		}
	}

	public void getPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new CorisCaseHistoryAllReport().createReport(request, response);
		} catch (Exception e){
			logger.error("getPDF(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

	public void getSinglePDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] attachment = null;
		try {
			CorisSingleReportParameters parameters = new CorisSingleReportParameters(request, response);
			attachment = new CorisCaseHistorySingleReport().createReport(parameters);
			
			if ("Y".equals(parameters.getEmail().toUpperCase())){
				if (attachment != null){
					// ///////////////////////////////////////////////////////////////
					// Profile Information
					// ///////////////////////////////////////////////////////////////
					ProfileBO profileBO = new ProfileBO(parameters.getCourtType())
					.where(ProfileBO.LOCNCODE, parameters.getLocnCode())
					.where(ProfileBO.COURTTYPE, parameters.getCourtType())
					.find(ProfileBO.COURTTITLE);
					
					// ///////////////////////////////////////////////////////////////
					// Profile Information
					// ///////////////////////////////////////////////////////////////
					KaseBO kaseBO = new KaseBO(parameters.getCourtType())
					.where(KaseBO.CASENUM, parameters.getCaseNumber())
					.where(KaseBO.LOCNCODE, parameters.getLocnCode())
					.where(KaseBO.COURTTYPE, parameters.getCourtType())
					.find(KaseBO.INTCASENUM);
					
					// ///////////////////////////////////////////////////////////////
					// Personnel Information
					// ///////////////////////////////////////////////////////////////
					PersonnelBO personnelBO = new PersonnelBO(parameters.getCourtType())
					.where(PersonnelBO.LOGNAME, parameters.getLogName())
					.where(PersonnelBO.LOCNCODE, parameters.getLocnCode())
					.where(PersonnelBO.COURTTYPE, parameters.getCourtType())
					.find(PersonnelBO.EMAILADDRESS);
					
					// ///////////////////////////////////////////////////////////////
					// Send EMail
					// ///////////////////////////////////////////////////////////////
					CorisNotificationEmailCommon.corisNotificationEmail(
						kaseBO.getIntCaseNum(), 
						Constants.SYSTEM_ID,
						attachment, 
						profileBO.getCourtTitle() + " Case Number " + parameters.getCaseNumber() + " " +	parameters.getFuncId() + " Event", 
						"", 
						personnelBO.getEmailAddress(),
						Constants.RETURN_EMAIL,
				        null,
				        null,
						parameters.getCourtType(), 
						parameters.getCaseNumber() + parameters.getLocnCode() + parameters.getCourtType()+".pdf", 
						false
					);
					
					kaseBO = null; 
					profileBO = null; 
					personnelBO = null;
					attachment = null;
				}
			}	
		} catch (Exception e){
			logger.error("getSinglePDF(HttpServletRequest request, HttpServletResponse response)", e);
		}
		
	}
}
