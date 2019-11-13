package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.caseme.CaseMeBO;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.dataaccess.judgehist.JudgeHistBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.coriscommon.xo.SpecialtyCourtXO;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.xo.PsReferralXO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.personcentral.dataaccess.sidpartycentral.SidPartyCentralBO;
import gov.utcourts.personcentral.xo.SidPartyCentralXO;
import gov.utcourts.problemsolving.constants.PSCDefnConstants;
import gov.utcourts.problemsolving.dataaccess.psactioncourtxref.PsActionCourtXrefBO;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO;
import gov.utcourts.problemsolving.dataaccess.psjudgehistory.PsJudgeHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO;
import gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;
import gov.utcourts.problemsolving.dataaccess.pstermination.PsTerminationBO;
import gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO;
import gov.utcourts.problemsolving.xo.PsReferralHistoryXO;
import gov.utcourts.problemsolving.xo.PsStatusActionXrefXO;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.TextUI;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class PSCAddReferralServlet
 */
@WebServlet("/PSCEditReferralServlet")
public class PSCEditReferralServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public PSCEditReferralServlet() {
		super();
	}

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = URLEncryption.getParamAsString(request, "mode");
		int intCaseNum = URLEncryption.getParamAsInt(request, "intCaseNum");
		int meId = URLEncryption.getParamAsInt(request, "meId");
		request.setAttribute(Constants.MINUTE_ID, meId);
		String jurisdiction = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
		Date referralDate = URLEncryption.getParamAsDate(request, "referralDate");
		Connection conn = null;
		PsReferralBO refBO = null;
		request.setAttribute("parentCornId", TextUtil.getParamAsString(request, "parentCornId"));
		String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
		JSONObject retValObj = new JSONObject();
		retValObj.put("error", false);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		try {
			if("updateCourtProfile".equalsIgnoreCase(mode)){
				updateCourtProfileLocation(request, response);
//			}else if("updatePsCourt".equalsIgnoreCase(mode)){
//				updatePsCourt(request, response);
			}else {
				
				int psCourtDefnId = URLEncryption.getParamAsInt(request, "psCourt");
				String psCourtDescr = new PsCourtDefnBO().where(PsCourtDefnBO.PSCOURTDEFNID, psCourtDefnId).find(PsCourtDefnBO.PSCODEDESCR).getPsCodeDescr();
				conn = DatabaseConnection.getConnection(PsReferralBO.PROBLEM_SOLVING_DB);
				conn.setAutoCommit(false);
				if ("add".equalsIgnoreCase(mode)) {
//					if(!findActiveReferral(intCaseNum, jurisdiction, psCourtDefnId, conn)){
						int crtLocnId = URLEncryption.getParamAsInt(request, "courtLocn");
						// Get needed case data from CORIS
						KaseBO corisCaseInfo = SpecialtyCourtXO.getSpecialtyCourtCaseInfo(intCaseNum, jurisdiction, null);
						// Generate PsReferralBO for insertion and insert
						// PsParticipant
						refBO = generatePsReferralBO(corisCaseInfo, crtLocnId, psCourtDefnId, jurisdiction, referralDate, conn);
						// Insert PsReferral and PsReferralHistory
						HashMap<String, Integer> refMap = PsReferralXO.createPsReferral(refBO, logName, conn);
						// Create and insert PsReferralCase data
						PsReferralXO.addPsReferralCase(refMap.get("referralId"), intCaseNum, refMap.get("psHistoryId"), jurisdiction, conn);
						// setup and insert PsJudgeHistory table
						setupAndInsertPsJudgeHistory(refMap.get("referralId"), intCaseNum, corisCaseInfo, referralDate, jurisdiction, conn);
//					}else {
//						retValObj.put("error", true);
//						retValObj.put("errorMessage", "The case has already been referred to " + psCourtDescr + ". Please refer to another problem solving court." );
//					}
				}else { 
					boolean update = true;
//					String actionDefnId = URLEncryption.getParamAsString(request, "actionId");
//					int dashPos = actionDefnId.indexOf('-');
//					if(dashPos>0){
//						actionDefnId = actionDefnId.substring(0,dashPos);
//					}
//					int terminateId = URLEncryption.getParamAsInt(request, "terminateId");
					// Retrieve referral from database
					int referralId = URLEncryption.getParamAsInt(request, "referralId");
					refBO = new PsReferralBO()
							.setUseConnection(conn)
							.where(PsReferralBO.PSREFERRALID, referralId)
							.find();
					int deletedStatusId = new PsStatusDefnBO().setUseConnection(conn).where(PsStatusDefnBO.PSSTATUSCODE, PSCDefnConstants.STATUS_DELETE_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
					if (refBO.getPsStatusDefnId() == deletedStatusId) {
							update = false;
					}
					if ("edit".equalsIgnoreCase(mode) && update) {
//						if(refBO.getPsCourtDefnId() != psCourtDefnId && findActiveReferral(intCaseNum, jurisdiction, psCourtDefnId, conn)){
//							retValObj.put("error", true);
//							retValObj.put("errorMessage", "The case has a referred to " + psCourtDescr + " already. Please select another problem solving court." );
//							update = false;
//						}else {
						updateVOFromRequest(request, refBO);
//						}
					} else if ("deleteReferral".equalsIgnoreCase(mode)) {
						if(referralHasMinute(referralId,(String)request.getSession().getAttribute(Constants.COURT_TYPE), conn)){
							response.setContentType("text/html");
							response.sendError(400, "Referral has active minutes. Please delete or save minute first before delete the referral");
							return;
						}
						refBO.setPsStatusDefnId(deletedStatusId);
					}
					if (update) {
//						String action = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID, Integer.valueOf(actionDefnId)).find(PsActionDefnBO.PSACTIONDESCR)
//								.getPsActionDescr();
						PsReferralXO.updatePsReferral(refBO, conn);
//						int refHistId = PsReferralHistoryXO.savePsReferralActionHistory(refBO.getPsReferralId(), Integer.valueOf(actionDefnId), refBO.getPsStatusDefnId(),
//								"Update for action: " + action, logName, new Date(), conn);
//						if (terminateId > 0) {
//							String terminateDescr = URLEncryption.getParamAsString(request, "terminateNote");
//							if (TextUtil.isEmpty(terminateDescr)) {
//								terminateDescr = new PsTerminationDefnBO().where(PsTerminationDefnBO.PSTERMINATIONDEFNID, terminateId).find(PsTerminationDefnBO.PSTERMINATIONDESCR)
//										.getPsTerminationDescr();
//							}
							// conn.commit(); //commit previous transactions
							// first for the following insert.
//							new PsTerminationBO().setUseConnection(conn).setPsHistoryId(refHistId).setPsTerminationDefnId(terminateId).setPsTerminationNote(terminateDescr)
//								.toString(BaseConstants.PRINT + BaseConstants.RUN).insert();
//						}
					}
				}
				conn.commit();
				response.getWriter().write(retValObj.toString());
			}
			
		} catch (Exception e) {
			log.error("Failed to " + mode + " referral", e);
			retValObj.put("error", true);
			retValObj.put("errorMessage", "Failed to " + mode + " referral");
			response.getWriter().write(retValObj.toString());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error(e.getMessage());
					retValObj.put("error", true);
					retValObj.put("errorMessage", "Failed to " + mode + " referral");
					response.getWriter().write(retValObj.toString());
				}
			}
		}

	}

	private boolean referralHasMinute(int referralId, String courtType, Connection conn) throws Exception {
		List<PsReferralHistoryBO> histList = new PsReferralHistoryBO().setUseConnection(conn).where(PsReferralHistoryBO.PSREFERRALID,referralId).search();
		for(PsReferralHistoryBO vo:histList){
			if(vo.getMeId() > 0){
				if(new CaseMeValueBO(courtType).where(new FindDescriptor(CaseMeValueBO.MEID,vo.getMeId()),
						  new FindDescriptor(CaseMeValueBO.MEFIELDID,"PSCMIN")).find()!=null){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int actionId = URLEncryption.getParamAsInt(request, "actionId");
		int refStatusId = URLEncryption.getParamAsInt(request, "refStatusId");
		
		PsStatusDefnBO defaultStatus = PsStatusActionXrefXO.getDefaultActionStatus(actionId);
		int statusId = defaultStatus==null?refStatusId:defaultStatus.getPsStatusDefnId();
		StringBuilder optString = new StringBuilder();
		List<PsActionDefnBO> actions = PsStatusActionXrefXO.getStatusActions(statusId);
		
		String selected = "";
		for(PsActionDefnBO vo:actions){
			selected = actionId == vo.getPsActionDefnId()?"selected":"";
			optString.append("<option value=\"" + vo.getPsActionDefnId() + "-" + vo.getPsActionCode() + "\"" + selected +">" + vo.getPsActionDescr() + "</option>");
		}
		
		if(actions.size()==0){
			PsActionDefnBO selectedAction = (PsActionDefnBO)new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID,actionId).find();
			optString.append("<option value=\"" + actionId + "-" + selectedAction.getPsActionCode() + "\" selected >" + selectedAction.getPsActionDescr() + "</option>");
		}
		
		optString.append(new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSDEFNID,statusId).find(PsStatusDefnBO.PSSTATUSDESCR).getPsStatusDescr());
		OutputStream os = response.getOutputStream();
		os.write(optString.toString().getBytes());
		os.flush();
		os.close();
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updatePsCourt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int psCourtId = URLEncryption.getParamAsInt(request, "psCourt");
		int actionId = URLEncryption.getParamAsInt(request, "actionId");
		List<PsActionDefnBO> courtActions = new PsActionDefnBO().includeFields(PsActionDefnBO.ALL_FIELDS)
																.includeTables(new PsActionCourtXrefBO().where(PsActionCourtXrefBO.PSCOURTDEFNID,psCourtId))
																.addForeignKey(PsActionDefnBO.PSACTIONDEFNID, PsActionCourtXrefBO.PSACTIONDEFNID)
																.search();
		String selected = "";
		StringBuilder optString = new StringBuilder();
		optString.append("<option value=\"0\"></option>");
		for(PsActionDefnBO vo:courtActions){
			selected = actionId == vo.getPsActionDefnId()?"selected":"";
			optString.append("<option value=\"" + vo.getPsActionDefnId() + "-" + vo.getPsActionCode() + "\"" + selected +">" + vo.getPsActionDescr() + "</option>");
		}
		OutputStream os = response.getOutputStream();
		os.write(optString.toString().getBytes());
		os.flush();
		os.close();
		
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void updateCourtProfileLocation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int crtProfileId = URLEncryption.getParamAsInt(request, "crtProfileId");
		int psCourtId = URLEncryption.getParamAsInt(request, "psCourt");
		List<PsCourtDefnBO> psCourts = new PsCourtDefnBO().includeFields(PsCourtDefnBO.ALL_FIELDS)
														  .includeTables(new PsCourtLocationXrefBO().where(PsCourtLocationXrefBO.COURTPROFILEID,crtProfileId))
														  .addForeignKey(PsCourtDefnBO.PSCOURTDEFNID, PsCourtLocationXrefBO.PSCOURTDEFNID)
														  .search();
		String selected = "";
		StringBuilder optString = new StringBuilder();
		optString.append("<option value=\"0\"></option>");
		for(PsCourtDefnBO vo:psCourts){
			selected = vo.getPsCourtDefnId()==psCourtId?"selected":"";
			optString.append("<option value=\"" + vo.getPsCourtDefnId() + "\"" + selected +">" + vo.getPsCodeDescr() + "</option>");
		}
		
		OutputStream os = response.getOutputStream();
		os.write(optString.toString().getBytes());
		os.flush();
		os.close();
	}

	/**
	 * @param request
	 * @param refVO
	 * @param actionDefnId 
	 * @param actionDefnId 
	 * @throws Exception 
	 */
	private void updateVOFromRequest(HttpServletRequest request, PsReferralBO refBO) throws Exception {
//		PsStatusDefnBO defaultStatus = PsStatusActionXrefXO.getDefaultActionStatus(actionDefnId);
//		int statusId = defaultStatus!=null?defaultStatus.getPsStatusDefnId():0;
		refBO.setPsCourtDefnId(URLEncryption.getParamAsInt(request, "psCourt"));
		refBO.setCourtProfileId(URLEncryption.getParamAsInt(request, "courtLocn"));
		refBO.setPsReferralDate(URLEncryption.getParamAsDate(request, "referralDate"));
//		refBO.setPsPhaseDefnId(URLEncryption.getParamAsInt(request, "phaseId"));
//		if(statusId > 0){
//			refBO.setPsStatusDefnId(statusId);
//		}
		refBO.setPsCompliantSinceDate(URLEncryption.getParamAsDate(request, "complianceDate"));
	}

	/**
	 * @param corisCase
	 * @param psCourtDefnId
	 * @param court
	 * @param referralDate 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private PsReferralBO generatePsReferralBO(KaseBO corisCase, int courtProfileId, int psCourtDefnId, String court, Date referralDate, Connection conn) throws Exception {

		PsReferralBO refBO = null;
		int defPartyNum = new PartyCaseBO(court).where(PartyCaseBO.INTCASENUM,corisCase.getIntCaseNum())
												.where(PartyCaseBO.PARTYCODE,PSCDefnConstants.DEFENDENT_PARTY_CODE).find(PartyCaseBO.PARTYNUM).getPartyNum();
		// Get party's SID
		int sid = 0;
		int participantId = 0;
		SidPartyCentralBO sidParty = SidPartyCentralXO.getSidPartyCentral(defPartyNum, court, null);
		if (sidParty != null) {
			sid = Integer.parseInt(sidParty.getSid());
		}
		if(defPartyNum > 0){
			participantId = insertPsParticipant(sid, defPartyNum, court, null, conn);
		}
		
		if (corisCase != null) {
			refBO = new PsReferralBO();
			refBO.setPsCourtDefnId(psCourtDefnId);
			refBO.setPsReferralDate(referralDate);
			refBO.setPsPhaseDefnId(new PsPhaseDefnBO().setUseConnection(conn).where(PsPhaseDefnBO.PSPHASECODE,PSCDefnConstants.PHASE_NA_CODE).find(PsPhaseDefnBO.PSPHASEDEFNID).getPsPhaseDefnId());
			refBO.setCreateDatetime(Calendar.getInstance().getTime());
			refBO.setPsClerkId(getClerkId(corisCase));
			refBO.setPsAssignedJudgeId(corisCase.getAssnJudgeId());
			refBO.setPsParticipantId(participantId);
			refBO.setCourtProfileId(courtProfileId);
			refBO.setPsStatusDefnId(new PsStatusDefnBO().setUseConnection(conn).where(PsStatusDefnBO.PSSTATUSCODE, PSCDefnConstants.STATUS_REFERRED_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID)
					.getPsStatusDefnId());
		}

		return refBO;
	}

	/**
	 * @param sid
	 * @param partNum
	 * @param courtType
	 * @param corisConn
	 * @param pscConn
	 * @return
	 * @throws Exception
	 */
	private int insertPsParticipant(int sid, Object partNum, String courtType, Connection corisConn, Connection pscConn) throws Exception {

		PartyBO party = new PartyBO(courtType).setUseConnection(corisConn).where(PartyBO.PARTYNUM, partNum).find();

		PsParticipantBO pBO = (PsParticipantBO) new PsParticipantBO()
									.setUseConnection(pscConn)
									.setPsSid(sid)
									.setPsSsn(party.getSsn())
									.setPsLastName(party.getLastName())
									.setPsFirstName(party.getFirstName() == null ? "--" : party.getFirstName())
									.setPsMiddleName(null).setPsNameSuffix(null)
									.setPsBirthDate(party.getBirthDate())
									.insert();

		return pBO.getPsParticipantId();

	}

	/**
	 * @param corisCaseInfo
	 * @return
	 * @throws Exception
	 */
	private int getClerkId(KaseBO corisCaseInfo) throws Exception {
		return (corisCaseInfo.get(CaseMeBO.CLERKID) != null && ((Integer) corisCaseInfo.get(CaseMeBO.CLERKID)) > 0) ? (Integer) corisCaseInfo.get(CaseMeBO.CLERKID) : corisCaseInfo
				.getUseridSrl();
	}

	/**
	 * @param psReferralId
	 * @param intCaseNum
	 * @param corisCaseInfo
	 * @param refDate
	 * @param courtType
	 * @param pscConn
	 * @throws Exception
	 */
	private void setupAndInsertPsJudgeHistory(int psReferralId, int intCaseNum, KaseBO corisCaseInfo, Date refDate, String courtType, Connection pscConn) throws Exception {

		JudgeHistBO judgeHist = new JudgeHistBO(courtType)
					.where(JudgeHistBO.INTCASENUM,intCaseNum)
					.where(JudgeHistBO.JUDGEID,corisCaseInfo.getAssnJudgeId()).find();
		
		
		if(judgeHist != null){
			new PsJudgeHistoryBO().setUseConnection(pscConn)
							  .setPsReferralId(psReferralId)
							  .setPsClerkId(judgeHist.getClerkId())
							  .setPsJudgeStartDatetime(refDate)
							  .setPsJudgeEndDatetime(null)
							  .setPsJudgeId(corisCaseInfo.getAssnJudgeId())
							  .insert();
		}
	}
	
	private boolean findActiveReferral(int intCaseNum, String courtType, int psCourtDefnId, Connection conn) throws Exception{
		List<PsReferralBO> caseRefs = PsReferralXO.findCaseReferrals(intCaseNum, courtType, false, conn);
		for(PsReferralBO bo:caseRefs){
			if(bo.getPsCourtDefnId() == psCourtDefnId){
				return true;
			}
		}
		return false;
	}

}
