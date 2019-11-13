package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.constants.Constants;
import gov.utcourts.coriscommon.dataaccess.casemevalue.CaseMeValueBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.corisweb.xo.PsParticipantXO;
import gov.utcourts.corisweb.xo.PsReferralXO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.problemsolving.constants.PSCDefnConstants;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO;
import gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO;
import gov.utcourts.problemsolving.dataaccess.psjudgehistory.PsJudgeHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;
import gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO;
import gov.utcourts.problemsolving.dataaccess.psreward.PsRewardBO;
import gov.utcourts.problemsolving.dataaccess.pssanction.PsSanctionBO;
import gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO;
import gov.utcourts.problemsolving.xo.PsReferralHistoryXO;
import gov.utcourts.problemsolving.xo.PsStatusActionXrefXO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;

/**
 * Servlet implementation class PSCReferralsServlet
 */
@WebServlet("/PSCReferralsServlet")
public class PSCReferralsServlet extends BaseServlet {
	private static final long serialVersionUID = -95421145511L;
	
	private static final String REFERRAL_OVERVIEW_PAGE = "/jsp/pscCaseReferrals.jsp";
	private static final String CASE_REFERRAL_LIST = "/jsp/pscCaseReferralList.jsp";
	private static final String PSC_REF_HEAD_PAGE = "/jsp/pscCaseReferralsHeader.jsp";
    
	/**
     * @see BaseServlet#BaseServlet()
     */
    public PSCReferralsServlet() {
        super();
    }

	/* (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = REFERRAL_OVERVIEW_PAGE;
		String mode = URLEncryption.getParamAsString(request, "mode");
		String courtType = getCourtType(request);
		int intCaseNum = URLEncryption.getParamAsInt(request, Constants.INT_CASE_NUM);
		request.setAttribute(Constants.INT_CASE_NUM,intCaseNum);
		int meId = URLEncryption.getParamAsInt(request, Constants.MINUTE_ID);
		String minuteParams = request.getQueryString();
		request.setAttribute("corisMinuteParams", minuteParams);
		
		try {
			if (intCaseNum > 0 && !TextUtil.isEmpty(courtType)) {
				boolean getDeleted = URLEncryption.getParamAsBoolean(request, "displayDeleted");
				List<PsReferralBO> caseRefs = PsReferralXO.findCaseReferrals(intCaseNum, courtType, getDeleted, null);
				if ("createMinuteHistory".equalsIgnoreCase(mode) && !createReferralHistory(request,response)){
					return;
				} else if ("displayReferralHeader".equals(mode)) {
					page = PSC_REF_HEAD_PAGE;
					List<PsParticipantBO> participants = new ArrayList<PsParticipantBO>();
//					if (caseRefs != null && caseRefs.size() > 0) {
//						participants = getReferralCaseParticipants(caseRefs);
//					} else {
						participants.add(PsParticipantXO.getParticipantData(intCaseNum, courtType));
//					}
					setParticipants(request, participants);
				} else if ("list".equalsIgnoreCase(mode)) {

					page = CASE_REFERRAL_LIST;
					request.setAttribute("caseReferrals", caseRefs);
//					if (PsReferralXO.findCaseReferrals(intCaseNum, courtType, false, null).size() > 2) {
//						request.setAttribute("hasActiveReferral", "true");
//					}
					
					PsReferralHistoryBO meHistory = findReferralMeIdHistory(caseRefs, meId, request, courtType);
					Date meDate = URLEncryption.getParamAsDate(request, "minuteDate");
					if (meHistory.getMeId() == meId) {
						if (meDate != null && !DateUtils.isSameDay(meDate, meHistory.getPsActionDate())) {
							meHistory.setPsActionDate(meDate);
							updateMinutesActionDate(meDate, meHistory.getPsHistoryId());
							request.setAttribute("minuteDateUpdated", true);
						}
					}
					request.setAttribute("meReferralHistory", meHistory);
					request.setAttribute("refreshAfterEdit", request.getParameter("refreshAfterEdit"));
				}
			} else {
				request.setAttribute("participants", "No Participants -- Case Not Found");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServletException(e.getMessage());
		}

		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @param meDate
	 * @param psHistoryId
	 * @throws Exception
	 */
	private void updateMinutesActionDate(Date meDate, int psHistoryId) throws Exception {
		new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID,psHistoryId)
		 	.setPsActionDate(meDate).update(PsReferralHistoryBO.PSACTIONDATE);
		new PsRewardBO().where(PsRewardBO.PSHISTORYID,psHistoryId).setPsRewardDate(meDate).update(PsRewardBO.PSREWARDDATE);
		new PsSanctionBO().where(PsSanctionBO.PSHISTORYID,psHistoryId).setPsSanctionDate(meDate).update(PsSanctionBO.PSSANCTIONDATE);
		
	}

	/**
	 * @param caseRefs
	 * @param meId
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	private PsReferralHistoryBO findReferralMeIdHistory(List<PsReferralBO> caseRefs, int meId, HttpServletRequest request, String courtType) throws Exception {
		HashMap<String, Integer> statusIdCodeMap = PsStatusActionXrefXO.getStatusCodeIdMap();
		PsReferralHistoryBO refHist = new PsReferralHistoryBO();
		if(caseRefs.size()==1){
			PsReferralBO refBO = caseRefs.get(0);
			if (refBO.getPsStatusDefnId() != statusIdCodeMap.get(PSCDefnConstants.STATUS_DELETE_CODE) && refBO.getPsStatusDefnId() != statusIdCodeMap.get(PSCDefnConstants.STATUS_TERMINATE_CODE)){
				refHist.setPsReferralId(refBO.getPsReferralId());
			}
		}
		for (PsReferralBO ref : caseRefs) {
			if (ref.getPsStatusDefnId() != statusIdCodeMap.get(PSCDefnConstants.STATUS_DELETE_CODE) && ref.getPsStatusDefnId() != statusIdCodeMap.get(PSCDefnConstants.STATUS_TERMINATE_CODE)) {
				
				List<PsReferralHistoryBO> histList = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, ref.getPsReferralId())
																			  .orderBy(PsReferralHistoryBO.PSREFERRALID, DirectionType.DESC).search();
				for (PsReferralHistoryBO h : histList) {
					if (h.getMeId() > 0 && new CaseMeValueBO(courtType).where(CaseMeValueBO.MEID, h.getMeId()).find() != null) {
						request.setAttribute("hasMinute", "true");
						if (h.getMeId() == meId) {
							refHist = h;
							return refHist;
						}
					}
				}
			}
			
		}
		return refHist;
	}

	/**
	 * @param request
	 * @param participants
	 */
	private void setParticipants(HttpServletRequest request, List<PsParticipantBO> participants) {
		StringBuilder nameStr = new StringBuilder();
		StringBuilder sids = new StringBuilder();
		StringBuilder dobs = new StringBuilder();
		String firstName = null;
		String lastName = null;
		for(PsParticipantBO vo:participants){
			firstName = TextUtil.isEmpty(vo.getPsFirstName())? "":vo.getPsFirstName();
			lastName = TextUtil.isEmpty(vo.getPsLastName())? "":vo.getPsLastName();
			if(nameStr.length() == 0){
				nameStr.append(firstName + " " + lastName );
				sids.append(vo.getPsSid()==0?"":vo.getPsSid());
				if(vo.getPsBirthDate()!=null){
					dobs.append(Constants.dateFormatCoris.format(vo.getPsBirthDate()));
				}
			}else if(!nameStr.toString().contains(vo.getPsFirstName() + " " + vo.getPsLastName())){
				nameStr.append("; " + vo.getPsFirstName() + " " + vo.getPsLastName());
				sids.append("; " + (vo.getPsSid()==0?"":vo.getPsSid()));
				if(vo.getPsBirthDate()!=null){
					dobs.append("; " + Constants.dateFormatCoris.format(vo.getPsBirthDate()));
				}
			}
		}
		request.setAttribute("participants", nameStr.toString());
		request.setAttribute("sids", sids.toString());
		request.setAttribute("dobs", dobs.toString());
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void displayHeader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String page = "/jsp/pscHeader.jsp";
			getServletContext().getRequestDispatcher(page).forward(request, response);
			page = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param caseRefs
	 * @return
	 * @throws Exception
	 */
	private List<PsParticipantBO> getReferralCaseParticipants(List<PsReferralBO> caseRefs) throws Exception {
		List<PsParticipantBO> participants = new ArrayList<PsParticipantBO>();
		PsParticipantBO parti = null;
		for(PsReferralBO vo:caseRefs){
			parti = new PsParticipantBO().where(PsParticipantBO.PSPARTICIPANTID,vo.getPsParticipantId()).find();
			participants.add(parti);
		}
		
		return participants;
	}
	
	/**
	 * @param request
	 * @param mode
	 * @return
	 */
	private String getCourtType(HttpServletRequest request){
		String courtType = URLEncryption.getParamAsString(request, "courtType");
		HttpSession session = request.getSession();
		if (!TextUtil.isEmpty(courtType)) {
			session.setAttribute(SessionVariables.COURT_TYPE, courtType);
		}else {
			courtType = (String) session.getAttribute(SessionVariables.COURT_TYPE);
		}
		return courtType;
	}

	private boolean createReferralHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean success = true;
		int meId = URLEncryption.getParamAsInt(request, "meId");
		Date hearingDate = URLEncryption.getParamAsDate(request, "minuteDate");
		String minuteTitle = URLEncryption.getParamAsString(request, "minuteTitle");
		int psReferralId = TextUtil.getParamAsInt(request, "psReferralId");

		int actionDefnId = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONCODE, PSCDefnConstants.ACTION_HEARING_CODE).find(PsActionDefnBO.PSACTIONDEFNID)
				.getPsActionDefnId();
		if (TextUtil.isEmpty(minuteTitle)) {
			minuteTitle = new PsAppearanceDefnBO().where(PsAppearanceDefnBO.PSACTIONDEFNID, actionDefnId).find(PsAppearanceDefnBO.PSAPPEARANCEDESCR)
					.getPsAppearanceDescr();
		}
		int statusId = new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId).find(PsReferralBO.PSSTATUSDEFNID).getPsStatusDefnId();
		PsReferralHistoryBO refHist = new PsReferralHistoryBO();
		if (insertCorisCaseMeValue(psReferralId, meId)) {
			refHist.setPsActionDate(hearingDate == null ? Calendar.getInstance().getTime() : hearingDate);
			refHist.setPsActionDefnId(actionDefnId);
			refHist.setPsReferralId(psReferralId);
			refHist.setMeId(meId);
			refHist.setCreateDatetime(Calendar.getInstance().getTime());
			refHist.setPsMinutesEventTitle(minuteTitle);
			refHist.setPsLogname((String) request.getSession().getAttribute(SessionVariables.LOG_NAME));
			refHist.setPsStatusDefnId(statusId);
			int histId = PsReferralHistoryXO.savePsReferralHistoryBO(refHist, null);
			refHist.setPsHistoryId(histId);
			new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId).setPsHistoryId(histId).update(PsReferralCaseBO.PSHISTORYID);
		} else {
			request.setAttribute(Constants.MINUTE_ID, "0");
//			deleteReferral(psReferralId);
			response.sendError(400, "Minute cannot be created due to issues with CORIS connection");
			success = false;
		}
		request.setAttribute("meReferralHistory", refHist);
		request.setAttribute("editMeId", refHist.getMeId());
		request.setAttribute("hearingDate", hearingDate);
		return success;
	}
	
	private void deleteReferral(int psReferralId) throws Exception {
		new PsJudgeHistoryBO().where(PsJudgeHistoryBO.PSREFERRALID,psReferralId).delete();
		new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID,psReferralId).delete();
		new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID,psReferralId).delete();
		new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId).delete();
	}

	private boolean insertCorisCaseMeValue(int psReferralId, int meId){
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
												.where(CaseMeValueBO.MEFIELDID, "PSCMIN")
												.where(CaseMeValueBO.MESCREENID, "PSCAPP")
												.where(CaseMeValueBO.MEFIELDSEQ, 1)
												.where(CaseMeValueBO.METYPESEQ, 81)
												.find(CaseMeValueBO.MEID);
			
			int exists = (Integer)caseMeValueBO.get(count);

			caseMeValueBO = null;

			if (exists == 0){
				new CaseMeValueBO(psReferralCaseBO.getJurisdiction())
				.setMeId(meId)
				.setMeTypeSeq(81)
				.setMeScreenId("PSCAPP")
				.setMeFieldId("PSCMIN")
				.setMeFieldSeq(1)
				.setMeFieldValue(null)
				.insert();
			}	
			
		} catch (Exception e){
			log.error("Error to insert case_me_value in Coris due to user exit without saving");
			return false;
		}
		
		return true;
	}
}
