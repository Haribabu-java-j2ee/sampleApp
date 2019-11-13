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

import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.commonparty.CommonPartyBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.location.LocationBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.CorisAttorneyUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;

/**
* Servlet implementation class CorisAttorneyAttachDetachBatchServlet
*/
@WebServlet("/CorisAttorneyAttachDetachBatchServlet")
public class CorisAttorneyAttachDetachBatchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CorisAttorneyAttachDetachBatchServlet.class.getName());
     
	/**
   	* @see HttpServlet#HttpServlet()
   	*/
	public CorisAttorneyAttachDetachBatchServlet() {
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
				switch (mode) {
					case UPDATE:
						attachDetachBatchCases(request, response, courtType, locnCode, logName);
						break;
					case FIND:
						validateCaseNum(request, response, courtType, locnCode);						
						break;
					default:
						defaultPage(request, response, courtType, locnCode);
						break;
				}	
				mode = null;
			}
		} catch (Exception e){
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
			logger.error("CorisAttorneyAttachDetachBatchServlet performTask(HttpServletRequest request, HttpServletResponse response)", e);
		}
	}
	
	private void attachDetachBatchCases(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode, String logName) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		int attyAttachCount = TextUtil.getParamAsInt(request, "attyAttachCount"); //number of attorneys to be attached
		int attyDetachCount = TextUtil.getParamAsInt(request, "attyDetachCount"); //number of attorneys to be detached
		int caseCount = TextUtil.getParamAsInt(request, "caseCount"); //number of cases
		int attachUserid = 0;
		int detachUserid = 0;
		String attyType = "";
		int intCaseNum = 0;
		int barNum = 0;
		String barState = "";
		int partyNum = 0;
		String partyCode = "";
		String caseNum = "";
		Date createDatetime;
		Date attachDatetime;
		Date detachDatetime;
		String partyType = TextUtil.getParamAsString(request, "partyType");
		int commonParty = TextUtil.getParamAsInt(request, "commonParty");
		try{
			int userid = new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logName).find().getUseridSrl();
			createDatetime = new Date();
			attachDatetime = new Date();
			detachDatetime = new Date();
			attachUserid = userid;
			detachUserid = userid;
			//insert record for each attach attorney and for each case and for each party (type or common)
			for(int i=1; i <= attyAttachCount; i++){ //for each attorney
				barNum = TextUtil.getParamAsInt(request, "barNumAttach"+i);
				barState = TextUtil.getParamAsString(request, "barStateAttach"+i);
				attyType = "P"; //P = Prosecutor; L = Legal Defender; Kristene said to default to "P"
				for(int j=1; j <= caseCount; j++){ //for each case
					caseNum = TextUtil.getParamAsString(request, "caseNum"+j);
					intCaseNum = new KaseBO(courtType)
							.where(KaseBO.CASENUM, caseNum)
							.where(KaseBO.COURTTYPE, courtType)
							.where(KaseBO.LOCNCODE, locnCode)
							.find(KaseBO.INTCASENUM).getIntCaseNum();					
					if(!TextUtil.isEmpty(partyType) || commonParty > 0){
						if(commonParty > 0){ //for common party
							partyNum = commonParty;
							partyCode = new PartyCaseBO(courtType)
									.where(PartyCaseBO.INTCASENUM, intCaseNum)
									.where(PartyCaseBO.PARTYNUM, partyNum)
									.find().getPartyCode();
							int exists = new PartyCaseBO(courtType)
									.where(PartyCaseBO.PARTYNUM, partyNum)
									.where(PartyCaseBO.INTCASENUM, intCaseNum)
									.find()
									.getPartyNum();
							if (exists > 1) {
								attachAttorney(courtType, barNum, barState, intCaseNum, partyNum, partyCode, attyType, createDatetime, attachDatetime, attachUserid);
							} else {
								errorMessage += "The selected Common Party does not exist on case "+caseNum+" and attorney has not been attached.<br>";
							}
						}else if(!TextUtil.isEmpty(partyType)){ //for party type
							partyCode = TextUtil.getParamAsString(request, "partyType");
							List<KaseBO> kaseBO = new KaseBO(courtType)
									.includeTables(
											new PartyCaseBO(courtType).where(PartyCaseBO.PARTYCODE, partyCode)
									)
									.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
									.where(KaseBO.CASENUM, caseNum)
									.where(KaseBO.LOCNCODE, locnCode)
									.where(KaseBO.COURTTYPE, courtType)
									.search(PartyCaseBO.PARTYNUM);
							for(KaseBO temp : kaseBO){ //for each party type
								partyNum = (Integer) temp.get(PartyCaseBO.PARTYNUM);
								attachAttorney(courtType, barNum, barState, intCaseNum, partyNum, partyCode, attyType, createDatetime, attachDatetime, attachUserid);
							}
						}
					}
				}
			}
			//update record for each detach attorney and for each case and for each party (type or common) 
			for(int i=1; i <= attyDetachCount; i++){ //for each attorney
				barNum = TextUtil.getParamAsInt(request, "barNumDetach"+i);
				barState = TextUtil.getParamAsString(request, "barStateDetach"+i);
				for(int j=1; j <= caseCount; j++){ //for each case
					caseNum = TextUtil.getParamAsString(request, "caseNum"+j);
					intCaseNum = new KaseBO(courtType)
							.where(KaseBO.CASENUM, caseNum)
							.where(KaseBO.COURTTYPE, courtType)
							.where(KaseBO.LOCNCODE, locnCode)
							.find(KaseBO.INTCASENUM).getIntCaseNum();					
					if(!TextUtil.isEmpty(partyType) || commonParty > 0){
						if(commonParty > 0){ //for common party
							partyNum = commonParty;
							partyCode = new PartyCaseBO(courtType)
									.where(PartyCaseBO.INTCASENUM, intCaseNum)
									.where(PartyCaseBO.PARTYNUM, partyNum)
									.find().getPartyCode();
							int exists = new PartyCaseBO(courtType)
									.where(PartyCaseBO.PARTYNUM, partyNum)
									.where(PartyCaseBO.INTCASENUM, intCaseNum)
									.find()
									.getPartyNum();
							if (exists > 1) {
								detachAttorney(courtType, barNum, barState, intCaseNum, partyNum, partyCode, detachDatetime, detachUserid);
							} else {
								errorMessage += "The selected Common Party does not exist on case "+caseNum+" and attorney has not been detached.<br>";
							}
						}else if(!TextUtil.isEmpty(partyType)){ //for party type
							partyCode = TextUtil.getParamAsString(request, "partyType");
							List<KaseBO> kaseBO = new KaseBO(courtType)
									.includeTables(
											new PartyCaseBO(courtType).where(PartyCaseBO.PARTYCODE, partyCode)
									)
									.addForeignKey(KaseBO.INTCASENUM, PartyCaseBO.INTCASENUM)
									.where(KaseBO.CASENUM, caseNum)
									.where(KaseBO.LOCNCODE, locnCode)
									.where(KaseBO.COURTTYPE, courtType)
									.search(PartyCaseBO.PARTYNUM);
							for(KaseBO temp : kaseBO){ //for each party type
								partyNum = (Integer) temp.get(PartyCaseBO.PARTYNUM);
								detachAttorney(courtType, barNum, barState, intCaseNum, partyNum, partyCode, detachDatetime, detachUserid);
							}
						}
					}
				}
			}
		}catch(Exception e){
			errorMessage += "There was an error and changes have not been saved.<br>";
			request.setAttribute("errorMessage", "An error occured and the page could not be loaded properly.");
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
	}
	
	private void detachAttorney(String courtType, int barNum, String barState, int intCaseNum, int partyNum, String partyCode, Date detachDatetime, int detachUserid) throws Exception {
		//make sure the attorney is currently attached and doesn't have a detach datetime so we don't overwrite
		int count = new AttyPartyBO(courtType)
			.where(AttyPartyBO.BARNUM, barNum)
			.where(AttyPartyBO.BARSTATE, barState)
			.where(AttyPartyBO.INTCASENUM, intCaseNum)
			.where(AttyPartyBO.PARTYNUM, partyNum)
			.where(AttyPartyBO.PARTYCODE, partyCode)
			.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
			.find(AttyPartyBO.BARNUM.count())
			.getCount();
		if (count > 0) {
			new AttyPartyBO(courtType)
				.setDetachDatetime(detachDatetime)
				.setDetachUserid(detachUserid)
				.where(AttyPartyBO.BARNUM, barNum)
				.where(AttyPartyBO.BARSTATE, barState)
				.where(AttyPartyBO.INTCASENUM, intCaseNum)
				.where(AttyPartyBO.PARTYNUM, partyNum)
				.where(AttyPartyBO.PARTYCODE, partyCode)
				.update();
			//update the party_case.pro_se column to null, if there are no other attorneys attached to this case/party
			int count2 = new AttyPartyBO(courtType)
					.where(AttyPartyBO.INTCASENUM, intCaseNum)
					.where(AttyPartyBO.PARTYNUM, partyNum)
					.where(AttyPartyBO.PARTYCODE, partyCode)
					.where(AttyPartyBO.DETACHDATETIME, Exp.IS_NULL)
					.find(AttyPartyBO.INTCASENUM.count())
					.getCount();			
			if (count2 == 0) {
				new PartyCaseBO(courtType)
					.setProSe(null)
					.where(PartyCaseBO.INTCASENUM, intCaseNum)
					.where(PartyCaseBO.PARTYCODE, partyCode)
					.where(PartyCaseBO.PARTYNUM, partyNum)
					.update();
			}
		}
	}

	private void attachAttorney(String courtType, int barNum, String barState, int intCaseNum, int partyNum, String partyCode, String attyType, Date createDatetime, Date attachDatetime, int attachUserid) throws Exception {
		//check to see if the attorney is already attached and no detach datetime...if already attached, don't attach again
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

	private void validateCaseNum(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode) throws Exception {
		JSONObject retValObj = new JSONObject();
		String caseNum = TextUtil.getParamAsString(request, "caseNum");
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
		caseNum = null;
	}

	private void defaultPage(HttpServletRequest request, HttpServletResponse response, String courtType, String locnCode) throws Exception {
		String page = "/jsp/corisAttorneyAttachDetachBatch.jsp";

		//get location lists
		List<LocationBO> locationDistrictListBO = CorisAttorneyUtil.getLocationList(request, "D");
		List<LocationBO> locationJusticeListBO = CorisAttorneyUtil.getLocationList(request, "J");
		request.setAttribute("locationDistrictList", locationDistrictListBO);
		request.setAttribute("locationJusticeList", locationJusticeListBO);
		
		//get the common parties for the batch cases
		getCommonParties(request, "D");
		getCommonParties(request, "J");

		getServletContext().getRequestDispatcher(page).forward(request, response);
		page = null;
	}
	
	private void getCommonParties(HttpServletRequest request, String courtType) throws Exception {
		List<CommonPartyBO> commonPartyListBO = new CommonPartyBO(courtType)
				.includeTables(
						new PartyBO(courtType)
				)
				.addForeignKey(PartyBO.PARTYNUM, CommonPartyBO.PARTYNUM)
				.orderBy(PartyBO.LASTNAME, PartyBO.FIRSTNAME)
				.limit(0)
				.search(
					CommonPartyBO.PARTYNUM,
					CommonPartyBO.LOCNCODE,
					CommonPartyBO.COURTTYPE,
					PartyBO.LASTNAME,
					PartyBO.FIRSTNAME
				);
		if ("D".equals(courtType)) {
			request.setAttribute("batchCommonPartyDistrictList", commonPartyListBO);
		} else if ("J".equals(courtType)) {
			request.setAttribute("batchCommonPartyJusticeList", commonPartyListBO);
		}
		commonPartyListBO = null;
	}

}
