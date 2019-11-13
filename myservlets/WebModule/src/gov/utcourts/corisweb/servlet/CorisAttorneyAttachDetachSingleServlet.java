package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.partytype.PartyTypeBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.report.ReportBaseSearchCriteria;
import gov.utcourts.coriscommon.util.CorisSecurityCommon;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.report.CorisAttorneysForCaseReportCriteria;
import gov.utcourts.corisweb.report.CorisAttorneysForCaseReportGenerator;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CorisAttorneyAttachDetachSingleServlet
*/
@WebServlet("/CorisAttorneyAttachDetachSingleServlet")
public class CorisAttorneyAttachDetachSingleServlet extends ReportBaseServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorisAttorneyAttachDetachSingleServlet.class.getName());
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyAttachDetachSingleServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "";

			String courtType = URLEncryption.getParamAsString(request, "courtType"); //get passed in value
			if (courtType == null || "".equals(courtType)) {
				courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE); //default	
			}
			request.setAttribute("courtType", courtType);

			String locnCode = URLEncryption.getParamAsString(request, "locnCode"); //get passed in value
			if (locnCode == null || "".equals(locnCode)) {
				locnCode = (String) request.getSession().getAttribute(SessionVariables.LOCN_CODE); //default	
			}
			request.setAttribute("locnCode", locnCode);

			String caseNum = URLEncryption.getParamAsString(request, "caseNum"); //get passed in value
			request.setAttribute("caseNum", caseNum);
			
			String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME); //default

			if(logName == null || "".equals(logName)) {
				page = "/login.jsp";
				getServletContext().getRequestDispatcher(page).forward(request, response);
			} else {
				PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));			
				String action = "";
				switch (mode) {
					case UPDATE:
						updateSingleCase(request, response, courtType, locnCode, logName);
						break;
					case FIND:
						action = URLEncryption.getParamAsString(request, "action");
						if("validateCaseNum".equals(action)){
							validateCaseNum(request, response, courtType, locnCode, caseNum);						
						}
						else if("getParties".equals(action)){
							getSingleCaseParties(request, courtType, locnCode, caseNum);	
							page = "/jsp/corisAttorneyAttachDetachParties.jsp";
							getServletContext().getRequestDispatcher(page).forward(request, response);
						}
						break;
					default:
						defaultPage(request, response, courtType, locnCode, caseNum);
						break;
				}	
				mode = null;
				action = null;
				courtType = null;
				locnCode = null;
				caseNum = null;
				logName = null;
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyAttachDetachSingleServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	private void updateSingleCase(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode, String logName) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		int attyAttachCount = URLEncryption.getParamAsInt(request, "attyAttachCount"); //number of attorneys to be attached
		String caseNum = URLEncryption.getParamAsString(request, "caseNum0"); //single caseNum
		int partyNum = 0;
		String partyCode = "";
		String partyInfo = "";
		int barNum = 0;
		String barState = "";
		int attachUserid = 0;
		String attyType = "";
		Date now;
		Date createDatetime;
		Date attachDatetime;
		int intCaseNum = 0;
		int partyCount = 0;
		String firstName = "";
		String lastName = "";
		try{
			//insert record for each attach attorney (and for each party) for the single case
			int userid = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logName).find().getUseridSrl();
			for(int i=1; i <= attyAttachCount; i++){
				partyCount = URLEncryption.getParamAsInt(request, "partyCount");
				while(partyCount > 0){
					partyInfo = URLEncryption.getParamAsString(request, "selectParties"+partyCount);
					if(!TextUtil.isEmpty(partyInfo)){
						partyCode = partyInfo.substring(0,3);
						partyNum = Integer.parseInt(partyInfo.substring(4));
						if(partyNum > 0){
							barNum = URLEncryption.getParamAsInt(request, "barNumAttach"+i);
							barState = URLEncryption.getParamAsString(request, "barStateAttach"+i);
							AttorneyBO attorneyBO = new AttorneyBO(courtType)
									.where(AttorneyBO.BARNUM, barNum)
									.where(AttorneyBO.BARSTATE, barState)
									.find(AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
							firstName = attorneyBO.getFirstName();
							lastName = attorneyBO.getLastName();
							attachUserid = userid;
							intCaseNum = new KaseBO(courtType)
									.where(KaseBO.CASENUM, caseNum)
									.where(KaseBO.COURTTYPE, courtType)
									.where(KaseBO.LOCNCODE, locnCode)
									.find(KaseBO.INTCASENUM).getIntCaseNum();
							attyType = "P"; //P = Prosecutor; L = Legal Defender; Kristene said to default to "P"
							now = new Date();
							createDatetime = now;
							attachDatetime = now;
							//check to see if the attorney is already attached to the case/party or not, if so then don't attach again
							int count = new AttyPartyBO(courtType)
									.where(AttyPartyBO.BARNUM, barNum)
									.where(AttyPartyBO.BARSTATE, barState)
									.where(AttyPartyBO.INTCASENUM, intCaseNum)
									.where(AttyPartyBO.PARTYNUM, partyNum)
									.where(AttyPartyBO.PARTYCODE, partyCode)
									.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
									.find(AttyPartyBO.BARNUM.count())
									.getCount();
							if(count == 0){
								//attach the attorney
								new AttyPartyBO(courtType)
									.setAttyPartyId(0)
									.setBarNum(barNum)
									.setBarState(barState)
									.setIntCaseNum(intCaseNum)
									.setPartyNum(partyNum)
									.setPartyCode(partyCode)
									.setAttyType(attyType)
									.setCreateDatetime(createDatetime)
									.setAttachDatetime(attachDatetime)
									.setAttachUserid(attachUserid)
									.insert();
								//update the party_case.pro_se column to "N"
								new PartyCaseBO(courtType)
									.setProSe("N")
									.where(PartyCaseBO.INTCASENUM, intCaseNum)
									.where(PartyCaseBO.PARTYCODE, partyCode)
									.where(PartyCaseBO.PARTYNUM, partyNum)
									.update();
							}
						}
					}
					partyCount--;
				}
			}
		}catch(Exception e){
			errorMessage += "There was an error and changes have not been saved.<br>";
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
		retValObj = null;
		errorMessage = null;
		partyCode = null;
		partyInfo = null;
		barState = null;
		attyType = null;
		now = null;
		createDatetime = null;
		attachDatetime = null;
	}
	
	private void validateCaseNum(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode, String caseNum) throws Exception {
		JSONObject retValObj = new JSONObject();
		boolean valid = false;
		List<KaseBO> results = new KaseBO(courtType)
				.where(KaseBO.CASENUM, caseNum)
				.where(KaseBO.COURTTYPE, courtType)
				.where(KaseBO.LOCNCODE, locnCode)
				.search();
		if(results.size() > 0){
			valid = true;
		}
		if (valid) {
			retValObj.put("error", false);
		}else{
			retValObj.put("error", true);
		}
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(retValObj.toString());
		retValObj = null;
	}

	private void defaultPage(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode, String caseNum) throws Exception {
		String page = "/jsp/corisAttorneyAttachDetachSingle.jsp";
		
		//get location lists
		List<LocationBO> locationDistrictListBO = CorisAttorneyUtil.getLocationList(request, "D");
		List<LocationBO> locationJusticeListBO = CorisAttorneyUtil.getLocationList(request, "J");
		request.setAttribute("locationDistrictList", locationDistrictListBO);
		request.setAttribute("locationJusticeList", locationJusticeListBO);
		
		//get the parties for the caseNum
		getSingleCaseParties(request, courtType, locnCode, caseNum);

		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}
	
	private void getSingleCaseParties(HttpServletRequest request, String courtType, String locnCode, String caseNum) throws Exception {
		if(!TextUtil.isEmpty(caseNum)){
			KaseBO results = new KaseBO(courtType);
			results.includeTables(
					new PartyBO(courtType),
					new PartyCaseBO(courtType),
					new PartyTypeBO(courtType)
							.where(PartyTypeBO.CATEGORY, "P")
			);
			results.addJoin(JoinType.LEFT_OUTER_JOIN, PartyCaseBO.TABLE.getTableName(), PartyCaseBO.INTCASENUM, KaseBO.INTCASENUM);
			results.addJoin(JoinType.LEFT_OUTER_JOIN, PartyBO.TABLE.getTableName(), PartyBO.PARTYNUM, PartyCaseBO.PARTYNUM);
			results.addJoin(JoinType.LEFT_OUTER_JOIN, PartyTypeBO.TABLE.getTableName(), PartyTypeBO.PARTYCODE, PartyCaseBO.PARTYCODE);
			results.where(KaseBO.CASENUM, caseNum);
			results.where(KaseBO.LOCNCODE, locnCode);
			results.where(KaseBO.COURTTYPE, courtType);
			results.orderBy(PartyBO.LASTNAME, PartyBO.FIRSTNAME);
			List<KaseBO> singleCasePartyList = results.setDistinct().search(
					PartyCaseBO.PARTYNUM,
					PartyCaseBO.PARTYCODE,
					PartyBO.LASTNAME,
					PartyBO.FIRSTNAME,
					KaseBO.CASETYPE
			);
			request.setAttribute("singleCasePartyList", singleCasePartyList);
			ReportBaseSearchCriteria criteria = (ReportBaseSearchCriteria) generateReportCriteria(request);
			request.setAttribute("userAccess", CorisSecurityCommon.getUserSecurityLevels(criteria, null));
			results = null;
			singleCasePartyList = null;
		}
	}
	
	/**
	 * Sets all the criteria from the incoming form submission (like case number, date range, court type, location) so it can be used when generating or emailing a report. 
	 * 
	 * @param HttpServletRequest request
	 * @return ReportBaseSearchCriteria
	 * @throws Exception 
	 */
	@Override
	ReportBaseSearchCriteria generateReportCriteria(HttpServletRequest request) throws Exception {
		CorisAttorneysForCaseReportCriteria criteria = new CorisAttorneysForCaseReportCriteria(request);
		criteria.setCourtType(URLEncryption.getParamAsString(request, "courtType"));
		if (criteria.getCourtType() == null || "".equals(criteria.getCourtType())) {
			criteria.setCourtType((String) request.getSession().getAttribute(SessionVariables.COURT_TYPE));	
		}
		criteria.setLocnCode(URLEncryption.getParamAsString(request, "locnCode"));
		if (criteria.getLocnCode() == null || "".equals(criteria.getLocnCode())) {
			criteria.setLocnCode((String) request.getSession().getAttribute(SessionVariables.LOCN_CODE));	
		}
		criteria.setLogName((String) request.getSession().getAttribute(SessionVariables.LOG_NAME));
		return criteria;
	}

	/**
	 * This really isn't used for this particular page....it was only needed so we could get the generateReportCriteria() to work
	 * 
	 * @param ReportBaseSearchCriteria criteria
	 * @return byte[]
	 * @throws Exception 
	 */
	@Override
	byte[] generateReport(ReportBaseSearchCriteria criteria) throws Exception {
		//return new CorisAttorneysForCaseReportGenerator(criteria).generateReport((List<KaseBO>) getReportData(criteria));
		return null;
	}

	/**
	 * Query to get the search results.
	 * 
	 * @param ReportBaseSearchCriteria filter
	 * @return List<?>
	 * @throws Exception
	 */
	@Override
	List<?> getReportData(ReportBaseSearchCriteria filter) throws Exception {
		//this is not used for this page...it was only needed so we could get the generateReportCriteria() to work
		return null;
	}
}
