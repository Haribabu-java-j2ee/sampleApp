package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO;
import gov.utcourts.coriscommon.dataaccess.trackingtype.TrackingTypeBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.sp.GetCaseTitle;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CaseTrackingServlet
*/
@WebServlet("/CaseTrackingServlet")
public class CaseTrackingServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CaseTrackingServlet.class.getName());
	private static String errorMessage = "";

	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CaseTrackingServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		JSONObject retValObj = new JSONObject();
		errorMessage = "";
		boolean useJSON = true;
		try {
			switch (mode) {
				case SAVE:
					String action = URLEncryption.getParamAsStringFromRequest(request, "action");
					if("add".equals(action)){
						insertTracking(request, response);
					}else if("edit".equals(action)){
						updateTracking(request, response);
					}
					break;
				case ADD:
					useJSON = false;
					displayAddEditForm(request, response);
					break;
				case EDIT:
					displayAddEditForm(request, response);
					break;
				case DISABLE:
					updateEndDate(request, response);
					break;
				default:
					useJSON = false;
					displayTrackingResults(request, response);
					break;
			}	
		} catch (Exception e){
			errorMessage += "An error occurred.<br>";
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CaseTrackingServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		}else{
			retValObj.put("error", false);
		}
		if (useJSON) {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(retValObj.toString());
		}

		mode = null;
		retValObj = null;
		errorMessage = null;
	}
	
	public void displayTrackingResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page = "/jsp/caseTracking.jsp";
		try{
			getTrackingList(request, response);
		}catch(Exception e){
			errorMessage += "Unable to display tracking results.";
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CaseTrackingServlet displayTrackingResults(HttpServletRequest request, HttpServletResponse response)", e);
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);		
		page = null;
	}
	
	public void getTrackingTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String courtType = URLEncryption.getParamAsStringFromRequest(request, "courtType");
		String locnCode = URLEncryption.getParamAsStringFromRequest(request, "locnCode");
		try{
			List<TrackingTypeBO> trackingTypeBO = new TrackingTypeBO(courtType)
				.where(TrackingTypeBO.LOCNCODE, locnCode)
				.where(TrackingTypeBO.COURTTYPE, courtType)
				.where(TrackingTypeBO.REMOVEDFLAG, "N")
				.orderBy(TrackingTypeBO.DESCR)
				.search();
			request.setAttribute("trackingTypeList", trackingTypeBO);
			trackingTypeBO = null;
		}catch(Exception e){
			errorMessage += "Unable to gather tracking type list.<br>";
			logger.error("CaseTrackingServlet getTrackingTypeList(HttpServletRequest request, HttpServletResponse response)", e);
		}
		courtType = null;
		locnCode = null;
	}

	public void getTrackingList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String courtType = URLEncryption.getParamAsStringFromRequest(request, "courtType");
		String locnCode = URLEncryption.getParamAsStringFromRequest(request, "locnCode");
		String caseNum = URLEncryption.getParamAsStringFromRequest(request, "caseNum");
		String locnCodeDescr = URLEncryption.getParamAsStringFromRequest(request, "locnCodeDescr");
		int intCaseNum = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.LOCNCODE, locnCode)
				.find().getIntCaseNum();
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		String caseTitle = GetCaseTitle.getCaseTitle(logName, intCaseNum, courtType, null);
		request.setAttribute("locnCodeDescr", locnCodeDescr);
		request.setAttribute("caseTitle", caseTitle);
		try{
			 List<TrackingBO> trackingBO = new TrackingBO(courtType).as("t")
					    .includeTables(
						    new TrackingTypeBO(courtType).as("tt")
							    .includeFields(TrackingTypeBO.DESCR)
							    .where(TrackingTypeBO.LOCNCODE, locnCode)
							    .where(TrackingTypeBO.COURTTYPE, courtType),
						    new PersonnelBO(courtType).as("p")
						    	.includeFields(new FieldConstant("p.userid_srl").as("CLERKID"), new FieldConstant("p.logname").as("CLERKLOGNAME")),
						    new PersonnelBO(courtType).as("pp")
						    	.includeFields(new FieldConstant("pp.userid_srl").as("ENDCLERKID"), new FieldConstant("pp.logname").as("ENDCLERKLOGNAME"))
					    )
					    .addJoin(JoinType.LEFT, "tracking_type tt", TrackingBO.TRACKCODE, TrackingTypeBO.TRACKCODE)
					    .addJoin(JoinType.LEFT, "personnel p", new FieldConstant("p.userid_srl"), TrackingBO.CLERKID)  
					    .addJoin(JoinType.LEFT, "personnel pp", PersonnelBO.USERIDSRL, TrackingBO.ENDUSERIDSRL)
					    .where(TrackingBO.INTCASENUM, intCaseNum)
					    .orderBy(TrackingBO.REVIEWDATE,DirectionType.DESC)
					    .search();
			request.setAttribute("trackingList", trackingBO);
			trackingBO = null;
		}catch(Exception e){
			errorMessage += "Unable to gather tracking list.<br>";
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly. Unable to gather tracking list.");
			logger.error("CaseTrackingServlet getTrackingList(HttpServletRequest request, HttpServletResponse response)", e);
		}
		caseNum = null;
		courtType = null;
		locnCode = null;
	}
	
	public void displayAddEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page = "/jsp/caseTrackingAddEdit.jsp";
		try{
			getTrackingTypeList(request, response);
		}catch(Exception e){
			errorMessage += "Unable to display form.<br>";
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CaseTrackingServlet displayAddEditForm(HttpServletRequest request, HttpServletResponse response)", e);
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}

	public void insertTracking(HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException {
		String courtType = URLEncryption.getParamAsStringFromRequest(request, "courtType");
		String locnCode = URLEncryption.getParamAsStringFromRequest(request, "locnCode");
		String caseNum = URLEncryption.getParamAsStringFromRequest(request, "caseNum");
		int intCaseNum = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.LOCNCODE, locnCode)
				.where(KaseBO.COURTTYPE, courtType)
				.find().getIntCaseNum();
		String trackCode = URLEncryption.getParamAsStringFromRequest(request, "trackCode");
		Date createDatetime = new Date();
		Date reviewDate = URLEncryption.getParamAsDate(request, "reviewDate");
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		try{
			int clerkId = new PersonnelBO(courtType)
					.where(PersonnelBO.LOGNAME, logName)
					.where(PersonnelBO.COURTTYPE, courtType)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.find()
					.getUseridSrl();
			new TrackingBO(courtType)
				.setIntCaseNum(intCaseNum)
				.setTrackCode(trackCode)
				.setCreateDatetime(createDatetime)
				.setReviewDate(reviewDate)
				.setClerkId(clerkId)
				.insert();
		}catch(Exception e){
			errorMessage += "Unable to add tracking entry.<br>";
			logger.error("CaseTrackingServlet insertTracking(HttpServletRequest request, HttpServletResponse response)", e);
		}
		courtType = null;
	}

	public void updateTracking(HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException {
		String courtType = URLEncryption.getParamAsStringFromRequest(request, "courtType");
		String locnCode = URLEncryption.getParamAsStringFromRequest(request, "locnCode");
		String caseNum = URLEncryption.getParamAsStringFromRequest(request, "caseNum");
		int intCaseNum = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.LOCNCODE, locnCode)
				.where(KaseBO.COURTTYPE, courtType)
				.find().getIntCaseNum();
		String trackCode = URLEncryption.getParamAsStringFromRequest(request, "trackCode");
		Date createDatetime = URLEncryption.getParamAsDate(request, "createDatetime", "yyyy-MM-dd HH:mm:ss.S");
		Date reviewDate = URLEncryption.getParamAsDate(request, "reviewDate");
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		try{
			int clerkId = new PersonnelBO(courtType)
					.where(PersonnelBO.LOGNAME, logName)
					.where(PersonnelBO.COURTTYPE, courtType)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.find()
					.getUseridSrl();
			new TrackingBO(courtType)
				.setReviewDate(reviewDate)
				.setClerkId(clerkId)
				.where(TrackingBO.INTCASENUM, intCaseNum)
				.where(TrackingBO.TRACKCODE, trackCode)
				.where(TrackingBO.CREATEDATETIME, createDatetime)
				.update();
		}catch(Exception e){
			errorMessage += "Unable to update tracking entry.<br>";
			logger.error("CaseTrackingServlet updateTracking(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

	public void updateEndDate(HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException {
		String courtType = URLEncryption.getParamAsStringFromRequest(request, "courtType");
		String locnCode = URLEncryption.getParamAsStringFromRequest(request, "locnCode");
		String caseNum = URLEncryption.getParamAsStringFromRequest(request, "caseNum");
		int intCaseNum = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.LOCNCODE, locnCode)
				.where(KaseBO.COURTTYPE, courtType)
				.find().getIntCaseNum();
		String trackCode = URLEncryption.getParamAsStringFromRequest(request, "trackCode");
		Date createDatetime = URLEncryption.getParamAsDate(request, "createDatetime", "yyyy-MM-dd HH:mm:ss.S");
		Date endDatetime = new Date();
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		try{
			int endUseridSrl = new PersonnelBO(courtType)
					.where(PersonnelBO.LOGNAME, logName)
					.where(PersonnelBO.COURTTYPE, courtType)
					.where(PersonnelBO.LOCNCODE, locnCode)
					.find()
					.getUseridSrl();
			new TrackingBO(courtType)
				.setEndDatetime(endDatetime)
				.setEndUseridSrl(endUseridSrl)
				.where(TrackingBO.INTCASENUM, intCaseNum)
				.where(TrackingBO.TRACKCODE, trackCode)
				.where(TrackingBO.CREATEDATETIME, createDatetime)
				.update();
		}catch(Exception e){
			errorMessage += "Unable to set end date.<br>" + e;
			logger.error("CaseTrackingServlet updateEndDate(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}

}
