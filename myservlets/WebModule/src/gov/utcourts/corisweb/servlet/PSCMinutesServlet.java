package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO;
import gov.utcourts.problemsolving.dataaccess.psphase.PsPhaseBO;
import gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;
import gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psreward.PsRewardBO;
import gov.utcourts.problemsolving.dataaccess.psrewardcourtxref.PsRewardCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO;
import gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO;
import gov.utcourts.problemsolving.dataaccess.pssanctioncourtxref.PsSanctionCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO;
import gov.utcourts.problemsolving.dataaccess.pssanctionleveldefn.PsSanctionLevelDefnBO;
import gov.utcourts.problemsolving.xo.PsAppearanceDefnXO;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONObject;

/**
* Servlet implementation class PSCMinutesServlet
*/
@WebServlet("/PSCMinutesServlet")
public class PSCMinutesServlet extends BaseServlet implements BaseConstants {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public PSCMinutesServlet() {
      super();
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
			request.setAttribute("parentCornId", URLEncryption.getParamAsString(request, "parentCornId"));
			switch (mode) {
				case ADDEDITGENERAL:
					addEditGeneral(request, response);
					break;
				case DISPLAYDETAILS:
					displayDetails(request, response);
					break;
				case ADDEDITREWARD:
					addEditReward(request, response);
					break;
				case INSERTREWARD:
					insertReward(request, response);
					break;
				case DELETEREWARD:
					deleteReward(request, response);
					break;
				case UPDATEREWARD:
					updateReward(request, response);
					break;
				case ADDEDITSANCTION:
					addEditSanction(request, response);
					break;
				case INSERTSANCTION:
					insertSanction(request, response);
					break;
				case DELETESANCTION:
					deleteSanction(request, response);
					break;
				case UPDATESANCTION:
					updateSanction(request, response);
					break;
				case UPDATEGENERAL:
					updateGeneral(request, response);
				case UPDATETITLE:
					updateEventDescr(request, response);
					break;
				case DELETEMINUTE:
					deleteMinute(request, response);
					break;
				default:
					displayDetails(request, response);
					break;
			}	
			mode = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void deleteMinute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int histId = URLEncryption.getParamAsInt(request, "psHistoryId");
		int psReferralId = URLEncryption.getParamAsInt(request, "psReferralId");
		int meId = URLEncryption.getParamAsInt(request, "meId");
		if(psReferralId == 0){
			int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
			String courtType = URLEncryption.getParamAsString(request, "courtType");
			PsReferralHistoryBO histBo = new PsReferralHistoryBO().where(PsReferralHistoryBO.MEID,meId)
																  .includeTables(new PsReferralCaseBO().where(new FindDescriptor(PsReferralCaseBO.CASEIDENTIFIER,intCaseNum),new FindDescriptor(PsReferralCaseBO.JURISDICTION,courtType)))
																  .addForeignKey(PsReferralHistoryBO.PSREFERRALID,PsReferralCaseBO.PSREFERRALID).find();
			psReferralId = histBo.getPsReferralId();
			histId = histBo.getPsHistoryId();
		}
		
		new PsRewardBO().where(PsRewardBO.PSHISTORYID,histId).delete();
		new PsSanctionBO().where(PsSanctionBO.PSHISTORYID,histId).delete();
		new PsPhaseBO().where(PsPhaseBO.PSHISTORYID,histId).delete();
		new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID,histId).setMeId(0).update(PsReferralHistoryBO.MEID);
		request.setAttribute(Constants.MINUTE_ID, 0);
		if(psReferralId > 0){
			deleteCorisCaseMeValue(psReferralId, meId);	
		}
	}

	private void updateEventDescr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int actionId = URLEncryption.getParamAsInt(request, "actionId");
		HashMap<Integer, String> appearanceDefnMap = PsAppearanceDefnXO.getAppearanceActionDescrMap();
		String appearActionDescr = appearanceDefnMap.get(actionId);
		if(appearActionDescr == null){
			appearActionDescr = "";
		}
		OutputStream o = response.getOutputStream();
		o.write(appearActionDescr.getBytes());
		o.flush();
		o.close();
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insertReward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		try {
			int psHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			DateFormat datetimeFormat = Constants.dateFormatDatetimeMilliseconds;
			String createDatetime = datetimeFormat.format(new Date());
			Date psRewardDate = TextUtil.getParamAsDate(request, "date");
			int psRewardDefnId = TextUtil.getParamAsInt(request, "reward");
			String psRewardNote = TextUtil.getParamAsString(request, "note");
			
			PsRewardBO psRewardBO = new PsRewardBO();
			psRewardBO.setPsRewardId(0);
			psRewardBO.setCreateDatetime(datetimeFormat.parse(createDatetime));
			psRewardBO.setPsHistoryId(psHistoryId);						
			psRewardBO.setPsRewardDate(psRewardDate);
			psRewardBO.setPsRewardDefnId(psRewardDefnId);
			psRewardBO.setPsRewardNote(psRewardNote);
			psRewardBO.insert();
			
			getRewardsList(request, response);
			getSanctionsList(request, response);
		} catch (Exception e){
			errorMessage = "There was an error and changes have not been saved.";
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
		retValObj = null;
		errorMessage = null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateReward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		try {
			int editPsRewardId = TextUtil.getParamAsInt(request, "editPsRewardId");
			int psRewardDefnId = TextUtil.getParamAsInt(request, "reward");
			Date psRewardDate = TextUtil.getParamAsDate(request, "date");
			String psRewardNote = TextUtil.getParamAsString(request, "note");
			
			PsRewardBO psRewardBO = new PsRewardBO();
			psRewardBO.where(PsRewardBO.PSREWARDID, editPsRewardId);
			psRewardBO.setPsRewardDefnId(psRewardDefnId);
			psRewardBO.setPsRewardDate(psRewardDate);
			psRewardBO.setPsRewardNote(psRewardNote);
			psRewardBO.update();

			psRewardBO = null;
			psRewardDate = null;
			psRewardNote = null;
			
		} catch (Exception e){
			errorMessage = "There was an error and changes have not been saved.";
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
		retValObj = null;
		errorMessage = null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteReward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
			int editPsRewardId = TextUtil.getParamAsInt(request, "editPsRewardId");

			PsRewardBO psRewardBO = new PsRewardBO();
			psRewardBO.where(PsRewardBO.PSREWARDID, editPsRewardId);
			psRewardBO.delete();
			psRewardBO = null;

			getRewardsList(request, response);
			getSanctionsList(request, response);

		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insertSanction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		try {
			int psHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			DateFormat datetimeFormat = Constants.dateFormatDatetimeMilliseconds;
			String createDatetime = datetimeFormat.format(new Date());
			Date psSanctionDate = TextUtil.getParamAsDate(request, "date");
			int psSanctionDefnId = TextUtil.getParamAsInt(request, "sanction");
			String psSanctionNote = TextUtil.getParamAsString(request, "note");
//			int psSanctionLevel = TextUtil.getParamAsInt(request, "level");
			int psSanctionAmount = TextUtil.getParamAsInt(request, "amount");
			String psSanctionUnit = TextUtil.getParamAsString(request, "unit");
			
			PsSanctionBO psSanctionBO = new PsSanctionBO();
			psSanctionBO.setPsSanctionId(0);
			psSanctionBO.setCreateDatetime(datetimeFormat.parse(createDatetime));
			psSanctionBO.setPsHistoryId(psHistoryId);						
			psSanctionBO.setPsSanctionDate(psSanctionDate);
			psSanctionBO.setPsSanctionDefnId(psSanctionDefnId);
			psSanctionBO.setPsSanctionNote(psSanctionNote);
//			psSanctionBO.setPsSanctionLevel(psSanctionLevel);
			psSanctionBO.setPsSanctionAmount(psSanctionAmount);
			psSanctionBO.setPsSanctionUnit(psSanctionUnit);
			psSanctionBO.insert();
			
			psSanctionBO = null;
			createDatetime = null;
			psSanctionDate = null;
			psSanctionNote = null;
			datetimeFormat = null;
			psSanctionUnit = null;
			
		} catch (Exception e){
			errorMessage = "There was an error and changes have not been saved.";
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
		retValObj = null;
		errorMessage = null;
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");
			int editPsHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			int psActionDefnId = TextUtil.getParamAsInt(request, "appearanceType");
			PsReferralHistoryBO originalHist = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID,editPsHistoryId).find();
			Date psActionDate = originalHist.getPsActionDate();
			String psActionNote = TextUtil.getParamAsString(request, "historyNote");
			String psLogname = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
			String psMinutesEventTitle = TextUtil.getParamAsString(request, "pscMinuteEventTitle");
			int phaseId = TextUtil.getParamAsInt(request, "phaseId");
			if(phaseId > 0){
				int currentPhaseId = new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId).find().getPsPhaseDefnId();
				if(currentPhaseId != phaseId){
					new PsReferralBO().where(PsReferralBO.PSREFERRALID,psReferralId).setPsPhaseDefnId(phaseId).update(PsReferralBO.PSPHASEDEFNID);
					Date createDatetime = Calendar.getInstance().getTime();
					PsPhaseBO psPhaseBO = new PsPhaseBO();
					psPhaseBO.setCreateDatetime(createDatetime);
					psPhaseBO.setPsHistoryId(editPsHistoryId);
					psPhaseBO.setPsPhaseDefnId(phaseId);
					psPhaseBO.setPsPhaseDate(psActionDate);
					psPhaseBO.insert();
				}
			}
			PsReferralHistoryBO psReferralHistoryBO = new PsReferralHistoryBO();
			psReferralHistoryBO.where(PsReferralHistoryBO.PSHISTORYID, editPsHistoryId);
			psReferralHistoryBO.setPsActionDefnId(psActionDefnId);
			psReferralHistoryBO.setPsActionNote(psActionNote);
			psReferralHistoryBO.setPsLogname(psLogname);
			psReferralHistoryBO.setPsMinutesEventTitle(psMinutesEventTitle);
			psReferralHistoryBO.update();
			
			psReferralHistoryBO = null;
			psActionDate = null;
			psActionNote = null;
			psLogname = null;
			psMinutesEventTitle = null;
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void addEditPhase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int editPsHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			request.setAttribute("editPsHistoryId", editPsHistoryId);
			int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");
			getMinuteDetails(request, response);
			getHistoryDetails(request, editPsHistoryId);
			getPhaseDetails(request, psReferralId);
			String page = "/jsp/pscMinutesPhaseAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEditGeneral(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int editPsHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			request.setAttribute("editPsHistoryId", editPsHistoryId);
			getHistoryDetails(request, editPsHistoryId);
			getMinuteDetails(request, response);
			String page = "/jsp/pscMinutesGeneralAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateSanction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		try {
			int editPsSanctionId = TextUtil.getParamAsInt(request, "editPsSanctionId");
			int psSanctionDefnId = TextUtil.getParamAsInt(request, "sanction");
			Date psSanctionDate = TextUtil.getParamAsDate(request, "date");
			String psSanctionNote = TextUtil.getParamAsString(request, "note");
//			int psSanctionLevel = TextUtil.getParamAsInt(request, "level");
			int psSanctionAmount = TextUtil.getParamAsInt(request, "amount");
			String psSanctionUnit = TextUtil.getParamAsString(request, "unit");
			
			PsSanctionBO psSanctionBO = new PsSanctionBO();
			psSanctionBO.where(PsSanctionBO.PSSANCTIONID, editPsSanctionId);
			psSanctionBO.setPsSanctionDefnId(psSanctionDefnId);
			psSanctionBO.setPsSanctionDate(psSanctionDate);
			psSanctionBO.setPsSanctionNote(psSanctionNote);
//			psSanctionBO.setPsSanctionLevel(psSanctionLevel);
			psSanctionBO.setPsSanctionAmount(psSanctionAmount);
			psSanctionBO.setPsSanctionUnit(psSanctionUnit);
			psSanctionBO.update();
			psSanctionBO = null;

			psSanctionDate = null;
			psSanctionNote = null;
			psSanctionUnit = null;
			
		} catch (Exception e){
			errorMessage = "There was an error and changes have not been saved.";
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
		retValObj = null;
		errorMessage = null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteSanction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
			int editPsSanctionId = TextUtil.getParamAsInt(request, "editPsSanctionId");

			PsSanctionBO psSanctionBO = new PsSanctionBO();
			psSanctionBO.where(PsSanctionBO.PSSANCTIONID, editPsSanctionId);
			psSanctionBO.delete();
			psSanctionBO = null;

			displayDetails(request, response);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEditReward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int editPsRewardId = TextUtil.getParamAsInt(request, "editPsRewardId");
			request.setAttribute("editPsRewardId", editPsRewardId);
			getHistoryDetails(request, URLEncryption.getParamAsInt(request, "psHistoryId"));
			getMinuteDetails(request, response);
			getRewardsList(request, response);
			String page = "/jsp/pscMinutesRewardsAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addEditSanction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int editPsSanctionId = TextUtil.getParamAsInt(request, "editPsSanctionId");
			request.setAttribute("editPsSanctionId", editPsSanctionId);
			getMinuteDetails(request, response);
			int editHistoryId = URLEncryption.getParamAsInt(request, "psHistoryId");
			getHistoryDetails(request, editHistoryId);
			getSanctionsList(request, response);
			getSanctionLevelsList(request, response);
			String page = "/jsp/pscMinutesSanctionsAddEdit.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void displayDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");
			int editHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			request.setAttribute("minuteDateUpdated", TextUtil.getParamAsBooleanValue(request, "minuteDateUpdated"));
			getMinuteDetails(request, response);
			getHistoryDetails(request, editHistoryId);
			getRewardsList(request, response);
			getSanctionsList(request, response);
			getReferralDetails(request,psReferralId);
			String page = "/jsp/pscMinutesDetails.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getMinuteDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psHistoryId = TextUtil.getParamAsInt(request, "psHistoryId");
			//get current rewards
			request.setAttribute("currentRewards", new PsRewardBO().where(PsRewardBO.PSHISTORYID, psHistoryId).search());
			//get current sanctions
			request.setAttribute("currentSanctions", new PsSanctionBO().where(PsSanctionBO.PSHISTORYID, psHistoryId).search());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getRewardsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");
			List<PsRewardCourtXrefBO> results = 
					new PsRewardCourtXrefBO().includeTables(
							new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId),
							new PsRewardDefnBO().where(PsRewardDefnBO.REMOVEDFLAG, "N").orderBy(PsRewardDefnBO.PSREWARDCODE)
					)
					.addJoin(JoinType.LEFT_OUTER_JOIN, PsReferralBO.TABLE.getTableName(), PsReferralBO.COURTPROFILEID, PsRewardCourtXrefBO.COURTPROFILEID)
					.addJoin(JoinType.LEFT_OUTER_JOIN, PsRewardDefnBO.TABLE.getTableName(), PsRewardDefnBO.PSREWARDDEFNID, PsRewardCourtXrefBO.PSREWARDDEFNID)
					.search();	
			request.setAttribute("rewardsList", results);
			results = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getSanctionsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");
			List<PsSanctionCourtXrefBO> results = 
					new PsSanctionCourtXrefBO()
					.includeTables(
					new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId),
					new PsSanctionDefnBO().where(PsSanctionDefnBO.REMOVEDFLAG, "N").orderBy(PsSanctionDefnBO.PSSANCTIONCODE)
					)
					.addJoin(JoinType.LEFT_OUTER_JOIN, PsReferralBO.TABLE.getTableName(), PsReferralBO.COURTPROFILEID, PsSanctionCourtXrefBO.COURTPROFILEID)
					.addJoin(JoinType.LEFT_OUTER_JOIN, PsSanctionDefnBO.TABLE.getTableName(), PsSanctionDefnBO.PSSANCTIONDEFNID, PsSanctionCourtXrefBO.PSSANCTIONDEFNID)
					.search();
			request.setAttribute("sanctionsList", results);
			results = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAppearanceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<PsAppearanceDefnBO> results = new PsAppearanceDefnBO().
				where(PsAppearanceDefnBO.PSAPPEARANCEDEFNID)
				.orderBy(PsAppearanceDefnBO.PSAPPEARANCECODE)
				.search();
			request.setAttribute("appearanceList", results);
			results = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param editHistoryId
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getHistoryDetails(HttpServletRequest request, int editHistoryId) throws ServletException, IOException {
		try{
			PsReferralHistoryBO refHist = new PsReferralHistoryBO();
			if(editHistoryId > 0){
				List<PsReferralHistoryBO> results = new PsReferralHistoryBO().includeFields(PsReferralHistoryBO.ALL_FIELDS)
				.includeTables(new PsAppearanceDefnBO().includeFields(PsAppearanceDefnBO.PSAPPEARANCEDESCR))
				.addJoin(JoinType.LEFT_OUTER_JOIN, PsAppearanceDefnBO.TABLE.getTableName(),PsReferralHistoryBO.PSACTIONDEFNID, PsAppearanceDefnBO.PSACTIONDEFNID)
				.where(PsReferralHistoryBO.PSHISTORYID, editHistoryId)
				.search();
				if(results.size() > 0){
					refHist = results.get(0);
				}
			}
			request.setAttribute("historyDetails", refHist);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getPhaseDetails(HttpServletRequest request, int referralId) throws ServletException, IOException {
		try{
			List<PsPhaseBO> results = new PsPhaseBO().includeFields(PsPhaseBO.ALL_FIELDS)
			.where(PsPhaseBO.PSPHASEID)
			.orderBy(PsPhaseBO.PSPHASEDEFNID)
			.search();
			request.setAttribute("phaseDetails", results);
			results= null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getReferralDetails(HttpServletRequest request, int psReferralId) throws ServletException, IOException {
		try{
			List<PsReferralBO> results = new PsReferralBO().includeFields(PsReferralBO.ALL_FIELDS)
			.includeTables(new PsPhaseDefnBO().includeFields(PsPhaseDefnBO.PSPHASEDESCR))
			.addForeignKey(PsReferralBO.PSPHASEDEFNID, PsPhaseDefnBO.PSPHASEDEFNID)
			.where(PsReferralBO.PSREFERRALID, psReferralId)
			.search();
			request.setAttribute("referralDetails", results.size() > 0?results.get(0):new PsReferralBO());
			results= null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getSanctionLevelsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<PsSanctionLevelDefnBO> results = new PsSanctionLevelDefnBO()
					.orderBy(PsSanctionLevelDefnBO.SANCTIONLEVELSRL)
					.search();
			request.setAttribute("sanctionLevelsList", results);
			results = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param psReferralId
	 * @param meId
	 * @throws ServletException
	 * @throws IOException
	 *//*
	public void insertCorisCaseMeValue(int psReferralId, int meId) throws ServletException, IOException {
		try {
			
			//////////////////////////////////////////////////////////////////////
			// Get PS Referal Case
			//////////////////////////////////////////////////////////////////////
			PsReferralCaseBO psReferralCaseBO = new PsReferralCaseBO();
			psReferralCaseBO.where(PsReferralHistoryBO.PSREFERRALID, psReferralId);
			psReferralCaseBO.find();
			
			/////////////////////////////////////////////////////
			// Does Coris Entry Already Exists
			/////////////////////////////////////////////////////
			FieldOperationDescriptor count = new FieldOperationDescriptor(CaseMeValueBO.MEID, FieldOperationType.COUNT, new TypeInteger());
			
			CaseMeValueBO caseMeValueBO = new CaseMeValueBO(psReferralCaseBO.getJurisdiction())
			.setFieldOperations(count)
			.where(CaseMeValueBO.MEID, meId)
			.where(CaseMeValueBO.MEFIELDID, "PSCMIN")
			.where(CaseMeValueBO.MESCREENID, "PSCAPP")
			.where(CaseMeValueBO.MEFIELDSEQ, 1)
			.where(CaseMeValueBO.METYPESEQ, 81)
			.find(CaseMeValueBO.MEID);
			
			int exists = (Integer)caseMeValueBO.get(count);

			caseMeValueBO = null;

			if (exists == 0){
				CaseMeValueBO caseMeValueBO = new CaseMeValueBO(psReferralCaseBO.getJurisdiction());
				caseMeValueBO.where(CaseMeValueBO.MEID, meId);
				caseMeValueBO.setMeId(meId);
				caseMeValueBO.setMeTypeSeq(81);
				caseMeValueBO.setMeScreenId("PSCAPP");
				caseMeValueBO.setMeFieldId("PSCMIN");
				caseMeValueBO.setMeFieldSeq(1);
				caseMeValueBO.setMeFieldValue("");
				caseMeValueBO.insert();
				caseMeValueBO = null;
			}	
			
			psReferralCaseBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}*/
	/**
	 * @param psReferralId
	 * @param meId
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteCorisCaseMeValue(int psReferralId, int meId) throws ServletException, IOException {
		try {
			
			//////////////////////////////////////////////////////////////////////
			// Get PS Referal Case
			//////////////////////////////////////////////////////////////////////
			PsReferralCaseBO psReferralCaseBO = new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID, psReferralId).find();
			
			/////////////////////////////////////////////////////
			// Does Coris Entry Already Exists
			/////////////////////////////////////////////////////
			FieldOperationDescriptor count = new FieldOperationDescriptor(CaseMeValueBO.MEID, FieldOperationType.COUNT, new TypeInteger());
			
			CaseMeValueBO caseMeValueBO = new CaseMeValueBO(psReferralCaseBO.getJurisdiction())
												.setFieldOperations(count)
												.where(CaseMeValueBO.MEID, meId)
												.where(CaseMeValueBO.METYPESEQ, 81)
												.where(CaseMeValueBO.MESCREENID, "PSCAPP")
												.where(CaseMeValueBO.MEFIELDID, "PSCMIN")
												.where(CaseMeValueBO.MEFIELDSEQ, 1)
												.find(CaseMeValueBO.MEID);
			
			int exists = (Integer)caseMeValueBO.get(count);

			caseMeValueBO = null;

			if (exists > 0){
				CaseMeValueBO caseMeValueTwo = new CaseMeValueBO(psReferralCaseBO.getJurisdiction());
				caseMeValueTwo.where(CaseMeValueBO.MEID, meId);
				caseMeValueTwo.setMeTypeSeq(81);
				caseMeValueTwo.setMeScreenId("PSCAPP");
				caseMeValueTwo.setMeFieldId("PSCMIN");
				caseMeValueTwo.setMeFieldSeq(1);
				caseMeValueTwo.setMeFieldValue("");
				caseMeValueTwo.delete();
				caseMeValueTwo = null;
			}	
			
			psReferralCaseBO = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
