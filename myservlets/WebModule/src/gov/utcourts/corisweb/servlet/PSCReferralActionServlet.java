package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO;
import gov.utcourts.problemsolving.dataaccess.psgoal.PsGoalBO;
import gov.utcourts.problemsolving.dataaccess.psphase.PsPhaseBO;
import gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO;
import gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO;
import gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO;
import gov.utcourts.problemsolving.dataaccess.pstermination.PsTerminationBO;
import gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class PSCReferralActionServlet
 */
@WebServlet("/PSCReferralActionServlet")
public class PSCReferralActionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public PSCReferralActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject retValObj = new JSONObject();
		String errorMessage = "";
		
		String mode = URLEncryption.getParamAsString(request, "mode");
		int psReferralId = URLEncryption.getParamAsInt(request, "psReferralId");
		int psHistoryId = URLEncryption.getParamAsInt(request, "psHistoryId");
		int psActionId = URLEncryption.getParamAsInt(request, "actionId");
		Date actionDate = URLEncryption.getParamAsDate(request, "actionDate");
		String psActionNote = URLEncryption.getParamAsString(request, "note");
		int terminateId = URLEncryption.getParamAsInt(request, "terminateId");
		int origTerminateId = 0;
		try {
			if ("deleteAction".equalsIgnoreCase(mode)) {
				new PsTerminationBO().where(PsTerminationBO.PSHISTORYID, psHistoryId).delete();
				new PsPhaseBO().where(PsPhaseBO.PSHISTORYID,psHistoryId).delete();
				new PsGoalBO().where(PsGoalBO.PSHISTORYID,psHistoryId).delete();
				new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID,psHistoryId).delete();
			} else if (psHistoryId == 0) {
				// add new action history
				PsReferralHistoryBO refHist = (PsReferralHistoryBO) new PsReferralHistoryBO().setPsReferralId(psReferralId)
										.setCreateDatetime(Calendar.getInstance().getTime())
										.setPsActionDate(Calendar.getInstance().getTime())
										.setPsActionDefnId(psActionId)
										.setPsActionNote(TextUtil.isEmpty(psActionNote)?null:psActionNote)
										.setPsLogname((String)request.getSession().getAttribute(SessionVariables.LOG_NAME))
										.setPsStatusDefnId(getReferralActionDefaultStatus(psReferralId, psActionId))
										.insert();
				psHistoryId = refHist.getPsHistoryId();
				new PsReferralCaseBO().where(PsReferralCaseBO.PSREFERRALID, psReferralId).setPsHistoryId(psHistoryId).update(PsReferralCaseBO.PSHISTORYID);
			} else {
				new PsReferralHistoryBO().where(PsReferralHistoryBO.PSHISTORYID, psHistoryId)
										.setPsActionDate(actionDate)
										.setPsActionNote(psActionNote)
										.setPsLogname((String)request.getSession().getAttribute(SessionVariables.LOG_NAME))
										.update();
			}
			
			if(origTerminateId != terminateId){
				handleTerminationRecord(terminateId, psActionNote, psHistoryId);
			}
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
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

	}
	
	private void handleTerminationRecord(int terminateId, String psActionNote, int psHistoryId) throws Exception {
		if (TextUtil.isEmpty(psActionNote)) {
			psActionNote = new PsTerminationDefnBO().where(PsTerminationDefnBO.PSTERMINATIONDEFNID, terminateId)
					.find(PsTerminationDefnBO.PSTERMINATIONDESCR).getPsTerminationDescr();
		}
		// conn.commit(); //commit previous transactions
		// first for the following insert.
		new PsTerminationBO().setPsHistoryId(psHistoryId).setPsTerminationDefnId(terminateId).setPsTerminationNote(psActionNote).insert();
	}

	private int getReferralActionDefaultStatus(int psReferralId, int psActionId) throws Exception {
		int statusId = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID, psActionId).find(PsActionDefnBO.DEFAULTPSSTATUSDEFNID).getDefaultPsStatusDefnId();
		if(statusId == 0){
			statusId = new PsReferralBO().where(PsReferralBO.PSREFERRALID, psReferralId).find(PsReferralBO.PSSTATUSDEFNID).getPsStatusDefnId();
		}else {
			 new PsReferralBO().setPsStatusDefnId(statusId).where(PsReferralBO.PSREFERRALID, psReferralId).update(PsReferralBO.PSSTATUSDEFNID);
		}
		return statusId;
	}

}
