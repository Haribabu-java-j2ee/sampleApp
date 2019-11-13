package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
* Servlet implementation class CorisAttorneyAttachDetachTransferServlet
*/
@WebServlet("/CorisAttorneyAttachDetachTransferServlet")
public class CorisAttorneyAttachDetachTransferServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisAttorneyAttachDetachTransferServlet.class.getName());
	private static String errorMessage = "";
	private static String ATTY_TYPE = "P"; //P = Prosecutor; L = Legal Defender; Kristene said to default to "P"
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyAttachDetachTransferServlet() {
	  super();
  	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		errorMessage = "";
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
		
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME); //default

		if(logName == null || "".equals(logName)) {
			page = "/login.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
		} else {
			try {
				PageMode mode = PageMode.resolveEnumFromString(URLEncryption.getParamAsString(request, "mode"));		
				switch (mode) {
					case UPDATE:
						processTransferCases(request, courtType, locnCode, logName);
						break;
					case FIND:
						retValObj.put("casesCount", getCasesCount(request, courtType, locnCode));
						break;
					default:
						defaultPage(request, response, courtType, locnCode);
						break;
				}	
				mode = null;
			} catch (Exception e) {
				request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
				logger.error("CorisAttorneyAttachDetachTransferServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
			}
		}
		
		if (!TextUtil.isEmpty(errorMessage)) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", errorMessage);
		} else {
			retValObj.put("error", false);
		}

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(retValObj.toString());
		
		retValObj = null;
		errorMessage = null;
	}
	
	private void processTransferCases(HttpServletRequest request, String courtType, String locnCode, String logName) throws ServletException, IOException {
		try {
			int userid = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logName).find().getUseridSrl();
			String emailAddress = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logName).find().getEmailAddress();
			List<AttyPartyBO> attyPartyList = getAttyPartyList(request, courtType, locnCode);
			List<BaseVO> valuesAttach = new ArrayList<BaseVO>();
			List<BaseVO> valuesDetach = new ArrayList<BaseVO>();
			List<BaseVO> valuesAttachProSe = new ArrayList<BaseVO>();
			List<BaseVO> valuesDetachProSe = new ArrayList<BaseVO>();
			for (AttyPartyBO attyPartyBO : attyPartyList) { //for each attyParty record

				Date now = new Date();

				//add attach attorney to array
				if (!TextUtil.isEmpty(courtType) && userid > 0 && attyPartyBO.getIntCaseNum() > 0 && TextUtil.getParamAsInt(request, "barNumAttach1") > 0 && !TextUtil.isEmpty(TextUtil.getParamAsString(request, "barStateAttach1")) && attyPartyBO.getPartyNum() > 0 && !TextUtil.isEmpty(attyPartyBO.getPartyCode())) {
					valuesAttach = CorisAttorneyUtil.attachAttorney(valuesAttach, courtType, TextUtil.getParamAsInt(request, "barNumAttach1"), TextUtil.getParamAsString(request, "barStateAttach1"), attyPartyBO.getIntCaseNum(), attyPartyBO.getPartyNum(), attyPartyBO.getPartyCode(), ATTY_TYPE, now, userid);
					valuesAttachProSe = CorisAttorneyUtil.updateAttachProSe(valuesAttachProSe, courtType, attyPartyBO.getPartyNum(), attyPartyBO.getPartyCode(), attyPartyBO.getIntCaseNum());
				}

				//add detach attorney to array
				if (!TextUtil.isEmpty(courtType) && userid > 0) {
					valuesDetach = CorisAttorneyUtil.detachAttorney(valuesDetach, courtType, userid, attyPartyBO.getAttyPartyId(), now);
					valuesDetachProSe = CorisAttorneyUtil.updateDetachProSe(valuesDetachProSe, courtType, attyPartyBO.getPartyNum(), attyPartyBO.getPartyCode(), attyPartyBO.getIntCaseNum());
				}

			}

			//execute the queries to the database
			CorisAttorneyUtil.processBatch(valuesAttach, valuesDetach, valuesAttachProSe, valuesDetachProSe, courtType, emailAddress, attyPartyList.size(), TextUtil.getParamAsInt(request, "barNum"), TextUtil.getParamAsString(request, "barState"), TextUtil.getParamAsInt(request, "barNumAttach1"), TextUtil.getParamAsString(request, "barStateAttach1"));

			valuesAttach = null;
			valuesDetach = null;

		} catch (Exception e) {
			errorMessage += "There was an error and cases have not been transferred.<br>";
			logger.error("CorisAttorneyAttachDetachTransferServlet processTransferCases(HttpServletRequest request)", e);
		}
		
	}
	
	private AttyPartyBO getAttyPartyBO(HttpServletRequest request, String courtType, String locnCode) throws Exception {
		AttyPartyBO attyPartyBO = new AttyPartyBO(courtType);
		try {
			if (TextUtil.getParamAsInt(request, "barNum") > 0 && !TextUtil.isEmpty(TextUtil.getParamAsString(request, "barState"))) {
				KaseBO kaseBO = new KaseBO(courtType);
				if (TextUtil.getParamAsDate(request, "filingDatetimeStart") != null && TextUtil.getParamAsDate(request, "filingDatetimeEnd") != null && TextUtil.getParamAsDate(request, "filingDatetimeStart") != TextUtil.getParamAsDate(request, "filingDatetimeEnd")) {
					kaseBO.where(Exp.LEFT_PARENTHESIS, KaseBO.FILINGDATE, Exp.GREATER_THAN_OR_EQUAL_TO, TextUtil.getParamAsDate(request, "filingDatetimeStart"), Exp.AND, KaseBO.FILINGDATE, Exp.LESS_THAN_OR_EQUAL_TO, TextUtil.getParamAsDate(request, "filingDatetimeEnd"), Exp.RIGHT_PARENTHESIS);
				} else if (TextUtil.getParamAsDate(request, "filingDatetimeStart") != null) {
					kaseBO.where(KaseBO.FILINGDATE, Exp.GREATER_THAN_OR_EQUAL_TO, TextUtil.getParamAsDate(request, "filingDatetimeStart"));
				}
				if (!TextUtil.isEmpty(TextUtil.getParamAsString(request, "caseType"))) {
					kaseBO.where(KaseBO.CASETYPE, TextUtil.getParamAsString(request, "caseType"));
				}
				if (!TextUtil.isEmpty(locnCode)) {
					kaseBO.where(KaseBO.LOCNCODE, locnCode);
				}
				if (!TextUtil.isEmpty(courtType)) {
					kaseBO.where(KaseBO.COURTTYPE, courtType);
				}
				CaseTypeBO caseTypeBO = new CaseTypeBO(courtType);
				if (!TextUtil.isEmpty(TextUtil.getParamAsString(request, "caseCategory"))) {
					caseTypeBO.where(CaseTypeBO.CATEGORY, TextUtil.getParamAsString(request, "caseCategory"));
				}
					attyPartyBO.includeTables(
							kaseBO,
							caseTypeBO
					);
					attyPartyBO.addForeignKey(KaseBO.INTCASENUM, AttyPartyBO.INTCASENUM);
					attyPartyBO.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE);
					attyPartyBO.where(AttyPartyBO.BARNUM, TextUtil.getParamAsInt(request, "barNum"));
					attyPartyBO.where(AttyPartyBO.BARSTATE, TextUtil.getParamAsString(request, "barState"));
					attyPartyBO.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL);
				kaseBO = null;
				caseTypeBO = null;
			}

		} catch (Exception e) {
			logger.error("CorisAttorneyAttachDetachTransferServlet getAttyPartyBO(HttpServletRequest request)", e);
			errorMessage += "An error occurred and cases could not be retrieved.<br>";
		}

		return attyPartyBO;
		
	}
	
	private int getCasesCount(HttpServletRequest request, String courtType, String locnCode) throws Exception {
		AttyPartyBO attyPartyBO = getAttyPartyBO(request, courtType, locnCode);
		return attyPartyBO.find(AttyPartyBO.ATTYPARTYID.count()).getCount();
	}

	private List<AttyPartyBO> getAttyPartyList(HttpServletRequest request, String courtType, String locnCode) throws Exception {
		AttyPartyBO attyPartyBO = getAttyPartyBO(request, courtType, locnCode);
		return attyPartyBO.limit(0).search(
				AttyPartyBO.ATTYPARTYID,
				AttyPartyBO.INTCASENUM,
				AttyPartyBO.PARTYNUM,
				AttyPartyBO.PARTYCODE
				);
	}
	
	private void defaultPage(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode) throws Exception {
		String page = "/jsp/corisAttorneyAttachDetachTransfer.jsp";
		try {
			
			String caseNum = "";
			if (!TextUtil.isEmpty(request.getParameter("caseNum"))) {
				caseNum = TextUtil.getParamAsString(request, "caseNum");
			}
			request.setAttribute("caseNum", caseNum);
			
			String caseType = "";
			if (!TextUtil.isEmpty((String) request.getParameter("caseType"))) {
				caseType = (String) request.getParameter("caseType");
			}
			request.setAttribute("caseType", caseType);
			
			//get location lists
			List<LocationBO> locationDistrictListBO = CorisAttorneyUtil.getLocationList(request, "D");
			List<LocationBO> locationJusticeListBO = CorisAttorneyUtil.getLocationList(request, "J");
			request.setAttribute("locationDistrictList", locationDistrictListBO);
			request.setAttribute("locationJusticeList", locationJusticeListBO);
			
			//get list of case_types
			getCaseTypeList(request, "D");
			getCaseTypeList(request, "J");
			
			caseNum = null;

		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyAttachDetachTransferServlet defaultPage(HttpServletRequest request, HttpServletResponse response)", e);
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}
	
	private void getCaseTypeList(HttpServletRequest request, String courtType) throws Exception {
		try {
			List<CaseTypeBO> caseTypeBO = null;
			caseTypeBO = new CaseTypeBO(courtType)
					.where(CaseTypeBO.REMOVEDFLAG, "N")
					.orderBy(CaseTypeBO.CASETYPE)
					.search();
			if ("D".equals(courtType)) {
				request.setAttribute("caseTypeDistrictList", caseTypeBO);
			}
			if ("J".equals(courtType)) {
				request.setAttribute("caseTypeJusticeList", caseTypeBO);
			}
			caseTypeBO = null;
		} catch (Exception e) {
			logger.error("CorisAttorneyAttachDetachTransferServlet getCaseTypeList(HttpServletRequest request, String courtType)", e);
		}			
	}

}
